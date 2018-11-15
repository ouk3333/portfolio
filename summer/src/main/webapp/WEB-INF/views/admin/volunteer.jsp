<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/etc/volunteer.js?${timestamp}"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						&nbsp;
					</h3>
					<div class="card-tools">
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="추가" onclick="insertVolunteerModal(); buttonBlur(this);"> <i class="fa fa-plus-square-o page-icon" style="font-size: 18px;"></i> </button>
					</div>
				</div>
				
				<div class="card-body" id="chart_area">

					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table id="volunteer_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
							
						</table>
					</div>
					
				</div>
			</div>

		</div>
	</div>
</div>

<div class="modal fade in" id="volunteer_modal">
	<div class="modal-dialog" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 데이터 추가 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<span> 기업 명칭 </span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 dt-center">
						<span> 기업 위치 </span>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<span> 지원 날짜 </span>
					</div>
				</div>
				
				<div class="padding-sm"></div>
				
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<input type="text" class="form-control" id="new_name">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 dt-center">
						<input type="text" class="form-control" id="new_location">
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<input type="date" class="form-control" id="new_date">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="insertVolunteerData()">추가</button>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade in" id="modify_volunteer_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 데이터 수정 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<span> 기업 명칭 </span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 dt-center">
						<span> 기업 위치 </span>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<span> 지원 날짜 </span>
					</div>
				</div>
				
				<div class="padding-sm"></div>
				
				<div class="row">
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<input type="text" class="form-control" id="modify_name">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 dt-center">
						<input type="text" class="form-control" id="modify_location">
					</div>
					<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 dt-center">
						<input type="date" class="form-control" id="modify_date">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="" id="modify_volunteer_button">수정</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>