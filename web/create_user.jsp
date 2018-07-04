<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<main class="container-fluid">
    <div class="row">
        <p>${ !empty message ? message : '' }</p>
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
                    <div class="form-group">
                        <input type="radio" id="particulier" name="type" value="particulier" checked />
                        <label for="particulier">Particulier</label>
                    </div>

                    <div class="form-group">
                        <input type="radio" id="entreprise" name="type" value="entreprise"/>
                        <label for="entreprise">Entreprise</label>
                    </div>

                    <div class="form-group">
                        <input type="radio" id="association" name="type" value="association"/>
                        <label for="association">Association</label>
                    </div>
                    <input type="submit" class="btn btn-primary" value="Envoyer">
                </div>
            </div>
        </form>
    </div>
</main>
<%@include file="footer.jsp"%>
</body>
</html>