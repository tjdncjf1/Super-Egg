$(function() {
	
	setInterval(function(){
		$.ajax({
	  	url: 'item/list.do',
	  	type: 'get',
	  	success: function(items){
	  		$.each(items.jsonResult.data, function(index, item){
	  				`	
	  			
	  			}); // each 괄호
	  		
	  	} // success 괄호
	  });	// $.ajax 괄호
	}, 3600000)
	 
});