<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-18
  Time: 오전 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말--%>
<jsp:include page="../../common/header.jsp"/>

<%--    본문 --%>
<div class="container">
    <form action="/exam01/faq/add" method="post">
        <%--        부트스트랩 input 양식 --%>
        <div class="mb-3">
            <%--            게시판제목 입력양식 --%>
            <label for="subject" class="form-label">제목</label>
            <input type="text"
                   class="form-control"
                   id="subject"
                   name="subject"
                   value="${faq.jubject}"
                   placeholder="제목">
        </div>
        <div class="mb-3">
            <%--            게시판내용 입력양식 --%>
            <label for="text" class="form-label">내용</label>
            <input type="text"
                   class="form-control"
                   id="text"
                   name="txt"
                   value="${faq.text}"
                   placeholder="제목">
        </div>

        <div class="md-3">
            <%--  HTML 에서 백엔드로 정보를 전달할려면 type="button" 에서 type="submit" 으로 바꿔야함--%>
            <button type="submit" class="btn btn-primary">저장</button>
        </div>
    </form>
</div>

<%--    꼬리말--%>
<jsp:include page="../../common/footer.jsp"/>

</body>
</html>
