<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 검색 결과</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png">
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqNJI6gdzgK53NXv9JqHDeMXYPvZaQypU&libraries=places" async defer></script>
    <script>
        let map, placesService;

        // Google Maps API 초기화
       function initMap() {
            const defaultLocation = { lat: 37.5665, lng: 126.9780 }; // 서울 기본 위치
            map = new google.maps.Map(document.createElement("div")); // 지도 숨김
            placesService = new google.maps.places.PlacesService(map);
            console.log("Google Places API 초기화 완료");
        }

        // 장소 검색 함수
        function searchPlaces(keyword, page = 1) {
            if (!keyword) {
                alert("검색어를 입력하세요.");
                return;
            }

            console.log(`검색 키워드: ${keyword}`);
            
            const request = {
                query: keyword,
                fields: ["name", "place_id", "photos", "formatted_address", "rating", "user_ratings_total"],
            };

            placesService.textSearch(request, (results, status) => {
                console.log("Google Places API 호출 완료", { status, results });

                if (status === google.maps.places.PlacesServiceStatus.OK) {
                    if (results && results.length > 0) {
                        displaySearchResults(results, page);
                    } else {
                        console.warn("검색 결과는 있지만 결과 리스트가 비어 있음.");
                        document.getElementById("places-container").innerHTML = `<p>검색 결과가 없습니다.</p>`;
                    }
                } else {
                    console.error("Google Places API 호출 오류", status);
                    document.getElementById("places-container").innerHTML = `<p>검색 결과를 가져오는 중 오류가 발생했습니다.</p>`;
                }
            });
        }

        // 상위 3개의 검색 결과 표시
		function displaySearchResults(results) {
		    console.log("전체 검색 결과 확인: ", results);
		    if (!Array.isArray(results) || results.length === 0) {
		        console.error("검색 결과가 배열이 아니거나 비어 있습니다.");
		        return;
		    }
		
		    const placesContainer = document.getElementById("places-container");
		    placesContainer.innerHTML = ""; // 기존 내용 초기화
		
		    // 상위 3개의 결과만 렌더링
		    results.slice(0, 3).forEach((place) => {
		        const name = place.name ?? "이름 없음";
		        const formattedAddress = place.formatted_address ?? "주소 정보 없음";
		        const photoUrl =
		            place.photos && place.photos[0]
		                ? place.photos[0].getUrl({ maxWidth: 100, maxHeight: 70 })
		                : "img/defaultPlaceImage.jpg";
		        const rating = place.rating ?? "N/A";
		        const userRatingsTotal = place.user_ratings_total ?? 0;
		
		        const placeCard = document.createElement("div");
		        placeCard.classList.add("place-card");
		
			     // 링크 생성
			    const link = document.createElement("a");
				const hrefValue = "https://www.google.com/maps/place/?q=place_id:" + place.place_id;
				link.href = hrefValue;
				link.target = "_blank";
		
		     	// 이미지 생성
			    const image = document.createElement("img");
			    image.src = photoUrl;
			    image.alt = name;

			    // 제목 생성
			    const title = document.createElement("h3");
			    title.textContent = name;

			    // 링크에 이미지와 제목 추가
			    link.appendChild(image);
			    link.appendChild(title);

			    // 평점 및 리뷰 정보 생성
			    const ratingInfo = document.createElement("p");
			    ratingInfo.textContent = ""; // 초기화
			    ratingInfo.appendChild(document.createTextNode(rating));
			    ratingInfo.appendChild(document.createTextNode(" ★ | "));
			    ratingInfo.appendChild(document.createTextNode(userRatingsTotal + " 리뷰"));
		
		        // 주소 정보 생성
		        const addressInfo = document.createElement("p");
		        addressInfo.textContent = formattedAddress;
		
		        // 카드에 요소 추가
		        placeCard.appendChild(link);
		        placeCard.appendChild(ratingInfo);
		        placeCard.appendChild(addressInfo);
		
		        console.log("Generated placeCard:", placeCard);
		
		        placesContainer.appendChild(placeCard);
		    });
		
		    console.log("상위 3개의 검색 결과 렌더링 완료");
		}

        // 페이지 로드 후 초기화
         document.addEventListener("DOMContentLoaded", () => {
            initMap();

            // 검색 키워드 가져오기
            const searchKeyword = new URLSearchParams(window.location.search).get("keyword");
            document.getElementById("search-keyword").textContent = searchKeyword || "검색어 없음";

            // 장소 검색
            if (searchKeyword) {
                searchPlaces(searchKeyword);
            } else {
                console.warn("검색 키워드가 전달되지 않았습니다.");
            }
        });
    </script>
</head>
<body>
    <jsp:include page="header.jsp" />
    <%
        String keyword = request.getParameter("keyword");
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "검색어 없음";
        }
        request.setAttribute("keyword", keyword);
    %>

    <div class="results">
        <h3>'<span class="highlight" id="search-keyword"><%= keyword %></span>' 장소 검색 결과</h3>

        <div class="placeResults">
            <h2>장소</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">
            <div id="places-container"></div>
            <button class="more-results-btn" 
			        onclick="location.href='${pageContext.request.contextPath}/searchPlace.jsp?keyword=${keyword}'">
			    장소 검색결과 더보기
			</button>
        </div>

        <div class="planResults">
            <h2>일정</h2>
            <hr size="3" color="#194091" style="margin: 20px 0;">
            <button class="more-results-btn" 
			        onclick="location.href='${pageContext.request.contextPath}/searchPlan.jsp?keyword=${keyword}'">
			    일정 검색결과 더보기
			</button>
        </div>
    </div>
</body>
</html>