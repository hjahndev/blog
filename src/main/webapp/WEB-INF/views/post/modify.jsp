<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp" %>
  <!-- Page Header -->
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="page-heading">
            <h2>글 수정</h2>
          </div>
        </div>
      </div>
    </div>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form method="post" id="modifyForm" novalidate>
          <input name="pno" type="hidden" value="${post.pno}">
          <input name="writer" type="hidden" value="${post.writer}">
          <div class="control-group">
            <div class="form-group row">
              <label class="col-sm-2 col-form-label">제목</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" name="title"
              	value='<c:out value="${post.title}"/>' required>
 	            <div class="invalid-feedback">
 	              	제목을 입력해 주세요.
 	            </div>
              </div>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group controls">
              <textarea class="form-control" name="content" rows="${rows<15 ? 15:rows}" 
              required><c:out value="${post.content}"/></textarea>
 	            <div class="invalid-feedback">
 	              	내용을 입력해 주세요.
 	            </div>
            </div>
          </div>
	  	   <div class=" text-center">
	  	     <ul class="list-inline text-center">
		  	   <li class="list-inline-item">
		         <button type="submit" class="btn btn-warning btn-sm" id="modifyBtn">수정</button>
		       </li>
		  	   <li class="list-inline-item">
		      	 <button type="button" class="btn btn-danger btn-sm" id="cancelBtn">취소</button>
		  	   </li>
		    </ul>  
		  </div>
   		</form>
	  </div>
    </div>  
  </div>
  
<%@include file="../includes/footer.jsp" %>