<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main</title>
</head>



<body>
메인페이지입니다.

	<h3>하이</h3>

	<div>
		<div> <h3>베스트 20</h3> </div>
		<c:forEach items="${bestlist}" var="gvo" varStatus="status">

			<div>				
				<div>
					${gvo.gseq} <br>
					${gvo.gname} - ${gvo.s_price}
				</div>
			</div>
		</c:forEach>	
	</div>
	<div>
		<div> <h3>신상품</h3> </div>
		<c:forEach items="${newlist}" var="gvo" varStatus="status">

			<div>				
				<div>
					${gvo.gseq} <br>
					${gvo.gname} - ${gvo.s_price}
				</div>
			</div>
		</c:forEach>	
	</div>
</body>

</html>

<%@ include file="footer.jsp" %>