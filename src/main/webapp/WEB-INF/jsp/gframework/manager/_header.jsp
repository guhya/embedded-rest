<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@	taglib uri="http://guhya.net" prefix="g" %>

<c:set var="url" 		value="${requestScope['javax.servlet.forward.request_uri']}"/>
<c:set var="menu" 		value="${fn:split(url, '/')[1]}" />

<header class="header dark-bg">
	<div class="toggle-nav">
		<i class="fa fa-bars"></i>
		<div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
	</div>
	<!--logo start-->
	<a href="<c:url value="/manager/user/list.do"/>" class="logo">REST-<span class="lite">PROVIDER</span></a>
	<!--logo end-->
	<div class="top-nav notification-row">
		<ul class="nav pull-right top-menu">
		</ul>
	</div>
</header>
<!--header end-->

<!--sidebar start-->
<aside>
	<div id="sidebar" class="nav-collapse " tabindex="5000" style="overflow: hidden; outline: none;">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu">
			<li class="<c:if test="${menu == 'user' }">active</c:if>">
				<a class="" href="<c:url value="/manager/user/list.do?page=1"/>"> <i class="fa fa-user"></i> <span>User</span></a>
			</li>
		</ul>
	</div>
</aside>
<!--sidebar end-->
