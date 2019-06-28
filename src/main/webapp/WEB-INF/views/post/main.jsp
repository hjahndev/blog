<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp" %>

  <!-- Main Content -->
  <div class="container main">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <!-- 블로그 소개 -->
		<div class="card">
		  <div class="card-header">
		  	블로그 소개
		  </div>
		  <div class="card-body introduce">
		   	<p>
		   	포트폴리오를 위해 만든 블로그지만 꾸준히 아래 기능을 추가할 예정입니다. 
		   	만족할 만한 블로그를 만드는 것과 그 과정에서 필요한 기술 및 유지보수 경험을 쌓으며 리팩토링 등을 공부하는 것이 목표입니다.
		   	</p>
		   	<br/>
		   	<p>
		   	<strong>업데이트하고 싶은 기능</strong><br/>
		   	마이페이지(회원정보 수정/탈퇴 등), 스마트 에디터 사용(포스트에 이미지 추가), 파일 업로드, 
		   	조회수, 썸네일, 대댓글, 댓글 페이징, 관리화면(카테고리 생성, 한 페이지 포스트 개수 변경 등), CI 배포 자동화,
			배포 시 안내 화면 등
			</p>	   
		  </div>
		</div>			        
        <!-- 최신 글 -->
        <div class="card">
		  <div class="card-header">
		    최신 글
		  </div>
		  <div class="card-body">
	        <c:forEach items="${list}" var="post">
	          <div class="post-preview">
	            <a class="read-main" href='<c:out value="${post.pno}"/>'>
	              <div class="post-title">
	                <c:out value="${post.title}" />
	              </div>
	              <div class="post-content">
	                <c:out value="${post.content}" />
	              </div>
	            </a>
	            <form id="pageForm">
				  <input id="pno" name="pno" type="hidden" value="">
			      <input type="hidden" name="countPerPage" value="5" />
			      <input type="hidden" name="page" value="1" />
			    </form>
	          </div>
	        <hr>
	        </c:forEach>
            <!-- post 페이지 호출 버튼 -->
            <div class="clearfix">
              <a class="btn btn-primary btn-sm float-right" href="/list">전체 글 보기 &rarr;</a>
            </div>
		  </div>
		</div>	
        <!-- 최신 댓글-->
        <div class="card">
		  <div class="card-header">
		    최신 댓글
		  </div>
		  <div class="card-body">
			<div class="container comment-list">
			  	<div class="control-group comment-list">
					<div class="form-row">
						<div class="form-group"><span class="comment-writer"></span></div>
						<div class="form-group"><span class="comment-date"></span></div>
					</div>
					<div class="form-group"><span class="comment"></span></div>
				</div>
			</div> 
		  </div>
		</div>			     
      </div>
    </div>
  </div>
<%@include file="../includes/footer.jsp" %>