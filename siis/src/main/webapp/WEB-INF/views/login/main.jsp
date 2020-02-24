<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
article {
	display: flex; /*바로안에있는것만적용됨*/
	flex-wrap: wrap;
	width: 80%;
	margin: auto;
}

.left-aside {
	background-color: EBFBFF;
	width: 20%;
	margin-top: 150px;
}

section {
	background-color: C8FFFF;
	width: 60%;
	margin-top: 150px;
}

.right-aside {
	background-color: BEEFFF;
	flex-grow: 1;
	height: 750px;
	margin-top: 150px;
}

.scroll-div {
	overflow-y: scroll;
	height: 750px;
}

.scroll-div::-webkit-scrollbar {
	display: none;
}
</style>

<article>

	<aside class="left-aside">
		<div class="row">
			<a href="member/follow">친구 요청</a>
		</div>
		<div class="row">
			<a href="#">내 게시글</a>
		</div>
		<div class="row">
			<a href="#">내정보</a>
		</div>
		<div class="row">
			<a href="#">친구관리</a>
		</div>
		<div class="row">
			<a href="#">설정</a>
		</div>
	</aside>

	<section>

		<div class="container scroll-div"></div>

	</section>




	<aside class="right-aside">
		<div class="container scroll-div">

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Type</th>
						<th scope="col">Column heading</th>
					</tr>
				</thead>

				<tbody>
					<tr class="table-dark">
						<th scope="row">
							<div class="set-List"></div>
						</th>
					</tr>
				</tbody>
			</table>
			
			</div>
			
	</aside>

</article>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
