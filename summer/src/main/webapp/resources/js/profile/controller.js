
// tab controller
var tab_click_func = function( object ) {
	
	var tab_id = object != null ? $(object).attr("data-value") : null;
	
	if( tab_id != null ) {
		
		$("a[data-toggle='tab']").each(function() {
			$(this).removeClass("active");
		});
		
		$('#' + tab_id).addClass("active");
		
	}
	
	if( tab_id == 'timeline_tab' ) {
		
		getTimelineData();
		
	} else if( tab_id == 'license_tab' ) {
		
		getLicenseData();
		
	} else if( tab_id == 'intro_tab' ) {
		
		getIntroData();
		
	} else if( tab_id == null ) {
		
		getTimelineData();
		
	}

}

$(document).ready(function() {
	tab_click_func( null );
});