
var getBrowserStatsData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/visitHistory/getBrowserStatsData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
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
			
			$('#browser_chart').empty();
			
			var result = google.visualization.arrayToDataTable(data.stats);
			var options = {'title':'브라우저 별 통계', 'width':$('#browser_chart').width(), 'height':500};
			var chart = new google.visualization.PieChart(document.getElementById('browser_chart'));
			
			chart.draw(result, options);
		}
	});
	
}

var getOsStatsData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/visitHistory/getOsStatsData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
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
			
			$('#os_chart').empty();
			
			var result = google.visualization.arrayToDataTable(data.stats);
			var options = {'title':'OS 별 통계', 'width':$('#os_chart').width(), 'height':500};
			var chart = new google.visualization.PieChart(document.getElementById('os_chart'));
			
			chart.draw(result, options);
		}
	});
	
}

var getIpStatsData = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/visitHistory/getIpStatsData',
		type: 'POST',
		dataType: 'JSON',
		data: {},
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
			
			$('#ip_chart').empty();
			
			var result = google.visualization.arrayToDataTable(data.stats);
			var options = {'title':'IP 별 통계', 'width':$('#ip_chart').width(), 'height':500};
			var chart = new google.visualization.PieChart(document.getElementById('ip_chart'));
			
			chart.draw(result, options);
		}
	});
	
}

var refresh_chart = function() {
	google.charts.load('current', {'packages':['corechart']});
	
	setPromise(() => {
		
	})
	.then(() => {
		google.charts.setOnLoadCallback(getIpStatsData);
	})
	.then(() => {
		google.charts.setOnLoadCallback(getOsStatsData);
	})
	.then(() => {
		google.charts.setOnLoadCallback(getBrowserStatsData);
	});
}

var getVisitorIpAddress = function() {

	$.ajax({
		url: getContextPath() + '/admin/visitHistory/getVisitorIpAddress',
		type: 'POST',
		dataType: 'JSON',
		data: {},
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
			
			$('#visitor_table').empty();
			
			$('#visitor_table').append(	"<thead>" +
											"<tr>" +
												"<th> # </th>" +
												"<th> 아이피 </th>" +
												"<th> 방문횟수 </th>" +
												"<th> 위치(오차: 반경5km) </th>" +
											"</tr>" +
										"</thead>");
			
			$('#visitor_table').append("<tbody>");
			for( var i = 0; i < data.list.length; i++ ) {
				var number = "<td>" + (i + 1) + "</td>";
				var ip = "<td>" + data.list[i].name + "</td>";
				var cnt = "<td>" + data.list[i].cnt + "</td>";
				var button = "<td> <input type='button' class='btn btn-success' value='바로가기' data-value='" + data.list[i].name + "' onclick='getLocationData(this)'> </td>";
				
				$('#visitor_table').append("<tr>" + number + ip + cnt + button + "</tr>");
			}
			$('#visitor_table').append("</tbody>");
		}
	})
	.done(function( data ) {
		if( $.fn.DataTable.isDataTable('#visitor_table') ) {
			$('#visitor_table').DataTable().destroy();
		}
		
		$('#visitor_table').DataTable({
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

var getLocationData = function( object ) {
	
	var ip = $(object).attr("data-value");
	
	$.ajax({
		url: 'http://ip-api.com/json/' + ip,
		type: 'GET',
		dataType: 'JSON',
		data: {},
		error: function( request, status, error ) {
			alert("Server Error");
			console.log( "request: " + request + " || status: " + status );
		},
		success: function( data ) {
			
			if( data.status != 'success' ) {
				show_alert("warning", "데이터 처리 중 문제가 발생했습니다.", 1500);
				console.log( data.message );
				return false;
			}
			
			var lat = data.lat;
			var lon = data.lon;
			
			window.open("https://www.google.co.kr/maps/place/" + lat + "," + lon, "Location", "location=no, toolbar=no, menubar=no, status=no, channelmode=no");
		}
	});
	
}

var skillToggleArea = function( object ) {
	
	var type = $(object).attr("data-type");
	
	if( type == 'chart' ) {
		$('#chart_area').slideToggle();
	} else if( type == 'coordinates' ) {
		$('#coordinates_area').slideToggle();
	}
	
	return false;
}

$(document).ready(function() {
	refresh_chart();
	getVisitorIpAddress();
});