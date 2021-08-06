<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="/accounts" class="bth btn-primary">home</a>
    <a href="/form/register" class="bth btn-primary">register</a>
    <a href="/form/deposite" class="bth btn-primary">deposite</a>
    <a href="/form/withdraw" class="bth btn-primary">withDraw</a>
    <a href="/form/transfer" class="bth btn-primary">transfer</a>
    <br>
    <br>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>id</th>
            <th>balance</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td><a href="account/${account.id}">${account.id}</a></td>
                <td><a href="account/balance?id=${account.id}"><button type="button" class="btn btn-primary">balance</button></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>