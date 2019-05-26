<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp" %>
<sec:authorize access="isAuthenticated()">
	<c:redirect url = "/list" />
</sec:authorize>
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 col-10">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">로그인</h1>
                  </div>
                  <form id="loginForm" class="user" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                      <input type="text" class="form-control form-control-user" name="username" 
                      placeholder="ID" required>
                      <div class="invalid-feedback">
 	              		아이디를 입력해 주세요.
 	            	  </div>
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="password" 
                      placeholder="Password" required>
                      <div class="invalid-feedback">
 	              		비밀번호를 입력해 주세요.
 	            	  </div>
                    </div>
                    <c:if test="${not empty errormsg}">
            			<div class="alert alert-danger">${errormsg}</div>
                    </c:if>                        
                    <div class="form-group">
                      <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" name="remember-me" id="customCheck">
                        <label class="custom-control-label small" for="customCheck">로그인 유지</label>
                      </div>
                    </div>
                    <button id="loginBtn" class="btn btn-primary btn-user btn-block">
                      	로그인
                    </button>
                    <hr>
                  </form>
                  <div class="text-center">
                    <a class="small text-primary" href="forgot-password.html">비밀번호 찾기</a>
                  </div>
                  <div class="text-center">
                    <a class="small text-primary" href="/join">회원가입</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

<%@include file="../includes/footer.jsp" %>