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
                    <c:if test="${!empty user}">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="captchaCheck" name="captchaCheck">
                            <label class="form-check-label" for="captchaCheck"> Ajouter un captcha </label>
                        </div>

                        <div class="form-group">
                            <input type="radio" id="debut_fin" name="validate" value="debut_fin" />
                        </div>
                        <div class="form-group">
                            <label for="date_base">Du: </label>
                            <input type="datetime-local" id="date_base" name="date_base" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="date_fin">Au: </label>
                            <input type="datetime-local" id="date_fin" name="date_fin" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="radio" id="max_clic" name="validate" value="max_clic" />
                        </div>
                        <div class="form-group">
                            <label for="clic">Max clics : </label>
                            <input type="number" id="clic" name="clic" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="radio" id="duree" name="validate" value="duree" />
                        </div>
                        <div class="form-group">
                            <label for="no_debut">Valable jusqu'au: </label>
                            <input type="datetime-local" id="no_debut" name="no_debut" class="form-control">
                        </div>

                    </c:if>
                    <input type="submit" class="btn btn-primary" value="Raccourcir">
                </div>

                <c:if test="${!empty link}">
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="link">Votre lien : </label>
                                    <input type="url" id="link" name="url" value="${ link }" class="form-control" readonly>
                                    <button type="button" onclick="copyToClipboard()">Copier</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <script>
                $('#passwordCheck').on('click', function(){
                    $('.passwordChecked').fadeToggle();
                });

                function copyToClipboard() {
                    var tocopy = document.getElementById('link');
                    tocopy.select();
                    document.execCommand("copy");
                }
            </script>
        </form>

    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
