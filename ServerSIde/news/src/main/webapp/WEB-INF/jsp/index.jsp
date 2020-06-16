
<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=windows-1251"
             pageEncoding="windows-1251"%>
    <title>Новостной агрегатор</title>
</head>
<body>
<%
    String redirectURL = "http://localhost:8080/feeds";
    response.sendRedirect(redirectURL);
%>
</body>

</html>