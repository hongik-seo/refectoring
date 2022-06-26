
  
  	
  	
  	//var flag=true;
  	
  $(function(){
  
	  	 $("input[type='checkbox']").prop("checked",true);
	  		
	  		
	
	  		
	  	
	  	
	  	
	  
  		$("#allCartCheck").click(function(){
  			
  			var chk= $(this).is(":checked");
  		      
  		        if(chk) {
  		        	
  		       	$("input[type='checkbox']").prop('checked', true);
  		        
  		     	
  		        
  		        }else  {
  		        $("input[type='checkbox']").prop('checked', false);
  				
  		  
  		     	
  				}
  		        
  		        
  		      
  		        
  			
  		});
  		
  	
  		
  		
  		
  	//아이디 부여
  	
  	$(".clearBtn").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	
  	
  	$(".cartList").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	
  	
  	$(".bookPrice").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  
  	$(".bookNum").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	
  	$(".plus").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	$(".numPlusBtn").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	
 	$(".removeBtn").each(function(index) {
        $(this).attr("id", this.id + index);
 	});
  	
  	
  	
 	
  	
  	
  	
  ///기본 가격구하기

 
 
  	var choice = $(".bookNum").val();
	var leng = $(".eachPrice").length;
	
	//var t=$("input[name ^='bookPrice']").val();
	
	var arr=new Array(leng);
	
	var t= $("input[name *='bookPrice']").val();
	for(var i=0; i<arr.length; i++){
		
		arr[i]=t
		
		console.log(arr[i]);
	}
	
	
	alert(leng)
	
	alert(t);
	
	
	var leng=$(".bookNum").length;
	
	

	$.ajax({
		type : 'get',
		
		url : "/nanam/pay/originalBookPrice",
		data : {
			"num" : choice,
			"bookPrice" : bookPrice,
			"leng":leng
			
			

		},

		success : function(data) {
			console.log("request succeeded");
			
		
			

		},
		error : function(xhr, ajaxOptions, thrownError) {
			console && console.log("request failed");
		},

	});//endOfAjax
  	
  	
  
	
			$(".numPlusBtn").each(function(i) {

					$("#numBtn"+i).click(function() {

						var choice = $(".bookNum").eq(i).val();
						var bookPrice = $(".bookPrice").eq(i).val();
						var allPrice = $("#allPrice").text();

						$.ajax({
							type : 'get',
							dataType : 'text',
							url : "/nanam/pay/totalBookNum",
							data : {
								"num" : choice,
								"bookPrice" : bookPrice,
								"allPrice" : allPrice
								

							},

							success : function(data) {
								console.log("request succeeded");
								
								parseInt(data);
								alert('data load ' + data);
								
								$("#allPrice").html(data);
								

							},
							error : function(xhr, ajaxOptions, thrownError) {
								console && console.log("request failed");
							},

						});//endOfAjax

					});
					
			});
					
			
			
			
			$("input[type='checkbox']").each(function(i) {
				$("#plusBook"+i).click(function(){
					
					console.log(i);
					var choice = $(".bookNum").eq(i).val();
					var bookPrice = $(".bookPrice").eq(i).val();
					var allPrice = $("#allPrice").text();
					var chk= $(this).is(":checked");
					
					//var chcked;
					var flag="0"
					//var nonChcked;
					if(chk) {
	  		        	
		  		       flag="1";
		  		       
		  		     	
		  		        
		  		        }else {
		  		        flag="0";
		  				
		  		      
		  		     	
		  				}
					
					
					$.ajax({
						type : 'get',
						dataType : 'text',
						url : "/nanam/pay/checkBookNum",
						data : {
							"flag":flag,
							"num" : choice,
							"bookPrice" : bookPrice,
							"allPrice" : allPrice
						},

						success : function(data) {
							console.log("request succeeded");
							
							parseInt(data);
							alert('data load ' + data);
							
							
							
							if($("#allCartCheck").is(":checked")==false){
								
									data=0;
								
							}
							
							if(flag=="0"){
								
								$("#numBtn"+i).eq(0).prop("disabled",true);
								$("#num"+i).eq(0).prop("disabled",true);
								
							}else if(flag=="1"){
								
								$("#numBtn"+i).eq(0).prop("disabled",false);
								$("#num"+i).eq(0).prop("disabled",false);
							}
								
							$("#allPrice").html(data);
							
							
						},
						error : function(xhr, ajaxOptions, thrownError) {
							console && console.log("request failed");
						},

					});//endOfAjax
					
					
					
					
				});
					
					
					
					
					
					
					

				});

				
		
			$(".removeBtn").each(function(i) {

				
				
				$("#deleteBtn"+i).click(function() {

					var choice = $(".bookNum").eq(i).val();
					var bookPrice = $(".bookPrice").eq(i).val();
					var allPrice = $("#allPrice").text();

					$.ajax({
						type : 'get',
						dataType : 'text',
						url : "/nanam/pay/deleteBookList",
						data : {
							"num" : choice,
							"bookPrice" : bookPrice,
							"allPrice" : allPrice
							

						},

						success : function(data) {
							console.log("request succeeded");
							parseInt(data);
							alert('data load ' + data);
							
							$("#allPrice").html(data);
							
							$("#list"+i).remove();
							
							$("totalPrice").attr("value",data);
							

						},
						error : function(xhr, ajaxOptions, thrownError) {
							console && console.log("request failed");
						},

					});//endOfAjax

				});
				

			});

			
		
			
			
			
			
			
			
			
			
			
			
			
			
			});//endOfscripts
		














