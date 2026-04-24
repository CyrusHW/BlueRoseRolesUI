let myTable;

/**
 * 1. Initialize Spreadsheet UI
 * Runs immediately to ensure the grid exists before Java calls it.
 */
function initSpreadsheet() {
    const container = document.getElementById('spreadsheet');
    if (!container) return;

    // Create the spreadsheet instance
    myTable = jspreadsheet(container, {
        data: [[]],
        columns: [
            { title: 'Robot Action', width: 250, type: 'text' },
            { title: 'Telemetry/Value', width: 200, type: 'text' },
            { title: 'Status', width: 120, type: 'dropdown', source: ['Complete', 'Running', 'Queued', 'Error'] }
        ],
        minDimensions: [3, 10], // Pre-creates a 3-column, 10-row grid
        onchange: (instance, cell, x, y, value) => {
            console.log(`Cell at ${x}, ${y} updated to: ${value}`);
        }
    });
}

/**
 * 2. Java Bridge: Constants Receiver
 * Called by App.java once the load worker succeeds.
 */
window.waitForConstants = function() {
    if (!window.CONSTANTS || !myTable) return;
    
    // Clear and set initial data from your Java Constants
    myTable.setData([
        ['System', 'Team Number', window.CONSTANTS.teamNumber || 'N/A'],
        ['System', 'Resolution', (window.CONSTANTS.windowWidth || '0') + 'x' + (window.CONSTANTS.windowHeight || '0')],
        ['Status', 'Connection', 'Initialized']
    ]);
};

/**
 * 3. Java Bridge: Live Data Functions
 * Use these via webEngine.executeScript() in your Java loop.
 */

// Adds a new row at the top (index 0)
window.addRow = function(action, value, status) {
    if (myTable) {
        myTable.insertRow([action, value, status], 0);
    }
};

// Updates a specific cell by coordinates
window.updateCell = function(row, col, newValue) {
    if (myTable) {
        myTable.setValueFromCoords(col, row, newValue);
    }
};

// Resets the sheet to empty rows
window.clearSheet = function() {
    if (myTable) {
        myTable.setData([[]]);
    }
};

/**
 * 4. Robot Connection Handlers
 */
function connectRobot() {
    document.getElementById('status').innerText = "Connecting to Robot...";
    // Add your WebSocket or Robot-specific connection logic here
}

function connectSim() {
    document.getElementById('status').innerText = "Connecting to Sim...";
}

// Kick off initialization
initSpreadsheet();
