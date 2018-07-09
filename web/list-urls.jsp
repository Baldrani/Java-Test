<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Url raccourci</th>
            <th scope="col">Url de Base</th>
            <th scope="col">Date de création</th>
            <th scope="col">Stats</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${urls}" var="item">
            <tr>
                <td>${item.getShortcut()}</td>
                <td>${item.getBase()}</td>
                <td>${item.getCreateAt()}</td>
                <td><a href="/stat/${item.getAbsolute()}">Voir</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%@include file="footer.jsp"%>
</body>
</html>
