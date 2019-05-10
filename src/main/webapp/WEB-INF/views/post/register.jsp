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
            <h1>블로그 글쓰기</h1>
          </div>
        </div>
      </div>
    </div>
    
  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <form method="post">
        <!-- 로그인 한 사람으로 바꾸기 -->
          <input type="hidden" name="writer" value="테스트">
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>제목</label>
              <input type="text" class="form-control" placeholder="제목" name="title" required data-validation-required-message="Please enter your name.">
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="control-group">
            <div class="form-group floating-label-form-group controls">
              <label>내용</label>
              <textarea rows="10" class="form-control" placeholder="내용" name="content" required data-validation-required-message="Please enter a message."></textarea>
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <br>
          <div class="form-group">
            <div class=" text-center">
              <button type="submit" class="btn btn-primary" id="registerBtn">입력</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <hr>
  
<%@include file="../includes/footer.jsp" %>