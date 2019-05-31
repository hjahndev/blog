<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" name="password" 
                   placeholder="비밀번호" required>
                  <div class="invalid-feedback">
 	            	비밀번호를 입력해 주세요.
 	              </div>
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-user" name="repeatPassword"
                   placeholder="비밀번호 재입력" required>
                  <div class="password-feedback">
                  	비밀번호와 다릅니다.
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