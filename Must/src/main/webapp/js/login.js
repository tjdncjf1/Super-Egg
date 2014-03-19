$(function(){
	$('#userLogin').click(function(){
		$.ajax({
			url: "user/login.do",
			type:"POST",
			data : {
				email:$('#loginEmail').val(),
				password:$('#loginPassword').val()
				
			   },
			   success: function(no){
//			  	 console.log(no.jsonResult.data[0].no);
			  	 if (no.jsonResult.data.length == 0) {
			  		 alert('로그인 정보가 일치하지 않습니다.'); 
			  		 return false;
			  	 } else {
			  		 localStorage.setItem('email', $('#loginEmail').val());
			  		 localStorage.setItem('password', $('#loginPassword').val());
			  		 localStorage.setItem('no', parseInt(no.jsonResult.data[0].no));
						
			  		 location.href='must.html';
			  	 }
			   }
			
		});
		
	});
});