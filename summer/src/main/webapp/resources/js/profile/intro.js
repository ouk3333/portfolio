
var g_ckeditor_init_sw = false;
var g_intro_init_data = "";
var g_typeout_init_sw = false;
var g_typeout_config = [];

// ckeditor 초기화
var init_ckeditor = function(  ) {
	
	if( g_ckeditor_init_sw == false ) {
		CKEDITOR.replace('intro_textarea');
		g_ckeditor_init_sw = true;
	}
	
}

// 미리보기
var previewIntroData = function() {
	
	var intro_data = CKEDITOR.instances['intro_textarea'].getData();
	var intro_delay = 100;
	var intro_preserve = true;
	var intro_marker = "_"; // _ | ㅣ * @ # $
	
	$('#preview_typeout').html( intro_data );
	
	if( g_typeout_init_sw == false ) {
		intro_delay = g_typeout_config["delay"];
		intro_preserve = g_typeout_config["preserve"];
		intro_marker = g_typeout_config["marker"];
	} else {
		intro_delay = $('#intro_delay').val();
		intro_preserve = $('#intro_preserve').val() == 1 ? true : false;
		intro_marker = $('#intro_marker').val();
	}
	
	$('#preview_typeout').typeOut({
		delay: intro_delay,
		preserve: intro_preserve,
		marker: intro_marker
	});
	
	return false;
}

// 되돌리기
var undoIntroData = function() {
	
	CKEDITOR.instances['intro_textarea'].setData( g_intro_init_data );
	
	$('#intro_marker').val( g_typeout_config["marker"] );
	$('#intro_delay').val( g_typeout_config["delay"] );
	$('#intro_preserve').val( g_typeout_config["preserve"] == true ? 1 : 0 );
	
	return false;
}

// 저장
var setIntroData = function() {
	
	var intro_data = CKEDITOR.instances['intro_textarea'].getData();
	var intro_marker = $('#intro_marker').val();
	var intro_preserve = $('#intro_preserve').val();
	var intro_delay = $('#intro_delay').val();
	
	if( intro_data == '' ) {
		show_alert("info", "데이터가 누락되었습니다", 1000);
		return false;
	}
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setIntroData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'intro': getEncode(intro_data),
			'marker': getEncode(intro_marker),
			'preserve': intro_preserve,
			'delay': intro_delay
		},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != "success" ) {
				show_alert("warning", "데이터를 처리하는 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", "저장 완료", 1500);
			
		}
	});
	
}

// 데이터 get
var getIntroData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getIntroData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function() {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 수신하는 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#intro_textarea').html( data.intro );
			g_intro_init_data = data.intro;
			
			g_typeout_config["delay"] = data.delay;
			g_typeout_config["marker"] = data.marker;
			g_typeout_config["preserve"] = data.preserve;
		}
	})
	.done(function( data ) {
		init_ckeditor(  );
	});
}

// typeout_blockUI 함수
var typeout_blockUI = function() {
	
	$('#typeout_blockUI').css("display", "none");
	g_typeout_init_sw = true;
	
	return;
}