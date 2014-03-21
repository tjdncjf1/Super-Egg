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
                  $('#searchImage').empty();
              $(data).find("item").each(function(i){
                  var boldTitle = $(this).find("title").text();
                  var boldFDel = boldTitle.split("<b>").join("");
                  var title = boldFDel.split("</b>").join("");
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
                    
                  }).appendTo('#searchImage');
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
	
  var ot = '';
	ot += '<div data-role="content">';
  ot += '<h1>' + title + '</h1>';
  ot += '<img src="' + image + '"/>';
  ot += '<h3>NOW PRICE</h3>';
  ot += '<h3 style ="text-align: right">'+ commaNum(min_price) + '원</h3>';
  ot += '<h3>WISH PRICE</h3>';
  ot += '<input type="text" id="wishPrice" placeholder="가격을 입력하세요."><br>';
  ot += '<input type="button" value="등록" id="regButton"></div>';
  $(ot).appendTo('#detail-items');
  
  $('#regButton').click(function() {
    $.ajax({
      url : 'item/addItem.do',
      type : 'get',
      data : {
        title : title,
        image : image,
        min_price : min_price,
//        wish_price : $('#wishPrice').val(),
        link : link,
        pId : pId,
        reg_date : new Date()
      },
      success : function(data) {
//      	console.log('성공');
      	$.ajax({
      		url: 'item/userItemAdd.do',
      		type: 'get',
      		data: {
      			no: parseInt(window.localStorage.getItem('no')),
      			pId: data.item.pId,
      			wish_price: $('#wishPrice').val(),
      			reg_date: new Date(parseInt(data.item.reg_date))
      		},
      		success: function(){
      			location.href='must.html';
      		} 
      	});
      	
//      	location.href='must.html';
      },
      error : function() {
      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
      }
    });  
  }); // 등록 버튼 클릭 괄호 
  
} // 디테일 괄호