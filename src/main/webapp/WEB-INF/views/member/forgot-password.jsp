<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

<body class="bg-gradient-primary">

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
                    <h1 class="h4 text-gray-900 mb-2">비밀번호 재설정</h1>
                    <p class="mb-4">이메일을 입력해 주세요. 비밀번호 재설정 링크를 보내드리겠습니다.</p>
                  </div>
                  <form id="forgotPasswordFrom" class="user">
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" name="email"
                       placeholder="이메일" required>
                      <div class="invalid-feedback">
 	              		이메일을 입력해 주세요.
 	              	  </div>
                    </div>  
                    <button id="forgotPasswordBtn" class="btn btn-primary btn-user btn-block">
                      	입력
                    </button>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="/member/join">회원가입</a>
                  </div>
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