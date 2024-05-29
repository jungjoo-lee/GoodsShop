<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 상세보기</title>
</head>
<body>
번호 : ${vo.qseq}<br/>
작성자 : ${vo.userid}<br/>
제목 : ${vo.subject}<br/>
내용 : ${vo.content}<br/>
작성일자 : ${vo.indate}<br/>
답변 : <c:if test="${not empty vo.reply}">${vo.reply}</c:if><br/> 
답변일자 : <c:if test="${not empty vo.reply}">${vo.reply}</c:if> 
</body>
</html>