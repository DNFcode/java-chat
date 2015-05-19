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
        }
        .but {
            color: #78909C; /* Цвет символа */
        }
    </style>
</head>
<body>
<h1></h1>

    <div class="container">
        <div class="row">
            <center>
            <div class="col-sm-12">
                <!-- Bual -->
                <button type="button" class="btn btn-default but2" data-toggle="modal" data-target=".bs-example-modal-sm">
                    <span class="but">Log in</span>
                </button>
                <button type="button" class="btn btn-default but2">
                    <span class="but">Log out</span>
                </button>
                <h1></h1>
            </div>

            <div class="col-sm-3">
                <font color=#78909C><h4>Пользователи</h4></font>
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox1" value="option1"> <font color=#78909C>онлайн</font>
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="inlineCheckbox2" value="option1"> <font color=#78909C>все</font>
                </label>
                <br>
                <select multiple class="form-control2">

                </select>
            </div>
            </center>
            <div class="col-sm-9">
                <h5 class="info">
                    <div multiple class="form-control2">

                    </div>
                </h5>
                <div class="col-sm-12">
                    <input type="text" class="form-control" name="message" placeholder="Введите сообщение">
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>

<center>
    <!-- Modal -->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><font color=#78909C>Авторизация</font></h4>
                </div>
                <div class="modal-body">
                    <form>
                        <h5><font color=#78909C>Логин:</font></h5><input type="text" class="form-control" name="login">
                        <h5><font color=#78909C>Пароль:</font></h5><input type="password" class="form-control" name="password">
                        <br>
                        <button type="submit" class="btn btn-default but2"><font color=#78909C>Войти</font></button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</center>
</body>
</html>