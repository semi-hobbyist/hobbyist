<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<div id="defaultViewModal" class="defaultViewModal">
	<div class="defaultViewModalBox">
		<button type="button" class="defaultViewClose">X</button>
		<div class="defaultViewHeader">
			<div>
			</div>
		</div>
		<div class="defaultViewBody" id="noticeDelListView">
			
		</div>
		<div class="defaultViewfooter">
			<div>
				<button type="button" class="button1" id="defaultViewfooterMo">수정하기</button>
				<button type="button" class="button1" id="defaultViewfooterRe">복구하기</button>
				<button type="button" class="button1" id="defaultViewfooterDel">삭제하기</button>
			</div>
		</div>
	</div>
</div>
<script>
	
	// 작가신청 버튼 기능 구현
	function fn_writerEnrollBtn() {
		alert("관리자페이지에서는 작가신청 페이지로 들어갈 수 없습니다.");
	}


	// function fn_defaultViewAjax(index) {
	// 	$.ajax({
	// 		url: '<%= request.getContextPath() %>/notice/noticeDelListViewAjax.do',
	// 		type: 'POST',
	// 		dataType: 'text',
	// 		data: { "noticeNo": index },
	// 		success: function (data) {
	// 			var noticeDelListView = $("#noticeDelListView");
	// 			noticeDelListView.empty();
	// 			noticeDelListView.html(data);

	// 			$("#defaultViewfooterMo").attr("onclick", "fn_noticeListMo(" + index + ")");
	// 			$("#defaultViewfooterRe").attr("onclick", "fn_noticeListRe(" + index + ")");
	// 			$("#defaultViewfooterDel").attr("onclick", "fn_noticeDB_Del(" + index + ")");
	// 		}
	// 	})

	// 	$("#defaultViewModal").css("display", "flex");
	// 	$(".defaultViewBody").animate({ scrollTop: 0 }, 0);
	// }

	$(".talBox_middle").on("click",".tal_ContentBox",function() {
		var index = $(".tal_ContentBox").index(this);

		console.log($(".selectView").get(index));
		
	})



	
	$(function () {
		$(".defaultViewClose").click(function () {
			$(".defaultViewModal").css("display", "none");
		})
	})
						
	function fn_fileDown(rName, oName) {
		rName = encodeURIComponent(rName);
		oName = encodeURIComponent(oName);
		location.href = "<%= request.getContextPath() %>/notice/noticeFileDownload?rName=" + rName + "&oName=" + oName;
	};

</script>
