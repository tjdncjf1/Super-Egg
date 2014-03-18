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
//				console.log(item.loginUserItem.wPrice);
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
		  		selectItem += '<span>희망가격: <input type="number" placeholder=' + item.loginUserItem.wPrice + ' id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
		  		selectItem += '<span>최저가격: <input type="text" value=' + item.min_price + ' readonly="readonly" /></span>';
		  		selectItem += '<div id="chart" style=" margin: 0 auto">';
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
		  			  		success: function(data) {
		  			  			location.href = 'must.html';
//		  			  			$('#wPrice').val() = wish_price;
//		  			  			alert('변경했습니다.');
		  			  		}
		  			  	}); 
		  			  }); // end of wish_update click

				}).appendTo('#list-items');
				
				
			}); // each
		} // success
	}); // end of ajax
	 
});