$(function(){

//토글 메뉴


	var dropDownImg=$("Img#dropDownImg");

	dropDownImg.on("click",function(){

		$("div#dropDownMenu").toggle();

	});

	var dropFirst=$("li#dropFirst");




	dropFirst.on("mouseover",function(){
		$("li.dropF").toggle();

	});

	/*dropFirst.on("mouseleave",function(){
    $("li.dropF").hide();

  });*/

	var dropSecond=$("li#dropSecond");

	dropSecond.on("mouseover",function(){
		$("li.dropS").toggle();

	});

	/* dropSecond.on("mouseleave",function(){
    $("li.dropS").hide();

  });*/


	//배너
	$('.skitter-large').skitter();

	// 탭매뉴 보여주기
	$("li#leftTab").on("mouseenter",function(){
		$("ul#leftList").css("display","block");
		$("ul#rightList").css("display","none");
		$("ul#centerList").css("display","none");
	});


	$("li#centerTab").on("mouseenter",function(){
		$("ul#leftList").css("display","none");
		$("ul#rightList").css("display","none");
		$("ul#centerList").css("display","block");

	});

	$("li#rightTab").on("mouseenter",function(){
		$("ul#leftList").css("display","none");
		$("ul#rightList").css("display","block");
		$("ul#centerList").css("display","none");

	});


	$("div.menu li").on("mouseenter", function(){
		// alert("안녕");
		$(this).append("<div class='boxColor'></div>");


	});

	$("div.menu li").on("mouseleave", function(){
		// alert("안녕");
		$(".boxColor").remove();


	});
	// 1. 대상이 호버됬을때 새로운 div요소가 추가된다.

	// 2. 대상에서 벗어낫을때 그 div요소가 삭제, css~~



	// 상품리스트 좌우로 움직이기
	$("img#prevBtn").click(function(){

		$("ul#leftList").children("li:eq(0)").appendTo("ul#leftList");


		$("ul#centerList").children("li:eq(0)").appendTo("ul#centerList");

		$("ul#rightList").children("li:eq(0)").appendTo("ul#rightList");

	});

	$("img#nextBtn").click(function(){

		$("ul#leftList").children("li:last").prependTo("ul#leftList");


		$("ul#centerList").children("li:last").prependTo("ul#centerList");

		$("ul#rightList").children("li:last").prependTo("ul#rightList");

	});




});



