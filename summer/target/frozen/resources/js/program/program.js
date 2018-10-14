/**
 * 
 */

var getProgramData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/program/getProgramData',
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
			
			$('#program_table').empty();
			
			$('#program_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 제목 </th>" +
							"<th> 언어 </th>" +
							"<th> 기술 </th>" +
							"<th> 시작일 </th>" +
							"<th> 종료일 </th>" +
							"<th> 이미지 </th>" +
							"<th> 순서 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");
			
			var new_title = "<td><input type='text' class='form-control' placeholder='프로그램 제목' id='new_title'></td>";
			var new_language = "<td><input type='text' class='form-control' placeholder='사용한 언어' id='new_language'></td>";
			var new_skill = "<td><input type='text' class='form-control' placeholder='사용한 기술' id='new_skill'></td>";
			var new_start_date = "<td><input type='date' class='form-control' id='new_start_date'></td>";
			var new_end_date = "<td><input type='date' class='form-control' id='new_end_date'></td>";
			var new_image = "<td> - </td>";
			var new_order_no = "<td> Auto </td>";
			var new_button = "<td><input type='button' value='추가' class='btn btn-info' onclick='addProgramData()'></td>";
			
			$('#program_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var up_class = "fa fa-arrow-circle-o-up ";
				var down_class = "fa fa-arrow-circle-o-down ";
				
				var up_func = "";
				var down_func = "";
				
				if( i == 0 ) { // first row
					up_class += "disabled-icon";
				} else {
					up_class += "page-icon";
					up_func = "setProgramDataOrdering(this, " + (i + 1) + ")";
				}
				
				if( i == data.data.length - 1 ) { // last row
					down_class += "disabled-icon";
				} else {
					down_class += "page-icon";
					down_func = "setProgramDataOrdering(this, " + (i + 1) + ")";
				}
				
				var title = "<td>" + data.data[i].title + "</td>";
				var language = "<td>" + data.data[i].language + "</td>";
				var skill = "<td>" + data.data[i].skill + "</td>";
				var start_date = "<td>" + data.data[i].start_date + "</td>";
				var end_date = "<td>" + data.data[i].end_date + "</td>";
				var image = "<td> <a class='btn btn-app' onclick='getProgramImage(" + data.data[i].uid + ")'> <span class='badge bg-info'> " + data.data[i].image_cnt + " </span> <i class='fa fa-picture-o'></i> </a> </td>";
				var order_no = "<td> <i class='" + up_class + "' style='font-size: 20px;' onclick='" + up_func + "' data-value='up'></i> &nbsp; <i class='" + down_class + "' style='font-size: 20px;' onclick='" + down_func + "' data-value='down'></i> </td>";
				var button = "<td><input type='button' value='제거' class='btn btn-warning' onclick='removeProgramData(" + data.data[i].uid + ")'></td>";
				
				$('#program_table').append("<tr>" + title + language + skill + start_date + end_date + image + order_no + button + "</tr>");
			}
			$('#program_table').append("<tr>" + new_title + new_language + new_skill + new_start_date + new_end_date + new_image + new_order_no + new_button + "</tr>");
			$('#program_table').append("</tbody>");
		}
	})
	.done(function(data) {
		
		if( $.fn.DataTable.isDataTable('#program_table') ) {
			$('#program_table').DataTable().destroy();
		}
		
		$('#program_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"paging": false,
			//"pageLength": 10, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "15%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "30%", "targets": 2, "className": "dt-center" },
				{ "width": "12.5%", "targets": 3, "className": "dt-center" },
				{ "width": "12.5%", "targets": 4, "className": "dt-center" },
				{ "width": "5%", "targets": 5, "className": "dt-center" },
				{ "width": "5%", "targets": 6, "className": "dt-center" },
				{ "width": "10%", "targets": 7, "className": "dt-center" }
			]
		});
		
	});
	
}

var getProgramImage = function( uid ) {
	
	window.open( getContextPath() + "/admin/program/getProgramImage?uid=" + uid, "programImage", "width=1200, height=500" );
	
	return false;
}

var addProgramData = function() {
	
	var new_data = {};
	var title = $('#new_title').val();
	var language = $('#new_language').val();
	var skill = $('#new_skill').val();
	var start_date = $('#new_start_date').val();
	var end_date = $('#new_end_date').val();
	
	if( title == '' || language == '' || skill == '' || start_date == '' || end_date == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요.", 1000);
		return false;
	}
	
	new_data.title = title;
	new_data.language = language;
	new_data.skill = skill;
	new_data.start_date = start_date;
	new_data.end_date = end_date;
	
	$.ajax({
		url: getContextPath() + '/admin/program/addProgramData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'addData': JSON.stringify(new_data)
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
			
			show_alert("info", title + " 항목이 추가되었습니다.", 1000)
			
			$('#new_title').val('');
			$('#new_language').val('');
			$('#new_skill').val('');
			$('#new_start_date').val('');
			$('#new_end_date').val('');
			
		}
	})
}

var programModalCall = function () {
	
	$('#program_modal').modal({
		show: true,
		keyboard: false
	});
	
	getProgramStorageData();
	
}

var getProgramStorageData = function () {
	
	$('#program_storage_table').empty();
	
	$('#program_storage_table')
	.append("<thead>" +
				"<tr>" +
					"<th> 제목 </th>" +
					"<th> 언어 </th>" +
					"<th> 기술 </th>" +
					"<th> 시작종료일 </th>" +
					"<th> </th>" +
				"</tr>" +
			"</thead>");
	
	$.ajax({
		url: getContextPath() + '/admin/program/getProgramStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#program_storage_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var title = "<td>" + decreaseWord(data.data[i].title, 15) + "</td>";
				var language = "<td>" + decreaseWord(data.data[i].language, 8) + "</td>";
				var skill = "<td>" + decreaseWord(data.data[i].skill, 30) + "</td>";
				var start_end_date = "<td>" + decreaseWord(data.data[i].start_date + ' ~ ' + data.data[i].end_date, 10) + "</td>";
				var delete_button = "<input type='button' value='삭제' class='btn btn-danger' onclick='deleteProgramStorageData(" + data.data[i].uid + ")'>";
				var modify_button = "&nbsp<input type='button' value='수정' class='btn btn-info' onclick='program_modify_modal(" + data.data[i].uid + ")'>&nbsp";
				var add_button = "<input type='button' value='등록' class='btn btn-success' onclick='insertProgramStoragedata(" + data.data[i].uid + ")'>";
				var buttons = "<td>" + delete_button + modify_button + add_button + "</td>";
				
				$('#program_storage_table').append("<tr>" + title + language + skill + start_end_date + buttons + "</tr>");
				
			}
			$('#program_storage_table').append("</tbody>");
		}
	})
	.done(function(data) {
		
		if( $.fn.DataTable.isDataTable('#program_storage_table') ) {
			$('#program_storage_table').DataTable().destroy();
		}
		
		$('#program_storage_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"paging": false,
			//"pageLength": 10, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "20%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "30%", "targets": 2, "className": "dt-center" },
				{ "width": "20%", "targets": 3, "className": "dt-center" },
				{ "width": "20%", "targets": 4, "className": "dt-center" }
			],
			"drawCallback": function( settings ) {
				$('[data-toggle="tooltip"]').tooltip();
			}
		});
		
	});
	
}

var deleteProgramStorageData = function( uid ) {
	
	swal({
		title: "데이터를 삭제하시겠습니까?",
		text: "삭제된 데이터는 복구되지 않습니다.",
		type: "warning",
		showCancelButton: true,
		confirmButtonClass: "btn-danger",
		confirmButtonText: "삭제",
		cancelButtonText: "취소",
		closeOnConfirm: true
	},
	function() {
		
		$.ajax({
			url: getContextPath() + '/admin/program/deleteProgramStorageData',
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
			getProgramStorageData();
		});
		
	});
	
}

var program_modify_modal = function( uid ) {
	
	$('#program_modify_modal').modal({
		show: true,
		keyboard: false
	});
	
	getModifyProgramStorageData( uid );
}

var getModifyProgramStorageData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/program/getModifyProgramStorageData',
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
			
			$('#modify_program_title').val( data.title );
			$('#modify_program_language').val( data.language );
			$('#modify_program_start_date').val( data.start_date );
			$('#modify_program_end_date').val( data.end_date );
			$('#modify_program_skill').val( data.skill );
			
			$('#modify_program_button').attr("onclick", "modifyProgramStorageData(" + data.uid + ")");
			
		}
	});
	
}

var modifyProgramStorageData = function( uid ) {
	
	var modify = {};
	var title = $('#modify_program_title').val();
	var language = $('#modify_program_language').val();
	var start_date = $('#modify_program_start_date').val();
	var end_date = $('#modify_program_end_date').val();
	var skill = $('#modify_program_skill').val();
	
	modify.title = title;
	modify.language = language;
	modify.start_date = start_date;
	modify.end_date = end_date;
	modify.skill = skill;
	
	$.ajax({
		url: getContextPath() + '/admin/program/modifyProgramStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid,
			'modify': JSON.stringify(modify)
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
	.done(function(data) {
		getProgramStorageData();
	});
	
}

var insertProgramStoragedata = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/program/insertProgramStoragedata',
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
		getProgramData();
		getProgramStorageData();
	});
	
}

var removeProgramData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/program/removeProgramData',
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
			
			setAutoOrderingProgram();
		}
	});
	
}

var setAutoOrderingProgram = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/program/setAutoOrderingProgram',
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
			
		}
	})
	.done(function( data ) {
		getProgramData();
	});
	
}

var setProgramDataOrdering = function( object, order_no ) {
	
	var type = $(object).attr("data-value");
	
	$.ajax({
		url: getContextPath() + '/admin/program/setProgramDataOrdering',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'type': type,
			'order_no': order_no
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
			
			getProgramData();
			
		}
	});
	
}

$(document).ready(function(){
	getProgramData();
});