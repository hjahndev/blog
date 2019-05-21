$(document).ready(function(){
	history.replaceState({},null,null);
	
	if($('span.comment').length){
		showComments();
	}
	
	$('.read').on('click', function(e){
		e.preventDefault();
		location.replace('/post?pno='+$(this).attr('href'));
	});
	
	$('#removeBtn').on('click', function(e){
		let pno = $('#pno').val();
		$('#removeModal').modal('show');
		$('#removeModal').find('input').attr('value', pno);
	});
	
	$('#postRemoveBtn').on('click', function(e){
		$('#removeModal').find('form').attr('action', '/remove').submit();
		$('#remove').modal('hide');
	});
	
	$('#updateFormBtn').on('click', function(e){
		let rows = countTextRows($('#post').text());
		location.replace('/modify?pno=' + $('#pno').val() + '&rows=' + rows);
	});
	
	$('#modifyBtn').on('click', function(e){
		let form = document.getElementById('modifyForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}
		$('#modifyForm').attr('action','/modify').submit();
	});
	
	$('#registerBtn').on('click', function(e){
		let form = document.getElementById('registerForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}
		$('#registerForm').attr('action','/register').submit();
	});
	
	$('#cancelBtn').on('click', function(e){
		location.replace('/post?pno='+$('input[name=pno]').val());
	});
	
	$('textarea').on('keyup input change paste propertychange', function() {
		//입력창은 15줄 이하일 경우 줄어들지 않도록
		let minHeight = 372;
		if($(this).parents('form').attr('id')==='registerForm'
			&& $(this).prop('scrollHeight') <= minHeight) {
			return;
		}
		$(this).height(1).height($(this).prop('scrollHeight')+12);
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