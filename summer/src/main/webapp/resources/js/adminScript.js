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