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
                  $('.searchImg').empty();
                  $('#detail-items').empty();
              $(data).find("item").each(function(i){
                  var title = $(this).find("title").text();
                  var image = $(this).find("image").text();
                  var min_price = parseInt($(this).find("lprice").text());
                  var link = $(this).find("link").text();
                  var pId = $(this).find("productId").text();
                  
                  var output ='';
                  output += '<img src="' + image + '"/>';
                  $(output).click(function(){
                  	
                    // detail 쪽 div에 아이템 상세정보 표시
                    // 서버에 아이템 등록
                    detail(title, image, min_price, link, pId); 
                    
                  }).appendTo('.searchImg');
              }); 
          }});
       return false;
  });
});

// 상세 정보
function detail(title, image, min_price, link, pId) {
	$('#detail-items').empty();
	
  var ot = '';
  ot += '<img src="' + image + '"/>';
  ot += '<p><h2>' + title + '</h2></p>';
  ot += '<p><h3>최저가: ' + min_price + '</h3></p>';
  ot += '<h3>희망가격: <input type="number" id="wishPrice"><br></h3>';
  ot += '<p><h3>상품코드: ' + pId + '</h3></p>';
  ot += '<input type="button" value="등록" class="reg_button">';
  $(ot).appendTo('#detail-items');
  
  $('#search-items').hide();
  $('#detail-items').show();
 
  $('.reg_button').click(function() {
  	confirm('등록하시겠습니까?');
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
      success : function(){
      	location.href='must.html';
      },
      error : function() {
      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
      }
    });  
  }); // 등록 버튼 클릭 괄호 
  
} // 디테일 괄호