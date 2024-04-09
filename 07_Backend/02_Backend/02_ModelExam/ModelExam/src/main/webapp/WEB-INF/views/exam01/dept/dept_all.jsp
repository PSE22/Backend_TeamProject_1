<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-13
  Time: 오후 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>


</head>
<body>
<%-- 머리말 --%>

<jsp:include page="../../common/header.jsp"/>
<%--  본문--%>
        <div class="container">
            <p>전체 조회</p>
            <p>${list}</p>
            <table class="table">
                <thead>
                <tr>
<%--                    테이블 행--%>
<%--                    TODO: 부서번호(dno) : 클릭하면 => 수정페이지 열기--%>
<%--                    url : /exam01/dept/edition/부서번호--%>
                    <th scope="col">dno</th>
                    <th scope="col">dname</th>
                    <th scope="col">loc</th>
                </tr>
                </thead>
                <tbody>
<%--            복습 : tr : 1행 , td/th : 1열(컬럼, 속성) --%>
<%--         JSP 반복문(jspl 표현식 -> taglib 임포트 필요) : <c:forEach var> --%>
<%--            사용법 : <c:forEach var="변수"  items="${배열}">  --%>
                <c:forEach var="data"  items="${list}">
                    <tr>
                        <td>
                            <a href="/exam01/dept/edition/${data.dno}">${data.dno}</a>
                        </td>
                        <td>${data.dname}</td>
                        <td>${data.loc}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

<%--        TODO : 추가버튼 추가 : a href="이동페이지주소" 태그--%>
<%--            버튼 클릭시 전체조회 화면(dept/addition) 으로 이동--%>
            <div class="text-center">
                <a href="/exam01/dept/edition" class="btn btn-primary">추가</a>
            </div>

        </div>
<%-- 꼬리말 --%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
