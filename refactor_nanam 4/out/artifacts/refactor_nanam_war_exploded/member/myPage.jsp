<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>


<%@ include file="../main/header.jsp"%>
<link rel="stylesheet" href="${nanam}/css/join.css">
<script type="text/javascript" src="${nanam}/js/join.js"></script>

<script type="text/javascript">

	$(document).ready(function () {
		let  userjoinNameCheck = new RegExp(/^[가-힣]{2,4}$/);
		let alertRed=$("p.alertP");
		let name = $("input#name");

		let userJoinIDCheck = new RegExp(/^[A-Za-z0-9]{5,15}$/);
		let id = $("input#userId");
		let userJoinPwdCheck = new RegExp(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$/);
		let pwd = $("input#pwd");
		let pwdFind = $("input#pwdConfirm");
		let userJoinEmailCheck = new RegExp(/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/);
		let email = $("input#email");
		let userJoinTellCheck=new RegExp(/^010-\d{4}-\d{4}$/);
		let phone =$("input#phone");
		let address=$("input.address");

		$("#email").on("keyup",function(){
			if (email.val() == "") {
				alertRed.eq(6).html("필수 입력사항입니다.");

				return false;
			} else if (!userJoinEmailCheck.test(email.val())) {
				alertRed.eq(6).html("형식을 맞추세요");

				return false;
			} else if (userJoinEmailCheck.test(email.val())) {
				alertRed.eq(6).html("");

			}
		});

		$(".address").on("blur",function(){
			if (address.val() == "") {
				alertRed.eq(5).html("필수 입력사항입니다.");

				return false;
			}else{
				alertRed.eq(5).html("");
			}
		});

		$("#phone").on("keyup",function(){
			if (phone.val() == "") {
				alertRed.eq(4).html("필수 입력사항입니다.");

				return false;
			} else if (!userJoinTellCheck.test(phone.val())) {
				alertRed.eq(4).html("형식을 맞춰주세요 하이픈 포함");

				return false;
			} else if (userJoinTellCheck.test(phone.val())) {
				alertRed.eq(4).html("");

			}
		});

		$("input[type=password]").on("keyup",function(){
			if ($("input[type=password]").val() == "") {
				alertRed.eq(3).html("필수입력사항입니다.");
				return false;
			} else if (pwd.val() != pwdFind.val()) {
				alertRed.eq(3).html("입력한 값이 서로 맞지않습니다. 다시입력해주세요");
				return false;
			} else if(!userJoinPwdCheck.test($("input[type=password]").val())){
				alertRed.eq(3).html("비밀번호(숫자,영문,특수문자 조합 최소 8자 최대30자로 형식을 맞추세요");
				return false;
			}else if(userJoinPwdCheck.test($("input[type=password]").val())){
				alertRed.eq(3).html("");
			}
		});

		$("#userId").on("blur",function(){
			if (id.val() == "") {
				alertRed.eq(1).html("필수 입력사항입니다.");
				return false;
			} else if (!userJoinIDCheck.test(id.val())) {
				alertRed.eq(1).html("영문 대문자 또는 소문자로 시작하는 아이디, 길이는 5~15자로 입력가능합니다.");
				return false;
			}
		});

		$("#name").on("keyup",function(){
			if (name.val() == "") {
				alertRed.eq(0).html("필수 입력사항입니다.");
				return false;
			} else if (!userjoinNameCheck.test(name.val())) {
				alertRed.eq(0).html("한글만 입력 가능합니다. 2에서 4글자 ex=이근,서홍익");
				return false;
			} else if (userjoinNameCheck.test(name.val())) {
				alertRed.eq(0).html("");
			}
		});

	});//end of ready
</script>

<script>


	function pwdChange(  ){

		let modFlag = false;

		for(let i=0; i<$("input[type='password']").length; i++) {

			let disabled = $($("input[type='password']").eq(i)).is(":disabled");


			if (disabled == true) {

				$($("input[type='password']").eq(i)).attr("disabled", false);

				modFlag = true;
			} else {

				$($("input[type='password']").eq(i)).attr("disabled", true);

				modFlag = false;

			}
		}

		if(modFlag ==true){

			alert("비밀번호 수정가능");
		}else {

			alert("비밀번호 수정불가");

		}
	}

	function  phoneChange(  ){

		let disabled = $("input[type='tel']").is(":disabled");

		if(disabled == true){

			$("input[type='tel']").prop("disabled", false);

			alert("전화번호 수정가능.")
		}else {

			$("input[type='tel']").prop("disabled", true);;
			alert("전화번호 수정불가.")
		}

	}

	function  addressChange(  ){

		let modFlag = false;

		for(let i=0; i<$(".address").length; i++) {

			let disabled = $(".address").eq(i).is(":disabled");

			if (disabled == true) {

				$($(".address").eq(i)).prop("disabled", false);
				modFlag = true;
			} else {

				$($(".address").eq(i)).prop("disabled", true);
				modFlag=false;
			}
		}

		if(modFlag ==true){

			alert("주소 수정가능.")
		}else {

			alert("주소 수정불가.")

		}

	}

	function emailChange(  ){

		let disabled = $("input[type='email']").is(":disabled");

		if (disabled == true) {

			$("input[type='email']").prop("disabled", false);
			alert("이메일 수정가능");

		}else{

			$("input[type='email']").prop("disabled", true);
			alert("이메일 수정불가");

		}

	}


</script>


<script>
	function myPageAdjust(){

		let id = $("input[name='id']").val();
		let user_id = $("input[name='userId']").val();
		let user_name = $("input[name='name']").val();
		let user_pwd = $("input[name='pwd']").val();
		let user_pwd_confirm = $("input[name='pwdConfirm']").val();
		let user_phone = $("input[name='phone']").val();
		let user_email = $("input[name='email']").val();
		let zipcode = $("input[name='zipcode']").val();
		let address = $("input[name='address']").val();
		let detail_address = $("input[name='detailAddress']").val();

		let modifyData = {
			"id":id,
			"userId": user_id,
			"userName": user_name,
			"userPwd": user_pwd,
			"userPwdConfirm": user_pwd_confirm,
			"userPhone": user_phone,
			"userEmail": user_email,
			"zipcode": zipcode,
			"address": address,
			"detailAddress": detail_address
		}


		$.ajax({
			type: 'POST',
			dataType: 'json',
			url: "/member/myPageAdjust",
			data: {
				"modifyData": JSON.stringify(modifyData)
			},
			success: function (data) {
				alert("request succeeded");

				if (data != null && data != undefined && data != '') {

						alert("수정 결과페이지로 이동합니다..")
						return location.href = "/member/myPageResult";

				}

			},
			error: function (xhr, ajaxOptions, thrownError) {
				alert(xhr+"request failed");
			},
		});

	}


</script>



<title>정보입력</title>


<c:if test="${not empty sessionScope.login_id }" >

<div style="width: 500px; height: 1000px; margin: 0 auto;" id="myFormWrap">
	<div id = "myForm">
		<ul id="must">
			<li id="nameWrap" >
				<label for="name">이름 필수사항</label>
				<input type="text" id="name" class="inputSize" placeholder="고객님의 이름을 입력해주세요" name="name" value="${sessionScope.login_name}" disabled="disabled"/>
				<p id="joinNameP" class="alertP"></p>
			</li>
			<li id="idWrap" ><label for="userId">아이디</label>
				<input type="text" id="userId"  placeholder="영문 대문자 또는 소문자 숫자로 시작하는 아이디, 길이는 5~15자를 입력해주세요" class="inputSize" name="userId" required="required" value="${sessionScope.login_id}" disabled="disabled"/>
				<p id="joinIDP" class="alertP"></p>
			</li>

			<input type="button" style="width:100px; height: 10px; background-color: #cfaeae; font-size: 10px;" id="pwdBtn" name="pwdBtn" value="비밀번호 바꾸기" onclick="pwdChange()"/>
			<li id="pwdWrap" class="inputInterval"><label for="pwd">비밀번호 필수사항(변경가능)</label>
				<input type="password" id="pwd" class="inputSize" placeholder="(최소 8 자 및 최대 30자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상)" name="pwd" required="required" value="${sessionScope.login_pwd}"  disabled="disabled"/> <!--//비밀번호--> <!--비밀번호확인-->
				<p id="joinPwdP" class="alertP" ></p>
			</li>
			<li id="pwdCWrap" ><label for="pwdConfirm">비밀번호확인 필수사항(변경가능)</label>
				<input type="password" id="pwdConfirm" class="inputSize" placeholder="비밀번호(최소 8 자 및 최대 30자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상)" name="pwdConfirm" required="required" value="${sessionScope.login_pwd}" disabled="disabled"/>
				<p id="joinPwdComfirmP" class="alertP" ></p>
			</li>

			<input type="button" style="width:100px; height: 10px; background-color: #cfaeae; font-size: 10px;" id="phoneBtn" name="phoneBtn" value="핸드폰 번호 바꾸기" onclick="phoneChange()"/>
			<li id="tellWrap"><label for="phone">핸드폰 번호 필수사항 (변경가능)</label>
				<input type="tel" id="phone" class="inputSize" placeholder="고객님의 핸드폰 번호를 입력해주세요 (하이픈 포함)" name="phone" required="required" value="${sessionScope.login_phone}" disabled="disabled"/>
				<p id="joinTellP" class="alertP"></p>
			</li>

			<input type="button" style="width:100px; height: 10px; background-color: #cfaeae; font-size: 10px;" id="addressBtn" name="addressBtn" value="주소지 번호 바꾸기"  onclick="addressChange()" />
			<li id="addressWrap" ><label>주소지 필수 사항 (변경가능)</label>
				<input type="text" id="sample6_postcode"  placeholder="우편번호" class="inputSize address"  name="zipcode" value="${sessionScope.login_zipcode}" disabled="disabled">
				<input type="text" id="sample6_address"  placeholder="주소" class="inputSize address"  name="address"  value="${sessionScope.login_address}" disabled="disabled"><br>
				<input type="text" id="sample6_extraAddress" placeholder="참고항목" class="inputSize address" name="detailAddress" value="${sessionScope.login_detail_address}" disabled="disabled" >
				<p id="addressP" class="alertP"></p>
			</li>

			<input type="button" style="width:100px; height: 10px; background-color: #cfaeae; font-size: 10px;" id="emailBtn" name="emailBtn" value="이메일 바꾸기" onclick="emailChange()"/>
			<li id="emailWrap" ><label for="email">이메일 필수사항 (변경가능)</label>
				<input type="email" id="email" class="inputSize" placeholder="고객님의 이메일을 입력해주세요" name="email" value="${sessionScope.login_email}" disabled="disabled" />
				<p id="joinEmailP" class="alertP">
			</li>
			<input type="text" id="id" name="id" value="${sessionScope.id}" style="display: none"/>
		</ul>
		<div id="btnWrap">
			<div style="width: 50%; float: left">
				<input type="button" class="btn" value="취소" id="reset"  role="button"
					   onclick="resetPage();" style="background-color: wheat; width: 100%;"  />
			</div>
			<div style="width: 50%; float: right">
				<input type="button" class="btn" id="submit" value="수정"  role="button"
					   onclick="myPageAdjust();"  style="background-color: #00bea4;width: 100%;"/>
			</div>
		</div>
	</div>
</div>
</c:if>
<%@ include file="../main/footer.jsp"%>