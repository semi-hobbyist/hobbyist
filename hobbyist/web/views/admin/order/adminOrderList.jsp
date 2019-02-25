<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.order.model.vo.Order, com.hobbyist.shop.model.vo.Cate" %>

<%	
	List<Order> list = (List)request.getAttribute("List");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	int totalCount = (Integer)request.getAttribute("TotalCount");
	String pageBar = (String)request.getAttribute("pageBar");
	String sort = (String)request.getAttribute("sort");
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if(<%= logginMember!=null && logginMember.getMemberEmail().equals("admin") %>) {
		
	} else {
		alert('관리자만 접근 가능합니다');
		location.href=history.back(-1);
	}
</script>

<section id="admin">
	<div class="admin_content">
		<div class="admin_top" id="admin_top">
			<ul>
				<li onclick="location.href='#'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">Order List | 주문목록</li>
				<li onclick="location.href='#'">관리자페이지 > 주문 관리 > 주문 목록</li>
			</ul>
			
		</div>
		<br>
		
		<!-- 관리자메뉴는 중복되기때문에 adminShop_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminShop_right">
			<div id="adminShop_main">
					<table>
						<tr>
							<td colspan="9"><h3>주문관리 목록</h3></td>
						</tr>
						<tr>
							<td colspan="9" style="text-align:center;">총 ( <%= totalCount %> ) 건의 클래스 목록</td>
						</tr>
						<tr>
							<th style="width:90px;">주문번호</th>
							<th style="width:80px;">분류</th>
							<th style="width:70px;">회원</th>
							<th style="width:80px;">주문클래스</th>
							<th style="width:100px;">주문 옵션</th>
							<th style="width:80px;">가격</th>
							<th style="width:90px;">결제방법</th>
							<th style="width:90px;">주문일</th>
							<th>환불처리</th>
						</tr>
						<% for(Order o : list) { %>
							<tr>
								<td><%= o.getOrderNo() %></td>
								<td><%= o.getOrderCate() %></td>
								<td><%= o.getOrderMember() %></td>
								<td><%= o.getOrderClass() %></td>
								<td><%= o.getOrderClassOption() %></td>
								<td><%= o.getOrderPrice() %></td>
								<td><%= o.getOrderType() %></td>
								<td><%= o.getOrderDate() %></td>
								<td><button type="button" onclick="fn_cancle('<%= o.getOrderRnum() %>')">환불</button>
							</tr>
						<% } %>
						<tr>
							<td colspan="9" class="pageBar"><%= pageBar %></td>
						</tr>
					</table>
					<script>
						function fn_cancle(a) {
							if(confirm('해당상품을 환불처리 하시겠습니까?')) {
								$.ajax({
									url : '<%= request.getContextPath() %>/admin/adminOrderCancle?code=' + a,
									success : function (data) {
										alert(data);
										location.href="<%=request.getContextPath() %>/admin/adminOrderList";
									}
								});
							}
						}
					
					</script>
				</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>