<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="../main/header.jsp" %>

<link rel="stylesheet" href="${nanam}/css/find.css" type="text/css">

<script>

	$(function(){
		$("#emailInput").hide();
		$("#tellInput").show();

		$("input[name='check']").each(function(i){
			$(this).click(function(){

				let howToFind =$($("input[name='check']").eq(i)).val();

				if(howToFind==0){

					$("#emailInput").hide();
					$("#tellInput").show();

				}else{

					$("#tellInput").hide();
					$("#emailInput").show();
				}
			});

		});
	});

	let number=10;

	let arrRan = 0;

	function Ran( number) {

		var arrNumber = new Array(); //배열선언
		arrNumber[0] = 15782;
		arrNumber[1] = 45319;
		arrNumber[2] = 78951;
		arrNumber[3] = 45628;
		arrNumber[4] = 78942;
		arrNumber[5] = 87463;
		arrNumber[6] = 87663;
		arrNumber[7] = 48348;
		arrNumber[8] = 74774;
		arrNumber[9] = 91735;

		arrRan = arrNumber[Math.floor(Math.random() * number)];
		this.arrRan = arrRan;
	}

	let result

	function beforeClickFindBtn(){

		let ran = new Ran(10);

		alert("콘솔창을 확인해 주세요");
		console.log(ran.arrRan);

		 result = ran.arrRan;

		return result;

	}

	function clickFindBtn(){

		let name =$("input[name='nametext']").val();
		let tel =$("input[name='phone']").val();
		let email =$("input[name='email']").val();
		let how_flag ="";
		let confirmAll =$("input[name='confirmAll']").val();


		if(confirmAll != result ){

			alert("인증번호를 다시 한번 확인해주세요");

			return false;

		}

		$("input[name='check']").each(function(i){


			let isChecked=$(this).is(':checked');
			let howToFind =$($("input[name='check']").eq(i)).val();

			if(isChecked==true) {

				if (howToFind == 0) {

					how_flag = "전화번호로 찾기";

				} else {
					how_flag = "이메일로 찾기";

				}
			}

		});


		let data = {
			"find_certification_num" : confirmAll,
			"name" : name,
			"phone" : tel,
			"email" : email,
			"how_find" : how_flag,
			"what_find" : "아이디찾기"
		}

		$.ajax({
			type: 'post',
			dataType: 'JSON',
			url: "/member/user-find",
			data: {"find_info" :  JSON.stringify( data )  },
			success: function (data) {
				console.log("request succeeded");

				console.log(data);

				$("#USER_FIND_WHAT").val(data.USER_ID);
				$("#USER_NAME").val(data.USER_NAME);
				$("#USER_PHONE").val(data.USER_PHONE);
				$("#USER_EMAIL").val(data.USER_EMAIL);
				$("#WHAT_FIND").val(data.WHAT_FIND);



				let url = "/member/findResult";

				window.open( url , 'pop01', 'top=10, left=10, width=500, height=600, status=no, menubar=no, toolbar=no, resizable=no');

			},
			error: function (xhr, ajaxOptions, thrownError) {
				console && console.log("request failed");
			},
		});//endOfAjax

	}


</script>

<title>아이디 찾기</title>
<div id="allWrap">
		<ul>
			<li style="width: 120px; height: 40px; text-align:center; box-sizing: border-box; padding: 10px;  background-color: #00c5aa; margin: 30px auto; ">아이디찾기</li>
		</ul>
	<div id="allFindLoginWrap" >

		<div id="allFindLogin" style="padding-left: 55px">
			<label for="tellCheck">휴대전화</label>
			<input type="radio" checked="checked" name="check" id="tellCheck" value="0"/>
			<label for="emailCheck">이메일</label>
			<input type="radio" name="check" id="emailCheck" value="1"/>

			<input type="text" id="nameInput" name="nametext" placeholder="이름 입력" class="putPositionFind"/>
			<input type="tel" id="tellInput" name="phone" placeholder="휴대전화 입력(-포함)" pattern="(010)-\d{3,4}-\d{4}" class="putPositionFind"/>
			<input type="email" id="emailInput" name="email" placeholder="이메일 입력" pattern="xxxxx@xxxx.com" class="putPositionFind"/>
			<input type="text" id="confirmAll" name="confirmAll" class="putPositionFind" placeholder="인증 번호 5자리 숫자 입력"/>
			<input type="button" id="overlapBtn" value="인증요청"  onclick = "beforeClickFindBtn()"/>
			<input type="button" id="searchBtn" value="아이디 찾기" onclick = "clickFindBtn()"/>
			<ul>
				<li><a href="${nanam}/member/loginFront">로그인</a></li>
				<li><a href="${nanam}/member/pwdFindFront">비밀번호 찾기</a></li>
			</ul>
			<input type="tel" id = "USER_PHONE" name="RESULT_USER_PHONE" style="display: none"/>
			<input type="name" id = "USER_NAME" name="RESULT_USER_NAME" style="display: none"/>
			<input type="email" id = "USER_EMAIL" name="RESULT_USER_EMAIL" style="display: none"/>
			<input type="text" id = "WHAT_FIND" name="RESULT_WHAT_FIND" style="display: none"/>
			<input type="text" id = "USER_FIND_WHAT" name=" USER_FIND_WHAT" style="display: none"/>

		</div>
	</div>
</div>
<%@ include file="../main/footer.jsp" %>