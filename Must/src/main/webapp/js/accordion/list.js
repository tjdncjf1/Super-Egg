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
	  		  list += '<p>최저가: ' + item.min_price + '</p>';
	  		  list += '<p>희망가: ' + item.wish_price + '</p></div></li>';  
	  		  $(list).appendTo('ul');
	  		}); // each 괄호
	  		$('#st-accordion').accordion({
	  			oneOpenedItem : false
	  		});
	  	} // success 괄호
	  });	// $.ajax 괄호
	
	
});