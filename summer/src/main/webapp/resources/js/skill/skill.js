/**
 * 
 */

var skillToggleArea = function( object ) {
	
	var type = $(object).attr("data-type");
	
	if( type == 'ability' ) {
		$('#ability_area').slideToggle();
	} else if( type == 'color' ) {
		$('#color_area').slideToggle();
	}
	
	$(object).blur();
	return false;
}

var skillButtonBlur = function( object ) {
	$(object).blur();
	return false;
}

var getSkillAbilityData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/skill/getSkillAbilityData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#ability_area').empty();
			
			var area = "<div class='row'>";
			
			if( isEmpty(data.data) ) {
				area += "<div class='col-12 dt-center'>" +
						"데이터가 존재하지 않습니다." +
						"</div>";
			}
			
			for( var i = 0; i < data.data.length; i++ ) {
				area += "<div class='col-3 dt-center' style='padding: 5px;'>" +
				"<input type='text' value='" + data.data[i].ability + "' class='dial skill-ability' data-width='120' data-height='120' data-uid='" + data.data[i].uid + "' data-fgColor='" + data.data[i].background_color + "'>" +
				"<div class='knob-label'><span class='tooltips page-icon' data-toggle='tooltip' data-placement='bottom' title='제거' onclick='removeSkillAbilityData(" + data.data[i].uid + ")'>" + data.data[i].name + "</span></div>" +
				"</div>";
			}
			
			area += "</div>";
			$('#ability_area').append(area);
			
			getSkillColorData( data );
		}
	})
	.done(function( data ) {
		$(".dial").knob();
		$(".tooltips").tooltip();
	});
	
}

var removeSkillAbilityData = function( uid ) {

	$.ajax({
		url: getContextPath() + '/admin/skill/removeSkillAbilityData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
		}
	})
	.done(function( data ) {
		$(".tooltip").remove();
		getSkillAbilityData();
	});
	
}

var setSkillAbilityData = function() {
	
	var uid_array = new Array();
	var value_array = new Array();
	
	$('.skill-ability').each(function() {
		uid_array.push($(this).attr("data-uid"));
		value_array.push($(this).val());
	});
	
	jQuery.ajaxSettings.traditional = true; // 데이터 배열 넘기기
	
	$.ajax({
		url: getContextPath() + '/admin/skill/setSkillAbilityData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid_array,
			'value': value_array
		},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", "저장 완료", 1000);
		}
	})
	.done(function( data ) {
		getSkillAbilityData();
	});
}

var skillAbilityStorageModal = function() {
	$('#skillAbility_modal').modal({
		show: true,
		keyboard: false
	});
	
	getSkillAbilityStorageData();
}

var getSkillAbilityStorageData = function() {

	$.ajax({
		url: getContextPath() + '/admin/skill/getSkillAbilityStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			var new_name = "<td> <input type='text' class='form-control' placeholder='기술명 입력' id='new_name'> </td>";
			var new_background_color = "<td> <input type='text' class='form-control' placeholder='ex) #000000' id='new_background_color' maxlength=7> </td>";
			var new_font_color = "<td> <input type='text' class='form-control' placeholder='ex) #000000' id='new_font_color' maxlength=7> </td>";
			var new_button = "<td> <input type='button' class='btn btn-info' value='추가' onclick='insertSkillStorageData()'> </td>";
			
			$('#skill_storage_table').empty();
			
			$('#skill_storage_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 기술명 </th>" +
							"<th> 배경색상 </th>" +
							"<th> 글자색상 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");

			$('#skill_storage_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var name = "<td>" + data.data[i].name + "</td>";
				var background_color = "<td>" + data.data[i].background_color + "</td>";
				var font_color = "<td>" + data.data[i].font_color + "</td>";
				var delete_button = "<input type='button' class='btn btn-danger' value='삭제' onclick='deleteSkillStorageData(" + data.data[i].uid + ")'>";
				var modify_button = "&nbsp;<input type='button' class='btn btn-info' value='수정' onclick='modifySkillStorageModal(" + data.data[i].uid + ")'>&nbsp;";
				var add_button = "<input type='button' class='btn btn-success' value='등록' onclick='addSkillStorageData(" + data.data[i].uid + ")'>";
				var buttons = "<td>" + delete_button + modify_button + add_button + "</td>";
				
				$('#skill_storage_table').append("<tr>" + name + background_color + font_color + buttons + "</tr>");
			}
			$('#skill_storage_table').append("<tr>" + new_name + new_background_color + new_font_color + new_button + "</tr>");
			$('#skill_storage_table').append("</tbody>");
			
		}
	})
	.done(function( data ) {
		if( $.fn.DataTable.isDataTable('#skill_storage_table') ) {
			$('#skill_storage_table').DataTable().destroy();
		}
		
		$('#skill_storage_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "25%", "targets": 0, "className": "dt-center" },
				{ "width": "25%", "targets": 1, "className": "dt-center" },
				{ "width": "25%", "targets": 2, "className": "dt-center" },
				{ "width": "25%", "targets": 3, "className": "dt-center" }
			]
		});
	});
	
}

var insertSkillStorageData = function() {
	
	var data = {};
	var name = $('#new_name').val();
	var background_color = $('#new_background_color').val();
	var font_color = $('#new_font_color').val();
	
	data.name = name;
	data.background_color = background_color;
	data.font_color = font_color;
	
	if( name == '' || background_color == '' || font_color == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요", 1000);
		return false;
	}
	
	$.ajax({
		url: getContextPath() + '/admin/skill/insertSkillStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'data': JSON.stringify(data)
		},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", name + " 항목이 추가되었습니다.", 1000);
			
		}
	})
	.done(function( data ) {
		getSkillAbilityStorageData();
	});
	
}

var deleteSkillStorageData = function( uid ) {

	delete_alert(function() {
		$.ajax({
			url: getContextPath() + '/admin/skill/deleteSkillStorageData',
			type: 'POST',
			dataType: 'JSON',
			data: {
				'uid': uid
			},
			error: function( error ) {
				alert("Server Error");
			},
			success: function( data ) {
				
				if( data.state != 'success' ) {
					show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
					console.log( data.error );
					return false;
				}
				
			}
		})
		.done(function( data ) {
			getSkillAbilityStorageData();
		});
	});
	
}

var modifySkillStorageModal = function( uid ) {
	
	$('#skillAbilityModify_modal').modal({
		show: true,
		keyboard: false
	});
	
	getModifySkillStorageData( uid );
}

var getModifySkillStorageData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/skill/getModifySkillStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#modify_skill_name').val( data.name );
			$('#modify_skill_background_color').val( data.background_color );
			$('#modify_skill_font_color').val( data.font_color );
		}
	});
	
	$('#skill_save_button').attr("onclick", "setModifySkillStorageData(" + uid + ")");
	
}

var setModifySkillStorageData = function( uid ) {
	
	var modify = {};
	var name = $('#modify_skill_name').val();
	var background_color = $('#modify_skill_background_color').val();
	var font_color = $('#modify_skill_font_color').val();
	
	if( name == '' || background_color == '' || font_color == '' ) {
		show_alert("info", "누락된 항목을 확인해주세요", 1500);
		return false;
	}
	
	modify.name = name;
	modify.background_color = background_color;
	modify.font_color = font_color;
	
	$.ajax({
		url: getContextPath() + '/admin/skill/setModifySkillStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid,
			'modify': JSON.stringify(modify)
		},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.state );
				return false;
			}
			
		}
	})
	.done(function( data ) {
		getSkillAbilityStorageData();
	});
	
}

var addSkillStorageData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/skill/addSkillStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status : " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
		}
	})
	.done(function( data ) {
		getSkillAbilityData();
		getSkillAbilityStorageData();
	});
	
}

var getSkillColorData = function( data ) {

	$('#color_area').empty();
	
	var area = "<div class='row'>";
	var R = 0;
	var G = 53;
	var B = 94;
	
	var R_pm = 0;
	var G_pm = 6;
	var B_pm = 11;
	
	for( var i = 0; i < data.data.length; i++ ) {
		var back_r = getConvertHEXtoRGB(data.data[i].background_color).r;
		var back_g = getConvertHEXtoRGB(data.data[i].background_color).g;
		var back_b = getConvertHEXtoRGB(data.data[i].background_color).b;
		
		var font_r = getConvertHEXtoRGB(data.data[i].font_color).r
		var font_g = getConvertHEXtoRGB(data.data[i].font_color).g;
		var font_b = getConvertHEXtoRGB(data.data[i].font_color).b;
		
		area += "<div class='col-7 dt-center' style='padding: 5px;'>" +
					"<input type='text' class='form-control' data-value='" + data.data[i].uid + "' data-type='color' maxlength='7' value='" + data.data[i].background_color + "' style='background-color: rgb(" + back_r + ", " + back_g + ", " + back_b + "); color: rgb(" + font_r + ", " + font_g + ", " + font_b + ")'>" +
				"</div>" + 
				"<div class='callout callout-danger' style='margin-top: 6px; border-left-color: rgb(" + back_r + ", " + back_g + ", " + back_b + "); width: 120px;'>" +
					"<h5> " + data.data[i].name + " </h5>" +
				"</div>";
		
		if( G <= (255 - G_pm) ) {
			G += G_pm;
		}
		if( B <= (255 - B_pm) ) {
			B += B_pm;
		}
	}
	
	area += "</div>";
	$('#color_area').append(area);

}

var setSkillColorData = function() {
	
	$('input[type=text][data-type=color]').each(function() {
		var uid = $(this).attr("data-value");
		var background_color = $(this).val();
		
		$.ajax({
			url: getContextPath() + '/admin/skill/setSkillColorData',
			type: 'POST',
			dataType: 'JSON',
			data: {
				'uid': uid,
				'background_color': background_color
			},
			error: function( request, status, error ) {
				alert("Server Error");
				console.log( "request: " + request + " || status : " + status );
			},
			success: function( data ) {
				
				if( data.state != 'success' ) {
					show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
					console.log( data.error );
					return false;
				}
				
				show_alert("success", "저장 완료", 1000);
				getSkillAbilityData();
			}
		});
	});
	
}

$(document).ready(function() {
	getSkillAbilityData();
});