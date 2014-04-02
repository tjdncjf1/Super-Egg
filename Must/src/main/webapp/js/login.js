var baseUrl = 'http://localhost:9991/Must/';

//숫자 콤마 넣기
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

function viewItemList(userNo) {
	$.ajax({
		url: baseUrl + 'item/userItemList.do',
//		url: 'item/userItemList.do',
		type: 'get',
		async: 'false',
		data: {
			uNo: userNo
		}, 
		success: function(userItems){
			console.log(userItems);
			$.each(userItems.jsonResult.data, function(i, item){

				var listItem = '';
				listItem += '<li><div class="itemSpace">';
				listItem += '<span id="titlePosition">' + item.title + '</span>';
				listItem += '<div class="viewImg" id="swipeItem">';
				listItem += '<img src="' + item.image + '" class="image" />';
				listItem += '</div></div></li>';

				$(listItem).click(function(){
					$.ajax({
						url: baseUrl + 'item/choiceUserItem.do',
//						url: 'item/choiceUserItem.do',
						type: 'get',
						async: 'false',
						data: {
							userNo : parseInt(localStorage.getItem('no')),
							prodId : item.pId
						},
						success: function(d){
//							console.log(d.jsonResult.data[0]);
							var choiceItem = d.jsonResult.data[0];
//							$.mobile.changePage('#select-items');
							$('.selectTitle').html(choiceItem.title);
							$('.selectImage').attr('src', choiceItem.image);
							$('#lPrice').val(commaNum(choiceItem.min_price));
							$('#wPrice').val(commaNum(choiceItem.loginUserItem.wPrice));
							$('#selectLink').attr('href', choiceItem.link);
							console.log(typeof commaNum(choiceItem.loginUserItem.wPrice));

							$.mobile.changePage('#select-items');

							$('#wish_update').click(function(){
								$(this).unbind('click');
								$.ajax({
									url: baseUrl + 'item/wishUpdate.do',
//									url: 'item/wishUpdate.do',
									type: 'get',
									async: 'false',
									data: {
										pId: choiceItem.pId,
										wish_price: parseInt($('#wPrice').val())
									},
									success: function() {
										/*alert('변경 성공했습니다.');*/
//										$(this).unbind('click');
										$.mobile.changePage('#list-items');
									},
									error: function() {
										console.log('오류');
									}
								}); 
							}); // end of wish_update click

						}
					}); // choiceUserItem ajax 끝

				}).bind('swiperight', function(event){
					$(event.target).addClass('swipe');
					$.mobile.changePage('#list-items', {transition:'slide', reverse: false}, true, true);
					var answer = confirm('삭제하시겠습니까?');
					if (answer) {
						$.ajax({
							url: baseUrl + 'item/userItemDelete.do',
							type: 'get',
							async: 'false',
							data: {
								userNo: parseInt(localStorage.getItem('no')),
								pId: item.pId
							},
							success: function(){
//								$('.items').listview('refresh');
//								$.mobile.changePage('#list-items', {
//								allowSamePageTransition: true,
//								transition: 'none',
//								reloadPage: true
//								});
								$('.items').empty();
								viewItemList(parseInt(localStorage.getItem('no')));
							}
						});
					} else {
//						$('.items').listview('refresh');
					}
				}).appendTo('.items');

				$('#list-items').click(function(event){
					$('#swipeItem').removeClass('swipe');
				});

			}); // each
		} // success
	}); // end of ajax
};

$(function(){
//	if (localStorage.getItem('no'))
//	console.log(localStorage.getItem('no') == null);
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

	if (localStorage.getItem('no') == null) {
		$('#userLogin').click(function(){
			$.ajax({
				url: baseUrl + "user/login.do",
//				url: "user/login.do",
				type:"POST",
				data : {
					email:$('#loginEmail').val(),
					password:$('#loginPassword').val()

				},
				success: function(no){
					console.log(no);
					if (no.jsonResult.data.length == 0) {
						/*alert('로그인 정보가 일치하지 않습니다.');*/
						return false; 
					} else {
						localStorage.setItem('email', $('#loginEmail').val());
						localStorage.setItem('password', $('#loginPassword').val());
						localStorage.setItem('no', parseInt(no.jsonResult.data[0].no));

						var userNo = parseInt(localStorage.getItem('no'));
						viewItemList(userNo);
						$.mobile.changePage('#list-items');
					}
				}
			});
		});
	} else {

		$('#loginEmail').val(localStorage.getItem('email'));
		$('#loginPassword').val(localStorage.getItem('password'));

		$('#userLogin').click(function(){
			$.ajax({
				url: baseUrl + "user/login.do",
//				url: "user/login.do",
				type:"POST",
				data : {
					email:$('#loginEmail').val(),
					password:$('#loginPassword').val()

				},
				success: function(no){
					var userNo = parseInt(localStorage.getItem('no'));
					viewItemList(userNo);
					$.mobile.changePage('#list-items');
				}
			});
		});
	}
});