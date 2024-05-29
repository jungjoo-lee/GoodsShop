<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main</title>
</head>



<body>
	<h3>하이</h3>

	<div>
		<c:forEach items="${bestlist}" var="gvo" varStatus="status">

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