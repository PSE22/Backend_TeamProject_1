<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-12
  Time: 오후 12:40
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
            <p>${name}</p>  <%-- name  == 삼식이   --%>
            <p>${color}</p> <%-- color == brown   --%>
            <p>${kind}</p>  <%-- kind  == dog   --%>

<%--            TODO: jsp 조건문 : jstl 표현식(taglib 임포트) --%>
<%--                뷰의 조건문 : 조건식이 참이면 태그가 화면에 보임--%>
<%--                jsp 의 조건문 : 조건식이 참이면 태그가 화면에 보임--%>
<%--                사용법 : <c:if test="${변수명 == '값'}> --%>
<%--                사용법 : <c:if test="${조건식}">       --%>
            <c:if test="${name == '삼식이'}">
                <p>삼식이가 맞습니다.</p>
            </c:if>

<%--            TODO: choose 조건문으로 출력 #2 : else if, else 에 해당되는 키워드--%>
<%--                사용법 : <c:choose> --%>
<%--                          <c:when test="${color == 'brown'}}"> --%>
<%--                           태그1   --%>
<%--                          </c:when>--%>
<%--                          <c:otherwise>--%>
<%--                           태그2--%>
<%--                          </c:otherwise>--%>
<%--                        </c:choose> --%>
<%--                해석 : 조건식1이 참이면 태그1이 보이고 모두 거짓이면 태그2가 보임--%>
            <c:choose>
                <c:when test="${color == 'brown'}}">
                    <p>갈색입니다.</p>
                </c:when>
                <c:when test="${color == 'yellow'}">
                    <p>노란색입니다.</p>
                </c:when>
                <c:otherwise>
                    <p>다른색깔입니다. </p>
                </c:otherwise>
            </c:choose>
        </div>
</body>
</html>
