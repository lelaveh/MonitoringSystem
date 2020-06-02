<%--
  Created by IntelliJ IDEA.
  User: Амир
  Date: 07.04.2020
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Car list</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Monitoring system - Cars</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <button type="submit" onclick="window.location.href='addCarForm'; return false;" class="add-button">Add car</button>
        <table>
            <tr>
                <th>Id</th>
                <th>License plate</th>
                <th>Weight</th>
                <th>Destination id</th>
                <th>Current location</th>
                <th>Magnetic card</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempCar" items="${cars}">
                <c:url var="updateLink" value="/monitoringSystem/cars/updateCarForm" >
                    <c:param name="carId" value="${tempCar.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/monitoringSystem/cars/deleteCar">
                    <c:param name="carId" value="${tempCar.id}"/>
                </c:url>
                <c:url var="startLink" value="/monitoringSystem/application/start">
                    <c:param name="carId" value="${tempCar.id}"/>
                </c:url>
                <tr>
                    <td>${tempCar.id}</td>
                    <td>${tempCar.regNum}</td>
                    <td>${tempCar.weight}</td>
                    <td>${tempCar.destinationId}</td>
                    <td>${tempCar.currentLocation}</td>
                    <td>${tempCar.magneticCard}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this car?'))) return false">Delete</a>
                        <a href="${startLink}" onclick="if (${tempCar.destinationId == 0}) { alert('Destination id for the car is not defined');   return false} ">Start</a>
                    </td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/monitoringSystem/">Return to the main page</a>
    </div>
</div>
</body>
</html>
