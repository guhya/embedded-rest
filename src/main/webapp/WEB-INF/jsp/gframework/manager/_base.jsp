<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="<c:url value="/resources/manager/favicon.ico"/>" type="image/x-icon">
	<link rel="icon" href="<c:url value="/resources/manager/favicon.ico"/>" type="image/x-icon">
    <title>REST</title>
	<!-- Bootstrap CSS -->
	<link href="<c:url value="/resources/manager/css/bootstrap.min.css"/>" rel="stylesheet">
	<!-- bootstrap theme -->
	<link href="<c:url value="/resources/manager/css/bootstrap-theme.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/datepicker.css"/>" rel="stylesheet">
	
	<!--external css-->
	<!-- font icon -->
	<link href="<c:url value="/resources/manager/css/elegant-icons-style.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/font-awesome.min.css"/>" rel="stylesheet">
	
	<link href="<c:url value="/resources/manager/css/widgets.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/style.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/style-responsive.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/jquery-ui-1.10.4.min.css"/>" rel="stylesheet">
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
	<!--[if lt IE 9]>
	      <script src="<c:url value="/resources/manager/js/html5shiv.js"/>"></script>
	      <script src="<c:url value="/resources/manager/js/respond.min.js"/>"></script>
	      <script src="<c:url value="/resources/manager/js/lte-ie7.js"/>"></script>
	<![endif]-->
	
		<%  /*####################################### */ %>
		<tiles:insertAttribute name="css" />
		<%  /*####################################### */ %>
</head>
<body>
	<section id="container" class="">
		
		<%  /*####################################### */ %>		
		<tiles:insertAttribute name="header" />
		<%  /*####################################### */ %>
		
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
					<%  /*####################################### */ %>		
					<tiles:insertAttribute name="body" />
					<%  /*####################################### */ %>
				</section>
		</section>
		<!--main content end-->
		
		<%  /*####################################### */ %>				
		<tiles:insertAttribute name="footer" />
		<%  /*####################################### */ %>
		
		<%  /*####################################### */ %>
		<tiles:insertAttribute name="js" />
		<%  /*####################################### */ %>
	</section>
</body>
</html>

	