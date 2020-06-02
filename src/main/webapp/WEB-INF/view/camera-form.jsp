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
        <h2> Camera relationship manager </h2>
    </div>
</div>

<div id="container">
    <h3>Save camera</h3>
    <i>Fields marked with asterisk(*) should not be empty</i>
    <form:form action="saveCamera" modelAttribute="camera" method="post">
        <form:hidden path="id"/>
        <form:hidden path="checkpointId"/>
        <td><label>Power (*):</label></td>
        On<td><form:radiobutton path="power" name="on" value="true"/></td>
        Off<td><form:radiobutton path="power" name="off" value="false"/></td>
        <br/> <br/>
        <button type="submit"> Save </button>
    </form:form>
    <a href="/monitoringSystem/cameras/">Return to the main page</a>
</div>

</body>
</html>
