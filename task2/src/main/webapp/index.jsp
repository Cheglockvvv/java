<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Online shop</title>
</head>
<body>
<h2>Welcome to online shop!</h2>
<form action="/task2/data" method="GET">
    <label>
        <input type="text" name="username" pattern="^\w+$" placeholder="Enter your username">
    </label>
    <input type="submit" value="Enter">
</form>
</body>
</html>
