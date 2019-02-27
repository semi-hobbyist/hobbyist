<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@page import="com.hobbyist.board.model.vo.BoardComment"%>
<%@page import="com.hobbyist.writer.model.vo.WriterEnroll"%>
	
<%
	boolean weFlag = false;
	if(request.getAttribute("weFlag")!=null) {
		weFlag = (boolean)request.getAttribute("weFlag");
	}
%>

<script>

	$.ajax({
		url: '<%=request.getContextPath()%>/mypage/myBoardCount',
		success: function(data) {
			var nums = data.split(",");
			
			$('#bCount').html(nums[0]+" 개");
			$('#bcCount').html(nums[1]+"개");
			console.log(nums[0]);
			
		}
	});

</script>

<div class="myPage_left">
   <div class="memberSimpleProfileBox">
      <div class="memberSimpleProfileLine1">
         <div class="memberSimpleProfileImgBox">
            <img src="<%= request.getContextPath() %>/upload/member/<%= logginMember.getMemberRenamedImage() %>">
         </div>
         <div class="memberSimpleProfileLine1Text">
            <div>
               <div class="memberSimpleProfileNickname"><%= logginMember.getMemberNickname() %></div>
               <div class="memberSimpleProfile">
                 	 가입일<br>
                  <%= logginMember.getMemberEnrolldate() %>
               </div>
            </div>
         </div>
      </div>
      <div class="memberSimpleProfileLine2">
         <div class="memberSimpleProfileTal">
            <div class="memberSimpleProfileTalTitle">내 포인트</div>
            <div class="memberSimpleProfileTalContent">100p</div>
         </div>
         <div class="memberSimpleProfileTal">
            <div class="memberSimpleProfileTalTitle">내가 쓴 글 보기</div>
            <div class="memberSimpleProfileTalContent" id="bCount"></div>
         </div>
         <div class="memberSimpleProfileTal">
            <div class="memberSimpleProfileTalTitle">내가 쓴 댓글 보기</div>
            <div class="memberSimpleProfileTalContent" id="bcCount"></div>
         </div>
      </div>
   </div>
   <ul>
      <li onclick="fn_myclass()">내 클래스</li>
      <li>내 커뮤니티</li>
	     <ul class="sub">
         	<li onclick="location.href='<%=request.getContextPath()%>/mypage/myBoardConfirm?nickName=<%=logginMember.getMemberNickname()%>'">나의 게시물</li>
         	<li onclick="location.href='<%=request.getContextPath()%>/mypage/myBoardCommentConfirm?nickName=<%=logginMember.getMemberNickname()%>'">나의 댓글</li>
   	  	</ul>
      <li onclick="location.href='<%=request.getContextPath()%>/mypage/myOrderList?member=<%=logginMember.getMemberEmail()%>'">내 주문내역</li>
      <li>내 정보보기</li>
      <ul class="sub">
         <li onclick="fn_updateMember()">내 정보 수정</li>
         <li onclick="fn_changePwd()">비밀번호 수정</li>
      </ul>
      <% if(weFlag) { %>
		<li onclick="location.href='<%= request.getContextPath() %>/mypage/mypage_writerEnrollList'">작가신청</li>
      <% } %>
   </ul>
</div>

<script>
	function fn_myclass() {
	    if (<%= logginMember != null %>) {
	        location.href = '<%= request.getContextPath() %>/myClass?member=<%= logginMember!=null? logginMember.getMemberEmail() : "" %>';
	    } else {
	        swal("[내 클래스] 로그인 후 이용해주세요");
	        return;
	    }
	}

   $(function () {
      var myPageLeftSelectValue = "";
      $(".myPage_left>ul>li").click(function () {
         if ($(this).html() == myPageLeftSelectValue) {
            myPageLeftSelectValue = $(this).html();
            $(this).next("ul").slideToggle(500);
         } else {
            myPageLeftSelectValue = $(this).html();
            $(".myPage_left>ul>li").next("ul").slideUp(500);
            $(this).next("ul").slideToggle(500);
         }
      })
   })

   $(window).scroll(function () {
      var target = $('#myPage_top');
      var scrollTo = $(window).scrollTop();
      if (scrollTo != 0) {
         $('#myPage_top').css("top", "97px");
      } else {
         $('#myPage_top').css("top", "163px");
      }
   });

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
   })

</script>

<script>
	function fn_updateMember(){
		location.href="<%=request.getContextPath()%>/memberUpdateView.do";
	}
	
	function fn_changePwd(){
		location.href="<%=request.getContextPath()%>/changePwdView.do";
	}
</script>