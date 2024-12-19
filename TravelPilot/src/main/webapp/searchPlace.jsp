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
            loadHeader();
            setupPagination();
        };
    </script>
</head>
<body>
 <jsp:include page="header.jsp" />
    <div id="header-container"></div> 

    <div class="results">
        <h3>'<span class="highlight"><%= request.getAttribute("searchKeyword") != null ? request.getAttribute("searchKeyword") : "검색어 없음" %></span>' 장소 검색 결과</h3>
        <div class="placeResults">
            <h2>장소</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">
            <div class="place-card">
                <a href="<%= request.getContextPath() %>/placeDetail.jsp">
                    <img src="<%= request.getContextPath() %>/img/search_image1.png" alt="이치란 본점">
                    <h3>이치란 본점</h3>
                </a>
                <p>4.2 ★★★★☆ (10,228) | ¥1,000~2,000</p>
            </div>
            <div class="place-card">
                <a href="<%= request.getContextPath() %>/placeDetail.jsp">
                    <