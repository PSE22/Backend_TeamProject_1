<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-29
  Time: 오후 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 머리말  --%>
<jsp:include page="../../../common/header.jsp"/>

<%-- 본문  --%>
<div class="container">
    <%--    todo: 테이블 반복문 시작--%>
    <table class="table">
        <tbody>
        <c:forEach var="data" items="${list}">
            <tr>
                <td>${data.eno}</td>
                <td>${data.ename}</td>
                <td>${data.job}</td>
                <td>${data.manager}</td>
                <td>${data.hiredate}</td>
                <td>${data.salary}</td>
                <td>${data.commission}</td>
                <td>${data.dno}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--    todo: 테이블 반복문 끝--%>
</div>
<%-- 꼬리말  --%>
<jsp:include page="../../../common/footer.jsp"/>
</body>
</html>

