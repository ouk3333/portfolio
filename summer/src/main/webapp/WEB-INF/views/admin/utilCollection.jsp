<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/manage/shaEncoding.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/manage/shortURLGenerator.js"></script>

<div class="content-wrapper">
	
	<%@ include file="/WEB-INF/views/inc/adminNavigator.jsp" %>
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">
								SHA256 생성기
							</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-toggle-value="convertToSha" onclick="toggleDIV(this); buttonBlur(this);">
									<i class="fa fa-minus page-icon" style="font-size: 18px;"></i>
								</button>
							</div>
						</div>
						<div class="card-body" id="convertToSha">
							<div class="row">
								<div class="col-2 dt-right">
									<span class="line-height"> 문자열 입력 </span>
								</div>
								<div class="col-9">
									<input type="text" class="form-control" id="convert_str">
								</div>
								<div class="col-1">
									<input type="button" class="btn btn-info" value="변환" onclick="convertToSHA()">
								</div>
							</div>
							
							<div class="padding-lg"></div>
							
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 dt-center">
									<i class="fa fa-arrow-down" style="font-size: 28px;"></i>
								</div>
							</div>
							
							<div class="padding-lg"></div>
							
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<input type="text" class="form-control" id="convert_result" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">
								단축 URL 생성기
							</h3>
							<div class="card-tools">
								<button type="button" class="btn btn-tool" data-toggle-value="shortURLGnerator" onclick="toggleDIV(this); buttonBlur(this);">
									<i class="fa fa-minus page-icon" style="font-size: 18px;"></i>
								</button>
							</div>
						</div>
						<div class="card-body" id="shortURLGnerator">
							<div class="row">
								<div class="col-2 dt-right">
									<span class="line-height"> 문자열 입력 </span>
								</div>
								<div class="col-9">
									<input type="text" class="form-control" id="">
								</div>
								<div class="col-1">
									<input type="button" class="btn btn-info" value="생성" onclick="">
								</div>
							</div>
							
							<div class="padding-lg"></div>
							
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 dt-center">
									<i class="fa fa-arrow-down" style="font-size: 28px;"></i>
								</div>
							</div>
							
							<div class="padding-lg"></div>
							
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<input type="text" class="form-control" id="" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>