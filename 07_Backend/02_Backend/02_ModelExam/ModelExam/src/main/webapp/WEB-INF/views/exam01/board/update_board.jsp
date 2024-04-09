<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-14
  Time: 오후 5:03
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

<%-- 본문 : 수정/추가 : form/input 태그 --%>
<%--  insert -> post 방식   -> @PostMapping --%>
<%--  update -> put 방식    -> @PutMapping --%>
<%--  delete -> delete 방식 -> @DeleteMapping --%>
<%--  select -> get 방식    -> @SelectMapping --%>
<div class="container">
    <%--    수정 form --%>
    <form action="/exam01/board/edit/${board.id}" method="post">
        <%--        TODO: 아래 input(hidden) 사용하면 put 방식으로 전송됨 --%>
        <input type="hidden" name="_method" value="put"/>
        <input type="hidden" name="id" value="${board.id}"/>
        <div class="mb-3">
            <%--            부서명 입력양식 --%>
            <label for="title" class="form-label">제목</label>
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   value="${board.title}"
                   placeholder="제목">
        </div>
        <div class="mb-3">
            <%--            부서위치 입력양식 --%>
            <label for="content" class="form-label">내용</label>
            <input type="text"
                   class="form-control"
                   id="content"
                   name="content"
                   value="${board.content}"
                   placeholder="내용">
        </div>
        <%--     수정버튼       --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>

<%--        삭제 form --%>
<%--        put 과 delete는 최신방식이라 method="delete" 못하고 post로 해놓고 input 한줄 넣어야함--%>
    <form action="/exam01/board/delete/${board.id}" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <button type="submit" class="btn btn-danger">삭제</button>
    </form>

</div>
<%--  꼬리말  --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
