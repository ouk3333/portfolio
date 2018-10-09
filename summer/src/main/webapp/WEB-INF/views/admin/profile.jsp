<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/ckeditor/ckeditor.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/profile/controller.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/profile/timeline.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/profile/license.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/profile/intro.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.typeout.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header d-flex p-0">
							<ul class="nav nav-pills ml-auto p-2">
								<li class="nav-item"><a class="nav-link active" href="#timeline_tab" data-toggle="tab" data-value="timeline_tab" onclick="tab_click_func(this)"> 타임라인 </a></li>
								<li class="nav-item"><a class="nav-link" href="#license_tab" data-toggle="tab" data-value="license_tab" onclick="tab_click_func(this)"> 자격증 </a></li>
								<li class="nav-item"><a class="nav-link" href="#intro_tab" data-toggle="tab" data-value="intro_tab" onclick="tab_click_func(this)"> 자기소개 </a></li>
							</ul>
						</div>
						
						<div class="card-body">
							<div class="tab-content">
								<div class="tab-pane active" id="timeline_tab">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<span style="padding-left: 10px;">타임라인</span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 dt-right">
											<span style="padding-right: 10px;"> <i class="fa fa-list tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="목록 보기" onclick="timeline_modal()"></i> </span>
										</div>
										
										<!-- 이전 목록 밑에 새로 등록 -->
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<table id="timeline_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
												<thead>
													<tr>
														<th> 주요 행사 </th>
														<th> 날짜 </th>
														<th> 세부사항 </th>
														<th> 순서 </th>
														<th>  </th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								
								<div class="tab-pane" id="license_tab">
									<div class="row">
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
											<span style="padding-left: 10px;">자격증</span>
										</div>
										<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 dt-right">
											<span style="padding-right: 10px;"> <i class="fa fa-list tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="목록 보기" onclick="license_modal()"></i> </span>
										</div>
										
										<!-- 이전 목록 밑에 새로 등록 -->
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<table id="license_table" class="table table-bordered table-hover dataTable dt-center" style="width: 100%;">
												<thead>
													<tr>
														<th> 종류 </th>
														<th> 자격증 명 </th>
														<th> 부가사항 </th>
														<th> 순서 </th>
														<th>  </th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
								
								<div class="tab-pane" id="intro_tab">
									<div class="row">
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
											<span> 자기소개 </span>
										</div>
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-right">
											<span style="padding-right: 3px;">
												<i class="fa fa-play tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="미리보기" onclick="previewIntroData()"></i>
												&nbsp;
												<i class="fa fa-undo tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="되돌리기" onclick="undoIntroData()"></i>
												&nbsp;
												<i class="fa fa-floppy-o tooltips page-icon" data-toggle="tooltip" data-placement="bottom" data-original-title="저장" onclick="setIntroData()"></i>
											</span>
										</div>
										<div class="padding-lg"></div>
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											<form>
												<textarea rows="10" cols="80" id="intro_textarea" name="intro_textarea">
												</textarea>
											</form>
										</div>
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-top: 15px;">
											<div id="preview_typeout" style="border: 1px solid #ccc;">
												미리보기 영역
											</div>
										</div>
										<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="margin-top: 15px;">
											<div class="row">
												<div class="col-12" style="background: #ccccccc4; position: absolute; height: 100%; z-index: 1;" id="typeout_blockUI">
													<div style="position: absolute; left: 50%; top: 50%;">
														<input type="button" class="btn btn-info" value="수정" onclick="typeout_blockUI()">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-3 dt-right">
													<div style="margin-top: 7px;"> 마커 모양 :  </div>
												</div>
												<div class="col-9">
													<select class="form-control" style="width: 50%;" id="intro_marker">
														<option value="_">_</option>
														<option value="|">|</option>
														<option value="ㅣ">ㅣ</option>
														<option value="*">*</option>
														<option value="@">@</option>
														<option value="#">#</option>
														<option value="$">$</option>
													</select>
												</div>
											</div>
											<div class="row">
												<div class="col-3 dt-right">
													<div style="margin-top: 7px;"> 진행 속도 :  </div>
												</div>
												<div class="col-9">
													<input type="text" class="form-control" style="width: 50%;" value="100" id="intro_delay" onkeyup="filter_number(this);" onkeypress="filter_number(this);" onkeydown="filter_number(this);">
												</div>
											</div>
											<div class="row">
												<div class="col-3 dt-right">
													<div style="margin-top: 7px;"> html태그허용 : </div>
												</div>
												<div class="col-9">
													<select class="form-control" style="width: 50%;" id="intro_preserve">
														<option value=1>true</option>
														<option value=0>false</option>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="timeline_modal">
	<div class="modal-dialog" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				타임라인 목록
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<table id="timeline_storage_table" class="table table-bordered table-hover dataTable" style="width: 100%;">
					<thead>
						<tr>
							<th> 주요 행사 </th>
							<th> 날짜 </th>
							<th> 세부사항 </th>
							<th>  </th>
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

<div class="modal fade in" id="timeline_modify_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				수정
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-center">
						<div> 주요 행사 </div>
						<input type='text' placeholder='주요 행사 입력' class='form-control' id="modify_event">
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-center">
						<div> 날짜 입력 </div>
						<input type='date' class='form-control' id="modify_date">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-center">
						<div> 세부사항 입력 </div>
						<input type='text' placeholder='세부 사항 입력' class='form-control' id="modify_remarks">
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" id="modify_save">저장</button>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade in" id="license_modal">
	<div class="modal-dialog" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				자격증 목록
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<table id="license_storage_table" class="table table-bordered table-hover dataTable" style="width: 100%;">
					<thead>
						<tr>
							<th> 종류 </th>
							<th> 자격증 명 </th>
							<th> 부가사항 </th>
							<th>  </th>
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

<div class="modal fade in" id="license_modify_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				자격증 수정
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-center">
						<div> 종류 </div>
						<select id='modify_license_type' class="form-control">
							<option value=1> 자격증 </option>
							<option value=2> 면허증 </option>
							<option value=3> 보류 </option>
						</select>
						<!-- <input type='text' placeholder='주요 행사 입력' class='form-control' id="modify_license_event"> -->
					</div>
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-center">
						<div> 자격증명 </div>
						<input type='text' class='form-control' id="modify_license_name">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 dt-center">
						<div> 부가사항 </div>
						<input type='text' placeholder='세부 사항 입력' class='form-control' id="modify_license_remarks">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" id="modify_license_save">저장</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>