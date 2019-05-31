<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>    

<!-- Begin Page Content -->
<div class="container-fluid">
	<div class="text-center">
	<div class="error mx-auto" data-text="404">Access Denied Page</div>
	<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}" /></h2>
	<h2><c:out value="${msg}"/></h2>
	</div>	
</div>    

<%@include file="../includes/footer.jsp"%>    