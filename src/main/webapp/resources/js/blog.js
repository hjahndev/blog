$(document).ready(function(){
	if($('article').length){//post.jsp
		showComments();
		showPrevNext();
		showPost();
	}
	if($('.main').length) {//main.jsp
		showLatestComments();
	}
	checkPagenation();
	
	$('.read').on('click', function(e){
		e.preventDefault();
		//페이징 추가
		$("#pageForm").find("input[name=page]").val($('.active').find('a').attr('href'));
		$("#pageForm").attr('action', '/post/'+$(this).attr('href'));
		$("#pageForm").submit();
	});
	
	$('.read-main').on('click', function(e){
		e.preventDefault();
		$("#pageForm").attr('action', '/post/'+$(this).attr('href')).submit();
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
		let rowsInput = '<input type="hidden" name="rows" value="'+rows+'" />';
		let writer = $('input[name=writer]').data('user');
		let writerInput = '<input type="hidden" name="writer" value="'+writer+'" />';
		$('#pageForm').append(rowsInput).append(writerInput).attr('method', 'get').attr('action','/modify').submit();
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
		let form = $('#modifyForm');
		let pno = $('input[name=pno]').val();
		let countPerPage = $('input[name=countPerPage]').clone();
		let page = $('input[name=page]').clone();
		let search = $('input[name=search]').clone();
		form.empty();
		form.append(countPerPage);
		form.append(page);
		form.append(search);
		form.attr('method', 'get').attr('action','/post/'+pno).submit();
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
		$("#pageForm").attr('action', '/list').submit();
	});
	
	$('#previousList').on('click', function() {
		event.preventDefault();
		$("#pageForm").attr('action', '/list').submit();
	});
	
	$('.card.link').on('click', 'a', function() {
		event.preventDefault();
		$("#pageForm").attr('action', '/post/'+$(this).attr('href')).submit();
	});
	
	$('input[name=search]').keydown(function(key) {
        if (key.keyCode == 13) {//엔터
        	$('#search').attr('action', '/list').submit();
        }
    });
});
function checkPagenation() {
	if($('.active').find('a').attr('href') === '1') {
		$('ul li:first-child').addClass('disabled');
	}
	if($('.active').find('a').attr('href') === '3') {
		$('ul li:last-child').addClass('disabled');
	}
}

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

function showPrevNext() {
	$.getJSON('/getLink/' + $('#pno').val(), function(data) {
		if(data.nextPno) {
			$('.next').find('a').attr('href', data.nextPno).text(data.nextTitle);
			$('.next').show();
		}
		if(data.prevPno) {
			$('.prev').find('a').attr('href', data.prevPno).text(data.prevTitle);
			$('.prev').show();
		}
		
	});
}

function addLineBreak(data) {
	let content = data.trim().split('\n');
	content = content.map(function(data, index) {
		if(data === '') {
			return '<br/>';
		} else {
			return '<p>'+data+'</p>';
		}
	}).join('');
	return content;
}
function showPost() {
	$('#post').html(addLineBreak($('#post').text()));
}