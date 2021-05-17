<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 17.05.2021
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome page</title>
</head>
<body>
<div>
    <form name="username" action="/ModJDBCWeb/dispatcher?action=mainPage" method="POST">
        Name: <input type="text" value="" name="username" placeholder="Enter name"/>
        <input type="submit" value="Enter" />
    </form>
</div>
</body>
</html>
