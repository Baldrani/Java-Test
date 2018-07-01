<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
    <body>
        <%@include file="header.jsp"%>
        <main class="container-fluid">
            <div class="row">
                <p>${ !empty message ? message : '' }</p>
                <jsp:doBody/>
            </div>
        </main>
        <%@include file="footer.jsp"%>
    </body>
</html>