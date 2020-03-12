<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>

$(function(){
	
	$(".dropdown-list").hide();
	
	$(".dropdown").click(function(){
		$(this).next().slideDown();
		$(this).removeClass("dropdown");
		$(this).addClass("updown");
		
	});
	
	$(".updown").click(function(){
		$(this).next().slideUp();
		$(this).removeClass("updown");
		$(this).addClass("dropdown");
	});
	
});

</script>

<style>

	.detail-list.detail-list{
		background-color: black;
	}
	
	.detail-list.detail-list:hover{
		background-color: gray;
	}

</style>
    
<div class="list-group">
  <button class="list-group-item list-group-item-action dropdown">회원</button>
  <div class="dropdown-list">
  	<a href="${pageContext.request.contextPath}/admin/management" class="list-group-item list-group-item-action detail-list">회원검색</a>
  	<a href="${pageContext.request.contextPath}/admin/threeout" class="list-group-item list-group-item-action detail-list">신고관리</a>
  </div>
  <button class="list-group-item list-group-item-action dropdown">게시글</button>
  <div class="dropdown-list">
  	<a href="${pageContext.request.contextPath}/admin/boardmanagement" class="list-group-item list-group-item-action detail-list">게시글검색</a>
  	<a href="${pageContext.request.contextPath}/admin/threeout" class="list-group-item list-group-item-action detail-list">신고관리</a>
  </div>
  <button class="list-group-item list-group-item-action dropdown">통계분석</button>
  <div class="dropdown-list">
  	<a href="${pageContext.request.contextPath}/admin/statismain" class="list-group-item list-group-item-action detail-list">사이트 이용현황</a>
  </div>
</div>	