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
		}
	});
});