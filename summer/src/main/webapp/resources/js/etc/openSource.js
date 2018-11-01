/**
 * 
 */

var getOpenSourceData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/openSource/getOpenSourceData',
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
			
			$('#openSource_table').empty();
			
			$('#openSource_table').append(	"<thead>" +
												"<tr>" +
													"<td> 명칭 </td>" +
													"<td> 종류 </td>" +
													"<td> url </td>" +
													"<td>  </td>" +
												"</tr>" +
											"</thead>");
			
			$('#openSource_table').append("<tbody>");
			for( var i = 0; i < data.opensource.length; i++ ) {
				
				var name = "<td>" + decreaseWord(data.opensource[i].name, 35) + "</td>";
				var type = "<td>" + decreaseWord(data.opensource[i].type, 15) + "</td>";
				var url = "<td>" + decreaseWord(data.opensource[i].url, 65) + "</td>";
				var delete_button = "<input type='button' value='삭제' class='btn btn-danger' onclick='deleteOpenSourceData(" + data.opensource[i].uid + ")'>";
				var modify_button = "<input type='button' value='수정' class='btn btn-info' onclick='modifyOpenSourceDataModal(" + data.opensource[i].uid + ")'>";
				var buttons = "<td>" + delete_button + "&nbsp;" + modify_button + "</td>";
				
				$('#openSource_table').append("<tr>" + name + type + url + buttons + "</tr>");
			}
			$('#openSource_table').append("</tbody>");
		}
	})
	.done(function( data ) {
		if( $.fn.DataTable.isDataTable('#openSource_table') ) {
			$('#openSource_table').DataTable().destroy();
		}
		
		$('#openSource_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 10, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "20%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "40%", "targets": 2, "className": "dt-center" },
				{ "width": "30%", "targets": 3, "className": "dt-center" }
			],
			"drawCallback": function( settings ) {
				$('[data-toggle="tooltip"]').tooltip();
			}
		});
	});
	
}

var addOpenSourceDataModal = function() {
	$('#add_openSource_modal').modal({
		show: true,
		keyboard: false
	});
}

var deleteOpenSourceData = function( uid ) {

	delete_alert(function(){
		
		$.ajax({
			url: getContextPath() + '/admin/openSource/deleteOpenSourceData',
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
				
				getOpenSourceData();
			}
		});
		
	});
	
}

var modifyOpenSourceDataModal = function( uid ) {
	$('#modify_openSource_modal').modal({
		show: true,
		keyboard: false
	});
	
	getModifyOpenSourceData( uid );
}

var getModifyOpenSourceData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/openSource/getModifyOpenSourceData',
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
			$('#modify_type').val( data.type );
			$('#modify_url').val( data.url );
			$('#modify_button').attr("onclick", "modifyOpenSourceData(" + data.uid + ")");
		}
	});
	
}

var modifyOpenSourceData = function( uid ) {
	
	var modify_data = {};
	var name = $('#modify_name').val();
	var type = $('#modify_type').val();
	var url = $('#modify_url').val();
	
	modify_data.name = name;
	modify_data.type = type;
	modify_data.url = url;
	
	$.ajax({
		url: getContextPath() + '/admin/openSource/modifyOpenSourceData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'modify_data': JSON.stringify(modify_data),
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
			
			show_alert("success", "수정완료", "1000");
			getOpenSourceData();
			
			$('#modify_name').val("");
			$('#modify_type').val("");
			$('#modify_url').val("");
		}
	});
	
}

var addOpenSourceData = function() {
	
	var add_data = {};
	var name = $('#new_name').val();
	var type = $('#new_type').val();
	var url = $('#new_url').val();
	
	if( name == '' || type == '' || url == '' ) {
		show_alert("info", "누락된 항목을 확인해주세요.", 1000);
		return false;
	}
	
	add_data.name = name;
	add_data.type = type;
	add_data.url = url;
	
	$.ajax({
		url: getContextPath() + '/admin/openSource/addOpenSourceData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'add_data': JSON.stringify(add_data)
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
			
			show_alert("success", name + " 항목이 추가되었습니다.", "1000");
			getOpenSourceData();
			
			$('#new_name').val("");
			$('#new_type').val("");
			$('#new_url').val("");
		}
	});
	
}

$(document).ready(function() {
	getOpenSourceData();
});