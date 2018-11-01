/**
 * 
 */

var getContactData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/contact/getContactData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( request, status, error ) {
			console.log( "request: " + request);
			console.log( "status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			for( var i = 0; i < data.data.length; i++ ) {
				
				$('#' + data.data[i].name).val( data.data[i].value );
				
			}
			
		}
	})
	
}

var setContactData = function() {
	
	$('input[type=text]').each(function() {
		
		var object = $(this);
		
		$.ajax({
			url: getContextPath() + '/admin/contact/setContactData',
			type: 'POST',
			dataType: 'JSON',
			data: {
				'name': object.attr("id"),
				'value': object.val()
			},
			error: function( request, status, error ) {
				console.log( "request: " + request);
				console.log( "status: " + status );
			},
			success: function( data ) {
				
				if( data.state != 'success' ) {
					show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
					console.log( data.error );
					return false;
				}
			}
		});
	});
	
	show_alert("success", "저장 완료", 1000);
}

$(document).ready(function() {
	getContactData();
});