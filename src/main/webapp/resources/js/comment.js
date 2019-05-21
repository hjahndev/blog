$(document).ready(function(){
	//댓글 입력
	$('#commentBtn').on('click', function(e){
		let form = document.getElementById('commentForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}		
		let comment = {
			'comment' :	$('textarea[name=comment]').val(),
			'writer' : $('input[name=writer]').val(),
			'password' : $('input[name=password]').val(),
			'pno': $('input[name=pno]').val()
		}
		addComment(comment, function() {
			$('#commentForm')[0].reset();
			showComments();
			form.classList.remove('was-validated');
		});
	});

	//댓글 수정, 삭제 글자 표시
	$('.container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
	
	$('.container.comment-list').on('mouseleave', '.control-group.comment-list', function() {
		//댓글 수정, 삭제 글자 삭제
		$('.commentButton').remove();
	});
	
	//대댓글 구현시
	//$('.container.comment-list').on('click', '.commentReply', function() {});

	//댓글 수정 화면
	$('.container.comment-list').on('click', '.commentModify', function() {
		var comment = $(this).parents('.control-group.comment-list').find('.comment');
		comment.before('<textarea class="form-control comment" name="comment">'+comment.html().replace('<br>','\n')+'</textarea>');
		comment.before('<a class="text-info" name="modifyCommentBtn">'+'수정'+'</a>');
		comment.before('<a class="text-info" name="cancelModifyBtn">'+'취소'+'</a>');
		comment.hide();
		//'수정 삭제 댓글'글자 안나오게
		$('.container.comment-list').off('mouseenter');
	});
	//댓글 수정 취소
	$('.container.comment-list').on('click', 'a[name="cancelModifyBtn"]', function() {
		$(this).parents('.control-group.comment-list').find('.comment').show();
		$(this).siblings('textarea').remove();
		$(this).siblings('a').remove();
		//'수정 삭제 댓글' 글자 다시 나오도록
		$('.container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
		$(this).remove();
	});
	//댓글 수정
	$('.container.comment-list').on('click', 'a[name="modifyCommentBtn"]', function() {
		let commentTextarea = $(this).siblings('textarea');
		if(!commentTextarea.val()) {
			event.preventDefault();
			addInvalidFeedback(commentTextarea)
			return;
		}	
		let comment = {
			'cno': $(this).parents('.control-group.comment-list').find('input').val(),	
			'comment': commentTextarea.val()
		}
		modifyComment(comment, function() {
			showComments();
		});
		//'수정 삭제 댓글' 글자 다시 나오도록
		$('.container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
	});
	
	$('.container.comment-list').on('click', '.commentRemove', function() {
		let cno = $(this).parents('.control-group.comment-list').find('input').val();
		$('#removeCommentModal').modal('show');
		$('#removeCommentModal').find('input').attr('value', cno);
	});
	
	$('#commentRemoveBtn').on('click', function() {
		let cno = $('#removeCommentModal').find('input').val();
		removeComment(cno, function() {
			$('#removeCommentModal').modal('hide');
			showComments();
		});
	});
	
	
	$('.container.comment-list').on('keydown keyup', 'textarea', function() {
		removeInvalidFeedback($(this));
		$(this).height(1).height($(this).prop('scrollHeight')+12);
	});
});

function addInvalidFeedback(textarea) {
	if(!textarea.next().hasClass('invalid-feedback')) {
		textarea.css('border-color', '#dc3545');
		textarea.after('<div class="invalid-feedback comment-modify">댓글 내용을 입력해 주세요.</div>');
		textarea.next().show();
		return;
	}
}

function removeInvalidFeedback(textarea) { 
	if(textarea.next().hasClass('invalid-feedback')) {
		$('.invalid-feedback.comment-modify').remove();
		textarea.css('border-color', '#28a745');
	}	
}
function addCommentButton(event) {
	var div = $('<div class="commentButton"></div>');
	//div.append('<span class="commentReply">댓글</span>');
	div.append('<span class="commentModify">수정</span>');
	div.append('<span class="commentRemove">삭제</span>');
	$(event.currentTarget).append(div);
}

function addComment(comment, callback, error){
	$.ajax({
		type : 'POST',
		url : '/comment/new',
		data : JSON.stringify(comment),
		contentType : 'application/json; charset=utf-8',
		success: function(result, status, xhr) {
			if (callback) {
				callback(result);
			}
		},
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
	})
}

function modifyComment(comment, callback){
	$.ajax({
		type : 'PUT',
		url : '/comment/' + comment.cno,
		data : JSON.stringify(comment),
		contentType : 'application/json; charset=utf-8',
		success: function(result, status, xhr) {
			if (callback) {
				callback(result);
			}
		},
		error : function(request, status, error) {
			 console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}

function removeComment(cno, callback){
	$.ajax({
		type : 'DELETE',
		url : '/comment/' + cno,
		success: function(result, status, xhr) {
			if (callback) {
				callback(result);
			}
		},
		error : function(request, status, error) {
			 console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
}

function showComments() {
	$('.container.comment-list').empty();
	var formGroup = $('<div class="form-group"></div>');
	$.getJSON('/comment/' + $('input[name=pno]').val(), function(data) {
		$.each(data, function(index, item) {
			var controlGroup = $('<div class="control-group comment-list"></div>');
			var row = $('<div class="form-row"></div>');
			row.append(formGroup.clone().html('<span class="comment-writer">'+item.writer+'</span>'));
			row.append(formGroup.clone().html('<span class="comment-date">'+displayTime(item.regDate)+'</span>'));
			controlGroup.append(row);
			controlGroup.append(formGroup.clone().html('<div class="comment">'+item.comment.replace('\n', '<br>')
													  +'</div>'));
			controlGroup.append('<input class="cno" type="hidden" value='+item.cno+'>');
			$('.container.comment-list').append(controlGroup);
		});
	}).fail(function(xhr, status, er) {
		
	});
}

function displayTime(timeValue) {
	var today = new Date();
	var gap = today.getTime() - timeValue;
	var dateObj = new Date(timeValue);
	var str = '';
	
	if (gap < (1000 * 60 * 60 * 24)) {//하루 안 됨
		var hh = dateObj.getHours();
		var mi = dateObj.getMinutes();
		var ss = dateObj.getSeconds();
		
		return [ (hh > 9 ? '': '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
			':', (ss > 9 ? '': '0') + ss ].join('');
	} else {
		var yy = dateObj.getFullYear();
		var mm = dateObj.getMonth() + 1; //getMonth() is zero-based
		var dd = dateObj.getDate();
		
		return [ yy, '/', (mm > 9 ? '': '0') + mm, '/',
			(dd > 9 ? '' : '0') + dd ].join('');
	}	
}