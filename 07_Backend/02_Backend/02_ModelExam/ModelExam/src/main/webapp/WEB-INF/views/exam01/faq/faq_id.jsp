<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-18
  Time: 오전 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말--%>
<jsp:include page="../../common/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--    본문  --%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">fno</th>
            <th scope="col">subject</th>
            <th scope="col">text</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${faq.fno}</td>
            <td>${faq.subject}</td>
            <td>${faq.text}</td>
        </tr>
        </tbody>
    </table>

    <%-- 꼬리말 --%>
    <jsp:include page="../../common/footer.jsp"/>
</div>
</body>
</html>
