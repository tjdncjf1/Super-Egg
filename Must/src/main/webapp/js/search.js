$(function(){
	$('.ui-btn-right').click(function(){
	  localStorage.clear();
	  location.href="must.html";
	});
	
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
                  	$.mobile.changePage('#detail-items');
                    // detail 쪽 div에 아이템 상세정보 표시
                    // 서버에 아이템 등록
                    detail(title, image, min_price, link, pId); 
                    
                  }).appendTo('#searchImage');
              }); 
          }});
       return false;
  });
  
});

// 상세 정보
function detail(title, image, min_price, link, pId) {
	
  $('#detailTitle').html(title);
  $('#detailImage').attr('src', image);
  $('#detailLow').val(min_price);
  $('#detailLink').attr('href', link);
  
  $('#regButton').click(function() {
    $.ajax({
      url : 'item/addItem.do',
      async: 'false',
      type : 'get',
      data : {
        title : title,
        image : image,
        min_price : min_price,
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
      			wish_price: $('#detailWish').val(),
      			reg_date: new Date(parseInt(data.item.reg_date))
      		},
      		success: function(){
      			$('.items').empty();
  					var userNo = parseInt(localStorage.getItem('no'));
  					viewItemList(userNo);
      			$.mobile.changePage('#list-items');
      		} 
      	});
      	
      },
      error : function() {
      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
      }
    });  
  }); // 등록 버튼 클릭 괄호 
  
} // 디테일 괄호