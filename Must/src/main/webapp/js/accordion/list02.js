$(function() {
	 $.ajax({
	  	url: 'item/list.do',
	  	type: 'get',
	  	success: function(items){
	  		console.log(items);
	  		var accor = '<div id="st-accordion" class="st-accordion"><ul></ul></div>';
	  		$(accor).appendTo('#list-items');
	  		$.each(items.jsonResult.data, function(index, item){
	  		  var list = '<li><a href="#">' + item.title + '<span class="st-arrow">Open or Close</span></a>';
	  		  list += '<div class="st-content">';	
	  		  list += '<img src="' + item.image + '" alt="image01"/>';
	  		  list += '<form id=sbUpdate><p>최저가: <input type="number" value=' + item.min_price + '></p>';
	  		  list += '<p><input type="hidden" id="pId_select" value="' + item.pId + '"></p>';  
	  		  list += '<p>희망가: <input type="number" id="wish_update" placeholder=' + item.wish_price + '></p>';  
	  		  list += '<input type="submit" value="가격변경"></form></div></li>';
	  		  $(list).appendTo('ul');
	  		  
	  		  $('#sbUpdate').submit(function(){
	  		  	$.ajax({
	  		  		url: 'item/update.do',
	  		  		type: 'get',
	  		  		data: {
	  		  			pId: $('#pId_select').val(),
	  		  			wish_price: $('#wish_update').val()
	  		  		},
	  		  		success: function() {
	  		  			location.href = 'must.html';
	  		  		}
	  		  	}); // ajax 괄호
	  		  	return false;
	  		  }); // submit 괄호
	  		  
	  		  
	  		  
	  		}); // each 괄호
	  		
	  		
	  		
	  		
	  		$('#st-accordion').accordion({
	  			oneOpenedItem : false
	  		});
	  	} // success 괄호
	  });	// $.ajax 괄호
	 
	
});