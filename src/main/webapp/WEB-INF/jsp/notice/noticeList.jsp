<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/notice/notice.css'/>">
</head>
<body>
<div class="row d-flex justify-content-center">
   <div class="col-lg-6 w-75">
      <div class="card">
           <div class="card-body">
               <h5 class="card-title">공지사항</h5>
               <div class="row w-100">
                   <div class="col d-flex">
                    <select class="form-select w-25 me-3" name="selectAmount" id="selectAmount">
                       <option value=0 selected>목록</option>
                       <option value=10>10</option>
                       <option value=30>30</option>
                       <option value=50>50</option>
                  </select>
                  <input type="button" name="noticeInsert" onClick="return insertNotice();"/>
               </div>
               <div class="col d-flex justify-content-end">
                   <select class="form-select w-25 me-1" name="search" id="search">
                       <option value="sc" selected>제목 + 내용</option>
                       <option value="subject">제목</option>
                       <option value="content">내용</option>
                  </select>
                     <div class="d-flex">
                        <input class="form-control me-2" name="keyword" id="keyword" type="text" placeholder="Search">
                   </div>
                </div>
               </div>
               <ul>
                    <li class="notice-header">
                       <div class="d-flex">
                          <div>번호</div>
                          <div>작성자</div>
                          <div>제목</div>
                          <div>내용</div>
                          <div>작성일자</div>
                       </div>
                    </li>
                    <div id="notice-list">
               <c:forEach var="notice" items="${noticeList}">
               <li class="notice-item">
                  <div class="d-flex justify-content-center align-items-center">
                     <div>${notice.nseq}</div>
                         <div>${notice.adminId}</div>
                         <div><a href="<c:url value='/gshop.do?command=adminNoticeView&nseq=${notice.nseq}'/>">${notice.subject}</a></div>
                         <div>${notice.content}</div>
                         <div><fmt:formatDate value="${notice.indate}" type="both" pattern="yyyy-MM-dd" /></div>
                      </div>
               </li>
               </c:forEach>
               </div>
            </ul>
            <span id="pagdInfo">${paging.currentPage} / ${paging.realEnd}</span>
            <nav>
               <ul class="pagination justify-content-center" id="pagination">
                 <!-- 이전 버튼 -->
                 <c:choose>
                    <c:when test="${paging.prev}">
                       <li class="page-item">
                          <a class="page-link" data-value="prev">Prev</a>
                       </li>
                    </c:when>
                    <c:otherwise>
                       <li class="page-item disabled">
                          <a class="page-link">Prev</a>
                       </li>
                    </c:otherwise>
                 </c:choose>
                 <!-- 페이지 번호 -->
                 <c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
                    <c:if test="${num == paging.currentPage}">
                       <li class="page-item active"><a class="page-link" data-value="${num}">${num}</a></li>
                    </c:if>
                    <c:if test="${num != paging.currentPage}">
                       <li class="page-item"><a class="page-link" data-value="${num}">${num}</a></li>
                    </c:if>
                 </c:forEach>
                <!-- 다음 버튼 -->
                <c:choose>
                    <c:when test="${paging.next}">
                       <li class="page-item">
                          <a class="page-link" data-value="next">Next</a>
                       </li>
                    </c:when>
                    <c:otherwise>
                       <li class="page-item disabled">
                          <a class="page-link">Next</a>
                       </li>
                    </c:otherwise>
                 </c:choose>
                 </ul>
            </nav>
            </div>
      </div>
   </div>
</div>
<script type="text/javascript" src="<c:url value='/resources/js/notice/notice.js'/>"></script>
</body>
</html>