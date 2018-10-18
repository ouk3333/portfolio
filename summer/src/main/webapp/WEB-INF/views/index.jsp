<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/views/inc/portfolioCSS.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body id="page-top">
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top nav-image" id="sideNav">
		<a class="navbar-brand js-scroll-trigger" href="#page-top">
			<span class="d-block d-lg-none"> - </span>
			<span class="d-none d-lg-block">
			<!-- <img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="img/profile.jpg" alt=""> -->
			</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent" style="padding-bottom: 100%;">
			<ul class="navbar-nav" id="scroll_nav">
				<li class="nav-item" style="color: #fff; opacity: 0.8;">
					<i class="fa fa-minus" aria-hidden="true"></i>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger active" href="#profile">profile</a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="#program">program</a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="#skill">skill</a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="#activity">activity</a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="#contact">contact</a>
				</li>
				<li class="nav-item" style="color: #fff; opacity: 0.8;">
					<i class="fa fa-minus" aria-hidden="true"></i>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container-fluid p-0 section-color-setting">
		<section class="resume-section p-lg-4 d-column" id="profile">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 content-left">
					<h2>
						<span class="title-color">Profile</span>
					</h2>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div id="timeline">
						
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<table class="license">
						<tr>
							<th colspan="2" class="content-center">보유 자격증</th>
						</tr>
						<tr>
							<th>자격증</th>
							<td>정보처리 산업기사</td>
						</tr>
						<tr>
							<th>자격증</th>
							<td>정보처리 기능사</td>
						</tr>
						<tr>
							<th>자격증</th>
							<td>컴퓨터활용능력 2급</td>
						</tr>
						<tr>
							<th>면허증</th>
							<td>1종 보통</td>
						</tr>
					</table>
				</div>
				
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" id="typeout_box" style="margin-top: 15px;">
					
				</div>
			</div>
		</section>
		
		<section class="resume-section p-lg-4 d-column" id="program">
			<div class="row">
				<div class="col-sm-12 content-left">
					<h2>
						<span class="title-color">Program</span>
					</h2>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">left</div>
				<div class="col-sm-6">right</div>
			</div>
		</section>

		<section class="resume-section p-lg-4 d-column" id="skill">
			<div class="row">
				<div class="col-sm-12 content-left">
					<h2>
						<span class="title-color">Skill</span>
					</h2>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">left</div>
				<div class="col-sm-6">right</div>
			</div>
		</section>

		<section class="resume-section p-lg-4 d-column" id="activity">
			<div class="row">
				<div class="col-sm-12 content-left">
					<h2>
						<span class="title-color">Activity</span>
					</h2>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">left</div>
				<div class="col-sm-6">right</div>
			</div>
		</section>

		<section class="resume-section p-lg-4 d-column" id="contact">
			<div class="row">
				<div class="col-sm-12 content-left">
					<h2>
						<span class="title-color">Contact</span>
					</h2>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">left</div>
				<div class="col-sm-6">right</div>
			</div>
		</section>

    </div>
    <div style="height: 30px; color: #000; text-align: center;">
    	Made by DONGWOOK, 2018 <a href="${ pageContext.request.contextPath }/admin/login"><i class="fa fa-cogs"></i></a> | <a>open-sources list</a>
    </div>
  </body>

</html>

<%@ include file="/WEB-INF/views/inc/portfolioScript.jsp" %>