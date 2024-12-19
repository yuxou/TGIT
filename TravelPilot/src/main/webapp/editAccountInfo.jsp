<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: 계정 정보 변경</title>
    <script src="${pageContext.request.contextPath}/js/submitForm.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editAccountInfo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png" type="image/png">
</head>
<body>
    <jsp:include page="header.jsp" />

    <form id="editForm" method="post">
        <div class="editInfo-container">
            <h1>계정 정보 변경</h1>
            <div class="editInfo-section">
                <div class="edit-info">
                    <h2>아이디 변경</h2>
                    <div class="email-box">
                        <p><strong>이메일</strong>
                            <input type="text" name="email" placeholder="Email">
                        </p>
                    </div>
                    <div class="id-box">
                        <p><strong>아이디</strong>
                            <input type="text" name="newId" placeholder="새 아이디">
                            <button class="check-button" type="button" onclick="checkDuplicate()">중복 확인</button>
                        </p>
                    </div>
                    <div>
                        <button class="edit-button" type="button" onclick="submitForm('${pageContext.request.contextPath}/editProfile.jsp')">변경하기</button>
                    </div>
                    <hr>

                    <h2>비밀번호 변경</h2>
                    <div class="email-box">
                        <p><strong>이메일</strong>
                            <input type="text" name="emailForPassword" placeholder="Email">
                        </p>
                    </div>
                    <div class="email-box">
                        <p><strong>아이디</strong>
                            <input type="text" name="idForPassword" placeholder="ID">
                        </p>
                    </div>
                    <div class="email-box">
                        <p><strong>새 비밀번호</strong>
                            <input type="password" name="newPassword" placeholder="새 비밀번호">
                        </p>
                    </div>
                    <div class="email-box">
                        <p><strong>비밀번호 확인</strong>
                            <input type="password" name="passwordCheck" placeholder="비밀번호 확인">
                        </p>
                    </div>
                    <div>
                        <button class="edit-button" type="button" onclick="submitForm('${pageContext.request.contextPath}/editProfile.jsp')">변경하기</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
