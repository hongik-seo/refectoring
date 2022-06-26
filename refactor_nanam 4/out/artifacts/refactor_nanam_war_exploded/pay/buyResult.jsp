<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../main/header.jsp" %>



<h1 id="id">${sessionScope.login_id}님이시키신</h1>

<c:forEach var="orderList" items="${detailBookList.orderList}" >
		<ul>
			<li>
				주문한 책 이름 ::	    ${orderList.cartBookTitle}
			</li>

			<li>
				수량 ::		${orderList.cartOfVolume}
			</li>
			<li>
				가격 ::		${orderList.cartPrice}
			</li>
		</ul>
</c:forEach>


<div style="margin-top: 300px; width: fit-content; height: 30px;"></div>
<c:forEach var="orderList" items="${detailBookList.orderList}" >
	<ul>
		<li>
				받는 사람 이름 :: "${orderList.orderName}"
		</li>
		<li>
				받는 사람 전화번호 :: "${orderList.orderPhone}"
		</li>
		<li>
				받는 사람 주소 :: "${orderList.orderAddress}"
		</li>
		<li>
				받는 사람 상세주소 :: "${orderList.orderDetailAddress}"
		</li>

		<li>
				받는 사람 요구사항 :: "${orderList.orderCall}"
		</li>
	</ul>
</c:forEach>

<h3>이 배송 준비중 입니다.</h3>


<h1>${sessionScope.login_id}님의 포인트는 :: ${sessionScope.login_point} 입니다.</h1>
<a  href="${nanam}/home.jsp">메인 페이지 이동</a>
<%@ include file="../main/footer.jsp" %>