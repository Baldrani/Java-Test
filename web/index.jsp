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
        <p>${ !empty message ? message : '' }</p>
        <form action="put-url" method="post" class="m-auto w-75">
            <div class="card mt-5">
                <div class="card-header">
                    Indiquer votre URL à raccourcir:
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="url">Url : </label>
                        <input type="url" id="url" name="url" placeholder="https://example.com" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password">Mot de passe : </label>
                        <input type="password" id="password" name="password" class="form-control">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Raccourcir">
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
