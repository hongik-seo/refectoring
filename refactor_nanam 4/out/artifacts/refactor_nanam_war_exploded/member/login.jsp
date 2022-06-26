<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ include file="../main/header.jsp"%>



<link rel="stylesheet" type="text/css" href="${nanam}/css/login.css">



<script>

$(function(){

	$("#loginBtn").click(function() {
		let id = $("input[name='id']").val();
		let pwd = $("input[name='pwd']").val();

		let login= {
			"id" : id,
			"pwd" : pwd
		}

		$.ajax({
			type : 'POST',
			dataType : 'JSON',
			url : "/member/login",
			data : {"loginInfo" :JSON.stringify(login) },
			success : function(data) {

				alert("main 페이지로 이동합니다. ");
				return location.href="/nanam/home";

			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert("아이디 또는 비밀번호를 다시 확인해주세요");
				console.log("request failed");

			},
		});
	});//end of pwd
});
</script>

<title>로그인</title>

<div id="allWrap">
		<ul>
			<li style="width: 100px; height: 40px; text-align:center; box-sizing: border-box; padding: 10px;  background-color: #00c5aa; margin: 30px auto; ">로그인하기</li>
		</ul>
	<div id="allFindLoginWrap">
			<ul>
				<li>
					<label>아이디</label> <input type="text" name="id" id="userLoginID" class="loginPutPosition" placeholder="[영문 대문자 또는 소문자 또는 숫자로 시작하는 아이디, 길이는 5~15자, 끝날때 제한 없음]">
					
				</li>
				<li>
					<label>비밀번호</label> <input type="password" name="pwd" id="userLoginPwd" class="loginPutPosition" placeholder="비밀번호를 입력해 주세요(대소문자 구분합니다.)"> <!--//로그인 패스워드 입력-->
				</li>
				<li>
					<input type="submit" id="loginBtn" value="로그인"/>
				</li>
			</ul>
		<ul>
			<li><a href="${nanam}/member/idFindFront">아이디 찾기</a></li>
			<li><a href="${nanam}/member/pwdFindFront">비밀번호 찾기</a></li>
		</ul>
	</div>
</div>

<%@ include file="../main/footer.jsp"%>