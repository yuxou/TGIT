<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot</title>
    <link rel="icon" href="img/favicon.png" type="image/png">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/planChecklist.css">
    <link rel="stylesheet" href="css/makePlan.css">
    <link rel="stylesheet" href="css/planMain.css">
    <link rel="stylesheet" href="css/calendar.css">
    <link rel="stylesheet" href="css/pikaday.css">
    <!-- Calendar 로드 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/pikaday@1.8.2/pikaday.js"></script>
    <!-- Google Maps API 로드 -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqNJI6gdzgK53NXv9JqHDeMXYPvZaQypU&libraries=places&callback=initMap" async defer></script>
    <!-- js 파일 로드 -->
    <script src="js/makePlan.js"></script>
    <script src="js/calendar.js"></script>
    <script src="js/planChecklist.js"></script>
    <script src="js/googleMap.js"></script>
    <script>
	    document.addEventListener("DOMContentLoaded", () => {
	        console.log("DOMContentLoaded triggered: Checking for makePlan.jsp...");
	
	        // content-container 내부의 로드된 페이지를 확인
	        const contentContainer = document.getElementById("content-container");
	        
	        if (!contentContainer) {
	            console.error("content-container not found.");
	            return;
	        }
	        
	        
	        initMap();	
	        // content-container의 innerHTML에 makePlan.jsp 관련 요소가 있는지 확인
	        /* const isMakePlanLoaded = contentContainer.innerHTML.includes("id=\"map\"");
	
	        if (isMakePlanLoaded && typeof initMap === "function") {
	            console.log("makePlan.jsp is loaded. Initializing Google Map...");
	            initMap();
	        } else if (!isMakePlanLoaded) {
	            console.log("makePlan.jsp is not currently loaded.");
	        } else {
	            console.error("initMap function is not defined.");
	        } */
	    });
    </script>
</head>
<body>
    <div class="header-container">
        <jsp:include page="header.jsp" />
    </div>
    <div class="container">
        <div class="title-container">
            <input type="text" value="제목을 입력하세요.">
            <div class="actions">
                <button class="save-btn">저장</button>
                <nav class="tabs">
                    <button id="planTab" class="tab active" onclick="showContent('makePlan')">일정</button>
                    <button id="checklistTab" class="tab" onclick="showContent('planChecklist')">체크리스트</button>
                </nav>
            </div>
        </div>
        <div id="content-container">
            <jsp:include page="makePlan.jsp" />
        </div>
    </div>
</body>
</html>