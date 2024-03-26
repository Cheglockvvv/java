<%--
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
<h2>Hello <%=request.getParameter("username")%></h2>
<form action="final.jsp" method="POST">
    <h4>Make your order</h4>
    <label>
        <select name="goods" multiple>
            <option value="first"></option>
            <option value="second">second</option>
        </select>
    </label>
    <input type="submit" value="Submit">
</form>
</body>
</html>
