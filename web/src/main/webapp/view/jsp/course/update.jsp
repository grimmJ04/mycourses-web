<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="hu.szte.inf.models.Semester" %>
<%@ page import="hu.szte.inf.models.Grade" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update</title>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<%@ include file="../common/navbars.jsp" %>

<main class="main">
  <div>
    <form method="post" class="form" action="${pageContext.request.contextPath}/course/update">
      <div class="form-group model-attributes">
        <label for="id">Id</label>
        <input id="id" name="id" value="${model.id}" type="number" readonly/>

        <label for="name">Name</label>
        <input id="name" name="name" type="text" value="${model.name}"/>

        <label for="credit">Credit</label>
        <input id="credit" name="credit" type="number" step="1" min="0" max="999" value="${model.credit}"/>

        <label for="semester">Semester</label>
        <select id="semester" name="semester">
          <c:forEach var="opt" items="${Semester.values()}">
            <option value="${opt}">${opt.displayValue}</option>
          </c:forEach>
        </select>

        <label for="grade">Grade</label>
        <select id="grade" name="grade">
          <option value="${null}" ${model.grade == null ? 'selected="selected"' : ''}></option>
          <c:forEach var="opt" items="${Grade.values()}">
            <option value="${opt}" ${model.grade == opt ? 'selected="selected"' : ''}>${opt.displayValue}</option>
          </c:forEach>
        </select>
      </div>
      <div class="form-group buttons">
        <button class="button" type="submit">Update</button>
        <button class="button" onclick="history.back()" type="button">Back</button>
      </div>
    </form>
  </div>
</main>
</body>
</html>
