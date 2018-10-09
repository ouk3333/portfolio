<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/powerGeneration.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			<div class="row" style="padding-left: 7px;">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					상태 : <span id="powerGeneration_state">  </span>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
					기준 : <span id="powerGeneration_time">  </span>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<table id="powerGeneration_table" class="table table-bordered table-hover dataTable" role="grid" aria-describedby="powerGenerationTable">
						
					</table>
				</div>
			</div>
			
			<div class="padding"></div>
			
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-info elevation-1"><i class="fa fa-percent" aria-hidden="true"></i></span>
		
						<div class="info-box-content dashboard-back">
							<span class="dashboard-left">공급예비력</span>
							<span class="dashboard-right" id="suppReservePwr"> - </span>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon bg-info elevation-1"><i class="fa fa-percent" aria-hidden="true"></i></span>
		
						<div class="info-box-content dashboard-back">
							<span class="dashboard-left">공급예비율</span>
							<span class="dashboard-right" id="suppReserveRate"> - </span>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon elevation-1"><i class="fa fa-bolt" aria-hidden="true"></i></span>
		
						<div class="info-box-content dashboard-back">
							<span class="dashboard-left">운영예비력</span>
							<span class="dashboard-right" id="operReservePwr"> - </span>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-6">
					<div class="info-box">
						<span class="info-box-icon elevation-1"><i class="fa fa-users" aria-hidden="true"></i></span>
		
						<div class="info-box-content dashboard-back">
							<span class="dashboard-left">운영예비율</span>
							<span class="dashboard-right" id="operReserveRate"> - </span>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>