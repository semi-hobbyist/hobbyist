<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>아이디찾기</title>
</head>

<body>
	<section>
	<form action="<%=request.getContextPath()%>/searchId.do" method="post">
		<div>
			<div>
				이름<input type="text" id="memberName" name="memberName" placeholder="이름을 입력해주세요."/><br>
				핸드폰번호<input type="text" id="memberPhone" name="memberPhone" placeholder="핸드폰번호를 입력해주세요."/>
			<div>
				<input type="submit" id="submit" value="확인" onclick="fn_confirm"/>
				<input type="button" id="cancel" value="취소" onclick="self.close()"/> 
			</div>
			<div id="confirm"></div>
			</div>
		</div>
	</form>
	</section>
	
	<script>
		function fn_confirm(){
			var memberName = $('#memberName').val();
			var memberEmail = $('#memberPhone').val();
			console.log(memberName+memberPhone);
			$.ajax({
				url:'<%=request.getContextPath()%>/searchId.do',
				data:'memberName='+memberName+'&memberPhone='+memberPhone,
				type:'post',
				dataType:'json',
				success:function(data){
					alert(data.msg);
					self.close();
				}
			}); 
			
		}
	</script>

	
	
</body>
</html>