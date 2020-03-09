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


section {
	/* 		background-color: C8FFFF; */
	width: 50%;
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
<section>
<div class="jumbotron">
  <h1 class="display-3">Hello, world!</h1>
  <p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
  <hr class="my-4">
  <p>It uses utility classes for typography and spacing to space content out within the larger container.</p>
  <p class="lead">
    <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
  </p>
</div>
</section>
</article>

