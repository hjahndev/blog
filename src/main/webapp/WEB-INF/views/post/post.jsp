<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="../includes/header.jsp"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<!-- Page Header -->
<div class="overlay"></div>
<div class="container">
	<div class="row">
		<div class="col-lg-8 col-md-10 mx-auto">
			<div class="post-heading">
				<h1>
					<c:out value="${post.title}" />
				</h1>
				<span class="meta">Posted by <a href="#">
					<c:out value="${post.writer}" /></a> on 
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
				<div class="col-lg-8 col-md-10 mx-auto">
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
		<div class="col-lg-8 col-md-10 mx-auto">
			<div class=" text-center">
			    <input id="pno" name="pno" type="hidden" value="${post.pno}">
			    <input name="writer" type="hidden" value="${post.writer}">
				<ul class="list-inline text-center">
				  <sec:authentication property="principal" var="pinfo"/>
	  	    	  <sec:authorize access="isAuthenticated()">
	  	  	  		<c:if test="${pinfo.username eq post.writer}">
					  <li class="list-inline-item">
						<button type="submit" class="btn btn-outline-primary btn-sm"
							id="updateFormBtn">수정</button>
					  </li>
					  <li class="list-inline-item">
						<button type="submit" class="btn btn-outline-primary btn-sm"
							id="removeBtn">삭제</button>
					  </li>
					</c:if>
				  </sec:authorize>	  
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="container commentForm">
	<div class="row">
		<div class="col-lg-8 col-md-10 mx-auto">
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
						<div class="form-group col-4">
						  <sec:authorize access="isAuthenticated()">
							<input type="text" class="form-control" name="writer"
							 value='<sec:authentication property="principal.username"/>' readonly>
						  </sec:authorize>
						  <sec:authorize access="isAnonymous()">
							<input type="text" class="form-control" placeholder="이름" name="writer" required>
							<div class="invalid-feedback">
						  	  이름을 입력해 주세요. 
							</div>
						  </sec:authorize>	
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
