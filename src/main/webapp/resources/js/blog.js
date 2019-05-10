$(document).ready(function(){
	history.replaceState({},null,null);
	var form = $("form");
	
	$(".read").on("click", function(e){
		e.preventDefault();
		location.replace('/post?pno='+$(this).attr("href"));
	});
	
	$("#removeBtn").on("click", function(e){
		form.attr("action","/remove");
		form.submit();		
	});
	
	$("#updateFormBtn").on("click", function(e){
		var pno = $("#pno").val();
		location.replace('/modify?pno='+pno);
	});
	
	$("#modifyBtn").on("click", function(e){
		form.attr("action","/modify");
		form.submit();
	});
	
	$("#registerBtn").on("click", function(e){
		form.attr("action","/register");
		form.submit();
	});
	
});