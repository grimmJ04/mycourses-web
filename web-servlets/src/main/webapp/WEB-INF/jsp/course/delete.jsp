<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="model" scope="request" type="hu.szte.inf.models.Course"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Delete</title>

  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<%@ include file="../common/navbars.jsp" %>

<main class="main">
  <div>
    <form method="post" class="form" action="${pageContext.request.contextPath}/course/delete">
      <div class="form-group model-attributes">
        <label for="id">Id</label>
        <input id="id" name="id" value="${model.id}" type="number" readonly/>

        <label for="name">Name</label>
        <input id="name" name="name" value="${model.name}" type="text" readonly/>

        <label for="credit">Credit</label>
        <input id="credit" name="credit" value="${model.credit}" type="number" readonly/>

        <label for="semester">Semester</label>
        <input id="semester" name="semester" value="${model.semester.displayValue}" type="text" readonly/>

        <label for="grade">Grade</label>
        <input id="grade" name="grade" value="${model.grade != null ? model.grade.displayValue : model.grade}" type="text" readonly/>
      </div>
      <div class="form-group buttons">
        <button class="button" type="submit">Delete</button>
        <button class="button" onclick="history.back()" type="button">Back</button>
      </div>
    </form>
  </div>
</main>
</body>
</html>
