<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>수정 페이지</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/admin.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
	<body class="sb-nav-fixed">
        <!-- header -->
        <jsp:include page="../fix/admin/header.jsp"/>
        
        <div id="layoutSidenav">
        	<!-- side -->
        	<jsp:include page="../fix/admin/sidemenu.jsp"/>
        	
        	<div id="layoutSidenav_content">
        		<div class="container-fluid px-4">
        			번호 : ${vo.nseq}<br/>
					작성자 : ${vo.adminId}<br/>
					제목 : ${vo.subject}<br/>
					내용 : ${vo.content}<br/>
					작성일자 : <fmt:formatDate value="${vo.indate}" type="both" pattern="yyyy-MM-dd HH:mm:SS" /><br/>	
					<a class="btn btn-light" href="<c:url value='/gshop.do?command=noticeUpdate'/>">수정완료</a>
					<a class="btn btn-light" href="<c:url value='/gshop.do?command=adminNoticeList'/>">목록</a>
        		</div>
        	</div>
        </div>
    <script type="text/javascript" src="<c:url value='/resources/js/admin/qnaView.js'/>"></script>
	</body>
</html>