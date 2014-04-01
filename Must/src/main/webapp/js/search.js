$(function(){
	$('.ui-btn-right').click(function(){
		localStorage.clear();
		location.href= "must.html";
	});

	$('.ui-btn-left').click(function(){
		$('#searchImage').empty();
		$('#searchValue').val(null);
		$('#detailWish').val(null);
		$.mobile.changePage('#search-items');
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
			url:  "proxy.jsp",
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
//						alert('okok');
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

function commaNum(num) {
	var len, point, str;  

	num = num + "";  
	point = num.length % 3  
	len = num.length;  
	str = num.substring(0, point);  
	while (point < len) {  
		if (str != "") {
			str += ",";  
		}
		str += num.substring(point, point + 3);  
		point += 3;  
	}  
	return str;  
};

//상세 정보
function detail(title, image, min_price, link, pId) {

	console.log(commaNum(min_price));

	$('#detailTitle').html(title);
	$('#detailImage').attr('src', image);
	$('#detailLow').val(commaNum(min_price));
	$('#detailLink').attr('href', link);

	$('#regButton').click(function() {
		$(this).unbind('click');
		$.ajax({
			url: baseUrl + 'item/userItemCheck.do',
//			url: 'item/userItemCheck.do',
			async: 'false',
			type: 'get',
			data: {
				userNo: parseInt(localStorage.getItem('no')),
				buyPid: pId
			},
			success: function(list){
				console.log(list.jsonResult.data.length == 0);

				if (list.jsonResult.data.length == 0) {
					console.log(list);
					$.ajax({
						url : baseUrl + 'item/addItem.do',
//						url : 'item/addItem.do',
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
//							console.log('성공');
							$.ajax({
								url: baseUrl + 'item/userItemAdd.do',
//								url: 'item/userItemAdd.do',
								type: 'get',
								data: {
									no: parseInt(window.localStorage.getItem('no')),
									pId: data.item.pId,
									wish_price: parseInt($('#detailWish').val()),
									reg_date: new Date(parseInt(data.item.reg_date))
								},
								success: function(){
									$('.items').empty();
									var userNo = parseInt(localStorage.getItem('no'));
									viewItemList(userNo);
									$.mobile.changePage('#list-items');
								} 
							});

						}
					});
				} else {
					console.log(list);
					alert('중복된 상품입니다.');
//					console.log('중복된 상품입니다.');
					$.mobile.changePage('#list-items');
				}
			} // success 끝
		});

	}); // 등록 버튼 클릭 끝 

}; // 디테일 괄호