<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file ="../main/header.jsp"%>   

<link rel="stylesheet" type="text/css" href="${nanam}/css/boardDetail.css">

<c:set  var="detailBoard"  value="${detailBoard}" />

<script>

    alert("글번호를 누르면 이전페이지로 이동합니다.");// 만약 페이지 유지하고 싶으면 다른 함수

    $(function(){

       let showAdmin = $(".showAdmin");

        for(let i = 0; i<showAdmin.length; i++){

            if ( "${sessionScope.login_id}" === 'scott' &&  "${sessionScope.login_pwd}" === 'tiger'){
                $($(".showAdmin").eq(i)).show();


            }else{

                $($(".showAdmin").eq(i)).hide();
            }

        }



    });


</script>
<script>

    function updateBoard(){

        let htmls = "<input name ='flag' value = 'UPDATE'>"

        $("#inHere").append(htmls);

    }

    function deleteBoard(){

        let htmls = "<input name ='flag' value = 'DELETE'>"

        $("#inHere").append(htmls);

    }

</script>

<form action="/board/boardControl" method="get">
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
        <div>게시장소 : ${detailBoard.boardList }</div>

        <div><a style="color: red" href="/board/officialBoardList/${detailBoard.boardList}">글번호 : ${detailBoard.rn}</a></div>
        <table id="noticeTable">
            <tbody>
            <tr></tr>
            <tr>
                <td id="subNoticeHead">
                    제목 :   ${detailBoard.boardSubject}
                    <input style="width: inherit"  class="showAdmin" type="text" name="subject" value="${detailBoard.boardSubject}"/>
                </td>
            </tr>
            <tr>
                <td id="date">
                    <ul>
                        <li id="writerYear">
                            게시일  : ${detailBoard.insertDate}
                        </li>
                                
                    </ul>
                </td>
            </tr>
            <tr>
                <td>
                    ${detailBoard.boardContent}
                    <textarea style=" width: 100%; height: 500px;" name="content" class="showAdmin" maxlength="400x">

                    </textarea>
                </td>
            </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_id eq 'scott' and sessionScope.login_pwd eq 'tiger'}">
			<div id="subNoticeBtnWrap">
                <input style="display: block" type="text" name="seq" value="${detailBoard.boardSeq}" readonly="readonly"/>
                <input style="display: block" type="text" name="list" value="${detailBoard.boardList}" readonly="readonly"/>
			    <input type="submit" name = "updateBtn"  value="수정" onclick="updateBoard();"  />
                <input type="submit" name = "deleteBtn"  value="삭제" onclick="deleteBoard();" />
            </div>
            <div id="inHere">

            </div>
        </c:if>
    </div>
</div>
</form>
<%@ include file ="../main/footer.jsp"%>      