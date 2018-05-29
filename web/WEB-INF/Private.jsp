<%--
  Created by IntelliJ IDEA.
  User: onyrick
  Date: 22/05/18
  Time: 11:36
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

<p>${ !empty message ? message : '' }</p>

<c:if test="${ !empty sessionScope.login }">
    <p>Bonjour ${ sessionScope.login }</p>
</c:if>
<p>
<h3>Liste des users</h3>

<c:forEach items="${ users }" var="user">
    <c:out value = "${user.login}"/> / <c:out value = "${user.password}"/><br>
</c:forEach>

<button><a href="/test1/logout">Se Deconnecter</a></button>
</body>
</html>
