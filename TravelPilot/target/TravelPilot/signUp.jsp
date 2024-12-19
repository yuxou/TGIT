<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 회원가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signUp.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
	<script src="${pageContext.request.contextPath}/js/birthdate.js"></script>
	<script src="${pageContext.request.contextPath}/js/submitForm.js"></script>
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png">
</head>
<body>
    <jsp:include page="header.jsp" />
	<form id="editForm" method="post">
    <div class="signup-container">
        <h1>회원가입</h1>
        <div class="signup-section">
            <div class="signup-info">
                <div class="id-box">
                    <p><strong>아이디</strong>
                    <input type="text" name="id" placeholder="ID" required>
                    <button type="button" class="edit-button">중복 확인</button></p>
                </div>
                <div class="pw-box">
                    <p><strong>비밀번호</strong>
                    <input type="password" name="password" placeholder="Password" required>
                </div>
                <div class="pw-box">
                    <p><strong>비밀번호 확인</strong>
                    <input type="password" name="passwordCheck" placeholder="Password Check" required>
                </div>
                <div class="name-box">
                    <p><strong>이름</strong>
                    <input type="text" name="name" placeholder="Name" required></p>
                </div>
                <div>
                    <p><strong>생년월일</strong>
                    <select class="box" name="yy" id="year" required>
                        <option disabled selected>Year</option>
                    </select>
                    <select class="box" name="mm" id="month" required>
                        <option disabled selected>Month</option>
                    </select>
                    <select class="box" name="dd" id="day" required>
                        <option disabled selected>Day</option>
                    </select></p>
                </div>
                <div class="email-box">
                    <p><strong>이메일</strong>
                    <input type="text" name="emailLocal" placeholder="Email" required>&emsp;@
                    <select class="box" name="emailDomain" id="domain-list" required>
                        <option value="naver.com">naver.com</option>
                        <option value="gmail.com">gmail.com</option>
                        <option value="hanmail.net">hanmail.net</option>
                        <option value="nate.com">nate.com</option>
                    </select></p>
                </div>
                <button type="button" class="signup-button" onclick="submitForm('${pageContext.request.contextPath}/login.jsp')">Create Account</button>
                
            </div>
        </div>
    </div>
</form>

</body>
</html>
