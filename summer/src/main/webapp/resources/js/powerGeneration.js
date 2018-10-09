/**
 * 
 */

var init_powerGeneration = function() {
	
	$.ajax({
		url: getContextPath() + '/admin/powerGeneration/getData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			
		},
		cache: false,
		success: function( data ) {
			
		},
		error: function( error ) {
			alert( "Server Error" );
			console.log( error );
		}
	})
	.done(function( data ) {
		powerGeneration_table_init( data );
		powerGeneration_dashboard_init( data );
	});
	
}

var powerGeneration_dashboard_init = function( data ) {
	
	var suppReserveRate = parseFloat(data.suppReserveRate);
	var operReserveRate = parseFloat(data.operReserveRate);
	
	$("#suppReservePwr").text( data.suppReservePwr );
	$("#suppReserveRate").text( suppReserveRate.toFixed(2) );
	$("#operReservePwr").text( data.operReservePwr );
	$("#operReserveRate").text( operReserveRate.toFixed(2) );
}

var powerGeneration_table_init = function( data ) {
	
	var thead = "";
	thead += "<thead>"
	thead += 	"<tr>";
	thead += 		"<th> 공급능력 </th>";
	thead += 		"<th> 현재수요 </th>";
	thead += 		"<th> 예측수요 </th>";
	thead += 	"</tr>";
	thead += "</thead>";

	var tbody = "";
	tbody += "<tbody>";
	tbody += 	"<tr>";
	tbody += 		"<td> " + data.suppAbility + " </td>";
	tbody += 		"<td> " + data.currPwrTot + " </td>";
	tbody += 		"<td> " + data.forecastLoad + " </td>";
	tbody += 	"</tr>";
	tbody += "</tbody>";
	
	$('#powerGeneration_table').append( thead ).append( tbody );
	
	console.log( data );
	
	$("#powerGeneration_state").text( data.state );
	$("#powerGeneration_time").text( data.baseDatetime );
	
	$('#powerGeneration_table').DataTable({
		"paging": false,
		"lengthChange": false,
		"searching": false,
		"ordering": false,
		"info": false,
		"autoWidth": false,
		"fixedHeader": true,
		"columnDefs": [
			{className: "dt-center", "targets": [0]},
			{className: "dt-center", "targets": [1]},
			{className: "dt-center", "targets": [2]}
		]
	});
}

$(document).ready(function() {
	init_powerGeneration();
});