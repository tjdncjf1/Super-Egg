$(function() {
	
	var list = '<div id="st-accordion" class="st-accordion">';
	list += '<ul><li><a href="#">Photography<span class="st-arrow">Open or Close</span></a>';
	list += '<div class="st-content">';
	list += '<p>She packed her seven versalia, put her initial into the belt and made herself on the way.</p>';
	list += '<img src="images/10.jpg" alt="image01"/></div></li></ul></div>';
	
	$('#list').append($(list));

	$('#st-accordion').accordion({
		oneOpenedItem : true
	});
	
	

	
	
	
});