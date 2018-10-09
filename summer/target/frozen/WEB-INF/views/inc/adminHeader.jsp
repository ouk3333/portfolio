<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- CSS -->
<link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/adminlte.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/dataTables.bootstrap4.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/font.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/bootstrap-colorpicker.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/sweetalert.css" rel="stylesheet">

<!-- ------------------------------------------------------------ -->

<!-- JAVASCRIPT -->
<script src="<%= request.getContextPath() %>/resources/js/jquery.min.js"></script><!-- jquery -->
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script><!-- bootstrap -->
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert.min.js"></script><!-- sweetalert -->
<script src="<%= request.getContextPath() %>/resources/js/adminlte.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.dataTables.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/dataTables.bootstrap4.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/bootstrap-colorpicker.js"></script><!-- colorpicker -->
<script src="<%= request.getContextPath() %>/resources/js/common.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/adminScript.js"></script><!-- 어드민 전용 -->

<head>
<style>
	/* all elements */
	body, button {
		font-size: 14px !important;
		font-family: "Nanum Gothic" !important;
	}
	
	/* ?? */
	a.top-menu {
		margin-top: -5px;
	}
	
	/* sidemenu logout button */
	.user-panel:hover { cursor: pointer; }
	.user-menu-on { display: block; background: #343a40; color: #fff; position: absolute; left: 20%; width: 50%; margin-top: -25px; border: 1px solid #4f5962; padding: 3px; }
	.user-menu-off { display: none; }
	.user-panel-contents { cursor: pointer; }

	/* padding */
	div.padding { padding-top: 15px; padding-bottom: 15px; }
	div.padding-sm { padding-top: 5px; padding-bottom: 5px; }
	div.padding-md { padding-top: 10px; padding-bottom: 10px; }
	div.padding-lg { padding-top: 15px; padding-bottom: 15px; }
	
	/* tooltip */
	div.tooltip { font-size: 12px !important; font-family: "Nanum Gothic" !important; }
	
	/* dashboard */
	div.info-box { cursor: pointer; }
	div.dashboard-back { padding-top: 25px; }
	span.dashboard-left { padding: 10px 10px 10px 0; border-right: 1px solid #d3d3d3; font-size: 18px; font-weight: bold; }
	span.dashboard-right { font-weight: bold; font-size: 18px; }
	
	/* datatable, text-align */
	.dt-center { text-align: center; }
	.dt-left { text-align: left; }
	.dt-right { text-align: right; }
	
	/* sidemenu */
	.nav>li>a:focus { color: #fff; background-color: rgba(255,255,255,.1); }
	
	/* page, table icons */
	i.page-icon { cursor: pointer; }
	i.page-icon:hover { color: #00c0ef; }
	i.disabled-icon { color: #6c757d; opacity: 0.65; }
	
	/* input[text] - column */
	span.line-height { line-height: 30px; }
	
	/* input field */
	input[type=text], input[type=date], input[type=button] { font-size: 14px; }
	
	/* select field */
	select { height: 34px !important; font-size: 14px !important; font-family: "Nanum Gothic" !important; }
	
	/* sidemenu bar */
	.main-sidebar { z-index: 1000 !important; }
	
	/* sweetalert */
	.sweet-overlay { z-index: 1060 !important; }
	.sweet-alert { z-index: 1070 !important; }
	.lead { font-size: 14px !important; font-family: "Nanum Gothic" !important; padding-top: 10px !important; }
	
	/* admin lte a tag hover */
	.nav-pills .nav-link:not(.active):hover { color: #fff !important; background: #007bff; }
</style>

</head>
<body class="hold-transition sidebar-mini">

<div class="wrapper">
	<!-- Navbar -->
	<nav class="main-header navbar navbar-expand bg-white navbar-light border-bottom">
		<!-- Left navbar links -->
		<ul class="navbar-nav">
			<!-- <li class="nav-item">
					<a class="nav-link" data-widget="pushmenu" href="#"><i class="fa fa-bars"></i></a>
				</li> -->
			<li class="nav-item d-none d-sm-inline-block">
				<a href="${ pageContext.request.contextPath }/admin/adminMain" class="nav-link top-menu"> 메인 </a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="${ pageContext.request.contextPath }/admin/pageSetting?m=manage" class="nav-link top-menu"> 페이지 설정 </a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="${ pageContext.request.contextPath }/admin/visitHistory" class="nav-link top-menu"> 방문기록 </a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="${ pageContext.request.contextPath }/admin/powerGeneration" class="nav-link top-menu"> 전력량 </a>
			</li>
		</ul>
	</nav>
	<!-- /.navbar -->

	<!-- Main Sidebar Container -->
	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo -->
		<a href="${ pageContext.request.contextPath }/admin/adminMain" class="brand-link">
			<img src="${ pageContext.request.contextPath }/resources/css/img/Frozen-admin-logo.png" alt="Frozen-admin-logo" class="brand-image img-circle elevation-3" style="opacity: 0.8; float: unset;">
			<span class="brand-text font-weight-light">Frozen-admin</span>
		</a>

	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex" onclick="profile_dropdown()">
			<div class="image">
				<img src="${ pageContext.request.contextPath }/resources/css/img/profile.jpg" class="img-circle elevation-2" alt="User Image" style="width: 3.1rem;">
			</div>
			<div class="info">
				<a class="d-block">이동욱</a>
			</div>
		</div>
		<div class="user-menu-off" id="user_panel">
			<div class="user-panel-contents" onclick="admin_logout()">
				<i class="fa fa-caret-right" style="font-size: 14px;"></i>&nbsp;로그아웃
			</div>
		</div>

	<!-- Sidebar Menu -->
	<nav class="mt-2">
		<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
			<li class="nav-item has-treeview manage"><!-- menu-open / active -->
				<a href="#" class="nav-link">
					<i class="nav-icon fa fa-dashboard text-primary"></i>
					<p> 관리 <i class="right fa fa-angle-left text-warning"></i> </p>
            	</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/pageSetting?m=manage" class="nav-link pageSetting">
							<i class="fa fa-cog nav-icon" aria-hidden="true"></i> 
							<p>페이지 기본 설정</p>
						</a>
					</li>
				</ul>
			</li>
			<li class="nav-item has-treeview portfolio"><!-- add menu-open -->
				<a href="#" class="nav-link">
					<i class="nav-icon fa fa-pie-chart text-success"></i>
					<p> 포트폴리오 <i class="right fa fa-angle-left text-warning"></i> </p>
				</a>
				<ul class="nav nav-treeview">
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/profile?m=portfolio" class="nav-link profile"><!-- add active -->
							<i class="fa fa-user nav-icon text-info"></i>
							<p>프로필</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/program?m=portfolio" class="nav-link program">
							<i class="fa fa-file nav-icon text-info"></i>
							<p>프로그램</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/skill?m=portfolio" class="nav-link skill">
							<i class="fa fa-android nav-icon text-info"></i>
							<p>기술</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/experience?m=portfolio" class="nav-link experience">
							<i class="fa fa-plus nav-icon text-info"></i>
							<p>경험</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="${ pageContext.request.contextPath }/admin/contact?m=portfolio" class="nav-link contact">
							<i class="fa fa-address-book nav-icon text-info"></i>
							<p>연락처</p>
						</a>
					</li>
				</ul>
			</li>
			
			<li class="nav-header">MORE</li>
			
			<li class="nav-item">
				<a href="#" class="nav-link" onclick="javascript:alert('대기')">
					<i class="nav-icon fa fa-list" aria-hidden="true"></i>
					<p> 지원 목록 </p>
				</a>
			</li>
		</ul>
	</nav>
	<!-- /.sidebar-menu -->
	</div>
</aside>