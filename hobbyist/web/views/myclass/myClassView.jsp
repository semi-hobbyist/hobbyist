<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.myclass.model.vo.Lecture" %>

<%
	List<Lecture> lecture = (List)request.getAttribute("Lecture");
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

<section id="lecture">
	<div class="lecture_content">
		<div class="lecture_top" id="lecture_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>/myClass?member=<%= logginMember != null ? logginMember.getMemberEmail() : "" %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="16px">&nbsp;&nbsp;
				돌아가기</li>
				<li>&nbsp;&nbsp;&nbsp;<img src="<%= request.getContextPath() %>/images/progress.png" width="13px">My Class</li>
				<li>내 클래스 > 내 클래스 강좌</li>
			</ul>
		</div>
		<br>
		<div class="lecture_center">
				<table id="lecture-Ajax">
				</table>
		</div>
	</div>
	<script>
			// Ajax 처리될 부분
			var table = $('#lecture-Ajax');
			// 강좌 페이지 진입 시 Ajax 로 리스트 가져오기
			$.ajax({
				url: '<%=request.getContextPath() %>/myClassDetailView?no=1',
				success: function (data) {
					table.empty();
					table.html(data);
				}
			});
			
			// 강좌메뉴 클릭 시, Ajax
			function fn_lecture_mainAjax(no) {
				$.ajax({
					url: '<%=request.getContextPath() %>/myClassDetailView?no=' + no,
					success: function (data) {
						table.empty();
						table.html(data);
					}
				});
			}
			
			// 강좌 페이지 상단 숨기기 및 보이기
            $(window).scroll(function(){
                var target = $('#lecture_top');
				var scrollTo = $(window).scrollTop();
				if(scrollTo>=70) {
					$('#lecture_top').css("height","0px");
				} else {
					$('#lecture_top').css({"height":"50px","border":"none"});
				}
                });
			
			// 다 봤어요 버튼 클릭 시, 수강현황 업데이트
			function fn_progress(no) {
				$.ajax({
					url : '<%= request.getContextPath() %>/myClassUpdate?no=' + no + '&member=<%= logginMember!=null ? logginMember.getMemberEmail() : "" %>',
					success: function(data) {
						
					}
				})
			}
        </script>
</section>

<%@ include file="/views/common/footer.jsp" %>