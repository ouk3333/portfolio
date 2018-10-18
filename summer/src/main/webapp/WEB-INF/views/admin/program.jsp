<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/program/program.js"></script>

<style>

.btn-app { padding: unset !important; min-width: 50px !important; height: 32px !important; padding-top: 5px !important; }

</style>

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
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="목록 보기" onclick="programModalCall()"> <i class="fa fa-list"></i> </button>
					</div>
				</div>
				<div class="card-body">
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

<div class="modal fade in" id="program_modify_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> 수정 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row dt-center">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
						<div> 제목 </div>
						<input type="text" class="form-control" id="modify_program_title" placeholder="프로그램 명칭">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<div> 언어 </div>
						<input type="text" class="form-control" id="modify_program_language" placeholder="사용한 언어">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<div> 시작일 </div>
						<input type="date" class="form-control" id="modify_program_start_date">
					</div>
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<div> 종료일 </div>
						<input type="date" class="form-control" id="modify_program_end_date">
					</div>
				</div>
				
				<div class="padding-sm"></div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div> 기술 </div>
						<input type="text" class="form-control" id="modify_program_skill" placeholder="사용한 기술">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-info" data-dismiss="modal" id="modify_program_button">저장</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>