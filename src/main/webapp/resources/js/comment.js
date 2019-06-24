$(document).ready(function(){
	//회원 이메일
	let user = $('#commentForm').find('input[name=writer]').data('user');
	//댓글 입력
	$('#commentBtn').on('click', function(e){
		let form = document.getElementById('commentForm');
		if(!form.checkValidity()) {
			event.preventDefault();
			form.classList.add('was-validated');
			return;
		}		
		let writer = $('input[name=writer]').val();
		if($('input[name=writer]').data('user') !== "") {
			writer = $('input[name=writer]').data('user');
		}
		let comment = {
			'comment' :	$('textarea[name=comment]').val(),
			'writer' : writer,
			'password' : $('input[name=password]').val(),
			'pno': $('input[name=pno]').val()
		}
		addComment(comment, function() {
			$('#commentForm')[0].reset();
			showComments();
			form.classList.remove('was-validated');
		});
	});

	//댓글 수정, 삭제 버튼 표시
	$('.commentForm .container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
	
	$('.commentForm .container.comment-list').on('mouseleave', '.control-group.comment-list', function() {
		//댓글 수정, 삭제 버튼 삭제
		$('.commentButton').remove();
	});
	
	//대댓글 구현시
	//$('.commentForm .container.comment-list').on('click', '.commentReply', function() {});

	//댓글 수정 화면
	$('.commentForm .container.comment-list').on('click', '.commentModify', function() {
		let target = $(this).parents('.control-group.comment-list');
		//회원은 바로 수정 화면 보여주기(앞에서 본인 확인 함)
		if(user !== '') {
			showModifyForm(target);
			return;
		}
		//비회원은 비번 맞을때 수정 화면 보여주기
		let cno = target.find('input').val();
		$('#checkPasswordModal').modal('show');
		$('#checkPasswordModal').find('input[name=cno]').attr('value', cno);
		//jQuery 이벤트 중복 에러 막기 위해 off() 추가
		$('#checkPasswordBtn').off('click').on('click', function() { 
			checkCommentPassword(showModifyForm, target)
		});
	});
	//댓글 수정 취소
	$('.commentForm .container.comment-list').on('click', 'a[name="cancelModifyBtn"]', function() {
		$(this).parents('.control-group.comment-list').find('.comment').show();
		$(this).siblings('textarea').remove();
		$(this).siblings('a').remove();
		//'수정 삭제 댓글' 버튼 다시 나오도록
		$('.commentForm .container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
		$(this).remove();
	});
	//댓글 수정
	$('.commentForm .container.comment-list').on('click', 'a[name="modifyCommentBtn"]', function() {
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
		//'수정 삭제 댓글' 버튼 다시 나오도록
		$('.commentForm .container.comment-list').on('mouseenter', '.control-group.comment-list', addCommentButton);
	});
	
	//댓글 삭제
	$('.commentForm .container.comment-list').on('click', '.commentRemove', function() {
		let target = $(this).parents('.control-group.comment-list');
		let cno = target.find('input').val();
		//회원은 바로 삭제 확인 모달 보여주기(앞에서 본인 확인 함)
		if(user !== '') {
			showRemoveModal(cno);
			return;
		}
		//비회원은 비번 맞을때 삭제 확인 모달 보여주기
		$('#checkPasswordModal').modal('show');
		$('#checkPasswordModal').find('input[name=cno]').attr('value', cno);
		//jQuery 이벤트 중복 에러 막기 위해 off() 추가
		$('#checkPasswordBtn').off('click').on('click', function() { 
			checkCommentPassword(showRemoveModal,cno);
		});
	});
	
	$('#commentRemoveBtn').on('click', function() {
		let cno = $('#removeCommentModal').find('input').val();
		removeComment(cno, function() {
			$('#removeCommentModal').modal('hide');
			showComments();
		});
	});
	
	$('.commentForm .container.comment-list').on('keydown keyup', 'textarea', function() {
		removeInvalidFeedback($(this));
		$(this).height(1).height($(this).prop('scrollHeight')+12);
	});
	$('#checkPasswordModal').find('button:contains("취소")').on('click', function() {
		$('#checkPasswordModal').find('form')[0].reset();
	});
});

function showRemoveModal(cno) {
	$('#removeCommentModal').modal('show');
	$('#removeCommentModal').find('input').attr('value', cno);
}

function showModifyForm(target) {
	let comment = target.find('.comment');
	comment.before('<textarea class="form-control comment" name="comment">'+comment.html().replace('<br>','\n')+'</textarea>');
	comment.before('<a class="text-info" name="modifyCommentBtn">'+'수정'+'</a>');
	comment.before('<a class="text-info" name="cancelModifyBtn">'+'취소'+'</a>');
	comment.hide();
	//수정, 삭제 버튼 안나오게
	$('.commentForm .container.comment-list').off('mouseenter');	
}

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
	//회원은 본인 글이 아닐 경우 수정, 삭제 버튼 보여주지 않음
	let user = $('#commentForm').find('input[name=writer]').data('user');
	let writer = $(event.currentTarget).find('.comment-writer').data('user');
	if((user !== '') && (user !== writer)) {
		return;
	}
	let div = $('<div class="commentButton"></div>');
	//div.append('<span class="commentReply">댓글</span>');
	div.append('<span class="commentModify">수정</span>');
	div.append('<span class="commentRemove">삭제</span>');
	$(event.currentTarget).append(div);
}

function checkCommentPassword(callback, callbackParam){
	let data = {
			'cno': $('#checkPasswordModal').find('input[name=cno]').val(),	
			'password': $('#checkPasswordModal').find('input[name=password]').val()
		}
	$.ajax({
		type : 'POST',
		url : '/comment/checkPassword',
		data : JSON.stringify(data),
		contentType : 'application/json; charset=utf-8',
		success: function(result, status, xhr) {
			if(result === "correct") {
				callback(callbackParam);
			} else {
				alert("비밀번호가 맞지 않습니다.");
			}
		},
		error : function(request, status, error) {
			console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
		}
	})
	$('#checkPasswordModal').modal('hide');
	$('#checkPasswordModal').find('form')[0].reset();
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
	$.getJSON('/comment/' + $('input[name=pno]').val(), function(data) {
		makeCommentTags(data);
	}).fail(function(request, status, error) {
		console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
	});
}

function showLatestComments() {
	$.getJSON('/comment/getLatestList?limit=5', function(data) {
		makeCommentTags(data, true);

	}).fail(function(request, status, error) {
		console.log('code:'+request.status+'\n'+'message:'+request.responseText+'\n'+'error:'+error);
	});
}

function makeCommentTags(data, latestComments) {
	$('.container.comment-list').empty();
	var formGroup = $('<div class="form-group"></div>');
	$.each(data, function(index, item) {
		let controlGroup = $('<div class="control-group comment-list"></div>');
		let row = $('<div class="form-row"></div>');
		if(item.writerNickname === null) {
			row.append(formGroup.clone().html('<span class="comment-writer">'+item.writer+'</span>'));
		} else {
			row.append(formGroup.clone().html('<span class="comment-writer" data-user="'+item.writer+'">'+item.writerNickname+'</span>'));
		}
		row.append(formGroup.clone().html('<span class="comment-date">'+displayTime(item.regDate)+'</span>'));
		controlGroup.append(row);
		controlGroup.append(formGroup.clone().html('<div class="comment">'+item.comment.replace('\n', '<br>')
												  +'</div>'));
		controlGroup.append('<input class="cno" type="hidden" value='+item.cno+'>');
		if(latestComments) {
			$('.container.comment-list').append(controlGroup.wrap('<a href="/post/'+item.pno+'"></a>').parent());
		} else {
			$('.container.comment-list').append(controlGroup);
		}
	});	
}

function displayTime(timeValue) {
	let today = new Date();
	let gap = today.getTime() - timeValue;
	let dateObj = new Date(timeValue);
	let str = '';
	
	if (gap < (1000 * 60 * 60 * 24)) {//하루 안 됨
		let hh = dateObj.getHours();
		let mi = dateObj.getMinutes();
		let ss = dateObj.getSeconds();
		
		return [ (hh > 9 ? '': '0') + hh, ':', (mi > 9 ? '' : '0') + mi,
			':', (ss > 9 ? '': '0') + ss ].join('');
	} else {
		let yy = dateObj.getFullYear();
		let mm = dateObj.getMonth() + 1; //getMonth() is zero-based
		let dd = dateObj.getDate();
		
		return [ yy, '/', (mm > 9 ? '': '0') + mm, '/',
			(dd > 9 ? '' : '0') + dd ].join('');
	}	
}