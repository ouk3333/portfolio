var convertToSHA = function() {
	var str = $('#convert_str').val();
	
	if( str == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요", 1000);
		return false;
	}
	
	var result = SHA256(str);
	
	$('#convert_result').val( result );
}