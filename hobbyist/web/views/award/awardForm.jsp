<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.award.model.vo.Award"%>
<%@ include file="/views/common/header.jsp"%>
<%
	Award a = (Award) request.getAttribute("award");
%>
<section id="award">
	<div class="award_content">
		<div id=award_right>
			<b>Award</b>|&nbsp;&nbsp;메인 > 하비스트 어워드 > 게시물 작성
			<div class="award_top"></div>
			<div class="award_middle">
				<table id="tbl-awardForm">
					<h2>어워드 게시판 작성</h2>
					<form action="<%=request.getContextPath()%>/award/awardFormEnd"
						method="post" enctype="multipart/form-data">
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" id="title" required></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer"
								value="<%=logginMember != null ? logginMember.getMemberEmail() : ""%>"
								readOnly></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td><input type="file" name="up_file"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea rows="5" cols="50" name="content"></textarea></td>
						</tr>
						
						<tr>

							<td colspan="2"><input type="submit" value="게시글 등록"
								onclick="return fn_validate();"></td>
						</tr>
				</table>
				</form>
			</div>
		</div>


	</div>
</section>
<script>
	function fn_validate() {
		var content = $('[name=content]').val();
		if (content.trim().length == 0) {
			alert("내용을 입력하세요");
			return false;
		}
		return true;
	}
</script>


<%@ include file="/views/common/footer.jsp"%>