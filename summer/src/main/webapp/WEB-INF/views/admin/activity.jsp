<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/activity/activity.js"></script>

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
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="목록 보기" onclick="getActivityStorageModal(); buttonBlur(this);"> <i class="fa fa-list"></i> </button>
					</div>
				</div>
				<div class="card-body">
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<table id="activity_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
							
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="activity_modal">
	<div class="modal-dialog" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 목록 보기 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<table id="activity_storage_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
							
						</table>
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade in" id="modify_activity_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 활동 수정 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" onclick="">저장</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>