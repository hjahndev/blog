$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	if(window.location.href.split('/').includes('login')) {
		$('input[name=username]').focus();
	}
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
  	$("#loginBtn").on("click", function(e){
  		e.preventDefault();
  		let form = document.getElementById('loginForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}
  		$("#loginForm").attr('action','/login').submit();
  	});
  	$("#logout").on("click", function(e){
  		e.preventDefault();
  		logout();
  	});
});
function logout(){
	$.ajax({
		type : 'POST',
		url : '/member/logout',
		success: function(result, status, xhr) {
			location.replace('/member/login');
		},
		error : function(request, status, error) {
			console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}