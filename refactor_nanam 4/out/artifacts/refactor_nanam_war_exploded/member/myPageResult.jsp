<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp" %>

	<h1>정보수정 결과</h1>
    <table border="1" >
        <tr>
            <td>이름</td>
            <td>아이디</td>
            <td>비밀번호</td>
            <td>이메일</td>
            <td>전화번호</td>
            <td>우편번호</td>
            <td>주소</td>
            <td>상세주소</td>
            <td>포인트</td>
        </tr>
        <tr>
            <td id="name">${sessionScope.login_name}</td>
            <td id="id">${sessionScope.login_id}</td>
            <td id="pwd">${sessionScope.login_pwd}</td>
            <td id="email">${sessionScope.login_email}</td>
            <td id="phone">${sessionScope.login_phone}</td>
            <td id="zipcode">${sessionScope.login_zipcode}</td>
            <td id="address">${sessionScope.login_address}</td>
            <td id="detailAddress">${sessionScope.login_detail_address}</td>
            <td id="point">${sessionScope.login_point}</td>
        </tr>
    </table>

<%@ include file="../main/footer.jsp" %>