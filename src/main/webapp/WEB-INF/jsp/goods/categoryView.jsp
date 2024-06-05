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
						<img src="<c:url value='/gshop.do?command=imageWrite&folder=${categoryList.gseq}${categoryList.gname}&realName=${categoryList.realname}'/>">
					</a>
					<a href="gshop.do?command=goodsDetailView&gseq=${categoryList.gseq}">
						${categoryList.gname} - ${categoryList.sprice}					
					</a>
				</div>
			</div>
	</c:forEach>
</body>
</html>