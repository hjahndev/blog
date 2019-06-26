$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	if(window.location.href.split('/').includes('login')) {
		$('input[name=email]').focus();
	}
  	$("#loginBtn").on("click", function(e){
  		e.preventDefault();
  		let form = document.getElementById('loginForm');
  		if(!isInvalidForm(form)) {
  			$("#loginForm").attr('action','/login').submit();
  		}
  	});
  	$("#logout").on("click", function(e){
  		e.preventDefault();
  		logout();
  	});
  	
  	$("input[name=repeatPassword]").on("keyup", function(){
  		if(!comparePasswords()) {
  			$(".repeat-password-feedback").text('비밀번호와 다릅니다.');
  			$(".repeat-password-feedback").show();
  		}
  	});
  	$("input[name=repeatPassword]").on("keydown", function(){
  		$(".repeat-password-feedback").hide();
  	});
  	
  	$("#joinPassword").on("keyup", function(){
  		isValidPassword($(this).val());
  	});
  	$("#joinPassword").on("keydown", function(){
  		$(".password-feedback").hide();
  	});
  	
  	$("#resetPassword").on("keyup", function(){
  		isValidPassword($(this).val());
  	});
  	$("#resetPassword").on("keydown", function(){
  		$(".password-feedback").hide();
  	});
  	
  	$("input[name=nickname]").on("keyup", function(){
  		checkNickname();
  	});
  	$("input[name=nickname]").on("keydown", function(){
  		$('#duplicateNicname').data('duplicate', false);
  		$('#duplicateNicname').hide();
  	});
  	
  	$("#joinEmail").on("keyup", function(){
  		checkEmail();
  	});
  	$("#joinEmail").on("keydown", function(){
  		$('#duplicateEmail').data('duplicate', false);
  		$('#duplicateEmail').hide();
  	});
  	
  	$("#joinBtn").on("click", function(){
  		event.preventDefault();
  		let form = document.getElementById('joinForm');
  		displayInvalidJoinForm(form, function() {
  			$("#joinForm").attr('action','/member/join').submit();
  		});
  	});
  	
  	$("#forgotPasswordBtn").on("click", function(e){
  		event.preventDefault();
  		let form = document.getElementById('forgotPasswordFrom');
  		if(!isInvalidForm(form)) {
  			forgotPassword();
  		}
  	});
  	
  	$("#resetPasswordBtn").on("click", function(e){
  		event.preventDefault();
  		if(!comparePasswords()) return;
  		if(!isValidPassword($('#resetPassword').val())) return;
  		$("#resetPasswordForm").attr('action','/member/resetPassword').submit();
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
function isInvalidForm(form) {
	if(!form.checkValidity()) {
		form.classList.add('was-validated');
		return true;
	}
	return false;
}
function displayInvalidJoinForm(form, callback) {
	if(isInvalidForm(form)) return;
	if($('#duplicateNicname').data('duplicate')) return;
	if($('#duplicateEmail').data('duplicate')) return;
	if(!isValidPassword($('#joinPassword').val())) return;
	if(!comparePasswords()) return;
	callback();
}
function checkNickname() {
	$.ajax({
		type : 'GET',
		url : '/member/checkNickname/' + $('input[name=nickname]').val(),
		dataType : 'text',
		success: function(result, status, xhr) {
			if(result === 'duplicate') {
				$('#duplicateNicname').data('duplicate', true);
				$('#duplicateNicname').show();
			}
		},
		error : function(request, status, error) {
			console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}
function checkEmail() {
	$.ajax({
		type : 'GET',
		url : '/member/checkEmail',
		data: { 'email': $('#joinEmail').val() },
		contentType : 'application/json; charset=utf-8',
		dataType : 'text',
		success: function(result, status, xhr) {
			if(result === 'duplicate') {
				$('#duplicateEmail').data('duplicate', true);
				$('#duplicateEmail').show();
			}
		},
		error : function(request, status, error) {
			console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}
function comparePasswords() {
	let password = $("input[name=password]").val();
	let repeatPassword =$("input[name=repeatPassword]").val();
	return password == repeatPassword;
}
function isValidPassword(password) {
	if(password.search(/[0-9]/g) < 0 || password.search(/[a-z]/ig) < 0 
		|| password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi) < 0 ){
		$(".password-feedback").text('영문, 숫자, 특수문자를 혼합하여 입력해주세요.');
		$(".password-feedback").show();
		return false;
	 }
	if(password.length < 8 || password.length > 20){
		$(".password-feedback").text('8자리~20자리 이내로 입력해주세요.');
		$(".password-feedback").show();
		return false;
	}
	return true;
}
function forgotPassword() {
	$.ajax({
		type : 'GET',
		url : '/member/passwordEmail',
		data: { 'email': $("#forgotPasswordFrom").find('input[name=email]').val() },
		contentType : 'application/json; charset=utf-8',
		dataType : 'text',
		success: function(result, status, xhr) {
			alert(result);
		},
		error : function(request, status, error) {
			location.href = '/error/'+request.status;
			console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}