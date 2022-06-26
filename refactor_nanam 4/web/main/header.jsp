<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nanam" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
	<link rel="stylesheet" href="${nanam}/css/common.css" type="text/css" >
	<link rel="stylesheet" href="${nanam}/css/header.css" type="text/css" >

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/jquery.easing.1.3.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/prefixfree.min.js"></script>
	<script type="text/javascript" src="${nanam}/js/custom.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/jquery.skitter.js"></script>
	<script type="text/javascript"
		src="${nanam}/js/jquery.skitter.min.js"></script>

    <script>
        function openWindowPop(){
            var url = "/pay/point.jsp";
            var name = "popup test";
            var option = "width = 500, height = 500, top = 100, left = 200, location = no"
            window.open(url, name, option);
        }
    </script>

	<script>

		$(function(){

			$("#freeLink").click(function(){


				alert("서비스 준비중 입니다.");

			})

		});

		function logOut(){

			let id = "${sessionScope.login_id}";
			let pwd = "${sessionScope.login_pwd}";

			let logout = {

				"id":id,
				"pwd":pwd

			}

			$.ajax({
				type : 'POST',
				dataType : 'JSON',
				url : "/member/logOutFront",
				data : {"logoutInfo" :JSON.stringify(logout) },
				success : function(data) {

					if(data!=null && data!='' && data!=undefined){

						alert("정상적으로 로그아웃 되었습니다.");
						return location.href="/nanam/home";
					}

				},
				error : function(xhr, ajaxOptions, thrownError) {
					console.log("request failed");

				},
			});

		}

	</script>

</head>
<body>
<div>
	<header>
		<div id="headerWrap">
			<div id="headerBox">
				<a title="메인페이지로 이동" href="${nanam}/main"> <img src="${nanam}/images/arr/logo2.gif" alt="로고 메인페이로 이동">
				</a>

				<ul id="moveLoginJoin">
					<c:choose>
						<c:when test="${empty sessionScope.login_id}">
							<li><a title="로그인" href="${nanam}/member/loginFront">로그인</a></li>
							<li><a title="회원가입페이지로 이동" href="${nanam}/member/agreeFront">회원가입</a></li>
						</c:when>
						<c:otherwise>
							<li><a id="logOut"  title="로그아웃" onclick="logOut()">로그아웃</a></li>

							<c:if test="${sessionScope.login_id ne 'scott' and sessionScope.login_pwd ne 'tiger'}">
								<li><a title="회원 탈퇴 페이지로 이동" href="${nanam}/member/leaveOutFront"> 회원탈퇴</a></li>
								<li><a href="javascript:openWindowPop();">포인트 충전</a></li>
								<li><a title="정보수정 페이지로 이동" href="${nanam}/member/myPageFront"> 정보수정 </a></li>
								<li><a title="나의 정보 결과로 이동" href="${nanam}/member/myPageResult"> 나의 정보 결과</a></li>
								<li><a title="장바구니로 이동" href="${nanam}/cart/cartFromHead?id=${sessionScope.id}&userId=${sessionScope.login_id}&flag=SELECT"> 장바구니</a></li>
								<li><a title="주문정보로 이동" href="${nanam}/order/orderFromHead?id=${sessionScope.id}&userId=${sessionScope.login_id}&flag=SELECT"> 주문정보</a></li>
								<li><a title="주문정보결과로 이동" href="${nanam}/order/payResultFromHead?id=${sessionScope.id}&userId=${sessionScope.login_id}&flag=SELECT"> 주문정보 결과페이지</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</ul>
				<div id="gnbWrap">
					<ul id="gnbBox">

						<li class="gnb"><a title="책" href="${nanam}/book/bookList/new" class="depth1" id="book">책 <span> BOOKS </span>

						</a> <!--lnb-->
							<div class="book menu">
								<ul>
									<li><a title="새로나온책" href="${nanam}/book/bookList/new">새로나온
											책 </a></li>
									<li><a title="스테디셀러"
										href="${nanam}/book/bookList/steady">스테디셀러 </a></li>
									<li><a title="나남의 책들"
										href="${nanam}/book/bookList/nanam">나남의 책들 </a></li>

									<c:if test="${sessionScope.login_id eq 'scott' and sessionScope.login_pwd eq 'tiger'}">
										<li><a title="글쓰기" href="${nanam}/book/uploadBookPage">책 글쓰기</a></li>
									</c:if>
								</ul>
							</div>

						</li>

						<li class="gnb"><a title="출판" href="${nanam}/board/officialBoardList/essay" class="depth1" id="publish">출판<span> PUBLISHING HOUSE </span></a> <!--lnb-->
							<div class="nanam menu">
								<ul>

									<li><a title="발행인 에세이" href="${nanam}/board/officialBoardList/essay">발행인 에세이 </a></li>
									<li><a title="언론이 본 나남" href="${nanam}/board/officialBoardList/nanam">언론이 본 나남 </a></li>
									<li><a title="공지사항" href="${nanam}/board/officialBoardList/notice">공지사항</a></li>
									<li><a title="자유게시판" href="#" id="freeLink">자유게시판</a></li>
									<c:if test="${sessionScope.login_id eq 'scott' and sessionScope.login_pwd eq 'tiger'}">
										<li><a title="글쓰기" href="${nanam}/board/uploadBoardFront">공식게시판 글쓰기</a></li>
									</c:if>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</div>