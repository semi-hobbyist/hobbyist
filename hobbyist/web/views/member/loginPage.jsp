<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Cookie[] cookies = request.getCookies();
	String saveEmail = null;
	if(cookies != null) {
		for(Cookie c : cookies) {
			System.out.println("c : "+ c.getName() +" "+ c.getValue());
			String key = c.getName();
			String value = c.getValue();
			if(key.equals("saveEmail")) {
				saveEmail = value;
				break;
			}
		}
	}

%>
<script>

</script>

<div id="loginSection">
	<div class="container">
		<div class="message signup">
			<div class="btn-wrapper">
				<button class="button" id="signup">로그인</button>
				<button class="button" id="login">회원가입</button>
			</div>
		</div>
		<form action="<%= request.getContextPath() %>/member/loginMember" id="loginMember" method="post"
			onsubmit="return fn_loginMember();" autocomplete="off">
			<div class="form form--memberLogin">
				<div class="form--heading">환영합니다~^ㅇ^!</div>
				<input type="text" class="inputText" name="memberEmail" placeholder="Email" value="<%= saveEmail!=null?saveEmail:"" %>" autocomplete="off">
				<input type="password" class="inputText" name="memberPassword" placeholder="Password" autocomplete="new-password">
				<div class="checkboxDiv">
					<input type="checkbox" name="saveEmail" id="saveEmail" <%= saveEmail!=null?"checked":"" %> />
					<label for="saveEmail">email 저장</label>
				</div>
				<input type="submit" class="button" value="Login" />
			</div>
		</form>

		<form action="<%= request.getContextPath() %>/member/memberEnroll" id="memberEnroll" method="post"
				onsubmit="return fn_memberEnroll();" autocomplete="off">
			<div class="form form--memberEnroll enroll signup">
				<div class="form--heading">회원 가입</div>
				<input type="text" class="inputText" name="memberEmail" placeholder="Email">
				<input type="password" class="inputText" name="memberPassword" id="memberPassword" placeholder="Password">
				<input type="password" class="inputText" name="confirmPassword" id="confirmPassword" placeholder="Confirm password">
				<input type="button" id="signupStart" class="button" value="다음" />
			</div>
			<div class="form form--memberEnroll enroll1 signup">
				<div class="memberImage">
					<div class="inputBackground">
						<input type="file" class="inputImage" name="memberOriginalImage"/>
					</div>
					<img alt="camaraIcon" src="<%= request.getContextPath() %>/images/camaraIcon30.png" width="30px" height="30px"/>
				</div>
				<input type="text" class="inputText" name="memberNickname" placeholder="NickName"/>
				<input type="text" class="inputText" name="memberName" placeholder="Name"/>
				<input type="text" class="inputText" name="memberBirthday" placeholder="Birthday"/>
				<input type="text" class="inputText" name="memberPhone" placeholder="Phone"/>
				<div id="selectBtn">
					<input type="button" id="beforeBtn" class="beforeBtn" value="이전" />
					<input type="submit" class="nextBtn" value="완료" />
				</div>
			</div>
		</form>
	</div>
</div>
<script>
	/* 기능 구현 */
	function fn_loginMember() {
		return true;
	}
	function fn_memberEnroll() {
		return true;
	}

	/* 애니메이션 기능 */
	$("#signup").click(function () {
		$(".message").css("transform", "translateX(100%)");
		$(".enroll").css("transform", "translateX(0)");
		if ($(".message").hasClass("login")) {
			$(".message").removeClass("login");
		}
		$(".message").addClass("signup");
	});

	$("#login").click(function () {
		$(".message").css("transform", "translateX(0)");
		$(".enroll").css("transform", "translateX(0)");
		if ($(".message").hasClass("login")) {
			$(".message").removeClass("signup");
		}
		$(".message").addClass("login");
	});

	$("#signupStart").click(function () {
		$(".enroll").css("transform", "translateX(-200%)");
	});

	$("#beforeBtn").click(function () {
		$(".enroll").css("transform", "translateX(0)");
	});

	$("#nextBtn").click(function () {
		$(".enroll1").css("display", "flex");
		$(".enroll").css("transform", "translateX(0%)");
	});
</script>
</div>
<script>
	/* 로그인 창 닫기 분기 처리 */
	$(function () {
		var closeFlag = true;
		$("#loginSection .container").click(function () {
			closeFlag = false;
		})
		$("#loginSection .container").mouseleave(function () {
			closeFlag = true;
		})
		$("#loginSection").on("click", function () {
			if (closeFlag) {
				location.href = "<%= request.getContextPath() %>/member/loginPage";
			}
		})
	})
</script>


<!-- 회원가입 창 띠우기 -->
<%
	//회원가입이 실패했을때 변수값을 받아서 처리
	boolean signupWindow = false;
	if(request.getAttribute("signupWindow")!=null) {
		signupWindow = (boolean)request.getAttribute("signupWindow");
	}
%>
<% if(signupWindow) { %>
<script>
	$(".message").css("transform", "translateX(0)");
	$(".enroll").css("transform", "translateX(0)");
	if ($(".message").hasClass("login")) {
		$(".message").removeClass("signup");
	}
	$(".message").addClass("login");
</script>
<% } %>