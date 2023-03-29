<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Read</title>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<%@ include file="../common/navbars.jsp" %>

<main class="main">
  <div>
    <a class="anchor-button" href="${pageContext.request.contextPath}/course/create">Create New</a>
  </div>
  <c:choose>
    <c:when test="${empty models}">
      <p>No records to display.</p>
    </c:when>
    <c:otherwise>
      <table class="table">
        <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Credit</th>
          <th>Semester</th>
          <th>Grade</th>
          <th><!-- HANDLES --></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="model" items="${models}">
          <tr>
            <td>${model.id}</td>
            <td>${model.name}</td>
            <td>${model.credit}</td>
            <td>${model.semester.getDisplayValue()}</td>
            <td>${model.grade != null ? model.grade.getDisplayValue() : ''}</td>
            <td>
              <a class="anchor-button" href="${pageContext.request.contextPath}/course/update?id=${model.id}">Edit</a>
              <a class="anchor-button" href="${pageContext.request.contextPath}/course/delete?id=${model.id}">Delete</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</main>
</body>
</html>
