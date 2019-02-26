<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<%
	int selectMyPageBoardCount = (int)request.getAttribute("selectMyPageBoardCount");
	int selectMyPageBoardCommentCount = (int)request.getAttribute("selectMyPageBoardCommentCount");
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if(<%= logginMember!=null %>) {
		
	} else {
		alert('회원만 접근 가능합니다');
		location.href=history.back(-1);
	}
</script>

<section id="myPage">
	<div class="myPage_content">
		<div class="myPage_top" id="myPage_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Member</li>
				<li>마이 페이지에 오신 걸 환영합니다.</li>
			</ul>
			
		</div><br>
		
		<!-- 마이페이지메뉴는 중복되기때문에 myPage_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/mypage/myPage_menu.jsp" %> 
		<!-- 마이페이지 메뉴 인클루드 끝 -->

		<div class="myPage_right">
			<div id="myPage_main">
				<h2>마이페이지 메인</h2>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>