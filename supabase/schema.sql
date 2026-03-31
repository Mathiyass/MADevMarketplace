-- MA_Dev Marketplace — Database Schema

-- 1. TABLES

-- Profiles (extends Supabase Auth)
CREATE TABLE IF NOT EXISTS public.profiles (
    id uuid REFERENCES auth.users ON DELETE CASCADE PRIMARY KEY,
    full_name text,
    email text,
    avatar_url text,
    fcm_token text,
    total_spent numeric DEFAULT 0.00,
    created_at timestamptz DEFAULT now(),
    updated_at timestamptz DEFAULT now()
);

-- Products
CREATE TABLE IF NOT EXISTS public.products (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    title text NOT NULL,
    description text,
    price numeric NOT NULL DEFAULT 0.00,
    category text NOT NULL, -- 'ai_models', 'scripts', 'linux_isos', 'tools'
    image_url text,
    file_path text, -- Internal path in 'assets' bucket
    is_active boolean DEFAULT true,
    created_at timestamptz DEFAULT now()
);

-- Orders
CREATE TABLE IF NOT EXISTS public.orders (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id uuid REFERENCES public.profiles(id) ON DELETE SET NULL,
    total_amount numeric NOT NULL,
    status text DEFAULT 'pending', -- 'pending', 'completed', 'failed'
    payment_id text, -- PayHere reference
    created_at timestamptz DEFAULT now(),
    updated_at timestamptz DEFAULT now()
);

-- Order Items
CREATE TABLE IF NOT EXISTS public.order_items (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    order_id uuid REFERENCES public.orders(id) ON DELETE CASCADE,
    product_id uuid REFERENCES public.products(id) ON DELETE SET NULL,
    price_at_purchase numeric NOT NULL,
    license_key text,
    created_at timestamptz DEFAULT now()
);

-- Meetups
CREATE TABLE IF NOT EXISTS public.meetups (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    title text NOT NULL,
    description text,
    location_name text,
    latitude numeric,
    longitude numeric,
    meetup_at timestamptz NOT NULL,
    created_at timestamptz DEFAULT now()
);

-- Broadcast Log (for Admin Panel history)
CREATE TABLE IF NOT EXISTS public.broadcast_log (
    id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    title text,
    body text,
    fcm_ok int DEFAULT 0,
    created_at timestamptz DEFAULT now()
);

-- 2. SECURITY (RLS)

ALTER TABLE public.profiles ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.products ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.orders ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.order_items ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.meetups ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.broadcast_log ENABLE ROW LEVEL SECURITY;

-- Profiles: Users can read/write their own only
CREATE POLICY "Users can view own profile" ON public.profiles FOR SELECT USING (auth.uid() = id);
CREATE POLICY "Users can update own profile" ON public.profiles FOR UPDATE USING (auth.uid() = id);

-- Products: Everyone can read active ones
CREATE POLICY "Public products are viewable" ON public.products FOR SELECT USING (is_active = true);

-- Orders: Users can view their own orders
CREATE POLICY "Users can view own orders" ON public.orders FOR SELECT USING (auth.uid() = user_id);

-- Order Items: Users can view items from their own orders
CREATE POLICY "Users can view own order items" ON public.order_items FOR SELECT USING (
    EXISTS (SELECT 1 FROM public.orders WHERE orders.id = order_items.order_id AND orders.user_id = auth.uid())
);

-- Meetups: Everyone can read
CREATE POLICY "Public meetups are viewable" ON public.meetups FOR SELECT USING (true);

-- Admin Access (Simplified for this project: check against a specific role or just bypass if needed)
-- In a real prod environment, use custom claims for 'admin' role.

-- 3. FUNCTIONS & TRIGGERS

-- Automatically create profile on signup
CREATE OR REPLACE FUNCTION public.handle_new_user()
RETURNS trigger AS $$
BEGIN
  INSERT INTO public.profiles (id, full_name, email)
  VALUES (new.id, new.raw_user_meta_data->>'full_name', new.email);
  RETURN new;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE TRIGGER on_auth_user_created
  AFTER INSERT ON auth.users
  FOR EACH ROW EXECUTE PROCEDURE public.handle_new_user();

-- Update updated_at on change
CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_profiles_modtime BEFORE UPDATE ON public.profiles FOR EACH ROW EXECUTE PROCEDURE update_modified_column();
CREATE TRIGGER update_orders_modtime BEFORE UPDATE ON public.orders FOR EACH ROW EXECUTE PROCEDURE update_modified_column();

-- Atomic increment for user spending (called by webhook)
CREATE OR REPLACE FUNCTION public.increment_user_spend(uid uuid, amount numeric)
RETURNS void AS $$
BEGIN
  UPDATE public.profiles
  SET total_spent = total_spent + amount
  WHERE id = uid;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;
