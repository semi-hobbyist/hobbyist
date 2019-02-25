<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.award.model.vo.*"%>
<%@ include file="/views/common/header.jsp"%>
<%
	Award a = (Award)request.getAttribute("award");
	List<AwardComment> comments= (List) request.getAttribute("comments");

	if(request.getAttribute("comments")!=null) {
	} else {
	}
	
%>
<section id="award">
	<div class="award_content">
		<div id="award_right">
			<b>Award</b>|&nbsp;&nbsp;메인 > 하비스트 어워드 > 어워드 게시물
			<!-- =============== -->
			<div class="award_top">
				<div class="award_top_left">
					<div class="award_board_no">
						<span>게시글 NO. <%=a.getAwardNo()%></span>
					</div>

				</div>
				<div class="award_top_right">
					<div class="award_board_date">

						<span><%=a.getAwardDate()%></span>
					</div>
				</div>
			</div>
			<!-- ================ -->
			<div class="award_board_view">
				<div class="award_board_view_name"><%=a.getAwardName()%></div>
				<div class="award_board_view_writer">
					작성자 :<%=a.getAwardWriter()%>
					
					
					<br>조회수 :<%=a.getReadCount() %>
					<br>추천수: ♡
					<%=a.getLikeCount() %>

				</div>

				<div class="award_board_view_content"><%=a.getAwardContent()%></div>
				
				<div class="award_board_view_image">
					<img
						src="<%=request.getContextPath()%>/upload/award/images/<%=a.getAwardOriginalFilename()%>"
						width="300px">
				</div>
				
			</div>
			<!-- =========좋아요버튼======= -->
			<div id="awardlike_btn" onclick="fn_clickLike()">
				<span>어워드 게시물이 마음에 드셨다면 <strong>추천</strong>버튼을 눌러주세요
				</span> <br>
				<br> <span> <a href=""><img id="award_like_img"
						src="<%=request.getContextPath()%>/images/award_like_off.png" width="90px" height="90px"></a>
				</span>
			</div>
		</div>



		<br>
		<br>
		<br>
		<br>
		<%if(logginMember!=null&&(a.getAwardWriter().equals(logginMember.getMemberEmail()))) {%>
		<input type="button" value="게시글 삭제하기" onclick="fn_deleteAward()">
		<input type="button" value="게시글 수정하기" onclick="fn_updateAward()">
		<%} %>
		<!-- ================ -->
		<div class="view_comment">
			<form name="commentFrm"
				action="<%=request.getContextPath()%>/award/awardCommentInsert"
				method="post">
				<input type="hidden" name="awardRef" value="<%=a.getAwardNo()%>">
				<input type="hidden" name="awardCommentWriter"
					value="<%=logginMember != null ? logginMember.getMemberEmail() : ""%>">
				<input type="hidden" name="awardCommentLevel" value="1"> <input
					type="hidden" name="awardCommentRef" value="0">


				<%if(logginMember!=null) {%>
				<textarea name="awardCommentContent" rows="3" cols="60"></textarea>
				<button type="submit" id="btn-insert">댓글등록</button>
				<%} %>
			</form>

			<form name="deleteFrm"
				action="<%=request.getContextPath()%>/award/awardDelete"
				method="post">
				<input type="hidden" name="deleteNo" value="<%=a.getAwardNo()%>">
				<input type="hidden" name="deleteFile"
					value="<%=a.getAwardOriginalFilename()%>">
			</form>
		</div>

		<!-- 커멘트 테이블  -->
		<table id="">
			<% 
			if(!comments.isEmpty()&&logginMember!=null) {
				for(AwardComment ac : comments) {
					if(ac.getAwardCommentLevel()==1) {
		%>
			<tr>
				<td><sub><%=ac.getAwardCommentWriter() %></sub> <sub><%=ac.getAwardCommentDate() %></sub>
					<br /><%=ac.getAwardCommentContent() %></td>
				<td>
					<!-- 대댓글버튼 -->


					<button class="btn-reply" value="<%=ac.getAwardCommentNo()%>">답글</button>

 					<% if(logginMember!=null){ %>
					<% if(ac.getAwardCommentWriter().equals(logginMember.getMemberEmail()) || logginMember.getMemberEmail().equals("admin")) { %>
						<button class="btn-delete" value="<%=ac.getAwardCommentNo()%>" onclick="fn_reCommentDelete()">삭제</button>
					<% } %>
					<% } %>
				</td>
			</tr>

				<%} else { %>
				<tr class='level2'>
					<td><sub><%=ac.getAwardCommentWriter() %></sub> <sub><%=ac.getAwardCommentWriter() %></sub>
						<br /><%=ac.getAwardCommentContent() %></td>
	
				</tr>
				<%} %>
			<%}} %>
		</table>
	</div>
</section>
<script>
		$(function() {
			$(document).one('click','.like-review',
				function(e) {$(this).html('<i class="fa fa-heart" aria-hidden="true"></i> You liked this');
							$(this).children('.fa-heart').addClass('animate-like');
							});	
			});

		function fn_deleteAward() {
			var flag = confirm("어워드를 삭제 하시겠습니까?");
			if (flag) {
				deleteFrm.submit();
			} else {
				return;
			}
		}
		
		function fn_updateAward(){
			location.href="<%=request.getContextPath()%>/award/awardUpdate?awardNo=<%= a.getAwardNo() %>";
		}
		
		$(function(){
			
			$("[name=awardCommentContent]").focus(function(){
				if(<%=logginMember==null%>){
					fn_loginAlert();
					return;
				}
			});
		
			$("[name=commentFrm]").submit(function(e){
				if(<%=logginMember==null%>){
					fn_loginAlert();
					e.preventDefault();
					return;
				}
				var len=$("textarea[name=awardCommentContent]").val().trim().length;
				if(len==0){
					alert("내용을 입력해 주세요");
					e.preventDefault();
				}
				
			})
			
		});
			
		function fn_loginAlert(){
			alert("로그인 후 이용할 수 있습니다");
		}
		
		function fn_deleteAward(){
			var flag=confirm("어워드 게시물을 지우시겠습니까?");
			if(flag){
				deleteFrm.submit();
			}else{
				return;
			}
			
		}
		
		$(".btn-reply").on("click",function(){
			<%if(logginMember!=null){%>
				var tr=$("<tr></tr>");
				var html="<td style='display:none;text-align:left;'colspan=2>";
				html+="<form action='<%=request.getContextPath()%>/award/awardCommentInsert' method='post'>";
				
				html+="<input type='hidden' name='awardRef' value='<%=a.getAwardNo()%>'/>";
				html+="<input type='hidden' name='awardCommentWriter' value='<%=logginMember.getMemberEmail()%>'/>";
				html+="<input type='hidden' name='awardCommentLevel' value='2'/>";
				html+="<input type='hidden' name='awardCommentRef' value='"+$(this).val()+"'/>";
				html+="<textarea cols='60' rows='1' name='awardCommentContent'></textarea>";
				html+="<button type='submit'> 대댓글 등록</button>";
				html+="</form></td>";
				
				tr.html(html);
				tr.insertAfter($(this).parent().parent()).children('td').slideDown(500);//[답글]누르면 slideDown시킨다
				//대댓글이므로 해당 댓글 아래에 대댓글이 달려야되니까==tr1 아래에달려야됨
				$(this).off('click');
				tr.find("form").submit(function(){
					if(<%=logginMember == null%>){
						fn_loginAlert();
						e.preventDefault();
						return;
					}
					var content=$(this).childres('textarea').val().trim().length;
					if(content==0){
						alert("내용을 입력하세요");
						e.preventDefault();
						return;
					}
				
				});
				
				
			
			
			<%} else {%>
			fn_loginAlert();
			<%}%>
		});
		
		/* =======좋아요버튼 */
		/* 버튼에 마우스 올렸을때 */
		$('#awardlike_btn').mouseenter(function(){
			$('#award_like_img').attr("src","<%=request.getContextPath()%>/images/award_like_on.png");	
		});
		/* 버튼에 마우스 안올렸을 때 */
		$('#awardlike_btn').mouseleave(function(){
			$('#award_like_img').attr("src","<%=request.getContextPath()%>/images/award_like_off.png");	
		});
		
		function fn_clickLike(){
			if(<%= logginMember!=null %>) {
				$.ajax({
					url:'<%= request.getContextPath() %>/award/awardLike',
					data : 'awardNo=<%= a.getAwardNo() %>&userId=<%=logginMember!=null?logginMember.getMemberEmail():'a'%>',
					dataType : 'text',
					success : function(data) {
						console.log(data);
					}
				});
			} else {
				alert('회원만 추천가능합니다. 로그인 후 이용하세요!');
			}
		}
		
		
		
		
		/* ============================= */
		
		$(function(){
			$(function(){
		
				$(".btn-delete").on('click',function(){
					if(!confirm("댓글을 삭제하시겠습니까?"))
					return;
					{
						location.href='<%=request.getContextPath()%>/award/awardCommentDelete?awardNo=<%=a.getAwardNo()%>&deleteNo='+ $(this).val();}
				
			})
		})

	});
</script>



<%@ include file="/views/common/footer.jsp"%>