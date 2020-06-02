<%--
  Created by IntelliJ IDEA.
  User: Амир
  Date: 07.04.2020
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2> Car relationship manager </h2>
    </div>
</div>

<div id="container">
    <h3>Save car</h3>
    <i>Fields marked with asterisk(*) should not be empty</i>
    <form:form action="saveCar" modelAttribute="car" method="post">
        <input type="hidden" name="isUpdate" value="${isUpdate}"/>
        <form:hidden path="id"/>
        <form:hidden path="currentLocation"/>
        <td><label>License plate (*):</label></td>
        <td><form:input path="regNum"/></td>
        <form:errors path="regNum" cssClass="error"/>
        <br/>
        <td><label>Weight (*):</label></td>
        <td><form:input path="weight"/></td>
        <form:errors path="weight" cssClass="error"/>
        <br/>
        <td><label>Destination:     </label></td>
        <form:select path="destinationId">
            <form:option value="0" label="Choose checkpoint id"/>
            <form:options items="${checkpoints}"  itemLabel="id" itemValue="id" />
        </form:select>
        <td><label> Magnetic card:  </label></td>
        <form:input path="magneticCard"/>
        <form:errors path="magneticCard" cssClass="error"/>
        <br/> <br/>

        <button type="submit"> Save </button>
    </form:form>
    <a href="/monitoringSystem/cars/">Return to the main page</a>
</div>

</body>
</html>
