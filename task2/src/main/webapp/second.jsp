<%@ page import="com.vorobey.entity.ItemEntity" %><%--
  Created by IntelliJ IDEA.
  User: cheglockvvv
  Date: 25.03.24
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello <c:out value="${username}"/> </h2>
<form action="final.jsp" method="POST" style="width:200px">
    <h4>Make your order</h4>
    <label>
        <select style="width: 200px" name="goods" multiple>
            <c:forEach var="item" items="${entityList}">
                <option value="${item.id}">${item.name} ${item.cost}$</option>
            </c:forEach>
        </select>
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
