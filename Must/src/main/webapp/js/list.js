$(function() {
	
	var items = JSON.parse(localStorage.getItem('item'));
//	console.log(items.length);
	$.each(items, function(i){
//		console.log(items[i].title);
		
		var listItem = '';
		listItem += '<ul class="items">';
		listItem += '<li><div class="itemSpace">';
		listItem += '<span>' + items[i].title + '</span>';
		listItem += '<div class="viewImg">';
		listItem += '<img src="' + items[i].image + '" class="image"><hr>';
		listItem += '</div></div></li></ul>';
		
		$(listItem).click(function(){
			
			$('#list-items').hide();
			$('#listwindow').show();
			$('#select-items').show();
			
			var selectItem = '';
			selectItem += '<div class="moreInfo">';
			selectItem += '<h3 style="margin: 0 auto">' + items[i].title + '</h3>';
			selectItem += '<img src="' + items[i].image + '" class="image" style="border: 2px solid yellow;" /><br>';
			selectItem += '<span>희망가격: <input type="number" placeholder=' + items[i].wish_price + ' id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
			selectItem += '<span>최저가격: <input type="text" value=' + items[i].min_price + ' readonly="readonly" /></span>';
			selectItem += '<div id="chart" style=" margin: 0 auto">';
			selectItem += '</div></div>';
			$(selectItem).appendTo('#select-items');
			
			$('#wish_update').click(function(){
		  	$.ajax({
		  		url: 'item/wishUpdate.do',
		  		type: 'get',
		  		data: {
		  			pId: items[i].pId,
		  			wish_price: $('#wPrice').val()
		  		},
		  		success: function() {
		  			
		  			
		  			
		  			
		  			location.href = 'must.html';
		  		}
		  	}); 
		  }); 
			
		}).appendTo('#list-items');
		
		
	});
	 
});