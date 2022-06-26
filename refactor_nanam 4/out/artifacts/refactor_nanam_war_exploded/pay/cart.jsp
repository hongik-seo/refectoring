<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp" %>

<link rel="stylesheet" href="${nanam}/css/cart.css" >
<c:set  var="detailBoard"  value="${detailBoard}" />

<script>

    $(function(){

        $("input[type='submit']").click(function(e){

            e.preventDefault();

            let total= parseInt($("#totalPrice").val());

            if(total==0){

                alert("제대로된 경로로 들어와주세요");

                return;

            }else {

                $("form[name='cartForm']").submit();
            }

        });

        let defaultNum  = 0;

        for(let i=0; i<$(".plusArea").length; i++) {

            defaultNum += parseInt($(".bookNum").eq(i).val());
        }

        $("#totalNum").val(defaultNum);


        let defaultPrice  = 0;

        for(let i=0; i<$(".plusArea").length; i++) {

            defaultPrice += parseInt($(".bookPrice").eq(i).val());
        }

        $("#totalPrice").val(defaultPrice);

        bookCalc();


        $(".removeBtn").each(function(i){

            $(this).click(function(){

                $(".cartList").eq(i).hide();

                //ajax 쏴서 useYn =n && useYn = y만 보이기

                let id =  $("input[name='ID']").eq(i).val()

                let cartSeq =  $("input[name='CART_SEQ']").eq(i).val();



                let deleteData ={
                    "ID" :id,
                    "CART_SEQ" :cartSeq

                }

                $.ajax({
                    type: 'POST',
                    dataType: 'json',
                    url: "/cart/deleteCart",
                    data: {
                        "deleteData": JSON.stringify(deleteData)
                    },
                    success: function (data) {

                        bookCalc();

                        location.href = "/cart/cartFromHead?id=${sessionScope.id}&userId=${sessionScope.login_id}&flag=SELECT";

                    },
                    error: function (xhr, ajaxOptions, thrownError) {

                    },
                });

            });

        });

    });
</script>

<script>

    function bookCalc(){

        $(".bookNum").change(function () {

            let lengthFlag =$(".plusArea").length;
            let initNum =0;

            if(lengthFlag==1){
                initNum = 1;


            }else {

               initNum=0;

            }



            let initPrice = 0;

            let initTotalPrice = 0;


            for (let i = 0; i < $(".bookNum").length; i++) {

                initNum += parseInt($(".bookNum").eq(i).val());

                initPrice += parseInt($(".bookPrice").eq(i).text());

                initTotalPrice = initNum * initPrice;

            }

            $("#totalNum").val(initNum);

            $("#totalPrice").val(initTotalPrice);

            let eachNum = 0;

            let eachPrice = 0;

            let bookNumArr = new Array();

            let bookNumArrForRecalc = new Array();

            let bookPriceArr = new Array();

            let bookPriceArrForRecalc = new Array();

            let totalPrice = 0;

            for (let i = 0; i < $(".bookNum").length; i++) {

                bookNumArr[i] = parseInt($(".bookNum").eq(i).val());

                bookNumArrForRecalc.push(bookNumArr[i]);

                bookPriceArr[i] = parseInt($(".bookPrice").eq(i).val());

                bookPriceArrForRecalc.push(bookPriceArr[i]);
            }


            for (let i = 0; i < bookNumArrForRecalc.length; i++) {

                eachNum += bookNumArrForRecalc[i];

                eachPrice += bookPriceArrForRecalc[i];


                if (initNum <= eachNum) {

                    initNum = initNum + 1

                } else {

                    initNum = initNum - 1;
                }

            }
            $("#totalNum").val(initNum);

            for (let i = 0; i < bookPriceArrForRecalc.length; i++) {

                eachNum = bookNumArrForRecalc[i];

                eachPrice = bookPriceArrForRecalc[i];

                totalPrice += eachNum * eachPrice;

            }


            $("#totalPrice").val(totalPrice);

        });
    }


</script>
<form action="/cart/goToOrder" method="post" name="cartForm">
    <div id="buyBooksWrap">
        <div id="buyBooksBox">
            <div id="cartWrap">
    	    <h2 >${sessionScope.login_id} 님의 장바구니 목록 </h2>
                <input type="text" name="cartUserId" value="${sessionScope.login_id}">
                <input type="text" name="cartId" value="${sessionScope.id}">

                <ul id="infoBoard">
                    <li>
                        <div style="color:#00c5aa;">
                                step01
                                책바구니
                        </div>
                        </li>
                        <li>
                            <div>
                                step02
                                주문 결제하기
                            </div>
                        </li>
                        <li>
                            <div>
                                step04
                                주문완료
                            </div>
                        </li>
                    </ul>
                    <div id="cart">
                        <table id="cartTable">
							<thead>
                            <tr>
                            	<th>

                            	</th>
                                <th>
                                    이미지
                                </th>
                                <th>
                                    구매도서정보
                                </th>

                                <th>
                                    도서가격
                                </th>

                                <th>
                                    수량
                                </th>

                                <th>
                                    기능
                                </th>

                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="cartList" items="${detailBookList.cartList}" >
                                <c:if test="${cartList.useYnOfService eq 'Y' && cartList.cartDecide eq 'Y' }">
                                    <tr  class="cartList">
                                        <td>
                                                ${cartList.cartBookIsbn}
                                            <input type="text"  name="bookISBN" class="bookISBN"  style="display: none" value="${cartList.cartBookIsbn}" >
                                        </td>
                                        <td>
                                            <input type="text" name="bookImg" value="${cartList.bookImg}" style="display:none" >
                                            <img src="${nanam}/images/allbook_list/${cartList.bookImg}">
                                        </td>
                                        <td>
                                            <input type="text" class="title" name="bookTitle" value="${cartList.cartBookTitle}" style="display:none">${cartList.cartBookTitle}
                                        </td>
                                        <td class="eachPrice">
                                            <input type="text"  name="bookPrice" class="bookPrice"  value="${cartList.cartPrice}" style="display: none;">${cartList.cartPrice}

                                        </td>
                                        <td class="plusArea">
                                            <input type="number" name="bookNum"  class="bookNum"  value="1"  min="1" max="20">
                                        </td>
                                        <td>
                                            <input type="button"  value="장바구니 비우기"  class="removeBtn" >
                                            <input type="text" name="ID" value="${cartList.id}" style="display: none">
                                            <input type="text" name="CART_SEQ" value="${cartList.cartSeq}" style="display: none">

                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                            	<td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td id="inhereTotalNum">
									<input type="text"  id="totalNum" name="totalNum"  readonly="readonly" />
                                </td>
                                <td id="allPrice">
                                   <input type="text"  id="totalPrice" name="totalPrice"  readonly="readonly" />
                                </td>
                            </tr>
							</tfoot>
                        </table>
                        <div id="buyBtnWrap">
                            <input type="submit" id="buy" class="buyBtn" value="주문하기">
                        </div>
                    </div>
            </div>
        </div>
    </div>
</form>
<%@ include file="../main/footer.jsp" %>