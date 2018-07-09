<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">
    <div class="row">
        <p>Bonjour ${ user.login },</p>
    </div>
    <div class="row">
        <p>vous pouvez désormais accéder à toutes nos options de création d’URL raccourcies</p>
    </div>
    <div class="row">
        <p> Avec mot de passe</p>
    </div>
    <div class="row">
        <p> Avec captcha</p>
    </div>
    <div class="row">
        <p> A durée limitée</p>
    </div>
    <div class="row">
        <p> A durée périodique</p>
    </div>
    <div class="row">
        <p> AVec un nombre maximum de clics</p>
    </div>
    <div class="row">
        <p> Visualisation des statistiques</p>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
