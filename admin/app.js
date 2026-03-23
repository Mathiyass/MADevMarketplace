const supabaseUrl = 'https://muchpzethrdrmhzztnsw.supabase.co';
const supabaseKey = 'sb_publishable_rut-gIT8nLdVT5z34zAgsQ_7RGcWpBb';
const supabase = window.supabase.createClient(supabaseUrl, supabaseKey);

// DOM Elements
const loginView = document.getElementById('login-view');
const mainView = document.getElementById('main-view');
const loginForm = document.getElementById('login-form');
const authError = document.getElementById('auth-error');

// Router
function showView(view) {
    loginView.style.display = view === 'login' ? 'flex' : 'none';
    mainView.style.display = view === 'main' ? 'flex' : 'none';
}

// Initial check
showView('login');

// Auth Form Handler
loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    authError.style.display = 'none';
    const { data, error } = await supabase.auth.signInWithPassword({ email, password });
    
    if (error) {
        authError.textContent = error.message;
        authError.style.display = 'block';
    } else {
        showView('main');
        renderContent('dashboard');
    }
});

// Nav routing inside Dashboard
const navItems = document.querySelectorAll('.nav-item[data-target]');
const pageTitle = document.getElementById('page-title');

navItems.forEach(item => {
    item.addEventListener('click', (e) => {
        e.preventDefault();
        navItems.forEach(nav => nav.classList.remove('active'));
        item.classList.add('active');
        const target = item.getAttribute('data-target');
        
        // Update Title
        pageTitle.textContent = target.charAt(0).toUpperCase() + target.slice(1);
        
        // Render content based on target
        renderContent(target);
    });
});

function renderContent(target) {
    // Hide all sections
    document.querySelectorAll('.page-section').forEach(sec => sec.style.display = 'none');
    // Show target section
    const section = document.getElementById('section-' + target);
    if(section) section.style.display = 'block';
    
    // Load mock data for now
    if (target === 'dashboard') loadDashboard();
    else if (target === 'users') loadUsers();
    else if (target === 'orders') loadOrders();
    else if (target === 'products') loadProducts();
}

// Data Loaders
async function loadDashboard() {
    const { count: usersCount } = await supabase.from('profiles').select('*', { count: 'exact', head: true });
    const { count: ordersCount } = await supabase.from('orders').select('*', { count: 'exact', head: true });
    const { data: revenueData } = await supabase.from('orders').select('amount').eq('status', 'paid');
    
    const revenue = revenueData ? revenueData.reduce((sum, order) => sum + (order.amount || 0), 0) : 0;
    
    document.getElementById('metric-users').textContent = usersCount || 0;
    document.getElementById('metric-orders').textContent = ordersCount || 0;
    document.getElementById('metric-revenue').textContent = '$' + revenue.toFixed(2);
    
    const { data: recentOrders } = await supabase.from('orders').select('id, status, amount, products(name)').order('created_at', { ascending: false }).limit(5);
    const tbody = document.getElementById('dashboard-recent-orders');
    tbody.innerHTML = '';
    if (recentOrders) {
        recentOrders.forEach(o => {
            const productName = o.products ? o.products.name : 'Unknown';
            tbody.innerHTML += `<tr><td>${o.id.substring(0,8)}...</td><td>${o.status}</td><td>${productName}</td><td>$${o.amount.toFixed(2)}</td></tr>`;
        });
    }
}

async function loadUsers() {
    const { data } = await supabase.from('profiles').select('*').order('created_at', { ascending: false });
    const tbody = document.getElementById('users-table-body');
    tbody.innerHTML = '';
    if (data) {
        data.forEach(u => {
            const joined = new Date(u.created_at).toLocaleDateString();
            tbody.innerHTML += `<tr><td>${u.id.substring(0,8)}...</td><td>${u.full_name || 'N/A'}</td><td>${u.email || '@'}</td><td>${joined}</td></tr>`;
        });
    }
}

async function loadOrders() {
    const { data } = await supabase.from('orders').select('id, status, amount, profiles(full_name)').order('created_at', { ascending: false });
    const tbody = document.getElementById('orders-table-body');
    tbody.innerHTML = '';
    if (data) {
        data.forEach(o => {
            const userName = o.profiles ? o.profiles.full_name : 'Unknown';
            tbody.innerHTML += `<tr><td>${o.id.substring(0,8)}...</td><td>${userName}</td><td>${o.status}</td><td>$${o.amount.toFixed(2)}</td></tr>`;
        });
    }
}

async function loadProducts() {
    const { data } = await supabase.from('products').select('*').order('created_at', { ascending: false });
    const grid = document.getElementById('products-grid');
    grid.innerHTML = '';
    if (data) {
        data.forEach(p => {
            grid.innerHTML += `
            <div class="product-card">
                <img src="${p.image_url || 'https://via.placeholder.com/300x150/161B22/00E5CC?text=MA_Dev'}" alt="${p.name}">
                <h4>${p.name}</h4>
                <p>$${p.price.toFixed(2)}</p>
                <button class="btn-primary" style="padding: 0.5rem; font-size: 0.8rem;">Edit</button>
            </div>
            `;
        });
    }
}

document.getElementById('logout-btn').addEventListener('click', () => {
    showView('login');
});
