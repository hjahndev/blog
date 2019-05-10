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
            <h1>글 수정</h1>
          </div>
        </div>
      </div>
    </div>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form method="post">
          <input name="pno" type="hidden" value="${post.pno}">
          <input name="writer" type="hidden" value="${post.writer}">
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>제목</label>
              <input type="text" class="form-control" name="title"
              	value='<c:out value="${post.title}"/>'>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>내용</label>
              <textarea rows="10" class="form-control" name="content">
                <c:out value="${post.content}"/></textarea>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <br>
          
		  <div class="container">
		    <div class="row">
		  	  <div class="col-lg-8 col-md-10 mx-auto">	
		  	  	<div class=" text-center">
		  	  	  <ul class="list-inline text-center">
  	  	    		<li class="list-inline-item">
			      	  <button type="submit" class="btn btn-warning" id="modifyBtn">수정</button>
			      	</li>
  	  	    		<li class="list-inline-item">
			      	  <button type="submit" class="btn btn-danger" id="resetBtn">취소</button>
			  	    </li>
			  	  </ul>  
			  	</div>
			  </div>
			</div>  
		  </div>
        </form>
      </div>
    </div>
  </div>
  <hr>
  
<%@include file="../includes/footer.jsp" %>