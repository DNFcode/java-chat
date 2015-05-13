<%--
  Created by IntelliJ IDEA.
  User: dnf
  Date: 12.05.15
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title></title>
      <script type="text/javascript" src="static/jquery-2.1.4.js"></script>
      <script type="text/javascript" src="static/chat.js"></script>
  </head>
  <body>
        <form method="post" action="/hello">
            <input type="text" name="login"/>
            <input type="password" name="password"/>
            <input type="submit" value="FORM"/>
        </form>
        <input id="a" type="button" onclick="" value="AJAX"/>
  </body>
</html>