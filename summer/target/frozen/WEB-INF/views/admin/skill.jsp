<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %>
<script src="<%= request.getContextPath() %>/resources/js/skill/skill.js"></script>

<style>
	.card-title { font-size: 1.75rem !important; }
</style>

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
									<button type="button" class="btn btn-tool tooltips" data-caption="ability" data-toggle="tooltip" data-placement="bottom" title="접기" onclick="toggleCardBody(this)">
										<i class="fa fa-minus"></i>
									</button>
									<button type="button" class="btn btn-tool tooltips" data-toggle="tooltip" data-placement="bottom" title="저장" onclick="">
										<i class="fa fa-floppy-o"></i>
									</button>
								</div>
							</div>
							
							<div class="card-body">
								<div class="row">
									<div class="col-12">
										asdfjasldkfjlds
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

<%@ include file="/WEB-INF/views/inc/adminFooter.jsp" %>