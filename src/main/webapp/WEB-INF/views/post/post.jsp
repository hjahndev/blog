<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!-- Page Header -->
<div class="overlay"></div>
<div class="container">
	<div class="row">
		<div class="col-lg-8 col-12 mx-auto">
			<div class="post-heading">
				<h1>
					<c:out value="${post.title}" />
				</h1>
				<span class="meta">
					<c:out value="${post.writerNickname}" /> on
					<fmt:formatDate pattern="yyyy-MM-dd" value="${post.regdate}" />
				</span>
			</div>
		</div>
	</div>
</div>

<!-- Post Content -->
<article>
	<div class="clearfix">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-12 mx-auto">
				  <div id="post" class="form-group floating-label-form-group controls">
				  	<p><c:out escapeXml="false" 
				  	value="${fn:replace(post.content, newLineChar, '</p><p>')}" /></p>
				  </div>	
				</div>
			</div>
		</div>
	</div>
</article>
<br>

<div class="container">
	<div class="row">
		<div class="col-lg-8 col-12 mx-auto">
			<div class=" text-center">
			    <input id="pno" name="pno" type="hidden" value="${post.pno}">
			    <div class="row">
	  	    	  <div class="form-group col-4"></div>
	  	    	  <div class="form-group col-4 text-center">
		  	    	  <sec:authorize access="isAuthenticated()">
		  	    	  	<sec:authentication var="userEmail" property="principal.member.email"/>
		  	  	  		<c:if test="${userEmail eq post.writer}">
							<button type="submit" class="btn btn-outline-primary btn-sm"
								id="updateFormBtn">수정</button>
							<button type="submit" class="btn btn-outline-primary btn-sm"
								id="removeBtn">삭제</button>
						</c:if>
					  </sec:authorize>	  
					  </div>
				  <div class="form-group col-4 text-right">
				    <form id="pageForm" method="get">
			      	  <input type="hidden" name="countPerPage" value="${pageSet.countPerPage}" />
			      	  <input type="hidden" name="page" value="${pageSet.page}" />
			        </form>
					<button type="submit" class="btn btn-primary btn-sm" id="previousList">목록
					</button>
				  </div>
				</div>  
			</div>
			<div class="row">
		        <div class="col-6 mb-4">
	              <div class="card prev">
	                <div class="card-body">
	                  <div class="row">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary mb-1">이전글</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800 text-center">
	                  		<a class="" href="">
	                  		</a>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
	            <div class="col-6 mb-4">
	              <div class="card next">
	                <div class="card-body">
	                  <div class="row">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-primary mb-1">다음글</div>
	                      <div class="h5 mb-0 font-weight-bold text-gray-800 text-center">
	                        <a class="" href="">
	                        </a>
	                      </div>
	                    </div>
	                  </div>
	                </div>
	              </div>
	            </div>
			</div>
		</div>
	</div>
</div>

<div class="container commentForm">
	<div class="row">
		<div class="col-lg-7 col-md-10 mx-auto">
 			<div class="control-group">
				<form id="commentForm" novalidate>
					<div class="form-group">
						<textarea class="form-control" placeholder="댓글" name="comment"	maxlength="1000"
						 required></textarea>
						<div class="invalid-feedback">
						  댓글 내용을 입력해 주세요. 
						</div>
					</div>
					<div class="form-row">
					  <sec:authorize access="isAuthenticated()">
					    <input type="hidden" class="form-control" name="writer"
						 data-user='<sec:authentication property="principal.member.email"/>'
						 value='<sec:authentication property="principal.member.nickname"/>' readonly>
						<div class="form-group col-12 text-right">
							<button type="button" class="btn btn-primary btn-sm"
								id="commentBtn">댓글 등록</button>
						</div>
					  </sec:authorize>
					  <sec:authorize access="isAnonymous()">
						<div class="form-group col-4">
							<input type="text" class="form-control" placeholder="이름" name="writer" data-user="" required>
							<div class="invalid-feedback">
						  	  이름을 입력해 주세요. 
							</div>
						</div>
						<div class="form-group col-4">
							<input type="password" class="form-control" placeholder="비밀번호" name="password"
							 required>
							<div class="invalid-feedback">
						  	  비밀번호를 입력해 주세요. 
							</div>
						</div>
						<div class="form-group col-4 text-right">
							<button type="button" class="btn btn-primary btn-sm"
								id="commentBtn">댓글 등록</button>
						</div>
					  </sec:authorize>	
					</div>
				</form>
			</div>
			<!-- 댓글 목록 -->
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

<%@include file="modal.jsp"%>
<%@include file="../includes/footer.jsp"%>
