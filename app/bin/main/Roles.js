const phasesContainer = document.getElementById("phases");
const nextPhaseContainer = document.getElementById("nextPhase");

// Global reference to the specific box you want to update later
let nextPhaseBoxElement; 
let phaseBoxes = []; 

function init() {
    // 1. Create the standalone box
    nextPhaseBoxElement = document.createElement("div");
    nextPhaseBoxElement.className = "nextPhaseBox";
    
    // Accessing index 0 as an example; ensure mainPhaseTimes exists
    nextPhaseBoxElement.innerText = CONSTANTS.mainPhaseTimes[0]; 
    nextPhaseContainer.appendChild(nextPhaseBoxElement);

    // 2. Create the list of boxes
    for (let i = 0; i < CONSTANTS.mainPhaseTimes.length; i++) {
        let box = document.createElement("div");
        box.className = "phaseBox";
        box.innerText = CONSTANTS.mainPhaseTimes[i];
        phaseBoxes.push(box); // Store in array if you need to bulk-update colors
        phasesContainer.appendChild(box);
    }
}

// Example function to update that specific box later from Java
function updateCountdown(newValue) {
    if (nextPhaseBoxElement) {
        nextPhaseBoxElement.innerText = newValue;
        // Example logic: flash red if low
        nextPhaseBoxElement.style.background = newValue < 3 ? "red" : "grey";
    }
}
