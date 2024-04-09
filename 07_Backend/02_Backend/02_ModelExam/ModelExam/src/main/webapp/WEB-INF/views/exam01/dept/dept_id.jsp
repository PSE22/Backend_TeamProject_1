<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-14
  Time: 오전 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--  머리말  --%>
<jsp:include page="../../common/header.jsp"/>

<%--   본문  --%>
<%--   상세조회 테이블--%>
<div class="container">
    <table class="table">
        <thead>
        <%--  출력 url :  http://localhost:8000/exam01/dept/10 --%>
        <tr>
            <th scope="col">dno</th>
            <th scope="col">dname</th>
            <th scope="col">loc</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${dept.dno}</td>
            <td>${dept.dname}</td>
            <td>${dept.loc}</td>
        </tr>
        </tbody>
    </table>
</div>

<script>
    <%--    js 코딩 부분 --%>
    let obj = "${dept}";
    console.log(obj);
</script>

<%--  꼬리말  --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
