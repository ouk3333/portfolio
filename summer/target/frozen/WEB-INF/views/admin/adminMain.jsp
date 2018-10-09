<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>

<div class="content-wrapper">
    
    <%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>

	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box tooltips" onclick="dashboard_click('visitor')" data-toggle="tooltip" data-placement="bottom" title="방문기록 페이지로 이동">
						<span class="info-box-icon bg-warning elevation-1"><i class="fa fa-users" aria-hidden="true"></i></span>
		
						<div class="info-box-content">
							<span class="info-box-text">전체 방문자</span>
							<span class="info-box-number">10<small>명</small></span>
						</div>
					</div>
				</div>
				
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box" onclick="dashboard_click('visitor')" data-toggle="tooltip" data-placement="bottom" title="방문기록 페이지로 이동">
						<span class="info-box-icon bg-success elevation-1"><i class="fa fa-user" aria-hidden="true"></i></span>
		
						<div class="info-box-content">
							<span class="info-box-text">금일 방문자</span>
							<span class="info-box-number">10<small>명</small></span>
						</div>
					</div>
				</div>
				
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box">
						<span class="info-box-icon bg-info elevation-1"><i class="fa fa-hdd-o" aria-hidden="true"></i></span>
		
						<div class="info-box-content">
							<span class="info-box-text">디스크 사용량</span>
							<span class="info-box-number">20<small>%</small></span>
						</div>
					</div>
				</div>
				
				<div class="col-12 col-sm-6 col-md-3">
					<div class="info-box" onclick="dashboard_click('powerGeneration')" data-toggle="tooltip" data-placement="bottom" title="전력량 페이지로 이동">
						<span class="info-box-icon bg-primary elevation-1"><i class="fa fa-battery-full" aria-hidden="true"></i></span>
		
						<div class="info-box-content">
							<span class="info-box-text">가용 전력량</span>
							<span class="info-box-number">15<small>%</small></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>