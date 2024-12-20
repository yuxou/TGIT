<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.HashMap, java.util.List, java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%-- <%
    List<Map<String, Object>> itineraries = new ArrayList<>();

    // 첫 번째 날 데이터 생성
    Map<String, Object> day1 = new HashMap<>();
    day1.put("date", "2024-12-20");
    List<Map<String, Object>> places1 = new ArrayList<>();
    Map<String, Object> place1 = new HashMap<>();
    place1.put("index", 1);
    place1.put("name", "서울 타워");
    place1.put("address", "서울시 용산구");
    places1.add(place1);
    Map<String, Object> place2 = new HashMap<>();
    place2.put("index", 2);
    place2.put("name", "경복궁");
    place2.put("address", "서울시 종로구");
    places1.add(place2);
    day1.put("places", places1);

    // 두 번째 날 데이터 생성
    Map<String, Object> day2 = new HashMap<>();
    day2.put("date", "2024-12-21");
    List<Map<String, Object>> places2 = new ArrayList<>();
    Map<String, Object> place3 = new HashMap<>();
    place3.put("index", 1);
    place3.put("name", "부산 해운대");
    place3.put("address", "부산시 해운대구");
    places2.add(place3);
    Map<String, Object> place4 = new HashMap<>();
    place4.put("index", 2);
    place4.put("name", "광안대교");
    place4.put("address", "부산시 수영구");
    places2.add(place4);
    day2.put("places", places2);

    // 일정 추가
    itineraries.add(day1);
    itineraries.add(day2);

    // 방문 국가 및 제목 추가
    List<String> countries = new ArrayList<>();
    countries.add("대한민국");
    
    // 동행인 목록
    List<String> companions = new ArrayList<>();
    companions.add("민하");
    companions.add("유주");
    companions.add("다현");

    // 작성자 및 여행 정보
    Map<String, String> writer = new HashMap<>();
    writer.put("name", "수진");
    writer.put("email", "soojin@example.com");

    String title = "2024 겨울 여행";
    String startDate = "2024-12-20"; // 여행 첫날
    String endDate = "2024-12-21"; // 여행 마지막 날

    // JSP에서 사용할 속성 설정
    Map<String, Object> plan = new HashMap<>();
    plan.put("itineraries", itineraries); // 일정 데이터
    plan.put("countries", countries);    // 방문 국가
    plan.put("planTitle", title);        // 여행 제목
    plan.put("startDate", startDate);      // 여행 첫날
    plan.put("endDate", endDate);        // 여행 마지막날
    plan.put("writer", writer);          // 작성자
    plan.put("companions", companions);  // 동행인 목록
    request.setAttribute("plan", plan);
%> --%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot</title>
    <link rel="icon" href="img/favicon.png" type="image/png">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/planMain.css">
    <link rel="stylesheet" href="css/makePlan.css">
    <link rel="stylesheet" href="css/calendar.css">
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqNJI6gdzgK53NXv9JqHDeMXYPvZaQypU&libraries=places&callback=initMap" async defer></script>
    <script src="js/makePlan.js"></script>
    <script src="js/googleMap.js"></script>
    <script src="js/calendar.js"></script>
    <style>
    .place-list {
    display: block; /* 항상 보이도록 설정 */
    }
        .details-country {
            display: flex; /* 가로 배치 */
            gap: 10px; /* 항목 간 간격 */
            list-style: none; /* 기본 리스트 스타일 제거 */
            padding: 0;
            margin: 0;
        }
    </style>
    <script>
        // 일정 토글
        function toggleItinerary(dayId) {
            const list = document.getElementById(`place-list-${dayId}`);
            list.classList.toggle('show');
        }
        
        function editPlan() {
            const form = document.createElement("form");
            form.method = "GET"; // 데이터 유지 (GET 방식)
            form.action = "planMain.jsp"; // 이동할 페이지

            // 현재 데이터를 유지하기 위해 hidden input 추가
            const currentData = JSON.stringify(${plan}); // JSP의 plan 데이터
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "planData";
            input.value = currentData;

            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        }
        
        function confirmDelete() {
            if (confirm("정말로 이 계획을 삭제하시겠습니까?")) {
                // AJAX로 삭제 요청
                fetch("deletePlan", {
                    method: "POST", // DELETE 요청
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ planId: ${plan.planId} }), // plan ID를 서버에 전달
                })
                .then(response => {
                    if (response.ok) {
                        alert("계획이 삭제되었습니다.");
                        window.location.href = "planList.jsp"; // 목록 페이지로 이동
                    } else {
                        alert("계획 삭제에 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error("삭제 요청 실패:", error);
                    alert("오류가 발생했습니다.");
                });
            }
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
    <div id="header-container"></div> 
    <div class="container">
        <div class="title-container">
            <!-- 계획 제목 -->
            <h1>${plan.planTitle}</h1>
            <nav class="tabs">
                <%-- 계획 삭제 버튼: 작성자인 경우에만 표시 --%>
				<c:if test="${currentUser.name == plan.writer.name}">
				    <button id="deletePlanButton" class="tab" onclick="confirmDelete()">계획 삭제</button>
				</c:if>
				<%-- 계획 수정 버튼: 작성자이거나 동행인인 경우에만 표시 --%>
				<c:if test="${currentUser.name == plan.writer.name || fn:contains(plan.companions, currentUser.name)}">
				    <button id="editPlanButton" class="tab" onclick="editPlan()">계획 수정</button>
				</c:if>
            </nav>
        </div>
        <section class="section-container">
            <div class="container" style="display: flex; gap: 20px;">
                <div class="left-section" style="flex: 1; display: flex; flex-direction: column; gap: 10px;">
                    <div class="trip-info" style="display: flex; align-items: center; gap: 10px;">
                        <img src="img/user2.png" alt="User Icon" width="24" height="24">
                        <h3 style="font-size: 18px; color: #333; margin: 0;">
                            ${plan.writer.name}
                            <c:if test="${not empty plan.companions}">
                                외 ${fn:length(plan.companions)}인
                            </c:if>
                        </h3>
                    </div>
                    <div class="trip-details">
                        <div class="details-title-container">
                            <h2 class="details-title">여행 일정</h2>
                        </div>
                        <div class="date-picker-container">
                            <p class="details-text">${plan.startDate} ~ ${plan.endDate}</p>
                        </div>
                        <div class="trip-details">
                            <div class="details-title-container">
                                <h2 class="details-title">방문 국가</h2>
                                <ul class="details-country">
                                    <c:forEach var="country" items="${plan.countries}">
                                        <li>${country}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <section class="place-section">
				    <h2 class="visit-title">방문 장소</h2>
				    <div id="visit-tabs-container">
				        <!-- 여러 날에 대한 반복 -->
				        <c:forEach var="day" items="${plan.itineraries}" varStatus="status">
				            <div class="day-section">
				                <!-- 일자 제목 -->
				                <div class="day-toggle">
				                    ${status.index + 1}일차 <span class="date">${day.date}</span> 
				                </div>
				                <!-- 장소 리스트 -->
				                <div class="place-list" id="place-list-${status.index}">
				                    <c:forEach var="place" items="${day.places}">
				                        <div class="place-item">
				                            <div class="icon circle" style="pointer-events: none;">${place.index}</div>
				                            <div class="content">
				                                <div class="location">${place.name}</div>
				                                <div class="details">${place.address}</div>
				                            </div>
				                        </div>
				                    </c:forEach>
				                </div>
				            </div>
				        </c:forEach>
				    </div>
				</section>
                </div>
                <div class="map-container">
                    <div class="map" id="map">
                        <!-- 지도 API가 여기에 표시됩니다 -->
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>
</html>