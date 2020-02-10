<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	article{
		width:50%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
	
	.table td{
		vertical-align:middle;
	}
</style>

<article>
	<section>
	
		<table class="table table-hover">
			<tbody>
				<c:forEach var="memberDto" items="${list}">
					<tr class="table-active">
						<td>
							<div class="card border-light mb-3" style="max-width: 50rem;">
							  <div class="card-body">
							  	<p class="card-title">${memberDto.email}</p>
							    <p class="card-text form-text text-muted">name : ${memberDto.member_name}</p>
							  </div>
							</div>
						</td>
						<td>
							<button></button>
						</td>
					</tr>				
				</c:forEach>
			</tbody>
		</table>
	



	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>