<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hobbyist.member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%-- <%
	String randomPwd = (String)request.getAttribute("randomPwd");
	Member result = (Member)request.getAttribute("result");
%> --%>
</head>
<body>
<section>
	<form action="<%=request.getContextPath()%>/searchPwd.do" method="post">
		<div>
			<div>
				이메일<input type="text" id="memberEmail" name="memberEmail" placeholder="이메일을 입력해주세요."/><br>
				<select name="memberEmailaddress" id="memberEmailaddress">
					<option>선택해주세요.</option>
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
					<option>@yahoo.co.kr</option>
					<option>@empal.com</optsion>
					<option>@dreamwiz.com</option>
					<option>@orgio.net</option>
					<option>@korea.com</option>
					<option>@nate.com</option>
				</select>
				<div>
					<input type="button" id="checkId" name="checkId" value="아이디확인" onclick="fn_checkId()"/>
				</div>
				
				<div id="check">
					<input type="text" id="checkCode" name="checkCode" onclick="fn_checkCode()"/>	
				</div>
				<div>
					<input type="button" id="cancel" value="취소" onclick="self.close()"/> 
				</div>
			</div>
		</div>
	</form>
	
	<form name="pwdCodeFrm" method="post">
		<input type="hidden" id="pwdCode" name="pwdCode"/>
	</form>
	</section>
	
	<script>
	function fn_checkId(){
		var memberEmail = $('#memberEmail').val();
		var memberEmailaddress = $('#memberEmailaddress').val();
		var pwdCode =$('#pwdCode').val();
		
		var text1 = "";
		var text2 = "";
		var result = "";
		var alphabet = "abcdefghijklmnopqrstuvwxyz";
		var num = "0123456789";
		     for( var i=0; i < 4; i++ )
		     {      
		         text1 += alphabet.charAt(Math.floor(Math.random() * alphabet.length));
		         text2 += num.charAt(Math.floor(Math.random() * num.length));
		     }
		   	result = text1+text2+"!!";
		
		$.ajax({
			url : '<%= request.getContextPath() %>/searchPwd.do',
			data : 'memberEmail=' + memberEmail + '&memberEmailaddress=' + memberEmailaddress +'&pwdCode=' +result,
			dataType : 'json',
			success : function(data) {
				alert(data.msg);
				self.close();
			}
		});
	}

	</script>
	

</body>
</html>