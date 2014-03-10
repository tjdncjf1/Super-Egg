$(function(){
	$('#valiex').validate({
		rules: {
			email: {
				required: true,
				email: true
//				remote: {type:'post', url:'check.js'}
			},
			password: {
				required: true,
				minlength: 6,
				maxlength: 12
			},
			pwchk: {
				required: true,
				minlength: 6,
				maxlength: 12,
				equalTo: '#password'
			}
		},
		messages: {
			email: {
				required: '이메일을 입력하세요',
				email: '이메일을 바르게 입력하세요'
//				remote: 
			},
			password: {
				required: '비밀번호를 입력하세요',
				minlength: '6자 이상으로 입력하세요',
				maxlength: '12자 이하로 입력하세요'
			},
			pwchk: {
				required: '비밀번호를 입력하세요',
				minlength: '6자 이상으로 입력하세요',
				maxlength: '12자 이하로 입력하세요',
				equalTo: '비밀번호가 서로 일치하지 않습니다.'
			}
		},
		
		submitHandler: function(){
			$.ajax({
				url: 'user/add.do',
				type: 'post',
				data: {
					email: $('#email').val(),
					password: $('#password').val()
				},
				success: function(){
					location.href='must.html'
				},
	      error : function() {
	      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
	      }
			});
		}
		
	});
});