<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 회원정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/submitForm.js"></script>
    <script src="<%= request.getContextPath() %>/js/birthdate.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/editProfile.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/header.css">
    <link rel="icon" href="<%= request.getContextPath() %>/img/favicon.png" type="image/png">
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <form id="editForm" method="post">
        <div class="editProfile-container">
            <h1>회원정보 수정</h1>
            <div class="editProfile-section">
                <div class="profile-info">
                    <div class="idpw-box">
                        <p><strong>아이디</strong>
                        <input type="text" name="newId" placeholder="dongduk12">
                        <button class="edit-button" type="button" onclick="submitForm('${pageContext.request.contextPath}/editAccountInfo.jsp')">변경하기</button></p>
                    </div>
                    
                    <div class="idpw-box">
                        <p><strong>비밀번호</strong>
                        <input type="password" name="password" placeholder="비밀번호">
                        <button class="edit-button" type="button" onclick="submitForm('${pageContext.request.contextPath}/editAccountInfo.jsp')">변경하기</button></p>
                    </div>
                    
                    <div class="name-box">
                        <p><strong>이름</strong>
                        <input type="text" name="name" placeholder="홍길동" required></p>
                    </div>
                    
                    <div>
                        <p><strong>생년월일</strong>
                        <select class="box" name="yy" id="year" required>
                            <option disabled selected>출생 연도</option>
                        </select>
                        <select class="box" name="mm" id="month" required>
                            <option disabled selected>월</option>
                        </select>
                        <select class="box" name="dd" id="day" required>
                            <option disabled selected>일</option>
                        </select></p>
                    </div>
                    
                    <div class="email-box">
                        <p><strong>이메일</strong>
                        <input type="text" name="emailLocal" placeholder="example" required>&emsp;@
                        <select class="box" name="emailDomain" id="domain-list" required>
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="nate.com">nate.com</option>
                        </select></p>
                    </div>
                    
                    <button class="cancel-button" type="button" onclick="location.href='${pageContext.request.contextPath}/myPage.jsp'">취소</button>
                    <button class="ok-button" type="button" onclick="submitForm('${pageContext.request.contextPath}/myPage.jsp')">확인</button>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
