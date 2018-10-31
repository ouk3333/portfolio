/**
 * 
 */

var getConfiguration = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/config/getConfig',
		type: 'POST',
		dataType: 'JSON',
		data: {
			
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생 했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#page_title').val( data.page_title );
			$('#main_color').val( data.main_color );
			$('#point_color').val( data.point_color );
			$('#title_color').val( data.title_color );
			$('#sidemenu_image').val( data.sidemenu_image );
			$('#program_layout').val( data.program_layout );
			
			$('#main_color').css("background-color", data.main_color);
			$('#point_color').css("background-color", data.point_color);
			$('#title_color').css("background-color", data.title_color);
			
		},
		error: function( data ) {
			alert("Server Error");
		}
	})
	.done(function( data ) {
		setColorpickerConfig( data );
	});
	
}

var setConfiguration = function() {
	
	var page_title = $('#page_title').val();
	var main_color = $('#main_color').val();
	var point_color = $('#point_color').val();
	var title_color = $('#title_color').val();
	var sidemenu_image = $('#sidemenu_image').val();
	var program_layout = $('#program_layout').val();
	
	$.ajax({
		url: getContextPath() + '/admin/config/setConfig',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'page_title': encodeURIComponent(page_title),
			'main_color': encodeURIComponent(main_color),
			'point_color': encodeURIComponent(point_color),
			'title_color': encodeURIComponent(title_color),
			'sidemenu_image': encodeURIComponent(sidemenu_image),
			'program_layout': encodeURIComponent(program_layout)
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생 했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", "저장 완료", 1500);
			
		},
		error: function( data ) {
			alert("Server Error");
		}
	});
}

var setColorpickerConfig = function( data ) {
	
	$("input[id$='_color']").each(function(){
		$(this).colorpicker();
		
		$(this).on('blur', function() {
			$(this).css("background-color", $(this).val());
		});
		$(this).on('focus', function() {
			$(this).css("background-color", $(this).val());
		});
		
		$(this).on('changeColor', function(e){
			var object = e.target;
			
			$(object).css("background-color", e.color.toHex());
		});
	})
}

var getConfigHist = function() {
	$('#config_hist_modal').modal('toggle');
}

$(document).ready(function() {
	getConfiguration();
});