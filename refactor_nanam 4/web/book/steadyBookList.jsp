<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="../main/header.jsp"%>

<link rel="stylesheet" href="${nanam}/css/bookList.css">

<title>스테디셀러</title>

<form action="/book/bookList/steady" method="get">
<div id="newBooksWrap" class="booksWrap">
    <div id="newBooksBox" class="booksBox">
        <div class="basebar">
            <ul>
                <li>
                    <a href="#">메인</a>
                </li>
                <li>></li>
                <li>
                    <a href="#">책</a>
                </li>
                <li>></li>
                <li>
                    <a href="#">스테디 셀러</a>
                </li>
            </ul>
        </div>
        <div class="booksArea">
            <c:forEach var="content" items="${contentList.content}">
                <div class="list">
                    <table class="bookTable">
                        <tbody>
                        <tr>
                            <td>
                                <a title="책설명 보기" href="/book/bookControl?list=${content.bookList }&title=${content.bookTitleInfo }&author=${content.bookAuthorInfo }&price=${content.bookPrice}&isbn=${content.bookIsbn}&year=${content.bookPubYearInfo}&seq=${content.bookSeq}&flag=SELECT">
                                    <img src="${nanam}/images/steadybook_list/${content.bookImg}">
                                </a>
                                <div id="modify">
                                    <p class="title">${content.bookTitleInfo}</p>
                                    <p class="author">${content.bookAuthorInfo}</p>
                                    <p class="price">${content.bookPrice}원</p>
                                    <p class="publishYear">${content.bookPubYearInfo}</p>
                                    <p class="bookText">${content.bookContent}</p>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<div id="inHere" style="display: inline; width: 600px; margin: 0 auto" >
    <ul class='pagination' style="display: flex; flex-wrap: nowrap; justify-content: center;">
        <c:if test="${forList.pageMaker.prev}">
            <li style='list-style: none' class='paginate_button previous'>
                <a href='/book/bookList/steady?pageNum=${forList.pageMaker.startPage -1 }'>Previous</a>
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
                        <a class="blockLink" style="padding: 0 10px;" href="/book/bookList/steady?pageNum=${num}&amount=${forList.pageMaker.amount }">${num}</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
        <c:if test="${forList.pageMaker.next}">
            <li style="list-style: none" class="paginate_button next">
                <a href="/book/bookList/steady?pageNum=${forList.pageMaker.endPage +1 }">Next</a>
            </li>
        </c:if>
    </ul>
</div>
</form>
<%@ include file="../main/footer.jsp"%>