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
    <title>Camera list</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Monitoring system - Cameras</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <button type="submit" onclick="window.location.href='addCameraForm'; return false;" class="add-button">Add camera</button>
        <table>
            <tr>
                <th>Id</th>
                <th>Power</th>
                <th>Checkpoint id</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempCamera" items="${cameras}">
                <c:url var="updateLink" value="/monitoringSystem/cameras/updateCameraForm" >
                    <c:param name="cameraId" value="${tempCamera.id}"/>
                </c:url>
                <c:url var="deleteLink" value="/monitoringSystem/cameras/deleteCamera">
                    <c:param name="cameraId" value="${tempCamera.id}"/>
                </c:url>
                <tr>
                    <td>${tempCamera.id}</td>
                    <td>${tempCamera.checkPower()}</td>
                    <td>${tempCamera.checkpointId}</td>
                    <td>
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this camera?'))) return false">Delete</a>
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
