$(document).ready(function(){
	if($('span.comment').length){
		showComments();
	}
	
	$('.read').on('click', function(e){
		e.preventDefault();
		location.replace('/post?pno='+$(this).attr('href'));
	});
	
	$('#removeBtn').on('click', function(){
		$('#removeModal').modal('show');
		$('#removeModal').find('input[name=pno]').attr('value', $('#pno').val());
		$('#removeModal').find('input[name=writer]').attr('value', $('input[name=writer]').data('user'));
	});
	
	$('#postRemoveBtn').on('click', function(){
		let token = $("meta[name='_csrf']").attr("content");
		let csrfInput = '<input type="hidden" name="_csrf" value="'+token+'" />';
		$('#removeModal').find('form').append(csrfInput).attr('action', '/remove').submit();
		$('#remove').modal('hide');
	});
	
	$('#updateFormBtn').on('click', function(){
		let rows = countTextRows($('#post').text());
		location.replace('/modify?pno=' + $('#pno').val() + '&rows=' + rows);
	});
	
	$('#modifyBtn').on('click', function(){
		let form = document.getElementById('modifyForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}
		$('#modifyForm').attr('action','/modify').submit();
	});
	
	$('#registerBtn').on('click', function(){
		let form = document.getElementById('registerForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}
		$('#registerForm').attr('action','/register').submit();
	});
	
	$('#cancelBtn').on('click', function(){
		location.replace('/post?pno='+$('input[name=pno]').val());
	});
	
	$('textarea').on('keyup input change paste propertychange', function() {
		//입력창, 수정창은 15줄 이하일 경우 줄어들지 않도록
		let minHeight = 372;
		let formId = $(this).parents('form').attr('id');
		if((formId === 'registerForm' || formId === 'modifyForm')
			&& $(this).prop('scrollHeight') <= minHeight) {
			return;
		}
		$(this).height(1).height($(this).prop('scrollHeight')+12);
	});
	
	$('.scroll-to-top').on('click', function() {
		window.scroll(0,0);
	});
	
	$(window).scroll( function() {
		if ($(this).scrollTop() > 200 ) {
			$('.scroll-to-top').fadeIn();
		} else {
			$('.scroll-to-top').fadeOut();
		}
	} );
	
	$('.page-link').on('click', function() {
		event.preventDefault();
		$("#pageForm").find("input[name=page]").val($(this).attr("href"));
		$("#pageForm").submit();
	});
});

function countTextRows(postText){
	const lengthOfOneRow = 45;
	let rows = 0;
	postText.trim().split('\n').forEach(function(str){
		if(str.length > lengthOfOneRow) {
			rows += Math.ceil(str.length / lengthOfOneRow);
			return;
		}
		rows += 1;
	});
	return rows;
}