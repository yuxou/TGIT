<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header Example</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/home.jsp">
            <img class="logo" src="${pageContext.request.contextPath}/img/logo.png" alt="로고">
        </a>

        <div class="search-bar">
            <input type="text" placeholder="장소 및 일정 검색">
            <a href="${pageContext.request.contextPath}/search.jsp">
                <button>
                    <img src="https://cdn-icons-png.flaticon.com/512/622/622669.png" alt="돋보기 아이콘" onclick="search()">
                </button>
            </a>
        </div>

        <nav class="nav">
            <div class="header-btn">
                <a href="${pageContext.request.contextPath}/planMain.jsp">계획하기</a>
            </div>
            <a class="header-text" href="${pageContext.request.contextPath}/login.jsp">로그인</a>
            <a class="header-text" href="${pageContext.request.contextPath}/myPage.jsp">마이페이지</a>
        </nav>
    </header>
</body>
</html>
