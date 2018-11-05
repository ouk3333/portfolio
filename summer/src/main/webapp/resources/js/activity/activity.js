/**
 * 
 */

var g_icon_option;
var g_icon;

var getActivityData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/activity/getActivityData',
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
			
			$('#activity_table').empty();
			
			$('#activity_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 아이콘 </th>" +
							"<th> 연도 </th>" +
							"<th> 내용 </th>" +
							"<th> 순서 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");
			
			$('#activity_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var up_class = "fa fa-arrow-circle-o-up ";
				var down_class = "fa fa-arrow-circle-o-down ";
				
				var up_func = "";
				var down_func = "";
				
				if( i == 0 ) { // first row
					up_class += "disabled-icon";
				} else {
					up_class += "page-icon";
					up_func = "setActivityDataOrdering(this, " + (i + 1) + ")";
				}
				
				if( i == data.data.length - 1 ) { // last row
					down_class += "disabled-icon";
				} else {
					down_class += "page-icon";
					down_func = "setActivityDataOrdering(this, " + (i + 1) + ")";
				}
				
				var icon = "<td>" + getIcon( data.data[i].icon ) + "</td>";
				var year = "<td>" + data.data[i].year + "</td>";
				var contents = "<td>" + data.data[i].contents + "</td>";
				var order_no = "<td> <i class='" + up_class + "' style='font-size: 20px;' onclick='" + up_func + "' data-value='up'></i> &nbsp; <i class='" + down_class + "' style='font-size: 20px;' onclick='" + down_func + "' data-value='down'></i> </td>";
				var buttons = "<td> <input type='button' class='btn btn-warning' value='제거' onclick='removeActivityData(" + data.data[i].uid + ")'> </td>";
				
				$('#activity_table').append("<tr>" + icon + year + contents + order_no + buttons + "</tr>");
			}
			$('#activity_table').append("</tbody>");
		}
	})
	.done(function(){
		if( $.fn.DataTable.isDataTable('#activity_table') ) {
			$('#activity_table').DataTable().destroy();
		}
		
		$('#activity_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "50%", "targets": 2, "className": "dt-center" },
				{ "width": "15%", "targets": 3, "className": "dt-center" },
				{ "width": "15%", "targets": 4, "className": "dt-center" }
			]
		});
	});
	
}

var removeActivityData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/activity/removeActivityData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
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

			setAutoOrdering_activity();
		}
	});
	
}

var getIconList = function() {
	
	var option = "";
	
	$.ajax({
		url: getContextPath() + '/admin/getIconList',
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
				option += "<option value=" + data.data[i].uid + ">" + data.data[i].name + "</option>"
			}
			
		}
	})
	.done(function( data ) {
		g_icon_option = option;
		g_icon = data.data;
		
		getActivityData();
	});
}

var getIcon = function( uid ) {
	
	for( var i = 0; i < g_icon.length; i++ ) {
		if( g_icon[i].uid == uid ) {
			return g_icon[i].shape;
		}
	}
	
}

var getActivityStorageModal = function() {
	$('#activity_modal').modal({
		show: true,
		keyboard: false
	});
	
	getActivityStorageData();
}

var getActivityStorageData = function() {

	$.ajax({
		url: getContextPath() + '/admin/activity/getActivityStorageData',
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
			
			$('#activity_storage_table').empty();
			
			$('#activity_storage_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 아이콘 </th>" +
							"<th> 연도 </th>" +
							"<th> 내용 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");
			
			var new_icon = "<td> <select class='form-control' id='new_icon'>" + g_icon_option + "</select> </td>";
			var new_year = "<td> <input type='text' maxlength=4 class='form-control' id='new_year' placeholder='ex) 2018'> </td>";
			var new_contents = "<td> <input type='text' class='form-control' id='new_contents' placeholder='활동 내용 입력'> </td>";
			var new_button = "<td> <input type='button' class='btn btn-info' value='추가' onclick='insertActivityStorageData()'> </td>";
			
			$('#activity_storage_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var icon = "<td>" + getIcon( data.data[i].icon ) + "</td>";
				var year = "<td>" + data.data[i].year + "</td>";
				var contents = "<td>" + data.data[i].contents + "</td>";
				var delete_button = "<input type='button' class='btn btn-danger' value='삭제' onclick='deleteActivityStorageData(" + data.data[i].uid + ")'>";
				var modify_button = "&nbsp;<input type='button' class='btn btn-info' value='수정' onclick='modifyActivityStorageDataModal(" + data.data[i].uid + ")'>&nbsp;";
				var add_button = "<input type='button' class='btn btn-success' value='등록' onclick='addActivityStorageDataModal(" + data.data[i].uid + ")'>";
				var buttons = "<td>" + delete_button + modify_button + add_button + "</td>";
				
				$('#activity_storage_table').append("<tr>" + icon + year + contents + buttons + "</tr>");
			}
			$('#activity_storage_table').append("<tr>" + new_icon + new_year + new_contents + new_button + "</tr>");
			$('#activity_storage_table').append("</tbody>");
			
		}
	})
	.done(function(){
		if( $.fn.DataTable.isDataTable('#activity_storage_table') ) {
			$('#activity_storage_table').DataTable().destroy();
		}
		
		$('#activity_storage_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "50%", "targets": 2, "className": "dt-center" },
				{ "width": "30%", "targets": 3, "className": "dt-center" }
			]
		});
	});
	
}

var insertActivityStorageData = function() {
	
	var insert = {};
	var icon = $('#new_icon').val();
	var year = $('#new_year').val();
	var contents = $('#new_contents').val();
	
	if( year == '' || contents == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요", 1000);
		return false;
	}
	
	insert.icon = icon;
	insert.year = year;
	insert.contents = contents;
	
	$.ajax({
		url: getContextPath() + '/admin/activity/insertActivityStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'insert': JSON.stringify(insert)
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
			
			show_alert("success", "항목이 추가되었습니다.", 1000);
		}
	})
	.done(function( data ) {
		getActivityStorageData();
	});
	
}

var deleteActivityStorageData = function( uid ) {
	
	delete_alert(function() {
		
		$.ajax({
			url: getContextPath() + '/admin/activity/deleteActivityStorageData',
			type: 'POST',
			dataType: 'JSON',
			data: {
				'uid': uid
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
		})
		.done(function( data ) {
			getActivityStorageData();
		});
		
	});
	
}

var modifyActivityStorageDataModal = function( uid ) {
	
	$('#modify_activity_modal').modal({
		show: true,
		keyboard: false
	});
	
	modifyActivityStorageData(uid);
}

var modifyActivityStorageData = function( uid ) {
	
}

var addActivityStorageDataModal = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/activity/addActivityStorageDataModal',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
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
	})
	.done(function( data ) {
		getActivityData();
		getActivityStorageData();
	});
	
}

var setActivityDataOrdering = function( object, order_no ) {

	var type = $(object).attr("data-value");
	
	$.ajax({
		url: getContextPath() + '/admin/activity/setActivityDataOrdering',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'type': type,
			'order_no': order_no
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
	})
	.done(function( data ) {
		getActivityData();
	});
	
}

var setAutoOrdering_activity = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/activity/setAutoOrderingActivity',
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
	.done(function( data ) {
		getActivityData();
	});
	
}

$(document).ready(function() {
	getIconList();
	//getActivityData();
});