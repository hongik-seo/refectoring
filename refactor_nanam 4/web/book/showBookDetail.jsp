<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp"%>
<link rel="stylesheet" href="${nanam}/css/bookDetail.css">
<c:set  var="detailBook"  value="${detailBook}" />

<script>


    $(function(){

        let showAdmin = $(".showAdmin");

        for(let i = 0; i<showAdmin.length; i++){

            if ( "${sessionScope.login_id}" === 'scott' &&  "${sessionScope.login_pwd}" === 'tiger'){
                $($(".showAdmin").eq(i)).show();


            }else{

                $($(".showAdmin").eq(i)).hide();
            }

        }



    });


</script>
<script>

    function updateBook(){

        let htmls = "<input name ='flag' value = 'UPDATE'>"

        $("#inHere").append(htmls);

    }

    function deleteBook(){

        let htmls = "<input name ='flag' value = 'DELETE'>"

        $("#inHere").append(htmls);

    }

</script>



<div id="booksWrap" class="booksWrap" style="height: unset;">
    <div id="booksBox" class="booksBox" style="height: unset;">
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
                    <a href="#">서브페이지</a>
                </li>
            </ul>
        </div>
        <div id="booksInfo">
            <c:if test="${detailBook.bookList eq 'new'}">
                <img src="${nanam}/images/newbook_list/${detailBook.bookImg}">
            </c:if>
            <c:if test="${detailBook.bookList eq 'steady'}">
                <img src="${nanam}/images/steadybook_list/${detailBook.bookImg}">
            </c:if>
            <c:if test="${detailBook.bookList eq 'nanam'}">
                <img src="${nanam}/images/nanambook_list/${detailBook.bookImg}">
            </c:if>
            <div id="tableWrap">
                <table id="infoBookTable" >
                    <thead>
                    <tr>
                        <th colspan="2">
                            <h2 class="title">${detailBook.bookTitleInfo}</h2>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td  class="author">${detailBook.bookAuthorInfo}</td>

                        </tr>
                        <tr>
                            <td class="color">
                                판매가
                            </td>
                            <td class="price color">${detailBook.bookPrice}</td>

                        </tr>
                        <tr>
                            <td>
                                발행일
                            </td>
                            <td class="publishYear">${detailBook.bookPubYearInfo}</td>

                        </tr>
                        <tr>
                            <td>
                                ISBN
                            </td>
                            <td class="ISBN">${detailBook.bookIsbn}</td>

                        </tr>
                    </tbody>
                </table>


                <c:if test="${!empty sessionScope.login_id}">
                <ul id="btnWrap">
                     <li>
                         <a title="책구매하기" >구매하기</a>
                     </li>
                     <li>
                         <a title="메인페이지로 가기" href="/main/home.jsp">메인페이지로</a>
                     </li>
                     <li>
                         <a title="장바구니 담기" href="/cart/cartFront?id=${sessionScope.id}&bookIsbn=${detailBook.bookIsbn}&userId=${sessionScope.login_id}&bookList=${detailBook.bookList}&bookSeq=${detailBook.bookSeq}&flag=INSERT">장바구니</a>
                     </li>
                </ul>
                </c:if>
            </div>
        </div>
        <div id="bookDetailWrap">
            <div class="detailBox">
                <ul>
                    <li class="informationBook">책내용</li>
                    <li class="borderColor" style="width : 0px;height: 0px"></li>
                </ul>
                <div class="bookSummery">
                    <p>${detailBook.bookContent}</p>

                </div>
            </div>
            <div class="detailBox">
                <ul>
                    <li class="informationBook">목차</li>
                    <li class="borderColor" style="width : 0px;height: 0px"></li>
                </ul>
                <div class="bookSummery">
                    <p>${detailBook.bookIndex }</p>

                </div>
            </div>
            <div class="detailBox">
                <ul>
                    <li class="informationBook">저자소개</li>
                    <li class="borderColor" style="width : 0px;height: 0px"></li>
                </ul>
                <div class="bookSummery">
                    <p>${detailBook.bookAuthorIntro }</p>

                </div>
            </div>
        </div>
    </div>
</div>



<form action="/book/bookControlConceal" method="post">
    <c:if test="${sessionScope.login_id eq 'scott' and sessionScope.login_pwd eq 'tiger'}">
        <div id="subNoticeBtnWrap">
            <input style="display: block" type="text" name="seq" value="${detailBook.bookSeq}" readonly="readonly"/>
            <input style="display: block" type="text" name="list" value="${detailBook.bookList}" readonly="readonly"/>

        </div>
        <div id="inHere">

        </div>


    <div>
        <p>책가격</p>
        <input style="width: inherit"  class="showAdmin" type="text" name="price" value="${detailBook.bookPrice}"/>

        <br>

        <p>책정보</p>
        <textarea name="content" class="showAdmin" placeholder="${detailBook.bookContent}"></textarea>

        <br>
        <p>책 목차</p>
        <textarea name="index" class="showAdmin" placeholder="${detailBook.bookIndex}"></textarea>

        <p>저자 정보</p>
        <textarea name="authorIntro" class="showAdmin" placeholder="${detailBook.bookAuthorIntro }"></textarea>

        <input type="submit" name = "updateBtn"  value="수정" onclick="updateBook();"  />
        <input type="submit" name = "deleteBtn"  value="삭제" onclick="deleteBook();" />
    </div>

    </c:if>

</form>

<%@ include file="../main/footer.jsp"%>