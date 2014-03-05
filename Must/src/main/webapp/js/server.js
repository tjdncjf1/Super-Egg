$(function() {
	
	setTimeout(function(){
		$.ajax({
	  	url: 'item/list.do',
	  	type: 'get',
	  	success: function(items){
	  		$.each(items.jsonResult.data, function(index, item){
	  				var stitle = item.title;
	  				var spId = item.pId;
	  				
	  				 var proxy_url = "http://openapi.naver.com/search?";
	           proxy_url += "key=be6c30428660950b9ece4f651a0d2dba";
	           proxy_url += "&target=shop";
	           proxy_url += "&display=10";
	           proxy_url += "&query="+ encodeURIComponent(stitle);
	           
	  				$.ajax({
	            url: "proxy.jsp",
	            type: "get",
	            data: {"url" : proxy_url},
	            dataType: "xml",
	            success: function(data){
	            	console.log(data);
//	            	$(data).find("item").each(function(i){
//                  var min_price = parseInt($(this).find("lprice").text());
//                  var pId = $(this).find("productId").text();
//                  
//                  console.log(min_price);
//	            });
	  					
	  				}});
	  			
	  			}); // each 괄호
	  		
	  	}}); // success 괄호
	  }, 1000);	// $.ajax 괄호
	});
 