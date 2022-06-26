<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="../main/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${nanam}/css/notice.css">

<link rel="stylesheet" type="text/css" href="${nanam}/css/notice.css">
<div class="basebar">
	<ul>
		<li><a href="#">메인</a></li>
		<li>></li>
		<li><a href="#">출판</a></li>
		<li>></li>
		<li><a href="#">공지사항</a></li>
	</ul>
</div>
<h2>공지사항 페이지 입니다.</h2>

<form action="/board/officialBoardList/notice" method="get">
	<div id="tableWrap" style="height: 600px;">
		<table id="noticeTable">
			<thead>
			<tr>
				<th>글번호</th>
				<th class="caption">제목</th>
				<!--<th>콘텐츠</th>!-->
				<th>등록일</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="content" items="${contentList.content}">
				<tr class="caption">
					<td>${content.rn }</td>
					<td><a href="/board/boardControl?list=${content.boardList}&subject=${content.boardSubject}&seq=${content.boardSeq}&rn=${content.rn}&flag=SELECT">${content.boardSubject}</a></td>
					<td>${content.insertDate}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="inHere" style="display: inline; width: 600px; margin: 0 auto" >
		<ul class='pagination' style="display: flex; flex-wrap: nowrap; justify-content: center;">
			<c:if test="${forList.pageMaker.prev}">
				<li style='list-style: none' class='paginate_button previous'>
					<a href='/board/officialBoardList/notice?pageNum=${forList.pageMaker.startPage -1 }'>Previous</a>
				</li>
			</c:if>
			<c:forEach var='num'   begin='${forList.pageMaker.startPage}' end='${forList.pageMaker.endPage }'>
				<li  style='list-style: none'  class='paginate_button'   >
					<c:choose>
						<c:when test="${num == forList.pageMaker.pageNum}">
							<span style="color: red;">${num}</span>
							<input name="pageNum" value="${num}" style="display: none"/>
							<input name="list" value="notice" style="display: none"/>
						</c:when>
						<c:otherwise>
							<a class="blockLink" style="padding: 0 10px;" href="/board/officialBoardList/notice?pageNum=${num}&amount=${forList.pageMaker.amount }">${num}</a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
			<c:if test="${forList.pageMaker.next}">
				<li style="list-style: none" class="paginate_button next">
					<a href="/board/officialBoardList/notice?pageNum=${forList.pageMaker.endPage +1 }">Next</a>
				</li>
			</c:if>
		</ul>
	</div>


</form>

<%@ include file ="../main/footer.jsp"%>