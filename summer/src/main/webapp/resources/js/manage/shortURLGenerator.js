
var getShortURL = function() {
	
	var value = $('#shortURL_value').val();
	
	if( value == '' ) {
		show_alert("info", "누락된 항목을 확인해주세요.", 1000);
		return false;
	}
	
	$.ajax({
		url: getContextPath() + '/admin/utilCollection/getShortURL',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'value': value
		},
		error: function( request, status, error ) {
			console.log( "request: " + request);
			console.log( "status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생 했습니다.", 1500);
				console.log( data.error );
				return false;
			}

			$('#shortURL_result').val( data.result.result.url );
		}
	});
	
}