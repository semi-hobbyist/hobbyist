<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<script>
IMP.request_pay({
    /* ...중략... */
}, function (rsp) { // callback
    if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
        // jQuery로 HTTP 요청
        jQuery.ajax({
            /* ...중략... */
        }).done(function(data) { // 응답 처리
            switch(data.status) {
                case: "vbankIssued":
                    // 가상계좌 발급 시 로직
                    break;
                case: "success":
                    // 결제 성공 시 로직
                    break;
            }
        })
    } else {
        alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
    }
}
});
</script>
</body>
</html>