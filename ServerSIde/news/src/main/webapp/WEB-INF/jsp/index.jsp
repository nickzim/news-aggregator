
<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=windows-1251"
             pageEncoding="windows-1251"%>
    <title>Новостной агрегатор</title>
</head>
<body>

    <jsp:scriptlet>
        String redirectURL = "http://localhost:8080/feeds";
        response.sendRedirect(redirectURL);
    </jsp:scriptlet>

</body>

</html>