<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<link href="<%= request.getContextPath() %>/resources/css/adminlte.min.css" rel="stylesheet">
<%-- <%@ include file="/WEB-INF/views/inc/adminHeader.jsp" %> --%>

<html>
<head>
	<title>Admin login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"><!-- mobile -->
</head>
<style>
	.input-icon { margin-top: 5px; }
</style>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<b>DU - Portfolio</b>
		</div>

		<div class="card" style="font-size: 14px;">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Admin Login</p>
				
				<div class="form-group has-feedback">
					<input id="id_input" type="text" class="form-control" placeholder="아이디" style="font-size: 14px;" required="required">
					<span class="fa fa-id-card form-control-feedback input-icon"></span>
				</div>
				
				<div class="form-group has-feedback">
					<input id="password_input" type="password" class="form-control" placeholder="비밀번호" style="font-size: 14px;" required="required">
					<span class="fa fa-lock form-control-feedback input-icon"></span>
				</div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<button id="login_button" type="button" class="btn btn-block btn-outline-primary btn-lg">로그인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script>
var admin_check = function( obj ) {
	// ajax 준비
	var id = $(obj).val();

	$.ajax({
		url: getContextPath() + '/admin/adminIdCheck',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'userID' : encodeURIComponent(id)
		},
		cache: false,
		success: function( data ) {
			if( data.state != 'success' ) {
				alert("error");
			}
			
			if( data.exist == 'y' ) { // 맞을때
				$('#admin_check').val("true");
				$(obj).css("background-color", "#00a65a").css("opacity", "0.5");
			} else { // 틀릴때
				$('#admin_check').val("false");
				$(obj).css("background-color", "#f39c12").css("opacity", "0.5");
			}
		},
		error: function( error ) {
			alert( "Server Error" );
			console.log(error);
		}
	});
}

var login_proc = function( id, password ) {
	
	$.ajax({
		url: getContextPath() + '/admin/adminLogin',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'userID': encodeURIComponent(id),
			'password': password
		},
		cache: false,
		success: function( data ) {
			if( data.state != 'success' ) {
				show_alert("warning", "작업 처리중 문제가 발생했습니다.", 1500);
			}
			
			if( data.admin == 'not exist' ) {
				show_alert("info", "존재하지 않는 관리자 입니다.", 1500);
			} else if( data.admin == 'password' ) {
				show_alert("info", "비밀번호가 일치하지 않습니다.", 1500);
			} else {
				goAdminPage();
			}
			
		},
		error: function( error ) {
			alert("Server Error");
		}
	})
	
	return false;
}

var goAdminPage = function() {
	location.href = getContextPath() + "/admin/adminMain";
}

$(document).ready(function() {
	$('#id_input').on('blur', function() {
		admin_check( this );
	});
	
	$('#login_button').click(function(){
		var id = $('#id_input').val();
		var pass = $('#password_input').val();
		
		if( id == "" ) {
			show_alert("warning", "아이디를 입력하세요", 1500);
			$('#id_input').focus();
			return false;
		}
		
		if( pass == "" ) {
			show_alert("warning", "비밀번호를 입력하세요", 1500);
			$('#password_input').focus();
			return false;
		}
		
		login_proc( id, pass );
	});
});
</script>