<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 검색 결과</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/search.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/pagenation.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
    <script>
        // Title에 검색 키워드 설정
        const searchKeyword = "<%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %>";
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

    <div class="results">
        <h3>'<span class="highlight"><%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %></span>' 일정 검색 결과</h3>
        <div class="planResults">
            <h2>일정</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">
            <div class="schedule-item">
                <p class="number"> 1 </p>
                <p class="title">TGIT의 여행 <small><img src="https://cdn-icons-png.flaticon.com/128/17879/17879012.png" class="creator-icon">수진 외 3명</small></p>
                <p class="detail">2일차: <span class="highlight"><%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %></span></p>
            </div>
            <div class="schedule-item">
                <p class="number"> 2 </p>
                <p class="title">혼자만의 여행 <img src="https://cdn-icons-png.flaticon.com/128/17879/17879012.png" class="creator-icon"><small>솜솜이</small></p>
                <p class="detail">5일차: <span class="highlight"><%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %></span></p>
            </div>
            <div class="schedule-item">
                <p class="number"> 3 </p>
                <p class="title">친구들과 여행 <img src="https://cdn-icons-png.flaticon.com/128/17879/17879012.png" class="creator-icon"><small>김동덕 외 2명</small></p>
                <p class="detail">3일차: <span class="highlight"><%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %></span></p>
            </div>
        </div>
    </div>
    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">&raquo;</a>
    </div>
</body>
</html>