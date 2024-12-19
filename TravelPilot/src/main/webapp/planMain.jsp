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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqNJI6gdzgK53NXv9JqHDeMXYPvZaQypU&libraries=places" async defer></script>
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
	
	        // content-container의 innerHTML에 makePlan.jsp 관련 요소가 있는지 확인
	        const isMakePlanLoaded = contentContainer.innerHTML.includes("id=\"map\"");
	
	        if (isMakePlanLoaded && typeof initMap === "function") {
	            console.log("makePlan.jsp is loaded. Initializing Google Map...");
	            initMap();
	        } else if (!isMakePlanLoaded) {
	            console.log("makePlan.jsp is not currently loaded.");
	        } else {
	            console.error("initMap function is not defined.");
	        }
	    });
    
        // Pikaday 초기화
        function initializePikaday() {
            console.log("Initializing Pikaday...");
            const calendarField = document.getElementById('selected-dates');

            if (!calendarField) {
                console.error("Field with id 'selected-dates' not found!");
                return;
            }

            const picker = new Pikaday({
                field: calendarField,
                format: 'YYYY.MM.DD(ddd)',
                i18n: {
                    weekdays: ['일', '월', '화', '수', '목', '금', '토'],
                    weekdaysShort: ['일', '월', '화', '수', '목', '금', '토'],
                },
                onSelect: function (date) {
                    if (typeof moment === 'function') {
                        try {
                            const selectedDate = this.getMoment().format('YYYY.MM.DD(ddd)');
                            console.log(`Selected date: ${selectedDate}`);
                            calendarField.value = selectedDate; // 입력 필드에 선택된 날짜 표시
                        } catch (error) {
                            console.error("Failed to format selected date:", error);
                        }
                    } else {
                        console.warn("Moment.js is not available. Falling back to native Date object.");
                        console.log(`Fallback date string: ${date.toDateString()}`);
                    }
                },
            });

            console.log("Pikaday instance created:", picker);
        }
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