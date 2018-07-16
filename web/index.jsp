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
        <div class="col-2 mt-5">
            <img src="/img/pub1.png" alt="" class="mw-100 rounded" id="pub">
        </div>
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
                    <c:if test="${!empty user}">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="captchaCheck" name="captchaCheck">
                            <label class="form-check-label" for="captchaCheck"> Ajouter un captcha </label>
                        </div>

                        <div class="form-group">
                            <div class="form-row">
                                <input type="radio" id="debut_fin" name="validate" value="debut_fin"/>&nbsp;
                                <label for="date_base">Du: </label>&nbsp;
                                <div class="col-4">
                                    <input type="datetime-local" id="date_base" name="date_base" class="form-control">
                                </div>
                                &nbsp;<label for="date_fin">Au: </label>&nbsp;
                                <div class="col-4">
                                    <input type="datetime-local" id="date_fin" name="date_fin" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <input type="radio" id="max_clic" name="validate" value="max_clic" />&nbsp;
                                <label for="clic">Max clics : </label>&nbsp;
                                <div class="col-4">
                                    <input type="number" id="clic" name="clic" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <input type="radio" id="duree" name="validate" value="duree" />&nbsp;
                            <label for="no_debut">Valable jusqu'au: </label>&nbsp;
                            <div class="col-4">
                                <input type="datetime-local" id="no_debut" name="no_debut" class="form-control">
                            </div>
                            </div>
                        </div>

                    </c:if>
                    <input type="submit" class="btn btn-primary" value="Raccourcir">

                    <c:if test="${!empty link}">
                        <hr>
                        <div class="form-group">
                            <label for="link">Votre lien : </label>
                            <input type="url" id="link" name="url" value="${ link }" class="form-control" readonly>
                            <button type="button" onclick="copyToClipboard()" class="btn btn-primary mt-2">Copier</button>
                        </div>
                    </c:if>
                </div>
            </div>
        </form>

    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
