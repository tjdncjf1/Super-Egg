jQuery(document).ready(function($) {
	
	$('button').click(function() {
		//get the rotation angle value
		var rotation_angle_value = $('#rotation_amount').val();
		
		if (isNaN(rotation_angle_value)) { //is not a number
			$('#error_message').show();
		} 
		else { //is a number
			$('#error_message').hide();
			
			//store CSS value syntax with the new rotation angle value
			var rotation_angle = 'rotate(' + rotation_angle_value + 'deg)';
			
			//store CSS properties and values
			var updated_css = {
				'-webkit-transform' :	rotation_angle,
				'-moz-transform'	:	rotation_angle,	
				'-o-transform'		:	rotation_angle,
				'-ms-transform'		:	rotation_angle,
				'transform'			:	rotation_angle,
			}
			
			//update our CSS
			$('.mask img').css(updated_css);
		}
	});
	
});
