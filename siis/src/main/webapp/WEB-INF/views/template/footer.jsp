<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	footer{
		position: absolute;
		bottom: 0;
		width: 100%;
	}
</style>

<footer>
	<h5>세션email = ${sessionScope.email}</h5>
	<h5>세션member_no = ${sessionScope.member_no}</h5>
	<h5>세션member_name = ${sessionScope.memeber_name}</h5>
</footer>


</body>
</html>
