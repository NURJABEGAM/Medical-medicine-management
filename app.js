// Enhanced Pharmacy Dashboard - Advanced Stock Management System

// Sample pharmacy data with 25+ realistic Indian pharmacy medicines
const sampleMedicines = [
    { id: 1, name: 'Paracetamol 500mg', quantity: 150, price: 45, expiryDate: '2025-12-15', manufacturer: 'Cipla Ltd', supplier: 'MedCare Pharma', category: 'Pain Relief', batchNo: 'BATCH001', sales: 120 },
    { id: 2, name: 'Ibuprofen 400mg', quantity: 8, price: 65, expiryDate: '2025-06-20', manufacturer: 'Lupin Ltd', supplier: 'HealthPharm', category: 'Pain Relief', batchNo: 'BATCH002', sales: 85 },
    { id: 3, name: 'Amoxicillin 500mg', quantity: 50, price: 180, expiryDate: '2025-08-10', manufacturer: 'Sun Pharma', supplier: 'MedCare Pharma', category: 'Antibiotics', batchNo: 'BATCH003', sales: 60 },
    { id: 4, name: 'Aspirin 100mg', quantity: 200, price: 35, expiryDate: '2025-12-01', manufacturer: 'Bayer', supplier: 'Global Pharma', category: 'Pain Relief', batchNo: 'BATCH004', sales: 150 },
    { id: 5, name: 'Metformin 500mg', quantity: 5, price: 120, expiryDate: '2025-09-15', manufacturer: 'Dr. Reddy', supplier: 'MedCare Pharma', category: 'Diabetes', batchNo: 'BATCH005', sales: 45 },
    { id: 6, name: 'Lisinopril 10mg', quantity: 75, price: 250, expiryDate: '2025-10-30', manufacturer: 'Cipla Ltd', supplier: 'HealthPharm', category: 'Blood Pressure', batchNo: 'BATCH006', sales: 70 },
    { id: 7, name: 'Omeprazole 20mg', quantity: 90, price: 95, expiryDate: '2025-11-05', manufacturer: 'Lupin Ltd', supplier: 'MedCare Pharma', category: 'Gastro', batchNo: 'BATCH007', sales: 55 },
    { id: 8, name: 'Vitamin C 1000mg', quantity: 3, price: 80, expiryDate: '2025-05-20', manufacturer: 'Nature\'s Bounty', supplier: 'Global Pharma', category: 'Supplements', batchNo: 'BATCH008', sales: 110 },
    { id: 9, name: 'Cetirizine 10mg', quantity: 110, price: 75, expiryDate: '2025-08-25', manufacturer: 'Cipla Ltd', supplier: 'HealthPharm', category: 'Allergy', batchNo: 'BATCH009', sales: 65 },
    { id: 10, name: 'Atorvastatin 20mg', quantity: 60, price: 210, expiryDate: '2025-09-30', manufacturer: 'Pfizer', supplier: 'MedCare Pharma', category: 'Cholesterol', batchNo: 'BATCH010', sales: 40 },
    { id: 11, name: 'Salbutamol Inhaler', quantity: 35, price: 350, expiryDate: '2025-07-15', manufacturer: 'GlaxoSmithKline', supplier: 'Global Pharma', category: 'Respiratory', batchNo: 'BATCH011', sales: 50 },
    { id: 12, name: 'Loratadine 10mg', quantity: 12, price: 85, expiryDate: '2025-06-30', manufacturer: 'Cipla Ltd', supplier: 'HealthPharm', category: 'Allergy', batchNo: 'BATCH012', sales: 38 },
    { id: 13, name: 'Insulin Syringes (Pack)', quantity: 180, price: 200, expiryDate: '2025-12-15', manufacturer: 'BD Medical', supplier: 'MedCare Pharma', category: 'Medical Supplies', batchNo: 'BATCH013', sales: 92 },
    { id: 14, name: 'Bandages (Box of 100)', quantity: 4, price: 180, expiryDate: '2026-01-01', manufacturer: 'Elastoplast', supplier: 'Global Pharma', category: 'Medical Supplies', batchNo: 'BATCH014', sales: 75 },
    { id: 15, name: 'Cough Syrup 100ml', quantity: 40, price: 95, expiryDate: '2025-07-10', manufacturer: 'Benadryl', supplier: 'HealthPharm', category: 'Cold & Cough', batchNo: 'BATCH015', sales: 88 },
    { id: 16, name: 'Azithromycin 500mg', quantity: 25, price: 140, expiryDate: '2025-09-20', manufacturer: 'Sun Pharma', supplier: 'MedCare Pharma', category: 'Antibiotics', batchNo: 'BATCH016', sales: 35 },
    { id: 17, name: 'Ranitidine 150mg', quantity: 95, price: 55, expiryDate: '2025-10-12', manufacturer: 'Dr. Reddy', supplier: 'HealthPharm', category: 'Gastro', batchNo: 'BATCH017', sales: 42 },
    { id: 18, name: 'Amlodipine 5mg', quantity: 130, price: 95, expiryDate: '2025-11-28', manufacturer: 'Cipla Ltd', supplier: 'MedCare Pharma', category: 'Blood Pressure', batchNo: 'BATCH018', sales: 78 },
    { id: 19, name: 'Domperidone 10mg', quantity: 110, price: 65, expiryDate: '2025-09-05', manufacturer: 'Lupin Ltd', supplier: 'Global Pharma', category: 'Gastro', batchNo: 'BATCH019', sales: 52 },
    { id: 20, name: 'Mefenamic Acid 250mg', quantity: 60, price: 75, expiryDate: '2025-08-18', manufacturer: 'Cipla Ltd', supplier: 'HealthPharm', category: 'Pain Relief', batchNo: 'BATCH020', sales: 48 },
    { id: 21, name: 'Levocetirizine 5mg', quantity: 85, price: 80, expiryDate: '2025-07-22', manufacturer: 'Dr. Reddy', supplier: 'MedCare Pharma', category: 'Allergy', batchNo: 'BATCH021', sales: 62 },
    { id: 22, name: 'Fluconazole 150mg', quantity: 22, price: 320, expiryDate: '2025-10-10', manufacturer: 'Cipla Ltd', supplier: 'Global Pharma', category: 'Anti-fungal', batchNo: 'BATCH022', sales: 28 },
    { id: 23, name: 'Prednisone 10mg', quantity: 45, price: 85, expiryDate: '2025-12-05', manufacturer: 'Sun Pharma', supplier: 'HealthPharm', category: 'Steroid', batchNo: 'BATCH023', sales: 33 },
    { id: 24, name: 'Miconazole Cream 2%', quantity: 38, price: 120, expiryDate: '2025-06-15', manufacturer: 'Cipla Ltd', supplier: 'MedCare Pharma', category: 'Topical', batchNo: 'BATCH024', sales: 29 },
    { id: 25, name: 'Iron Supplements 325mg', quantity: 155, price: 70, expiryDate: '2025-11-10', manufacturer: 'Fortis Health', supplier: 'Global Pharma', category: 'Supplements', batchNo: 'BATCH025', sales: 97 }
];

// Storage keys
const STORAGE_KEY = 'pharmacy_medicines';
const SALES_HISTORY_KEY = 'pharmacy_sales_history';
const PURCHASE_ORDERS_KEY = 'pharmacy_purchase_orders';

let allMedicines = [];
let allSalesHistory = [];
let allPurchaseOrders = [];
let charts = {};
let editingMedicineId = null;

document.addEventListener('DOMContentLoaded', () => {
    loadAllData();
    initializeEventListeners();
    initializeUI();
    updateDashboard();
    updateInventoryTable();
    updateSalesHistoryTable();
    updatePurchaseOrdersList();
    updateAlerts();
});

function loadAllData() {
    const storedMedicines = localStorage.getItem(STORAGE_KEY);
    if (storedMedicines) {
        allMedicines = JSON.parse(storedMedicines);
    } else {
        allMedicines = JSON.parse(JSON.stringify(sampleMedicines));
        saveMedicines();
    }

    const storedSalesHistory = localStorage.getItem(SALES_HISTORY_KEY);
    if (storedSalesHistory) {
        allSalesHistory = JSON.parse(storedSalesHistory);
    }

    const storedPurchaseOrders = localStorage.getItem(PURCHASE_ORDERS_KEY);
    if (storedPurchaseOrders) {
        allPurchaseOrders = JSON.parse(storedPurchaseOrders);
    }
}

function initializeEventListeners() {
    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            switchTab(e.currentTarget.dataset.tab);
        });
    });

    document.getElementById('addMedicineForm').addEventListener('submit', addMedicine);
    document.getElementById('addPurchaseOrderForm').addEventListener('submit', addPurchaseOrder);

    document.getElementById('filterByName').addEventListener('input', applyFilters);
    document.getElementById('filterByCategory').addEventListener('change', applyFilters);
    document.getElementById('filterByManufacturer').addEventListener('change', applyFilters);
    document.getElementById('filterByStatus').addEventListener('change', applyFilters);

    document.getElementById('filterByDate').addEventListener('change', updateSalesHistoryTable);

    document.getElementById('darkModeToggle').addEventListener('click', toggleDarkMode);

    document.querySelector('.close').addEventListener('click', () => {
        document.getElementById('medicineModal').style.display = 'none';
    });

    window.addEventListener('click', (e) => {
        const modal = document.getElementById('medicineModal');
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    });
}

function initializeUI() {
    populateCategoryFilter();
    populateManufacturerFilter();
    populateMedicineSelect();
}

function populateCategoryFilter() {
    const categories = [...new Set(allMedicines.map(m => m.category))].sort();
    const filterSelect = document.getElementById('filterByCategory');
    categories.forEach(cat => {
        const option = document.createElement('option');
        option.value = cat;
        option.textContent = cat;
        filterSelect.appendChild(option);
    });
}

function populateManufacturerFilter() {
    const manufacturers = [...new Set(allMedicines.map(m => m.manufacturer))].sort();
    const filterSelect = document.getElementById('filterByManufacturer');
    manufacturers.forEach(mfg => {
        const option = document.createElement('option');
        option.value = mfg;
        option.textContent = mfg;
        filterSelect.appendChild(option);
    });
}

function populateMedicineSelect() {
    const select = document.getElementById('poMedicine');
    allMedicines.forEach(medicine => {
        const option = document.createElement('option');
        option.value = medicine.id;
        option.textContent = medicine.name;
        select.appendChild(option);
    });
}

function saveMedicines() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(allMedicines));
}

function saveSalesHistory() {
    localStorage.setItem(SALES_HISTORY_KEY, JSON.stringify(allSalesHistory));
}

function savePurchaseOrders() {
    localStorage.setItem(PURCHASE_ORDERS_KEY, JSON.stringify(allPurchaseOrders));
}

function switchTab(tabName) {
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });

    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.classList.remove('active');
    });

    document.getElementById(tabName).classList.add('active');
    document.querySelector(`[data-tab="${tabName}"]`).classList.add('active');

    if (tabName === 'overview') {
        setTimeout(() => {
            if (charts.salesChart) charts.salesChart.resize();
            if (charts.revenueChart) charts.revenueChart.resize();
        }, 100);
    }
}

function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
    localStorage.setItem('darkMode', document.body.classList.contains('dark-mode'));
}

// Check for saved dark mode preference
if (localStorage.getItem('darkMode') === 'true') {
    document.body.classList.add('dark-mode');
}

// Dashboard Updates
function updateDashboard() {
    updateOverviewCards();
    updateSalesChart();
    updateRevenueChart();
}

function updateOverviewCards() {
    const lowStockCount = allMedicines.filter(m => m.quantity < 10).length;
    const expirySoonCount = allMedicines.filter(m => {
        const daysUntilExpiry = Math.ceil((new Date(m.expiryDate) - new Date()) / (1000 * 60 * 60 * 24));
        return daysUntilExpiry > 0 && daysUntilExpiry <= 30;
    }).length;

    const todaysSales = allSalesHistory
        .filter(s => s.date === new Date().toISOString().split('T')[0])
        .reduce((sum, s) => sum + s.amount, 0);

    const totalRevenue = allSalesHistory.reduce((sum, s) => sum + s.amount, 0);
    const inventoryValue = allMedicines.reduce((sum, m) => sum + (m.quantity * m.price), 0);
    const totalUnits = allMedicines.reduce((sum, m) => sum + m.quantity, 0);
    const avgPrice = allMedicines.length > 0 ? (allMedicines.reduce((sum, m) => sum + m.price, 0) / allMedicines.length) : 0;

    document.getElementById('totalMedicines').textContent = allMedicines.length;
    document.getElementById('lowStockCount').textContent = lowStockCount;
    document.getElementById('expirySoonCount').textContent = expirySoonCount;
    document.getElementById('todaysSales').textContent = `₹${todaysSales.toFixed(2)}`;
    document.getElementById('totalRevenue').textContent = `₹${totalRevenue.toFixed(2)}`;
    document.getElementById('inventoryValue').textContent = `₹${inventoryValue.toFixed(2)}`;
    document.getElementById('totalUnits').textContent = totalUnits;
    document.getElementById('totalCategories').textContent = [...new Set(allMedicines.map(m => m.category))].length;
    document.getElementById('avgPrice').textContent = `₹${avgPrice.toFixed(2)}`;
}

function updateSalesChart() {
    const ctx = document.getElementById('salesChart').getContext('2d');
    const topMedicines = [...allMedicines]
        .sort((a, b) => b.sales - a.sales)
        .slice(0, 10);

    if (charts.salesChart) {
        charts.salesChart.destroy();
    }

    charts.salesChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: topMedicines.map(m => m.name),
            datasets: [{
                label: 'Units Sold',
                data: topMedicines.map(m => m.sales),
                backgroundColor: 'rgba(37, 99, 235, 0.8)',
                borderColor: 'rgba(37, 99, 235, 1)',
                borderWidth: 2,
                borderRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: { display: true, labels: { color: '#6b7280', font: { size: 12, weight: '600' } } }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { color: '#6b7280', stepSize: 1 },
                    grid: { color: '#e5e7eb' }
                },
                x: {
                    ticks: { color: '#6b7280' },
                    grid: { display: false }
                }
            }
        }
    });
}

function updateRevenueChart() {
    const ctx = document.getElementById('revenueChart').getContext('2d');
    const last7Days = getLast7DaysRevenue();

    if (charts.revenueChart) {
        charts.revenueChart.destroy();
    }

    charts.revenueChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: last7Days.map(d => d.date),
            datasets: [{
                label: 'Revenue (₹)',
                data: last7Days.map(d => d.revenue),
                borderColor: 'rgba(16, 185, 129, 1)',
                backgroundColor: 'rgba(16, 185, 129, 0.1)',
                borderWidth: 3,
                fill: true,
                tension: 0.4,
                pointRadius: 5,
                pointBackgroundColor: 'rgba(16, 185, 129, 1)'
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: { display: true, labels: { color: '#6b7280', font: { size: 12, weight: '600' } } }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: { color: '#6b7280' },
                    grid: { color: '#e5e7eb' }
                },
                x: {
                    ticks: { color: '#6b7280' },
                    grid: { display: false }
                }
            }
        }
    });
}

function getLast7DaysRevenue() {
    const days = [];
    for (let i = 6; i >= 0; i--) {
        const date = new Date();
        date.setDate(date.getDate() - i);
        const dateStr = date.toISOString().split('T')[0];
        const revenue = allSalesHistory
            .filter(s => s.date === dateStr)
            .reduce((sum, s) => sum + s.amount, 0);
        days.push({ date: date.toLocaleDateString('en-IN', { month: 'short', day: 'numeric' }), revenue });
    }
    return days;
}

// Medicine Management
function addMedicine(e) {
    e.preventDefault();

    const medicineData = {
        id: editingMedicineId || (Math.max(...allMedicines.map(m => m.id), 0) + 1),
        name: document.getElementById('medicineName').value,
        quantity: parseInt(document.getElementById('medicineQuantity').value),
        price: parseFloat(document.getElementById('medicinePrice').value),
        expiryDate: document.getElementById('medicineExpiry').value,
        category: document.getElementById('medicineCategory').value,
        manufacturer: document.getElementById('medicineManufacturer').value,
        supplier: document.getElementById('medicineSupplier').value,
        batchNo: document.getElementById('medicineBatchNo').value || 'N/A',
        sales: editingMedicineId ? allMedicines.find(m => m.id === editingMedicineId).sales : 0
    };

    if (editingMedicineId) {
        allMedicines = allMedicines.map(m => m.id === editingMedicineId ? medicineData : m);
        showNotification('Medicine updated successfully!', 'success');
        editingMedicineId = null;
    } else {
        allMedicines.push(medicineData);
        showNotification('Medicine added successfully!', 'success');
    }

    saveMedicines();
    document.getElementById('addMedicineForm').reset();
    updateDashboard();
    updateInventoryTable();
    updateAlerts();
    initializeUI();
    switchTab('inventory');
}

function editMedicine(id) {
    const medicine = allMedicines.find(m => m.id === id);
    if (!medicine) return;

    document.getElementById('medicineName').value = medicine.name;
    document.getElementById('medicineQuantity').value = medicine.quantity;
    document.getElementById('medicinePrice').value = medicine.price;
    document.getElementById('medicineExpiry').value = medicine.expiryDate;
    document.getElementById('medicineCategory').value = medicine.category;
    document.getElementById('medicineManufacturer').value = medicine.manufacturer;
    document.getElementById('medicineSupplier').value = medicine.supplier;
    document.getElementById('medicineBatchNo').value = medicine.batchNo;

    editingMedicineId = id;
    switchTab('add-medicine');
    showNotification('Edit medicine and click save', 'info');
}

function deleteMedicine(id) {
    if (confirm('Are you sure you want to delete this medicine?')) {
        allMedicines = allMedicines.filter(m => m.id !== id);
        saveMedicines();
        updateDashboard();
        updateInventoryTable();
        updateAlerts();
        showNotification('Medicine deleted successfully!', 'success');
    }
}

function sellMedicine(id) {
    const medicine = allMedicines.find(m => m.id === id);
    if (!medicine) return;

    if (medicine.quantity > 0) {
        medicine.quantity--;
        medicine.sales = (medicine.sales || 0) + 1;

        const today = new Date().toISOString().split('T')[0];
        allSalesHistory.push({
            medicineId: id,
            medicineName: medicine.name,
            date: today,
            time: new Date().toLocaleTimeString('en-IN'),
            quantity: 1,
            amount: medicine.price,
            timestamp: new Date().toISOString()
        });

        saveMedicines();
        saveSalesHistory();
        updateDashboard();
        updateInventoryTable();
        updateSalesHistoryTable();
        updateAlerts();
        showNotification(`Sold 1 unit of ${medicine.name}`, 'success');
    } else {
        showNotification('Out of stock!', 'error');
    }
}

function viewMedicineDetails(id) {
    const medicine = allMedicines.find(m => m.id === id);
    if (!medicine) return;

    const modal = document.getElementById('medicineModal');
    const details = document.getElementById('medicineDetails');

    const status = getMedicineStatus(medicine);

    details.innerHTML = `
        <div class="medicine-modal-header">
            <h2>${medicine.name}</h2>
            <span class="status-badge status-${status.class}">${status.text}</span>
        </div>
        <div class="medicine-modal-details">
            <div class="detail-group">
                <div class="detail-label">Manufacturer</div>
                <div class="detail-value">${medicine.manufacturer}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Supplier</div>
                <div class="detail-value">${medicine.supplier}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Category</div>
                <div class="detail-value">${medicine.category}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Batch/Lot Number</div>
                <div class="detail-value">${medicine.batchNo}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Quantity in Stock</div>
                <div class="detail-value">${medicine.quantity} units</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Unit Price</div>
                <div class="detail-value">₹${medicine.price.toFixed(2)}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Total Value</div>
                <div class="detail-value">₹${(medicine.quantity * medicine.price).toFixed(2)}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Expiry Date</div>
                <div class="detail-value">${formatDate(medicine.expiryDate)}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Units Sold</div>
                <div class="detail-value">${medicine.sales}</div>
            </div>
            <div class="detail-group">
                <div class="detail-label">Revenue Generated</div>
                <div class="detail-value">₹${(medicine.sales * medicine.price).toFixed(2)}</div>
            </div>
        </div>
        <div style="margin-top: 20px; display: flex; gap: 10px;">
            <button class="btn btn-success" onclick="sellMedicine(${medicine.id})">
                <i class="fas fa-cart-plus"></i> Record Sale
            </button>
            <button class="btn btn-primary" onclick="editMedicine(${medicine.id})">
                <i class="fas fa-edit"></i> Edit
            </button>
            <button class="btn btn-danger" onclick="deleteMedicine(${medicine.id})">
                <i class="fas fa-trash"></i> Delete
            </button>
        </div>
    `;

    modal.style.display = 'block';
}

// Inventory Table
function updateInventoryTable(medicines = null) {
    const medicinesToShow = medicines || allMedicines;
    const tbody = document.getElementById('medicineTableBody');
    tbody.innerHTML = '';

    if (medicinesToShow.length === 0) {
        tbody.innerHTML = '<tr><td colspan="10" class="no-data"><i class="fas fa-inbox"></i><p>No medicines found</p></td></tr>';
        return;
    }

    medicinesToShow.forEach(medicine => {
        const status = getMedicineStatus(medicine);
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><strong>${medicine.name}</strong></td>
            <td><code>${medicine.batchNo}</code></td>
            <td>${medicine.quantity}</td>
            <td>₹${medicine.price.toFixed(2)}</td>
            <td>${formatDate(medicine.expiryDate)}</td>
            <td>${medicine.manufacturer}</td>
            <td>${medicine.supplier}</td>
            <td>${medicine.category}</td>
            <td><span class="status-badge status-${status.class}">${status.text}</span></td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-small btn-success" onclick="sellMedicine(${medicine.id})" title="Record Sale"><i class="fas fa-shopping-cart"></i></button>
                    <button class="btn btn-small btn-primary" onclick="viewMedicineDetails(${medicine.id})" title="View Details"><i class="fas fa-eye"></i></button>
                    <button class="btn btn-small btn-primary" onclick="editMedicine(${medicine.id})" title="Edit"><i class="fas fa-edit"></i></button>
                    <button class="btn btn-small btn-danger" onclick="deleteMedicine(${medicine.id})" title="Delete"><i class="fas fa-trash"></i></button>
                </div>
            </td>
        `;
        tbody.appendChild(row);
    });
}

function getMedicineStatus(medicine) {
    const expiryDate = new Date(medicine.expiryDate);
    const today = new Date();
    const daysUntilExpiry = Math.ceil((expiryDate - today) / (1000 * 60 * 60 * 24));

    if (expiryDate < today) {
        return { text: 'Expired', class: 'expired' };
    } else if (medicine.quantity < 10) {
        return { text: 'Low Stock', class: 'low' };
    } else {
        return { text: 'In Stock', class: 'in-stock' };
    }
}

function applyFilters() {
    const nameFilter = document.getElementById('filterByName').value.toLowerCase();
    const categoryFilter = document.getElementById('filterByCategory').value;
    const manufacturerFilter = document.getElementById('filterByManufacturer').value;
    const statusFilter = document.getElementById('filterByStatus').value;

    const filtered = allMedicines.filter(m => {
        const matchesName = m.name.toLowerCase().includes(nameFilter) || m.category.toLowerCase().includes(nameFilter);
        const matchesCategory = !categoryFilter || m.category === categoryFilter;
        const matchesManufacturer = !manufacturerFilter || m.manufacturer === manufacturerFilter;

        let matchesStatus = true;
        if (statusFilter) {
            const status = getMedicineStatus(m);
            matchesStatus = status.class === statusFilter;
        }

        return matchesName && matchesCategory && matchesManufacturer && matchesStatus;
    });

    updateInventoryTable(filtered);
}

function formatDate(dateString) {
    const options = { year: 'numeric', month: 'short', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('en-IN', options);
}

// Sales History
function updateSalesHistoryTable() {
    const filterDate = document.getElementById('filterByDate').value;
    let salesToShow = allSalesHistory;

    if (filterDate) {
        salesToShow = allSalesHistory.filter(s => s.date === filterDate);
    }

    const tbody = document.getElementById('salesHistoryTableBody');
    tbody.innerHTML = '';

    if (salesToShow.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6" class="no-data"><i class="fas fa-inbox"></i><p>No sales found</p></td></tr>';
        return;
    }

    const reversedSales = [...salesToShow].reverse();
    reversedSales.forEach(sale => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${sale.date}</td>
            <td>${sale.time}</td>
            <td>${sale.medicineName}</td>
            <td>${sale.quantity}</td>
            <td>₹${sale.amount.toFixed(2)}</td>
            <td>₹${(sale.quantity * sale.amount).toFixed(2)}</td>
        `;
        tbody.appendChild(row);
    });
}

// Purchase Orders
function addPurchaseOrder(e) {
    e.preventDefault();

    const medicineId = parseInt(document.getElementById('poMedicine').value);
    const medicine = allMedicines.find(m => m.id === medicineId);

    if (!medicine) {
        showNotification('Please select a valid medicine', 'error');
        return;
    }

    const purchaseOrder = {
        id: Math.max(...allPurchaseOrders.map(p => p.id), 0) + 1,
        medicineId: medicineId,
        medicineName: medicine.name,
        supplier: document.getElementById('poSupplier').value,
        quantity: parseInt(document.getElementById('poQuantity').value),
        unitPrice: parseFloat(document.getElementById('poPrice').value),
        totalAmount: parseInt(document.getElementById('poQuantity').value) * parseFloat(document.getElementById('poPrice').value),
        expectedDate: document.getElementById('poExpectedDate').value,
        status: document.getElementById('poStatus').value,
        notes: document.getElementById('poNotes').value,
        createdDate: new Date().toISOString().split('T')[0]
    };

    allPurchaseOrders.push(purchaseOrder);
    savePurchaseOrders();
    document.getElementById('addPurchaseOrderForm').reset();
    updatePurchaseOrdersList();
    showNotification('Purchase order created successfully!', 'success');
    switchTab('purchase-orders');
}

function updatePurchaseOrdersList() {
    const container = document.getElementById('purchaseOrdersList');
    container.innerHTML = '';

    if (allPurchaseOrders.length === 0) {
        container.innerHTML = '<div class="no-data"><i class="fas fa-inbox"></i><p>No purchase orders yet</p></div>';
        return;
    }

    const reversedOrders = [...allPurchaseOrders].reverse();
    reversedOrders.forEach(po => {
        const card = document.createElement('div');
        card.className = 'purchase-order-card';

        const statusClass = `status-${po.status}`;

        card.innerHTML = `
            <div class="po-header">
                <div class="po-medicine">${po.medicineName}</div>
                <span class="po-status ${statusClass}">${po.status}</span>
            </div>
            <div class="po-details">
                <div class="po-detail-row">
                    <span class="po-detail-label">Supplier:</span>
                    <span class="po-detail-value">${po.supplier}</span>
                </div>
                <div class="po-detail-row">
                    <span class="po-detail-label">Quantity:</span>
                    <span class="po-detail-value">${po.quantity} units</span>
                </div>
                <div class="po-detail-row">
                    <span class="po-detail-label">Unit Price:</span>
                    <span class="po-detail-value">₹${po.unitPrice.toFixed(2)}</span>
                </div>
                <div class="po-detail-row">
                    <span class="po-detail-label">Total Amount:</span>
                    <span class="po-detail-value">₹${po.totalAmount.toFixed(2)}</span>
                </div>
                <div class="po-detail-row">
                    <span class="po-detail-label">Expected Date:</span>
                    <span class="po-detail-value">${po.expectedDate || 'N/A'}</span>
                </div>
                <div class="po-detail-row">
                    <span class="po-detail-label">Created:</span>
                    <span class="po-detail-value">${po.createdDate}</span>
                </div>
                ${po.notes ? `<div class="po-detail-row"><span class="po-detail-label">Notes:</span><span class="po-detail-value">${po.notes}</span></div>` : ''}
            </div>
            <div class="po-actions">
                <button class="btn btn-small btn-success" onclick="updatePOStatus(${po.id}, 'in-transit')">
                    <i class="fas fa-shipping-fast"></i> In Transit
                </button>
                <button class="btn btn-small btn-primary" onclick="receivePO(${po.id})">
                    <i class="fas fa-check"></i> Receive
                </button>
                <button class="btn btn-small btn-danger" onclick="deletePO(${po.id})">
                    <i class="fas fa-trash"></i> Delete
                </button>
            </div>
        `;
        container.appendChild(card);
    });
}

function updatePOStatus(poId, newStatus) {
    const po = allPurchaseOrders.find(p => p.id === poId);
    if (po) {
        po.status = newStatus;
        savePurchaseOrders();
        updatePurchaseOrdersList();
        showNotification('Purchase order status updated!', 'success');
    }
}

function receivePO(poId) {
    const po = allPurchaseOrders.find(p => p.id === poId);
    if (!po) return;

    const medicine = allMedicines.find(m => m.id === po.medicineId);
    if (medicine) {
        medicine.quantity += po.quantity;
        saveMedicines();
        updateDashboard();
        updateInventoryTable();
    }

    po.status = 'received';
    savePurchaseOrders();
    updatePurchaseOrdersList();
    showNotification(`Received ${po.quantity} units of ${po.medicineName}!`, 'success');
}

function deletePO(poId) {
    if (confirm('Are you sure you want to delete this purchase order?')) {
        allPurchaseOrders = allPurchaseOrders.filter(p => p.id !== poId);
        savePurchaseOrders();
        updatePurchaseOrdersList();
        showNotification('Purchase order deleted!', 'success');
    }
}

// Alerts
function updateAlerts() {
    const container = document.getElementById('alertsContainer');
    container.innerHTML = '';

    const today = new Date();
    const alerts = [];

    allMedicines.forEach(medicine => {
        const expiryDate = new Date(medicine.expiryDate);
        const daysUntilExpiry = Math.ceil((expiryDate - today) / (1000 * 60 * 60 * 24));

        if (expiryDate < today) {
            alerts.push({
                type: 'danger',
                icon: '❌',
                title: `EXPIRED: ${medicine.name}`,
                message: `Expired on ${formatDate(medicine.expiryDate)}. Please remove from stock.`,
                priority: 3
            });
        } else if (daysUntilExpiry <= 7) {
            alerts.push({
                type: 'danger',
                icon: '⏰',
                title: `Expiring Very Soon: ${medicine.name}`,
                message: `Expires on ${formatDate(medicine.expiryDate)} (${daysUntilExpiry} days)`,
                priority: 3
            });
        } else if (daysUntilExpiry <= 30) {
            alerts.push({
                type: 'warning',
                icon: '🔔',
                title: `Expiring Soon: ${medicine.name}`,
                message: `Expires on ${formatDate(medicine.expiryDate)} (${daysUntilExpiry} days)`,
                priority: 2
            });
        }

        if (medicine.quantity < 5) {
            alerts.push({
                type: 'danger',
                icon: '🚨',
                title: `Critical Stock: ${medicine.name}`,
                message: `Only ${medicine.quantity} units left. Urgent reorder needed.`,
                priority: 3
            });
        } else if (medicine.quantity < 10) {
            alerts.push({
                type: 'warning',
                icon: '📉',
                title: `Low Stock: ${medicine.name}`,
                message: `Only ${medicine.quantity} units remaining. Consider reordering.`,
                priority: 2
            });
        }
    });

    alerts.sort((a, b) => b.priority - a.priority);

    if (alerts.length === 0) {
        container.innerHTML = '<div class="no-data"><i class="fas fa-check-circle"></i><p>✅ No alerts at this time. All medicines are in good condition!</p></div>';
        return;
    }

    alerts.forEach(alert => {
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${alert.type}`;
        alertDiv.innerHTML = `
            <div style="display: flex; align-items: center; flex: 1;">
                <span class="alert-icon">${alert.icon}</span>
                <div class="alert-content">
                    <h4>${alert.title}</h4>
                    <p>${alert.message}</p>
                </div>
            </div>
        `;
        container.appendChild(alertDiv);
    });
}

// Reports & Export
function exportInventoryCSV() {
    let csv = 'Medicine Name,Batch No,Quantity,Price (₹),Expiry Date,Manufacturer,Supplier,Category,Status\n';

    allMedicines.forEach(medicine => {
        const status = getMedicineStatus(medicine);
        csv += `"${medicine.name}","${medicine.batchNo}",${medicine.quantity},${medicine.price},"${medicine.expiryDate}","${medicine.manufacturer}","${medicine.supplier}","${medicine.category}","${status.text}"\n`;
    });

    downloadCSV(csv, 'pharmacy_inventory.csv');
}

function exportInventoryJSON() {
    const data = {
        exportDate: new Date().toISOString(),
        medicines: allMedicines,
        summary: {
            totalMedicines: allMedicines.length,
            totalInventoryValue: allMedicines.reduce((sum, m) => sum + (m.quantity * m.price), 0),
            totalUnits: allMedicines.reduce((sum, m) => sum + m.quantity, 0)
        }
    };

    const json = JSON.stringify(data, null, 2);
    const blob = new Blob([json], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `pharmacy_inventory_${new Date().toISOString().split('T')[0]}.json`;
    link.click();
}

function exportSalesCSV() {
    let csv = 'Date,Time,Medicine,Quantity,Price (₹),Total (₹)\n';

    allSalesHistory.forEach(sale => {
        csv += `"${sale.date}","${sale.time}","${sale.medicineName}",${sale.quantity},${sale.amount},${sale.quantity * sale.amount}\n`;
    });

    downloadCSV(csv, 'pharmacy_sales_history.csv');
}

function downloadCSV(csv, filename) {
    const blob = new Blob([csv], { type: 'text/csv' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    link.click();
}

function generateProfitLossReport() {
    const reportContainer = document.getElementById('profitLossReport');

    const totalRevenue = allSalesHistory.reduce((sum, s) => sum + s.amount, 0);
    const costOfGoods = allMedicines.reduce((sum, m) => sum + (m.quantity * m.price), 0);
    const profit = totalRevenue - costOfGoods;

    let html = `
        <table class="profit-loss-table">
            <tr>
                <th>Metric</th>
                <th>Amount (₹)</th>
            </tr>
            <tr>
                <td><strong>Total Revenue</strong></td>
                <td>₹${totalRevenue.toFixed(2)}</td>
            </tr>
            <tr>
                <td><strong>Cost of Goods (Inventory Value)</strong></td>
                <td>₹${costOfGoods.toFixed(2)}</td>
            </tr>
            <tr>
                <td><strong>Gross Profit/Loss</strong></td>
                <td style="color: ${profit >= 0 ? '#10b981' : '#ef4444'}; font-weight: bold;">
                    ₹${profit.toFixed(2)}
                </td>
            </tr>
            <tr>
                <td><strong>Profit Margin (%)</strong></td>
                <td style="color: ${profit >= 0 ? '#10b981' : '#ef4444'}; font-weight: bold;">
                    ${totalRevenue > 0 ? ((profit / totalRevenue) * 100).toFixed(2) : '0.00'}%
                </td>
            </tr>
            <tr>
                <td><strong>Total Units Sold</strong></td>
                <td>${allSalesHistory.reduce((sum, s) => sum + s.quantity, 0)}</td>
            </tr>
            <tr>
                <td><strong>Average Sale Value</strong></td>
                <td>₹${allSalesHistory.length > 0 ? (totalRevenue / allSalesHistory.length).toFixed(2) : '0.00'}</td>
            </tr>
        </table>
    `;

    reportContainer.innerHTML = html;
}

function printInventory() {
    const printWindow = window.open('', '', 'width=900,height=700');
    let html = `
        <html><head><title>Pharmacy Inventory Report</title>
        <style>
            body { font-family: Arial, sans-serif; }
            table { width: 100%; border-collapse: collapse; margin-top: 20px; }
            th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
            th { background-color: #2563eb; color: white; }
            .header { text-align: center; margin-bottom: 20px; }
            .header h1 { color: #2563eb; }
            .date { color: #666; font-size: 12px; }
        </style>
        </head><body>
        <div class="header">
            <h1>Pharmacy Inventory Report</h1>
            <p class="date">Generated on: ${new Date().toLocaleString('en-IN')}</p>
        </div>
        <table>
            <tr>
                <th>Medicine Name</th>
                <th>Batch #</th>
                <th>Qty</th>
                <th>Price (₹)</th>
                <th>Expiry</th>
                <th>Manufacturer</th>
                <th>Value</th>
            </tr>
    `;

    allMedicines.forEach(m => {
        html += `<tr>
            <td>${m.name}</td>
            <td>${m.batchNo}</td>
            <td>${m.quantity}</td>
            <td>₹${m.price.toFixed(2)}</td>
            <td>${formatDate(m.expiryDate)}</td>
            <td>${m.manufacturer}</td>
            <td>₹${(m.quantity * m.price).toFixed(2)}</td>
        </tr>`;
    });

    html += `</table></body></html>`;

    printWindow.document.write(html);
    printWindow.document.close();
    printWindow.print();
}

function exportData() {
    const data = {
        medicines: allMedicines,
        salesHistory: allSalesHistory,
        purchaseOrders: allPurchaseOrders,
        timestamp: new Date().toISOString()
    };

    const json = JSON.stringify(data, null, 2);
    const blob = new Blob([json], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `pharmacy_backup_${new Date().toISOString().split('T')[0]}.json`;
    link.click();
}

function importData(file) {
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (e) => {
        try {
            const data = JSON.parse(e.target.result);
            allMedicines = data.medicines || [];
            allSalesHistory = data.salesHistory || [];
            allPurchaseOrders = data.purchaseOrders || [];
            saveMedicines();
            saveSalesHistory();
            savePurchaseOrders();
            updateDashboard();
            updateInventoryTable();
            updateSalesHistoryTable();
            updatePurchaseOrdersList();
            updateAlerts();
            initializeUI();
            showNotification('Data imported successfully!', 'success');
        } catch (error) {
            showNotification('Error importing data: ' + error.message, 'error');
        }
    };
    reader.readAsText(file);
}

function resetToSampleData() {
    if (confirm('This will reset all data to sample medicines. Continue?')) {
        allMedicines = JSON.parse(JSON.stringify(sampleMedicines));
        allSalesHistory = [];
        allPurchaseOrders = [];
        saveMedicines();
        saveSalesHistory();
        savePurchaseOrders();
        updateDashboard();
        updateInventoryTable();
        updateSalesHistoryTable();
        updatePurchaseOrdersList();
        updateAlerts();
        initializeUI();
        showNotification('Data reset to sample medicines', 'success');
    }
}

// Notifications
function showNotification(message, type = 'success') {
    const container = document.getElementById('notificationContainer');
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;

    const iconMap = {
        success: 'fas fa-check-circle',
        error: 'fas fa-exclamation-circle',
        warning: 'fas fa-warning',
        info: 'fas fa-info-circle'
    };

    notification.innerHTML = `
        <i class="${iconMap[type]}"></i>
        <span>${message}</span>
    `;

    container.appendChild(notification);

    setTimeout(() => {
        notification.remove();
    }, 3000);
}
