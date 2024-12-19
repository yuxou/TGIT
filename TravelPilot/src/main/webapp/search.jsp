<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="results">
        <h3>'<span class="highlight">${keyword}</span>' 검색 결과</h3>

        <!-- 장소 검색 결과 -->
        <div class="placeResults">
            <h2>장소</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">

            <c:choose>
                <c:when test="${not empty places}">
                    <c:forEach var="place" items="${places}">
                        <div class="place-card">
                            <a href="<%= request.getContextPath() %>/placeDetail.jsp?placeId=${place.placeId}">
                                <img src="<%= request.getContextPath() %>/img/${place.image}" alt="${place.name}">
                                <h3>${place.name}</h3>
                            </a>
                            <p>${place.rating} ★★★★☆ (${place.reviewsCount}) | ${place.priceRange}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="no-results">검색된 장소가 없습니다.</p>
                </c:otherwise>
            </c:choose>

            <button class="more-results-btn" onclick="location.href='<%= request.getContextPath() %>/searchPlace.jsp?keyword=${keyword}'">
                장소 검색결과 더보기
            </button>
        </div>

        <!-- 일정 검색 결과 -->
        <div class="planResults">
            <h2>일정</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">

            <c:choose>
                <c:when test="${not empty plans}">
                    <c:forEach var="plan" items="${plans}">
                        <div class="schedule-item">
                            <p class="number">${plan.planId}</p>
                            <p class="title">${plan.planTitle} 
                                <img src="<%= request.getContextPath() %>/img/creator-icon.png" class="creator-icon">
                                <small>${plan.writer.userName}</small>
                            </p>
                            <p class="detail">${plan.startDate} ~ ${plan.endDate}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="no-results">검색된 일정이 없습니다.</p>
                </c:otherwise>
            </c:choose>

            <button class="more-results-btn" onclick="location.href='<%= request.getContextPath() %>/searchPlan.jsp?keyword=${keyword}'">
                일정 검색결과 더보기
            </button>
        </div>
    </div>
</body>
</html>