<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-18
  Time: 오전 11:06
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

<%--    본문 --%>
<div class="container">
    <%--    수정 form --%>
    <form action="/exam01/faq/edit/${faq.fno}" method="post">
        <%--        TODO: 아래 input(hidden) 사용하면 put 방식으로 전송됨 --%>
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" name="fno" value="${faq.fno}"/>
        <div class="mb-3">
            <%--            부서명 입력양식 --%>
            <label for="subject" class="form-label">제목</label>
            <input type="text"
                   class="form-control"
                   id="subject"
                   name="subject"
                   value="${faq.subject}"
                   placeholder="제목">
        </div>
        <div class="mb-3">
            <%--            부서위치 입력양식 --%>
            <label for="text" class="form-label">내용</label>
            <input type="text"
                   class="form-control"
                   id="text"
                   name="text"
                   value="${faq.text}"
                   placeholder="내용">
        </div>
        <%--     수정버튼       --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>

    <%--        삭제 form --%>
    <%--        put 과 delete는 최신방식이라 method="delete" 못하고 post로 해놓고 input 한줄 넣어야함--%>
    <form action="/exam01/faq/delete/${faq.fno}" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit" class="btn btn-danger">삭제</button>
    </form>

</div>
<%--  꼬리말  --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
