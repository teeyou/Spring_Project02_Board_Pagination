<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
</head>
<body>
<form action="/board/save" method="post">
    <input type="text" name="boardWriter" placeholder="작성자">
    <input type="text" name="boardPass" placeholder="비밀번호">
    <input type="text" name="boardTitle" placeholder="제목">
    <textarea name="boardContents" cols="30" rows="10" placeholder="내용을 입력하세요"></textarea>
    <input type="submit" value="작성">
</form>
</body>
<script>
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
        history.pushState(null, null, document.URL);
        if(confirm("작성을 취소하시겠습니까?")) {
            location.href = "/";
        } else {

        }
    });
</script>
</html>