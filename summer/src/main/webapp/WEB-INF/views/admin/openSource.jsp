<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/etc/openSource.js"></script>

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
						<!-- <button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="추가" onclick=""> <i class="fa fa-plus-square-o"></i> </button> -->
					</div>
				</div>
				<div class="card-body">
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
						
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-right">
						<span style="padding-right: 10px;" >
							<i class="fa fa-plus-square-o page-icon tooltips" style="font-size: 18px;" data-toggle="tooltip" data-placement="bottom" data-original-title="추가" onclick="addOpenSourceDataModal()"></i>
						</span>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table id="openSource_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
							
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="modify_openSource_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 오픈소스 수정 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row dt-center">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<span> 명칭 </span>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<span> 종류 </span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span> URL </span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<input type="text" class="form-control" id="modify_name">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<input type="text" class="form-control" id="modify_type">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<input type="text" class="form-control" id="modify_url">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="" id="modify_button">저장</button>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade in" id="add_openSource_modal">
	<div class="modal-dialog" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 오픈소스 데이터 추가 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row dt-center">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<span> 명칭 </span>
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<span> 종류 </span>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<span> URL </span>
					</div>
				</div>
				
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
						<input type="text" class="form-control" id="new_name">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<input type="text" class="form-control" id="new_type">
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<input type="text" class="form-control" id="new_url">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="addOpenSourceData()">저장</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>