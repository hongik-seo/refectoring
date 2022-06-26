<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp"%>
    
<c:set var="book" value="${book}" />

<link rel="stylesheet" type="text/css" href="${nanam}/css/inputBook.css">

<!--인풋전체 영역-->

<!--인풋전체 영역-->
<div id="bookAllWrap">
		<div class="basebar">
			<ul>
				<li><a href="#">메인</a></li>
				<li>></li>
				<li><a href="#">출판</a></li>
				<li>></li>
				<li><a href="#">관리자 페이지</a></li>
			</ul>
		</div>
	<form  action="/nanam/book/updateBookMain" method="get" >
		<div id="bookWrap">
			<h2>업로드 책 글쓰기</h2>
			<div>
				<label for="ISBN" > ISBN</label>
				<input name="ISBN" id="ISBN" value="${book.ISBN }" readonly/>
				
				<select style="width: 150px;" name="bookList" id="list" >
					<c:if test="${book.bookList eq 'hot'}">
						<option value='hot'>새로나온 책</option>
					</c:if>
					 <c:if test="${book.bookList eq 'steady'}">
						<option value='steady'>스테디 셀러</option>
					</c:if>
					<c:if test="${book.bookList eq 'nBook'}">
						<option value='nBook'>나남의 책들</option>
					</c:if>
				</select>
			</div>
		</div>
		<div  class="caption">
			<label for="title">책 제목</label>
			<input type="text" id="title" class="write" name="bookTitle">
			<br>
			<label for="author">책 저자</label>
			<input type="text" id="author" class="write" name="bookAuthor">
			<br>
			<label for="price">책 가격</label>
			<input type="text" id="price" class="write" name="bookPrice">
			<br>
			<label for="size">책 판형</label>
			<input type="text" id="size" class="write" name="bookSize">
			<br>
			<label for="page">책 면수</label>
			<input type="text" id="page" class="write" name="bookPage">
			<br>
			<label for="date">발행일</label>
			<input type="date" id="date" class="write" name="bookDate">
			<br>

			<p>본문</p>

			<textarea id="content" name="bookContent" class="write" cols="40" placeholder=${book.bookContent }rows="8"></textarea>

			<p>목차 </p>
			<textarea id="content" name="bookIndex" class="write" cols="40" 	 placeholder=${book.bookIndex }rows="8"></textarea>

			<p>저자소개 </p>
			<textarea id="content" name="bookAuthorIntroduce" class="write" cols="40"   placeholder=${book.bookAuthorIntroduce }rows="8"></textarea>
					
			<br>
			<input type="file" id="file" class="write" name="file" accept="image/*" />
			<br>
			<button type="submit" class="inputBtn" >등록</button>
			<button type="reset" class="inputBtn">취소</button>
		</div>
	</form >
</div>
<%@ include file="../main/footer.jsp"%>
