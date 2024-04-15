<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-14
  Time: 오후 2:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말  --%>
<jsp:include page="../../common/header.jsp"/>


<%--    본문   --%>
<div class="container">
    <form action="/exam01/member/add" method="post">
        <%--        부트스트랩 input 양식 --%>
        <div class="mb-3">
            <%--            사원명 입력양식 --%>
            <label for="ename" class="form-label">ename</label>
            <input type="text"
                   class="form-control"
                   id="ename"
                   name="ename"
                   placeholder="사원명입력">
        </div>
        <div class="mb-3">
            <%--            직위 입력양식 --%>
            <label for="job" class="form-label">job</label>
            <input type="text"
                   class="form-control"
                   id="job"
                   name="job"
                   placeholder="직위입력">
        </div>
        <div class="mb-3">
            <%--            부서위치 입력양식 --%>
            <label for="manager" class="form-label">manager</label>
            <input type="text"
                   class="form-control"
                   id="manager"
                   name="manager"
                   placeholder="관리자">
        </div>
        <div class="md-3">
            <button type="submit" class="btn btn-primary">저장</button>
        </div>
    </form>
</div>

<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
