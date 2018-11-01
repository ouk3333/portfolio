<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS -->
<link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/adminlte.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/dataTables.bootstrap4.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/font.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/bootstrap-colorpicker.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/sweetalert.css" rel="stylesheet">

<!-- ------------------------------------------------------------ -->

<!-- JAVASCRIPT -->
<script src="<%= request.getContextPath() %>/resources/js/jquery.min.js"></script><!-- jquery -->
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script><!-- bootstrap -->
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.bundle.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert.min.js"></script><!-- sweetalert -->
<script src="<%= request.getContextPath() %>/resources/js/adminlte.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery.dataTables.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/dataTables.bootstrap4.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/bootstrap-colorpicker.js"></script><!-- colorpicker -->
<script src="<%= request.getContextPath() %>/resources/js/common.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/adminScript.js"></script><!-- 어드민 전용 -->

<head>
<style>
	/* all elements */
	body, button {
		font-size: 14px !important;
		font-family: "Nanum Gothic" !important;
	}
	
	/* ?? */
	a.top-menu {
		margin-top: -5px;
	}
	
	/* sidemenu logout button */
	.user-panel:hover { cursor: pointer; }
	.user-menu-on { display: block; background: #343a40; color: #fff; position: absolute; left: 20%; width: 50%; margin-top: -25px; border: 1px solid #4f5962; padding: 3px; }
	.user-menu-off { display: none; }
	.user-panel-contents { cursor: pointer; }

	/* padding */
	div.padding { padding-top: 15px; padding-bottom: 15px; }
	div.padding-sm { padding-top: 5px; padding-bottom: 5px; }
	div.padding-md { padding-top: 10px; padding-bottom: 10px; }
	div.padding-lg { padding-top: 15px; padding-bottom: 15px; }
	
	/* tooltip */
	div.tooltip { font-size: 12px !important; font-family: "Nanum Gothic" !important; }
	
	/* dashboard */
	div.info-box { cursor: pointer; }
	div.dashboard-back { padding-top: 25px; }
	span.dashboard-left { padding: 10px 10px 10px 0; border-right: 1px solid #d3d3d3; font-size: 18px; font-weight: bold; }
	span.dashboard-right { font-weight: bold; font-size: 18px; }
	
	/* datatable, text-align */
	.dt-center { text-align: center; }
	.dt-left { text-align: left; }
	.dt-right { text-align: right; }
	
	/* sidemenu */
	.nav>li>a:focus { color: #fff; background-color: rgba(255,255,255,.1); }
	
	/* page, table icons */
	i.page-icon { cursor: pointer; }
	i.page-icon:hover { color: #00c0ef; }
	i.disabled-icon { color: #6c757d; opacity: 0.65; }
	
	/* input[text] - column */
	span.line-height { line-height: 30px; }
	
	/* input field */
	input[type=text], input[type=date], input[type=button] { font-size: 14px; }
	
	/* select field */
	select { height: 34px !important; font-size: 14px !important; font-family: "Nanum Gothic" !important; }
	
	/* sidemenu bar */
	.main-sidebar { z-index: 1000 !important; }
	
	/* sweetalert */
	.sweet-overlay { z-index: 1060 !important; }
	.sweet-alert { z-index: 1070 !important; }
	.lead { font-size: 14px !important; font-family: "Nanum Gothic" !important; padding-top: 10px !important; }
	
	/* admin lte a tag hover */
	.nav-pills .nav-link:not(.active):hover { color: #fff !important; background: #007bff; }
	
	.content-wrapper { margin-left: 0 !important; background-color: #fff !important; }
	.users-list>li img { border-radius: unset !important; }
</style>

<title> programImage </title>
</head>
<input type="hidden" id="uid" name="uid" value=${ uid }>
<body>
	<div class="content-wrapper">
	
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			
			<div class="row">
				<div class="col-12">
					<div class="card-body">
						<ul class="users-list clearfix" id="image_area">
							
						</ul>
					</div>
				</div>
			</div>
			
			<div class="row" style="margin-top: 450px;">
				<div class="col-12" style="display: flex;">
					<input type="file" name="file" value="파일 선택" id="upload_file">
					<input type="button" value="업로드" id="upload_button">
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>

<script>

var upload_button_func = function() {
	
	$('#upload_button').click(function() {
		
		var formData = new FormData();
		
		formData.append("uploadfile", $("#upload_file")[0].files[0]);
		formData.append("uid", $('#uid').val());
		
		if( $("#upload_file")[0].files[0] == null || $("#upload_file")[0].files[0] == undefined ) {
			
			show_alert("info", "파일을 선택해주세요", 1000);
			
			return false;
		}
		
		$.ajax({
			url: getContextPath() + '/admin/program/programImageFileUpload',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			success: function( data ) {
				show_alert("success", "업로드 성공", 1000);
				
				$("#upload_file").val(""); // 파일 업로드 후 꼭 초기화 해줄것
			}
		})
		
	});
	
}

$(document).ready(function() {
	
	var uid = "${uid}";
	
	upload_button_func();
	getPreviewProgramImage( uid );
});

var getPreviewProgramImage = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/program/getPreviewProgramImage',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( request, status, error ) {
			console.log( "request: " + request);
			console.log( "status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			for( var i = 0; i < data.data.length; i++ ) {

				$('#image_area').append("<li>" + 
											"<img src='" + getContextPath() + "/admin/program/getPreview?f=" + data.data[i].convert_name + "&ext=" + data.data[i].ext + "' width='200px' height='200px'>" +
											"<span class='users-list-name' style='font-size: 14px;'> " + data.data[i].order_no + " | 순서변경 준비중 </span>" +
										"</li>");
				
			}
		}
	});
	
}
	
</script>

</html>