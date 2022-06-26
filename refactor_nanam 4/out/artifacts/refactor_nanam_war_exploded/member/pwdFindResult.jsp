<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="../main/header.jsp" %>
	<h1>아이디 찾기 결과</h1>
    <table border="1" >
        <tr>
            <td>이름</td>
            <td>비밀번호 정보</td>
            <td>가입일</td>
        </tr>
        <tr>
            <td id="name">${pwdFind.name}</td>
            <td id="id">${pwdFind.pwd}</td>
            <td id="indate">${pwdFind.adjustIndate}</td>
        </tr>
    </table>
    <a href="${nanam}/member/login.jsp">로그인 하기</a>
    
<%@ include file="../main/footer.jsp" %>