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
                    <h1 class="h4 text-gray-900 mb-4">회원가입을 축하드립니다!</h1>
                    <p class="mb-4">
                      <c:out value="${nickname}" />님, 회원가입이 완료되었습니다. 
                     </p>
                  </div>
                  <a href="/member/login" class="btn btn-primary btn-user btn-block">
                   	로그인 화면으로 이동
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>

<%@include file="../includes/footer.jsp" %>