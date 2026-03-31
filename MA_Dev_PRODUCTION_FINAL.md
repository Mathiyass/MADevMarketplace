# MA_Dev Marketplace — PRODUCTION-READY MASTER PROMPT
# Firebase MCP + Supabase + Stitch UI + PayHere Real Creds
# Paste EVERYTHING inside the triple-backtick block as your first Claude CLI message.

```
You are a world-class senior full-stack mobile engineer, UI/UX designer, and DevOps
architect. Your mission: build "MA_Dev Marketplace" — a production-grade native Android
M-Commerce app for digital developer assets + a production-grade web Admin Panel —
from zero to fully deployed. This is a REAL production project. Every file complete,
every error handled, every security concern addressed. No stubs, no TODOs in logic.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
CARDINAL RULES
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1. STITCH IS LAW — every screen designed in Stitch BEFORE any layout is written
2. FIREBASE MCP FIRST — use firebase-mcp to create project, register app,
   enable FCM, and download google-services.json autonomously — no manual steps
3. MEMORY CHECKPOINTS — save named key before AND after every phase
4. ZERO BROKEN BUILDS — run ./gradlew assembleDebug after every file batch; fix all errors
5. PRODUCTION SECURITY — no secrets in source; all keys in secrets.xml (gitignored)
   or Supabase Edge secrets; RLS on every table; signed URLs only for paid downloads
6. COMPLETE CODE ONLY — every Kotlin file and XML layout 100% implemented

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
REAL CREDENTIALS
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PAYHERE (Sandbox — ready to use):
  Merchant ID:     1222339
  Merchant Secret: MzkwMDEyMDk5NjExOTI3Njc4MjgzNDEyNDU1Njk2MzM2NzAwMDQ5OQ==
  Sandbox Mode:    true
  Sandbox URL:     https://sandbox.payhere.lk/pay/checkout

SUPABASE: retrieved via supabase-mcp-server after project creation
FIREBASE: retrieved via firebase-mcp after project setup
GOOGLE MAPS: YOUR_MAPS_KEY (only manual placeholder)
ADMIN PASSWORD: MADev@Admin2025 (SHA-256 hashed in app.js)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
MCP TOOL EXECUTION MAP
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
sequential-thinking → plan all sub-tasks with dependencies
memory             → named checkpoint after every phase
context7           → SDK docs (supabase-kt, firebase-ktx, room, markwon, payhere)
firecrawl          → scrape PayHere, Supabase, Firebase FCM, Markwon docs
firebase-mcp       → create project, register Android app, enable FCM,
                     get Server Key, download google-services.json
supabase-mcp-server → create tables/RLS/functions/triggers/storage/seed/deploy functions
google-stitch      → generate_screen_from_text + get_screen_code all 18 screens
filesystem         → write ALL project files (Android + Admin + Edge Functions)
terminal           → ./gradlew assembleDebug / lint / test / supabase deploy
github             → create private repo, structured commits, PR, merge
Cloud Run          → deploy_local_folder (admin panel)
playwright         → QA screenshots + console error checks
fetch              → test all 3 Edge Function endpoints

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PROJECT IDENTITY
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
App Name:    MA_Dev Marketplace   |  Package:  com.madev.marketplace
Developer:   Mathisha Angirasa    |  Unit:     JIAT/HHDPII
Min SDK:     24 (Android 7)       |  Target:   34 (Android 14)
Language:    Kotlin               |  Arch:     Clean MVVM + Repository + UseCases

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
DESIGN LANGUAGE (used in every Stitch prompt and every layout file)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
--bg:         #0D1117   --surface:   #161B22   --surface-2: #21262D
--border:     #30363D   --text:      #E6EDF3   --muted:     #8B949E
--accent:     #00E5CC   --success:   #3FB950   --danger:    #F85149
--warning:    #D29922
Category colors: Local_AI=#FF6B6B  Scripts=#4ECDC4  Linux_ISO=#45B7D1  Tools=#FFA07A
Fonts: JetBrains Mono Bold (titles/code/numbers/IDs)  |  Inter (body/labels)
Cards: 8dp radius, 1dp border #30363D, NO shadows, flat design
Buttons: 56dp height, 8dp radius, teal fill or teal outline
Logo: ">_" teal terminal cursor + "~/MA_Dev" JetBrains Mono

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 0 — MASTER PLAN  [sequential-thinking → memory:"P0_plan"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Use sequential-thinking to produce a complete numbered dependency-ordered task list
covering every sub-task across all phases including file names, tool calls, and
success criteria. Save → memory:"P0_plan". Do NOT write any file before this is saved.

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 1 — DEEP RESEARCH  [context7 + firecrawl → memory:"P1_research"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
context7 docs: supabase-kt (postgrest/auth/realtime/storage), firebase-messaging-ktx,
room-ktx, navigation-fragment-ktx, markwon, payhere-android-sdk, security-crypto,
work-runtime-ktx, play-services-maps, okhttp3

firecrawl scrape:
  https://supabase.com/docs/reference/kotlin/introduction
  https://supabase.com/docs/guides/realtime/postgres-changes
  https://supabase.com/docs/guides/storage/serving/signed-urls
  https://supabase.com/docs/guides/functions/quickstart
  https://firebase.google.com/docs/cloud-messaging/android/client
  https://developers.payhere.lk/api-&-sdk/payhere-onsite

Save → memory:"P1_research"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 2 — FIREBASE SETUP VIA MCP  [firebase-mcp → memory:"P2_firebase"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Use firebase-mcp to execute ALL of the following autonomously:

STEP 2.1 — Create Firebase project:
  Project name: "MA Dev Marketplace"
  Project ID:   "madev-marketplace" (or auto if taken)

STEP 2.2 — Register Android app in the project:
  Package name:    com.madev.marketplace
  App nickname:    MA_Dev Marketplace
  SHA-1 fingerprint: run in terminal:
    keytool -list -v -keystore ~/.android/debug.keystore \
      -alias androiddebugkey -storepass android -keypass android 2>/dev/null \
      | grep "SHA1:" | awk '{print $2}'
  Register the app with that SHA-1 via firebase-mcp.

STEP 2.3 — Enable Cloud Messaging (FCM):
  Enable FCM API on the project.
  Retrieve the FCM Legacy Server Key.

STEP 2.4 — Download google-services.json:
  Use firebase-mcp to download and save to:
  ./MADevMarketplace/app/google-services.json

STEP 2.5 — Save all to memory:"P2_firebase":
  { project_id, app_id, fcm_server_key, google_services_path }

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 3 — STITCH UI DESIGN SYSTEM  [google-stitch → filesystem → memory:"P3_stitch"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Generate all 18 screens BEFORE writing any Android code. Extract code from each.
Build DESIGN_SYSTEM.md. Every subsequent layout file MUST match its Stitch reference.

BASE BRIEF (append to every Stitch call — never omit):
  "Dark developer terminal aesthetic. #0D1117 background. Cards #161B22 border
  1px #30363D 8px radius. Text #E6EDF3. Muted #8B949E. Teal accent #00E5CC.
  JetBrains Mono for titles/code/numbers. Inter for body/labels. Flat, no shadows.
  Mobile viewport 393x852px unless specified desktop."

─── ANDROID SCREENS (generate_screen_from_text → get_screen_code → save) ───

S-01 SPLASH: "Full-screen #0D1117. Dead center: '>_' terminal cursor SVG 72dp teal
#00E5CC blink animation. Below: '~/MA_Dev' JetBrains Mono Bold 32sp #E6EDF3. Below:
'initializing system...' 14sp #8B949E animated dots. Bottom 24dp: 'Tap to continue'
11sp #8B949E uppercase tracking-1px. Nothing else. [BASE BRIEF]"
→ ./stitch_outputs/android/S01-splash.html

S-02 ONBOARDING-1: "Onboarding slide 1 of 3. Top-right 'Skip' 13sp #8B949E. Dot
indicators center-top: active dot 24x8dp teal pill, 2x 8dp circle #30363D. Center
40%: 96dp CPU/chip outlined icon teal 2dp stroke. 24dp gap. 'Local AI & Dev Tools.'
JetBrains Mono Bold 26sp. 16dp. 'Download optimized models, debloated scripts, and
productivity tools directly to your local environment.' Inter 15sp #8B949E center 80%w.
Bottom: 'Next →' full-width teal 56dp 8dp radius. [BASE BRIEF]"
→ ./stitch_outputs/android/S02-onboarding1.html

S-03 ONBOARDING-2: "Same layout. Dot 2 active. Icon: download-arrow-circle 96dp teal.
Title: 'Background Downloads.' Body: 'Massive files download silently. Auto-pauses on
Wi-Fi loss and resumes byte-for-byte when reconnected.' Two bottom buttons: 'Back'
ghost 44%w + 'Next →' teal 52%w. [BASE BRIEF]"
→ ./stitch_outputs/android/S03-onboarding2.html

S-04 ONBOARDING-3: "Dot 3 active. Icon: shield-lock 96dp teal. Title: 'Secure.
Licensed. Yours.' Body: 'Every purchase generates a unique license key in your library.
Signed URLs protect every download.' Full-width 'Get Started' teal 56dp. [BASE BRIEF]"
→ ./stitch_outputs/android/S04-onboarding3.html

S-05 LOGIN: "24dp top back-arrow #8B949E. Center 25%: '>_' icon 20dp teal + 'Auth_Required'
JetBrains Mono Bold 28sp teal inline. 8dp: 'Please login to access your library.' Inter
14sp #8B949E. 40dp. Email outlined input 52dp #161B22 fill #30363D border email-icon
20dp left placeholder #8B949E teal focus 2dp. 12dp. Password input same lock-icon left
eye-slash right. 20dp. 'Execute Login' full-width teal 56dp JetBrains Mono Medium.
24dp. Divider '-- OR --' #30363D 13sp #8B949E. 24dp. 'Login with GitHub' outlined
#161B22 border #30363D 56dp github-mark-icon left Inter Medium 15sp #E6EDF3. Bottom
pinned: 'No account? ' #8B949E 14sp + 'Register_Here' teal bold 14sp. [BASE BRIEF]"
→ ./stitch_outputs/android/S05-login.html

S-06 REGISTER: "Same language as login. Title: 'Initialize_Account' teal. Subtitle:
'Create your developer account.' #8B949E. Four inputs: Full Name person-icon, Email
email-icon, Password lock+eye-toggle, Confirm Password lock-check+eye-toggle.
'Create Account' teal 56dp. Bottom: 'Already registered? Login_Here' teal. [BASE BRIEF]"
→ ./stitch_outputs/android/S06-register.html

S-07 CATALOG: "TopAppBar 64dp #0D1117: '~/MA_Dev' JetBrains Mono Bold 17sp left.
'> logged_in_as: dev_root' 11sp teal. Bell icon right with 8dp teal badge. Search bar
52dp full-width #161B22 border #30363D 8dp search-icon left mic-icon right placeholder
'Search models, scripts...' 14sp #8B949E. Horizontal scroll chips: 'All' teal filled,
'Local_AI' #FF6B6B outlined, 'Scripts' #4ECDC4, 'Linux_ISO' #45B7D1, 'Tools' #FFA07A.
2-col grid 8dp gap. Product card #161B22 border #30363D 8dp 12dp padding: 6dp circle
category-color + title JetBrains Mono Bold 14sp. 4dp. Description 2-line Inter 12sp
#8B949E. 8dp. Bottom: '$12.00' teal pill chip, '4.2 GB' chip #21262D, 'v1.2' chip,
'View' outlined teal 28dp right. Bottom nav 64dp #161B22 top-border #30363D 5 items:
terminal-icon Catalog teal-active, books Library, bag Cart teal-badge-2, map-pin Map,
person Profile. [BASE BRIEF]"
→ ./stitch_outputs/android/S07-catalog.html

S-08 PRODUCT DETAIL: "CollapsingToolbarLayout: gradient #FF6B6B→#8B0000 80dp header.
White back+share icons. 'Llama-3-8B-Quantized' JetBrains Mono Bold 20sp white. Collapses
to #161B22 toolbar on scroll. Chips row: 'Local_AI' #FF6B6B pill, 'v1.2.0' #21262D,
'4.2 GB' #21262D, 'ollama' #21262D. Markwon rendered description card #161B22 Inter
15sp. Hardware card #161B22 4dp-left-border #D29922: 'Requires 8GB VRAM | Ollama 0.1.x+'
13sp. 'Preview Code' collapsible card: #0D1117 code block JetBrains Mono 12sp keywords-teal
strings-amber comments-#8B949E. '$12.00' JetBrains Mono Bold 32sp teal. FAB 56dp circle
teal bag-plus-icon bottom-right 24dp. [BASE BRIEF]"
→ ./stitch_outputs/android/S08-detail.html

S-09 LIBRARY: "'My_Library' JetBrains Mono Bold 20sp. 'local storage: 45 GB free' 12sp
#8B949E. TabLayout: 'Purchased' active teal underline 2dp | 'Downloading'. PURCHASED:
cards #161B22: row1 category-dot + title Bold 14sp + version chip right. row2 'License:'
12sp #8B949E + blurred-text + eye-icon. row3 'Download' teal outlined 32dp + 'View Code'
ghost. DOWNLOADING: title + '45%' teal right. Progress bar teal fill on #30363D 4dp.
'1.9 GB / 4.2 GB' 12sp #8B949E. Pause + Cancel icon buttons. Bottom nav. [BASE BRIEF]"
→ ./stitch_outputs/android/S09-library.html

S-10 CART: "'Shopping_Cart' JetBrains Mono Bold 20sp. Cart items #161B22 cards: left
6dp category-dot + title Bold 14sp + 'Digital Download' 12sp #8B949E. Right '$12.00'
JetBrains Mono Bold 15sp teal + X 20dp #F85149. Info banner #161B22 4dp-amber-left-border:
'Shake device to clear cart.' italic 13sp #8B949E. Subtotal/Tax/Total rows. Total Bold
18sp teal. Full-width 'Proceed to Checkout' teal 56dp. Empty state: '> cart is empty.
browse /catalog' mono #8B949E + teal outlined 'Browse Catalog'. Bottom nav. [BASE BRIEF]"
→ ./stitch_outputs/android/S10-cart.html

S-11 CHECKOUT+SUCCESS: "Back + 'Secure_Checkout' JetBrains Mono Bold 20sp. Summary
card #161B22: name + '$12.00' JetBrains Mono Bold 28sp teal. Two radio-select cards
#161B22 (selected=teal 2dp border): Card '... 4242' + PayHere option. Lock icon +
'256-bit encrypted' 12sp #8B949E. 'Pay $12.00 Now' full-width teal 56dp. SUCCESS STATE:
80dp teal checkmark-circle animated. 'Payment_Success' JetBrains Mono Bold 24sp teal.
Receipt card #161B22: TXN-ID mono + item + blurred license. 'Go to My Library' teal.
[BASE BRIEF]"
→ ./stitch_outputs/android/S11-checkout.html

S-12 MAP: "'Tech_Meetups' JetBrains Mono Bold 18sp. '> Find local developer events.'
11sp teal. Dark-style Google Maps 65% screen. Custom 40dp teal circle markers white
number center. Blue pulsing user dot. Bottom sheet peek 35% #161B22: handle bar 40x4dp
#30363D. 'AI Hackathon 2025' JetBrains Mono Bold 16sp. Location + time 12sp #8B949E.
Description 13sp 2-line. 'Add to Calendar' teal outlined 32dp. Bottom nav. [BASE BRIEF]"
→ ./stitch_outputs/android/S12-map.html

S-13 PROFILE: "'User_Profile' JetBrains Mono Bold 20sp. 80dp teal circle white initials
'DR' JetBrains Mono Bold 28sp. 'dev_root' Bold 18sp. 'root@madev.local' 13sp #8B949E.
Three menu cards #161B22 border #30363D 56dp each: gear Settings + chevron, receipt
Billing History + chevron, phone-teal Contact Support + chevron. 'Logout / Exit'
full-width outlined teal 56dp logout-icon. Bottom nav. [BASE BRIEF]"
→ ./stitch_outputs/android/S13-profile.html

─── ADMIN PANEL SCREENS (desktop 1440x900px) ────────────────────────────────

S-14 ADMIN LOGIN (desktop): "Full page #0D1117 subtle 1px dot-grid #161B22 12px.
Center card 440px #161B22 border #30363D 12px radius 48px padding. '>_' SVG 28px teal
+ 'MA_Dev Admin' JetBrains Mono Bold 22px inline. 8px: 'Administrator Access' Inter
13px #8B949E. 32px. Password input 52px #0D1117 border #30363D 8px lock-icon 16px teal
left placeholder-dots #8B949E eye-toggle right. 16px. 'Authenticate' full-width teal
52px JetBrains Mono Medium. 16px. 'Unauthorized access is strictly prohibited.' 12px
#8B949E center."
→ ./stitch_outputs/admin/S14-login.html

S-15 ADMIN DASHBOARD (desktop): "Left sidebar 220px fixed #161B22 border-right 1px
#30363D. Top 24px: '>_' 24px teal + 'MA_Dev' JetBrains Mono Bold 18px + 'Admin Panel'
11px #8B949E below. Nav items 48px 16px-hpad: active=3px-left-border-teal #21262D-bg
teal-icon teal-text. Items: grid Dashboard, box Products, users Users, receipt Orders,
bell Notifications. Main #0D1117 24px pad. Topbar: 'Dashboard' 22px Bold + date 13px
#8B949E right. 4 KPI cards #161B22 border #30363D 8px 20px pad: 'Total Revenue' 13px
#8B949E + 'LKR 142,500' JetBrains Mono Bold 28px teal + up-arrow 12% green. 'Total
Orders' 28px white order-icon #45B7D1. 'Total Users' 28px users-icon #4ECDC4. 'Active
Products' 28px box-icon #FFA07A. Full-width chart card #161B22 teal Chart.js line dark
grid. 'Recent Orders' table alternating #161B22/#21262D."
→ ./stitch_outputs/admin/S15-dashboard.html

S-16 ADMIN PRODUCTS (desktop): "Same sidebar Products active. 'Products' 22px Bold +
'+ Add Product' teal 44dp right. Search + category-dropdown same row. Table #161B22:
Title Bold, Category colored pill, Price mono teal, Version #8B949E mono, Size, Status
toggle green/gray, Actions pencil-teal trash-red arrow-amber 20px icons. Row hover
#21262D. Modal: 600px #161B22 border #30363D 12px full product form dark inputs
backdrop-blur #0D1117-50%."
→ ./stitch_outputs/admin/S16-products.html

S-17 ADMIN USERS (desktop): "Same sidebar Users active. 'Users' 22px + search right.
Table #161B22: 36px teal avatar initials, Name Bold, Email #8B949E, Joined, Orders teal
pill, 'View Orders' teal text. Expandable row #21262D mini-orders-table chevron-animate.
Hover #21262D. Pagination bottom right."
→ ./stitch_outputs/admin/S17-users.html

S-18 ADMIN ORDERS (desktop): "Same sidebar Orders active. 'Orders' 22px + 'Export CSV'
outlined right. Filter tabs: All teal-underline-active, Pending amber badge, Paid green,
Failed red, Refunded gray. Table #161B22: Order-ID mono truncated, Email, Amount teal
bold mono, Status pill, Date, 'Mark Paid' teal text 'Refund' gray text. Hover #21262D.
Pagination bottom."
→ ./stitch_outputs/admin/S18-orders.html

─── DESIGN SYSTEM EXTRACTION ────────────────────────────────────────────────

After ALL 18 screens generated, extract and save:
./stitch_outputs/DESIGN_SYSTEM.md  — colors, typography, spacing, borders,
  component specs (buttons/inputs/cards/chips/pills/bottom-nav/topbar/progress-bar),
  Android XML attribute mapping, admin CSS variables
./stitch_outputs/android/colors.xml   — exact Android colors.xml
./stitch_outputs/android/themes.xml   — exact Android themes.xml
./stitch_outputs/android/styles.xml   — button/input/chip/card styles
./stitch_outputs/admin/design-vars.css — CSS :root variables for admin

memory:"P3_stitch" → "18 screens + design system complete"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 4 — SUPABASE BACKEND  [supabase-mcp-server → memory:"P4_supabase"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Execute ALL SQL in strict order via supabase-mcp-server.

-- EXTENSIONS
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "pg_trgm";

-- ENUMS
CREATE TYPE product_category AS ENUM ('Local_AI','Scripts','Linux_ISO','Tools');
CREATE TYPE payment_status   AS ENUM ('pending','paid','failed','refunded');
CREATE TYPE download_status  AS ENUM ('queued','downloading','paused','complete','error');

-- TABLES
CREATE TABLE public.profiles (
  user_id       UUID PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE,
  full_name     TEXT NOT NULL DEFAULT '',
  avatar_url    TEXT,
  fcm_token     TEXT,
  total_spent   NUMERIC(12,2) NOT NULL DEFAULT 0,
  created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE public.products (
  product_id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  title         TEXT NOT NULL,
  description   TEXT NOT NULL DEFAULT '',
  price         NUMERIC(10,2) NOT NULL CHECK (price >= 0),
  storage_path  TEXT,
  preview_code  TEXT,
  size_mb       NUMERIC(10,2) NOT NULL DEFAULT 0,
  category      product_category NOT NULL,
  version       TEXT NOT NULL DEFAULT '1.0.0',
  tags          TEXT[] NOT NULL DEFAULT '{}',
  requirements  TEXT,
  download_count INT NOT NULL DEFAULT 0,
  is_active     BOOLEAN NOT NULL DEFAULT true,
  created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE public.orders (
  order_id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id         UUID NOT NULL REFERENCES public.profiles(user_id),
  total_amount    NUMERIC(10,2) NOT NULL CHECK (total_amount >= 0),
  payment_status  payment_status NOT NULL DEFAULT 'pending',
  payment_ref     TEXT,
  currency        TEXT NOT NULL DEFAULT 'LKR',
  order_date      TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE public.order_items (
  item_id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  order_id     UUID NOT NULL REFERENCES public.orders(order_id) ON DELETE CASCADE,
  product_id   UUID NOT NULL REFERENCES public.products(product_id),
  price_paid   NUMERIC(10,2) NOT NULL,
  license_key  TEXT NOT NULL DEFAULT gen_random_uuid()::TEXT,
  UNIQUE(order_id, product_id)
);

CREATE TABLE public.meetups (
  meetup_id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  title        TEXT NOT NULL,
  description  TEXT,
  lat          DOUBLE PRECISION NOT NULL,
  lng          DOUBLE PRECISION NOT NULL,
  location     TEXT,
  starts_at    TIMESTAMPTZ,
  is_active    BOOLEAN NOT NULL DEFAULT true,
  created_at   TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE public.notifications_log (
  id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id      UUID REFERENCES public.profiles(user_id) ON DELETE SET NULL,
  title        TEXT NOT NULL,
  body         TEXT NOT NULL,
  data         JSONB,
  is_broadcast BOOLEAN NOT NULL DEFAULT false,
  fcm_success  INT NOT NULL DEFAULT 0,
  sent_at      TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- INDEXES
CREATE INDEX idx_products_category    ON public.products(category) WHERE is_active = true;
CREATE INDEX idx_products_fts         ON public.products USING GIN(to_tsvector('english', title || ' ' || description));
CREATE INDEX idx_products_tags        ON public.products USING GIN(tags);
CREATE INDEX idx_orders_user          ON public.orders(user_id);
CREATE INDEX idx_orders_status        ON public.orders(payment_status);
CREATE INDEX idx_order_items_order    ON public.order_items(order_id);
CREATE INDEX idx_order_items_product  ON public.order_items(product_id);

-- TRIGGER FUNCTIONS
CREATE OR REPLACE FUNCTION public.handle_new_user()
RETURNS TRIGGER LANGUAGE plpgsql SECURITY DEFINER SET search_path = public AS $$
BEGIN
  INSERT INTO public.profiles(user_id, full_name, avatar_url)
  VALUES(NEW.id,
    COALESCE(NEW.raw_user_meta_data->>'full_name', split_part(NEW.email,'@',1)),
    NEW.raw_user_meta_data->>'avatar_url')
  ON CONFLICT(user_id) DO NOTHING;
  RETURN NEW;
END; $$;
CREATE TRIGGER on_auth_user_created AFTER INSERT ON auth.users
  FOR EACH ROW EXECUTE FUNCTION public.handle_new_user();

CREATE OR REPLACE FUNCTION public.set_updated_at()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN NEW.updated_at = now(); RETURN NEW; END; $$;
CREATE TRIGGER profiles_updated_at BEFORE UPDATE ON public.profiles FOR EACH ROW EXECUTE FUNCTION public.set_updated_at();
CREATE TRIGGER products_updated_at BEFORE UPDATE ON public.products FOR EACH ROW EXECUTE FUNCTION public.set_updated_at();

CREATE OR REPLACE FUNCTION public.on_order_paid()
RETURNS TRIGGER LANGUAGE plpgsql SECURITY DEFINER AS $$
BEGIN
  IF NEW.payment_status = 'paid' AND OLD.payment_status != 'paid' THEN
    UPDATE public.products p SET download_count = download_count + 1
    FROM public.order_items oi WHERE oi.order_id = NEW.order_id AND oi.product_id = p.product_id;
    UPDATE public.profiles SET total_spent = total_spent + NEW.total_amount WHERE user_id = NEW.user_id;
  END IF;
  RETURN NEW;
END; $$;
CREATE TRIGGER order_paid_trigger AFTER UPDATE ON public.orders
  FOR EACH ROW EXECUTE FUNCTION public.on_order_paid();

-- FCM HELPER FUNCTIONS
CREATE OR REPLACE FUNCTION public.get_product_buyer_fcm_tokens(p_product_id UUID)
RETURNS TEXT[] LANGUAGE SQL SECURITY DEFINER AS $$
  SELECT ARRAY_AGG(DISTINCT pr.fcm_token)
  FROM public.order_items oi
  JOIN public.orders o ON o.order_id = oi.order_id
  JOIN public.profiles pr ON pr.user_id = o.user_id
  WHERE oi.product_id = p_product_id AND o.payment_status = 'paid' AND pr.fcm_token IS NOT NULL;
$$;

CREATE OR REPLACE FUNCTION public.get_all_fcm_tokens()
RETURNS TEXT[] LANGUAGE SQL SECURITY DEFINER AS $$
  SELECT ARRAY_AGG(DISTINCT fcm_token) FROM public.profiles WHERE fcm_token IS NOT NULL;
$$;

CREATE OR REPLACE FUNCTION public.search_products(query TEXT)
RETURNS SETOF public.products LANGUAGE SQL AS $$
  SELECT * FROM public.products WHERE is_active = true AND (
    to_tsvector('english', title || ' ' || description) @@ plainto_tsquery('english', query)
    OR title ILIKE '%' || query || '%' OR query = ANY(tags))
  ORDER BY download_count DESC;
$$;

-- RLS
ALTER TABLE public.profiles          ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.products          ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.orders            ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.order_items       ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.meetups           ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.notifications_log ENABLE ROW LEVEL SECURITY;

CREATE POLICY "own profile"         ON public.profiles FOR ALL USING (auth.uid() = user_id);
CREATE POLICY "read active products" ON public.products FOR SELECT TO authenticated USING (is_active = true);
CREATE POLICY "service products"    ON public.products FOR ALL TO service_role USING (true);
CREATE POLICY "own orders"          ON public.orders FOR ALL USING (auth.uid() = user_id);
CREATE POLICY "service orders"      ON public.orders FOR ALL TO service_role USING (true);
CREATE POLICY "own order items"     ON public.order_items FOR SELECT USING (
  EXISTS(SELECT 1 FROM public.orders o WHERE o.order_id = order_items.order_id AND o.user_id = auth.uid()));
CREATE POLICY "service order items" ON public.order_items FOR ALL TO service_role USING (true);
CREATE POLICY "public meetups"      ON public.meetups FOR SELECT USING (is_active = true);
CREATE POLICY "service notifications" ON public.notifications_log FOR ALL TO service_role USING (true);

-- STORAGE BUCKETS
-- Create bucket "assets": public=false, fileSizeLimit=5368709120, allowedMimeTypes=null
-- Create bucket "avatars": public=true, fileSizeLimit=5242880, allowedMimeTypes=["image/jpeg","image/png","image/webp"]

CREATE POLICY "paid download only" ON storage.objects FOR SELECT USING (
  bucket_id = 'assets' AND auth.role() = 'authenticated' AND
  EXISTS(SELECT 1 FROM public.order_items oi
    JOIN public.orders o ON o.order_id = oi.order_id
    JOIN public.products p ON p.product_id = oi.product_id
    WHERE o.user_id = auth.uid() AND o.payment_status = 'paid' AND p.storage_path = storage.objects.name));

CREATE POLICY "own avatar" ON storage.objects FOR ALL USING (
  bucket_id = 'avatars' AND auth.uid()::TEXT = (storage.foldername(name))[1]);

-- SEED PRODUCTS (5 products with full markdown descriptions)
INSERT INTO public.products(title,description,price,size_mb,category,version,tags,preview_code,storage_path,requirements) VALUES
('Llama-3-8B-Quantized',
 E'## Llama 3 8B — Q4_K_M Quantized\n\nHighly optimized **4-bit quantized** LLM. Best quality-to-size ratio.\n\n### Includes\n- Pre-quantized GGUF file\n- Custom Modelfile\n- Python & Node.js examples\n- Benchmark results',
 12.00,4200,'Local_AI','v1.2.0',ARRAY['llm','ollama','llama3','quantized'],
 E'ollama run llama3:8b-instruct-q4_K_M\n\nimport ollama\nresponse = ollama.chat(\n  model="llama3",\n  messages=[{"role":"user","content":"Hello"}]\n)\nprint(response["message"]["content"])',
 'models/llama3-8b-q4.zip','GPU: 8GB VRAM | CPU: 16GB RAM | Ollama 0.1.x+'),

('WA_AutoResponder.py',
 E'## WhatsApp Auto Responder\n\nAutomated **WhatsApp Business** reply bot. Undetectable headless Chrome.\n\n### Features\n- Keyword trigger rules\n- Scheduled reply windows\n- Anti-detection headers\n- Multi-number support',
 5.00,12,'Scripts','py3.10',ARRAY['python','selenium','whatsapp','automation'],
 E'options = Options()\noptions.add_argument("--headless=new")\noptions.add_experimental_option("excludeSwitches", ["enable-automation"])\ndriver = webdriver.Chrome(options=options)\ndriver.get("https://web.whatsapp.com")',
 'scripts/wa_autoresponder.zip',NULL),

('Arch_Debloat.sh',
 E'## Arch Linux Debloat v2.1\n\nRemove **40+ bloat packages**. Cuts boot 30%, reduces idle RAM 200MB.\n\n### Removes\n- Bluetooth stack\n- Avahi/mDNS\n- CUPS printing\n- Unused firmware blobs',
 3.50,0.5,'Linux_ISO','v2.1',ARRAY['arch','bash','linux','debloat'],
 E'#!/bin/bash\nset -euo pipefail\npacman -Rns $(pacman -Qqdt) --noconfirm 2>/dev/null || true\nfor svc in bluetooth avahi-daemon cups; do\n  systemctl disable --now "$svc" 2>/dev/null || true\ndone\necho "Reboot recommended."',
 'scripts/arch_debloat.zip',NULL),

('DevProxy_Manager',
 E'## DevProxy Manager\n\nGUI tool for **multiple proxy configs**. Switch instantly.\n\n### Supports\n- HTTP/HTTPS proxies\n- SOCKS5 tunnels\n- Custom PAC file hosting\n- Per-app overrides',
 8.00,45,'Tools','v1.0.0',ARRAY['proxy','networking','gui','java'],
 E'SwingUtilities.invokeLater(() -> {\n  try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }\n  catch (Exception ignored) {}\n  new ProxyManagerFrame().setVisible(true);\n});',
 'tools/devproxy_manager.zip','Java 11+ | Windows/Ubuntu/macOS'),

('Ubuntu_Minimal_AI.iso',
 E'## Ubuntu Minimal AI 24.04\n\nStripped Ubuntu 24.04 LTS. AI-ready on first boot. 2.1GB smaller than standard.\n\n### Pre-installed\n- CUDA 12.5 + cuDNN 9.x\n- Python 3.11 + Ollama 0.3.x\n- Docker CE 26 + NVIDIA Container Toolkit',
 15.00,890,'Linux_ISO','v24.04',ARRAY['ubuntu','cuda','ollama','docker','iso'],
 E'# Flash to USB:\ndd if=ubuntu_minimal_ai.iso of=/dev/sdX bs=4M status=progress && sync\n\n# Default credentials:\n# user: madev | pass: changeme123 -- CHANGE IMMEDIATELY\n\nnvidia-smi && python3 -c "import torch; print(torch.cuda.is_available())"',
 'iso/ubuntu_minimal_ai.iso','x86_64 | NVIDIA GPU recommended | 16GB USB | 20GB disk');

INSERT INTO public.meetups(title,description,lat,lng,location,starts_at) VALUES
('AI Hackathon 2025','Build local AI tools in 24hrs. Prizes for best Ollama integration. Food provided.',6.9271,79.8612,'Colombo Innovation Hub',now()+interval '7 days'),
('Linux Users Sri Lanka','Monthly meetup: Arch vs NixOS deep dive. All levels welcome.',7.2906,80.6337,'Kandy Tech Space',now()+interval '14 days');

-- NOTE FOR README: Create Supabase DB Webhook manually in Dashboard:
-- Table: products | Events: UPDATE
-- URL: https://{PROJECT}.supabase.co/functions/v1/send-push-notification
-- Header: Authorization: Bearer {SERVICE_ROLE_KEY}

Save URL+anon+service_role → memory:"P4_supabase"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 5 — EDGE FUNCTIONS  [filesystem → supabase-mcp deploy → memory:"P5_functions"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Write all 3 functions then deploy via supabase-mcp-server.

─── supabase/functions/send-push-notification/index.ts ─────────────────────
Firebase FCM REST API. Handles DB webhook (product version change → notify buyers)
and admin broadcasts. Chunks 999 tokens per FCM call. Logs to notifications_log.

import { serve } from "https://deno.land/std@0.177.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
const FCM = "https://fcm.googleapis.com/fcm/send"
const cors = {"Access-Control-Allow-Origin":"*","Access-Control-Allow-Headers":"authorization,x-client-info,apikey,content-type"}
serve(async(req)=>{
  if(req.method==="OPTIONS") return new Response("ok",{headers:cors})
  try{
    const body=await req.json()
    const admin=createClient(Deno.env.get("SUPABASE_URL")!,Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")!)
    let title="",message="",tokens:string[]=[],data:Record<string,string>={}
    if(body.type==="UPDATE"&&body.record?.version!==body.old_record?.version){
      title="Update Available"; message=`${body.record.title} updated to ${body.record.version}`
      data={type:"product_update",product_id:body.record.product_id,version:body.record.version}
      const {data:t}=await admin.rpc("get_product_buyer_fcm_tokens",{p_product_id:body.record.product_id})
      tokens=(t??[]).filter(Boolean)
    } else if(body.type==="broadcast"){
      title=body.title??"MA_Dev"; message=body.message??""
      data={type:"broadcast"}
      const {data:t}=await admin.rpc("get_all_fcm_tokens")
      tokens=(t??[]).filter(Boolean)
    } else return new Response(JSON.stringify({error:"Unknown type"}),{status:400,headers:cors})
    if(!tokens.length) return new Response(JSON.stringify({sent:0}),{status:200,headers:cors})
    const results=[]
    for(let i=0;i<tokens.length;i+=999){
      const r=await fetch(FCM,{method:"POST",
        headers:{"Content-Type":"application/json","Authorization":`key=${Deno.env.get("FIREBASE_SERVER_KEY")}`},
        body:JSON.stringify({registration_ids:tokens.slice(i,i+999),
          notification:{title,body:message,sound:"default",icon:"ic_notification",color:"#00E5CC"},
          data,priority:"high",content_available:true})})
      results.push(await r.json())
    }
    const success=results.reduce((s,r)=>s+(r.success??0),0)
    await admin.from("notifications_log").insert({title,body:message,data,is_broadcast:body.type==="broadcast",fcm_success:success})
    return new Response(JSON.stringify({sent:tokens.length,fcm_success:success}),{status:200,headers:{...cors,"Content-Type":"application/json"}})
  }catch(e){return new Response(JSON.stringify({error:e.message}),{status:500,headers:cors})}
})

─── supabase/functions/generate-signed-url/index.ts ────────────────────────
Verifies paid purchase then returns 1-hour signed download URL.
Never exposes storage_path to client.

import { serve } from "https://deno.land/std@0.177.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
const cors={"Access-Control-Allow-Origin":"*","Access-Control-Allow-Headers":"authorization,x-client-info,apikey,content-type"}
serve(async(req)=>{
  if(req.method==="OPTIONS") return new Response("ok",{headers:cors})
  try{
    const {product_id}=await req.json()
    if(!product_id) return new Response(JSON.stringify({error:"product_id required"}),{status:400,headers:cors})
    const auth=req.headers.get("Authorization")
    if(!auth) return new Response(JSON.stringify({error:"Authorization required"}),{status:401,headers:cors})
    const user_client=createClient(Deno.env.get("SUPABASE_URL")!,Deno.env.get("SUPABASE_ANON_KEY")!,
      {global:{headers:{Authorization:auth}}})
    const {data:{user},error:ae}=await user_client.auth.getUser()
    if(ae||!user) return new Response(JSON.stringify({error:"Invalid token"}),{status:401,headers:cors})
    const {data:item}=await user_client.from("order_items")
      .select("item_id,orders!inner(payment_status,user_id)")
      .eq("product_id",product_id).eq("orders.user_id",user.id)
      .eq("orders.payment_status","paid").maybeSingle()
    if(!item) return new Response(JSON.stringify({error:"No paid purchase"}),{status:403,headers:cors})
    const admin=createClient(Deno.env.get("SUPABASE_URL")!,Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")!)
    const {data:p}=await admin.from("products").select("storage_path,title,size_mb").eq("product_id",product_id).single()
    if(!p?.storage_path) return new Response(JSON.stringify({error:"File not found"}),{status:404,headers:cors})
    const {data:signed}=await admin.storage.from("assets").createSignedUrl(p.storage_path,3600,{download:p.title})
    if(!signed?.signedUrl) return new Response(JSON.stringify({error:"Could not sign URL"}),{status:500,headers:cors})
    return new Response(JSON.stringify({url:signed.signedUrl,filename:p.title,size_mb:p.size_mb,
      expires:new Date(Date.now()+3600000).toISOString()}),
      {status:200,headers:{...cors,"Content-Type":"application/json"}})
  }catch(e){return new Response(JSON.stringify({error:e.message}),{status:500,headers:cors})}
})

─── supabase/functions/process-payment-webhook/index.ts ────────────────────
PayHere webhook. MD5 verified with real merchant secret (1222339).
Updates order status on success/failure.

import { serve } from "https://deno.land/std@0.177.0/http/server.ts"
import { createClient } from "https://esm.sh/@supabase/supabase-js@2"
import { crypto } from "https://deno.land/std@0.177.0/crypto/mod.ts"
serve(async(req)=>{
  if(req.method!=="POST") return new Response("Method Not Allowed",{status:405})
  try{
    const form=await req.formData()
    const g=(k:string)=>form.get(k)?.toString().trim()??""
    const merchant_id=g("merchant_id"),order_id=g("order_id"),amount=g("payhere_amount"),
      currency=g("payhere_currency"),status=g("status_code"),md5sig=g("md5sig"),payment_id=g("payment_id")
    if(!merchant_id||!order_id||!status||!md5sig){
      console.error("Missing fields"); return new Response("Bad Request",{status:400})}
    if(merchant_id!==(Deno.env.get("PAYHERE_MERCHANT_ID")??"1222339")){
      console.error("Merchant mismatch"); return new Response("Forbidden",{status:403})}
    const secretHash=await md5(Deno.env.get("PAYHERE_MERCHANT_SECRET")!.toUpperCase())
    const expected=await md5(merchant_id+order_id+amount+currency+status+secretHash)
    if(expected.toUpperCase()!==md5sig.toUpperCase()){
      console.error("MD5 mismatch"); return new Response("Invalid Signature",{status:400})}
    const admin=createClient(Deno.env.get("SUPABASE_URL")!,Deno.env.get("SUPABASE_SERVICE_ROLE_KEY")!)
    const statusMap:Record<string,string>={"2":"paid","0":"pending","-1":"failed","-2":"failed","-3":"failed"}
    const newStatus=statusMap[status]??"pending"
    await admin.from("orders").update({payment_status:newStatus,payment_ref:payment_id||null}).eq("order_id",order_id)
    console.log(`PayHere: ${order_id} → ${newStatus}`)
    return new Response("OK",{status:200})
  }catch(e){console.error(e);return new Response("Internal Error",{status:500})}
})
async function md5(text:string):Promise<string>{
  const b=await crypto.subtle.digest("MD5",new TextEncoder().encode(text))
  return Array.from(new Uint8Array(b)).map(x=>x.toString(16).padStart(2,"0")).join("")
}

─── SET SECRETS AND DEPLOY ──────────────────────────────────────────────────
Set via supabase-mcp-server secrets:
  SUPABASE_URL              = from P4_supabase
  SUPABASE_ANON_KEY         = from P4_supabase
  SUPABASE_SERVICE_ROLE_KEY = from P4_supabase
  FIREBASE_SERVER_KEY       = from P2_firebase
  PAYHERE_MERCHANT_ID       = "1222339"
  PAYHERE_MERCHANT_SECRET   = "MzkwMDEyMDk5NjExOTI3Njc4MjgzNDEyNDU1Njk2MzM2NzAwMDQ5OQ=="

terminal: supabase functions deploy send-push-notification
terminal: supabase functions deploy generate-signed-url
terminal: supabase functions deploy process-payment-webhook

Test each via fetch: 401 on missing auth, 400 on bad signature. Fix any failures.
memory:"P5_functions" → "3 functions deployed and smoke-tested"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 6 — COMPLETE ANDROID PROJECT  [filesystem → terminal → memory:"P6_android"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
READ ./stitch_outputs/DESIGN_SYSTEM.md FIRST.
Copy Stitch resource files → app/src/main/res/values/

FULL PROJECT TREE — write every file listed. Zero omissions allowed:

MADevMarketplace/
├── app/
│   ├── google-services.json              ← from P2_firebase (real file)
│   ├── build.gradle.kts                  ← all dependencies below
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/madev/marketplace/
│       │   ├── MADevApp.kt               ← Application class
│       │   ├── util/
│       │   │   ├── Constants.kt          ← all keys (strings.xml refs + PayHere ID)
│       │   │   ├── SessionManager.kt     ← EncryptedSharedPreferences
│       │   │   ├── ShakeDetector.kt      ← accelerometer, threshold 2.7g, debounce 800ms
│       │   │   ├── Extensions.kt         ← gone/visible, dp/sp, toast, formatPrice
│       │   │   ├── NetworkUtils.kt       ← isWifiConnected(), isNetworkAvailable()
│       │   │   └── FormatUtils.kt        ← formatFileSize(), formatVersion()
│       │   ├── data/
│       │   │   ├── local/
│       │   │   │   ├── AppDatabase.kt    ← Room v1, all 4 entities
│       │   │   │   ├── entity/           ← ProductEntity, CartItemEntity, OrderEntity, DownloadEntity
│       │   │   │   └── dao/              ← ProductDao, CartDao, OrderDao, DownloadDao (all Flows)
│       │   │   ├── remote/
│       │   │   │   ├── SupabaseClientProvider.kt ← singleton init
│       │   │   │   ├── ProductApi.kt     ← getProducts, getById, search (FTS via RPC)
│       │   │   │   ├── OrderApi.kt       ← createOrder, createItems, getByUser, updateStatus
│       │   │   │   ├── ProfileApi.kt     ← getProfile, update, updateFcmToken
│       │   │   │   ├── StorageApi.kt     ← callGenerateSignedUrl (Edge Fn + JWT)
│       │   │   │   └── RealtimeManager.kt ← subscribeToProducts, subscribeToOrderStatus, unsubscribeAll
│       │   │   └── repository/
│       │   │       ├── AuthRepository.kt ← signInEmail, signUpEmail, signInGitHub, signOut, getCurrentUser, saveFcmToken
│       │   │       ├── ProductRepository.kt ← Flow (Room cache → Supabase refresh)
│       │   │       ├── CartRepository.kt ← addItem, removeItem, clearCart, getItems:Flow, getTotal:Flow
│       │   │       ├── OrderRepository.kt ← placeOrder, getLibrary (paid items)
│       │   │       └── DownloadRepository.kt ← startDownload(calls StorageApi), pause, resume, cancel, status:Flow
│       │   ├── domain/
│       │   │   ├── model/
│       │   │   │   ├── User.kt, Product.kt, Order.kt, OrderItem.kt
│       │   │   │   ├── CartItem.kt, Meetup.kt, DownloadTask.kt
│       │   │   │   ├── UiState.kt        ← sealed class: Loading, Success<T>, Error(msg)
│       │   │   │   └── PayHereModel.kt   ← all fields PayHere SDK needs
│       │   │   └── usecase/
│       │   │       ├── LoginUseCase.kt, RegisterUseCase.kt
│       │   │       ├── GetProductsUseCase.kt, SearchProductsUseCase.kt
│       │   │       ├── PlaceOrderUseCase.kt, GetSignedUrlUseCase.kt, GetLibraryUseCase.kt
│       │   ├── ui/
│       │   │   ├── SplashActivity.kt              (→ S-01)
│       │   │   ├── onboarding/
│       │   │   │   ├── OnboardingActivity.kt      (→ S-02/03/04 ViewPager2 + dots)
│       │   │   │   └── OnboardingPageFragment.kt
│       │   │   ├── auth/
│       │   │   │   ├── AuthActivity.kt
│       │   │   │   ├── LoginFragment.kt + LoginViewModel.kt      (→ S-05)
│       │   │   │   └── RegisterFragment.kt + RegisterViewModel.kt (→ S-06)
│       │   │   ├── main/
│       │   │   │   └── MainActivity.kt            ← NavHostFragment + BottomNavigationView
│       │   │   ├── catalog/
│       │   │   │   ├── CatalogFragment.kt + CatalogViewModel.kt  (→ S-07)
│       │   │   │   ├── ProductDetailFragment.kt + ViewModel       (→ S-08)
│       │   │   │   └── adapter/ProductAdapter.kt  ← DiffUtil, category colors, ViewBinding
│       │   │   ├── library/
│       │   │   │   ├── LibraryFragment.kt + LibraryViewModel.kt   (→ S-09)
│       │   │   │   └── adapter/PurchasedAdapter.kt + DownloadAdapter.kt
│       │   │   ├── cart/
│       │   │   │   ├── CartFragment.kt + CartViewModel.kt         (→ S-10)
│       │   │   │   ├── CheckoutFragment.kt + CheckoutViewModel.kt (→ S-11)
│       │   │   │   └── adapter/CartAdapter.kt     ← ItemTouchHelper swipe-to-delete
│       │   │   ├── map/
│       │   │   │   └── MapFragment.kt + MapViewModel.kt           (→ S-12)
│       │   │   └── profile/
│       │   │       └── ProfileFragment.kt + ProfileViewModel.kt  (→ S-13)
│       │   └── service/
│       │       ├── FCMService.kt         ← onNewToken saves to DB; onMessageReceived builds notification
│       │       ├── DownloadService.kt    ← OkHttp Range-header resume, 512KB chunks, pause/cancel PendingIntents
│       │       └── NetworkReceiver.kt    ← dynamic registration, LocalBroadcast WIFI_CHANGED
│       └── res/
│           ├── layout/                   ← all XML layouts matching Stitch screens (see list below)
│           ├── navigation/nav_graph.xml  ← all 13 destinations + all actions
│           ├── menu/bottom_nav_menu.xml  ← 5 items matching S-07 bottom nav
│           ├── raw/map_style.json        ← Google Maps dark style JSON
│           ├── font/                     ← JetBrains Mono (downloadable font XML)
│           ├── drawable/ic_notification.xml ← vector for FCM notifications
│           ├── values/
│           │   ├── colors.xml            ← from Stitch DESIGN_SYSTEM
│           │   ├── themes.xml            ← from Stitch DESIGN_SYSTEM
│           │   ├── styles.xml            ← from Stitch DESIGN_SYSTEM
│           │   ├── strings.xml           ← ALL user-facing strings
│           │   └── secrets.xml.template  ← template only (real file gitignored)
│           └── xml/file_paths.xml        ← FileProvider config

─── build.gradle.kts (app) ──────────────────────────────────────────────────
plugins: com.android.application, kotlin.android, com.google.gms.google-services, kotlin-kapt
android: compileSdk=34, minSdk=24, targetSdk=34, versionCode=1, versionName="1.0.0"
buildFeatures { viewBinding=true }
buildTypes.release: minifyEnabled=true, shrinkResources=true, proguard
compileOptions + kotlinOptions: JavaVersion.VERSION_17 / "17"

dependencies:
  // Supabase
  platform("io.github.jan-tennert.supabase:bom:2.6.1")
  supabase: postgrest-kt, auth-kt, realtime-kt, storage-kt
  implementation("io.ktor:ktor-client-android:2.3.12")
  // Firebase
  platform("com.google.firebase:firebase-bom:33.1.2")
  implementation("com.google.firebase:firebase-messaging-ktx")
  // Room
  room-runtime:2.6.1, room-ktx:2.6.1, kapt room-compiler:2.6.1
  // Navigation
  navigation-fragment-ktx:2.7.7, navigation-ui-ktx:2.7.7
  // UI
  material:1.12.0, viewpager2:1.1.0, swiperefreshlayout:1.1.0, constraintlayout:2.1.4
  // Maps + PayHere
  play-services-maps:19.0.0, play-services-location:21.3.0
  payhere-mobilesdk-android:1.0.26
  // Images + Markdown
  glide:4.16.0, kapt glide-compiler:4.16.0
  markwon: core:4.6.2, syntax-highlight:4.6.2, ext-strikethrough:4.6.2, ext-tables:4.6.2
  // Security + Lifecycle + Coroutines
  security-crypto:1.1.0-alpha06
  coroutines-android:1.8.1
  lifecycle: viewmodel-ktx:2.8.4, livedata-ktx:2.8.4, runtime-ktx:2.8.4
  // WorkManager + OkHttp
  work-runtime-ktx:2.9.1
  okhttp:4.12.0, logging-interceptor:4.12.0
  startup-runtime:1.1.1

─── AndroidManifest.xml permissions ─────────────────────────────────────────
INTERNET, ACCESS_NETWORK_STATE, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION,
FOREGROUND_SERVICE, FOREGROUND_SERVICE_DATA_SYNC, POST_NOTIFICATIONS (API33+),
VIBRATE, WRITE_EXTERNAL_STORAGE (maxSdkVersion=28), READ_MEDIA_IMAGES (API33+)

Activities: SplashActivity(LAUNCHER,NO_HISTORY), OnboardingActivity(NO_HISTORY),
AuthActivity(NO_HISTORY), MainActivity(singleTop)
Services: DownloadService(foreground,dataSync), FCMService(MESSAGING_EVENT intent-filter)
FileProvider: com.madev.marketplace.fileprovider → @xml/file_paths
Meta-data: google_maps_api_key, default_notification_channel_id="madev_alerts",
           default_notification_icon=@drawable/ic_notification, default_notification_color=#00E5CC

─── XML Layouts (implement each from its Stitch reference) ──────────────────
activity_splash.xml (S-01), activity_onboarding.xml, fragment_onboarding_page.xml (S-02/03/04),
activity_auth.xml, fragment_login.xml (S-05), fragment_register.xml (S-06),
activity_main.xml, fragment_catalog.xml (S-07), item_product_card.xml,
fragment_product_detail.xml (S-08), fragment_library.xml (S-09),
item_library_purchased.xml, item_library_download.xml,
fragment_cart.xml (S-10), item_cart.xml,
fragment_checkout.xml (S-11),
fragment_map.xml (S-12), fragment_profile.xml (S-13),
nav_graph.xml (all 13 destinations), bottom_nav_menu.xml

─── Key Implementation Rules ────────────────────────────────────────────────
MADevApp.kt: FirebaseApp.initializeApp(this), FirebaseMessaging.getInstance().token
  .addOnSuccessListener{token→SessionManager.saveFcmToken(token); if(loggedIn)ProfileApi.updateFcmToken(token)}

FCMService.kt: onNewToken→update DB+SessionManager; onMessageReceived→NotificationCompat.Builder
  channel "madev_alerts", teal color #00E5CC, correct deeplink Intent per notification type

DownloadService.kt: OkHttp Range:"bytes={bytesDownloaded}-", 512KB chunks→update Room,
  Notification with Pause+Cancel PendingIntents, LocalBroadcast from NetworkReceiver

CheckoutViewModel.kt: placeOrder→OrderRepository→get order_id→subscribeToOrderStatus via
  Realtime→on paid emit PaymentSuccess; PayHere fields:
    merchantId="1222339", sandbox=true, orderId=supabase_order_id, currency="LKR"

CatalogFragment.kt: RealtimeManager.subscribeToProducts in onResume, unsubscribe in onPause
  ChipGroup filter→ProductRepository.getProducts(category)
  SearchView→debounce 300ms→SearchProductsUseCase

ProductDetailFragment.kt: Markwon.create(context) with syntax-highlight plugin
  CollapsingToolbarLayout gradient set programmatically based on product.category
  Requirements card GONE if category != Local_AI

MapFragment.kt: dark map style from raw/map_style.json, custom teal BitmapDescriptor markers,
  BottomSheetBehavior, graceful location permission fallback to Colombo (6.9271,79.8612)

Every ViewModel: sealed UiState (Loading/Success/Error), StateFlow for state,
  SharedFlow for one-shot events, viewModelScope+Dispatchers.IO for network,
  Dispatchers.Main for state updates

─── Build Verification ───────────────────────────────────────────────────────
terminal: ./gradlew assembleDebug → BUILD SUCCESSFUL required, fix all errors
terminal: ./gradlew lint         → zero errors
terminal: ./gradlew test         → all pass
memory:"P6_android" → "Android BUILD SUCCESSFUL, lint clean"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 7 — ADMIN PANEL  [google-stitch get_screen_code → filesystem → memory:"P7_admin"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Retrieve Stitch code for S-14 through S-18. Import ./stitch_outputs/admin/design-vars.css.
Build unified SPA: ./MADevAdmin/index.html + style.css + app.js

CDN imports: @supabase/supabase-js@2 (jsdelivr), chart.js@4 (jsdelivr),
             JetBrains Mono + Inter (Google Fonts)

AUTH (SHA-256 in app.js):
  Precompute sha256("MADev@Admin2025") → hardcode hex as ADMIN_HASH constant
  Login: sha256(input)===ADMIN_HASH → sessionStorage.setItem("ma_admin","1")
  Wrong: CSS shake animation on card + "Invalid credentials" error text
  Every page load: !sessionStorage.getItem("ma_admin") → router.navigate("#login")

ROUTER: hash-based (#login, #dashboard, #products, #users, #orders)
  window.addEventListener("hashchange"), initial load check

SUPABASE JS:
  const db=createClient(SUPABASE_URL, SUPABASE_ANON_KEY)
  const adminDb=createClient(SUPABASE_URL, SUPABASE_SERVICE_ROLE_KEY)

DASHBOARD (→ S-15): Promise.all([revenue SUM, order COUNT, user COUNT, product COUNT])
  Chart.js line chart 30-day revenue: teal line, transparent fill, dark grid #30363D
  Recent orders table last 10 with status color pills
  "Broadcast" button → modal → POST Edge Function send-push-notification

PRODUCTS (→ S-16): Full CRUD table, search+filter client-side
  Add/Edit modal all fields. Version bump: X.Y.Z → increment Z → UPDATE → triggers webhook → FCM
  Soft delete: is_active=false with confirm dialog

USERS (→ S-17): profiles JOIN orders(count), expandable rows, name/email search

ORDERS (→ S-18): filter tabs with live counts, "Mark as Paid", CSV export
  CSV filename: madev-orders-YYYY-MM-DD.csv, Blob URL download

memory:"P7_admin" → "admin panel complete"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 8 — GITHUB  [github → memory:"P8_github"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
1. Create private repo: "MADevMarketplace"
2. .gitignore: secrets.xml, google-services.json, *.keystore, .env, supabase/.env,
               node_modules/, build/, .DS_Store
3. Branches: main (stable) + develop (active work)
4. Structured commits on develop:
   "feat: firebase project + google-services.json via firebase-mcp"
   "feat: supabase schema + rls + storage + functions + seed"
   "feat: stitch design system — 18 screens + design tokens extracted"
   "feat: android project setup + all dependencies"
   "feat: android resources from stitch (colors/themes/styles/nav)"
   "feat: android util layer (constants/session/shake/extensions/network)"
   "feat: android data layer (room + supabase apis + repositories)"
   "feat: android domain layer (models + usecases)"
   "feat: splash + onboarding (viewpager2 + dots)"
   "feat: auth screens (login + github oauth + register)"
   "feat: catalog + product detail + realtime subscriptions"
   "feat: library + download service + network receiver"
   "feat: cart + shake-to-clear + swipe-to-delete adapter"
   "feat: checkout + payhere sdk (merchant 1222339) + payment success"
   "feat: map + dark style + meetup markers + bottom sheet"
   "feat: profile + fcm refresh + billing history"
   "feat: admin panel — 4 pages + chart.js + broadcast push"
   "test: playwright qa suite"
   "docs: readme + setup guide + payhere test cards"
5. PR develop → main, merge

memory:"P8_github" → "repo created, PR merged"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 9 — DEPLOY ADMIN PANEL  [Cloud Run → memory:"P9_deploy"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Verify MADevAdmin/app.js has correct SUPABASE_URL, ANON_KEY, SERVICE_ROLE_KEY,
EDGE_FN_BASE, and precomputed ADMIN_HASH.

Cloud Run → deploy_local_folder:
  folder_path:  <absolute path to ./MADevAdmin/>
  service_name: "madev-admin-panel"
  region:       "us-central1"

Test in browser: login → dashboard loads with live data.
Save URL → memory:"P9_deploy"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 10 — AUTOMATED QA  [playwright → memory:"P10_qa"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Write ./tests/admin-qa.spec.js and run with playwright.

Tests (all must pass):
  T1: Login page renders — title contains "MA_Dev Admin", password input visible → screenshot qa_01.png
  T2: Wrong password rejected — error visible, NOT redirected
  T3: Correct password "MADev@Admin2025" → hash=#dashboard, sidebar visible → screenshot qa_02.png
  T4: Dashboard KPI cards — 4 cards, numeric values (not NaN/null/undefined), Chart.js canvas rendered
  T5: Products nav → table >= 5 rows, "+ Add Product" visible → screenshot qa_03.png
  T6: Users nav → table >= 1 row → screenshot qa_04.png
  T7: Orders nav → filter tabs visible (All/Pending/Paid/Failed), Export CSV visible → screenshot qa_05.png
  T8: Zero console.error events across all 4 pages

Save → ./qa_screenshots/ | Report: PASS (8/8) or FAIL with details
memory:"P10_qa" → "<PASS|FAIL> 8/8 tests"

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PHASE 11 — README + FINAL CHECKPOINT  [filesystem + memory:"P11_done"]
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
Write ./README.md with:
  - Architecture table (all tech layers)
  - Android setup (git clone + secrets.xml template + google-services.json note)
  - Supabase setup (SQL reference + bucket creation)
  - Edge Function secrets (supabase secrets set command)
  - Supabase DB Webhook manual step (Dashboard → Database → Webhooks)
  - Firebase setup (Firebase Console + download google-services.json)
  - PayHere webhook URL (give to PayHere dashboard: process-payment-webhook URL)
  - Admin panel URL + password
  - PayHere sandbox test cards:
      4916 2177 5624 6455 / 12/25 / 100 → Success
      5307 7326 3004 6049 / 12/25 / 100 → Success
      4024 0071 2244 4943 / 12/25 / 100 → Fail
  - Manual steps remaining after automated build

Save memory:"P11_done":
{
  "project":           "MA_Dev Marketplace v1.0.0",
  "apk":               "./MADevMarketplace/app/build/outputs/apk/debug/app-debug.apk",
  "admin_url":         "<from P9_deploy>",
  "repo_url":          "<GitHub repo URL>",
  "supabase_url":      "<from P4_supabase>",
  "firebase_project":  "<from P2_firebase>",
  "payhere_merchant":  "1222339",
  "payhere_sandbox":   true,
  "stitch_screens":    18,
  "edge_functions":    3,
  "push_via":          "Firebase FCM",
  "qa_result":         "<from P10_qa>",
  "phases_complete":   [0,1,2,3,4,5,6,7,8,9,10,11],
  "manual_only":       [
    "Replace app/google-services.json with real Firebase file (if firebase-mcp could not write it)",
    "Create Supabase DB Webhook: products.UPDATE → send-push-notification",
    "Set PayHere Notify URL in PayHere dashboard → process-payment-webhook endpoint",
    "Replace google_maps_key in secrets.xml"
  ]
}

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
PRODUCTION FINAL CHECKLIST
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
FIREBASE:
[ ] Project created via firebase-mcp
[ ] Android app registered (com.madev.marketplace) + SHA-1 fingerprint
[ ] FCM enabled, Server Key in Edge secrets
[ ] google-services.json in app/ directory

STITCH:
[ ] 18 screens generated and saved (13 Android + 5 Admin)
[ ] DESIGN_SYSTEM.md extracted with full token docs
[ ] colors.xml + themes.xml + styles.xml + design-vars.css generated
[ ] All Android layouts implement exact Stitch references

SUPABASE:
[ ] 6 tables + 3 enums + 5 triggers/functions + 8 RLS policies
[ ] pg_trgm + pgcrypto extensions enabled
[ ] Full-text search index on products
[ ] Storage buckets "assets" (private 5GB) + "avatars" (public 5MB)
[ ] Storage RLS for paid buyers only
[ ] 5 seed products + 2 meetups inserted
[ ] get_product_buyer_fcm_tokens() + get_all_fcm_tokens() + search_products() created

EDGE FUNCTIONS:
[ ] send-push-notification: FCM chunked, logs to DB, handles webhook+broadcast
[ ] generate-signed-url: purchase verification, 1-hour expiry, filename in response
[ ] process-payment-webhook: MD5 verified, all PayHere status codes handled
[ ] 6 secrets set (SUPABASE_URL/ANON/SERVICE, FIREBASE_SERVER_KEY, PAYHERE ID+SECRET)
[ ] All 3 functions deployed and smoke-tested

ANDROID:
[ ] Full file tree written — zero missing files, zero stubs
[ ] ViewBinding enabled, used in all fragments
[ ] FCMService: saves token to DB on refresh, shows correct notification per type
[ ] DownloadService: Range-header resume, 512KB chunks, pause/cancel PendingIntents
[ ] NetworkReceiver: dynamic registration, WIFI_CHANGED broadcast
[ ] ShakeDetector: MaterialAlertDialog confirm → CartRepository.clearCart()
[ ] Realtime: catalog live updates + payment status detection
[ ] PayHere: merchant="1222339", sandbox=true, currency="LKR"
[ ] Dark map style applied in MapFragment
[ ] CollapsingToolbar gradient per category in ProductDetailFragment
[ ] All EncryptedSharedPreferences for sensitive data
[ ] ./gradlew assembleDebug → BUILD SUCCESSFUL
[ ] ./gradlew lint → zero errors
[ ] ./gradlew test → all pass

ADMIN PANEL:
[ ] 4 pages styled from Stitch S-14 through S-18
[ ] SHA-256 login with "MADev@Admin2025"
[ ] Wrong password rejected with shake animation
[ ] Dashboard: live KPI cards, Chart.js 30-day chart, recent orders
[ ] Dashboard: broadcast push works via Edge Function
[ ] Products: full CRUD, version bump triggers FCM push to buyers
[ ] Users: expandable order history rows, search
[ ] Orders: filter tabs, Mark as Paid, CSV export with date-stamped filename

DELIVERY:
[ ] GitHub private repo "MADevMarketplace" created
[ ] .gitignore excludes secrets.xml + google-services.json + keystore
[ ] 19 structured commits on develop branch
[ ] PR merged to main
[ ] Cloud Run admin panel deployed → publicly accessible
[ ] Playwright: 8/8 tests pass, 5 screenshots, zero console errors
[ ] README.md complete with PayHere test cards + all manual steps
[ ] memory:"P11_done" saved with complete delivery manifest

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
START NOW.
Phase 0: sequential-thinking → create master plan → save memory:"P0_plan"
Then immediately proceed to Phase 1. Execute all 11 phases without stopping.
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
```
