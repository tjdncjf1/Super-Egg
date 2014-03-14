$(function() {
	 $.ajax({
	  	url: 'item/list.do',
	  	type: 'get',
	  	success: function(items){
	  		console.log(items);
	  		$.each(items.jsonResult.data, function(index, item){
	  			
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
	  				selectItem += '<span>희망가격: <input type="number" placeholder=' + item.wish_price + ' id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
	  				selectItem += '<span>최저가격: <input type="text" value=' + item.min_price + ' readonly="readonly" /></span>';
	  				selectItem += '<div id="chart7" style="min-width: 310px; height: 400px; margin: 0 auto">';
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
		  		  			location.href = 'must.html';
		  		  		}
		  		  	}); 
		  		  }); 
	  				
	  				$.ajax({
		  				url: 'chart/selectDay.do',
		  				type: 'get',
		  				data: {
		  					pId: item.pId
		  				},
		  				success: function(list) {
		  					console.log(list.jsonResult.data[0].price);
//		  					$.each(list.jsonResult.data, function(index, data){
//		  					var v = new Date(parseInt(data.time, "mm/DD"));
//		  					var names = v.getMonth() + '.' + v.getDate();
//		  					console.log(names);
//		  					var values = list.jsonResult
//		  					var 2week = 
		  					
		  					
//		  					Nwagon.chart(options);
//		  					});
//		  					$('#chart7').highcharts({
//		  						chart: {
//		  							type: 'line'
//		  						},
//		  						title: {
//		  							text: '최근 2주간의 가격 추이'
//		  						},
//		  						xAxis: {
//		  							categories: [
//			  							$.each(list.jsonResult.data, function(index, data){
////			  								var dateTime = new Date(parseInt(data.time, "mm/DD"));
////			  								var parse = dateTime.getMonth() + '.' + dateTime.getDate();
////			  								'"' + parse + '",' 
////			  								var dateTime = JSON.stringify(data);
////			  								console.log(dateTime);
//			  								
//			  								
//			  							})
//		  							]
//		  						},
//		              yAxis: {
//		                title: {
//		                    text: '가격'
//		                }
//		              },
////		  						tooltip: {
////		                enabled: false,
////		                formatter: function() {
////		                    return '<b>'+ this.series.name +'</b><br/>'+
////		                        this.x +': '+ this.y +'°C';
////		                }
////		  						},
//		  						plotOptions: {
//		                line: {
//		                    dataLabels: {
//		                        enabled: true,
//		                        style: {
//		                            textShadow: '0 0 3px white, 0 0 3px white'
//		                        }
//		                    },
//		                    enableMouseTracking: false
//		                } 
//		  						},
//		  						series: [{
//		                name: 'Tokyo',
//		                data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
//		  						}, {
//		                name: 'London',
//		                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
//		  						}]
//		  					}); // end of highchart 
		  				} // success
		  			}); // end of selectDay ajax
	  			}).appendTo('#list-items');
	  			
	  		
	  		
	  		}); // each 괄호
	  	} // success 괄호
	  });	// $.ajax 괄호
});