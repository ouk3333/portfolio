/**
 * 
 */

var profile_dropdown = function() {
	
	var panel = $('#user_panel');

	if( panel.hasClass("user-menu-on") ) {
		panel.addClass("user-menu-off");
		panel.removeClass("user-menu-on");
	} else {
		panel.addClass("user-menu-on");
		panel.removeClass("user-menu-off");
	}
	
	return false;
}

var admin_logout = function() {
	
	location.replace(getContextPath() + '/admin/adminLogout');
	
	return false;
}

var dashboard_click = function( event ) {
	
	if( event == 'visitor' ) {
		
		location.href = getContextPath() + '/admin/visitHistory';
		//location.replace( getContextPath() + '/admin/visitHistory' );
		
	} else if( event == 'powerGeneration' ) {
		
		location.href = getContextPath() + '/admin/powerGeneration';
		//location.replace( getContextPath() + '/admin/powerGeneration' );
		
	}
	
}

var open_sha_modal = function() {
	$('#sha_modal').modal({
		show: true,
		keyboard: false
	});
	
	$('#convert_str').val("");
	$('#convert_result').val("");
}

var convertToSHA = function() {
	var str = $('#convert_str').val();
	
	if( str == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요", 1000);
		return false;
	}
	
	var result = SHA256(str);
	
	$('#convert_result').val( result );
}

$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});

$(document).ajaxError(function( event, request, setting ) {
	if( request.status == 200 ) {
		location.href = getContextPath() + '/admin/login';
	} else if( request.status == 0 ) {
		location.reload();
	}	
});