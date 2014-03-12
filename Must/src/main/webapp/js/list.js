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
	  				selectItem += '<span>희망가격: <input type="number" placeholder="' + item.wish_price + '" id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
	  				selectItem += '<span>최저가격: <input type="number" placeholer="' + item.min_price + '" /></span>';
	  				selectItem += '<div id="chart" style="min-width: 310px; height: 400px; margin: 0 auto">';
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
		  		  	}); // ajax 괄호
		  		  	// return false;
		  		  }); // submit 괄호
	  				
	  				
	  				
	  			}).appendTo('#list-items');
	  			
//	  			$.ajax({
//	  				url: 'chart/selectDay.do',
//	  				type: 'get',
//	  				data: {
//	  					pId: item.pId
//	  				},
//	  				success: function(data) {
//	  					
//	  				}
//	  			});
	  		
	  		}); // each 괄호
	  	} // success 괄호
	  });	// $.ajax 괄호
});