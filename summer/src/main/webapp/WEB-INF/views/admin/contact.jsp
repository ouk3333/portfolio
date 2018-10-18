<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/contact/contact.js"></script>

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
						<button type="button" class="btn btn-tool" data-toggle="tooltip" data-placement="bottom" data-original-title="저장" onclick="setContactData(); buttonBlur(this);"> <i class="fa fa-floppy-o"></i> </button>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-right">
							<span class="line-height"> <i class="fa fa-comments"></i> 카카오</span>
						</div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
							<input type="text" class="form-control" id="kakaotalk">
						</div>
					</div>
					
					<div class="padding-sm"></div>
					
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-right">
							<span class="line-height"> <i class="fa fa-facebook-official"></i> 페이스북</span>
						</div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
							<input type="text" class="form-control" id="facebook">
						</div>
					</div>
					
					<div class="padding-sm"></div>
					
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-right">
							<span class="line-height"> <i class="fa fa-instagram"></i> 인스타그램</span>
						</div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
							<input type="text" class="form-control" id="instagram">
						</div>
					</div>
					
					<div class="padding-sm"></div>
					
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-right">
							<span class="line-height"> <i class="fa fa-mobile"></i> 휴대폰 번호 - 캡차 포함</span>
						</div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
							<input type="text" class="form-control" id="phone">
						</div>
					</div>
					
					<div class="padding-sm"></div>
					
					<div class="row">
						<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 dt-right">
							<span class="line-height"> <i class="fa fa-envelope"></i> 이메일 </span>
						</div>
						<div class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
							<input type="text" class="form-control" id="email">
						</div>
					</div>
					
				</div>
			</div>
			
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>