<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-15
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말--%>
<jsp:include page="../../common/header.jsp"/>

<%--    본문  --%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">title</th>
            <th scope="col">content</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${board.id}</td>
                <td>${board.title}</td>
                <td>${board.content}</td>
            </tr>
        </tbody>
    </table>

    <%-- 꼬리말 --%>
    <jsp:include page="../../common/footer.jsp"/>
</div>
</body>
</html>
