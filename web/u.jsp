<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<c:if test="${!empty messageVerif}">
    <div class="container mt-5">
        <div class="row">
            <div class="col">
                <div class="alert alert-danger" role="alert">
                        ${ messageVerif }
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${!empty badEnter}">
    <div class="container mt-5">
        <div class="row">
            <div class="col">
                <div class="alert alert-danger" role="alert">
                        ${ badEnter }
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
                    <c:if test="${link.getPassword() != null}">
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" class="form-control" placeholder="••••••••">
                        </div>
                    </c:if>
                    <c:if test="${link.getCaptcha() == 1}">
                        <img src="/stickyImg" />
                            <input name="answer_captcha" />
                    </c:if>
                    <c:if test="${empty messageVerif}">
                        <input type="submit" class="btn btn-primary" value="Ouvrir">
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
