/**
 * 
 */

var g_nav_img = new Image();
var g_nav_img_sw = false;

var g_timeline_data;
var g_license_data;
var g_intro_data;

var g_title_color = window.getComputedStyle(document.documentElement).getPropertyValue('--title-color-val');
var g_point_color = window.getComputedStyle(document.documentElement).getPropertyValue('--point-color-val');
var g_main_color = window.getComputedStyle(document.documentElement).getPropertyValue('--main-color-val');

g_nav_img.src = getContextPath() + "/resources/css/img/portfolio-main-compress.jpg";

$(g_nav_img).on("load", function() {
	g_nav_img_sw = true;
	$.unblockUI();
});

var func_profile = function() {
	
	$.ajax({
		url: getContextPath() + '/portfolio/getProfileData',
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
			
			g_timeline_data = data.timeline;
			g_license_data = data.license;
			g_intro_data = data.intro;
		}
	})
	.done(function( data ) {
		
		var myEvents = [];
		
		for( var i = 0; i < g_timeline_data.length; i++ ) {
			myEvents.push({ 'date': g_timeline_data[i].event + "<br/>" + g_timeline_data[i].year + "-" + g_timeline_data[i].month + "-" + g_timeline_data[i].day, 'content': g_timeline_data[i].remarks });
		}
		
		$('#timeline').roadmap(myEvents, {
			eventsPerSlide: g_timeline_data.length, // 한 슬라이드에 보여줄 contents
			slide: 1, // 슬라이드 수
			prevArrow: null, // 이전 슬라이드 버튼
			nextArrow: null, // 다음 슬라이드 버튼
			orientation: 'horizontal' // auto, horizontal, vertical 로 타임라인 정렬
		});
		
		$('#license_table').append("<tr><th colspan='2' class='content-center'> 보유 자격증 </th></tr>");
		for( var i = 0; i < g_license_data.length; i++ ) {
			var type = "";
			
			if( g_license_data[i].type == 1 ) {
				type = "자격증";
			} else if( g_license_data[i].type == 2 ) {
				type = "면허증";
			} else {
				type = "보류";
			}
			$('#license_table').append("<tr><th> " + type + " </th><td> " + g_license_data[i].name + " </td></tr>");
		}
		
		$('#typeout_box').append(g_intro_data[0].intro);
		$('#typeout_box').typeOut({
			delay: g_intro_data[0].delay, // 타이핑 딜레이
			preserve: (g_intro_data[0].preserve == 1) ? true : false, // html tag 반영여부
			marker: g_intro_data[0].marker // carot 모양
		});
		
	});
	
}

var func_program = function() {
	
	$.ajax({
		url: getContextPath() + '/portfolio/getProgramData',
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
			
			var layout = 12;
			
			for( var i = 0; i < data.program.length; i++ ) {
				$('#program_area').append("<div class='col-" + (layout / data.program[i].program_layout) + "' style='text-align: center; padding-top: 25px;'>" +
										"<a>" +
											"<img src='" + getContextPath() + "/getPreviewImage?f=" + data.program[i].convert_name + "&ext=" + data.program[i].ext + "' class='image' width='300' height='200' onclick='func_sub_modal(" + data.program[i].uid + ")'>" +
										"</a>" +
										"<span style='display: block; margin-top: 10px;'> " + data.program[i].title + " </span>" +
										"</div>");
			}
		}
	});
	
}

var func_sub_modal = function( uid ) {
	
	$('#program_sub_modal').modal({
		show: true,
		keyboard: false
	});
	
	func_sub_program( uid );
}

var func_sub_program = function( uid ) {

	$.ajax({
		url: getContextPath() + '/portfolio/getProgramSubData',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'uid': uid
		},
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
			
			$('#imageSlider').empty();
			$('#imageSliderNav').remove();
			g_slideIndex = 1;
			
			for( var i = 0; i < data.program.length; i++) {
				$('#program_name').text( data.program[i].title );
				$('#imageSlider').append("<div class='mySlides'>" +
											"<img src='" + getContextPath() + "/getPreviewImage?f=" + data.program[i].convert_name + "&ext=" + data.program[i].ext + "' class='slideImages'>" +
											"<div class='text'> " + data.program[i].remarks + " </div>" +
										"</div>");
			}
			
			$('#imageSlider').append("<a class='prev' onclick='plusSlides(-1)'>&#10094;</a>");
			$('#imageSlider').append("<a class='next' onclick='plusSlides(1)'>&#10095;</a>");
			
			$('#imageSlider').after("<div id='imageSliderNav' style='text-align: center;'></div");
			
			for( var i = 0; i < data.program.length; i++ ) {
				$('#imageSliderNav').append("<span class='dot' onclick='currentSlide(" + (i + 1) + ")'></span>");
			}
		}
	})
	.done(function( data ) {
		showSlides(1);
	});
	
}

var func_skill = function() {
	
	$('#skill_area').empty();
	
	$.ajax({
		url: getContextPath() + '/portfolio/getSkillData',
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

			for( var i = 0; i < data.skill.length; i++ ) {
				$('#skill_area').append("<div class='skill-bar' data-width='" + data.skill[i].ability + "' data-background=" + data.skill[i].background_color + ">" + data.skill[i].name + " ( " + data.skill[i].ability + "% ) " + "</div>");
			}
			
		}
	})
	.done(function( data ) {
		$('.skill-bar').simpleSkillbar({});
	});
	
}

var func_activity = function() {
	
	$.ajax({
		url: getContextPath() + '/portfolio/getActivityData',
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
			
			$('#activity_area').append("<div class='row' id='activity_inner'></div>");
			for( var i = 0; i < data.activity.length; i++ ) {
				$('#activity_inner').append("<div class='col-6' style='padding: 50px;'>" +
												"<span style='color: " + g_title_color + "'>" +
													data.activity[i].shape + "&nbsp;&nbsp;" +
												"</span>" +
												"<span class='cool-link'>" +
													data.activity[i].year + "&nbsp;&nbsp;" + data.activity[i].contents +
												"</span>" +
											"</div>");
			}
		}
	});
	
}

var func_contact = function() {
	
	//func_contact
	/*$.ajax({
		url: getContextPath() + '/portfolio/getContactData',
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
			
		}
	});*/
	
}

$(document).on("scroll", onScroll);
$(document).ready(function() {
	$.blockUI({ message: '화면 준비중입니다.' });
	
	setTimeout(function() {
		if( g_nav_img_sw == true ) {
			$.unblockUI();
		}
	}, 500);
	
	func_profile();
	func_skill();
	func_program();
	func_activity();
	func_contact();
});