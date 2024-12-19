<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 검색 결과</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagenation.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png">
    <script>
        // Title에 검색 키워드 설정
        const searchKeyword = "<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "검색어 없음" %>";
        document.title = `TravelPilot: ${searchKeyword} 검색 결과`;

        // 페이지네이션에서 active 클래스 변경
        function setupPagination() {
            const pages = document.querySelectorAll('.pagination a');
            pages.forEach(page => {
                page.addEventListener('click', function(event) {
                    event.preventDefault(); // 페이지 이동 방지
                    pages.forEach(p => p.classList.remove('active')); // 모든 페이지에서 active 제거
                    this.classList.add('active'); // 클릭한 페이지에 active 추가
                });
            });
        }

        window.onload = function() {
            setupPagination();
        };
    </script>
</head>
<body>
    <!-- Header 포함 -->
    <jsp:include page="header.jsp" />

    <!-- 검색 결과 -->
    <div class="results">
        <h3>'<span class="highlight"><%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "검색어 없음" %></span>' 일정 검색 결과</h3>

        <!-- 일정 검색 결과 -->
        <div class="planResults">
            <h2>일정</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">
            
            <c:choose>
                <c:when test="${not empty plans}">
                    <c:forEach var="plan" items="${plans}" begin="${(currentPage - 1) * 9}" end="${currentPage * 9 - 1}" varStatus="status">
                        <div class="schedule-item">
                            <p class="number">${status.index + 1}</p>
                            <p class="title">
                                <c:out value="${plan.planTitle}" />
                                <img src="https://cdn-icons-png.flaticon.com/128/17879/17879012.png" class="creator-icon">
                                <small><c:out value="${plan.writer.userName}" /></small>
                            </p>
                            <p class="detail">
                                <c:out value="${plan.startDate}" /> ~ 
                                <c:out value="${plan.endDate}" />
                            </p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="no-results">검색된 일정이 없습니다.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <a href="${pageContext.request.contextPath}/searchPlan.jsp?keyword=${keyword}&page=1">&laquo;</a>
        <c:forEach var="page" begin="1" end="${totalPages}">
            <a href="${pageContext.request.contextPath}/searchPlan.jsp?keyword=${keyword}&page=${page}" 
               class="${page == currentPage ? 'active' : ''}">${page}</a>
        </c:forEach>
        <a href="${pageContext.request.contextPath}/searchPlan.jsp?keyword=${keyword}&page=${totalPages}">&raquo;</a>
    </div>
</body>
</html>