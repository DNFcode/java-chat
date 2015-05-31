<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: dnf
  Date: 12.05.15
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="static/chat.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <meta charset="UTF-8">
    <title></title>
    <style>
        body {
            background-color: #CFD8DC; /* Цвет фона веб-страницы */
            margin-top: 40px;
        }
    </style>
</head>
<body>
    <div id="startDate" style="visibility: hidden"><%=(new Date((new Date()).getTime()-6*60*60*1000)).getTime()%></div>

    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <!-- Bual -->
                <% if (request.getSession().getAttribute("user") == null){%>
                    <button type="button" id="login-btn" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-sm">
                        <span>Log in</span>
                    </button>
                <%} else {%>
                    <div id="username">Hi, <%=(String)request.getSession().getAttribute("user")%></div>
                    <form method="get" action="/logout">
                        <button type="submit" id="logout-btn" class="btn btn-default">
                            <span>Log out</span>
                        </button>
                    </form>
                <% } %>
            </div>

            <div class="col-sm-3">
                <div class="center-text"><h4>Пользователи</h4></div>
                <div id="users-container">
                    <div id="online-switch" class="switch online-switch-on">Online</div>
                    <div id="all-switch" class="switch all-switch-off">All</div>
                    <div id="users-list" class="users-list-online">
                    </div>
                </div>
            </div>

            <div class="col-sm-9">
                <div id="messages">

                </div>
                <div class="col-sm-12">
                    <%--<input id="message-input" type="text" class="form-control" name="message" placeholder="Введите сообщение">--%>
                    <input id="message-input" type="text" class="form-control" name="message-input">
                    <button id="send-button" class="btn btn-default">Send</button>
                </div>
            </div>


        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <div class="center-text"><h4 class="modal-title" id="myModalLabel">Авторизация</h4></div>
                </div>
                <div class="modal-body">
                    <form method="post" action="/login">
                        <div class="center-text"><h5>Логин:</h5></div><input type="text" class="form-control" name="username">
                        <div class="center-text"><h5>Пароль:</h5></div><input type="password" class="form-control" name="password">
                        <br>
                        <div class="center-text">
                            <button type="submit" class="btn btn-default but2">Войти</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>