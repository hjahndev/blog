<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

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
                <h1 class="h4 text-gray-900 mb-4">비밀번호 재설정</h1>
              </div>
              <form class="user" id="resetPasswordForm" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="hidden" name="email" value="${email}" />
                <input type="hidden" name="token" value="${token}" />
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" name="password" 
                   placeholder="비밀번호" id="resetPassword" required>
                   <div class="password-feedback">
                   </div>
                   <div class="invalid-feedback">
 	            	비밀번호를 새로 설정한 후에 로그인 페이지로 이동합니다.
 	               </div>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" name="repeatPassword"
                   placeholder="비밀번호 재입력" required>
                  <div class="repeat-password-feedback">
                  </div>                   
                  <div class="invalid-feedback">
					비밀번호를 재입력해 주세요.
 	              </div>
                </div>
                <a id="resetPasswordBtn" href="" class="btn btn-primary btn-user btn-block">
                  	비밀번호 재설정
                </a>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>  
  </div> 
  </div>

  <%@include file="../includes/footer.jsp" %>