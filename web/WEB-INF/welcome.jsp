<%@ page import="ENTITIES.ArticleEntity" %><%--
  Created by IntelliJ IDEA.
  User: guillaume
  Date: 28/06/18
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vide-Dressing</title>
    <meta charset="UTF-8">
    <%@include file="header.jsp"%>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="card mt-5 w-100">
            <div class="card-header">
                <c:choose>
                    <c:when test="${empty vendeur}">
                        <h5 class="card-title">S'inscrire en tant que vendeur</h5>
                    </c:when>
                    <c:otherwise>
                        <h5 class="card-title">Liste de vos articles</h5>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${empty vendeur}">
                        <div class="card-text">
                            <form action="/index" method="post">
                                <div class="form-group">
                                    <label for="prenom">Prénom</label>
                                    <input type="text" id="prenom" name="prenom" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" id="nom" name="nom" class="form-control" required>
                                </div>
                                <input type="submit" name="signup" class="btn btn-primary" value="S'inscrire">
                            </form>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="card-text">
                            <div class="col">
                                <c:if test="${!empty articles}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Code Article</th>
                                            <th scope="col">Prix</th>
                                            <th scope="col">Taille</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Voir</th>
                                            <th scope="col">Editer</th>
                                            <th scope="col">Statut</th>
                                            <th scope="col">Retirer</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${articles}" var="article">
                                            <tr>
                                                <td class="code-article">${article.codeArticle}</td>
                                                <td class="prix-article">${article.prix} €</td>
                                                <td class="taille-article">${article.taille}</td>
                                                <td class="description-article">${article.description}</td>
                                                <td>
                                                    <a href="article?id=${article.codeArticle}" class="btn btn-primary">Voir la fiche</a>
                                                </td>

                                                <td>
                                                    <a href="edit-article?id=${article.codeArticle}" class="btn btn-outline-primary">Editer</a>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${article.vendu == 1}">
                                                            <button class="btn btn-danger" disabled>Vendu</button>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <c:if test="${article.codeVendeur != vendeur.codeVendeur}">
                                                                <a href="buy-article?id=${article.codeArticle}" class="btn btn-outline-success">Acheter</a>
                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a class="btn btn-danger" href="delete-article?id=${article.codeArticle}">Retirer</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${empty articles}">
                                    Aucun article
                                </c:if>
                                <div>
                                    <a href="create-article" class="btn btn-outline-primary">Ajouter un nouvel article</a>
                                </div>

                                <div class="mt-3">
                                    Total de vos ventes : <c:if test="${!empty total}">${total}</c:if> €
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <c:if test="${!empty danger}">
            <div class="alert alert-danger" role="alert">
                    ${danger}
            </div>
        </c:if>

        <c:if test="${!empty succes}">
            <div class="alert alert-succes" role="alert">
                    ${succes}
            </div>
        </c:if>
    </div>

    <div class="row">
        <div class="card mt-5 w-100 mb-5">
            <div class="card-header">
                <h5 class="card-title">Tous les articles</h5>
            </div>
            <div class="card-body">
                <c:choose>
                    <c:when test="${!empty vendeur}">
                        <div class="card-text">
                            <div class="col">
                                <c:if test="${!empty allArticles}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">Code Article</th>
                                            <th scope="col">Prix</th>
                                            <th scope="col">Taille</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Voir</th>
                                            <th scope="col">Editer</th>
                                            <th scope="col">Statut</th>
                                            <th scope="col">Retirer</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${allArticles}" var="article">
                                            <tr>
                                                <td class="code-article">${article.codeArticle}</td>
                                                <td class="prix-article">${article.prix} €</td>
                                                <td class="taille-article">${article.taille}</td>
                                                <td class="description-article">${article.description}</td>
                                                <td>
                                                    <a href="article?id=${article.codeArticle}" class="btn btn-primary">Voir la fiche</a>
                                                </td>

                                                <td>
                                                    <c:if test="${article.codeVendeur == vendeur.codeVendeur}">
                                                        <a href="edit-article?id=${article.codeArticle}" class="btn btn-outline-primary">Editer</a>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${article.vendu == 1}">
                                                            <button class="btn btn-danger" disabled>Vendu</button>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <c:if test="${article.codeVendeur != vendeur.codeVendeur}">
                                                                <a href="buy-article?id=${article.codeArticle}" class="btn btn-outline-success">Acheter</a>
                                                            </c:if>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:if test="${article.codeVendeur == vendeur.codeVendeur}">
                                                        <a class="btn btn-danger" href="delete-article?id=${article.codeArticle}">Retirer</a>

                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <c:if test="${empty articles}">
                                    Aucun article
                                </c:if>

                            </div>
                        </div>
                    </c:when>

                    <c:otherwise>
                        Connectez-vous pour voir les articles.
                    </c:otherwise>

                </c:choose>
            </div>
        </div>
    </div>

</div>

<%@include file="footer.jsp"%>
</body>
</html>
