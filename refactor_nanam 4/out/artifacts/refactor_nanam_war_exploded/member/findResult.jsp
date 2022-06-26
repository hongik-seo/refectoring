<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="nanam" value="${pageContext.request.contextPath}"/>





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


	<h1 id ="WHAT_FIND"></h1>
    <table border="1" >
        <tr>
            <td id="what"></td>
            <td>이름</td>
            <td>전화번호</td>
            <td>이메일</td>
        </tr>
        <tr>

            <td >
                <input type="text" id = "USER_FIND_WHAT" name=" USER_FIND_WHAT" readonly="readonly" disabled="disabled"/>
            </td>
            <td >
                <input type="text" id = "RESULT_USER_NAME" name="RESULT_USER_NAME" readonly="readonly" disabled="disabled"/>
            </td>
            <td>
                <input type="tel" id = "RESULT_USER_PHONE" name="RESULT_USER_PHONE" readonly="readonly" disabled="disabled"/>
            </td>
            <td >
                <input type="email" id = "RESULT_USER_EMAIL" name="RESULT_USER_EMAIL"readonly="readonly" disabled="disabled"/>
            </td>
        </tr>
    </table>

<script>


    function setParentText(){

        let userWhat =opener.document.getElementById("USER_FIND_WHAT").value;
        let name = opener.document.getElementById("USER_NAME").value;
        let phone=opener.document.getElementById("USER_PHONE").value;
        let email = opener.document.getElementById("USER_EMAIL").value;
        let whatFind = opener.document.getElementById("WHAT_FIND").value;
        $("#RESULT_USER_NAME").val(name);
        $("#RESULT_USER_PHONE").val(phone);
        $("#RESULT_USER_EMAIL").val(email);
        $("#WHAT_FIND").text(whatFind);


        if(whatFind=='아이디찾기'){

            userWhat = userWhat.replace(/[a-zA-Z]/g,"*");
            $("#what").text("아이디");

        }else if(whatFind=='비밀번호찾기') {
            userWhat =  userWhat.replace(/[0-9]/g,"*");
            $("#what").text("비밀번호");
        }



        alert(userWhat);
        $("#USER_FIND_WHAT").val(userWhat);
    }

    setParentText();


</script>