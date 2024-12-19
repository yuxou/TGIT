let map;
let markers = []; // 마커를 저장할 배열

// 구글맵 초기화
function initMap() {
    const center = { lat: 37.5665, lng: 126.9780 }; // 기본 중심 좌표 (서울)
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 10,
        center: center,
    });
}

// 장소 선택
function selectPlace(place, dayIndex) {
    if (!place.place_id) {
        console.error("place_id is missing for the selected place:", place);
        return;
    }

    const placeName = place.name; // 장소 이름
    const placeId = place.place_id; // place_id를 가져오기

    // PlacesService 인스턴스 생성
    const service = new google.maps.places.PlacesService(document.createElement("div"));

    // 장소 세부정보 요청
    service.getDetails(
        { placeId: placeId, fields: ["formatted_address", "geometry"] },
        (details, status) => {
            if (status === google.maps.places.PlacesServiceStatus.OK && details) {
                const fullAddress = details.formatted_address; // 전체 주소
                const location = details.geometry.location; // 위치 정보

                // 지도 중심 및 줌 변경
                map.setCenter(location);
                map.setZoom(15);

                // 마커 추가
                addMarker(location, placeName);

                // 선택된 장소를 리스트에 추가
                console.log(`Calling addPlace with dayIndex: ${dayIndex}`);
                addPlace(dayIndex, placeName, fullAddress);

                console.log(`Selected place: ${placeName}, Full Address: ${fullAddress}`);
            } else {
                console.error("Failed to fetch place details:", status);
            }
        }
    );
}

// 장소 검색 및 결과 표시
function searchPlaces(inputFieldId, dayIndex) {
    console.log(`searchPlaces called with inputFieldId: ${inputFieldId}, dayIndex: ${dayIndex}`);

    const inputField = document.getElementById(inputFieldId);
    if (!inputField) {
        console.error(`Input field with ID "${inputFieldId}" not found.`);
        return;
    }

    const query = inputField.value.trim();
    if (!query) {
        alert("장소 이름을 입력하세요.");
        return;
    }

    const resultContainerId = inputFieldId.replace("popup-location-place", "search-results");
    const resultContainer = document.getElementById(resultContainerId);
    if (!resultContainer) {
        console.error(`Search results container with ID "${resultContainerId}" not found.`);
        return;
    }

    console.log(`Searching for: ${query}, Results container ID: ${resultContainerId}, DayIndex: ${dayIndex}`);

    const service = new google.maps.places.PlacesService(document.createElement("div"));
    service.textSearch({ query }, (results, status) => {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            displaySearchResults(resultContainer, results, dayIndex);
        } else {
            resultContainer.innerHTML = "<p>검색 결과가 없습니다.</p>";
        }
    });
}

function displaySearchResults(resultsContainer, results, dayIndex) {
    console.log(`displaySearchResults called with dayIndex: ${dayIndex}`);

    if (!resultsContainer) {
        console.error("Results container not found.");
        return;
    }

    resultsContainer.innerHTML = ""; // 기존 결과 초기화

    // 모든 검색 결과 표시
    results.forEach((place) => {
        const resultItem = document.createElement("div");
        resultItem.className = "result-item";

        const placeName = document.createElement("div");
        placeName.className = "place-name";
        placeName.textContent = place.name;

        const placeAddress = document.createElement("div");
        placeAddress.className = "place-address";
        placeAddress.textContent = place.formatted_address || "주소 정보 없음";

        resultItem.appendChild(placeName);
        resultItem.appendChild(placeAddress);

        resultItem.addEventListener("click", () => {
            console.log(`Result item clicked. dayIndex: ${dayIndex}, place: ${place.name}`);
            selectPlace(place, dayIndex);
        });
        resultsContainer.appendChild(resultItem);
    });

    // 스크롤 가능하도록 스타일 적용
    resultsContainer.style.maxHeight = "200px";
    resultsContainer.style.overflowY = "auto";

    // 디버깅 코드
    console.log("Displayed search results:", results);
}

// 마커 추가
function addMarker(location, title) {
    // 기존 마커 제거
    markers.forEach((marker) => marker.setMap(null));
    markers = [];

    // 새로운 마커 추가
    const marker = new google.maps.Marker({
        position: location,
        map: map,
        title: title,
    });

    markers.push(marker);
    map.panTo(location); // 지도 중심 이동
}

function addPlace(dayIndex, placeName, address) {
    // 방문 장소 리스트 가져오기
    const placeList = document.getElementById(`place-list-${dayIndex}`);
    if (!placeList) {
        console.error(`Element with ID "place-list-${dayIndex}" not found.`);
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
            <div class="details">${address}</div>
        </div>
    `;

    // 새 항목을 추가 버튼 위에 삽입
    placeList.insertBefore(placeItem, addLocationButton);
    console.log(`Place added to dayIndex: ${dayIndex}, Place Name: ${placeName}`);

    // 디버깅 코드
    console.log("Adding place:", placeName, address);
    console.log("New place index:", newIndex);

    // 팝업 닫기
    const popupMenu = document.getElementById(`popup-menu-location-${dayIndex}`);
    if (popupMenu) {
        popupMenu.classList.remove("show");
    }
    console.log(`Popup with ID "popup-menu-location-${dayIndex}" closed.`);
}

// 팝업 메뉴 닫기
function closePopup(popupId) {
    const popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = "none";
    }
}