<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@ include file="../main/header.jsp"%>
    
<link rel="stylesheet" href="${nanam}/css/inputnotice.css">
<link rel="stylesheet" type="text/css" href="${nanam}/css/detailBoard.css">
      
<c:set var="b" value="${b }" />
<form id="sForm" action="/nanam/board/deleteBoardMain" method="get">
	<div class="basebar">
		<ul>
			<li>
				<a href="#">메인</a>
			</li>
			<li>></li>
			<li>
				<a href="#">출판</a>
			</li>
			<li>></li>
			<li>
				<a href="#">에세이</a>
			</li>
		</ul>
	</div>
	<div id="noticeInputWrap">
		<div id="noticeInputBox">
			<div class="basebar">
			<ul>
				<li><a href="#">메인</a></li>
				<li>></li>
				<li><a href="#">출판</a></li>
				<li>></li>
				<li><a href="#">공지사항</a></li>
			</ul>
		</div>
			<div id="noticeListWrap">
				<div>
					<label for="qseq" > 삭제할 qseq</label>
					<input type="text" id="qseq" name="qseq" value="${b.qseq }" readonly/>
					<label >삭제할 글 제목</label>
					<input type="text" id="subjecta" class="write" name="subject" value="${b.subject }" readonly>
					<label >삭제할 글 내용</label>
					<input type="text" id="contenta" class="write" name="content" value="${b.content }" readonly>
				</div>
				<div id="choiceNotice" class="caption">
					<p>수정 게시판</p>
				</div>
				<div id="inputTitle" class="caption">
					<p>제목</p>
					<div id="inputBtnWrap">
						<button type="submit" class="inputBtn" id="onList" >삭제</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<%@ include file="../main/footer.jsp"%>