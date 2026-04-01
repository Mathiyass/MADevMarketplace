/* ═══════════════════════════════════════════════════════════════
   MA_Dev Admin Panel — app.js (Production)
   Supabase REST · products · orders · users · meetups · broadcast
   ═══════════════════════════════════════════════════════════════ */

// ─── CONFIG ──────────────────────────────────────────────────────
const SUPABASE_URL     = 'https://uhirktzthfqximowpdws.supabase.co';
const SUPABASE_ANON    = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVoaXJrdHp0aGZxeGltb3dwZHdzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzQ4ODcwMjYsImV4cCI6MjA5MDQ2MzAyNn0.4cn4OJWYmKdMUZ5D9-WdZOgC2lFABIiL3ZvZKoJLegc';
const ADMIN_HASH       = 'd200dec2c5ce12451fe8b1b41c24e1e3a28aa815a12acdca2bd360d39263f82b';

// ─── Supabase client ──────────────────────────────────────────────
const { createClient } = supabase;
const sb = createClient(SUPABASE_URL, SUPABASE_ANON, {
  auth: { persistSession: false }
});

// ─── Charts ──────────────────────────────────────────────────────
let chartCategory = null;
let chartOrders   = null;

// ════════════════════════════════════════════════════════════════
//  AUTH
// ════════════════════════════════════════════════════════════════
async function handleLogin(event) {
  event.preventDefault();
  const pwd = document.getElementById('master-pwd').value;
  const hash = await sha256(pwd);
  if (hash === ADMIN_HASH) {
    sessionStorage.setItem('ma_admin', '1');
    document.getElementById('login-overlay').classList.add('hidden');
    document.getElementById('app').classList.remove('hidden');
    document.getElementById('login-err').classList.add('hidden');
    initApp();
  } else {
    document.getElementById('login-err').classList.remove('hidden');
    document.querySelector('.login-card').classList.add('shake');
    setTimeout(() => document.querySelector('.login-card').classList.remove('shake'), 500);
  }
}

async function sha256(message) {
  const msgBuffer = new TextEncoder().encode(message);
  const hashBuffer = await crypto.subtle.digest('SHA-256', msgBuffer);
  return Array.from(new Uint8Array(hashBuffer)).map(b => b.toString(16).padStart(2, '0')).join('');
}

function logout() {
  sessionStorage.removeItem('ma_admin');
  document.getElementById('app').classList.add('hidden');
  document.getElementById('login-overlay').classList.remove('hidden');
  document.getElementById('master-pwd').value = '';
}

// Auto-check session on load
window.addEventListener('DOMContentLoaded', () => {
  if (sessionStorage.getItem('ma_admin') === '1') {
    document.getElementById('login-overlay').classList.add('hidden');
    document.getElementById('app').classList.remove('hidden');
    initApp();
  }
});

// ════════════════════════════════════════════════════════════════
//  NAV
// ════════════════════════════════════════════════════════════════
function showTab(tab) {
  document.querySelectorAll('.tab').forEach(t => t.classList.add('hidden'));
  document.querySelectorAll('.nav-link').forEach(l => l.classList.remove('active'));
  document.getElementById(`tab-${tab}`).classList.remove('hidden');
  document.getElementById(`nav-${tab}`).classList.add('active');
  document.getElementById('topbar-title').textContent = tab.charAt(0).toUpperCase() + tab.slice(1);

  if (tab === 'products')  loadProducts();
  if (tab === 'orders')    loadOrders();
  if (tab === 'users')     loadUsers();
  if (tab === 'meetups')   loadMeetups();
  if (tab === 'broadcast') loadBroadcastHistory();

  return false;
}

// ════════════════════════════════════════════════════════════════
//  INIT
// ════════════════════════════════════════════════════════════════
async function initApp() {
  await loadDashboard();
}

// ════════════════════════════════════════════════════════════════
//  DASHBOARD
// ════════════════════════════════════════════════════════════════
async function loadDashboard() {
  try {
    const [{ data: products }, { data: orders }, { data: users }] = await Promise.all([
      sb.from('products').select('product_id,category,is_active').eq('is_active', true),
      sb.from('orders').select('order_id,total_amount,payment_status,order_date,user_id'),
      sb.from('profiles').select('user_id,created_at')
    ]);

    // KPIs
    const paidOrders = (orders || []).filter(o => o.payment_status === 'paid');
    const revenue    = paidOrders.reduce((s, o) => s + parseFloat(o.total_amount || 0), 0);
    const pending    = (orders || []).filter(o => o.payment_status === 'pending').length;

    document.getElementById('kpi-revenue').textContent     = `LKR ${revenue.toLocaleString('en-LK', {minimumFractionDigits:2})}`;
    document.getElementById('kpi-revenue-sub').textContent = `${paidOrders.length} paid orders`;
    document.getElementById('kpi-products').textContent    = (products || []).length;
    document.getElementById('kpi-orders').textContent      = (orders || []).length;
    document.getElementById('kpi-orders-sub').textContent  = `${pending} pending`;
    document.getElementById('kpi-users').textContent       = (users || []).length;

    // Charts
    renderCategoryChart(products || []);
    renderOrdersChart(orders || []);

    // Recent orders table
    const recent = [...(orders || [])].sort((a,b) => new Date(b.order_date) - new Date(a.order_date)).slice(0,10);
    const tbody = document.querySelector('#recent-orders-table tbody');
    tbody.innerHTML = recent.map(o => `
      <tr>
        <td style="font-family:monospace;font-size:11px">${o.order_id.slice(0,8)}…</td>
        <td style="font-family:monospace;font-size:11px">${o.user_id.slice(0,8)}…</td>
        <td>LKR ${parseFloat(o.total_amount||0).toFixed(2)}</td>
        <td><span class="badge badge-${o.payment_status}">${o.payment_status}</span></td>
        <td>${fmtDate(o.order_date)}</td>
      </tr>`).join('') || '<tr><td colspan="5" class="table-loading">No orders yet</td></tr>';

  } catch (e) {
    console.error('Dashboard error', e);
    toast('Dashboard load error: ' + e.message, 'err');
  }
}

function renderCategoryChart(products) {
  const counts = {};
  products.forEach(p => { counts[p.category] = (counts[p.category]||0) + 1; });
  const labels = Object.keys(counts);
  const data   = Object.values(counts);

  if (chartCategory) chartCategory.destroy();
  const ctx = document.getElementById('chart-category').getContext('2d');
  chartCategory = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels,
      datasets: [{ data, backgroundColor: ['#ef4444','#3b82f6','#4338ca','#f59e0b'], borderWidth: 0 }]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { labels: { color: '#9ca3af', font: { size: 12 } } }
      }
    }
  });
}

function renderOrdersChart(orders) {
  // Group by date (last 14 days)
  const buckets = {};
  const now = new Date();
  for (let i = 13; i >= 0; i--) {
    const d = new Date(now); d.setDate(d.getDate() - i);
    buckets[d.toISOString().slice(0,10)] = 0;
  }
  (orders || []).filter(o => o.payment_status === 'paid').forEach(o => {
    const day = (o.order_date||'').slice(0,10);
    if (day in buckets) buckets[day]++;
  });

  if (chartOrders) chartOrders.destroy();
  const ctx = document.getElementById('chart-orders').getContext('2d');
  chartOrders = new Chart(ctx, {
    type: 'line',
    data: {
      labels: Object.keys(buckets).map(d => d.slice(5)),
      datasets: [{
        label: 'Paid Orders',
        data: Object.values(buckets),
        borderColor: '#00d9b5', backgroundColor: 'rgba(0,217,181,.08)',
        fill: true, tension: 0.4, pointRadius: 3, borderWidth: 2
      }]
    },
    options: {
      responsive: true,
      scales: {
        x: { ticks: { color: '#6b7280' }, grid: { color: '#1a1a24' } },
        y: { ticks: { color: '#6b7280', precision: 0 }, grid: { color: '#1a1a24' } }
      },
      plugins: { legend: { labels: { color: '#9ca3af' } } }
    }
  });
}

// ════════════════════════════════════════════════════════════════
//  PRODUCTS
// ════════════════════════════════════════════════════════════════
async function loadProducts() {
  document.getElementById('products-loading').style.display = 'block';
  let query = sb.from('products').select('*').order('created_at', { ascending: false });
  const cat = document.getElementById('filter-category')?.value;
  const onlyActive = document.getElementById('filter-active')?.checked;
  if (cat)        query = query.eq('category', cat);
  if (onlyActive) query = query.eq('is_active', true);

  const { data, error } = await query;
  document.getElementById('products-loading').style.display = 'none';
  if (error) return toast('Products error: ' + error.message, 'err');

  const tbody = document.getElementById('products-tbody');
  tbody.innerHTML = (data || []).map(p => `
    <tr>
      <td title="${p.title}"><strong>${p.title}</strong></td>
      <td>${catPill(p.category)}</td>
      <td>LKR ${parseFloat(p.price).toFixed(2)}</td>
      <td>${p.size_mb ? p.size_mb + ' MB' : '—'}</td>
      <td>${p.download_count || 0}</td>
      <td><span class="badge ${p.is_active ? 'badge-active':'badge-inactive'}">${p.is_active?'active':'inactive'}</span></td>
      <td>
        <button class="btn-sm" onclick='openProductModal(${JSON.stringify(p).replace(/'/g,"\\'")})'  style="margin-right:6px">Edit</button>
        <button class="btn-danger" onclick="deleteProduct('${p.product_id}')">Delete</button>
      </td>
    </tr>`).join('') || '<tr><td colspan="7" class="table-loading">No products found</td></tr>';
}

function openProductModal(product = null) {
  document.getElementById('modal-product-title').textContent = product ? 'Edit Product' : 'Add Product';
  document.getElementById('pf-id').value           = product?.product_id || '';
  document.getElementById('pf-title').value        = product?.title       || '';
  document.getElementById('pf-desc').value         = product?.description || '';
  document.getElementById('pf-price').value        = product?.price       ?? '';
  document.getElementById('pf-category').value     = product?.category    || 'Tools';
  document.getElementById('pf-version').value      = product?.version     || '1.0.0';
  document.getElementById('pf-size').value         = product?.size_mb     ?? '';
  document.getElementById('pf-path').value         = product?.storage_path|| '';
  document.getElementById('pf-requirements').value = product?.requirements || '';
  document.getElementById('pf-tags').value         = (product?.tags || []).join(', ');
  document.getElementById('pf-preview').value      = product?.preview_code|| '';
  document.getElementById('pf-active').checked     = product ? product.is_active : true;
  document.getElementById('modal-product').classList.remove('hidden');
}

async function saveProduct(event) {
  event.preventDefault();
  const id   = document.getElementById('pf-id').value.trim();
  const tags = document.getElementById('pf-tags').value.split(',').map(t=>t.trim()).filter(Boolean);
  const payload = {
    title:        document.getElementById('pf-title').value.trim(),
    description:  document.getElementById('pf-desc').value.trim(),
    price:        parseFloat(document.getElementById('pf-price').value || 0),
    category:     document.getElementById('pf-category').value,
    version:      document.getElementById('pf-version').value.trim() || '1.0.0',
    size_mb:      parseFloat(document.getElementById('pf-size').value) || 0,
    storage_path: document.getElementById('pf-path').value.trim() || null,
    requirements: document.getElementById('pf-requirements').value.trim() || null,
    tags,
    preview_code: document.getElementById('pf-preview').value.trim() || null,
    is_active:    document.getElementById('pf-active').checked,
  };

  let error;
  if (id) {
    ({ error } = await sb.from('products').update(payload).eq('product_id', id));
  } else {
    ({ error } = await sb.from('products').insert(payload));
  }
  if (error) return toast('Save error: ' + error.message, 'err');
  toast(id ? 'Product updated!' : 'Product added!');
  closeModal('modal-product');
  loadProducts();
}

async function deleteProduct(id) {
  if (!confirm('Delete this product? This cannot be undone.')) return;
  // First remove any order_items referencing this product (cascade)
  await sb.from('order_items').delete().eq('product_id', id);
  const { error } = await sb.from('products').delete().eq('product_id', id);
  if (error) {
    if (error.code === '23503') {
      return toast('Cannot delete: product has linked records. Mark it inactive instead.', 'err');
    }
    return toast('Delete error: ' + error.message, 'err');
  }
  toast('Product deleted');
  loadProducts();
}

// ════════════════════════════════════════════════════════════════
//  ORDERS
// ════════════════════════════════════════════════════════════════
async function loadOrders() {
  document.getElementById('orders-loading').style.display = 'block';
  let query = sb.from('orders').select('*').order('order_date', { ascending: false });
  const status = document.getElementById('filter-order-status')?.value;
  if (status) query = query.eq('payment_status', status);

  const { data, error } = await query;
  document.getElementById('orders-loading').style.display = 'none';
  if (error) return toast('Orders error: ' + error.message, 'err');

  const tbody = document.getElementById('orders-tbody');
  tbody.innerHTML = (data || []).map(o => `
    <tr>
      <td style="font-family:monospace;font-size:11px">${o.order_id}</td>
      <td style="font-family:monospace;font-size:11px">${(o.user_id||'').slice(0,16)}…</td>
      <td>${o.currency} ${parseFloat(o.total_amount||0).toFixed(2)}</td>
      <td><span class="badge badge-${o.payment_status}">${o.payment_status}</span></td>
      <td style="font-family:monospace;font-size:11px">${o.payment_ref||'—'}</td>
      <td>${fmtDate(o.order_date)}</td>
    </tr>`).join('') || '<tr><td colspan="6" class="table-loading">No orders</td></tr>';
}

async function exportOrdersToCSV() {
  const { data, error } = await sb.from('orders').select('*').order('order_date', { ascending: false });
  if (error) return toast('Export error: ' + error.message, 'err');
  if (!data?.length) return toast('No orders to export', 'err');

  const headers = ['Order ID', 'User ID', 'Amount', 'Currency', 'Status', 'Ref', 'Date'];
  const rows = data.map(o => [
    o.order_id,
    o.user_id,
    o.total_amount,
    o.currency,
    o.payment_status,
    o.payment_ref || '',
    o.order_date
  ]);

  const csvContent = [headers, ...rows].map(e => e.join(",")).join("\n");
  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
  const url = URL.createObjectURL(blob);
  const link = document.createElement("a");
  link.setAttribute("href", url);
  link.setAttribute("download", `madev_orders_${new Date().toISOString().split('T')[0]}.csv`);
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  toast('Orders exported to CSV');
}

// ════════════════════════════════════════════════════════════════
//  USERS
// ════════════════════════════════════════════════════════════════
async function loadUsers() {
  document.getElementById('users-loading').style.display = 'block';
  const { data, error } = await sb.from('profiles').select('*').order('created_at', { ascending: false });
  document.getElementById('users-loading').style.display = 'none';
  if (error) return toast('Users error: ' + error.message, 'err');

  const tbody = document.getElementById('users-tbody');
  tbody.innerHTML = (data || []).map(u => `
    <tr>
      <td style="font-family:monospace;font-size:11px">${u.user_id}</td>
      <td>${u.full_name || '—'}</td>
      <td style="font-family:monospace;font-size:10px;max-width:140px;overflow:hidden;text-overflow:ellipsis">${u.fcm_token||'—'}</td>
      <td>LKR ${parseFloat(u.total_spent||0).toFixed(2)}</td>
      <td>${fmtDate(u.created_at)}</td>
    </tr>`).join('') || '<tr><td colspan="5" class="table-loading">No users</td></tr>';
}

// ════════════════════════════════════════════════════════════════
//  MEETUPS
// ════════════════════════════════════════════════════════════════
async function loadMeetups() {
  document.getElementById('meetups-loading').style.display = 'block';
  const { data, error } = await sb.from('meetups').select('*').order('starts_at', { ascending: true });
  document.getElementById('meetups-loading').style.display = 'none';
  if (error) return toast('Meetups error: ' + error.message, 'err');

  const tbody = document.getElementById('meetups-tbody');
  tbody.innerHTML = (data || []).map(m => `
    <tr>
      <td><strong>${m.title}</strong></td>
      <td>${m.location || `${m.lat}, ${m.lng}`}</td>
      <td>${fmtDate(m.starts_at)}</td>
      <td><span class="badge ${m.is_active ? 'badge-active':'badge-inactive'}">${m.is_active?'active':'inactive'}</span></td>
      <td>
        <button class="btn-sm" onclick='openMeetupModal(${JSON.stringify(m).replace(/'/g,"\\'")})'  style="margin-right:6px">Edit</button>
        <button class="btn-danger" onclick="deleteMeetup('${m.meetup_id}')">Delete</button>
      </td>
    </tr>`).join('') || '<tr><td colspan="5" class="table-loading">No meetups</td></tr>';
}

function openMeetupModal(meetup = null) {
  document.getElementById('modal-meetup-title').textContent = meetup ? 'Edit Meetup' : 'Add Meetup';
  document.getElementById('mf-id').value       = meetup?.meetup_id  || '';
  document.getElementById('mf-title').value    = meetup?.title      || '';
  document.getElementById('mf-desc').value     = meetup?.description|| '';
  document.getElementById('mf-lat').value      = meetup?.lat        || 6.9271;
  document.getElementById('mf-lng').value      = meetup?.lng        || 79.8612;
  document.getElementById('mf-location').value = meetup?.location   || '';
  document.getElementById('mf-starts').value   = meetup?.starts_at ? meetup.starts_at.slice(0,16) : '';
  document.getElementById('mf-active').checked = meetup ? meetup.is_active : true;
  document.getElementById('modal-meetup').classList.remove('hidden');
}

async function saveMeetup(event) {
  event.preventDefault();
  const id = document.getElementById('mf-id').value.trim();
  const payload = {
    title:       document.getElementById('mf-title').value.trim(),
    description: document.getElementById('mf-desc').value.trim() || null,
    lat:         parseFloat(document.getElementById('mf-lat').value),
    lng:         parseFloat(document.getElementById('mf-lng').value),
    location:    document.getElementById('mf-location').value.trim() || null,
    starts_at:   document.getElementById('mf-starts').value || null,
    is_active:   document.getElementById('mf-active').checked,
  };

  let error;
  if (id) {
    ({ error } = await sb.from('meetups').update(payload).eq('meetup_id', id));
  } else {
    ({ error } = await sb.from('meetups').insert(payload));
  }
  if (error) return toast('Save error: ' + error.message, 'err');
  toast(id ? 'Meetup updated!' : 'Meetup added!');
  closeModal('modal-meetup');
  loadMeetups();
}

async function deleteMeetup(id) {
  if (!confirm('Delete this meetup?')) return;
  const { error } = await sb.from('meetups').delete().eq('meetup_id', id);
  if (error) return toast('Delete error: ' + error.message, 'err');
  toast('Meetup deleted');
  loadMeetups();
}

// ════════════════════════════════════════════════════════════════
//  BROADCAST
// ════════════════════════════════════════════════════════════════
async function sendBroadcast() {
  const title = document.getElementById('bc-title').value.trim();
  const body  = document.getElementById('bc-body').value.trim();
  if (!title || !body) return toast('Title and body are required', 'err');

  const resultEl = document.getElementById('bc-result');
  resultEl.className = 'bc-result'; resultEl.textContent = 'Sending…'; resultEl.classList.remove('hidden');

  try {
    // Get all FCM tokens
    const { data: profiles } = await sb.from('profiles').select('fcm_token').not('fcm_token', 'is', null);
    const tokens = (profiles || []).map(p => p.fcm_token).filter(Boolean);

    // Call Edge Function to broadcast
    const { error } = await sb.functions.invoke('send-push-notification', {
      body: { type: 'broadcast', title, message: body }
    });

    if (error) throw error;

    // Log to DB
    await sb.from('notifications_log').insert({ title, body, is_broadcast: true, fcm_success: tokens.length }).catch(() => {});

    resultEl.className = 'bc-result success';
    resultEl.textContent = `✓ Broadcast sent to ${tokens.length} device(s)`;
    document.getElementById('bc-title').value = '';
    document.getElementById('bc-body').value  = '';
    loadBroadcastHistory();
  } catch (e) {
    resultEl.className = 'bc-result error';
    resultEl.textContent = '✗ Error: ' + (e.message || e);
  }
}

async function loadBroadcastHistory() {
  const { data } = await sb.from('notifications_log').select('*').eq('is_broadcast', true).order('sent_at', { ascending: false }).limit(20);
  const tbody = document.getElementById('bc-history-tbody');
  tbody.innerHTML = (data || []).map(r => `
    <tr>
      <td><strong>${r.title||'—'}</strong></td>
      <td style="max-width:200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">${r.body||'—'}</td>
      <td>${fmtDate(r.sent_at)}</td>
      <td>${r.fcm_success ?? '—'}</td>
    </tr>`).join('') || '<tr><td colspan="4" class="table-loading">No history</td></tr>';
}

// ════════════════════════════════════════════════════════════════
//  GLOBAL SEARCH
// ════════════════════════════════════════════════════════════════
let searchTimer;
async function globalSearch(q) {
  clearTimeout(searchTimer);
  if (!q.trim()) return loadProducts();
  searchTimer = setTimeout(async () => {
    const { data } = await sb.from('products').select('*')
      .ilike('title', `%${q}%`).limit(20);
    
    // Always switch to products tab
    showTab('products');
    
    const tbody = document.getElementById('products-tbody');
    if (!data?.length) {
      tbody.innerHTML = '<tr><td colspan="7" class="table-loading">No search results for "'+q+'"</td></tr>';
      return;
    }
    
    tbody.innerHTML = data.map(p => `
      <tr>
        <td title="${p.title}"><strong>${p.title}</strong></td>
        <td>${catPill(p.category)}</td>
        <td>LKR ${parseFloat(p.price).toFixed(2)}</td>
        <td>${p.size_mb ? p.size_mb + ' MB' : '—'}</td>
        <td>${p.download_count || 0}</td>
        <td><span class="badge ${p.is_active ? 'badge-active':'badge-inactive'}">${p.is_active?'active':'inactive'}</span></td>
        <td>
          <button class="btn-sm" onclick='openProductModal(${JSON.stringify(p).replace(/'/g,"\\'")})'  style="margin-right:6px">Edit</button>
          <button class="btn-sm" onclick="loadProducts()">Clear</button>
        </td>
      </tr>`).join('');
  }, 300);
}

// ════════════════════════════════════════════════════════════════
//  HELPERS
// ════════════════════════════════════════════════════════════════
function closeModal(id) {
  document.getElementById(id).classList.add('hidden');
}

let toastTimer;
function toast(msg, type = 'ok') {
  const el = document.getElementById('toast');
  el.textContent = type === 'err' ? '✗ ' + msg : '✓ ' + msg;
  el.style.color  = type === 'err' ? 'var(--red)' : 'var(--teal)';
  el.style.borderColor = type === 'err' ? 'var(--red)' : 'var(--teal)';
  el.classList.remove('hidden');
  clearTimeout(toastTimer);
  toastTimer = setTimeout(() => el.classList.add('hidden'), 3500);
}

function fmtDate(iso) {
  if (!iso) return '—';
  try {
    return new Date(iso).toLocaleString('en-LK', { day:'2-digit', month:'short', year:'numeric', hour:'2-digit', minute:'2-digit' });
  } catch { return iso; }
}

function catPill(cat) {
  const map = {
    'Local_AI':  ['cat-local-ai',  'Local AI'],
    'Scripts':   ['cat-scripts',   'Scripts'],
    'Linux_ISO': ['cat-linux-iso', 'Linux ISO'],
    'Tools':     ['cat-tools',     'Tools'],
  };
  const [cls, label] = map[cat] || ['', cat];
  return `<span class="cat-pill ${cls}">${label}</span>`;
}

// Close modals on overlay click
document.querySelectorAll('.modal-overlay').forEach(el => {
  el.addEventListener('click', function(e) {
    if (e.target === this) this.classList.add('hidden');
  });
});
