<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp" %>
	<h1>회원 탈퇴</h1>
	<div>
    <table border="1" >
        <tr>
            <td>이름</td>
            <td>아이디</td>
            <td>이메일</td>
            <td>주소</td>
            <td>포인트</td>
            <td>수정일</td>
        </tr>
        <tr>
            <td id="name">${login.name}</td>
            <td id="userid">${login.id}</td>
            <td id="email">${login.email}</td>
            <td id="address">${login.address}</td>
            <td id="point">${login.point}</td>
            <td id="indate">${login.adjustIndate}</td>
            
        </tr>
    </table>
    </div>
    <a href="${nanam}/home.jsp">메인 페이지 이동</a><br/>
    <h2>탈퇴 할 정보 입력</h2>
    <form action="/nanam/member/leaveOutMain" method="post">
	<ul>
		<li><label for="id">아이디 입력</label> <input type="text" id="id" name="id" /></li>
		<li><label for="pwd">비밀번호 입력</label> <input type="password" id="pwd" name="pwd" /></li>
        <li><input type="submit" value="회원 탈퇴" /></li>
	</ul>
</form>
    
<%@ include file="../main/footer.jsp" %>    
    
    
    