<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예산 및 체크리스트</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/planChecklist.css">
    <script src="${pageContext.request.contextPath}/js/planChecklist.js"></script>
</head>
<body>
    <section class="section-container">
        <!-- Section 1: 예산 -->
        <div class="section1">
            <h2>예산</h2>
            <table class="budget-table">
                <thead>
                    <tr>
                        <th>사용처</th>
                        <th>사용 일자</th>
                        <th>비용 항목</th>
                        <th>금액</th>
                        <th>비고</th>
                    </tr>
                    <tr>
                        <td><input type="text" class="budget-text" id="row_place" name="row_place" size="10" value=""></td>
                        <td><input type="text" class="budget-text" id="row_date" name="row_date" size="10" value=""></td>
                        <td><input type="text" class="budget-text" id="row_cate" name="row_cate" size="10" value=""></td>
                        <td><input type="text" class="budget-text" id="row_budget" name="row_budget" size="10" value=""></td>
                        <td><input type="text" class="budget-text" id="row_note" name="row_note" size="10" value=""></td>
                    </tr>
                </thead>
                <tbody id="tblShow"></tbody>
            </table>
            <div class="add-btn-container">
                <button class="add-btn" onClick="addRow()">예산 추가하기 +</button>
                <button class="add-btn" onClick="deleteRow()">예산 삭제하기 -</button>
            </div>
            <div class="total">합계: 660,000원</div>
        </div>

        <!-- Section 2: 체크리스트 -->
        <div class="section2">
            <h2>체크리스트</h2>
            <div class="filter-options">
                <button class="filter-option" onclick="toggleFilterOption(this, false)">
                    <span class="checkbox"></span>미완료
                </button>
                <button class="filter-option active" onclick="toggleFilterOption(this, true)">
                    <span class="checkbox checked"></span>완료
                </button>
                <div class="category-add-btn-container">
                    <button class="category-add-btn" onClick="addCategory()">카테고리 생성 +</button>
                    <button class="category-add-btn" onClick="deleteCategory()">카테고리 삭제 -</button>
                </div>
            </div>

            <div class="checklist-category">
                <!-- Category 1 -->
                <div class="category">
                    <h3>
                        <input type="text" class="checklist-text" placeholder="To Do">
                        <button class="add-task-btn" onClick="addTask(this.closest('.category'))">+</button>
                        <button class="delete-task-btn" onClick="deleteTask(this.closest('.category'))">-</button>
                    </h3>
                    <ul class="checklist">
                        <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                        <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                        <li><input type="checkbox" checked> <input type="text" placeholder="Task"></li>
                    </ul>
                </div>

                <!-- Category 2 -->
                <div class="category">
                    <h3>
                        <input type="text" class="checklist-text" placeholder="Clothes">
                        <button class="add-task-btn" onClick="addTask(this.closest('.category'))">+</button>
                        <button class="delete-task-btn" onClick="deleteTask(this.closest('.category'))">-</button>
                    </h3>
                    <ul class="checklist">
                        <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                        <li><input type="checkbox"> <input type="text" placeholder="Task"></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
