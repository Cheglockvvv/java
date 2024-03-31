<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Receipt</title>
</head>
<body>
    <h2>Dear <c:out value="${sessionScope.username}"/>, your order:</h2>
    <ol>
        <c:forEach var="item" items="${requestScope.chosen}">
            <li>${item.name} ${item.cost}$</li>
        </c:forEach>
    </ol>
    <p>
        Total: <c:out value="${requestScope.total}$"/>
    </p>
</body>
</html>
