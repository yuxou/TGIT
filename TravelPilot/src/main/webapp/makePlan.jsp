<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행 계획</title>
</head>
<body>
<section class="section-container">
    <div class="container" style="display: flex; gap: 20px;">
        <!-- 왼쪽 섹션 -->
        <div class="left-section" style="flex: 1; display: flex; flex-direction: column; gap: 10px;">
            <div class="trip-info" style="display: flex; align-items: center; gap: 10px;">
                <a href="#" class="trip-info-trigger" onclick="togglePopup(event, 'popup-menu-users')">
                    <img src="../img/user2.png" alt="User2 Icon" width="24" height="24">
                    <h3 id="companion-info" style="font-size: 18px; color: #333; margin: 0;">
                        <%= request.getAttribute("companionInfo") != null ? request.getAttribute("companionInfo") : "동행인을 선택하세요" %>
                    </h3>
                </a>
                <!-- 동행자 팝업 -->
                <div class="popup-menu popup-menu-companion" id="popup-menu-users">
                    <div class="popup-search">
                        <input type="text" id="popup-search-input" placeholder="이메일을 입력하세요">
                        <button class="popup-search-btn" onclick="filterPopupMenu()">검색</button>
                    </div>
                    <ul id="user-list">
                        <%-- 동행자 검색 결과를 서버에서 동적으로 처리 --%>
                        <c:forEach var="user" items="${userList}">
                            <li>${user.name} (${user.email})</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!-- 여행 일정 -->
            <div class="trip-details">
                <div class="details-title-container">
                    <h2 class="details-title">여행 일정</h2>
                    <img src="../img/calendar.png" alt="Calendar Icon" class="calendar-icon" onclick="toggleCalendarPopup(event)">
                </div>
                <div class="date-picker-container">
                    <p class="details-text" id="selected-dates">
                        <%= request.getAttribute("selectedDates") != null ? request.getAttribute("selectedDates") : "날짜를 선택하세요" %>
                    </p>
                </div>
                <!-- 달력 팝업 -->
                <div class="calendar-popup" id="calendar-popup">
                    <div id="calendar"></div>
                    <button class="calendar-close-btn" onclick="closeCalendar()">닫기</button>
                </div>
            </div>
            <!-- 방문 국가 -->
            <div class="trip-details">
                <div class="details-title-container">
                    <h2 class="details-title">방문 국가</h2>
                    <div class="details-country">
                        <span><%= request.getAttribute("selectedCountry") != null ? request.getAttribute("selectedCountry") : "" %></span>
                    </div>
                    <button class="add-country-btn" onclick="togglePopup(event, 'popup-menu-country')">+ 국가 추가</button>
                </div>
                <div class="popup-menu" id="popup-menu-country">
                    <div class="popup-search">
                        <input type="text" id="popup-search-users" placeholder="국가명을 입력하세요.">
                        <button class="popup-add-btn" onclick="addCountry()">추가</button>
                    </div>
                </div>
            </div>
            <!-- 방문 장소 -->
            <section class="place-section">
                <h2 class="visit-title">방문 장소</h2>
                <div id="visit-tabs-container">
                    <div class="day-section">
                        <div class="day-toggle">
                            <span class="date">
                                <%= request.getAttribute("visitDate") != null ? request.getAttribute("visitDate") : "날짜를 선택하세요" %>
                            </span>
                        </div>
                        <div class="place-list" id="place-list">
                            <!-- 장소 리스트 -->
                            <c:forEach var="place" items="${placeList}">
                                <div class="place-item">
                                    <span>${place.name}</span>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- 장소 팝업 -->
                    <div class="popup-menu" id="popup-menu-location">
                        <div class="popup-search">
                            <input type="text" id="popup-location-place" placeholder="장소 이름을 입력하세요">
                        </div> 
                        <div id="search-results" class="search-results"></div>
                        <button class="popup-add-btn" onclick="searchPlaces()">검색</button>
                    </div>
                </div>
            </section>
        </div>
        <!-- 지도 -->
        <div class="map-container">
            <div class="map" id="map">
                <!-- 지도 API -->
            </div>
        </div>
    </div>
</section>
</body>
</html>