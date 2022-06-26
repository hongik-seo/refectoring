$(document).ready(function(){
  
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


  $("div.menu li").on("mouseenter", function () {
    // alert("안녕");
    $(this).append("<div class='boxColor'></div>");


  });

  $("div.menu li").on("mouseleave", function () {
    // alert("안녕");
    $(".boxColor").remove();


  });




    

  });



