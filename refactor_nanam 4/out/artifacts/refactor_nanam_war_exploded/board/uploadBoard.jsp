<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp"%>
<link rel="stylesheet" href="${nanam}/css/inputNotice.css">

<script>

	function writeBoard(){

		let id = $("input[name='id']").val();
		let login_id = $("input[name='login_id']").val();
		let list = $("select[name='list']").val();
		let subject = $("input[name='subject']").val();
		let content = $("textarea[name='content']").val();

		let writeInfo = {
			"list":list,
			"subject":subject,
			"content":content,
			"id" : id,
			"login_id":login_id
		}


		$.ajax({
			type: 'GET',
			dataType: 'JSON',
			url: "/board/writeOfficialBook",
			data: {"writeInfo" : JSON.stringify(writeInfo)},
			success: function (data) {
				console.log("request succeeded");
				console.log(data)

				let htmls = "";

				let listFlag =data.BOARD_LIST;

				htmls += "<form name='moveForm' action='/board/officialBoardList/"+listFlag+"' method='get'>";
				htmls += "<input type='text' name='list' value='"+data.BOARD_LIST+"' style='display: block'/>" ;
				htmls += "<input type='text' name='returnFlag' value='"+data.result+"' style='display: block'/>" ;
				htmls += "<input type='submit' value='쓴글 확인하러가기' />" ;
				htmls += "</from>";

				$("#confirmBoard").append(htmls);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				console && console.log("request failed");
			},
		});

	}
</script>

<div id="noticeInputWrap">
	<div id="noticeInputBox">
		<div class="basebar">
			<ul>
				<li><a href="#">메인</a></li>
				<li>></li>
				<li><a href="#">출판</a></li>
				<li>></li>
				<li><a href="#">관리자 페이지</a></li>
			</ul>
		</div>

		<form name="moveForm" action="officialBoardDetail.jsp" method="get"></form>
		<div id="sForm">
			<div id="noticeListWrap">
				<div id="choiceNotice" class="caption">
					<h2>업로드 게시판</h2>
					<select style="width: 150px;" name="list" id="list">
						<option value='essay'>발행인 에세이</option>
						<option value='nanam'>언론이 본 나남</option>
						<option value='notice'>공지사항</option>
					</select>
				</div>
				<div id="inputTitle" class="caption">
					<p>제목</p>
					<input type="text" id="subject" class="write" name="subject">
				</div>
			</div>

			<div id="inputBody" class="caption">
				<div id="bodyArea">
					<p>본문</p>
					<textarea id="content" name="content" class="write" cols="40" rows="8"></textarea>
				</div>
				<div id="inputBtnWrap">
					<input type="button" class="inputBtn" id="onList" value="등록"  onclick="writeBoard();" />
					<input type="button" class="inputBtn" value="취소 "/>
					<input type="text" name = 'id'  value="${sessionScope.id}" style="display: none">
					<input type="text" name = 'login_id'  value="${sessionScope.login_id}" style="display: none">

					<div id="confirmBoard">

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../main/footer.jsp"%>
