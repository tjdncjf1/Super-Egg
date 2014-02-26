$(function(){
  $("#searchButton").click(function() {
      var keyword = $("#searchValue").val();
       
      if(!keyword) {
          alert("검색어를 입력하세요.");
          return false;
      }
       
      var proxy_url = "http://openapi.naver.com/search?";
          proxy_url += "key=be6c30428660950b9ece4f651a0d2dba";
          proxy_url += "&target=shop";
          proxy_url += "&display=10";
          proxy_url += "&query="+ encodeURIComponent(keyword);
          
      $.ajax({
          url: "proxy.jsp",
          type: "get",
          data: {"url" : proxy_url},
          dataType: "xml",
          success: function(data){
                  console.log(data);
                  $(".portfolio-items").empty();
              $(data).find("item").each(function(){
                  var title = $(this).find("title").text();
                  var image = $(this).find("image").text();
                  var min_price = $(this).find("lprice").text();
                  var high_price = $(this).find("hprice").text();
                  var link = $(this).find("link").text();
                  var pId = $(this).find("productId").text();
                  
                  var html = "<li class='item'><figure><div class='view'><img src='" + image + "'/></div><figcaption><p><span>" + title + "</span></p><p><span>최저가 :" + min_price + "</span></p><div class='date'>" + min_price + "</div></li>";
                  $(html).click(function(){
                  	$.ajax({
                  		url: 'main.html',
                  		type: 'get',
                  		data: {
                  			title: title,
                  			image: image,
                  			min_price: min_price,
                  			high_price: high_price,
                  			link: link,
                  			pId: pId
                  		},
                  		success: function(data){
                  			location.href='main.html';
                  		}
                  	});
                  }).appendTo('.portfolio-items');
              });
          },
          error: function(xhr, message, errorThrown){
              var msg = xhr.status + " / " + message + " / " + errorThrown;
              alert(msg);
          }
      });
       return false;
  });
});