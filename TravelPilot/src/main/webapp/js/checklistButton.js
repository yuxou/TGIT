let isIncompleteActive = false;
let isCompleteActive = false;

function toggleIncomplete() {
    const incompleteButton = document.querySelector(".filter-options .filter-option:nth-child(1)");
    const completeButton = document.querySelector(".filter-options .filter-option:nth-child(2)");

    if (isIncompleteActive) {
        incompleteButton.classList.remove("active");
        incompleteButton.style.backgroundColor = "#FFFFFF";
        isIncompleteActive = false;
        updateChecklistFilter();
    } else {
        incompleteButton.classList.add("active");
        incompleteButton.style.backgroundColor = "#e0e7f4";
        completeButton.classList.remove("active");
        completeButton.style.backgroundColor = "#FFFFFF";
        isIncompleteActive = true;
        isCompleteActive = false;
        updateChecklistFilter();
    }
}

function toggleComplete() {
    const completeButton = document.querySelector(".filter-options .filter-option:nth-child(2)");
    const incompleteButton = document.querySelector(".filter-options .filter-option:nth-child(1)");

    if (isCompleteActive) {
        completeButton.classList.remove("active");
        completeButton.style.backgroundColor = "#FFFFFF";
        isCompleteActive = false;
        updateChecklistFilter();
    } else {
        completeButton.classList.add("active");
        completeButton.style.backgroundColor = "#e0e7f4";
        incompleteButton.classList.remove("active");
        incompleteButton.style.backgroundColor = "#FFFFFF";
        isCompleteActive = true;
        isIncompleteActive = false;
        updateChecklistFilter();
    }
}

function updateChecklistFilter() {
    const checklistItems = document.querySelectorAll(".checklist li");
    checklistItems.forEach(item => {
        const checkbox = item.querySelector("input[type='checkbox']");

        if (isIncompleteActive && !checkbox.checked) {
            item.style.display = "";
        } else if (isCompleteActive && checkbox.checked) {
            item.style.display = "";
        } else if (!isIncompleteActive && !isCompleteActive) {
            item.style.display = "";
        } else {
            item.style.display = "none";
        }
    });
}
