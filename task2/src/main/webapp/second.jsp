<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello <c:out value="${param.username}"/>!</h2>
<form action="/task2/receipt" method="POST">
    <h4>Make your order</h4>
    <label>
        <select style="width: 200px" name="goods" multiple>
            <c:forEach var="item" items="${requestScope.entityList}">
                <option value="${item.id}">${item.name} ${item.cost}$</option>
            </c:forEach>
        </select>
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
