<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Oneday" %>

<%
	Oneday oneday = (Oneday)request.getAttribute("Oneday");
%>

<%@ include file="/views/common/header.jsp" %>



<section id="onedayView">
	<div class="onedayView_content">
		<div class="view_right">
			<div class="view_top_info">작가명 :
				<%= oneday.getOnedayWriter() %> | 클래스코드 :
				<%= oneday.getOnedayNo() %>
			</div>
			<div class="view_top_left"><img src="<%=request.getContextPath()%>/upload/oneday/images/<%= oneday.getOnedayImage1() %>"
				 width="460px"></div>
			<div class="view_top_right">
				<table>
					<tr>
						<td colspan="2">
							<h2><%= oneday.getOnedayName() %></h2>
						</td>
					</tr>
					<tr>
						<td colspan="2"><p><%= oneday.getOnedayInfo() %></p></td>
					</tr>
					<tr>
						<td>예약</td>
						<td class="tdclass">
							예약현황<%= oneday.getOnedayCurrentPeople() %>/<b><%= oneday.getOnedayPeople() %></b> (명)
							<%= oneday.getOnedayReservationStatus().equals("Y") ?  "[예약가능]" : "[예약불가]" %>
						</td>
					</tr>
					<tr>
						<td>가격</td>
						<td class="tdclass">
							<%= oneday.getOnedayPrice() %>원</td>
					</tr>
					<tr>
						<td>날짜/장소</td>
						<td class="tdclass">
						<select name="class_option" id="class_option">
							<option>------------- 선택 -------------</option>
							<%= oneday.getOnedayOption1()!=null? "<option value=" + oneday.getOnedayOption1() + ">"+ oneday.getOnedayOption1() + "</option>" : " " %>
							<%= oneday.getOnedayOption2()!=null? "<option value=" + oneday.getOnedayOption2() + ">"+ oneday.getOnedayOption2() + "</option>" : " " %>
							<%= oneday.getOnedayOption3()!=null? "<option value=" + oneday.getOnedayOption3() + ">"+ oneday.getOnedayOption3() + "</option>"  :  " " %>
							<%= oneday.getOnedayOption4()!=null? "<option value=" + oneday.getOnedayOption4() + ">"+ oneday.getOnedayOption4() + "</option>": " " %>
							<%= oneday.getOnedayOption5()!=null? "<option value=" + oneday.getOnedayOption5() + ">"+ oneday.getOnedayOption5()+ "</option>" : " " %>
						</select>
						</td>
					</tr>
					<tr>
					<td colspan="2"><button class="api" id="kakao-link-btn" onclick="javascript:;">
							</button> 
							<button class="api" onclick="fn_shortUrl()">
							</button>
							<!-- ------------------ 카카오톡 공유 api ---------------- -->
							<script type='text/javascript'>
							  //<![CDATA[
							    // // 사용할 앱의 JavaScript 키를 설정해 주세요.
							    Kakao.init('90bce1ec4b05bd5a83a7026725712442');
							    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
							    Kakao.Link.createDefaultButton({
							      container: '#kakao-link-btn',
							      objectType: 'feed',
							      content: {
							        title: '<%= oneday.getOnedayName()  %>',
							        description: '<%= oneday.getOnedayInfo() %>',
							        imageUrl: 'http://hobbyists.ddns.net:8080/hobbyist/upload/oneday/images/<%= oneday.getOnedayImage1() %>',
							        link: {
							          mobileWebUrl: 'http://hobbyists.ddns.net:8080/hobbyist/oneday/onedayView?no=<%= oneday.getOnedayNo() %>' ,
							          webUrl: 'http://hobbyists.ddns.net:8080/hobbyist/oneday/onedayView?no=<%= oneday.getOnedayNo() %>'
							        }
							      }
							    });
							  //]]>
							</script>
							
							<!-- -------------공유/짧은글주소 등 api 를 위한 스크립트	----------->
				<script>
					function fn_shortUrl() {
						// 현재 페이지 주소 변수에 담아링~
						var CurrentUrl = window.location.href;
						// Ajax로 짧은글주소 서블릿에 현재주소를 넘겨줌
						$.ajax({
							url: '<%=request.getContextPath()%>/shop/shopShortUrl?urlText=' + CurrentUrl,
							dataType: 'json',
							success: function (data) {
								console.log(data);
								window.prompt("짧은글 주소로 변환되었습니다. \nCtrl + C 복사해서 사용하세요 \n ",  data.result['url']);
							}
						})
					}
				</script>
				<!-- -------------공유/짧은글주소 등 api 를 위한 스크립트 끝	-----------></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="addcart-btn" onclick="fn_addCart()">Add My Cart | 취미바구니 담기 </button>
							<script>
								function fn_addCart() {
									if(<%= logginMember != null %>) {
										location.href="<%=request.getContextPath()%>/myCartInsert?member=<%= logginMember != null? logginMember.getMemberEmail() : 'a'%>&classNo=<%=oneday.getOnedayNo()%>&cartCate=oneday&cartOption=" + $('#class_option').val();
									} else {
										alert('로그인 후 취미바구니 이용이 가능합니다');
									}
								}
							</script>
						</td>
					</tr>
				</table>
			</div>
			<br>
			<!-- 중간 고정메뉴 -->
			<div class="view_menu" id="view_menu">
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/oneday/onedayList';">←다른 원데이클래스 보기</li>
					<li id="sub1" onclick="location.href='#';">예약하기</li>
					<li id="sub2" onclick="location.href='#about';">원데이클래스 소개</li>
					<li id="sub3" onclick="location.href='#policy';">예약안내</li>
				</ul>
			</div>
			
			<!-- 상세내용시작 -->
			<div id="about">
				<%= oneday.getOnedayContent() %>
			</div>
			
				<script>
				$.ajax({
					url: '<%= request.getContextPath() %>/oneday/onedayReviewListAjax',
					data: 'review_class=<%=oneday.getOnedayNo()%>',
					success: function (data) {
						$('#review_tbl').html(data);
					}
				})
				
						function fn_reviewSubmit() {
							var rvContnt = $('[name=review_content]');
							if(rvContnt.val()=='') {
								alert('리뷰내용을 작성해주세요');
								rvContnt.focus();
								return false;
							} else {
								$.ajax({
								url: '<%= request.getContextPath() %>/oneday/onedayReviewWrite',
								data : $('#reviewFrm').serialize(),
								success: function (data) {
									$('#review_tbl').empty();
									$('#review_tbl').html(data);
								}
								})
							}
							
						}

					</script>
			<div id="policy">
					<%= oneday.getOnedayPolicy() %>
			</div>
		</div>
	</div>
</section>


<script>
	var sub1 = $('#sub1');
	var sub2 = $('#sub2');
	var sub3 = $('#sub3');

	var about = $('#about').offset().top - 100;
	var policy = $('#policy').offset().top;

	$(window).scroll(function() {
		var scrollTo = $(document).scrollTop();
		if (scrollTo > 400) {
			$('#view_menu').css({
				"z-index" : "9999",
				"top" : "200px",
				"position" : "fixed"
			});
		} else {
			$('#view_menu').css({
				"z-index" : "",
				"top" : "",
				"position" : ""
			});
		}

		if (scrollTo < about) {
			sub1.css({
				"background-color" : "#3b4f6a",
				"color" : "#fff"
			});
			sub2.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub3.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		} else if (scrollTo >= policy - 100) {
			sub1.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub2.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub3.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		}
	})
	
		//메인 헤더 숨기기
			 $(window).scroll(function () {
			    if ($(window).scrollTop() != 0) {
			       $("#header-middle").css("transition", "500ms");
			       $("#header-middle").css("height", "0px");
			       $("#header-middle .middle").css("display", "none");
			    } else {
			       $("#header-middle").css("height", "100px");
			       setTimeout(function () {
			          $("#header-middle .middle").css("display", "inline-block");
			       }, 500)
			    }
			 });

</script>

<%@ include file="/views/common/footer.jsp" %>