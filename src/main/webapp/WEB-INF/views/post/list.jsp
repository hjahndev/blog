<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
              <c:out value="${post.content.replaceAll('\\\<.*?\\\>','')}"/>
            </div>
          </a>
            <p class="post-meta">Posted by
              <c:out value="${post.writerNickname}" />
              on <fmt:formatDate pattern="yyyy-MM-dd"
              value="${post.regdate}"/></p>
        </div>
        <hr>
      </c:forEach>		
      <!-- 블로그 목록 끝 -->
      <form id="pageForm" method="get">
      	<input type="hidden" name="countPerPage" value="5" />
      	<input type="hidden" name="page" value="" />
      	<input type="hidden" name="search" value="${pagination.setting.search}" />
      </form>
        <!-- Pager -->
        <nav aria-label="...">
		  <ul class="pagination justify-content-center">
		    <li class="page-item">
		      <a class="page-link" href="1" tabindex="-1">&laquo;</a>
			</li>
		    <c:forEach var="page" begin="${pagination.startPage}"
		       		end="${pagination.endPage}">
			  <li class="page-item ${pagination.setting.page == page ? 'active':''}">
			    <a class="page-link" href="${page}">${page}</a>
			  </li>
		    </c:forEach>
			<li class="page-item">
			  <a class="page-link" href="${pagination.totalPage}">&raquo;</a>
			</li>
		  </ul>
		</nav>
      </div>
    </div>
  </div>
  
<%@include file="../includes/footer.jsp" %>