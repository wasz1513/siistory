<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	article{
		width:80%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
	
	section{
		display: flex;
		flex-wrap: wrap;
	}
	
	.myfriend{
		width:25%;
		height:200px;
	}
	
	.myfriend-img{
		height:100px;
	}
	
	.myfriend-img-item{
		width:100px;
		height:100px;
		border-radius: 50%;
	}
	
	.friend-info-exe{
		color:white;
	}
	
</style>

<article>

	<section>
		<c:forEach var="myfriend" items="${myfriendlist}">
			<div class="myfriend">
				<a href="${pageContext.request.contextPath}/member/info?member_no=${myfriend.member_no}" class="friend-info-exe">
					<div class="myfriend-img">
						<img src="${pageContext.request.contextPath}/util/download?member_no=${myfriend.member_no}" class="myfriend-img-item">
					</div>
					<div class="myfriend-email">
					${myfriend.email}				
					</div>
					<div class="myfriend-name">
					${myfriend.member_name}
					</div>
				</a>
			</div>
		</c:forEach>
	</section>
	
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>