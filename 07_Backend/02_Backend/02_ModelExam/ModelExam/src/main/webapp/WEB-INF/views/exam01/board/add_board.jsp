<%--
  Created by IntelliJ IDEA.
  User: GGG
  Date: 2024-03-14
  Time: 오후 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    머리말--%>
<jsp:include page="../../common/header.jsp"/>

<%--    본문--%>
<%-- html : 입력(insert/update) : form/input 태그 --%>
<%-- 웹 전송(약속) : get(조회). post(추가) --%>
<%--               put(수정:update), delete(삭제:delete)--%>
<%--    get 방식 : 웹브라우저 주소창에 값을 입력해서 전송하는 방식 --%>
<%--    post/put/delete 방식 : 눈에 안보이고 내부적으로 숨겨서 전송되는 방식--%>
<%--  TODO : 프론트(html/vue/jsp) <=> 백앤드(spring) :
        1) from 태그의 input 양식의 값은 name 속성이름으로 spring 으로 전송됨  --%>
<%--       예) input name="dname" => spring : dname 변수명으로 전달받음--%>
<%--    2) submit 버튼을 클릭하면 위의 값이 전달됨 --%>
<%--    3) form action="/컨트롤러함수url" method="웹전송방식"--%>

<%--   form 태그에서 기본적으로 get, post 밖에 사용 못하는데
       application.properties 에 아래 세팅 넣으면 사용 가능해짐--%>
<%--# todo: PUT , DELETE 방식도 form 에서 사용할 수 있게 만들어줌--%>
<%--spring.mvc.hiddenmethod.filter.enabled=true--%>

<div class="container">
    <form action="/exam01/board/add" method="post">
        <%--        부트스트랩 input 양식 --%>
        <div class="mb-3">
            <%--            게시판제목 입력양식 --%>
            <label for="title" class="form-label">제목</label>
            <input type="text"
                   class="form-control"
                   id="title"
                   name="title"
                   value="${board.title}"
                   placeholder="게시판제목">
        </div>
        <div class="mb-3">
            <%--            게시판내용 입력양식 --%>
            <label for="content" class="form-label">내용</label>
            <input type="text"
                   class="form-control"
                   id="content"
                   name="content"
                   value="${board.content}"
                   placeholder="게시판제목">
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
