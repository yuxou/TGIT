function initializePlaceListListeners() {
    const container = document.getElementById("visit-tabs-container");

    if (!container) {
        console.error("Visit tabs container not found.");
        return;
    }

    console.log("Initializing place list listeners...");

    // 이벤트 위임 방식으로 place-list 내 클릭 이벤트 처리
    container.addEventListener("click", (event) => {
        const target = event.target;

        // 클릭된 요소가 "icon" 클래스인지 확인
        if (target.classList.contains("icon")) {
            console.log("Icon clicked:", target);

            // 클릭된 day-section 요소와 dayIndex 추출
            const daySection = target.closest(".day-section");
            if (!daySection) {
                console.error("Day section not found for clicked icon.");
                return;
            }

            // dayIndex 추출
            const dayIndex = Array.from(container.querySelectorAll(".day-section")).indexOf(daySection);
            if (dayIndex === -1) {
                console.error("Invalid day section. Could not determine dayIndex.");
                return;
            }

            console.log(`Day Index: ${dayIndex}`);

            // 해당 day-section의 place-list 찾기
            const placeList = document.getElementById(`place-list-${dayIndex}`);
            if (!placeList) {
                console.error(`Place list with ID "place-list-${dayIndex}" not found.`);
                return;
            }

            // 클릭된 place-item 찾기
            const clickedPlace = target.closest(".place-item");
            if (!clickedPlace) {
                console.error("Clicked place item not found.");
                return;
            }

            // place-item의 인덱스를 계산
            const placeItems = Array.from(placeList.querySelectorAll(".place-item"));
            const index = placeItems.indexOf(clickedPlace);

            if (index !== -1) {
                console.log(`Deleting place at index: ${index}`);
                deletePlace(placeList, index); // 삭제 함수 호출
            } else {
                console.error("Invalid place index. Could not find clicked place in the list.");
            }
        }
    });

    console.log("Place list listeners initialized.");
}

function showContent(content) {
    if (content === 'makePlan') {
        loadJSP('makePlan.jsp', 'content-container');
        initializePlaceListListeners(); // makePlan.jsp 로드 이후 호출
        document.getElementById('planTab').classList.add('active');
        document.getElementById('checklistTab').classList.remove('active');
    } else if (content === 'planChecklist') {
        loadJSP('planChecklist.jsp', 'content-container');
        document.getElementById('checklistTab').classList.add('active');
        document.getElementById('planTab').classList.remove('active');
    }
}

function loadJSP(jspFile, containerId, callback) {
    const container = document.getElementById(containerId);

    if (!container) {
        console.error(`Container with ID "${containerId}" not found.`);
        return;
    }

    fetch(jspFile)
        .then((response) => {
            if (response.ok) {
                return response.text();
            } else {
                console.error(`Failed to load JSP: ${jspFile}`);
            }
        })
        .then((html) => {
            if (html) {
                container.innerHTML = html;
                console.log(`Loaded ${jspFile} into #${containerId}`);
                if (typeof callback === "function") {
                    callback();
                }
            }
        })
        .catch((error) => {
            console.error(`Error loading JSP file "${jspFile}":`, error);
        });
}

function toggleItinerary() {
    const daySection = event.currentTarget.closest(".day-section");
    const placeList = daySection.querySelector(".place-list");
    const toggleIcon = daySection.querySelector(".toggle-icon");

    if (!placeList) {
        console.error("placeList not found in the current daySection");
        return;
    }

    console.log("Toggling placeList visibility:", placeList);

    if (placeList.classList.contains("show")) {
        placeList.classList.remove("show");
        toggleIcon.textContent = "▼";
    } else {
        placeList.classList.add("show");
        toggleIcon.textContent = "▲";
    }
}

function toggleItinerary2(event) {
    const daySection = event.currentTarget.closest(".day-section"); // 이벤트 발생한 day-section 찾기
    if (!daySection) {
        console.error("daySection not found");
        return;
    }

    const placeList = daySection.querySelector(".place-list");
    const toggleIcon = daySection.querySelector(".toggle-icon");

    if (!placeList) {
        console.error("placeList not found in the current daySection");
        return;
    }

    console.log("Toggling placeList visibility:", placeList);

    if (placeList.classList.contains("show")) {
        placeList.classList.remove("show");
        toggleIcon.textContent = "▼";
    } else {
        placeList.classList.add("show");
        toggleIcon.textContent = "▲";
    }
}

function addCountry() {
    // 입력 필드 값 가져오기
    const countryInput = document.getElementById("popup-search-users");
    const countryName = countryInput.value.trim(); // 공백 제거

    if (countryName === "") {
        alert("국가명을 입력하세요.");
        return;
    }

    // 국가명을 표시할 텍스트 요소 가져오기
    const countryList = document.querySelector(".details-country");

    // 현재 추가된 국가의 개수 확인
    const existingCountries = Array.from(countryList.querySelectorAll("span"))
        .filter(span => span.textContent.trim() !== ""); // 공백 제거 후 유효한 국가만 카운트

    // 새로운 국가 추가
    const newCountry = document.createElement("span");

    if (existingCountries.length === 0) {
        // 첫 번째 국가에는 쉼표 추가하지 않음
        newCountry.textContent = countryName;
    } else {
        // 두 번째부터 쉼표 추가
        newCountry.textContent = `, ${countryName}`;
    }

    newCountry.style.marginLeft = "5px";

    // 국가 목록에 추가
    countryList.appendChild(newCountry);

    // 입력 필드 초기화
    countryInput.value = "";

    // 팝업 닫기
    document.getElementById('popup-menu-country').classList.remove('show');
}

// 추가하기 토글 (동행인, 국가, 장소)
function togglePopup(event, popupId) {
    console.log(`togglePopup triggered for popup ID: ${popupId}`);
    event.preventDefault();

    const button = event.currentTarget; // 클릭한 버튼
    const popup = document.getElementById(popupId);

    if (!popup) {
        console.error(`Popup with ID "${popupId}" not found`);
        return;
    }

    // 버튼의 화면 기준 위치 계산
    const rect = button.getBoundingClientRect();

    // 버튼의 상위 요소 기준 위치 계산 (보정)
    const parent = button.offsetParent; // 버튼의 부모 요소
    const parentRect = parent ? parent.getBoundingClientRect() : { top: 0, left: 0 };

    let topOffset = rect.bottom - parentRect.top + window.scrollY; // 부모 기준 보정
    const leftOffset = rect.left - parentRect.left + window.scrollX; // 부모 기준 보정

    console.log("Button rect:", rect);
    console.log("Parent rect:", parentRect)
    console.log("Popup calculated top, left:", topOffset, leftOffset);

    // 팝업 메뉴 위치 설정
    popup.style.position = "fixed";
    popup.style.top = `${rect.bottom + 10}px`; // 버튼 아래로 10px 여백
    popup.style.left = `${rect.left}px`;
    popup.style.display = popup.style.display === "block" ? "none" : "block";
    popup.style.zIndex = "1000";

    // 팝업 토글
    popup.classList.toggle("show");

    if (popupId === "popup-menu-users") {
        // 동행인 팝업일 경우, 열릴 때 리스트 초기화
        initializeCompanionPopup();
    }

    // 다른 팝업 메뉴 숨기기
    document.querySelectorAll(".popup-menu").forEach(menu => {
        if (menu.id !== popupId) menu.classList.remove("show");
    });
}       

// 팝업 외부 클릭 시 닫기
document.addEventListener("click", function (event) {
    console.log("Document click detected. Event target:", event.target);

    const popupMenus = document.querySelectorAll(".popup-menu");
    const triggers = document.querySelectorAll(".trip-info-trigger, .add-country-btn, .add-location-btn");

    let isClickInsidePopup = false;

    popupMenus.forEach(popup => {
        if (popup.contains(event.target)) {
            console.log("Click occurred inside popup:", popup);
            isClickInsidePopup = true;
        }
    });

    let isClickOnTrigger = false;

    triggers.forEach(trigger => {
        if (trigger.contains(event.target)) {
            console.log("Click occurred on trigger:", trigger);
            isClickOnTrigger = true;
        }
    });

    // 팝업 내부 또는 트리거 클릭이 아닐 경우 팝업 닫기
    if (!isClickInsidePopup && !isClickOnTrigger) {
        console.log("Click outside popup and trigger. Closing all visible popups.");
        popupMenus.forEach(popup => {
            popup.classList.remove("show");
            popup.style.display = "none"; // 팝업 숨김 처리
        });
    }
});

// 팝업 내부 클릭 시 이벤트 전파 방지
document.querySelectorAll(".popup-menu").forEach(popup => {
    popup.addEventListener("click", (event) => {
        console.log("Popup clicked, stopping event propagation.");
        event.stopPropagation();
    });
});

function addPlace() {
    // 입력 필드 값 가져오기
    const placeInput = document.getElementById("popup-location-place");
    if (!placeInput) {
        console.error('Input field with ID "popup-location-place" not found.');
        return;
    }

    const placeName = placeInput.value.trim();

    if (placeName === "") {
        alert("장소 이름을 입력하세요.");
        return;
    }

    // 방문 장소 리스트 가져오기
    const placeList = document.getElementById("place-list");
    if (!placeList) {
        console.error('Element with ID "place-list" not found.');
        return;
    }

    // 추가 버튼 확인 및 위치 설정
    const addLocationButton = placeList.querySelector(".add-location-btn");
    if (!addLocationButton) {
        console.error('Add location button not found in the place list.');
        return;
    }

    // 기존 장소 아이템 확인
    const placeItems = placeList.querySelectorAll(".place-item");
    const newIndex = placeItems.length + 1; // 새로운 번호 계산

    // 새 장소 항목 생성
    const placeItem = document.createElement("div");
    placeItem.className = "place-item";
    placeItem.innerHTML = `
        <div class="icon circle">${newIndex}</div>
        <div class="content">
            <div class="location">${placeName}</div>
            <div class="details">대한민국 인천 (예시)</div>
        </div>
    `;

    // 새 항목을 추가 버튼 위에 삽입
    placeList.insertBefore(placeItem, addLocationButton);

    // 디버깅 코드
    console.log("Adding place:", placeName);
    console.log("New place index:", newIndex);
    console.log("Updated place list HTML:", placeList.innerHTML);

    // 입력 필드 초기화
    placeInput.value = "";

    // 팝업 닫기
   
    // 추가된 장소 아이템 확인 후 팝업 닫기
    const updatedPlaceItems = placeList.querySelectorAll(".place-item");
    if (updatedPlaceItems.length > placeItems.length) {
        const popupMenu = document.getElementById("popup-menu-location");
        if (popupMenu) {
            popupMenu.classList.remove("show");
        }
        console.log("Place added and popup closed.");
    } else {
        console.error("Place was not added. Popup remains open.");
    }
}

document.addEventListener("click", (event) => {
    const target = event.target;

    // 클릭된 요소가 "icon" 클래스인지 확인
    if (target.classList.contains("icon")) {
        console.log("Icon clicked:", target);

        // 가장 가까운 place-item 요소를 찾음
        const clickedPlace = target.closest(".place-item");
        if (!clickedPlace) {
            console.error("Clicked place item not found.");
            return;
        }

        // place-list의 부모 day-section을 찾음
        const daySection = target.closest(".day-section");
        if (!daySection) {
            console.error("Day section not found.");
            return;
        }

        // dayIndex 추출
        const visitTabsContainer = document.getElementById("visit-tabs-container");
        const dayIndex = Array.from(visitTabsContainer.querySelectorAll(".day-section")).indexOf(daySection);

        if (dayIndex === -1) {
            console.error("Invalid day section. Could not determine dayIndex.");
            return;
        }

        console.log(`Day Index: ${dayIndex}`);

        // 해당 dayIndex에 맞는 place-list를 찾음
        const placeList = document.getElementById(`place-list-${dayIndex}`);
        if (!placeList) {
            console.error(`Place list with ID "place-list-${dayIndex}" not found.`);
            return;
        }

        // place-item의 인덱스를 계산
        const placeItems = Array.from(placeList.querySelectorAll(".place-item"));
        const index = placeItems.indexOf(clickedPlace);

        if (index !== -1) {
            console.log(`Deleting place at index: ${index} in place-list-${dayIndex}`);
            deletePlace(placeList, index); 
        } else {
            console.error("Invalid place index. Could not find clicked place in the list.");
        }
    }
});

function deletePlace(placeList, index) {
    const placeItems = placeList.querySelectorAll(".place-item");

    if (index < 0 || index >= placeItems.length) {
        console.error(`Invalid index: ${index}`);
        return;
    }

    const placeToRemove = placeItems[index];
    console.log("Removing place item:", placeToRemove);

    // 삭제
    placeList.removeChild(placeToRemove);

    console.log(`Place at index ${index} deleted.`);

    // 남은 장소 넘버링 업데이트
    updatePlaceNumbers(placeList);
}

function updatePlaceNumbers() {
    const placeItems = placeList.querySelectorAll(".place-item");

    placeItems.forEach((item, idx) => {
        const icon = item.querySelector(".icon");
        if (icon) {
            icon.textContent = idx + 1; // 새 번호로 업데이트
        }
    });

    console.log("Place numbers updated.");
}


// 동행인 관련 함수

// 기능 구현 확인용 배열
// 동행인 배열 (첫 번째는 계획 생성자, 고정)
let companions = [
    { name: "수진", email: "hsj1111@naver.com" } // 작성자
];

// 미동행인 배열
let nonCompanions = [
    { name: "민하", email: "kmh0098@naver.com" },
    { name: "다현", email: "jdh13590@naver.com" },
    { name: "유주", email: "syj12345@naver.com" }
];

// 동행인 정보를 업데이트하는 함수
function updateCompanionInfo() {
    console.log("Updating companion info...");

    const companionInfoElement = document.getElementById("companion-info");
    if (!companionInfoElement) {
        //console.error('ID "companion-info"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    console.log("Companions Array on Update:", companions); // 배열 상태 로깅

    if (companions.length === 1) {
        companionInfoElement.textContent = companions[0].name; // 작성자 이름만 표시
    } else {
        const additionalCompanions = companions.length - 1;
        companionInfoElement.textContent = `${companions[0].name} 외 ${additionalCompanions}인`;
    }

    console.log(`Companion info updated: ${companionInfoElement.textContent}`);
}

// 함수 호출로 초기 상태 업데이트
updateCompanionInfo();

function removeCompanion(index) {
    if (index === 0) {
        console.warn("작성자는 삭제할 수 없습니다.");
        return;
    }

    const companion = companions[index];
    if (!companion) {
        console.error("Invalid companion index:", index);
        return;
    }
    
     // 이벤트 전파 중지 (팝업 닫기 방지)
    if (event) {
        event.stopPropagation();
    }

    // 동행인 배열에서 삭제
    companions.splice(index, 1);

    // 미동행인 배열로 추가
    nonCompanions.push(companion);

    console.log(`동행인 "${companion.name}" 삭제.`);

    // UI 업데이트
    renderCompanionList();
    updateCompanionInfo();
}

function initializeCompanionPopup() {
    console.log("Initializing companion popup...");

    const companionList = document.getElementById("user-list");
    const searchInput = document.getElementById("popup-search-input");

    if (!companionList) {
        console.error('ID "user-list"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    if (!searchInput) {
        console.error('ID "popup-search-input"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    console.log("Rendering companion list...");
    renderCompanionList();
}

function renderCompanionList() {
    console.log("Rendering companion list...");

    const companionList = document.getElementById("user-list");
    if (!companionList) {
        console.error('ID "user-list"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    companionList.innerHTML = ""; // 기존 리스트 초기화

    companions.forEach((user, index) => {
        const li = document.createElement("li");
        li.textContent = `${user.name} (${user.email})`; // 이름과 이메일 표시
        li.classList.add("companion-item");

        if (index !== 0) {
            // 작성자(0번 인덱스)를 제외하고 삭제 가능
            li.addEventListener("click", () => removeCompanion(index));
        } else {
            // 작성자는 클릭해도 아무 동작하지 않음
            li.style.cursor = "not-allowed";
        }

        companionList.appendChild(li);
    });

    console.log("Rendered companion list:", companionList.innerHTML);
}

function filterPopupMenu() {
    const keywordInput = document.getElementById("popup-search-input");
    if (!keywordInput) {
        console.error('ID "popup-search-input"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    const keyword = keywordInput.value.toLowerCase();
    const userList = document.getElementById("user-list");

    if (!userList) {
        console.error('ID "user-list"에 해당하는 요소를 찾을 수 없습니다.');
        return;
    }

    // 기존 리스트 초기화
    userList.innerHTML = "";

    // 검색 결과 필터링
    const filteredUsers = nonCompanions.filter(user =>
        user.name.toLowerCase().includes(keyword) || user.email.toLowerCase().includes(keyword)
    );
    
    // 검색 결과 추가
    if (filteredUsers.length > 0) {
        filteredUsers.forEach(user => {
            const li = document.createElement("li");
            li.textContent = `${user.name} (${user.email})`; // 이름과 이메일 표시
            li.classList.add("non-companion-item");

            li.addEventListener("click", (event) => {
                event.stopPropagation(); // 이벤트 전파 중지
                selectCompanion(user); // 동행인 선택 처리
            });

            userList.appendChild(li);
        });
    } else {
        const li = document.createElement("li");
        li.textContent = "검색 결과가 없습니다.";
        userList.appendChild(li);
    }

    // 입력창 초기화
    keywordInput.value = ""; // 입력창 비우기
}


function selectCompanion(user) {
    console.log(`사용자 선택됨: ${user.name} (${user.email})`);

    // 미동행인 배열에서 삭제
    const nonCompanionIndex = nonCompanions.findIndex(companion => companion.email === user.email);
    if (nonCompanionIndex > -1) {
        nonCompanions.splice(nonCompanionIndex, 1);
    } else {
        console.warn("선택된 사용자가 미동행인 배열에 존재하지 않습니다.");
    }

    // 동행인 배열에 추가
    companions.push(user);
    console.log("동행인 배열 업데이트:", companions);

    // UI 업데이트
    renderCompanionList();
    updateCompanionInfo();
}

document.addEventListener("DOMContentLoaded", () => {
    console.log("DOMContentLoaded triggered: Initializing the page...");

    // makePlan.jsp 로드 후 초기화
    loadJSP('makePlan.jsp', 'content-container', () => {
        console.log("makePlan.jsp loaded. Checking for required elements...");

        const companionInfoElement = document.getElementById("companion-info");
        const userListElement = document.getElementById("user-list");

        if (!companionInfoElement || !userListElement) {
            console.warn(
                "Required elements (companion-info or user-list) are missing after makePlan.jsp load. Initialization skipped."
            );
            return;
        }

        console.log("Required elements found. Proceeding with initialization...");

        // 초기화 함수 호출
        updateCompanionInfo();
        initializeCompanionPopup();
    });
});
