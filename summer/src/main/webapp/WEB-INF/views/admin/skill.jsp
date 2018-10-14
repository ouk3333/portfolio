<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/skill/skill.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-title">
							<div class="card-header">
								<h3 class="card-title">
									<i class="fa fa-bar-chart-o"></i> 능력치 수정
								</h3>
								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-type="ability" onclick="skillToggleArea(this)">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-tool tooltips" data-toggle="tooltip" data-placement="bottom" title="목록 보기" onclick="skillAbilityStorageModal(); skillButtonBlur(this)">
										<i class="fa fa-list"></i>
									</button>
									<button type="button" class="btn btn-tool tooltips" data-toggle="tooltip" data-placement="bottom" title="저장" onclick="setSkillAbilityData(); skillButtonBlur(this)">
										<i class="fa fa-floppy-o"></i>
									</button>
								</div>
							</div>
							
							<div class="card-body" id="ability_area">
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="padding-sm"></div>
			
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-title">
							<div class="card-header">
								<h3 class="card-title">
									<i class="fa fa-eercast"></i> 색상 수정
								</h3>
								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-type="color" onclick="skillToggleArea(this)">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-tool tooltips" data-toggle="tooltip" data-placement="bottom" title="저장" onclick="">
										<i class="fa fa-floppy-o"></i>
									</button>
								</div>
							</div>
							
							<div class="card-body" id="color_area">
								
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade in" id="skillAbility_modal">
	<div class="modal-dialog" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				기술 목록
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				<table id="skill_storage_table" class="table table-bordered table-hover dataTable" style="width: 100%;">
					
				</table>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>

<div class="modal fade in" id="skillAbilityModify_modal">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 800px;">
		
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				기술 수정
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 dt-center">
						<input type="text" class="form-control" id="modify_skill_name" placeholder="기술 명칭 입력">
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 dt-center">
						<input type="text" class="form-control" id="modify_skill_background_color" placeholder="ex) #000000">
					</div>
					<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 dt-center">
						<input type="text" class="form-control" id="modify_skill_font_color" placeholder="ex) #000000">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-info" data-dismiss="modal" id="skill_save_button">저장</button>
			</div>
		</div>
		
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>