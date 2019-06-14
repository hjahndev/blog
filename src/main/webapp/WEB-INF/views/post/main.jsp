<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

  <!-- Main Content -->
  <div class="container main">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <!-- 블로그 소개 -->
		<div class="card">
		  <div class="card-header">
		  	블로그 소개
		  </div>
		  <div class="card-body">
		  </div>
		</div>			        
        <!-- 최신 글 -->
        <div class="card">
		  <div class="card-header">
		    최신 글
		  </div>
		  <div class="card-body">
	        <c:forEach items="${list}" var="post">
	          <div class="post-preview">
	            <a class="read" href='<c:out value="${post.pno}"/>'>
	              <div class="post-title">
	                <c:out value="${post.title}" />
	              </div>
	              <div class="post-content">
	                <c:out value="${post.content}" />
	              </div>
	            </a>
	          </div>
	        <hr>
	        </c:forEach>
            <!-- post 페이지 호출 버튼 -->
            <div class="clearfix">
              <a class="btn btn-primary btn-sm float-right" href="/list">전체 글 보기 &rarr;</a>
            </div>
		  </div>
		</div>	
        <!-- 최신 댓글-->
        <div class="card">
		  <div class="card-header">
		    최신 댓글
		  </div>
		  <div class="card-body">
			<div class="container comment-list">
			  	<div class="control-group comment-list">
					<div class="form-row">
						<div class="form-group"><span class="comment-writer"></span></div>
						<div class="form-group"><span class="comment-date"></span></div>
					</div>
					<div class="form-group"><span class="comment"></span></div>
				</div>
			</div> 
		  </div>
		</div>			     
      </div>
    </div>
  </div>
<%@include file="../includes/footer.jsp" %>