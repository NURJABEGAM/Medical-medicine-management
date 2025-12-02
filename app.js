// Sample pharmacy data with realistic medicines
const sampleMedicines = [
    {
        id: 1,
        name: 'Paracetamol 500mg',
        quantity: 120,
        price: 2.50,
        expiryDate: '2025-06-15',
        category: 'Pain Relief',
        sales: 45
    },
    {
        id: 2,
        name: 'Ibuprofen 400mg',
        quantity: 8,
        price: 3.00,
        expiryDate: '2025-07-20',
        category: 'Pain Relief',
        sales: 32
    },
    {
        id: 3,
        name: 'Amoxicillin 500mg',
        quantity: 50,
        price: 5.75,
        expiryDate: '2025-08-10',
        category: 'Antibiotics',
        sales: 28
    },
    {
        id: 4,
        name: 'Aspirin 100mg',
        quantity: 200,
        price: 1.50,
        expiryDate: '2025-12-01',
        category: 'Pain Relief',
        sales: 67
    },
    {
        id: 5,
        name: 'Metformin 500mg',
        quantity: 5,
        price: 4.25,
        expiryDate: '2025-09-15',
        category: 'Diabetes',
        sales: 15
    },
    {
        id: 6,
        name: 'Lisinopril 10mg',
        quantity: 75,
        price: 6.50,
        expiryDate: '2025-10-30',
        category: 'Blood Pressure',
        sales: 22
    },
    {
        id: 7,
        name: 'Omeprazole 20mg',
        quantity: 90,
        price: 3.80,
        expiryDate: '2025-11-05',
        category: 'Gastro',
        sales: 18
    },
    {
        id: 8,
        name: 'Vitamin C 1000mg',
        quantity: 3,
        price: 2.00,
        expiryDate: '2025-05-20',
        category: 'Supplements',
        sales: 41
    },
    {
        id: 9,
        name: 'Cetirizine 10mg',
        quantity: 110,
        price: 2.75,
        expiryDate: '2025-08-25',
        category: 'Allergy',
        sales: 25
    },
    {
        id: 10,
        name: 'Atorvastatin 20mg',
        quantity: 60,
        price: 7.50,
        expiryDate: '2025-09-30',
        category: 'Cholesterol',
        sales: 12
    },
    {
        id: 11,
        name: 'Salbutamol Inhaler',
        quantity: 35,
        price: 8.99,
        expiryDate: '2025-07-15',
        category: 'Respiratory',
        sales: 19
    },
    {
        id: 12,
        name: 'Loratadine 10mg',
        quantity: 12,
        price: 3.25,
        expiryDate: '2025-06-30',
        category: 'Allergy',
        sales: 16
    },
    {
        id: 13,
        name: 'Insulin Syringes',
        quantity: 180,
        price: 0.50,
        expiryDate: '2025-12-15',
        category: 'Medical Supplies',
        sales: 55
    },
    {
        id: 14,
        name: 'Bandages (Box of 100)',
        quantity: 4,
        price: 5.50,
        expiryDate: '2026-01-01',
        category: 'Medical Supplies',
        sales: 33
    },
    {
        id: 15,
        name: 'Cough Syrup 100ml',
        quantity: 40,
        price: 2.99,
        expiryDate: '2025-07-10',
        category: 'Cold & Cough',
        sales: 23
    }
];

// Storage key
const STORAGE_KEY = 'pharmacy_medicines';
const SALES_HISTORY_KEY = 'pharmacy_sales_history';

let allMedicines = [];
let chart = null;
let todaysSalesCount = 0;
let totalRevenueAmount = 0;

// Initialize app
document.addEventListener('DOMContentLoaded', () => {
    loadMedicines();
    initializeEventListeners();
    updateDashboard();
    updateInventoryTable();
    updateAlerts();
});

// Load medicines from localStorage or use sample data
function loadMedicines() {
    const stored = localStorage.getItem(STORAGE_KEY);
    if (stored) {
        allMedicines = JSON.parse(stored);
    } else {
        allMedicines = JSON.parse(JSON.stringify(sampleMedicines));
        saveMedicines();
    }
    loadSalesHistory();
}

// Load sales history
function loadSalesHistory() {
    const history = localStorage.getItem(SALES_HISTORY_KEY);
    if (history) {
        const salesData = JSON.parse(history);
        // Calculate today's sales and total revenue
        const today = new Date().toISOString().split('T')[0];
        todaysSalesCount = salesData.filter(s => s.date === today).length;
        totalRevenueAmount = salesData.reduce((sum, s) => sum + s.amount, 0);
    }
}

// Save medicines to localStorage
function saveMedicines() {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(allMedicines));
}

// Initialize event listeners
function initializeEventListeners() {
    // Tab navigation
    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.addEventListener('click', (e) => {
            switchTab(e.target.dataset.tab);
        });
    });

    // Form submission
    document.getElementById('addMedicineForm').addEventListener('submit', addMedicine);

    // Search input
    document.getElementById('searchInput').addEventListener('input', filterMedicines);
}

// Switch between tabs
function switchTab(tabName) {
    // Hide all tabs
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active');
    });

    // Remove active class from buttons
    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.classList.remove('active');
    });

    // Show selected tab
    document.getElementById(tabName).classList.add('active');
    document.querySelector(`[data-tab="${tabName}"]`).classList.add('active');

    // Refresh chart if switching to overview
    if (tabName === 'overview') {
        setTimeout(() => {
            if (chart) {
                chart.resize();
            }
        }, 100);
    }
}

// Update dashboard overview
function updateDashboard() {
    updateOverviewCards();
    updateSalesChart();
}

// Update overview cards
function updateOverviewCards() {
    const today = new Date().toISOString().split('T')[0];
    
    document.getElementById('totalMedicines').textContent = allMedicines.length;
    
    const lowStock = allMedicines.filter(m => m.quantity < 10).length;
    document.getElementById('lowStockCount').textContent = lowStock;
    
    document.getElementById('todaysSales').textContent = todaysSalesCount;
    document.getElementById('totalRevenue').textContent = `$${totalRevenueAmount.toFixed(2)}`;
}

// Update sales chart
function updateSalesChart() {
    const ctx = document.getElementById('salesChart').getContext('2d');
    
    // Sort medicines by sales (descending) and get top 8
    const topMedicines = [...allMedicines]
        .sort((a, b) => b.sales - a.sales)
        .slice(0, 8);

    if (chart) {
        chart.destroy();
    }

    chart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: topMedicines.map(m => m.name),
            datasets: [{
                label: 'Sales Count',
                data: topMedicines.map(m => m.sales),
                backgroundColor: 'rgba(37, 99, 235, 0.8)',
                borderColor: 'rgba(37, 99, 235, 1)',
                borderWidth: 1,
                borderRadius: 6
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: true,
            plugins: {
                legend: {
                    display: true,
                    labels: {
                        color: '#6b7280',
                        font: {
                            size: 12,
                            weight: '600'
                        }
                    }
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: '#6b7280',
                        stepSize: 1
                    },
                    grid: {
                        color: '#e5e7eb'
                    }
                },
                x: {
                    ticks: {
                        color: '#6b7280'
                    },
                    grid: {
                        display: false
                    }
                }
            }
        }
    });
}

// Update inventory table
function updateInventoryTable(medicines = allMedicines) {
    const tbody = document.getElementById('medicineTableBody');
    tbody.innerHTML = '';

    medicines.forEach(medicine => {
        const status = getStatus(medicine);
        const statusClass = getStatusClass(status);
        
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><strong>${medicine.name}</strong></td>
            <td>${medicine.quantity} units</td>
            <td>$${medicine.price.toFixed(2)}</td>
            <td>${formatDate(medicine.expiryDate)}</td>
            <td><span class="status-badge ${statusClass}">${status}</span></td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-small btn-success" onclick="sellMedicine(${medicine.id})">Sell</button>
                    <button class="btn btn-small btn-primary" onclick="editMedicine(${medicine.id})">Edit</button>
                    <button class="btn btn-small btn-danger" onclick="deleteMedicine(${medicine.id})">Delete</button>
                </div>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// Get medicine status
function getStatus(medicine) {
    const expiryDate = new Date(medicine.expiryDate);
    const today = new Date();

    if (expiryDate < today) {
        return 'Expired';
    } else if (medicine.quantity < 10) {
        return 'Low';
    } else {
        return 'In Stock';
    }
}

// Get status badge class
function getStatusClass(status) {
    switch (status) {
        case 'In Stock':
            return 'status-in-stock';
        case 'Low':
            return 'status-low';
        case 'Expired':
            return 'status-expired';
        default:
            return '';
    }
}

// Format date
function formatDate(dateString) {
    const options = { year: 'numeric', month: 'short', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('en-US', options);
}

// Add medicine
function addMedicine(e) {
    e.preventDefault();

    const newMedicine = {
        id: Math.max(...allMedicines.map(m => m.id), 0) + 1,
        name: document.getElementById('medicineName').value,
        quantity: parseInt(document.getElementById('medicineQuantity').value),
        price: parseFloat(document.getElementById('medicinePrice').value),
        expiryDate: document.getElementById('medicineExpiry').value,
        category: document.getElementById('medicineCategory').value || 'Uncategorized',
        sales: 0
    };

    allMedicines.push(newMedicine);
    saveMedicines();

    // Reset form
    document.getElementById('addMedicineForm').reset();

    // Update UI
    updateDashboard();
    updateInventoryTable();
    updateAlerts();

    // Show success message
    showNotification('Medicine added successfully!', 'success');

    // Switch to inventory tab
    switchTab('inventory');
}

// Sell medicine
function sellMedicine(id) {
    const medicine = allMedicines.find(m => m.id === id);
    if (!medicine) return;

    if (medicine.quantity > 0) {
        medicine.quantity--;
        medicine.sales = (medicine.sales || 0) + 1;
        
        // Record the sale
        recordSale(id, medicine.price);
        
        saveMedicines();
        updateDashboard();
        updateInventoryTable();
        updateAlerts();
        showNotification(`Sold 1 unit of ${medicine.name}`, 'success');
    } else {
        showNotification('Out of stock!', 'error');
    }
}

// Record sale in history
function recordSale(medicineId, price) {
    let history = [];
    const stored = localStorage.getItem(SALES_HISTORY_KEY);
    if (stored) {
        history = JSON.parse(stored);
    }

    const today = new Date().toISOString().split('T')[0];
    history.push({
        medicineId,
        date: today,
        amount: price,
        timestamp: new Date().toISOString()
    });

    localStorage.setItem(SALES_HISTORY_KEY, JSON.stringify(history));
    
    // Update counters
    loadSalesHistory();
}

// Edit medicine
function editMedicine(id) {
    const medicine = allMedicines.find(m => m.id === id);
    if (!medicine) return;

    // Populate form with medicine data
    document.getElementById('medicineName').value = medicine.name;
    document.getElementById('medicineQuantity').value = medicine.quantity;
    document.getElementById('medicinePrice').value = medicine.price;
    document.getElementById('medicineExpiry').value = medicine.expiryDate;
    document.getElementById('medicineCategory').value = medicine.category;

    // Remove the old medicine
    allMedicines = allMedicines.filter(m => m.id !== id);
    saveMedicines();

    // Update table
    updateInventoryTable();

    // Switch to add medicine tab
    switchTab('add-medicine');

    showNotification('Edit the medicine details and save to update', 'success');
}

// Delete medicine
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

// Filter medicines by search
function filterMedicines() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const filtered = allMedicines.filter(m => 
        m.name.toLowerCase().includes(searchTerm) ||
        m.category.toLowerCase().includes(searchTerm)
    );
    updateInventoryTable(filtered);
}

// Update alerts
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
        } else if (daysUntilExpiry <= 30) {
            alerts.push({
                type: 'warning',
                icon: '⏰',
                title: `Expiring Soon: ${medicine.name}`,
                message: `Expires on ${formatDate(medicine.expiryDate)} (${daysUntilExpiry} days)`,
                priority: 2
            });
        }

        if (medicine.quantity < 10) {
            alerts.push({
                type: 'warning',
                icon: '📉',
                title: `Low Stock: ${medicine.name}`,
                message: `Only ${medicine.quantity} units remaining. Consider reordering.`,
                priority: 1
            });
        }
    });

    // Sort by priority (higher first)
    alerts.sort((a, b) => b.priority - a.priority);

    if (alerts.length === 0) {
        container.innerHTML = '<div class="text-center" style="padding: 40px; color: var(--text-secondary);">✅ No alerts at this time. All medicines are in good condition!</div>';
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

// Show notification
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = type === 'success' ? 'success-message' : 'error-message';
    notification.textContent = message;
    notification.style.position = 'fixed';
    notification.style.top = '20px';
    notification.style.right = '20px';
    notification.style.zIndex = '1000';
    notification.style.maxWidth = '400px';

    document.body.appendChild(notification);

    setTimeout(() => {
        notification.remove();
    }, 3000);
}

// Export data for backup
function exportData() {
    const data = {
        medicines: allMedicines,
        timestamp: new Date().toISOString()
    };
    const dataStr = JSON.stringify(data, null, 2);
    const dataBlob = new Blob([dataStr], { type: 'application/json' });
    const url = URL.createObjectURL(dataBlob);
    const link = document.createElement('a');
    link.href = url;
    link.download = `pharmacy_backup_${new Date().toISOString().split('T')[0]}.json`;
    link.click();
}

// Import data from backup
function importData(file) {
    const reader = new FileReader();
    reader.onload = (e) => {
        try {
            const data = JSON.parse(e.target.result);
            allMedicines = data.medicines;
            saveMedicines();
            updateDashboard();
            updateInventoryTable();
            updateAlerts();
            showNotification('Data imported successfully!', 'success');
        } catch (error) {
            showNotification('Error importing data', 'error');
        }
    };
    reader.readAsText(file);
}

// Reset to sample data
function resetToSampleData() {
    if (confirm('This will reset all data to sample medicines. Continue?')) {
        allMedicines = JSON.parse(JSON.stringify(sampleMedicines));
        saveMedicines();
        localStorage.removeItem(SALES_HISTORY_KEY);
        todaysSalesCount = 0;
        totalRevenueAmount = 0;
        updateDashboard();
        updateInventoryTable();
        updateAlerts();
        showNotification('Data reset to sample medicines', 'success');
    }
}
