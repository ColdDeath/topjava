<%@ taglib uri="http://example.com/functions" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .normal {color: green;}
        .exceeded {color: red;}
        table {
            width: 300px; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        td, th {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }
        th {
            background: #b0e0e6; /* Цвет фона */
        }
    </style>
</head>
<body>
<table class="tableC" border="1" b>
    <thead>
        <th>DateTime</th>
        <th>Description</th>
        <th>Calories</th>
    </thead>
    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
        <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
            <td>
                ${f:formatDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm:ss')}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
