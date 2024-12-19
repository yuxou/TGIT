<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<%=request.getContextPath()%>/img/favicon.png" type="image/png">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/placeDetail.css">
    <title>TravelPilot</title>
    <script>
        // header.jsp 파일을 불러와서 삽입
        function loadHeader() {
            fetch('<%=request.getContextPath()%>/header.jsp')
                .then(response => response.text())
                .then(data => {
                    document.getElementById('header-container').innerHTML = data;
                });
        }

        document.addEventListener('DOMContentLoaded', () => {
            const starLabels = document.querySelectorAll('.star-rating .star-label');
            let stars = document.querySelectorAll('.star-rating .star-icon');

            // 별점 초기화 및 선택된 별 점수 채우기
            function checkedRate() {
                initStars();
                const checkedRadio = document.querySelector('.star-input:checked');
                
                if (checkedRadio) {
                    const index = Array.from(starLabels).indexOf(checkedRadio.parentElement);
                    console.log(`Checked index: ${index}`); // 디버깅: 선택된 인덱스 확인
                    filledRate(index);
                }
            }

            // 별 채우기
            function filledRate(index) {
                for (let i = 0; i <= index; i++) {
                    stars[i].classList.add('filled');
                    console.log(`Filled star at index: ${i}`); // 디버깅: 클래스 추가 확인
                }
            }

            // 별점 초기화 (비활성화)
            function initStars() {
                stars.forEach((star, idx) => {
                    star.classList.remove('filled');
                    console.log(`Removed filled class from star at index: ${idx}`); // 디버깅: 초기화 확인
                });
            }

            // 별에 대한 클릭 이벤트
            starLabels.forEach((label, index) => {
                label.addEventListener('click', () => {
                    checkedRate();
                    filledRate(index);
                });
            });
        });

        // DOM이 로드된 후에 header를 불러오기
        window.onload = function() {
            loadHeader();

            document.querySelectorAll('.review-section button').forEach(button => {
            button.addEventListener('click', function() {
                if (this.classList.contains('selected')) {
                this.classList.remove('selected'); // 이미 활성화된 버튼을 다시 클릭 시 비활성화
            } else {
                document.querySelectorAll('.review-section button').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected'); // 새로운 버튼을 활성화
            }
            });
        });
        };
    </script>
</head>
<body>
    <div id="header-container"></div>

    <div class="container">
        <!-- 가게 정보 섹션 -->
        <h1>이치란 본점 <span class="subtitle">후쿠오카 | 음식점</span></h1>
        <p class="place-description">
            돼지 사골 국물로 만든 돈코츠 라멘을 전문으로 하는 편안한 분위기의 레스토랑입니다.
        </p>
        <img src="<%=request.getContextPath()%>/img/search_image1.png" alt="가게 이미지" class="image">

        <!-- 지도 및 정보 섹션 -->
        <div class="map-info">
            <h3 class="section-title">정보</h3>
            <div class="map-container">
                <img src="<%=request.getContextPath()%>/img/mapSample.png" alt="지도 이미지" class="map-image">
                <div class="info">
                    <div class="info-item">
                        <span class="icon">📍</span>5 Chome-3-2 Nakasu, Hakata Ward, Fukuoka, 810-0801 일본
                    </div>
                    <div class="info-item">
                        <span class="icon">📞</span>+81 50-3733-2600
                    </div>
                    <div class="info-item">
                        <span class="icon">🕒</span>24시간 영업
                    </div>
                </div>
            </div>
        </div>

        <!-- 리뷰 섹션 -->
        <div class="review-section">
            <h3 class="section-title">리뷰</h3>
            <div class="rating"><h2>4.2 ★★★★☆</div>
            <button>최신순</button>
            <button>인기순</button>
            <button>별점 높은 순</button>
            <button>별점 낮은 순</button>
            
            <!-- 샘플 리뷰 -->
            <div class="review">
                <div class="user-info">
                    <img src="<%=request.getContextPath()%>/img/user.png" alt="유저 이미지" width="40">
                    <span class="username">사용자1</span>
                    <span class="time-elapsed">2시간 전</span>
                </div>
                <div class="rating">★★★★☆</div>
                <p>명불허전 이치란 라멘 맛집! 직원들이 친절하게 안내해 주고, 음식도 빠르게 나와서 좋았어요.</p>
            </div>
            
            <!-- 리뷰 작성 폼 -->
            <div class="review-form">
                <div class="star-rating">
                    <label class="star-label star-label--half" for="star0.5">
                        <input type="radio" id="star0.5" class="star-input" name="rating" value="0.5">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--full" for="star1">
                        <input type="radio" id="star1" class="star-input" name="rating" value="1">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--half" for="star1.5">
                        <input type="radio" id="star1.5" class="star-input" name="rating" value="1.5">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--full" for="star2">
                        <input type="radio" id="star2" class="star-input" name="rating" value="2">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--half" for="star2.5">
                        <input type="radio" id="star2.5" class="star-input" name="rating" value="2.5">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--full" for="star3">
                        <input type="radio" id="star3" class="star-input" name="rating" value="3">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--half" for="star3.5">
                        <input type="radio" id="star3.5" class="star-input" name="rating" value="3.5">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--full" for="star4">
                        <input type="radio" id="star4" class="star-input" name="rating" value="4">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--half" for="star4.5">
                        <input type="radio" id="star4.5" class="star-input" name="rating" value="4.5">
                        <span class="star-icon"></span>
                    </label>
                    <label class="star-label star-label--full" for="star5">
                        <input type="radio" id="star5" class="star-input" name="rating" value="5">
                        <span class="star-icon"></span>
                    </label>
                </div>
                <br>
                <textarea rows="5" placeholder="이용한 경험을 작성해주세요!"></textarea> <br>
                <input type="submit" value="리뷰 작성">
            </div>
    </div>
</body>
</html>
