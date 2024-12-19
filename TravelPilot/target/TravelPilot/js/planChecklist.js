// 예산 행 관련 함수
function addRow() {
    var objRow;
    objRow = document.all("tblShow").insertRow();

    var objCell_place = objRow.insertCell();
    objCell_place.innerHTML = "<input type='text' class= 'budget-text' id='row_place' name='row_place' size='10' value=''/>";

    var objCell_date = objRow.insertCell();
    objCell_date.innerHTML = "<input type='text' class= 'budget-text' id='row_date' name='row_date' size='10' value=''/>";

    var objCell_cate = objRow.insertCell();
    objCell_cate.innerHTML = "<input type='text' class= 'budget-text' id='row_cate' name='row_cate' size='10' value=''/>";

    var objCell_budget = objRow.insertCell();
    objCell_budget.innerHTML = "<input type='text' class= 'budget-text' id='row_budget' name='row_budget' size='10' value=''/>";

    var objCell_note = objRow.insertCell();
    objCell_note.innerHTML = "<input type='text' class= 'budget-text' id='row_note' name='row_note' size='10' value=''/>";
}
function deleteRow(rownum) {
    // table element 찾기
    const table = document.getElementById("tblShow");

    // 행(Row) 삭제
    const newRow = table.deleteRow(-1);
}

function addCategory() {
    const newCategoryHTML = `
        <div class="category">
            <h3><input type="text" class= "checklist-text" placeholder="To Do">
            <button class="add-task-btn" onClick="addTask(this.closest('.category'))">+</button>
            <button class="delete-task-btn" onClick="deleteTask(this.closest('.category'))">-</button></h3>
            <ul class="checklist">
                <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                <li><input type="checkbox" checked> <input type="text" placeholder="Task"></li>
            </ul>
        </div>
    `;

    const checklistContainer = document.querySelector('.checklist-category');

    checklistContainer.insertAdjacentHTML('beforeend', newCategoryHTML);
}

function deleteCategory() {
    const checklistContainer = document.querySelector('.checklist-category');

    const lastCategory = checklistContainer.querySelector('.category:last-child');

    lastCategory.remove();
}

function addTask(categoryElement) {
    const taskContainer = categoryElement.querySelector('.checklist');

    const newTaskHTML = `<li><input type="checkbox"> <input type="text" placeholder="Task"></li>`;
    taskContainer.insertAdjacentHTML('beforeend', newTaskHTML);

}

function deleteTask(categoryElement) {
    // 해당 카테고리 내 'Task' 항목 찾기
    const taskContainer = categoryElement.querySelector('.checklist');

    if (taskContainer) {
        const lastTask = taskContainer.querySelector('li:last-child');

        if (lastTask) {
            lastTask.remove();
        }
    }
}

function addToggleFilterScript() {
    const script = document.createElement("script");
    script.innerHTML = `
        function toggleFilterOption(button) {
            const isActive = button.classList.contains('active');
            document.querySelectorAll('.filter-option').forEach(btn => btn.classList.remove('active'));
            if (!isActive) {
                button.classList.add('active');
            }
        }
    `;
    document.body.appendChild(script);
}