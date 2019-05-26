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
            <div class="post-title">
              <c:out value="${post.title}" />
            </div>
            <div class="post-content">
              <c:out value="${post.content}" />
            </div>
          </a>
            <p class="post-meta">Posted by
              <a href="#"><c:out value="${post.writer}" /></a>
              on <fmt:formatDate pattern="yyyy-MM-dd"
              value="${post.regdate}"/></p>
        </div>
        <hr>
      </c:forEach>		
      <!-- 블로그 목록 끝 -->
        <!-- Pager -->
        <nav aria-label="...">
		  <ul class="pagination justify-content-center">
		    <li class="page-item">
		      <a class="page-link" href="#" tabindex="-1">&laquo;</a>
			</li>
		    <c:forEach var="num" begin="${pageMaker.startPage}"
	       end="${pageMaker.endPage}">
			  <li class="page-item "><a class="page-link" href="#">1</a></li>
			  <li class="page-item active">
			    <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
			  </li>
			  <li class="page-item"><a class="page-link" href="#">3</a></li>
		    </c:forEach>
			<li class="page-item">
			  <a class="page-link" href="#">&raquo;</a>
			</li>
		  </ul>
		</nav>
      </div>
    </div>
  </div>
  
<%@include file="../includes/footer.jsp" %>