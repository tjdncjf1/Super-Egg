$(function() {
	 $.ajax({
	  	url: 'item/list.do',
	  	type: 'get',
	  	success: function(items){
	  		console.log(items);
	  		$.each(items.jsonResult.data, function(index, item){

	  			
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
	  		
	  	} // success 괄호
	  });	// $.ajax 괄호
});