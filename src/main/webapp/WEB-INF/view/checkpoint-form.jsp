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
    <title>Save checkpoint</title>
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
        <h2> Checkpoint relationship manager </h2>
    </div>
</div>

<div id="container">
    <h3>Save checkpoint</h3>
    <i>Fields marked with asterisk(*) should not be empty</i>
    <form:form action="saveCheckpoint" modelAttribute="checkpoint"  method="post">
        <input type="hidden" name="entryCameraWasPresent" value="${entryCameraWasPresent}"/>
        <input type="hidden" name="entryCameraId" value="${entryCameraId}"/>
        <input type="hidden" name="exitCameraWasPresent" value="${exitCameraWasPresent}"/>
        <input type="hidden" name="exitCameraId" value="${exitCameraId}"/>
        <form:hidden path="id"/>
        <td><label>Location (*):</label></td>
        <form:input path="location"/>
        <form:errors path="location" cssClass="error"/>
<%--        <form:errors path="power" cssClass="error"/>--%>
        <br/>
        <td><label>Entry camera id :</label></td>
        <form:select path="entryCamera">
            <form:option value="0" label="Select camera"/>
            <form:options items="${availableCameras}" itemLabel="id" />
        </form:select>
        <td><label>Exit camera id :</label></td>
        <form:select path="exitCamera" >
            <form:option value="0" label="Select camera"/>
            <form:options items="${availableCameras}"  itemLabel="id" />
        </form:select>
        <form:errors path="exitCamera" cssClass="error"/>
        <br/> <br/>
        <button type="submit"> Save </button>
    </form:form>
    <a href="/monitoringSystem/checkpoints/">Return to the main page</a>
</div>

</body>
</html>
