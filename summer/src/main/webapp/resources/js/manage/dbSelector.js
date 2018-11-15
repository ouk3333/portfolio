
var g_DBTableNames = new Array();

var getSelectDataBase = function() {
	
	var table_name = $('#table_name_select_input').val();
	
	$.ajax({
		url: getContextPath() + '/admin/utilCollection/getSelectDataBase',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'table_name' : table_name
		},
		error: function( request, status, error ) {
			console.log( "request: " + request);
			console.log( "status: " + status );
		},
		success: function( data ) {
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생 했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			$('#database_table').empty();
			
			var title_column = "";
			
			for( var i = 0; i < data.columns.length; i++ ) {
				title_column += "<th> " + data.columns[i].column + " </th>";
			}
			
			$('#database_table').append("<thead><tr>" + title_column + "</tr></thead>");
			$('#database_table').append("<tbody>");
			for( var i = 0; i < data.data.length; i++ ) {
				
				var key = Object.keys(data.data[i]);
				var value = Object.values(data.data[i]);
				
				var row = "";
				
				for( var j = 0; j < key.length; j++ ) {
					row += "<td>" + value[j] + "</td>";
				}
				
				$('#database_table').append("<tr>" + row + "</tr>");
				
			}
			$('#database_table').append("</tbody>");
		}
	})
	.done(function( data ) {
		if( $.fn.DataTable.isDataTable('#database_table') ) {
			$('#database_table').DataTable().destroy();
		}
		
		$('#database_table').DataTable({
			"info": false, // 검색 결과 수 기능
			"searching": false, // 필터링 기능
			"ordering": false, // 상위컬럼 정렬 기능
			"lengthChange": false, // 페이지에 표시할 데이터 수 변경
			"pageLength": 5, // 한 페이지에 표시할 데이터 수
			"language": oLanguageSetting,
			"autoWidth": false,
			"columnDefs": [
				/*{ "width": "10%", "targets": 0, "className": "dt-center" },
				{ "width": "10%", "targets": 1, "className": "dt-center" },
				{ "width": "50%", "targets": 2, "className": "dt-center" },
				{ "width": "15%", "targets": 3, "className": "dt-center" },
				{ "width": "15%", "targets": 4, "className": "dt-center" }*/
			]
		});
	});
	
}

var getDBTableNames = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/utilCollection/getDBTableNames',
		type: 'POST',
		dataType: 'JSON',
		data: {},
		error: function( request, status, error ) {
			console.log( "request: " + request);
			console.log( "status: " + status );
		},
		success: function( data ) {
			
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생 했습니다.", 1500);
				console.log( data.error );
				return false;
			}
			
			for( var i = 0; i < data.table_names.length; i++ ) {
				g_DBTableNames.push( data.table_names[i].table_name );
			}
		}
	})
	.done(function( data ) {
		$('#table_name_select_input').autocomplete({
			source: g_DBTableNames,
			select: function( e, ui ) {
				//console.log( ui.item );
			},
			focus: function( e, ui ) {
				return false;
			}
		})
	});
	
}

$(document).ready(function() {
	getDBTableNames();
});