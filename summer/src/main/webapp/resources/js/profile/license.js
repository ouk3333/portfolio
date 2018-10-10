
// 목록 보기
function license_modal() {
	
	$('#license_modal').modal({
		show: true,
		keyboard: false
	});
	
	getLicenseStorageData();
	
	return false;
}

// 모달 안 데이터 get
var getLicenseStorageData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getLicenseStorageData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function(error) {
			alert("Server Error");
		},
		success: function(data) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 가져오는데 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#license_storage_table').empty();
			
			$('#license_storage_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 종류 </th>" +
							"<th> 자격증 명 </th>" +
							"<th> 부가사항 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");

			$('#license_storage_table').append("<tbody>");
			console.log( data );
			for( var i = 0; i < data.data.length; i++ ) {
				var type = "";
				
				if( data.data[i].type == 1 ) {
					type = "<td> 자격증 </td>";
				} else if( data.data[i].type == 2 ) {
					type = "<td> 면허증 </td>";
				} else {
					type = "<td> 기타 </td>";
				}
				
				var name = "<td>" + data.data[i].name + "</td>";
				var remarks = "<td>" + data.data[i].remarks.replace(/&/i, " ") + "</td>";
				var delete_button = "<input type='button' class='btn btn-danger' value='제거' data-type='delete' data-value='" + data.data[i].uid + "' onclick='func_license_data(this)'>";
				var modify_button = "<input type='button' class='btn btn-info' value='수정' data-type='modify' data-value='" + data.data[i].uid + "' onclick='func_license_data(this)'>";
				var add_button = "<input type='button' class='btn btn-success' value='등록' data-type='add' data-value='" + data.data[i].uid + "' onclick='func_license_data(this)'>";
				var buttons = "<td>" + delete_button + "&nbsp;" + modify_button + "&nbsp;" + add_button + "</td>";
				
				$('#license_storage_table').append("<tr>" + type + name + remarks + buttons + "</tr>");
			}
			$('#license_storage_table').append("</tbody>");
		}
	})
	.done(function(){
		if( $.fn.DataTable.isDataTable('#license_storage_table') ) {
			$('#license_storage_table').DataTable().destroy();
		}
		
		$('#license_storage_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "20%", "targets": 1, "className": "dt-center" },
				{ "width": "40%", "targets": 2, "className": "dt-center" },
				{ "width": "30%", "targets": 3, "className": "dt-center" }
			]
		});
	});
	
}

// 새로 추가
var addLicenseData = function() {
	
	var type = $('#license_type').val();
	var name = $('#license_name').val();
	var remarks = $('#license_remarks').val();
	var add_data = {};
	
	if( name == '' || remarks == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요.", 1000);
		return false;
	}
	
	add_data.type = type;
	add_data.name = name;
	add_data.remarks = remarks;
	
	$.ajax({
		url: getContextPath() + '/admin/profile/addLicenseData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'data': JSON.stringify(add_data)
		},
		error: function(error) {
			alert("Server Error");
		},
		success: function(data) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 처리하는 중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			show_alert("success", name + " 항목이 추가되었습니다.", 1000);
			
			$('#license_type').val(1);
			$('#license_name').val("");
			$('#license_remarks').val("");
		}
	});
	
}

// 기존에 등록된 license data get
var getLicenseData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getLicenseData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function(error) {
			alert("Server Error");
		},
		success: function(data) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "데이터를 가져오는데 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#license_table').empty();
			
			$('#license_table')
			.append("<thead>" +
						"<tr>" +
							"<th> 종류 </th>" +
							"<th> 자격증 명 </th>" +
							"<th> 부가사항 </th>" +
							"<th> 순서 </th>" +
							"<th> </th>" +
						"</tr>" +
					"</thead>");
			
			$('#license_table').append("<tbody>");
			
			for( var i = 0; i < data.data.length; i++ ) {
				var type = "";
				
				if( data.data[i].type == 1 ) {
					type = "<td> 자격증 </td>";
				} else if( data.data[i].type == 2 ) {
					type = "<td> 면허증 </td>";
				} else {
					type = "<td> 기타 </td>";
				}
				
				var up_class = "fa fa-arrow-circle-o-up ";
				var down_class = "fa fa-arrow-circle-o-down ";
				
				var up_func = "";
				var down_func = "";
				
				if( i == 0 ) { // first row
					up_class += "disabled-icon";
				} else {
					up_class += "page-icon";
					up_func = "setLicenseDataOrdering(this, " + (i + 1) + ")";
				}
				
				if( i == data.data.length - 1 ) { // last row
					down_class += "disabled-icon";
				} else {
					down_class += "page-icon";
					down_func = "setLicenseDataOrdering(this, " + (i + 1) + ")";
				}
				
				var name = "<td>" + data.data[i].name + "</td>";
				var remarks = "<td>" + data.data[i].remarks.replace(/&/i, " ") + "</td>";
				var order_no = "<td> <i class='" + up_class + "' style='font-size: 20px;' onclick='" + up_func + "' data-value='up'></i> &nbsp; <i class='" + down_class + "' style='font-size: 20px;' onclick='" + down_func + "' data-value='down'></i> </td>";
				var button = "<td> <input type='button' value='제거' class='btn btn-warning' onclick='remove_license_data(" + data.data[i].uid + ")'> </td>";
				
				$('#license_table').append( "<tr>" + type + name + remarks + order_no + button + "</tr>" );
			}
			
			var new_type = "<td> <select id='license_type' class='form-control'><option value=1> 자격증 </option><option value=2> 면허증 </option><option value=3> 기타 </option></select> </td>";
			var new_name = "<td> <input type='text' placeholder='자격증 명 입력' class='form-control' id='license_name'> </td>";
			var new_remarks = "<td> <input type='text' placeholder='세부 사항 입력 (ex. yyyy-mm-dd&remark)' class='form-control' id='license_remarks'> </td>";
			var new_order_no = "<td> Auto </td>";
			var new_buttons = "<td> <input type='button' value='추가' class='btn btn-info' onclick='addLicenseData()'> </td>";
			
			$('#license_table').append( "<tr>" + new_type + new_name + new_remarks + new_order_no + new_buttons + "</tr>" );
			$('#license_table').append("</tbody>");
		}
	})
	.done(function(data) {
		// datatable init
		if( $.fn.DataTable.isDataTable('#license_table') ) {
			$('#license_table').DataTable().destroy();
		}
		
		$('#license_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"paging": false,
			//"pageLength": 10, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "20%", "targets": 1 },
				{ "width": "40%", "targets": 2 },
				{ "width": "10%", "targets": 3 },
				{ "width": "20%", "targets": 4 }
			]
		});
	});
	
}

// 등록된 데이터 제거
var remove_license_data = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/removeLicenseData',
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
				show_alert("warning", "데이터 처리중 문제가 발생했습니다", 1500);
				console.log( data.error );
				return false;
			}

		}
	})
	.done(function(data) {
		setAutoOrdering_license();
	});
	
}

// 순서 재정렬
var setAutoOrdering_license = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setAutoOrderingLicense',
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
		getLicenseData();
	});
	
}

// 모달 안 데이터 조작
var func_license_data = function( object ) {
	
	var type = $(object).attr("data-type");
	var uid = $(object).attr("data-value");
	
	if( type == 'delete' ) {
		
		license_delete_func( uid ); // deleteLicenseStorageData
		
	} else if( type == 'modify' ) {
		
		license_modify_func( uid ); // modifyLicenseStorageData
		
	} else {
		
		license_add_func( uid ); // addLicenseStorageData
		
	}
}

// 모달 안 데이터 등록
var license_add_func = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/addLicenseStorageData',
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
			
			getLicenseData();
			getLicenseStorageData();
			
		}
	});
	
}

// 모달 안 데이터 수정
var license_modify_func = function( uid ) {
	
	$('#license_modify_modal').modal({
		show: true,
		keyboard: false
	});
	
	getLicenseStorageModifyData( uid );
	
}

var getLicenseStorageModifyData = function( uid ) {
	
	$.ajax({
		url: getContextPath() + '/admin/profile/getLicenseStorageModifyData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
		error: function( error ) {
			alert("Server Error");
		},
		success: function( data ) {
			
			if( data.state != 'success' ){
				show_alert("warning", "데이터 처리중 문제가 발생했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			console.log( data );
			
			$('#modify_license_type').val( data.type );
			$('#modify_license_name').val( data.name );
			$('#modify_license_remarks').val( data.remarks);
			
			$('#modify_license_save').attr("onclick", "setModifyLicenseData(" + uid + ")");
		}
	});
	
}

var setModifyLicenseData = function( uid ) {
	
	var object = {};
	var type = $('#modify_license_type').val();
	var name = $('#modify_license_name').val();
	var remarks = $('#modify_license_remarks').val();
	
	if( type == '' || name == '' || remarks == '' ) {
		show_alert("info", "데이터 누락 항목을 확인해주세요.", 1000);
		return false;
	}
	
	object.type = type;
	object.name = name;
	object.remarks = remarks;
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setModifyLicenseData',
		type : 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid,
			'modify': JSON.stringify(object)
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
		getLicenseStorageData();
		getLicenseData();
	});
	
}

// 모달 안 데이터 삭제
var license_delete_func = function( uid ) {
	
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
			url: getContextPath() + '/admin/profile/deleteLicenseStorageData',
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
				
				getLicenseStorageData();
				
			}
		});
		
	});
	
}

//ordering
var setLicenseDataOrdering = function( object, order_no ) {
	
	var type = $(object).attr("data-value");
	
	$.ajax({
		url: getContextPath() + '/admin/profile/setLicenseDataOrdering',
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
			
			getLicenseData();
			
		}
	});
	
}