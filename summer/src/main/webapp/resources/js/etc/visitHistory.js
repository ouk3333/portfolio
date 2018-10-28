/**
 * 
 */

// 아이피에 따른 위경도 바로가기 시스템 구축

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
	$('#coordinates_area').empty();
	
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
			
			if( isEmpty(data.list) == false ) {
				for( var i = 0; i < data.list.length; i++ ) {
					$('#coordinates_area').append(	"<div class='col-lg-3 col-md-3 col-sm-3 col-xs-3'>" +
														"<div class='color-palette-set'>" +
															"<div class='bg-info color-palette'><span>보류</span></div>" +
														"</div>" +
													"</div>");
				}
			}
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