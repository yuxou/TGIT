<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelPilot: Login</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
    <link rel="icon" href="<%=request.getContextPath()%>/img/favicon.png" type="image/png">
</head>
<body>
    <div class="login-container">
        <a href="<%=request.getContextPath()%>/home.jsp">
            <img class="logo" src="<%=request.getContextPath()%>/img/logo.png" alt="로고">
        </a>
        <form action="<%=request.getContextPath()%>/login" method="POST" class="login-form">
            <input type="text" placeholder="ID" name="id" required>
            <input type="password" placeholder="Password" name="password" required>
            <button type="submit">Sign In</button>
            <a href="<%=request.getContextPath()%>/signUp.jsp" class="create-account">Create Account</a>
        </form>
    </div>
</body>
</html>