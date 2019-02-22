<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.myclass.model.vo.MyClass, com.hobbyist.shop.model.vo.Shop" %>

<%
	List<Notice> list = (List)request.getAttribute("list");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
%>

<%@ include file="/views/common/header.jsp" %>

<section id="notice">
	<div class="notice_content">
		<div class="notice_TalBox">
			<div class="talBox_top">
				<form id="searchFrm" name="searchFrm" action="javascript:fn_searchFrm();" method="POST">
						<div class="tal_top">
							<div class="sort">
								<input type="hidden" id="sort" name="sort" value="<%= sort!=null? sort : "" %>"/>
								<div id="sort1" class="sortBtn" name="descEnroll" value="descEnroll" onclick="fn_sortBtn(this)" >최근등록순</div>
								<div id="sort2" class="sortBtn" name="ascEnroll" value="ascEnroll" onclick="fn_sortBtn(this)" >이전등록순</div>
							</div>
							<div class="searchForm">
								닉네임명&nbsp;&nbsp;&nbsp;<input type="search" autocomplete="off" placeholder="Search..." name="keyword" id="keyword" value="<%= !keyword.equals("")? keyword : "" %>">
								<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png" width="70%"></button>
								<div id="ajaxSearch"></div>
							</div>
						</div>
						<div class="tal_Title">
							<div class="talT_noticeNo">번호</div>
							<div class="talT_noticeSort">분류</div>
							<div class="talT_noticeTitle">제목</div>
							<div class="talT_noticeWriter">작성자</div>
							<div class="talT_noticeDate">작성일</div>
							<div class="talT_noticeReadcount">조회수</div>
						</div>
					</form>
			</div>
			<div class="talBox_middle">
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				<div class="tal_Content" onclick="fn_WEViewAjax()">
					<div class="talC_noticeNo">10001</div>
					<div class="talC_noticeSort">이벤트</div>
					<div class="talC_noticeTitle">끝내는 주는 행사 놓치면 후회함</div>
					<div class="talC_noticeWriter">Hobbyist</div>
					<div class="talC_noticeDate">20190222</div>
					<div class="talC_noticeReadcount">20010</div>
				</div>
				
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>