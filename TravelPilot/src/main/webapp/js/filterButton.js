let isFutureActive = false;  // '예정된 계획' 버튼 활성화 상태
let isPastActive = false;    // '지난 계획' 버튼 활성화 상태

function bgGreen() {
    var future = document.getElementById("future");
    var past = document.getElementById("past");

    // '예정된 계획' 버튼이 이미 활성화된 상태에서 다시 클릭하면 '모두 보기' 상태로 전환
    if (isFutureActive) {
        future.style.backgroundColor = "#f0f0f0";  // 비활성화
        isFutureActive = false;
        updateFilter();  // 필터 초기화: 모두 보기
    } else {
        // '예정된 계획'만 보이게 필터링
        future.style.backgroundColor = "darkseagreen";
        past.style.backgroundColor = "#f0f0f0";  // '지난 계획' 비활성화
        isFutureActive = true;
        isPastActive = false;
        updateFilter();  // 예정된 계획만 필터링
    }
}

function bgBlue() {
    var future = document.getElementById("future");
    var past = document.getElementById("past");

    // '지난 계획' 버튼이 이미 활성화된 상태에서 다시 클릭하면 '모두 보기' 상태로 전환
    if (isPastActive) {
        past.style.backgroundColor = "#f0f0f0";  // 비활성화
        isPastActive = false;
        updateFilter();  // 필터 초기화: 모두 보기
    } else {
        // '지난 계획'만 보이게 필터링
        past.style.backgroundColor = "cornflowerblue";
        future.style.backgroundColor = "#f0f0f0";  // '예정된 계획' 비활성화
        isPastActive = true;
        isFutureActive = false;
        updateFilter();  // 지난 계획만 필터링
    }
}

function updateFilter() {
    var rows = document.querySelectorAll("table tbody tr");
    rows.forEach(function(row) {
        var circle = row.querySelector("div");

        // 필터링 상태에 따른 표시 여부 설정
        if (isFutureActive && circle.classList.contains("future-circle")) {
            row.style.display = "";  // '예정된 계획'만 표시
        } else if (isPastActive && circle.classList.contains("past-circle")) {
            row.style.display = "";  // '지난 계획'만 표시
        } else if (!isFutureActive && !isPastActive) {
            row.style.display = "";  // 아무 필터가 활성화되지 않은 상태에서 모든 데이터 표시
        } else {
            row.style.display = "none";  // 해당 조건에 맞지 않으면 숨기기
        }
    });
}
