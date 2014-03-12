$(function(){
  $("#sbSearch").submit(function() {
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
                  $('.searchImg').empty();
                  $('#detail-items').empty();
              $(data).find("item").each(function(i){
                  var aa = $(this).find("title").text();
                  var bb = aa.split("<b>").join("");
                  var title = bb.split("</b>").join("");
                  var image = $(this).find("image").text();
                  var min_price = parseInt($(this).find("lprice").text());
                  var link = $(this).find("link").text();
                  var pId = $(this).find("productId").text();
                  
                  var output ='';
                  output += '<figure>';
                  output += '<img src="' + image + '"/><br>';
                  output += '<figcaption> '+ title + '</figcaption><br>';
                  output += '<figcaption style = "float: right;">'+ commaNum(min_price) + '원</figcaption>';
                  output += '</figure> ';
                  $(output).click(function(){
                  	
                    // detail 쪽 div에 아이템 상세정보 표시
                    // 서버에 아이템 등록
                    detail(title, image, min_price, link, pId); 
                    
                  }).appendTo('.searchImg');
              }); 
          }});
       return false;
  });
  
  // 숫자 콤마 넣기
  function commaNum(num) {
  	var len, point, str;  
    
    num = num + "";  
    point = num.length % 3  
    len = num.length;  

    str = num.substring(0, point);  
    while (point < len) {  
        if (str != "") str += ",";  
        str += num.substring(point, point + 3);  
        point += 3;  
    }  
    return str;  
  };
  
});

// 상세 정보
function detail(title, image, min_price, link, pId) {
	
  // 숫자 콤마 넣기`
  function commaNum(num) {
  	var len, point, str;  
    
    num = num + "";  
    point = num.length % 3  
    len = num.length;  
    str = num.substring(0, point);  
    while (point < len) {  
        if (str != "") str += ",";  
        str += num.substring(point, point + 3);  
        point += 3;  
    }  
    return str;  
  };
	
	$('#detail-items').empty();
	
  var ot = '';
  ot += '<h1>' + title + '</h1>';
  ot += '<img src="' + image + '"/>';
  ot += '<h3>NOW PRICE</h3>';
  ot += '<h3 style ="text-align: right">'+ commaNum(min_price) + '원</h3>';
  ot += '<h3>WISH PRICE</h3>';
  ot += '<input type="text" id="wishPrice" placeholder="가격을 입력하세요."><br>';
  ot += '<input type="button" value="등록" id="regButton">';
  $(ot).appendTo('#detail-items');
  
  $('#search-items').hide();
  $('#detail-items').show();
//  console.log(new Date());
  
  $('#regButton').click(function() {
    $.ajax({
      url : 'item/addItem.do',
      type : 'get',
      data : {
        title : title,
        image : image,
        min_price : min_price,
        wish_price : parseInt($('#wishPrice').val()),
        link : link,
        pId : pId,
        reg_date : new Date()
      },
      success : function(data,status){
//      	$.each(data, function(key, value){
//      		
//      	});
//      	var localData = JSON.stringify(data);
//      	window.localStorage.setItem('userItem',localData);
      	var userItemArray = localStorage["userItemArray"];
      	if (!userItemArray) {
      		userItemArray = [];
      		localStorage.setItem("userItemArray", JSON.stringify(data));
      	}else {
      		userItemArray = JSON.parse(userItemArray);
      	}
      	for (var i = 0; i < userItemArray.length; i++) {
      		car key = userItemArray[i];
      		var value = localStrorage[key];
      		add
      	}

      	
      	location.href='must.html';
      },
      error : function() {
      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
      }
    });  
  }); // 등록 버튼 클릭 괄호 
  
  function 
} // 디테일 괄호