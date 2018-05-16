<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/board.js"></script>
<title>비밀번호가 일치할 경우 처리하는 jsp</title>
</head>
<body>
<script type="text/javascript">
	if(window.name=="update"){ //수정을 위해 비밀번호 입력한거라면 밑의 href요청
		window.opener.parent.location.href="BoardServlet?command=board_update_form&num=${param.num}";
	}else if(window.name=='delete'){
		alert("삭제되었습니다.");
		window.opener.parent.location.href="BoardServlet?command=board_delete&num=${param.num}";
	}
	window.close();
</script>
</body>
</html>