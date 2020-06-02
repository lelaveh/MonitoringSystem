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
    <title>Checkpoint list</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css"/>

</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>Monitoring system - Checkpoints</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <button type="submit" onclick="window.location.href='addCheckpointForm'; return false;" class="add-button">Add checkpoint</button>
        <table>
            <tr>
                <th>Id</th>
                <th>Location</th>
                <th>Entry camera id</th>
                <th>Exit camera id</th>
                <th>Action</th>
            </tr>
            <c:forEach var="tempCheckpoint" items="${checkpoints}">
                <c:url var="updateLink" value="/monitoringSystem/checkpoints/updateCheckpointForm" >
                <c:param name="checkpointId" value="${tempCheckpoint.id}"/>
            </c:url>
                <c:url var="deleteLink" value="/monitoringSystem/checkpoints/deleteCheckpoint">
                    <c:param name="checkpointId" value="${tempCheckpoint.id}"/>
                </c:url>
                <tr>
                    <td>${tempCheckpoint.id}</td>
                    <td>${tempCheckpoint.location}</td>
                    <td>${tempCheckpoint.entryCamera.id}</td>
                    <td>${tempCheckpoint.exitCamera.id}</td>
<%--                    <td>${tempCar.email}</td>--%>
                    <td>
                        <a href="${updateLink}">Update</a>
                        <a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this checkpoint?'))) return false">Delete</a>
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
