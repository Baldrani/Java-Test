<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div class="row mt-5">
        <div class="card m-auto">
            <div class="card-header">
                <h5 class="card-title m-0">Bonjour ${ user.login } !</h5>
            </div>
            <div class="card-body">
                <p>vous pouvez désormais accéder à toutes nos options de création d’URL raccourcies <br> •  Avec mot de passe <br> •  Avec captcha <br> •  A durée limitée <br> •  A durée périodique <br> •  Avec un nombre maximum de clics <br> •  Visualiser vos statistiques</p>
                <a class="btn btn-primary mt-2" href="/">Commencer</a>
            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
