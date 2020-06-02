<%--
  Created by IntelliJ IDEA.
  User: Амир
  Date: 07.04.2020
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Save car</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-car-style.css">
    <style>
        .error {color:red}
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2> Monitoring system </h2>
    </div>
</div>

<div id="container">
    <h3>Monitoring system - home page</h3>
    <a href="cars/">Cars management page</a>
    <br><br/>
    <a href="cameras/">Security cameras management page</a>
    <br><br/>
    <a href="checkpoints/">Checkpoints management page</a>
</div>

</body>
</html>
