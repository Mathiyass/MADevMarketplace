// MADev Marketplace - Admin Panel Logic
const TARGET_HASH_HEX = "9cf52538cb33924f3ce3dc07cfff534a78cb4fd1dbfe3a9db582531aa7729f27"; // Hash of 'MADev@Admin2025'

async function hashStr(msg) {
    const encoder = new TextEncoder();
    const data = encoder.encode(msg);
    const hash = await crypto.subtle.digest('SHA-256', data);
    return Array.from(new Uint8Array(hash)).map(b => b.toString(16).padStart(2, '0')).join('');
}

async function login() {
    const input = document.getElementById('master-pwd').value;
    const computed = await hashStr(input);
    
    if (computed === TARGET_HASH_HEX) {
        document.getElementById('login-overlay').classList.add('hidden');
        document.getElementById('app').classList.remove('hidden');
        document.getElementById('login-err').classList.add('hidden');
        
        loadDashboardData();
    } else {
        document.getElementById('login-err').classList.remove('hidden');
    }
}

function showTab(id) {
    document.querySelectorAll('.tab').forEach(el => el.classList.add('hidden'));
    document.getElementById('tab-' + id).classList.remove('hidden');
    
    document.querySelectorAll('.sidebar nav a').forEach(el => el.classList.remove('active'));
    document.getElementById('nav-' + id).classList.add('active');
    
    if(id === 'products') loadProducts();
}

// Supabase interaction stub
async function loadDashboardData() {
    try {
        console.log("Loading dashboard stats connected to Supabase...");
        // In full impl: const { data } = await _supabase.from('products').select('*');
        document.querySelectorAll('.data')[0].innerText = "LKR 12,500";
        document.querySelectorAll('.data')[1].innerText = "5";
        document.querySelectorAll('.data')[2].innerText = "3";
    } catch(err) {
        console.error("Dashboard error", err);
    }
}

async function loadProducts() {
    console.log("Loading products via Supabase REST API...");
    const tbody = document.querySelector("#products-table tbody");
    tbody.innerHTML = `
        <tr>
            <td>PRD-992</td>
            <td>Local AI Model Wrapper</td>
            <td>LKR 5500</td>
            <td><span style="color:var(--cat-local-ai)">localai</span></td>
            <td><button class="btn-primary" style="padding:6px 12px">Edit</button></td>
        </tr>
    `;
}
