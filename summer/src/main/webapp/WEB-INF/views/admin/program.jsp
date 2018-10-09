<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/program/program.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
					<!-- <span style="padding-left: 10px;"> 프로그램 </span> -->
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 dt-right">
					<span style="padding-right: 10px;"> <i class="fa fa-list tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="목록 보기" onclick="programModalCall()"></i> </span>
				</div>
				
				<!-- 이전 목록 밑에 새로 등록 -->
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<table id="program_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
						<thead>
							<tr>
								<th> 제목 </th>
								<th> 언어 </th>
								<th> 기술 </th>
								<th> 시작일 </th>
								<th> 종료일 </th>
								<th> 이미지 </th>
								<th> 순서 </th>
								<th>  </th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="program_modal">
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
						<table id="program_storage_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
							
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

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>