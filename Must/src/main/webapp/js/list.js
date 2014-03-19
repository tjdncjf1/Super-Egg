$(function() {
	
//	var items = JSON.parse(localStorage.getItem('item'));
//	console.log(items.length);
//	$.each(items, function(i){
//		console.log(items[i].title);
	var userNo = parseInt(localStorage.getItem('no'));
	
	$.ajax({
		url: 'item/userItemList.do',
		type: 'get',
		data: {
			uNo: userNo
		},
		success: function(userItems){
			$.each(userItems.jsonResult.data, function(i, item){
				var listItem = '';
				listItem += '<ul class="items">';
				listItem += '<li><div class="itemSpace">';
				listItem += '<span>' + item.title + '</span>';
				listItem += '<div class="viewImg">';
				listItem += '<img src="' + item.image + '" class="image"><hr>';
				listItem += '</div></div></li></ul>';
				
				$(listItem).click(function(){
					$('#list-items').hide();
					$('#listwindow').show();
					$('#select-items').show();
					
					var selectItem = '';
		  		selectItem += '<div class="moreInfo">';
		  		selectItem += '<h3 style="margin: 0 auto">' + item.title + '</h3>';
		  		selectItem += '<img src="' + item.image + '" class="image" style="border: 2px solid yellow;" /><br>';
		  		selectItem += '<span>희망가격: <input type="number" value=' + item.loginUserItem.wPrice + ' id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
		  		selectItem += '<span>최저가격: <input type="text" value=' + item.min_price + ' readonly="readonly" /></span>';
		  		selectItem += '<div id="dayChart" style=" margin: 0 auto">';
		  		selectItem += '</div></div>';
		  		$(selectItem).appendTo('#select-items');
						
		  		$('#wish_update').click(function(){
		  			 $.ajax({
		  			  url: 'item/wishUpdate.do',
		  			  type: 'get',
		  			  data: {
		  			  	pId: item.pId,
		  			  	wish_price: $('#wPrice').val()
		  			  },
		  			  success: function() {
		  			  	alert('변경했습니다.');
//		  			  	location.href = 'must.html';
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
//			  								console.log(typeof list.jsonResult.data[i].time);
			  			  				var bj = new Date(list.jsonResult.data[i].time);
//			  			  				console.log(bj.getFullYear());
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
		  				
				}).appendTo('#list-items');
				
				
			}); // each
		} // success
	}); // end of ajax
	 
});