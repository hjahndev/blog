<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Remove Comment Modal-->
<div class="modal fade" id="removeCommentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h6 class="modal-title">댓글 삭제</h6>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">댓글을 삭제하시겠습니까?</div>
      <input type="hidden" name="cno" value="">
      <div class="modal-footer">
        <button class="btn btn-secondary btn-sm" type="button" data-dismiss="modal">취소</button>
        <button class="btn btn-primary btn-sm" type="button" id="commentRemoveBtn">확인</button>
      </div>
    </div>
  </div>
</div>
<!-- Remove Post Modal-->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h6 class="modal-title">포스트 삭제</h6>
        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">삭제된 글은 복구가 불가능합니다. 글을 삭제하시겠습니까?</div>
      <form method="post">
      	<input type="hidden" name="pno" value="">
      </form>
      <div class="modal-footer">
        <button class="btn btn-secondary btn-sm" type="button" data-dismiss="modal">취소</button>
        <button class="btn btn-primary btn-sm" type="button" id="postRemoveBtn">확인</button>
      </div>
    </div>
  </div>
</div>