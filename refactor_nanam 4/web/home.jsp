<%@page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="main/header.jsp" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link  rel="stylesheet" type="text/css" href="${nanam}/css/index.css">
<link  rel="stylesheet" type="text/css" href="${nanam}/css/skitter.css">

<title>나남 메인</title>

<script type="text/javascript" src="${nanam}/js/jquery.skitter.js"></script>
<script type="text/javascript" src="${nanam}/js/jquery.skitter.min.js"></script>

<div class="skitter skitter-large with-dots">
    <ul>
        <li>
            <a href="#cut">
                <img src="${nanam}/images/bxIMG/main_visual_1911200214288.jpg" class="cut" />
            </a>
            <div class="label_text">
            </div>
        </li>
        <li>
            <a href="#swapBlocks">
                <img src="${nanam}/images/bxIMG/main_visual_1911200214422.jpg" class="swapBlocks" />
            </a>
            <div class="label_text">

            </div>
        </li>
        <li>
            <a href="#swapBarsBack">
                <img src="${nanam}/images/bxIMG/main_visual_2002051012298.jpg" class="swapBarsBack" />
            </a>
            <div class="label_text">

            </div>
        </li>
        <li>
            <a href="#swapBarsBack">
                <img src="${nanam}/images/bxIMG/main_visual_2002051013007.jpg" class="swapBarsBack" />
            </a>
            <div class="label_text">

            </div>
        </li>
        <li>
            <a href="#swapBarsBack">
                <img src="${nanam}/images/bxIMG/main_visual_2002051032253.jpg" />
            </a>
            <div class="label_text">

            </div>
        </li>
        <li>
            <a href="#swapBarsBack">
                <img src="${nanam}/images/bxIMG/main_visual_2004221039395.jpg" />
            </a>
            <div class="label_text">

            </div>
        </li>
    </ul>
</div>

<div id="contentsWrap">
    <ul id="contentsBox">
        <li id="news">
            <h3>언론이 본 나남</h3>
            <a href="../notice/notice.html" title="언론이 본 나남 페이지로 이동">
                <img src="./images/S2004270335382_1.jpg">
				[소설 예수] [신간] 소설 예수
				2020-04-27
            </a>
            <a class="moreBtn" href="../notice/notice.html" title="언론이 본 나남으로 이동">
                <img alt="더보기" src="${nanam}/images/arr/more_btn.gif">
            </a>
        </li>
        <li id="notice">
            <h3>
                공지사항
            </h3>
            <ul>
                <li><a href="../subnotice/subnotice.html" title="해당페이지로 이동">[이벤트] 소설 예수 초성 퀴즈 이벤트</a>[20-04-07]</li>

                <li><a href="#" title="해당페이지로 이동">강의도서 신청양식(구글 설문지)</a>[19-01-11]</li>

                <li><a href="#" title="해당페이지로 이동"> [이벤트] 가쿠다 미쓰요와 함께하는 한·일 공감콘서트</a>[18-11-129]</li>

                <li><a href="#" title="해당페이지로 이동"> [이벤트] 신간 숲속에 잠든 물고기 서평단 모집</a>[18-11-129]</li>
            </ul>

            <a class="moreBtn" href="#" title="공지사항 이동">
                <img alt="더보기" src="${nanam}/images/arr/more_btn.gif">
            </a>
        </li>
    </ul>
</div>
    <ul id=tab>
        <li id="leftTab">
            <a title="새로나온 책 페이지로 이동" href="../newbooks/newbooks.html">새로나온 책</a>
        </li>
        <li id="centerTab">
            <a title="스테디셀러 페이지로 이동" href="../steadyseller/steadyseller.html">스터디 셀러</a>
        </li>
        <li id="rightTab">
            <a title="나남의 책들 페이지로 이동" href="../booksofnanam/booksofnanam.html">나남의 책들</a>
        </li>
    </ul>


<div id="productionList">
    <img id="prevBtn" src="${nanam}/images/bx/arrow_prev.gif" alt="이전으로 가기">
        <div id="innerProductionList">
            <ul class="overFlow" id="leftList">
                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">
                        <img alt="새로나온 책 리스트" src="${nanam}/images/allbook_list/1707061399_R.jpg">
                	      <div> 성의 역사 <span>미셸 푸코</span></div>
                    </a>
                </li>
                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">

                        <img alt="새로나온 책 리스트" src="${nanam}/images/allbook_list/1707062412_R.jpg">
                       <div> 로도스 섬 해변의 흔적 <span>클래런스 글래컨</span></div> 
                    </a>
                </li>
                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">
                        <img alt="새로나온 책 리스트" src="${nanam}/images/allbook_list/1707062435_R.jpg">
                    <div>   인지부조화 이론 (양장) <span>레온 페스팅거</span></div> 
                    </a>
                </li>
                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">

                        <img alt="새로나온 책 리스트" src="${nanam }/images/allbook_list/2005060001_R.jpg">
                       <div> 감시와처벌 <span>강상경</span></div> 
                    </a>
                </li>

                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">

                        <img alt="새로나온 책 리스트" src="${nanam}/images/allbook_list/2004020001_R.jpg">
                         <div>소설예수<span>구영희</span></div> 
                    </a>
                </li>

                <li>
                    <a href="../newbooks/newbooks.html" title="새로나온 책">

                        <img alt="새로나온 책 리스트" src="${nanam }/images/allbook_list/1907230001_R.jpg">
                      <div>법과 세상을 엮다 <span>이주홍 </span></div>
                    </a>
                </li>
            </ul>
            <ul class="overFlow" id="centerList">
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam}/images/steadyseller/1707062430_R.jpg">
                     <div> 청소년을 위한 백범일지 <span>김구</span></div> 
                    </a>
                </li>
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam }/images/steadyseller/1707062460_M1.jpg">
                      <div>한국전쟁의 발발과 기원 <span>박명림</span></div> 
                    </a>
                </li>
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam }/images/steadyseller/1707170001_M1.jpg">
                       <div>직업으로서의 학문 <span>막스 베버</span></div>
                    </a>
                </li>
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam }/images/steadyseller/1802230001_M1.jpg">
                        <div>뉴미디어와 정보 사회 <span>최정호</span></div> 
                    </a>
                </li>
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam}/images/steadyseller/2003020001_M1.jpg">
                         <div>인간행동과 사회환경 <span>이인정, 최해경 지음</span></div>
                    </a>
                </li>
                <li>
                    <a href="../steadyseller/steadyseller.html" title="스테디 셀러">
                        <img alt="스테디셀러 리스트" src="${nanam }/images/steadyseller/1707062410_M1.jpg">
                         <div>경험으로서 예술 1 <span>존듀이</span></div>
                    </a>
                </li>
            </ul>
            <ul class="overFlow" id="rightList">
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/1707062410_M1.jpg">
                         <div>경험으로서 예술 1 <span>존듀이</span></div>
                    </a>
                </li>
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/2003160001_M1.jpg">
                         <div>방송영상미디어 새로 읽기 <span>강형철</span></div>
                    </a>
                </li>
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/2004020001_M1.jpg">
                         <div>소설 예수1 <span>윤석철</span></div>
                    </a>
                </li>
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/2004060001_M1.jpg">
                        <div>소설 예수2 <span>윤석철</span></div>
                    </a>
                </li>
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/2005060001_M1.jpg">
                        <div>감시와 처벌 <span>미셸 푸코</span></div>
                    </a>
                </li>
                <li>
                    <a href="../booksofnanam/booksofnanam.html" title="나남의책들">
                        <img alt="나남의 책들 리스트" src="${nanam }/images/nanamofbook/1707062412_M1.jpg">
                        <div>로도스 섬 해변의 흔적 <span>클래런스 글래컨</span> </div>
                    </a>
                </li>
            </ul>
		</div>
        <img id="nextBtn" src="${nanam }/images/arr/arrow_next.gif" alt="이후로 가기" >
    </div>

<%@ include file="main/footer.jsp" %>