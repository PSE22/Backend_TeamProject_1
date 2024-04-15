<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-14
  Time: 오전 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%--머리말--%>
<jsp:include page="../../common/header.jsp"/>

<%--//  todo: 연습 2)--%>
<%--//    MemberService 클래스를 만들고 findById() 함수를 정의한다.--%>
<%--//    MemberController 클래스를 만들어서 getMemberId() 함수를 정의하고,--%>
<%--//    샘플데이터 뷰로(jsp : member_id.jsp) 전송해 보세요.--%>
<%--//    url : /member/{eno}--%>
<%--//    jsp : exam01/member/member_id.jsp--%>
<%--//    url test : http://localhost:8000/exam01/member/7369--%>
<%--본문--%>

<div class="container">
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
        <tr>
            <td>${member.eno}</td>
            <td>${member.ename}</td>
            <td>${member.job}</td>
            <td>${member.manager}</td>
        </tr>
        </tbody>
    </table>
</div>





<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
