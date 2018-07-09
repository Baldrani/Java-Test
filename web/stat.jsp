<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp"%>
    <title></title>
</head>
<body>
<%@include file="header.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
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
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Url raccourci</th>
            <th scope="col">Url de Base</th>
            <th scope="col">Date de création</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${url.getShortcut()}</td>
                <td>${url.getBase()}</td>
                <td>${url.getCreateAt()}</td>
            </tr>
        </tbody>
    </table>

    <canvas id="chart" width="400" height="200"></canvas>
    <script type="text/javascript">
        var ctx = document.getElementById("chart");
        var labels = [];
        var data = [];
        var timeFormat = "DD/MM/YYYY";
        <c:forEach items="${chartData}" var="stat">
            labels.push("${stat.key}");
            data.push(${stat.value});
        </c:forEach>

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Nombre de clics',
                        data: data,
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            display: true,
                            ticks: {
                                suggestedMin: 0,
                                beginAtZero: true
                            },
                        }]
                    }
                }
           });
    </script>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
