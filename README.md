# 💊 Pharmacy Dashboard - Demo

A responsive, fully-functional pharmacy stock management dashboard built with vanilla HTML, CSS, and JavaScript. Perfect for visualizing features and getting feedback on the complete pharmacy management system.

## 🎯 Features

### Dashboard Overview
- **Overview Cards**: Display total medicines, low stock items, today's sales, and total revenue
- **Sales Chart**: Visual representation of top-selling medicines using Chart.js
- **Real-time Metrics**: Live updates as you manage inventory

### Inventory Management
- **Complete Inventory Table**: Browse all medicines with details (name, quantity, price, expiry date, status)
- **Quick Search**: Filter medicines by name or category
- **Status Indicators**: 
  - ✅ In Stock (>10 units)
  - ⚠️ Low Stock (<10 units)
  - ❌ Expired (past expiry date)

### Medicine Management
- **Add New Medicine**: Quick form to add medicines to inventory
- **Edit Medicine**: Update existing medicine details
- **Sell Medicine**: One-click selling that records transactions
- **Delete Medicine**: Remove medicines from stock

### Smart Alerts
- **Low Stock Alerts**: Medicines with <10 units
- **Expiry Warnings**: Medicines expiring within 30 days
- **Expired Alerts**: Medicines past their expiry date
- **Priority-based Sorting**: Critical alerts shown first

### Data Persistence
- **Local Storage**: All data persists in your browser
- **Automatic Backups**: Data saved with every action
- **Sample Data**: Pre-populated with 15 realistic medicines

## 🚀 Getting Started

### Installation
No installation required! This is a static site that works directly in your browser.

### Running Locally
1. Clone the repository or download the files
2. Open `index.html` in your web browser
3. Start managing medicines!

### Deploying to GitHub Pages
1. Push the code to a GitHub repository
2. Go to repository Settings → Pages
3. Select `main` branch as the source
4. Your dashboard will be live at `https://yourusername.github.io/repository-name`

## 📊 Dashboard Components

### Overview Cards Section
- **Total Medicines**: Count of all medicines in inventory
- **Low Stock Items**: Count of medicines with <10 units
- **Today's Sales**: Number of transactions processed today
- **Total Revenue**: Total sales amount from all transactions

### Sales Chart
Bar chart showing the top 8 best-selling medicines with transaction counts.

### Inventory Table
Comprehensive table with:
- Medicine Name
- Current Quantity (in units)
- Unit Price (USD)
- Expiry Date
- Status Badge (In Stock/Low/Expired)
- Action Buttons (Sell/Edit/Delete)

### Add Medicine Form
- Medicine Name (required)
- Quantity (required)
- Price in USD (required)
- Expiry Date (required)
- Category (optional)

### Alerts Section
Displays all active alerts:
- Expired items (red alert)
- Items expiring soon (orange alert)
- Low stock items (orange alert)

## 💾 Data Management

### Local Storage
All data is stored in browser's local storage:
- Medicines inventory
- Sales history
- Custom configuration

### Sample Data
Pre-populated with 15 realistic medicines including:
- Pain relief medications (Paracetamol, Ibuprofen, Aspirin)
- Antibiotics (Amoxicillin)
- Chronic disease medications (Metformin, Lisinopril, Atorvastatin)
- Allergy medications (Cetirizine, Loratadine)
- Respiratory medications (Salbutamol)
- Supplements (Vitamin C)
- Medical supplies (Insulin syringes, Bandages)

### Browser DevTools
You can manage data through browser console:

```javascript
// View all medicines
console.log(allMedicines);

// Reset to sample data
resetToSampleData();

// Export data
exportData();

// Clear all data
localStorage.removeItem('pharmacy_medicines');
localStorage.removeItem('pharmacy_sales_history');
```

## 🎨 Design Features

### Responsive Design
- Desktop optimized (1400px max width)
- Tablet friendly (768px breakpoint)
- Mobile optimized (480px breakpoint)
- Touch-friendly buttons and controls

### User Experience
- Smooth animations and transitions
- Clear visual hierarchy
- Intuitive navigation with tabs
- Real-time form validation
- Success/error notifications
- Status badges with color coding

### Color Scheme
- **Primary Blue**: #2563eb (navigation, primary actions)
- **Success Green**: #10b981 (in-stock items)
- **Warning Orange**: #f59e0b (low stock alerts)
- **Danger Red**: #ef4444 (expired items)
- **Light Gray**: #f3f4f6 (backgrounds)

## 📱 Navigation

The dashboard has 4 main sections accessible via tabs:

1. **Overview**: Dashboard cards, search, and sales chart
2. **Inventory**: Complete medicine inventory table
3. **Add Medicine**: Form to add or edit medicines
4. **Alerts**: Low stock and expiry notifications

## 🔧 Technical Stack

- **HTML5**: Semantic markup
- **CSS3**: Modern styling with CSS Grid and Flexbox
- **Vanilla JavaScript**: No frameworks, pure ES6+
- **Chart.js 3.9**: Beautiful data visualization
- **Local Storage API**: Browser-based persistence
- **Responsive Design**: Mobile-first approach

## 📋 Sample Data

The dashboard comes with 15 pre-loaded medicines:

| Medicine | Quantity | Price | Status | Category |
|----------|----------|-------|--------|----------|
| Paracetamol 500mg | 120 | $2.50 | In Stock | Pain Relief |
| Ibuprofen 400mg | 8 | $3.00 | Low | Pain Relief |
| Amoxicillin 500mg | 50 | $5.75 | In Stock | Antibiotics |
| Aspirin 100mg | 200 | $1.50 | In Stock | Pain Relief |
| Metformin 500mg | 5 | $4.25 | Low | Diabetes |
| Lisinopril 10mg | 75 | $6.50 | In Stock | Blood Pressure |
| Omeprazole 20mg | 90 | $3.80 | In Stock | Gastro |
| Vitamin C 1000mg | 3 | $2.00 | Low | Supplements |
| Cetirizine 10mg | 110 | $2.75 | In Stock | Allergy |
| Atorvastatin 20mg | 60 | $7.50 | In Stock | Cholesterol |
| Salbutamol Inhaler | 35 | $8.99 | In Stock | Respiratory |
| Loratadine 10mg | 12 | $3.25 | Low | Allergy |
| Insulin Syringes | 180 | $0.50 | In Stock | Medical Supplies |
| Bandages (Box of 100) | 4 | $5.50 | Low | Medical Supplies |
| Cough Syrup 100ml | 40 | $2.99 | In Stock | Cold & Cough |

## 🎯 How to Use

### Adding a Medicine
1. Click the "Add Medicine" tab
2. Fill in the medicine details
3. Click "Add Medicine" button
4. Success notification appears and you're redirected to inventory

### Searching Medicines
1. Go to the "Overview" tab
2. Use the search box to filter by medicine name or category
3. Results update in real-time

### Selling a Medicine
1. Go to the "Inventory" tab
2. Find the medicine in the table
3. Click the "Sell" button
4. Quantity decreases and sales are recorded
5. Check Overview tab to see updated metrics

### Editing a Medicine
1. Go to the "Inventory" tab
2. Click "Edit" button on the medicine row
3. Form populates with current details
4. Modify and add the medicine to save changes

### Viewing Alerts
1. Click the "Alerts" tab
2. See all low stock and expiry warnings
3. Alerts are sorted by priority
4. Take action by editing or ordering medicines

## ⚙️ Customization

### Modifying Colors
Edit the CSS variables in `styles.css`:

```css
:root {
    --primary-color: #2563eb;
    --success-color: #10b981;
    --warning-color: #f59e0b;
    --danger-color: #ef4444;
    /* ... more variables */
}
```

### Adding More Sample Data
Edit the `sampleMedicines` array in `app.js` to include more medicines.

### Changing Dashboard Title
Edit the header in `index.html`:

```html
<header class="header">
    <h1>💊 Pharmacy Dashboard</h1>
    <p class="subtitle">Stock Management System</p>
</header>
```

## 🐛 Troubleshooting

### Data not persisting
- Check if browser allows local storage
- Try clearing browser cache
- Use incognito/private mode to test

### Chart not displaying
- Ensure Chart.js CDN link is working
- Check browser console for errors
- Try refreshing the page

### Responsive design issues
- Check browser zoom level (should be 100%)
- Clear browser cache
- Try a different browser

## 🤝 Contributing

This is a demo/prototype dashboard. Feel free to:
- Extend with more features
- Add additional medicines
- Customize styling
- Integrate with a backend API

## 📄 License

Open source - free to use and modify

## 🎓 Learning Resources

- [MDN Web Docs](https://developer.mozilla.org)
- [Chart.js Documentation](https://www.chartjs.org)
- [CSS Grid & Flexbox](https://css-tricks.com)
- [Local Storage API](https://developer.mozilla.org/en-US/docs/Web/API/Window/localStorage)

---

**Ready to deploy to GitHub Pages? Simply push the files and enable Pages in your repository settings!**
