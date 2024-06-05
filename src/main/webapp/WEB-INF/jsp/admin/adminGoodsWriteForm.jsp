<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods</title>
</head>
<body>
	<form name="goodsWriteForm" method="post" enctype="multipart/form-data">
	
	<c:choose>
		<c:when test="${empty updateGoods}">
<!-- ----------------------- 상품 등록 페이지 ------------------------ -->
			<label>상품명</label>
			<input type="text" name="gname">
			
			<label>원가</label>
			<input id="prices" type="text" name="oprice">
			
			<label>판매가</label>
			<input id="prices" type="text" name="sprice">
			
			<label>마진</label>
			<input id="prices" type="text" name="mprice">
			
			<label>상세설명</label>
			<input type="text" name="content">
			
			<label>상품이미지</label>
			<input type="file" name="image" multiple="multiple">
			
			<label>카테고리</label>
			<select name="cgseq"> 
				<option value="0" selected="selected"> 카테고리 선택 </option>
 				<c:forEach items="${categoryList}" var="cate" varStatus="stat">
					<option value="${cate.cgseq}"> ${cate.category}	
				</c:forEach>
			</select>
			
			<label>베스트상품여부</label>
			<input type="radio" name="bestyn" value="1" checked="checked">사용
			<input type="radio" name="bestyn" value="0">미사용
			
			<label>상품 판매여부</label>
			<input type="radio" name="useyn" value="1" checked="checked">판매
			<input type="radio" name="useyn" value="0">미판매
			
			<input type="button" id="admingoods_insert" value="상품 등록">
		</c:when>
		<c:otherwise>
<!-- ----------------------- 상품 수정 페이지 ------------------------ -->
			<input type="hidden" name="gseq" value="${updateGoods.gseq}">


			<label>상품명</label>
			<input type="text" name="gname" value="${updateGoods.gname}">
			
			<label>원가</label>
			<input id="prices" type="text" name="oprice" value="${updateGoods.oprice}">
			
			<label>판매가</label>
			<input id="prices" type="text" name="sprice" value="${updateGoods.sprice}">
			
			<label>마진</label>
			<input id="prices" type="text" name="mprice" value="${updateGoods.mprice}">
			
			<label>상세설명</label>
			<input type="text" name="content" value="${updateGoods.content}">
			
			<label>상품이미지</label>
			<input type="file" name="image" multiple="multiple">
			
			<label>기존이미지</label>
			<div>		
			<c:set var="imgcnt" value=""></c:set>
			<c:forEach items="${updateGoods.imageList}" var="img" varStatus="imgIdx">				
				<img src='<c:url value = "/resources/image/goods/${img.realname}.png"/>'>
				<input type="checkbox" name="giseq" value="${img.giseq}"> 	
			</c:forEach>
			</div>
			
			<label>카테고리</label>
			<select name="cgseq"> 
				<option> 카테고리 선택 </option>
 				<c:forEach items="${categoryList}" var="cate">
					<c:choose>
						<c:when test="${cate.cgseq == updateGoods.cgseq}">
							<option value="${cate.cgseq}" selected="selected"> ${cate.category}
						</c:when>
						<c:otherwise>
							<option value="${cate.cgseq}"> ${cate.category}	
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				
			</select>
			
			<label>베스트상품여부</label>
				<c:choose>
					<c:when test='${updateGoods.bestyn==1}'>
						<input type="radio" name="bestyn" value="1" checked="checked">사용
						<input type="radio" name="bestyn" value="0">미사용
					</c:when>
					<c:otherwise>
						<input type="radio" name="bestyn" value="1">사용
						<input type="radio" name="bestyn" value="0" checked="checked">미사용
					</c:otherwise>
				</c:choose>

			<label>상품 판매여부</label>
				<c:choose>
					<c:when test='${updateGoods.useyn==1}'>
						<input type="radio" name="useyn" value="1" checked="checked">판매
						<input type="radio" name="useyn" value="0">미판매
					</c:when>
					<c:otherwise>
						<input type="radio" name="useyn" value="1">판매
						<input type="radio" name="useyn" value="0" checked="checked">미판매
					</c:otherwise>
				</c:choose>
			
			<input type="button" id="admingoods_update" value="상품 수정">
		</c:otherwise>
	</c:choose>
	</form>	
<script type="text/javascript" src='<c:url value="/resources/js/admin/goodsWrite.js"/>'></script>	
</body>
</html>