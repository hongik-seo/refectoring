<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%@ include file="../main/header.jsp" %>

<script>

    $(function(){

       $("#nextOrderBtn").click(function(){

           let totalStr=$("input[name='totalBookPrice']").val()
           let total=parseInt($("input[name='totalBookPrice']").val());
           let payPoint=parseInt($("input[name='payPoint']").val());
           let point=parseInt($("#userPoint").val());


           if(totalStr==null || totalStr=="" || totalStr==undefined){

               alert("제대로된 경로로 들어와주세요");

               return;

           }

           if(point-total<0){

               alert("포인트가 적습니다. 충전해주세요");

               return;

           }

           if(total>payPoint){

               alert("총 합보다 작은 값을 입력하셨습니다.");
               return;
           }

           if(total>point){

               alert("총 합보다 포인트가 적습니다.");
               return;
           }

           if(payPoint>point){

               alert("쓰시려는 포인트보다 큰값을 입력했습니다.");
               return ;

           }

           if(isNaN(payPoint)==true){

               alert("숫자만 입력해주세요")
               return ;
           }


           let order_id = $("input[name='orderId']").val();
           let order_name = $("input[name='orderName']").val();
           let order_phone = $("input[name='orderPhone']").val();
           let order_zipcode = $("input[name='orderZipcode']").val();
           let order_address = $("input[name='orderAddress']").val();
           let order_detail_address = $("input[name='orderDetailAddress']").val();
           let order_call = $("input[name='orderCall']").val();


           let id = "${sessionScope.id}";
           let userId = "${sessionScope.login_id}";
           let userName = "${sessionScope.login_name}";
           let phoneNumberForCharging = "${sessionScope.login_phone}";


           let order_info = {
               "ID":id,
               "ORDER_ID": order_id,
               "ORDER_NAME": order_name,
               "ORDER_PHONE": order_phone,
               "ORDER_ZIPCODE": order_zipcode,
               "ORDER_ADDRESS": order_address,
               "ORDER_DETAIL_ADDRESS": order_detail_address,
               "ORDER_CALL" : order_call
           }


           $.ajax({
               type: 'POST',
               dataType: 'json',
               url: "/orderAndPay/orderAndPay",
               data: {
                   "order_info": JSON.stringify(order_info),

               },
               success: function (data) {
                   alert("request succeeded");
                   console.log(data);

                   location.href="/orderAndPay/result?id="+${sessionScope.id};

               },
               error: function (xhr, ajaxOptions, thrownError) {
                   alert(xhr+"request failed");
               },
           });

           pointInvolve();

       });

    });

   function pointInvolve(){
        let id = "${sessionScope.id}";
        let userId = "${sessionScope.login_id}";
        let userName = "${sessionScope.login_name}";
        let phoneNumberForCharging = "${sessionScope.login_phone}";
        let payPoint=$("input[name='payPoint']").val();

        let point_result = {

            "ID":id,
            "USER_ID" :userId,
            "USER_NAME":userName,
            "TOTAL_BOOK_PRICE" :payPoint,
            "PHONE_NUMBER_FOR_CHARGING" : phoneNumberForCharging,

        }

        $.ajax({
            type : 'POST',
            dataType : 'JSON',
            url : "/pay/pointSpend",
            data : {
                "point_result": JSON.stringify(point_result)
            },
            success : function(data) {

                console.log(data)

                $("#userPoint").val(data.TOTAL_POINT);


            },
            error : function(xhr, ajaxOptions, thrownError) {
                console && console.log("request failed");
            },
        });//endOfAjax

    };


</script>


<link rel="stylesheet" href="${nanam}/css/order.css" >

<link rel="stylesheet" href="${nanam}/css/cart.css" >


<div id ="cartWrap">
    <h2 >${sessionScope.login_id} 님의 주문 목록 </h2>
    <input type="text" id="id" name="buyId" value="${sessionScope.id}" style="display:none">

    <ul id="infoBoard">
        <li>
            <div >
                step01
                책바구니
            </div>
        </li>
        <li>
            <div style="color:#00c5aa;">
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
                    총수량
                </th>
                <th>
                    총가격
                </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="cartList" items="${detailBookList.cartList}" >
                    <c:if test="${cartList.useYnOfService eq 'Y' && cartList.cartDecide eq 'Y' }">
                    <tr  class="cartList">
                        <td>
                            <input type="text" name="bookImg" value="${cartList.bookImg}" style="display:none" >
                            <img src="${nanam}/images/allbook_list/${cartList.bookImg}">
                        </td>
                        <td>
                            <input type="text" class="title" name="bookTitle" value="${cartList.cartBookTitle}" style="display:none">"${cartList.cartBookTitle}"
                        </td>

                        <td class="eachPrice">
                            <input type="text"  name="bookPrice" class="bookPrice"  readonly value="${cartList.cartPrice}" style="display:none">${cartList.cartPrice}
                        </td>
                        <td>
                                ${cartList.cartOfVolume}
                        </td>

                    </tr>
                    </c:if>
                </c:forEach>

                </tbody>


                <tfoot>

                <c:forEach var="cartListTotal" items="${detailBookList.cartList}" begin="0" end="1" >
                    <c:if test="${cartListTotal.useYnOfService eq 'Y' && cartListTotal.cartDecide eq 'Y' }">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>

                            <td>
                                 ${cartListTotal.cartOfTotalVolume}
                            </td>
                            <td>
                                 ${cartListTotal.cartTotalPrice} <input type="text" name="totalBookPrice" value="${cartListTotal.cartTotalPrice}" disabled="disabled" style="display: none"/>
                            </td>
                      </tr>
                    </c:if>
                </c:forEach>
                </tfoot>
            </table>
            </div>
        <div class="buyFormWrap">
            <table id="orderFirstTable" class="orderTable" >
                <tr>
                    <td>
                        주문자
                    </td>
                </tr>
                <tr>
                    <td>
                        주문 하시는 분*
                    </td>
                    <td>
                        <input type="text" name="name" value="${sessionScope.login_name}">
                    </td>
                </tr>
                <tr>
                    <td>
                        전화번호*
                    </td>
                    <td>
                        <input type="tel" name="phone" value="${sessionScope.login_phone}" >
                    </td>

                </tr>
                <tr>
                    <td>
                        이메일
                    </td>
                    <td>
                        <input type="email" name="email" value="${sessionScope.login_email}" >
                    </td>
                </tr>
                <tr>
                    <td>
                        우편번호*
                    </td>
                    <td>
                        <input type="text" name="zipcode" value="${sessionScope.login_zipcode}">
                    </td>
                </tr>
                <tr>
                    <td>
                        주소*
                    </td>
                    <td>
                        <input type="text" name="address" value="${sessionScope.login_address}" >
                    </td>
                </tr>
                <tr>
                    <td>
                        상세주소*
                    </td>
                    <td>
                        <input type="text" name="detailAddress" value="${sessionScope.login_detail_address}" >
                    </td>
                </tr>
            </table>
        </div>

    <div class="buyFormWrap">
        <table id="orderSecondTable" class="orderTable"  >
            <tr>
                <td>
                    받으시는분
                </td>
            </tr>
            <tr>
                <td>
                    받으시는 분*
                </td>
                <td>
                    <input type="text" name="orderName" value="${sessionScope.login_name}">
                </td>
            </tr>
            <tr>
                <td>
                    휴대전화번호*
                </td>
                <td>
                    <input type="tel" name="orderPhone" value="${sessionScope.login_phone}">
                </td>
            </tr>
            <tr>
                <td>
                    우편번호*
                </td>
                <td>
                    <input type="text" id="sample6_postcode"   class="join notMust" name="orderZipcode" value="${sessionScope.login_zipcode }">
                </td>
            </tr>
            <tr>
                <td>
                    주소*
                </td>
                <td>
                    <input type="text" id="sample6_address"  class="join notMust" name="orderAddress" value="${sessionScope.login_address}"><br>
                </td>
            </tr>
            <tr>
                <td>
                    상세주소*
                </td>
                <td>
                    <input type="text"  id="sample6_detailAddress" name="orderDetailAddress" value="${sessionScope.login_detail_address}">
                </td>
            </tr>

            <tr>
                <td>
                    요청사항
                </td>
                <td>
                    <input type="text" id="orderCall" name="orderCall"  class="join notMust" >
                </td>
            </tr>
        </table>
        <div id="payFormWrap" style="margin-top:100px">
            아이디 : ${sessionScope.login_id}님의 포인트 ::   <input name="userPoint" id="userPoint" value="${sessionScope.login_point}" disabled="disabled" >
            <input name="userPhone" id="userPhone" value="${sessionScope.login_phone}" style="display:none">
            <table id="payFirstTable" class="orderTable" >
                <tr>
                    <td>
                        결제수단
                    </td>
                    <td>
                        포인트
                    </td>
                </tr>
                <tr>
                    <td>
                        포인트사용
                    </td>
                    <td>
                        <input type="text" id="pay" name="payPoint" value=""  >원
                    </td>
                </tr>
            </table>
        </div>
        <div id="oderBtnWrap" style="display: inline; margin:  0 auto">
            <input type="button" class="orderBtn" id="nextOrderBtn"  value="결제하기">
            <input type="reset" class="orderRestBtn" id="resetOrderBtn"  value="취소하기">
        </div>
    </div>
<%@ include file="../main/footer.jsp" %>