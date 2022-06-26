<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    


<%@ include file="../main/header.jsp"%>
    
<link rel="stylesheet" href="${nanam}/css/inputnotice.css">
<link rel="stylesheet" type="text/css" href="${nanam}/css/detailBoard.css">
      
<form id="sForm" action="/nanam/book/deleteBookMain" method="get">
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
				<label for="ISBN" > 삭제할 책의 ISBN</label>
				<input type="text" id="ISBN" name="ISBN" value="${book.ISBN }" readonly/>
				<label for="bookTitle">삭제할 책제목</label>
				<input type="text" id="bookTitle" class="write" name="bookTitle" value="${book.bookTitle }" readonly>
			</div>
				<div id="choiceNotice" class="caption">
					<p>책 삭제</p>
				<div id="inputBtnWrap">
					<button type="submit" class="inputBtn" id="onList" >삭제</button>
				</div>
			</div>
			</div>
		</div>
	</div>
</form>

<%@ include file="../main/footer.jsp"%>