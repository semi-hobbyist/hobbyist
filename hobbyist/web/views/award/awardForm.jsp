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
							<td> 
								<!-- <input type="file" name="up_file"> -->
								<div class="inputImageBox">
                     <div class="inputImageback">
                        <div class="inputImage">
                           <img src="<%= request.getContextPath() %>/images/uploadIcon.png" width="100px" height="100px"/>
                           <div class="imageTitle"></div>
                        </div>
                        <input type="file" name="up_file" class="inputImageFile"/>
                     </div>
                  </div>
								
							</td>		
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea id="noticeContent_editor" rows="5" cols="50" name="content"></textarea></td>
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
		var content = $('[name=noticeContent_editor]').val();
		if (content.trim().length == 0) {
			alert("내용을 입력하세요");
			return false;
		}
		return true;
	}
	
	
	 $(function () {
         $("input[name='up_file']").change(function (event) {
            $(".inputImage img").attr("src", URL.createObjectURL(event.target.files[0]));
            $(".inputImage img").css({"width":"300px","height":"200px"});
            var fileName = $("input[name='up_file']").val();
            $(".imageTitle").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
         })
      })
	
	 //textarea css 가져오기
    CKEDITOR.replace('noticeContent_editor');
    CKEDITOR.add
</script>


<%@ include file="/views/common/footer.jsp"%>