<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 마이페이지</title>
    <script src="${pageContext.request.contextPath}/js/filterButton.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/myPage.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
</head>
<body>
    <jsp:include page="header.jsp" />

    <%
        // 샘플 회원 정보
        String userId = "dongduk12";
        String userName = "김동덕";
        String birthDate = "2000-01-23";
        String email = "dongduk12@gmail.com";

        // 샘플 계획 데이터
        List<Map<String, String>> travelPlans = new ArrayList<>();
        Map<String, String> plan1 = new HashMap<>();
        plan1.put("type", "future");
        plan1.put("title", "유럽 여행");
        plan1.put("country", "파리 외 3곳");
        plan1.put("people", "김동덕 외 1인");
        plan1.put("dates", "2024-10-16 ~ 2024-10-30");
        plan1.put("cost", "3,500,000원");
        travelPlans.add(plan1);

        Map<String, String> plan2 = new HashMap<>();
        plan2.put("type", "past");
        plan2.put("title", "도쿄 여행");
        plan2.put("country", "일본");
        plan2.put("people", "김동덕");
        plan2.put("dates", "2024-6-10 ~ 2024-6-14");
        plan2.put("cost", "1,200,000원");
        travelPlans.add(plan2);

        // 데이터를 request 객체에 저장
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
        request.setAttribute("birthDate", birthDate);
        request.setAttribute("email", email);
        request.setAttribute("travelPlans", travelPlans);
    %>

    <div class="mypage-container">
        <h1>마이페이지</h1>
        <div class="profile-section">
            <div class="profile-image">
                <img src="<%= request.getContextPath() %>/img/user.png" alt="User Profile Image">
                <button class="edit-button" onClick="location.href='editProfile.jsp'">회원정보 수정</button>
            </div>
            <div>
                <p>&emsp;&emsp;&emsp;</p>
            </div>
            <div class="profile-info">
                <p><strong>아이디</strong> | <%= request.getAttribute("userId") %></p>
                <p><strong>이름</strong> | <%= request.getAttribute("userName") %></p>
                <p><strong>생년월일</strong> | <%= request.getAttribute("birthDate") %></p>
                <p><strong>이메일</strong> | <%= request.getAttribute("email") %></p>
            </div>
        </div>

        <h2>나의 계획</h2>
        <div class="filter-section">
            <p>Filter</p>&nbsp;&nbsp;
            <button id="future" onClick="bgGreen(); filterPlans('future')">예정된 계획</button>
            <button id="past" onClick="bgBlue(); filterPlans('past')">지난 계획</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>제목</th>
                    <th>국가</th>
                    <th>작성자</th>
                    <th>여행 날짜</th>
                    <th>경비</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Map<String, String>> plans = (List<Map<String, String>>) request.getAttribute("travelPlans");
                    if (plans != null) {
                        for (Map<String, String> plan : plans) {
                            String type = plan.get("type");
                            String circleClass = "future".equals(type) ? "future-circle" : "past-circle";
                %>
                <tr>
                    <td><div class="<%= circleClass %>"></div> <%= plan.get("title") %></td>
                    <td><%= plan.get("country") %></td>
                    <td><%= plan.get("people") %></td>
                    <td><%= plan.get("dates") %></td>
                    <td><%= plan.get("cost") %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">등록된 여행 계획이 없습니다.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
