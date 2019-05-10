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
          <div class="post-heading">
            <h1><c:out value="${post.title}" /></h1>
            <span class="meta">Posted by
              <a href="#"><c:out value="${post.writer}" /></a>
              on <fmt:formatDate pattern="yyyy-MM-dd"
              value="${post.regdate}"/></span>
          </div>
        </div>
      </div>
    </div>

  <!-- Post Content -->
  <article>
    <form method="post">
  	  <div class="clearfix">
    	<div class="container">
      	  <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
              <p><c:out value="${post.content}"/></p>
              <input id="pno" name="pno" type="hidden" value="${post.pno}">
        	</div>
      	  </div>
    	</div>
  	  </div>  
    </form>
  </article>
  
  <!-- button -->
  <div class="container">
    <div class="row">
  	  <div class="col-lg-8 col-md-10 mx-auto">	
  	  	<div class=" text-center">
  	  	  <ul class="list-inline text-center">
  	  	    <li class="list-inline-item">
	    	<button type="submit" class="btn btn-warning" id="updateFormBtn">수정</button>
	    	</li>
	    	<li class="list-inline-item">
	    	<button type="submit" class="btn btn-danger" id="removeBtn">삭제</button>
	  	    </li>
	  	  </ul>
	  	</div>
	  </div>
	</div>  
  </div>
  <hr>
<%@include file="../includes/footer.jsp" %>