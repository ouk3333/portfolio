
var getVolunteerData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/volunteer/getVolunteerData',
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
			
			$('#volunteer_table').empty();
			
			$('#volunteer_table').append(	"<thead>" +
												"<tr>" +
													"<th> # </th>" +
													"<th> 기업 명칭 </th>" +
													"<th> 기업 위치 </th>" +
													"<th> 지원 날짜 </th>" +
													"<th>  </th>" +
												"</tr>" +
											"</thead>");
			
			$('#volunteer_table').append("<tbody>");
			for( var i = 0; i < data.volunteer.length; i++ ) {
				var uid = "<td>" + data.volunteer[i].uid + "</td>";
				var name = "<td>" + data.volunteer[i].name + "</td>";
				var location = "<td>" + data.volunteer[i].location + "</td>";
				var reg_date = "<td>" + data.volunteer[i].reg_date + "</td>";
				var delete_button = "<input type='button' class='btn btn-danger' value='삭제' onclick='deleteVolunteerData(" + data.volunteer[i].uid + ")'>";
				var modify_button = "<input type='button' class='btn btn-info' value='수정' onclick='modifyVolunteerModal(" + data.volunteer[i].uid + ")'>";
				var buttons = "<td>" + delete_button + "&nbsp;" + modify_button + "</td>";
				
				$('#volunteer_table').append("<tr>" + uid + name + location + reg_date + buttons + "</tr>");
			}
			$('#volunteer_table').append("</tbody>");
			
		}
	})
	.done(function( data ) {
		if( $.fn.DataTable.isDataTable('#volunteer_table') ) {
			$('#volunteer_table').DataTable().destroy();
		}
		
		$('#volunteer_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": true, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 10, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "20%", "targets": 1, "className": "dt-center" },
				{ "width": "35%", "targets": 2, "className": "dt-center" },
				{ "width": "20%", "targets": 3, "className": "dt-center" },
				{ "width": "15%", "targets": 4, "className": "dt-center" }
			],
			"drawCallback": function( setting ) {
				$('input[type=search][aria-controls=volunteer_table]').css('font-size', '14px');
			}
		});
	});
	
}

var insertVolunteerModal = function() {
	$('#volunteer_modal').modal({
		show: true,
		keyboard: false
	});
}

var insertVolunteerData = function() {
	
	var insert_data = {};
	var name = $('#new_name').val();
	var location = $('#new_location').val();
	var reg_date = $('#new_date').val();
	
	if( name == '' || location == '' || reg_date == '' ) {
		show_alert("info", "누락된 항목을 확인해주세요.", 1000);
		return false;	
	}
	
	insert_data.name = name;
	insert_data.location = location;
	insert_data.reg_date = reg_date;
	
	$.ajax({
		url: getContextPath() + '/admin/volunteer/insertVolunteerData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'insert_data': JSON.stringify(insert_data)
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
			
			show_alert("success", name + " 항목이 추가되었습니다", 1000);
			getVolunteerData();
		}
	});
}

var deleteVolunteerData = function( uid ) {
	
	if( uid == null ) {
		show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
		console.log( "uid is null" );
		return false;
	}
	
	delete_alert(function() {
		$.ajax({
			url: getContextPath() + '/admin/volunteer/deleteVolunteerData',
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
				
				getVolunteerData();
			}
		});
	});
}

var modifyVolunteerModal = function( uid ) {
	$('#modify_volunteer_modal').modal({
		show: true,
		keyboard: false
	});
	
	getModifyVolunteerData( uid );
}

var getModifyVolunteerData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/volunteer/getModifyVolunteerData',
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
			
			$('#modify_name').val( data.name );
			$('#modify_location').val( data.location );
			$('#modify_date').val( data.reg_date );
			
			$('#modify_volunteer_button').attr("onclick", "updateVolunteerData(" + data.uid + ")");
		}
	});
	
}

var updateVolunteerData = function( uid ) {
	
	var modify_data = {};
	var name = $('#modify_name').val();
	var location = $('#modify_location').val();
	var reg_date = $('#modify_date').val();
	
	modify_data.name = name;
	modify_data.location = location;
	modify_data.reg_date = reg_date;
	
	$.ajax({
		url: getContextPath() + '/admin/volunteer/updateVolunteerData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid,
			'modify_data': JSON.stringify(modify_data)
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
			
			getVolunteerData();
		}
	});
	
}

$(document).ready(function() {
	getVolunteerData();
});