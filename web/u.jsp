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
        <form action="${uri}" method="post" class="m-auto w-75">
            <div class="card mt-5">
                <div class="card-header">
                    Demande d'accès:
                </div>
                <div class="card-body">
                    <div class="form-group">
                        <label for="url">Url demandée: </label>
                        <input type="url" id="url" name="url" value="${link.getShortcut()}" class="form-control" readonly>
                    </div>
                    <div class="form-group passwordChecked">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="••••••••">
                    </div>
                    <input type="submit" class="btn btn-primary" value="Ouvrir">
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
