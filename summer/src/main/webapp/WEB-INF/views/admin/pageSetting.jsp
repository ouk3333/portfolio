<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/setting.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="row">
				<div class="col-12" style="text-align: right;">
					<i class="fa fa-bars tooltips page-icon" aria-hidden="true" style="font-size: 18px; margin-right: 15px;" data-toggle="tooltip" data-placement="bottom" title="불러오기" onclick="getConfigHist()"></i>
					&nbsp;
					<i class="fa fa-floppy-o tooltips page-icon" aria-hidden="true" style="font-size: 18px; margin-right: 15px;" data-toggle="tooltip" data-placement="bottom" title="저장" onclick="setConfiguration()"></i>
				</div>
			</div>
			
			<div style="padding-top: 15px;"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 페이지 제목 : </span>
				</div>
				<div class="col-10">
					<input type="text" class="form-control" style="width: 80%; display: inline-flex;" id="page_title">
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 메인 색상 : </span>
				</div>
				<div class="col-10">
					<input type="text" class="form-control" style=" width: 80%; display: inline-flex" id="main_color" maxlength="7">
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 포인트 색상 : </span>
				</div>
				<div class="col-10">
					<input type="text" class="form-control" style=" width: 80%; display: inline-flex" id="point_color" maxlength="7">
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 타이틀 색상 : </span>
				</div>
				<div class="col-10">
					<input type="text" class="form-control" style=" width: 80%; display: inline-flex" id="title_color" maxlength="7">
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 사이드메뉴 이미지 : </span>
				</div>
				<div class="col-10">
					<input type="text" class="form-control" style=" width: 80%; display: inline-flex" id="sidemenu_image" readonly="readonly">
					<input type="button" value="파일 선택" class="btn btn-info">
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-2 dt-right">
					<span class="line-height"> 프로그램 레이아웃 : </span>
				</div>
				<div class="col-10">
					<select id="program_layout" class="form-control" style="width: 10%;">
						<option value=1> 1 </option>
						<option value=2> 2 </option>
						<option value=3> 3 </option>
						<option value=4> 4 </option>
					</select>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="config_hist_modal">
	<div class="modal-dialog" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				이전 설정 목록
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<table id="config_hist_table" class="table table-bordered table-hover dataTable">
					<thead>
						<tr>
							<th> 변경일 </th>
							<th> 페이지 제목 </th>
							<th> 메인 색상 </th>
							<th> 포인트 색상 </th>
							<th> 타이틀 색상 </th>
							<th> 사이드메뉴 이미지 </th>
						</tr>
					</thead>
				</table>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>