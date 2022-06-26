<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="../main/header.jsp"%>


<link rel="stylesheet" href="${nanam}/css/agree.css" type="text/css">
<link rel="stylesheet" href="${nanam}/css/join.css" type="text/css">

<script>
  
	/*
    function check() {//체크 매소드 선언

    	var allChecked = false;
        var list = document.getElementsByName("list")[2].checked;
        if (list) {
            allChecked = true;
        }

        if (allChecked == true) { 
            //전부 체크해줘...
            for (var i = 0; i < document.getElementsByName("list").length; i++) {
                document.getElementsByName("list")[i].checked = true;
            }
		
        } else {
            //전체 선택이 아니라면.... 해제!!
            //전부 풀어줘....
            for (var i = 0; i < document.getElementsByName("list").length; i++) {
                document.getElementsByName("list")[i].checked = false;
            }
        }

    }
	 */

    function check() {//체크 매소드 선언

        var allChecked = false;
        var list = document.getElementsByName("list")[2].checked;
        if (list) {
            allChecked = true;
        }

        if (allChecked == true) {
            //전부 체크해줘...
            for (var i = 0; i < document.getElementsByName("list").length; i++) {
                document.getElementsByName("list")[i].checked = true;
            }

        } else {
            //전체 선택이 아니라면.... 해제!!
            //전부 풀어줘....
            for (var i = 0; i < document.getElementsByName("list").length; i++) {
                document.getElementsByName("list")[i].checked = false;
            }
        }

    }




    function resetPage(){


    }

    function checkAndGoNextPage() {

        let agreeList = new Array();
        let agreeYn = new Array();
        let allAgreeAndWrite = new Array();
        let allAgreeAndWriteNotTrim = new Array();
        let allAgreeAndWriteFlag = new Array();
        for (let i = 0; i < document.getElementsByName("list").length; i++) {

            agreeList[i] = document.getElementsByName("list")[i].checked

            if (agreeList[i] == true) {
                agreeYn.push('Y');

                $("input[name='list']").eq(i).val('Y');

            } else {
                agreeYn.push('N');
            }
        }

        let user_id = $("input[name='userId']").val();
        let user_name = $("input[name='name']").val();
        let user_pwd = $("input[name='pwd']").val();
        let user_pwd_confirm = $("input[name='pwdConfirm']").val();
        let user_phone = $("input[name='phone']").val();
        let user_email = $("input[name='email']").val();
        let zipcode = $("input[name='zipcode']").val();
        let address = $("input[name='address']").val();
        let detail_address = $("input[name='detailAddress']").val();

        for(let i=0; i<$("input").length; i++){


            allAgreeAndWrite[i] = $.trim($("input").eq(i).val());

            allAgreeAndWriteNotTrim[i] = $("input").eq(i).val();

            if(allAgreeAndWrite[i] != allAgreeAndWriteNotTrim[i]){
                alert("띄어쓰기는 허용이 안됩니다.");
                return ;

            }

            if(allAgreeAndWrite[i] !="" && allAgreeAndWrite[i] !=null && allAgreeAndWrite[i] !==undefined  ){

                allAgreeAndWriteFlag.push("true");

            } else {
                allAgreeAndWriteFlag.push("false");
            }
        }

            console.log(allAgreeAndWriteFlag)

            if(allAgreeAndWriteFlag.includes("false")){

                alert("모든 정보를 입력해야합니다.")
                return false;

            }else {

                let agreeData = {
                    "agree": [
                        {"agreeYn": agreeYn[0]},
                        {"agreeYn": agreeYn[1]},
                        {"agreeYn": agreeYn[2]}
                    ]
                }

                let joinData = {
                    "userId": user_id,
                    "userName": user_name,
                    "userPwd": user_pwd,
                    "userPwdConfirm": user_pwd_confirm,
                    "userPhone": user_phone,
                    "userEmail": user_email,
                    "zipcode": zipcode,
                    "address": address,
                    "detailAddress": detail_address

                }

                $.ajax({
                    type: 'POST',
                    dataType: 'json',
                    url: "/member/agreeAndJoin",
                    data: {
                        "agreeList": JSON.stringify(agreeData),
                        "joinList": JSON.stringify(joinData)
                    },
                    success: function (data) {
                        alert("request succeeded");

                        if (data != null && data != undefined && data != '') {
                            alert("정보입력 페이지로 이동합니다.")
                            return location.href = "/member/loginFront";

                        }

                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr+"request failed");
                    },
                });

            }
    }


</script>


<script type="text/javascript">

    $(document).ready(function () {
        let  userjoinNameCheck = new RegExp(/^[가-힣]{2,4}$/);
        let alertRed=$("p.alertP");
        let name = $("input#name");

        let userJoinIDCheck = new RegExp(/^[A-Za-z0-9]{5,15}$/);
        let id = $("input#userId");
        let userJoinPwdCheck = new RegExp(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$/);
        let pwd = $("input#pwd");
        let pwdFind = $("input#pwdConfirm");
        let userJoinEmailCheck = new RegExp(/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/);
        let email = $("input#email");
        let userJoinTellCheck=new RegExp(/^010-\d{4}-\d{4}$/);
        let phone =$("input#phone");
        let address=$("input.address");

        $("#email").on("keyup",function(){
            if (email.val() == "") {
                alertRed.eq(6).html("필수 입력사항입니다.");

                return false;
            } else if (!userJoinEmailCheck.test(email.val())) {
                alertRed.eq(6).html("형식을 맞추세요");

                return false;
            } else if (userJoinEmailCheck.test(email.val())) {
                alertRed.eq(6).html("");

            }
        });

        $(".address").on("blur",function(){
            if (address.val() == "") {
                alertRed.eq(5).html("필수 입력사항입니다.");

                return false;
            }else{
                alertRed.eq(5).html("");
            }
        });

        $("#phone").on("keyup",function(){
            if (phone.val() == "") {
                alertRed.eq(4).html("필수 입력사항입니다.");

                return false;
            } else if (!userJoinTellCheck.test(phone.val())) {
                alertRed.eq(4).html("형식을 맞춰주세요 하이픈 포함");

                return false;
            } else if (userJoinTellCheck.test(phone.val())) {
                alertRed.eq(4).html("");

            }
        });

        $("input[type=password]").on("keyup",function(){
            if ($("input[type=password]").val() == "") {
                alertRed.eq(3).html("필수입력사항입니다.");
                return false;
            } else if (pwd.val() != pwdFind.val()) {
                alertRed.eq(3).html("입력한 값이 서로 맞지않습니다. 다시입력해주세요");
                return false;
            } else if(!userJoinPwdCheck.test($("input[type=password]").val())){
                alertRed.eq(3).html("비밀번호(숫자,영문,특수문자 조합 최소 8자 최대30자로 형식을 맞추세요");
                return false;
            }else if(userJoinPwdCheck.test($("input[type=password]").val())){
                alertRed.eq(3).html("");
            }
        });

        $("#userId").on("blur",function(){
            if (id.val() == "") {
                alertRed.eq(1).html("필수 입력사항입니다.");
                return false;
            } else if (!userJoinIDCheck.test(id.val())) {
                alertRed.eq(1).html("영문 대문자 또는 소문자로 시작하는 아이디, 길이는 5~15자로 입력가능합니다.");
                return false;
            } else {

                let alreadyId = $("#userId").val();

                $.ajax({
                    type: 'get',
                    dataType: 'text',
                    url: "/member/alreadyIdFind",
                    data: {"alreadyId": alreadyId},
                    success: function (data) {
                        console.log("request succeeded");

                        console.log(data)

                        if (data == "usable") {
                            alertRed.eq(1).html("사용가능한 아이디입니다.");
                        } else if (data == "notUsable") {

                            alertRed.eq(1).html("이미 사용중인 아이디입니다.");
                            return false;
                        }


                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        console && console.log("request failed");
                    },
                });//endOfAjax
            }
        });

        $("#name").on("keyup",function(){
            if (name.val() == "") {
                alertRed.eq(0).html("필수 입력사항입니다.");
                return false;
            } else if (!userjoinNameCheck.test(name.val())) {
                alertRed.eq(0).html("한글만 입력 가능합니다. 2에서 4글자 ex=이근,서홍익");
                return false;
            } else if (userjoinNameCheck.test(name.val())) {
                alertRed.eq(0).html("");
            }
        });

    });//end of ready
</script>


<div>
    <div id="agreeJoinWrap" style="width: 70%;padding-left: 200px;">
        <div style="float: left;">
            <div class="basebar">
                <ul>
                    <li>
                        <a href="#">메인</a>
                    </li>
                    <li>></li>
                    <li>
                        <a href="#">회원가입</a>
                    </li>
                    <li>></li>
                    <li>
                        <a href="#">약관동의</a>
                    </li>
                </ul>
            </div>
            <div id="goAgreeJoinWrap" >

                <div style="width: 100%; height: 700px;">
                        <ul id="conditionsWrap">
                                <div id="userConditionsBox" class="spec">
                                    <p>
                                        제 1조 (목적)
                                        <br>
                                        이 약관은 전기통신 사업법 및 동 법 시행령에 의하여 "(주)나남"(이하 "회사" 라 합니다.)가 제공하는 인터넷 홈페이지 서비스 (이하 "서비스" 라
                                        합니다.)의
                                        이용조건 및 절차에 관한 사항, 회사와 이용자의 권리와 의무 및 책임사항을 규정함을 목적으로 합니다.
                                        <br>
                                        제 2조 (약관의 효력과 개정)
                                        <br>
                                        1. 이 약관은 전기통신사업법 제 31 조, 동 법 시행규칙 제 21조의 2에 따라 공시절차를 거친 후 홈페이지를 통하여 이를 공지하거나 전자우편
                                        기타의 방법으로
                                        이용자에게 통지함으로써 효력을 발생합니다.
                                        2. 회사는 본 약관을 사전 고지 없이 개정할 수 있으며, 개정된 약관은 제9조에 정한 방법으로 공지합니다. 회원은 개정된 약관에 동의하지 아니하는
                                        경우 본인의
                                        회원등록을 취소(회원탈퇴)할 수 있으며, 계속 사용의 경우는 약관 개정에 대한 동의로 간주됩니다. 개정된 약관은 공지와 동시에 그 효력이 발생됩니다.
                                        <br>
                                        제 3조 (약관이외의 준칙)
                                        <br>
                                        이 약관에 명시되어 있지 않은 사항은 전기통신 기본법, 전기통신 사업법, 기타 관련법령의 규정에 따릅니다.
                                        <br>
                                        제 4조 (용어의 정의)
                                        <br>
                                        이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
                                        <br>
                                        1. 회원 : 서비스에 개인정보를 제공하여 회원등록을 한 자로서, 서비스의 정보를 지속적으로 제공받으며, 이용할 수 있는 자를 말합니다.
                                        2. 이용자 : 본 약관에 따라 회사가 제공하는 서비스를 받는 회원 및 비회원을 말합니다.
                                        3. 아이디 (ID) : 회원 식별과 회원의 서비스 이용을 위하여 회원이 선정하고 회사가 승인하는 문자와 숫자의 조합을 말합니다.
                                        4. 비밀번호 : 회원이 통신상의 자신의 비밀을 보호하기 위해 선정한 문자와 숫자의 조합을 말합니다.
                                        5. 전자우편 (E-mail) : 인터넷을 통한 우편입니다.
                                        6. 해지 : 회사 또는 회원이 서비스 이용 이후 그 이용계약을 종료 시키는 의사표시를 말합니다.
                                        7. 홈페이지 : 회사가 이용자에게 서비스를 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 이용자가 열람 및 이용할 수 있도록 설정한 가상의 서비스
                                        공간을
                                        말합니다
                                        <br>
                                        제 5조 (서비스의 제공 및 변경)
                                        <br>
                                        1. 회사가 제공하는 서비스는 다음과 같습니다.
                                        <br>
                                        1) 회사에 대한 홍보 내용
                                        2) 회사가 판매하는 제품 안내
                                        3) 기타 회사가 제공하는 각종 정보
                                        4) 고객 상담 서비스
                                        5) 회원 이용 서비스
                                        <br>
                                        2. 회사는 필요한 경우 서비스의 내용을 추가 또는 변경하여 제공할 수 있습니다.
                                        <br>
                                        제 6조 (서비스의 중단)
                                        <br>
                                        1. 회사는 컴퓨터 등 정보통신설비의 보수점검/교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.
                                        2. 제 1항에 의한 서비스 중단의 경우에는 제 9조에 정한 방법으로 이용자에게 통지합니다.
                                        3. 회사는 제1항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상하지 아니합니다. 단, 회사에
                                        고의 또는
                                        중과실이 있는 경우에는 그러하지 아니합니다.
                                        <br>
                                        제 7조 (회원가입)
                                        <br>
                                        1. 이용자는 회사가 정한 가입양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사표시를 함으로서 회원가입을 신청합니다.
                                        2. 이용자는 반드시 실명으로 회원가입을 하여야 하며, 1개의 이름에 대해 1건의 회원가입 신청을 할 수 있습니다.
                                        3. 회사는 제 1항과 같이 회원으로 가입할 것을 신청한 이용자 중 다음 각 호에 해당하지 않는 한 회원으로 등록합니다.
                                        1) 이름이 실명이 아닌 경우
                                        2) 등록 내용에 허위, 기재누락, 오기가 있는 경우
                                        3) 타인의 명의를 사용하여 신청한 경우
                                        4) 가입신청자가 이 약관 제 8조 3항에 의하여 이전에 회원자격을 상실한 적이 있는 경우(단, 제 8조 3항에 의한 회원자격 상실 후 3년이 경과한
                                        자로서 회사의
                                        회원 재가입 승낙을 얻은 경우는 예외로 합니다.)
                                        5) 만 14세 미만의 아동
                                        6) 기타 회원으로 회사 소정의 이용신청요건을 충족하지 못하는 경우
                                        4. 회원가입계약의 성립시기는 회사의 승낙이 이용자에게 도달한 시점으로 합니다.
                                        5. 회원은 제 10조 1항에 의한 등록사항에 변경이 있는 경우 회원정보변경 항목을 통해 직접 변경사항을 수정, 등록하여야 합니다.
                                        <br>
                                        제 8조 (회원탈퇴 및 자격 상실 등)
                                        1. 회원은 언제든지 회원의 탈퇴를 홈페이지에 요청할 수 있으며, 홈페이지는 즉시 이에 응합니다.
                                        2. 회원이 다음 각 호의 사유에 해당하는 경우, 회사는 회원자격을 제한 및 정지시킬 수 있습니다.
                                        1) 가입 신청 시에 허위 내용을 등록한 경우
                                        2) 타인의 서비스 이용을 방해하거나 그 정보를 도용하는 등 서비스 운영질서를 위협하는 경우
                                        3) 서비스를 이용하여 법령과 이 약관이 금지하거나, 공서양속에 반하는 행위를 하는 경우
                                        4) 제 13조 에 명기된 회원의 의무사항을 준수하지 못할 경우
                                        3. 회사가 회원자격을 제한/정지시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 회사는 회원자격을 상실
                                        시킬 수
                                        있습니다.
                                        4. 회사가 회원자격을 상실 시키는 경우 회원에게 이를 통지하고 탈퇴를 처리합니다. 이 경우 회원에게 이를 통지하고, 탈퇴 전에 소명할 기회를
                                        부여합니다.
                                        <br>
                                        제 9조 (이용자에 대한 통지)
                                        1. 회사가 이용자에 대한 통지를 하는 경우, 이용자가 서비스에 제출한 전자우편 주소로 할 수 있습니다.
                                        2. 회사가 불특정 다수 이용자에 대한 통지의 경우 1주일 이상 서비스 게시판에 게시함으로써 개별 통지에 갈음할 수 있습니다.
                                        <br>
                                        제 10조 (개인 정보 보호)
                                        <br>
                                        1. 회사는 이용자 정보 수집 시 회사측이 필요한 최소한의 정보를 수집합니다. 다음 사항을 필수사항으로 하며 그 외 사항은 선택사항으로 합니다.
                                        1) 성명
                                        2) 희망 ID
                                        3) 비밀번호
                                        4) E-mail
                                        5) 주소
                                        6) 전화번호
                                        7) favor 구독 여부
                                        <br>
                                        2. 회사가 이용자의 개인식별이 가능한 개인정보를 수집하는 때에는 반드시 당해 이용자의 동의를 받습니다.
                                        <br>
                                        3. 제공된 개인정보는 당해 이용자의 동의 없이 제 3자에게 제공할 수 없으며, 이에 대한 모든 책임은 회사가 집니다. 다만 다음의 경우에는 예외로
                                        합니다.
                                        1) 배송업무상 배송업체에게 배송에 필요한 최소한의 이용자의 정보(성명, 주소, 전화번호)를 알려주는 경우
                                        2) 통계작성, 학술연구 또는 시장조사를 위하여 필요한 경우로서 특정 개인을 식별할 수 없는 형태로 제공하는 경우
                                        3) 관계법령에 의하여 국가기관으로부터 요구 받은 경우
                                        4) 범죄에 대한 수사상의 목적이 있거나, 정보통신 윤리위원회의 요청이 있는 경우
                                        5) 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우
                                        <br>
                                        4. 이용자는 언제든지 회사가 가지고 있는 자신의 개인정보에 대해 열람 및 오류정정을 할 수 있습니다.
                                        <br>
                                        5. 회사로부터 개인정보를 제공받은 제 3자는 개인정보를 제공받은 목적을 달성한 때에는 당해 개인정보를 지체 없이 파기합니다.
                                        <br>
                                        제 11조 (회사의 의무)
                                        1. 회사는 이 약관에서 정한 바에 따라 계속적, 안정적으로 서비스를 제공할 수 있도록 최선의 노력을 다하여야만 합니다.
                                        2. 회사는 서비스에 관련된 설비를 항상 운용할 수 있는 상태로 유지/보수하고, 장애가 발생하는 경우 지체 없이 이를 수리/복구할 수 있도록 최선의
                                        노력을 다하여야
                                        합니다.
                                        3. 회사는 이용자가 안전하게 서비스를 이용할 수 있도록 이용자의 개인정보보호를 위한 보안시스템을 갖추어야 합니다.
                                        4. 회사는 이용자가 원하지 않는 영리목적의 광고성 전자우편을 발송하지 않습니다.
                                        <br>
                                        제 12조 (회원의 ID 및 비밀번호에 대한 의무)
                                        1. 회원에게 부여된 아이디(ID)와 비밀번호의 관리책임은 회원에게 있으며 관리 소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 책임은 회원에게
                                        있습니다.
                                        2. 회원이 자신의 ID 및 비밀번호를 도난 당하거나 제 3자가 사용하고 있음을 인지한 경우에는 바로 회사에 통보하고 회사의 안내가 있는 경우에는 그에
                                        따라야
                                        합니다.
                                        <br>
                                        제 13조 (회원의 의무)
                                        1. 회원은 관계법령, 본 약관의 규정, 이용안내 및 주의사항 등 회사가 통지하는 사항을 준수하여야 하며, 기타 회사의 업무에 방해되는 행위를 하여서는
                                        안됩니다.
                                        2. 회원은 회사의 사전승낙 없이 서비스를 이용하여 어떠한 영리행위도 할 수 없습니다.
                                        3. 회원은 서비스를 이용하여 얻은 정보를 회사의 사전승낙 없이 복사, 복제, 변경, 번역, 출판/방송 기타의 방법으로 사용하거나 이를 타인에게 제공할
                                        수 없습니다.
                                        4. 회원은 자기 신상정보의 변경사항 발생시 즉각 변경하여야 합니다. 회원정보를 수정하지 않아 발생하는 모든 결과에 대한 책임은 회원에게 있습니다.
                                        5. 회원은 서비스 이용과 관련하여 다음 각 호의 행위를 하지 않아야 하며, 다음 행위를 함으로 발생하는 모든 결과에 대한 책임은 회원에게 있습니다.
                                        1) 다른 회원의 아이디(ID)를 부정하게 사용하는 행위
                                        2) 다른 회원의 E-mail 주소를 취득하여 스팸메일을 발송하는 행위
                                        3) 범죄행위를 목적으로 하거나 기타 범죄행위와 관련된 행위
                                        4) 선량한 풍속, 기타 사회질서를 해하는 행위
                                        5) 회사 및 타인의 명예를 훼손하거나 모욕하는 행위
                                        6) 회사 및 타인의 지적재산권 등의 권리를 침해하는 행위
                                        7) 해킹행위 또는 컴퓨터 바이러스의 유포행위
                                        8) 타인의 의사에 반하여 광고성 정보 등 일정한 내용을 지속적으로 전송하는 행위
                                        9) 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있는 일체의 행위
                                        10) 회사가 제공하는 서비스의 내용을 변경하는 행위
                                        11) 기타 관계법령에 위배되는 행위
                                        <br>
                                        제 14조 (게시물 삭제)
                                        1. 회사는 이용자가 게시하거나 등록하는 서비스내의 게시물이 제 13조의 규정에 위반되거나, 다음 각 호에 해당한다고 판단되는 경우 사전통지 없이
                                        게시물을 삭제할 수
                                        있습니다.
                                        1) 다른 이용자 또는 제 3자를 비방하거나 중상모략으로 명예를 손상시키는 내용
                                        2) 공공질서 또는 미풍양속에 위반되는 내용
                                        3) 범죄적 행위에 결부된다고 인정되는 내용
                                        4) 제 3자의 저작권 등 기타 권리를 침해하는 내용
                                        5) 서비스의 안정적인 운영에 지장을 주거나 줄 우려가 있는 내용
                                        6) 근거나 확인절차 없이 회사를 비난하거나 유언비어를 유포한 내용
                                        7) 기타 관계법령에 의거하여 위반된다고 판단되는 내용
                                        단, 독자게시판의 경우 다음과 같이 예외를 둔다.
                                        용량이 큰 데이터의 경우 업로드 된 게시물의 수를 제한하며 그 수를 넘을 때에는 서버의 원활한 운영을 위해 가장 오래된 게시물부터 삭제할 수 있다.
                                        2. 회사는 이용자가 게시하거나 등록하는 서비스내의 게시물이 제 13조의 규정에 위반되거나 동 조 제1항 각 호에 해당한다고 판단되는 정보를 링크하고
                                        있을 경우
                                        사전통지 없이 게시물을 삭제할 수 있습니다.
                                        <br>
                                        제 15조 (게시물에 대한 권리 / 의무)
                                        게시물에 대한 저작권을 포함한 모든 권리 및 책임은 이를 게시한 이용자에게 있습니다.
                                        <br>
                                        제 16조 (연결 "홈페이지"와 피연결 "홈페이지"간의 관계)
                                        1. 상위 "홈페이지"와 하위 "홈페이지"가 하이퍼 링크(예:하이퍼 링크의 대상에는 문자, 그림 및 동화상 등이 포함됨) 방식 등으로 연결된 경우,
                                        전자를 연결
                                        "홈페이지"라고 하고 후자를 피연결 "홈페이지(웹사이트)"라고 합니다.
                                        2. 연결 "홈페이지"는 피연결 "홈페이지"가 독자적으로 제공하는 재화?용역에 의하여 이용자와 행하는 거래에 대해서 보증책임을 지지 않습니다.
                                        <br>
                                        제 17조 (저작권의 귀속 및 이용제한)
                                        1. 회사가 작성한 저작물에 대한 저작권 및 기타 지적재산권은 회사에 귀속합니다.
                                        2. 이용자는 서비스를 이용함으로써 얻은 정보를 회사의 사전승낙 없이 복제, 송신, 출판, 배포, 방송, 기타 방법에 의하여 영리목적으로 이용하거나 제
                                        3자에게
                                        이용하게 하여서는 안됩니다.
                                        <br>
                                        제 18조 (양도금지)
                                        회원이 서비스의 이용권한, 기타 이용 계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.
                                        <br>
                                        제 19조 (손해배상)
                                        회사는 무료로 제공되는 서비스와 관련하여 이용자에게 어떠한 손해가 발생하더라도 동 손해가 회사의 중대한 과실에 의한 경우를 제외하고 이에 대하여 책임을
                                        부여하지
                                        아니합니다.
                                        <br>
                                        제 20조 (면책 / 배상)
                                        1. 회사는 이용자가 서비스에 게재한 정보, 자료, 사실의 정확성, 신뢰성 등 그 내용에 관하여는 어떠한 책임을 부담하지 아니하고, 이용자는 자신의
                                        책임아래 서비스를
                                        이용하며, 서비스를 이용하여 게시 또는 전송한 자료 등에 관하여 손해가 발생하거나 자료의 취사선택, 기타 서비스 이용과 관련하여 어떠한 불이익이
                                        발생하더라도 이에
                                        대한 모든 책임은 이용자에게 있습니다.
                                        2. 회사는 제 13조의 규정에 위반하여 이용자간 또는 이용자와 제 3자간에 서비스를 매개로 한 물품거래 등과 관련하여 어떠한 책임도 부담하지
                                        아니하고, 이용자가
                                        서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다.
                                        3. 이용자가 제 13조, 기타 이 약관의 규정을 위반함으로 인하여 회사가 이용자 또는 제 3자에 대하여 책임을 부담하게 되고, 이로써 회사에게 손해가
                                        발생하게 되는
                                        경우, 이 약관을 위반한 이용자는 회사에게 발생하는 모든 손해를 배상하여야 하며, 동 손해로부터 회사를 면책시켜야 합니다.
                                        <br>
                                        제 21조 (분쟁의 해결)
                                        1. 회사와 이용자는 서비스와 관련하여 발생한 분쟁을 원만하게 해결하기 위하여 필요한 모든 노력을 하여야 합니다.
                                        2. 제 1항의 규정에도 불구하고, 동 분쟁으로 인하여 소송이 제기될 경우 동 소송은 서울지방법원을 관할로 합니다.
                                        3. 동 소송에는 대한민국 법을 적용합니다.
                                        <br>
                                        제 22조 (기타)
                                        이 약관에 명시되지 아니한 사항의 처리를 위하여 이용자는 담당자에게 문의해주시면 됩니다.
                                        <br>
                                        부칙
                                        이 약관은 2017년 06월 10일부터 시행합니다.
                                    </p>
                                </div>
                            <li class="itemBox">
                                <input type="checkbox" name="list" id="acceptUserCondition" value="">
                                <label for="acceptUserCondition" class="label_space">
                                    회원가입 약관(필수)
                                </label>
                            </li>
                            <li class="itemBox">
                                <div id="emailConditionsBox" class="spec">
                                    <p>
                                        <br>
                                        ■ 수집하는 개인정보 항목
                                        <br>
                                        우리 회사는 회원가입, 상담, 서비스 신청 등등을 위해 아래와 같은 개인정보를 수집하고 있습니다.
                                        <br>
                                        ο 수집항목 : 이름 , 로그인ID , 비밀번호 , 전화번호 , 주소, 휴대전화번호 , 이메일 , 서비스 이용기록 , 접속 로그 , 쿠키 , 접속 IP
                                        정보
                                        ο 개인정보 수집방법 : 홈페이지(회원가입)
    
                                        <br>
                                        ■ 개인정보의 수집 및 이용목적
                                        <br>
                                        우리 회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.
                                        <br>
                                        ο 회원 관리
                                        회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입 의사 확인 , 불만처리 등 민원처리 ,
                                        고지사항 전달
                                        <br>
                                        <br>
                                        ■ 개인정보의 보유 및 이용기간
                                        <br>
                                        원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우
                                        회사는 아래와 같이
                                        관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.
                                        <br>
                                        - 보존 항목 : 이름, 로그인ID, 결제기록, 주문정보, 배송정보
                                        - 보존 근거 : 서비스 이용의 혼선 방지
                                        - 보존 기간 : 5년 상법, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 관계법령에서 정한
                                        일정한 기간
                                        동안 회원정보를 보관합니다. 이 경우 회사는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다.
                                        <br>
                                        - 계약 또는 청약철회 등에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)
                                        - 대금결제 및 재화 등의 공급에 관한 기록 : 5년 (전자상거래등에서의 소비자보호에 관한 법률)
                                        - 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년 (전자상거래등에서의 소비자보호에 관한 법률)
                                        - 신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년 (신용정보의 이용 및 보호에 관한 법률)
                                    </p>
                                </div>

                                <input type="checkbox" name="list" id="acceptPrivate" value="" >
                                <label for="acceptPrivate" class="label_space">
                                    개인정보 수집 이용 동의 (필수)
                                </label>
                            </li>
                        </ul>
                    </div>
                 </div>
            </div>
            <div>
               <ul>
                    <li class="itemBox">
                        <input type="checkbox" name="list" id="acceptAll" onclick="check();" value="">
                        <label for="acceptAll">
                            모든약관에 동의합니다.
                        </label>
                    </li>
               </ul>
            <div style="width: 500px; height: 1000px; float: right;" id="myFormWrap">
                <div id = "myForm">
                    <ul id="must">
                        <li id="nameWrap" >
                            <label for="name">이름 필수사항</label>
                            <input type="text" id="name" class="inputSize" placeholder="고객님의 이름을 입력해주세요" name="name" />
                            <p id="joinNameP" class="alertP"></p>
                        </li>
                        <li id="idWrap" ><label for="userId">아이디</label>
                            <input type="text" id="userId"  placeholder="영문 대문자 또는 소문자 숫자로 시작하는 아이디, 길이는 5~15자를 입력해주세요" class="inputSize" name="userId" required="required" />
                            <p id="joinIDP" class="alertP"></p>
                        </li>
                        <li id="pwdWrap" class="inputInterval"><label for="pwd">비밀번호 필수사항</label>
                            <input type="password" id="pwd" class="inputSize" placeholder="(최소 8 자 및 최대 30자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상)" name="pwd" required="required" /> <!--//비밀번호--> <!--비밀번호확인-->
                            <p id="joinPwdP" class="alertP" ></p>
                        </li>
                        <li id="pwdCWrap" ><label for="pwdConfirm">비밀번호확인 필수사항</label>
                            <input type="password" id="pwdConfirm" class="inputSize" placeholder="비밀번호(최소 8 자 및 최대 30자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상)" name="pwdConfirm" required="required"/>
                            <p id="joinPwdComfirmP" class="alertP" ></p>
                        </li>
                        <li id="tellWrap"><label for="phone">핸드폰 필수사항 번호</label>
                            <input type="tel" id="phone" class="inputSize" placeholder="고객님의 핸드폰 번호를 입력해주세요 (하이픈 포함)" name="phone" required="required" />
                            <p id="joinTellP" class="alertP"></p>
                        </li>
                        <li id="addressWrap" ><label>주소지 필수 사항</label>
                            <!--<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" id="join findAddressBtn" ><br>-->
                            <input type="text" id="sample6_postcode"  placeholder="우편번호" class="inputSize " class="address"  name="zipcode">
                            <input type="text" id="sample6_address"  placeholder="주소" class="inputSize" class="address" name="address"><br>
                            <input type="text" id="sample6_detailAddress" placeholder="상세주소" class="inputSize" class="address" name="detailAddress">
                            <input type="text" id="sample6_extraAddress" placeholder="참고항목" class="inputSize" >
                            <p id="addressP" class="alertP"></p>
                        </li>
                        <li id="emailWrap" ><label for="email">이메일 필수사항</label>
                            <input type="email" id="email" class="inputSize" placeholder="고객님의 이메일을 입력해주세요" name="email" />
                            <p id="joinEmailP" class="alertP">
                        </li>
                    </ul>
                    <div id="btnWrap">
                        <div style="width: 50%; float: left">
                            <input type="button" class="btn" value="취소" id="reset"  role="button"
                               onclick="resetPage();" style="background-color: wheat; width: 100%;"  />
                        </div>
                        <div style="width: 50%; float: right">
                            <input type="button" class="btn" id="submit" value="가입"  role="button"
                               onclick="checkAndGoNextPage();"  style="background-color: #00bea4;width: 100%;"/>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="../main/footer.jsp" %>