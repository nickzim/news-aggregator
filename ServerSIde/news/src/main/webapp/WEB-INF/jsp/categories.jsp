<%@ page import="java.net.URLEncoder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE HTML>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
    <title>Категории</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>

    <style>
        .rectangle {
            counter-reset: li;
            list-style: none;
            font: 14px "Trebuchet MS", "Lucida Sans";
            padding: 0;
            text-shadow: 0 1px 0 rgba(255,255,255,.5);
        }
        .rectangle a {
            position: relative;
            display: block;
            padding: .4em .4em .4em .8em;
            margin: .5em 0 .5em 2.5em;
            background: #D3D4DA;
            color: #444;
            text-decoration: none;
            transition: all .3s ease-out;
        }
        .rectangle a:hover {background: #DCDDE1;}
        .rectangle a:before {
            content: counter(li);
            counter-increment: li;
            position: absolute;
            left: -2.5em;
            top: 50%;
            margin-top: -1em;
            background: #9097A2;
            height: 2em;
            width: 2em;
            line-height: 2em;
            text-align: center;
            font-weight: bold;
        }
        .rectangle a:after {
            position: absolute;
            content: "";
            border: .5em solid transparent;
            left: -1em;
            top: 50%;
            margin-top: -.5em;
            transition: all .3s ease-out;
        }
        .rectangle a:hover:after {
            left: -.5em;
            border-left-color: #9097A2;
        }
    </style>
</head>
<body>
<c:forEach  items="${categories}" var ="category">
    <ol class="rectangle">
        <li><a href="${pageContext.request.contextPath}/news?feedUrl=<%=request.getParameter("feedUrl") %>&category=${category.nameUTF8}">${category.nameCP1251}   ${category.count}</a></li>
    </ol>
</c:forEach>
</body>

</html>