<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../main/header.jsp"%>
    

<link rel="stylesheet" type="text/css" href="${nanam}/css/inputBook.css">

<script>

	$(function (){

		$("#searchBtn").on("click", function(){


			let kwd=$("#keyword").val();
			let pageNum=$("#pageNum").val();

			let data={
				"kwd" :kwd,
				"pageNum" : pageNum

			}

			$.ajax({
				type: 'post',
				dataType: 'JSON',
				url: "/book/uploadBook",
				data: {"searchBook": JSON.stringify(data)},
				success: function (data) {
					console.log("request succeeded");

					let rdata = new Array();

					    rdata = data.parsing;

					let resultData =new Array();

					let tittles ="";
					let authors = "";
					let years = "";
					let isbns = "";
					let pubInfos ="";
					for(let i=0; i<rdata.length; i++) {

						resultData[i] = rdata[i];

						console.log(resultData[i])
						tittles += "<option>"
						tittles += resultData[i].BOOK_PUB_TITLE_INFO
						tittles += "</option>";

						authors += "<option >"
						authors += resultData[i].BOOK_AUTHOR_INFO
						authors += "</option>";

						years += "<option >"
						years += resultData[i].BOOK_PUB_YEAR_INFO
						years += "</option>";

						isbns += "<option >"
						isbns += resultData[i].BOOK_ISBN
						isbns +="</option>"

						pubInfos += "<option >"
						pubInfos += resultData[i].BOOK_PUB_INFO
						pubInfos += "</option>";
						
					}

					document.getElementById("tittles").innerHTML = tittles;
					document.getElementById("authors").innerHTML = authors;
					document.getElementById("years").innerHTML = years;
					document.getElementById("isbns").innerHTML = isbns;
					document.getElementById("pubInfos").innerHTML = pubInfos;

					$("#authors").prop('disabled', true);
					$("#years").prop('disabled', true);
					$("#isbns").prop('disabled', true);
					$("#pubInfos").prop('disabled', true);


				},
				error: function (xhr, ajaxOptions, thrownError) {
					console && console.log("request failed");
				},
			});//endOfAjax


		})

	});

	function selectedOption(){

		var index = $("#tittles option").index($("#tittles option:selected"));

		$("#authors option:eq("+index+")").attr("selected", "selected");
		$("#years option:eq("+index+")").attr("selected", "selected");
		$("#isbns option:eq("+index+")").attr("selected", "selected");
		$("#pubInfos option:eq("+index+")").attr("selected", "selected");

	}

</script>
<script>

	function writeBook(){

		let id = $("input[name='id']").val();
		let login_id = $("input[name='login_id']").val();
		let list = $("select[name='list'] option:selected").val();
		let tittles = $("select[name='tittles'] option:selected").val();
		let authors = $("select[name='authors'] option:selected").val();
		let years = $("select[name='years'] option:selected").val();
		let isbns = $("select[name='isbns'] option:selected").val();
		let pubInfos = $("select[name='pubInfos'] option:selected").val();
		let bookPrice = $("input[name='bookPrice']").val();
		let bookContent = $("textarea[name='bookContent']").val();
		let bookIndex = $("textarea[name='bookIndex']").val();
		let bookAuthorIntroduce = $("textarea[name='bookAuthorIntroduce']").val();
		let imgFile = $("input[name='file'] ").val();

		let writeInfo = {
			"id" : id,
			"login_id" :login_id,
			"list":list,
			"tittles":tittles,
			"authors":authors,
			"years" : years,
			"isbns":isbns,
			"pubInfos":pubInfos,
			"bookPrice":bookPrice,
			"bookContent":bookContent,
			"bookIndex" : bookIndex,
			"bookAuthorIntroduce":bookAuthorIntroduce,
			"file" : imgFile,
		}


		$.ajax({
			type: 'GET',
			dataType: 'JSON',
			url: "/book/writeBook",
			data: {"writeInfo" : JSON.stringify(writeInfo)},
			success: function (data) {
				console.log("request succeeded");
				console.log(data)

				let htmls = "";

				let listFlag =data.BOOK_LIST;

				htmls += "<form name='moveForm' action='/book/bookList/"+listFlag+"' method='get'>";
				htmls += "<input type='text' name='list' value='"+listFlag+"' style='display: block'/>" ;
				htmls += "<input type='text' name='returnFlag' value='"+data.result+"' style='display: block'/>" ;
				htmls += "<input type='submit' value='쓴글 확인하러가기' />" ;
				htmls += "</from>";

				$("#confirmBook").append(htmls);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				console && console.log("request failed");
			},
		});

	}
</script>


</script>
<h2>책 글쓰기</h2>

<div id="bookAllWrap">
	<div class="basebar">
		<ul>
			<li><a href="#">메인</a></li>
			<li>></li>
			<li><a href="#">출판</a></li>
			<li>></li>
			<li><a href="#">관리자 페이지</a></li>
		</ul>
	</div>
	<div>
		<h2>업로드 book</h2>
		<select style="width: 150px;" name="list" id="list">
			<option value='new'>새로나온 책</option>
			<option value='steady'>스테디 셀러</option>
			<option value='nanam'>나남의 책</option>
		</select>
	</div>
	<div id="bookWrap">
		<p>키워드 입력</p>
		<input type="text" id="keyword" name="keyword">
		<p>검색할 페이지 숫자 입력</p>
		<input type="text" id="pageNum" name="pageNum">

		<input type="button"  id="searchBtn" name="btn" value="책 찾기">
	</div>
	<div  id="inHere">
		<h2>책 제목</h2>
		<div id="tittleDiv" >
			<select style='width: 150px;' name='tittles' id='tittles' onchange='selectedOption()' >

			</select>
		</div>

		<h2>책 저자</h2>
		<div id="authorDiv">
			<select style='width: 150px;' name='authors' id='authors'>

			</select>
		</div>

		<h2>책 발행일자</h2>
		<div id="yearDiv">
			<select style='width: 150px;' name='years' id='years'>

			</select>
		</div>

		<h2>책 ISBN</h2>
		<div id="isbnDiv">
			<select style='width: 150px;' name='isbns' id='isbns'>

			</select>
		</div>

		<h2>책 출판사</h2>
		<div id="pubInfoDiv">
			<select style='width: 150px;' name='pubInfos' id='pubInfos'>

			</select>
		</div>


	</div>
	<div>

		<p>책 가격</p>
		<input type="text" id="bookPrice" class="write" name="bookPrice">
		<br>

		<p>본문</p>
		<textarea  name="bookContent" class="write" cols="40" rows="8"></textarea>

		<p>목차 </p>
		<textarea  name="bookIndex" class="write" cols="40" rows="8"></textarea>

		<p>저자소개 </p>
		<textarea  name="bookAuthorIntroduce" class="write" cols="40" rows="8"></textarea>

		<p>이미지 올리기 </p>
		<input type="file" id="file" class="write" name="file">
		<br>
		<button type="submit" class="inputBtn" onclick="writeBook()" >등록</button>
		<button type="reset" class="inputBtn">취소</button>
		<input type="text" name = 'id'  value="${sessionScope.id}" style="display: none">
		<input type="text" name = 'login_id'  value="${sessionScope.login_id}" style="display: none">
	</div>
	<div id="confirmBook">


	</div>
</div>

<%@ include file="../main/footer.jsp"%>
