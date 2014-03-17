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
//			console.log(data);
			$.each(userItems.jsonResult.data, function(i, items){
				var listItem = '';
				listItem += '<ul class="items">';
				listItem += '<li><div class="itemSpace">';
				listItem += '<span>' + items[i].title + '</span>';
				listItem += '<div class="viewImg">';
				listItem += '<img src="' + items[i].image + '" class="image"><hr>';
				listItem += '</div></div></li></ul>';
				
			}); // each
		} // success
	}); // end of ajax
	

		
		$(listItem).click(function(){
//			console.log(items[i].pId);
			
			$('#list-items').hide();
			$('#listwindow').show();
			$('#select-items').show();
			
			$.ajax({
				url: 'item/userItemSelect.do',
				type: 'get',
				data: {
					pId: items[i].pId
				},
				success: function(item){
					console.log(item.jsonResult.data);
					var selectItem = '';
  				selectItem += '<div class="moreInfo">';
  				selectItem += '<h3 style="margin: 0 auto">' + item.jsonResult.data.title + '</h3>';
  				selectItem += '<img src="' + item.jsonResult.data.image + '" class="image" style="border: 2px solid yellow;" /><br>';
  				selectItem += '<span>희망가격: <input type="number" placeholder=' + item.jsonResult.data.wish_price + ' id="wPrice" /><input type="button" value="변경" id="wish_update" /></span><br>';
  				selectItem += '<span>최저가격: <input type="text" value=' + item.jsonResult.data.min_price + ' readonly="readonly" /></span>';
  				selectItem += '<div id="chart" style=" margin: 0 auto">';
  				selectItem += '</div></div>';
  				$(selectItem).appendTo('#select-items');
				
  				$('#wish_update').click(function(){
  			  	$.ajax({
  			  		url: 'item/wishUpdate.do',
  			  		type: 'get',
  			  		data: {
  			  			pId: item.jsonResult.data.pId,
  			  			wish_price: $('#wPrice').val()
  			  		},
  			  		success: function(data) {
//  			  			location.href = 'must.html';
  			  			alert('변경했습니다.');
  			  		}
  			  	}); 
  			  }); // end of wish_update click
				
				}});   

		}).appendTo('#list-items');
//	});
	 
});