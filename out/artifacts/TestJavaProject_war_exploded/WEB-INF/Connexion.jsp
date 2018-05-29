<%--
  Created by IntelliJ IDEA.
  User: onyrick
  Date: 22/05/18
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
</head>
<body>
<div class="header">
    <img src="https://upload.wikimedia.org/wikipedia/fr/2/2e/Java_Logo.svg" alt="Logo">
</div>

<p>${ !empty message ? message : '' }</p>

<p>
<h2>Connexion</h2>
<form action="connexion" method="post">
    <p>
        <label for="login">Login : </label>
        <input type="text" id="login" name="login">
    </p>
    <p>
        <label for="password">Password : </label>
        <input type="password" id="password" name="password">
    </p>

    <input type="submit">
</form>

</body>
</html>

