//var baseUrl = 'http://175.196.13.97:9997/Must/';

//DayChart 
function dayChartInsert(dayLabel,dayData) {
	var dChart = [];
	console.log(dayLabel[0]);
	console.log(dayData[0]);
	for(var i = 0 ; i < dayLabel.length ; i++) {
		dChart.push([dayLabel[i],dayData[i]]);
		console.log(dChart[i]);
	}
	if (dChart != null) {
		console.log(dChart.length);
		var plot = $.jqplot("dayChart", [dChart], {
			animate:true,
			animateReplot: true,
			axes:{
				xaxis:{
					renderer: $.jqplot.DateAxisRenderer,
//					tickOptions:{formatString:'%b&nbsp %#d'},
					tickOptions:{formatString:'%b&nbsp/%#d'},
					tickInterval: '1 day'
				},
				yaxis:{
					tickOptions:{formatString:'%d원'}
				}
			},
			highlighter: {
				show: true,
				sizeAdjust: 7.5
			},
			cursor: {
				show: false
			},
			series:[{lineWidth:4, markerOptions:{style:'square'}}]
		});
	} 
};

//숫자 콤마 넣기
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

function viewItemList(userNo) {
	$.ajax({
//		url: baseUrl + 'item/userItemList.do',
		url: 'item/userItemList.do',
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
				listItem += '<div class="viewImg">';
				listItem += '<img src="' + item.image + '" class="image" />';
				listItem += '</div></div></li>';

				$(listItem).click(function(){
					$.ajax({
//						url: baseUrl + 'item/choiceUserItem.do',
						url: 'item/choiceUserItem.do',
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
							console.log(commaNum(choiceItem.loginUserItem.wPrice));
							$('#wPrice').val(commaNum(choiceItem.loginUserItem.wPrice));
							$('#lPrice').val(commaNum(choiceItem.min_price));

							$.ajax({
//								url: baseUrl + 'chart/selectDay.do',
								url: 'chart/selectDay.do',
								type: 'get',
								async: 'false',
								data: {
									pId: choiceItem.pId 
								},
								success: function(list) {
									$('#chartPosition').empty();
									var cc = '';
									cc += '<div id="dayChart" style="height:300px; width:650px;"></div>';
									$(cc).appendTo('#chartPosition');
									console.log(list);

									var dayList = list.jsonResult.data;
//									console.log(dayList);
									var dayLabel = [];
									var dayData = [];
									var dprice = [];
									for(var i = 0; i < dayList.length; i++) {
										dprice = dayList[i];
										$.each(dprice, function(key,value){
											if(key == "time") {
//												console.log(list.jsonResult.data[i].time);
												var bj = new Date(list.jsonResult.data[i].time);
//												console.log(bj.getFullYear());
												var cj = bj.getFullYear() + '-' + (bj.getMonth()+1) + '-' + bj.getDate();	
												dayLabel.push(cj);
											}
											if(key == "price") {
												dayData.push(value);
											}

										});
									}
									dayChartInsert(dayLabel, dayData);

								} // success 끝
							}); // selectDay ajax 끝

							$('#wish_update').click(function(){
								$(this).unbind('click');
								$.ajax({
//									url: baseUrl + 'item/wishUpdate.do',
									url: 'item/wishUpdate.do',
									type: 'get',
									async: 'false',
									data: {
										pId: choiceItem.pId,
										wish_price: $('#wPrice').val()
									},
									success: function() {
										alert('변경 성공했습니다.');
//										$(this).unbind('click');
										$.mobile.changePage('#list-items');
									},
									error: function() {
										console.log('오류');
									}
								}); 
							}); // end of wish_update click



							$.mobile.changePage('#select-items');

						}

					}); // choiceUserItem ajax 끝

				}).appendTo('.items');

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
//				url: baseUrl + "user/login.do",
				url: "user/login.do",
				type:"POST",
				data : {
					email:$('#loginEmail').val(),
					password:$('#loginPassword').val()

				},
				success: function(no){
					console.log(no);
					if (no.jsonResult.data.length == 0) {
						alert('로그인 정보가 일치하지 않습니다.'); 
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
//				url: baseUrl + "user/login.do",
				url: "user/login.do",
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