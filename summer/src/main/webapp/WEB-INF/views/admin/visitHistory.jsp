<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/googleChart.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/etc/visitHistory.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						통계
					</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="접기" data-type="chart" onclick="skillToggleArea(this); buttonBlur(this);"> <i class="fa fa-minus page-icon" style="font-size: 18px;"></i> </button>
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="새로고침" onclick="refresh_chart(); buttonBlur(this);"> <i class="fa fa-refresh page-icon" style="font-size: 18px;"></i> </button>
					</div>
				</div>
				
				<div class="card-body" id="chart_area">
				
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-center" id="ip_chart">
						
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-center" id="os_chart">
						
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 dt-center" id="browser_chart">
						
					</div>
					
				</div>
			</div>
			
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						위경도 좌표
					</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="접기" data-type="coordinates" onclick="skillToggleArea(this); buttonBlur(this);"> <i class="fa fa-minus page-icon" style="font-size: 18px;"></i> </button>
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="새로고침" onclick="getVisitorIpAddress(); buttonBlur(this);"> <i class="fa fa-refresh page-icon" style="font-size: 18px;"></i> </button>
					</div>
				</div>
				
				<div class="card-body" id="coordinates_area">
					<table id="visitor_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
						
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>