<%--
  Created by IntelliJ IDEA.
  User: onyrick
  Date: 22/05/18
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout>

    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:body>
        <form action="create-user" method="post" class="m-auto w-75">
            <div class="card mt-5">
                <div class="card-header">
                    Inscription
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="login">Pseudo : </label>
                        <input type="text" id="login" name="login" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="email">Email : </label>
                        <input type="email" id="email" name="email" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Mot de passe : </label>
                        <input type="password" id="password" name="password" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password-confirm">Confirmation du mot de passe : </label>
                        <input type="password" id="password-confirm" name="password-confirm" class="form-control">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Envoyer">
                </div>
            </div>
        </form>
    </jsp:body>

</t:layout>