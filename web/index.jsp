<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<c:if test="${!empty message}">
    <div class="container mt-5">
        <div class="row">
            <div class="col">
                    <div class="alert alert-danger" role="alert">
                        ${ message }
                    </div>
            </div>
        </div>
    </div>
</c:if>
<div class="container">
    <div class="row">
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
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="checkbox" id="passwordCheck">
                        <label class="form-check-label" for="passwordCheck"> Ajouter un mot de passe </label>
                    </div>
                    <div class="form-group passwordChecked">
                        <input type="password" id="password" name="password" class="form-control" placeholder="••••••••">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Raccourcir">
                </div>
            </div>
            <script>
                $('#passwordCheck').on('click', function(){
                    $('.passwordChecked').fadeToggle();
                })
            </script>
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
