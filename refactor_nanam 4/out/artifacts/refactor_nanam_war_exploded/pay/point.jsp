<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="nanam" value="${pageContext.request.contextPath}"/>

 <style type="text/css">
 	td{
 	font-size :10px;
 	} 
 	
 	table{
 		width:500px;
 	}
 </style>

<script type="text/javascript" src="${nanam}/js/jquery-3.1.1.min.js"></script>
<script>	
    $(function(){

        let num=Math.floor( ( Math.random() * 10000 ));

        $("#ticketBtn").click(function(){

            var pattern = /\s/g;

            console.log(num);

            alert("콘솔창에서 번호를 확인하세요");

        });

        for(let i =0; i<$("input:radio[name='addPoint']").length; i++){


            $("input:radio[name='addPoint']").eq(i).prop("disabled",true);

        }

        $("input[name='ticket_number_for_charging']").blur(function(){

            let ticketNumber= $("input[name='ticket_number_for_charging']").val();
            var pattern = /\s/g;



            if(num!= ticketNumber){

                alert("티켓번호를 다시 한번 확인해주세요");
                return ;
            }else if(ticketNumber.match(pattern) ) {
                //정규식
                alert("공백을 지워주세요");
                return;
            } else {

                if(num>=5000){

                    alert(num)

                    $("input:radio[name='addPoint']:radio[value='20000']").attr("checked",true);
                }else {

                    alert(num)
                    $("input:radio[name='addPoint']:radio[value='10000']").attr("checked",true);
                }
            }



        });


        $("#pointPhone").blur(function(){

            let myPhone = "${sessionScope.login_phone}";
            let pointPhone= $(this).val();

            var pattern = /\s/g;


            if(myPhone != pointPhone){

                alert("휴대전화 번호를 다시 확인해주세요 ");

                return ;

            }else if(!pointPhone.indexOf("-")){
                alert("하이폰을 입력해주세요")

                return ;

            }else if(pointPhone.match(pattern) ){
                //정규식
                alert("공백을 지워주세요");
                return ;
            }
            
        });



		$("#getFullPointBtn").click(function() {
            var pointId = $("#pointId").val();
            var pointPhone = $("#pointPhone").val();
            var point = $(":checked").val();
            var pointCultureNum  = $("#inputCultureNum").val();
            var id = $("input[name='idNum']").val();
            var userName = $("input[name='userName']").val();

            let data ={
                "ID" : id,
                "USER_ID" : pointId,
                "VOLUME_OF_POINT" :point,
                "PHONE_NUMBER_FOR_CHARGING":pointPhone,
                "TICKET_NUMBER_FOR_CHARGING":pointCultureNum,
                "USER_NAME":userName,

            }

            $.ajax({
                type : 'POST',
                dataType : 'JSON',
                url : "/pay/pointCharge",
                data : {
						"point_info":JSON.stringify(data)
                },
                success : function(data) {

                    console.log(data)

                    $("input[name='total_point']").val(data.TOTAL_POINT)

                },
                error : function(xhr, ajaxOptions, thrownError) {
						console && console.log("request failed");
                },
            });//endOfAjax
        });

        $("input[name='total_point']").change(function(){

            alert("한 건밖에 충전 못합니다.");

            $("#getFullPointBtn").hide();

        });


    });
</script>

<head>
<meta charset="UTF-8">
<title>포인트 충전 창</title>
</head>
   <table border=1>
       <thead>
       <tr>
           <th colspan="2">
               포인트 충전 창
           </th>
       </tr>
       <tr>
           <th colspan="2">
               포인트 충전 금액
           </th>
       </tr>
       </thead>
       <tbody>
       <tr>
           <td colspan="2">
               아이디
               <input name="id" type="text" id="pointId" value="${sessionScope.login_id}" readonly="readonly">
           </td>
       </tr>
       <tr>
           <td colspan="2">
               인증 :가입하신 휴대폰 번호<input type="text" id="pointPhone" name="pointPhone" placeholder="(하이픈 포함)">
           </td>
       </tr>
       <tr >
           <td>
               문화 상품권
           </td>
           <td colspan="2">
           </td>
       </tr>
       <tr>
           <td colspan="2">
               <input type="button" id="ticketBtn"  value="문화상품권 번호 받기">
               <input id="inputCultureNum" type="text"  name="ticket_number_for_charging" placeholder="문화상품권 번호 입력">
           </td>
       </tr>
       <tr>
           <td>
               <label for="pointNum1"></label>
               <input id="pointNum1"   name="addPoint" type="radio" class="addPoint" value=10000>
           </td>

           <td>
               10,000원
           </td>
       </tr>
       <tr>
           <td>
               <label for="pointNum2"></label>
               <input id="pointNum2" name="addPoint" type="radio" class="addPoint" value=20000>
           </td>

           <td>
               20,000원
           </td>
       </tr>
       <tr>
           <td>

               보유 포인트 : <input type="text" name="total_point" disabled="disabled" value="${sessionScope.login_point}" >

           </td>

       </tr>

       <tr>
           <td colspan="2">
               <input type="button" id="getFullPointBtn" value="충전">
           </td>
       </tr>
       </tbody>
   </table>
    <input name="idNum" value="${sessionScope.id}" style="display:none;">
    <input name="userName" value="${sessionScope.login_name}" style="display:none;">
</html>