<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="../main/header.jsp"%>   

<link rel="stylesheet" type="text/css" href="${nanam}/css/boardDetail.css">

<div id="noticetableWrap">
    <div class="basebar">
        <ul>
            <li>
                <a href="#">메인</a>
            </li>
            <li>></li>
            <li>
                <a href="#">출판</a>
            </li>
            <li>></li>
            <li>
                <a href="#">에세이</a>
            </li>
        </ul>
    </div>
    <div id="tableWrap">
        <div>게시장소 : ${b.list }</div>
   
        <table id="noticeTable">
            <tbody>
            <tr></tr>
            <tr>
                <td id="subNoticeHead">
                    제목 :   ${b.subject}
                </td>
            </tr>
            <tr>
                <td id="date">
                    <ul>
                        <li id="writerYear">
                            게시일  : ${b.adjustIndate}
                            전체 글 번호 : ${b.qseq }
                        </li>
                                
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    <p>내용  : ${b.content}</p>
                </td>
            </tr>
            </tbody>
        </table>
  	<c:choose>
		<c:when
			test="${sessionScope.login.id eq'scott' and login.pwd eq 'tiger'}">
			<div id="subNoticeBtnWrap">
				<a href="/nanam/board/updateBoardFront?list=${b.list}&subject=${b.subject}&content=${b.content}">수정</a>
				<a href="/nanam/board/deleteBoardFront?list=${b.list}&subject=${b.subject}&content=${b.content}">삭제</a>
			</div>
		</c:when>
	</c:choose>
    </div>
</div>
<%@ include file ="../main/footer.jsp"%>      