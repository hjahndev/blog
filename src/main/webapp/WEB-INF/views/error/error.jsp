<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>    

<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- 404 Error Text -->
  <div class="text-center">
    <div class="error mx-auto"><c:out value="${code}"/></div>
    <p class="lead text-gray-800 mb-5"><c:out value="${msg}"/></p>
    <p class="text-gray-800 mb-0"><c:out value="${explain}"/></p>
  </div>

</div>
    
<%@include file="../includes/footer.jsp"%>        
