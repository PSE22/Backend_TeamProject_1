<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-13
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<%-- 머리말 --%>
<jsp:include page="../../common/header.jsp"/>

<div class="container">
    <h2>전체 조회</h2>
    <p>${list}</p>
    <table class="table">
        <thead>

        <tr>
            <th scope="col">eno</th>
            <th scope="col">ename</th>
            <th scope="col">job</th>
            <th scope="col">manager</th>
        </tr>
        </thead>
        <tbody>
        <%--            복습 : tr : 1행 , td/th : 1열(컬럼, 속성) --%>
        <%--         JSP 반복문(jspl 표현식 -> taglib 임포트 필요) : <c:forEach var> --%>
        <%--            사용법 : <c:forEach var="변수"  items="${배열}">  --%>
        <c:forEach var="data"  items="${list}">
            <tr>
                <td>
                    <a href="/exam01/member/edition/${data.eno}">${data.eno}</a>
                </td>
                <td>${data.ename}</td>
                <td>${data.job}</td>
                <td>${data.manager}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>

<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
