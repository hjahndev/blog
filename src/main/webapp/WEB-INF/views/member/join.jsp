<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

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
                <h1 class="h4 text-gray-900 mb-4">회원가입</h1>
              </div>
              <form class="user" id="joinForm" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="form-group">
                  <input type="text" class="form-control form-control-user" name="nickname" 
                  placeholder="닉네임" required>
                  <div id="duplicateNicname" class="alert alert-danger" data-duplicate="false">이미 등록된 닉네임입니다.</div>
                  <div class="invalid-feedback">
 	              	닉네임을 입력해 주세요.
 	              </div>
                </div>
                <div class="form-group">
                  <input type="email" class="form-control form-control-user" name="email" 
                  id="joinEmail" placeholder="아이디(이메일)" required>
                  <div id="duplicateEmail" class="alert alert-danger" data-duplicate="false">이미 등록된 이메일입니다.</div>
                  <div class="invalid-feedback">
 	              	이메일을 입력해 주세요.
 	              </div>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" name="password" 
                   id="joinPassword" placeholder="비밀번호" required>
                  <div class="password-feedback">
                  </div> 
                  <div class="invalid-feedback">
 	            	비밀번호를 입력해 주세요.
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
                <button id="joinBtn" class="btn btn-primary btn-user btn-block">
                  	가입
                </button>
              </form>
              <hr>
              <div class="text-center">
                <a class="small" href="/member/login">계정이 있으신가요? 로그인</a>
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