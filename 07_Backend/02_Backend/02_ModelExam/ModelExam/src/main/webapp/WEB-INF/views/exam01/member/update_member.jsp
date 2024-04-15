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
    <form action="/exam01/member/edit/${member.eno}" method="post">
        <input type="hidden" name="_method" value="put"/>
<%--        기본키 : 시원번호(eno) : 숨김 --%>
        <input type="hidden" name="eno" value="${member.eno}"/>
        <div class="mb-3">
            <%--            사원명(ename) 입력양식 --%>
            <label for="ename" class="form-label">ename</label>
            <input type="text"
                   class="form-control"
                   id="ename"
                   name="ename"
                   value="${member.ename}"
                   placeholder=사원명입력">
        </div>
        <div class="mb-3">
            <%--            직위(job) 입력양식 --%>
            <label for="job" class="form-label">job</label>
            <input type="text"
                   class="form-control"
                   id="job"
                   name="job"
                   value="${member.job}"
                   placeholder="직위입력">
        </div>

        <div class="mb-3">
            <%--            관리자사원번호(manager) 입력양식 --%>
            <label for="manager" class="form-label">manager</label>
            <input type="text"
                   class="form-control"
                   id="manager"
                   name="manager"
                   value="${member.manager}"
                   placeholder="관리자사워번호입력">
        </div>
        <%--     수정버튼       --%>
        <div class="mb-3">
            <button type="submit" class="btn btn-warning">수정</button>
        </div>
    </form>

    <form action="/exam01/member/delete/${member.eno}" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <%--        삭제 버튼 추가 : submit--%>
        <button type="submit" class="btn btn-banger">삭제</button>
    </form>
</div>

<%--  꼬리말  --%>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>