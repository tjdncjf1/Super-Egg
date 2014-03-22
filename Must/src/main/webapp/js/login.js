//숫자 콤마 넣기`
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
		url: 'item/userItemList.do',
		type: 'get',
		data: {
			uNo: userNo
		},
		success: function(userItems){
			console.log(userItems);
//			$('.items').listview('refresh');
			$.each(userItems.jsonResult.data, function(i, item){

				var listItem = '';
				listItem += '<li><div class="itemSpace">';
				listItem += '<span id="titlePosition">' + item.title + '</span>';
				listItem += '<div class="viewImg">';
				listItem += '<img src="' + item.image + '" class="image" />';
				listItem += '</div></div></li>';

				$(listItem).click(function(){ 
//					$('.moreInfo').empty();
//					location.href="#select-items";
					$.mobile.changePage('#select-items');
					$('.selectTitle').html(item.title);
					$('.selectImage').attr('src', item.image);
					$('#wPrice').val(commaNum(item.loginUserItem.wPrice));
					$('#lPrice').val(commaNum(item.min_price));

					$('#wish_update').click(function(){
						$.ajax({
							url: 'item/wishUpdate.do',
							type: 'get',
							data: {
								pId: item.pId,
								wish_price: $('#wPrice').val()
							},
							success: function() {
//								alert('변경했습니다.');
								$.mobile.changePage('#select-items');
							}
						}); 
					}); // end of wish_update click

					var jsondata = $.ajax({
						url: 'chart/selectDay.do',
						type: 'get',
						data: {
							pId: item.pId 
						},
						success: function(list) {
							var dayList = list.jsonResult.data;
							console.log(dayList);
							var dayLabel = new Array();
							var dayData = new Array();
							var dprice = null;
							for(var i = 0; i < dayList.length; i++) {
								dprice = dayList[i];
								$.each(dprice, function(key,value){
									if(key === "time") {
//										console.log(list.jsonResult.data[i].time);
										var bj = new Date(list.jsonResult.data[i].time);
//										console.log(bj.getFullYear());
										var cj = bj.getFullYear() + '-' + (bj.getMonth()+1) + '-' + bj.getDate();	
										dayLabel.push(cj);
									}

									if(key === "price") {
										dayData.push(value);
									}
								});
							};
							dayChart(dayLabel, dayData);
						}
					});
					function dayChart(dayLabel,dayData) {
						var dayChart = [];
						for(var i = 0 ; i < dayLabel.length ; i += 1) {
							dayChart.push([dayLabel[i],dayData[i]]);
						}
						var plot = $.jqplot("dayChart", [dayChart],{
							animate:true,
							animateReplot: true,
							axes:{
								xaxis:{
									renderer: $.jqplot.DateAxisRenderer,
									tickOptions:{
										formatString:'%b&nbsp;%#d'
									}
								},
								yaxis:{
									tickOptions:{
										formatString:'%d원'
									}
								}
							},
							highlighter: {
								show: true,
								sizeAdjust: 7.5
							},
							cursor: {
								show: false
							}
						});
					};
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
		location.href="must.html";
	});

	if (localStorage.getItem('no') == null) {
		$('#userLogin').click(function(){
			$.ajax({
				url: "user/login.do",
				type:"POST",
				data : {
					email:$('#loginEmail').val(),
					password:$('#loginPassword').val()

				},
				success: function(no){
//					console.log(no.jsonResult.data[0].no);
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