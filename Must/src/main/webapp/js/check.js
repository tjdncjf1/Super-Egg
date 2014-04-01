$(function(){

	$.validator.addMethod("password",function(value,element){ 
		return this.optional(element) || /^(?=.*\d)(?=.*[a-z])/i.test(value); 
	},"알파벳과 숫자만 사용가능합니다."); 

	$.validator.addMethod('emailCheck', function(email) {
		$.ajax({
			url: baseUrl + 'user/emailCheck.do',
//			url: 'user/emailCheck.do',
			type: 'post',
			data: {
				userEmail: $('#email').val()
			},
			cache:false,
			async:false,
			success: function(answer) {
				console.log(answer);
				result = answer.jsonResult.data.length == 1 ? false : true;
				console.log(result);
			}
		});
		console.log(result);
		return result;
	}, '');

	$('#valiex').validate({
		onkeyup: false,
		rules: {
			email: {
				required: true,
				email: true,
				emailCheck: true
			},
			password: {
				required: true,
				minlength: 8
			},
			pwchk: {
				required: true,
				minlength: 8,
				equalTo: '#password'
			}
		},
		messages: {
			email: {
				required: 'Enter Email!',
				email: 'The email address you entered is not valid.',
				emailCheck: 'Duplicated Email!'
			},
			password: {
				required: 'Enter Password!',
				minlength: 'Choose a stronger password.'
			},
			pwchk: {
				required: 'Enter Password!',
				minlength: 'Choose a stronger password.',
				equalTo: 'Passwords do not match.'
			}
		},
		submitHandler: function(){
			$.ajax({
				url: baseUrl + 'user/add.do',
//				url: 'user/add.do',
				type: 'post',
				data: {
					email: $('#email').val(),
					password: $('#password').val()
				},
				success: function(){

					$.ajax({
						url: baseUrl + 'user/selectNo.do',
//						url: 'user/selectNo.do',
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
//				error : function() {
//				alert('시스템이 바쁩니다.\n나중에 다시 시도해 주세요!');
//				}
			});
		},
		invalidHandler: function() {
			alert('모두 입력해주세요.');
		}
	});
});