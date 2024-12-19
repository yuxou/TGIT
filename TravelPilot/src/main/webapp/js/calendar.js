// 달력 팝업 토글
function toggleCalendarPopup(event) {
    console.log(`togglePopup called for:`, event.target);
    const calendarPopup = document.getElementById("calendar-popup");

    if (!calendarPopup) {
        console.error(`Calendar popup element not found`);
        return;
    }
    
    // 팝업 위치 조정
    const rect = event.target.getBoundingClientRect();
    calendarPopup.style.top = `${rect.bottom + window.scrollY}px`;
    calendarPopup.style.left = `${rect.left + window.scrollX}px`;

    // 팝업 토글
    if (calendarPopup.style.display === "block") {
        calendarPopup.style.display = "none";

        // 달력 숨김 처리
        const pikaSingle = calendarPopup.querySelector(".pika-single");
        if (pikaSingle) {
            pikaSingle.style.display = "none";
        }
    } else {
        calendarPopup.style.display = "block";
        initializeCalendar();

        // 달력 표시 처리
        const pikaSingle = calendarPopup.querySelector(".pika-single");
        if (pikaSingle) {
            pikaSingle.style.display = "block";
        }
    }
    console.log("Calendar popup toggled.");
}

// 달력 닫기
function closeCalendar() {
    const calendarPopup = document.getElementById("calendar-popup");
    calendarPopup.style.display = "none";
}

// 달력 초기화 함수 
// 두 날짜를 저장할 변수
let startDate = null;
let endDate = null;

function initializeCalendar() {
    const calendarPopup = document.getElementById("calendar-popup");
    const calendarElement = document.getElementById("calendar");

    if (!calendarPopup) {
        console.error(`Calendar popup element not found`);
        return;
    }

    if (!calendarElement) {
        console.error(`Calendar element not found`);
        return;
    }

    if (!calendarElement.pikaday) { // 중복 초기화 방지
        const calendar = new Pikaday({
            field: document.getElementById("selected-dates"),
            container: document.getElementById("calendar"),
            bound: false,
            format: "YYYY.MM.DD",
            moment: moment, // Moment.js 객체 전달
            onSelect: function (date) {
                const selectedMoment = this.getMoment(); // Moment.js 객체로 변환
                if (!selectedMoment) {
                    console.error("Moment.js failed to format the date.");
                    return;
                }

                if (!startDate || (startDate && endDate)) {
                    // 첫 번째 선택 또는 두 날짜가 모두 선택된 상태
                    startDate = selectedMoment;
                    endDate = null; // 두 번째 선택을 대기
                    console.log(`Start date selected: ${startDate.format("YYYY.MM.DD")}`);
                } else {
                    // 두 번째 날짜 선택
                    endDate = selectedMoment;

                    // 시작 날짜와 종료 날짜의 순서 보장
                    if (startDate.isAfter(endDate)) {
                        const temp = startDate;
                        startDate = endDate;
                        endDate = temp;
                    }
                    console.log(`End date selected: ${endDate.format("YYYY.MM.DD")}`);

                    // 날짜 선택이 완료되었을 때 방문 장소 섹션 생성
                    generateDaySections(startDate, endDate);
                }

                // 범위 업데이트
                updateDateRangeDisplay();
            },
        });
        calendarElement.pikaday = calendar;
        console.log("Pikaday calendar initialized successfully with Moment.js.");
    } else {
        console.log("Pikaday calendar is already initialized.");
    }
    const pikaSingle = calendarPopup.querySelector(".pika-single");
    if (pikaSingle) {
        pikaSingle.style.display = "block";
    }
}

// 날짜 범위를 업데이트
function updateDateRangeDisplay() {
    const dateDisplay = document.getElementById("selected-dates");

    // 요소 존재 여부 확인
    if (!dateDisplay) {
        console.error('Element with ID "selected-dates" not found.');
        return;
    }

    if (!startDate || !endDate) {
        // 한 날짜만 선택된 경우
        const singleDate = startDate ? startDate.format("YYYY.MM.DD (ddd)") : "날짜를 선택하세요";
        dateDisplay.textContent = singleDate;
    } else {
        // 두 날짜 모두 선택된 경우
        const start = startDate.format("YYYY.MM.DD (ddd)");
        const end = endDate.format("YYYY.MM.DD (ddd)");
        dateDisplay.textContent = `${start} - ${end}`;
    }
}

// 방문 장소 탭 생성
function generateDaySections(startDate, endDate) {
    const daySectionsContainer = document.getElementById("visit-tabs-container");
    if (!daySectionsContainer) {
        console.error("Visit tabs container not found.");
        return;
    }

    // 기존 day-section 초기화
    daySectionsContainer.innerHTML = "";

    const daysCount = endDate.diff(startDate, "days") + 1; // 날짜 계산
    const fragment = document.createDocumentFragment();
    let currentDate = startDate.clone();

    for (let i = 0; i < daysCount; i++) {
        // 날짜 포맷: M.D/ddd
        const dayText = currentDate.format("M.D/ddd");

        // 고유한 ID 생성 (각 섹션마다 고유한 팝업 메뉴 ID)
        const popupId = `popup-menu-location-${i}`;

        // day-section 생성
        const daySection = document.createElement("div");
        daySection.classList.add("day-section");

         const searchResultsHTML = searchResults
            .map(
                (place) => `
            <div class="result-item">
                <span>${place.name}</span>
            </div>
        `
            )
            .join("");

        daySection.innerHTML = `
            <div class="day-toggle" onclick="toggleItinerary()">
                ${i + 1}일차 <span class="date">${dayText}</span> <span class="toggle-icon">▼</span>
            </div>
            <div class="place-list" id="place-list-${i}">
                <button class="add-location-btn" onclick="togglePopup(event, '${popupId}')">+ 장소 추가</button>
            </div>
            <div class="popup-menu" id="${popupId}">
                <div class="popup-search">
                    <input type="text" id="popup-location-place-${i}" placeholder="장소 이름을 입력하세요" />
                    <button class="popup-add-btn" onclick="searchPlaces('popup-location-place-${i}', ${i})">검색</button>
                </div>
                <div id="search-results-${i}" class="search-results">
                    ${searchResultsHTML}
                </div>
            </div>
        `;

        fragment.appendChild(daySection);
        currentDate.add(1, "days");
    }

    daySectionsContainer.appendChild(fragment);
}

document.addEventListener("DOMContentLoaded", () => {
    console.log("Initializing page...");

    // HTML 요소 확인
    const dateDisplay = document.getElementById("selected-dates");
    if (!dateDisplay) {
        console.error('Element with ID "selected-dates" not found during initialization.');
        return;
    }

    // 오늘 날짜와 내일 날짜 설정
    const today = moment(); // 오늘
    const tomorrow = moment().add(1, "days"); // 내일
    startDate = today;
    endDate = tomorrow;

    // 초기 값 표시
    updateDateRangeDisplay();

    // 캘린더 초기화
    initializeCalendar();
});