<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>    

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="text-center">
	<div class="error mx-auto">Access Denied Page</div>
	<p class="lead text-gray-800 mb-5"><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}" /></p>
	<p class="text-gray-800 mb-0"><c:out value="${msg}"/></p>
	</div>	
</div>    

<%@include file="../includes/footer.jsp"%>    