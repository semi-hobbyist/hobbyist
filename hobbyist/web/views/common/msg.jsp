<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String msg = (String)request.getAttribute("msg");
   		String loc = (String)request.getAttribute("loc");
    %>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		swal('<%=msg%>');
		location.href="<%=request.getContextPath()%><%=loc%>";
	</script>
</body>
</html>