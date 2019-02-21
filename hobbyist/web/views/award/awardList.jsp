<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.award.model.vo.Award"%>
<%@ include file="/views/common/header.jsp"%>

<%
	List<Award> list = (List) request.getAttribute("list");
	//String keyword = (String) request.getAttribute("keyword");
	String pageBar = (String) request.getAttribute("pageBar");
%>

<section id="award">
	<div class="award_content">
		<div id="award_left"></div>


		<div id="award_right">
			<b>Award</b>|&nbsp;&nbsp;메인 > 하비스트 어워드
			<div class="award_banner"></div>

			<div class="award_top">
				<form id="searchFrm" name="searchFrm" action=""></form>
				<div class="sort">
					<input type="radio" id="sort" name="sort" value="">
					<div id="sort1" class="sortBtn" name="descEnroll"
						onclick="fn_sortBtn(this)">최근등록순</div>
					<input type="radio" id="sort" name="sort" value="">
					<div id="sort2" class="sortBtn" name="descLike"
						onclick="fn_sortBtn(this)">♡ 좋아요 순</div>
				</div>
				<div class="searchForm">
					<input type="search" placeholder="게시물 검색하세요 ♡" name="keyword"
						id="keyword" value="">
					<button type="submit">
						<img alt="" src="<%=request.getContextPath()%>/images/search.png"
							width="70%">
					</button>
				</div>
			</div>

			<div class="award_middle">
			
			<%for(Award a: list){ %>
				<div class="award_imgbox">
					<div class="award_img_hover"></div>
					<a href="<%= request.getContextPath() %>/award/awardView?awardNo=<%=a.getAwardNo()%>"><img src="<%=request.getContextPath()%>/upload/award/images/<%=a.getAwardOriginalFilename()%>" width=200px height=200px></a>
				</div>
			<% } %>
				</div>
		<div class="award_bottom"><%=pageBar%></div>
		<div class="award_bottom2">
			<input type="button" value="어워드 참여하기" id="btn=add" onclick="fn_awardForm()">
			
		</div>
			</div>
		</div>

		
		<script>
		function fn_awardForm(){
			location.href="<%=request.getContextPath()%>/award/awardForm";
		}
		</script>
		


	
</section>

<%@ include file="/views/common/footer.jsp"%>
