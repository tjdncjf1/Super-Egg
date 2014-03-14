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
	  				selectItem += '<div id="Nwagon" style=" margin: 0 auto">';
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
		  				datatype : 'json',
		  				data: {
		  					pId: item.pId 
		  				},
		  				success: function(list) {
//		  					var a = parseInt(list.jsonResult.data[0].time);
//		  					var b = new Date(a);
//		  					var c = b.getMonth() + '.' + b.getDate();
//		  					var b = a.getMonth() + '.' + a.getDate();
//		  						console.log(c);
		  					
		  					var Stringdatetime = [];
		  					var cprice = [];
		  					$.each(list.jsonResult.data, function(i){
		  						var dateTime = new Date(parseInt(list.jsonResult.data[i].time));
		  					 cprice = parseInt(list.jsonResult.data[i].price);
              	 Stringdatetime = dateTime.getMonth() + '.' + dateTime.getDate();
              	console.log(Stringdatetime);
		  					})
				                	
				                	
		  					var options = {
		  					        'legend':{
		  					            names: [
		  					                    Stringdatetime[0] , Stringdatetime[1] , Stringdatetime[2] ,
		  					                  Stringdatetime[3] , Stringdatetime[4] , Stringdatetime[5] ,
		  					                Stringdatetime[6] , Stringdatetime[7] , Stringdatetime[8] ,
		  					              Stringdatetime[9] , Stringdatetime[10] , Stringdatetime[11] ,
		  					            Stringdatetime[12]
		  					            ],
		  					            hrefs: []
		  					        },
		  					        'dataset': {
		  					            title: 'hi', 
		  					            values: [
		  					                   [1],[1],[1],[1],[1],[1],[1],[1],[1],[1],[1],[1],[1]
		  					                     
		  					                     //cprice[1],cprice[2],cprice[3],cprice[4],cprice[5],cprice[6],cprice[7],
		  					                   //cprice[8],cprice[9],cprice[10],cprice[11],cprice[12]
		  					                     ], 
		  					                   colorset: ['#DC143C'],
		  					                 fields:['Pass']
		  					        },
		  					      'chartDiv' : 'Nwagon',
		  				        'chartType' : 'line',
		  				        'chartSize' : {width:310, height:400},
		  				        'increment' : 10000
		  					    };
		  					    Nwagon.chart(options);
		  						
		  				} // success
		  			}); // end of selectDay ajax
	  			}).appendTo('#list-items');
	  			
	  		}); // each 괄호
	  	} // success 괄호
	  });	// $.ajax 괄호
	 
});