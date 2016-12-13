<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit / Create</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Edit / Create</h2>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
    <input type="hidden" value="${meal.id}" name="id">
    <dl>
        <dt>DateTime</dt>
        <dd>
            <input type="datetime-local" value="${meal.dateTime}", name="dateTime">
        </dd>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd>
            <input type="text" value="${meal.description}", name="description">
        </dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd>
            <input type="number" value="${meal.calories}", name="calories">
        </dd>
    </dl>
    <dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </dl>

</form>
</body>
</html>
