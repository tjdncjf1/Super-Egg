$(function(){
	
	$.validator.addMethod("password",function(value,element){ 
    return this.optional(element) || /^(?=.*\d)(?=.*[a-z])/i.test(value); 
    },"알파벳과 숫자만 사용가능합니다."); 
	
	$('#valiex').validate({
		rules: {
			email: {
				required: true,
				email: true,
//				remote: {
//					url: '',
//					type: 'post',
//					data: {
//						userEmail: function() {
//							
//						}
//					}
//				}
			},
			password: {
				required: true,
				minlength: 8,
				maxlength: 16,
			},
			pwchk: {
				required: true,
				minlength: 8,
				maxlength: 16,
				equalTo: '#password'
			}
		},
		messages: {
			email: {
				required: '이메일을 입력하세요',
				email: '이메일을 바르게 입력하세요'
			},
			password: {
				required: '비밀번호를 입력하세요',
				minlength: '8자 이상으로 입력하세요',
				maxlength: '16자 이하로 입력하세요',
			},
			pwchk: {
				required: '비밀번호를 입력하세요',
				minlength: '8자 이상으로 입력하세요',
				maxlength: '16자 이하로 입력하세요',
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
					
					$.ajax({
						url: 'user/selectNo.do',
						type: 'post',
						data: {
							email: $('#email').val()
						},
						success: function(no){
							localStorage.setItem('email', $('#email').val());
							localStorage.setItem('password', $('#password').val());
							localStorage.setItem('no', parseInt(no.jsonResult.data));
							location.href='#list-items';
						}
					});

				}
//	      error : function() {
//	      	alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
//	      }
			});
		},
		invalidHandler: function(){
			alert('모두 입력해주세요.');
		}
	});
});