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
            <h2>블로그 글쓰기</h2>
          </div>
        </div>
      </div>
    </div>
    
  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form method="post" id="registerForm" novalidate>
          <sec:authorize access="hasRole('ADMIN')">
            <input type="hidden" name="writer" value='<sec:authentication property="principal.member.email"/>'>
          </sec:authorize>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="control-group">
            <div class="form-group row">
              <label class="col-sm-2 col-form-label">제목</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" name="title" required>
 	            <div class="invalid-feedback">
 	              	제목을 입력해 주세요.
 	            </div>
              </div>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group controls">
              <textarea rows="15" class="form-control" placeholder="내용" name="content" 
              required></textarea>
 	            <div class="invalid-feedback">
 	              	내용을 입력해 주세요.
 	            </div>
            </div>
          </div>
          <div class="form-group">
            <div class=" text-center">
              <button type="submit" class="btn btn-primary btn-sm" id="registerBtn">입력</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  
<%@include file="../includes/footer.jsp" %>