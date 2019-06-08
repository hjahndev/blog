<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>
<sec:authorize access="isAuthenticated()">
	<c:redirect url = "/list" />
</sec:authorize>
  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 col-12">

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
                      <input type="email" class="form-control form-control-user" name="email" 
                      placeholder="아이디(이메일)" required>
                      <div class="invalid-feedback">
 	              		이메일을 입력해 주세요.
 	            	  </div>
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" name="password" 
                      placeholder="비밀번호" required>
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
                    <a class="small" href="/member/forgotPassword">비밀번호 찾기</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="/member/join">회원가입</a>
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