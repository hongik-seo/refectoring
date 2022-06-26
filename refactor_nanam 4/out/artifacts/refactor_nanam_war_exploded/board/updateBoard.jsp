<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@ include file="../main/header.jsp"%>
    
<link rel="stylesheet" href="${nanam}/css/inputnotice.css">
<link rel="stylesheet" type="text/css" href="${nanam}/css/updateBoard.css">
      
           
<c:set var="b" value="${b }"/>
        
<form id="sForm" action="/nanam/board/updateBoardMain" method="get">
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

			<div id="noticeListWrap">
				<div>
					<label for="qseq" > 수정할 qseq</label>
					<input type="text" id="qseq" name="qseq" value="${b.qseq }" readonly/>
					<label for="subject2">수정할 글 제목</label>
					<input type="text" id="subject2" class="write" name="subjecta" value="${b.subject }" readonly>
					<label for="content">수정할 글 내용</label>
					<input type="text" id="content2" class="write" name="contenta" value="${b.content }" readonly>
				</div>
				<div id="choiceNotice" class="caption">
					<h2>수정 게시판</h2>
				</div>
				<div id="inputTitle" class="caption">
					<p>제목</p>
					<input type="text" id="subject" class="write" name="subject" placeholder="${b.subject}" >
				</div>
			</div>
			<div id="inputBody" class="caption">
				<div id="bodyArea">
					<p>본문</p>
						<textarea id="content" name="content" class="write" cols="40" rows="8" placeholder="${b.content}"  ></textarea>
					</div>
				<div id="inputBtnWrap">
					<button type="submit" class="inputBtn" id="onList" >수정</button>
				</div>
			</div>
		</div>
	</div>
</form>
<%@ include file="../main/footer.jsp"%>