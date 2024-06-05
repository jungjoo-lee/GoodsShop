<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>category</title>
</head>
<body>
	<c:forEach items="${categoryList}" var="categoryList">
			<div>				
				<div>
					<a href="gshop.do?command=goodsDetailView&gseq=${categoryList.gseq}">
						<img alt="${categoryList.thum}.png" src='<c:url value="/resources/image/goods/${categoryList.thum}.png"/>'>	
					</a>
					<a href="gshop.do?command=goodsDetailView&gseq=${categoryList.gseq}">
						${categoryList.gname} - ${categoryList.sprice}					
					</a>
				</div>
			</div>
	</c:forEach>
</body>
</html>