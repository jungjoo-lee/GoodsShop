<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">회원관리</div>
                <a class="nav-link" href="<c:url value='gshop.do?command=adminIndex'/>">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-users"></i></div>
                    회원목록
                </a>
                <div class="sb-sidenav-menu-heading">상품 & 주문</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                    <div class="sb-nav-link-icon"><i class="fa-brands fa-shopify"></i></div>
                    상품관리
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="#">상품 등록</a>
                        <a class="nav-link" href="#">상품 목록</a>
                    </nav>
                </div>
                <a class="nav-link" href="#">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-sack-dollar"></i></div>
                    주문관리
                </a>
                <div class="sb-sidenav-menu-heading">게시판</div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-clipboard"></i></div>
                    게시판
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                        <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                            Q&A / 리뷰
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link" href="<c:url value='/gshop.do?command=adminQnaList'/>">Q&A</a>
                                <a class="nav-link" href="<c:url value='/gshop.do?command=adminReviewList'/>">리뷰</a>
                            </nav>
                        </div>
	                    <a class="nav-link" href="<c:url value='/gshop.do?command=adminNoticeList'/>">공지사항</a>
                    </nav>
                </div>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">속보:</div>
            집에 가고 싶어짐
        </div>
    </nav>
</div>
<script type="text/javascript"
	src="<c:url value='/resources/js/admin/sidemenu.js'/>"></script>