
// 등록된 타임라인 데이터 가져오기
var getTimelineData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getTimelineData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert( "Server Error" );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#timeline_table').empty();
			
			$('#timeline_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 주요 행사 </th>" +
							"<th> 날짜 </th>" +
							"<th> 세부사항 </th>" +
							"<th> 순서 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");
			
			var new_event = "<td> <input type='text' placeholder='주요 행사 입력' class='form-control' id='timeline_event'> </td>";
			var new_date = "<td> <input type='date' class='form-control' id='timeline_date'> </td>";
			var new_remarks = "<td> <input type='text' placeholder='세부 사항 입력' class='form-control' id='timeline_remarks'> </td>";
			var new_order_no = "<td> Auto </td>";
			var new_buttons = "<td> <input type='button' value='추가' class='btn btn-info' onclick='addTimelineData()'> </td>";
			
			$('#timeline_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				var up_class = "fa fa-arrow-circle-o-up ";
				var down_class = "fa fa-arrow-circle-o-down ";
				
				var up_func = "";
				var down_func = "";
				
				if( i == 0 ) { // first row
					up_class += "disabled-icon";
				} else {
					up_class += "page-icon";
					up_func = "setTimelineDataOrdering(this, " + (i + 1) + ")";
				}
				
				if( i == data.data.length - 1 ) { // last row
					down_class += "disabled-icon";
				} else {
					down_class += "page-icon";
					down_func = "setTimelineDataOrdering(this, " + (i + 1) + ")";
				}
				
				var event = "<td> " + data.data[i].event + " </td>";
				var date = "<td> " + data.data[i].date + " </td>";
				var remarks = "<td> " + data.data[i].remarks + " </td>";
				var order_no = "<td> <i class='" + up_class + "' style='font-size: 20px' onclick='" + up_func + "' data-value='up'></i> &nbsp; <i class='" + down_class + "' style='font-size: 20px;' onclick='" + down_func + "' data-value='down'></i> </td>";
				var buttons = "<td> <input type='button' class='btn btn-warning' value='제거' onclick='remove_timeline_data(" + data.data[i].uid + ")'> </td>";
				
				$('#timeline_table').append("<tr>" + event + date + remarks + order_no + buttons + "</tr>");
			}
			$('#timeline_table').append("<tr>" + new_event + new_date + new_remarks + new_order_no + new_buttons + "</tr>");
			$('#timeline_table').append("</tbody>");
						
		}
	})
	.done(function( data ) {
		// datatable init
		if( $.fn.DataTable.isDataTable('#timeline_table') ) {
			$('#timeline_table').DataTable().destroy();
		}
		
		$('#timeline_table').DataTable({
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
				{ "width": "10%", "targets": 1 },
				{ "width": "40%", "targets": 2 },
				{ "width": "10%", "targets": 3 },
				{ "width": "20%", "targets": 4 }
			]
		});
	});
	
}

// 새로운 타임라인 데이터 추가
var addTimelineData = function() {
	
	var event = $('#timeline_event').val(); // text
	var date = $('#timeline_date').val(); // 0000-00-00
	var remarks = $('#timeline_remarks').val(); // text
	
	if( event == '' || date == '' || remarks == '' ) {
		
		show_alert("info", "데이터 누락 항목을 확인해주세요.", 1000);
		return false;
	}
	
	var year = date.split('-')[0];
	var month = date.split('-')[1];
	var day = date.split('-')[2];
	var sw = "n";
	
	$.ajax({
		url: getContextPath() + '/admin/profile/addTimelineData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'event': getEncode(event),
			'year': getEncode(year),
			'month': getEncode(month),
			'day': getEncode(day),
			'remarks': getEncode(remarks),
			'sw': getEncode(sw)
		},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 추가하는 도중에 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", event + " 행사가 등록 되었습니다", 1500);
			
			// object data clear
			$('#timeline_event').val('');
			$('#timeline_date').val('');
			$('#timeline_remarks').val('');
		}
	})
}

// modal 안 테이블 데이터 가져오기
var getTimelineStorageData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getTimelineStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 가져오는데 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#timeline_storage_table').empty();
			
			$('#timeline_storage_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 주요 행사 </th>" +
							"<th> 날짜 </th>" +
							"<th> 세부사항 </th>" +
							"<th>  </th>" +
						"</tr>" +
					"</thead>");

			$('#timeline_storage_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var event = "<td>" + data.data[i].event + "</td>";
				var date = "<td>" + data.data[i].date + "</td>";
				var remarks = "<td>" + data.data[i].remarks + "</td>";
				var delete_button = "<input type='button' value='삭제' class='btn btn-danger' data-type='delete' id='delete_event_" + data.data[i].uid + "' onclick='func_timeline_data(getUID(this.id), this)'>";
				var modify_button = "<input type='button' value='수정' class='btn btn-info' data-type='modify' id='modify_event_" + data.data[i].uid + "' onclick='func_timeline_data(getUID(this.id), this)'>";
				var add_button = "<input type='button' value='등록' class='btn btn-success' data-type='add' id='add_event_" + data.data[i].uid + "' onclick='func_timeline_data(getUID(this.id), this)'>";
				var buttons = "<td>" + delete_button + "&nbsp;" + modify_button + "&nbsp;" + add_button + "</td>";
				
				$('#timeline_storage_table').append("<tr>" + event + date + remarks + buttons + "</tr>");				
			}
			$('#timeline_storage_table').append("</tbody>");
		}
	})
	.done(function(){
		
		if( $.fn.DataTable.isDataTable('#timeline_storage_table') ) {
			$('#timeline_storage_table').DataTable().destroy();
		}
		
		$('#timeline_storage_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "20%", "targets": 0, "className": "dt-center" },
				{ "width": "15%", "targets": 1, "className": "dt-center" },
				{ "width": "35%", "targets": 2, "className": "dt-center" },
				{ "width": "30%", "targets": 3, "className": "dt-center" }
			]
		});
	});
	
}

// 수정 모달 안 데이터 가져오기
var getModifyTimelineData = function( uid, type_url ) {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getModifyTimelineData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( error ) {
			alert( "Server Error" );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 수신 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#modify_event').val( data.event );
			$('#modify_date').val( data.date );
			$('#modify_remarks').val( data.remarks );
			$('#modify_save').attr("onclick", "setModifyTimelineData(" + data.uid + ", '" + type_url + "')");
			
		}
	});
}

// 수정 모달 안 저장 버튼
var setModifyTimelineData = function( uid, type_url ) {
	
	var modify_data = {};
	var event = $('#modify_event').val();
	var date = $('#modify_date').val();
	var remarks = $('#modify_remarks').val();
	
	if( event == '' || date == '' || remarks == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요.", 1000);
		return false;
	}
	
	var year = date.split('-')[0];
	var month = date.split('-')[1];
	var day = date.split('-')[2];
	
	modify_data.event = event;
	modify_data.year = year;
	modify_data.month = month;
	modify_data.day = day;
	modify_data.remarks = remarks;
	
	exec_timeline_data( uid, type_url, JSON.stringify(modify_data) );
}

// timeline storage modal 
function timeline_modal() {
	
	$('#timeline_modal').modal({
		show: true,
		keyboard: false
	});
	
	getTimelineStorageData();
	
	return false;
}

// timeline modify modal
function timeline_modify_modal( uid, type_url ) {
	
	$('#timeline_modify_modal').modal({
		show: true,
		backdrop: 'static',
		keyboard: false
	});
	
	getModifyTimelineData( uid, type_url );
}

// uid 추출 function
var getUID = function( id ) {
	
	var value = id;
	
	value = value.replace("add_event_", "");
	value = value.replace("modify_event_", "");
	value = value.replace("delete_event_", "");
	
	return value;
}

// 타임라인 데이터 조작 컨트롤러
var func_timeline_data = function( uid, object ) {
	
	var type_url = getContextPath() + "/admin/profile/";
	var type = $(object).attr("data-type");
	
	if( type == 'add' ) {
		
		type_url += "addTimelineStorageData";
		exec_timeline_data( uid, type_url );
		
	} else if( type == 'modify' ) {
		
		type_url += "modifyTimelineStorageData";
		timeline_modify_modal( uid, type_url );
		
	} else if( type == 'delete' ) {
		
		type_url += "deleteTimelineStorageData";
		
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
			exec_timeline_data( uid, type_url );
		});
	}
	
}

// 타임라인 데이터 조작 함수
var exec_timeline_data = function( uid, type_url, modify_data ) {

	$.ajax({
		url: type_url,
		type: 'POST',
		dataType: 'JSON',
		data: { 'uid': uid, 'modify': modify_data },
		error: function( error ) {
			alert( "Server Error" );
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
		getTimelineStorageData();
		getTimelineData();
	});
	
}

// 타임라인에 등록된 데이터 제거 버튼 클릭 함수
var remove_timeline_data = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/removeTimelineData',
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
				show_alert("warning", "데이터 처리중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
		}
	})
	.done(function( data ) {
		setAutoOrdering_timeline();
	});
	
}

// auto reordering
var setAutoOrdering_timeline = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setAutoOrderingTimeline',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( error ) {
			alert( "Server Error" );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터 처리중 문제가 발생했습니다.");
				console.log( data.error );
				return false;
			}
			
		}
	})
	.done(function(data) {
		getTimelineData();
	});
	
}

// ordering function
var setTimelineDataOrdering = function( object, order_no ) {
	
	var type = $(object).attr("data-value");
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setTimelineDataOrdering',
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
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.");
				console.log( data.error );
				return false;
			}
			
			getTimelineData();
		}
	});
	
}