<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-12
  Time: 오후 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--TODO: jstl 표현식 사용을 위한 import --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <%-- bootstrap css cdn   --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>매개변수 전달받아 출력 : 조건문 </h2>
    <p>${title}</p>  <%-- name  == 아반떼   --%>
    <p>${color}</p> <%-- color == grey   --%>
    <p>${kind}</p>  <%-- kind  == 세단   --%>

<%--     연습 4) : 아래의 결과를 출력하세요, 조건문 이용해서 아래 출력--%>
<%--        url : /example04--%>
<%--        함수 : setExample04--%>
<%--        jsp : exam03/example04.jsp--%>
<%--        URL 테스트 : http://localhost:8000/exam03/example04?title=아반떼&color=grey&kind=세단--%>
<%--        결과 : 아반떼가 맞습니다.--%>
<%--              회색입니다.--%>
    <c:if test="${title == '아반떼'}">
    <p>아반떼가 맞습니다.</p>
    </c:if>

    <c:if test="${color == 'grey'}">
        <p>회색입니다.</p>
    </c:if>

</div>
</body>
</html>
