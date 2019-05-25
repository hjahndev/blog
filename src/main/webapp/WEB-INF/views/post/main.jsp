<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp" %>

  <!-- Main Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
      <!-- 블로그 목록 -->
      <c:forEach items="${list}" var="post">
        <div class="post-preview">
          <a class="read" href='<c:out value="${post.pno}"/>'>
            <h2 class="post-title">
              <c:out value="${post.title}" />
            </h2>
            <h3 class="post-subtitle">
              	글 내용 일부
            </h3>
          </a>
            <p class="post-meta">Posted by
              <c:out value="${post.writer}" />
              on <fmt:formatDate pattern="yyyy-MM-dd"
              value="${post.regdate}"/></p>
        </div>
        <hr>
      </c:forEach>		
      <!-- 블로그 목록 끝 -->
      
        <!-- Pager -->
        <div class="clearfix">
          <a class="btn btn-primary float-right" href="#">Older Posts &rarr;</a>
        </div>
      </div>
    </div>
  </div>
<%@include file="../includes/footer.jsp" %>