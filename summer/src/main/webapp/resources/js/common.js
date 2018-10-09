/**
 * 
 */

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf("/", hostIndex + 1));
}

var getParameter = function( url, finder ) {
	
	var url_string = new URL( url );
	var parameter = url_string.searchParams.get( finder );
	
	return parameter;
}

var getEncode = function( value ) {
	
	var str = encodeURIComponent(value);
	
	return str;
}

var getDecode = function( value ) {
	
	var str = decodeURIComponent( value );
	
	return str;
}

var filter_number = function( object ) {
	
	var str = $(object).val().substring($(object).val().length - 1, $(object).val().length);
	var input = /^[0-9]/g;
	
	if( input.test(str) ) {
		return true;
	} else {
		$(object).val( $(object).val().substring(0, $(object).val().length - 1) );
		return false;
	}
}

var setPageName = function() {
	var path = location.pathname.split('/')[2];
	var pageTitle = window.getComputedStyle(document.documentElement).getPropertyValue('--page-title-val'); // 페이지 타이틀
	var nav = "관리자 메인";
	var pageName = "관리자";
	
	// id.page_name == pageName 으로 (오른쪽)
	// id.page_name_nav == nav 로 (왼쪽)
	
	if( path == 'admin' ) {
		
		var second_path = location.pathname.split('/')[3];
		
		if( second_path == 'login' ) {
			pageTitle += " - 관리자로그인";
		} else if( second_path == 'powerGeneration' ) {
			nav = "전력량";
			pageName = "전력량 확인";
		} else if( second_path == 'visitHistory' ) {
			nav = "방문기록";
			pageName = "방문기록 확인";
		} else if( second_path == 'pageSetting' ) {
			nav = "페이지 설정";
			pageName = "페이지 기본 설정"
		} else if( second_path == 'profile' ) {
			nav = "프로필";
			pageName = "프로필 수정";
		} else if( second_path == 'program' ) {
			nav = "프로그램";
			pageName = "프로그램 수정";
		} else if( second_path == 'skill' ) {
			nav = "기술";
			pageName = "기술 수정"
		} else if( second_path == 'experience' ) {
			nav = "경험";
			pageName = "경험 수정";
		} else if( second_path == 'contact' ) {
			nav = "연락처";
			pageName = "연락처 수정"
		}
		
		pageTitle += " | 관리자페이지";
		
		$('#page_name').text(pageName);
		$('#page_name_nav').text(nav);
		
	}
	
	document.title = pageTitle;
}

var setCurrentMenuActive = function() {

	var path = location.pathname.split('/')[2];
	var url = window.location.href;
	var menu = getParameter( url, "m" );
	
	if( path == 'portfolio' ) {
		return;
	}
	
	path = location.pathname.split('/')[3];
	
	if( menu == 'manage' ) {
		
		$('.manage').addClass("menu-open");
		
		if( path == 'pageSetting' ) {
			$('.pageSetting').addClass("active");
		}
		
	} else if( menu == 'portfolio' ) {
		
		$('.portfolio').addClass("menu-open");
		
		if( path == 'profile' ) {
			$('.profile').addClass("active");
		} else if( path == 'program' ) {
			$('.program').addClass("active");
		} else if( path == 'skill' ) {
			$('.skill').addClass("active");
		} else if( path == 'experience' ) {
			$('.experience').addClass("active");
		} else if( path == 'contact' ) {
			$('.contact').addClass("active");
		}
		
	}
	
}

var show_alert = function( type, msg, timer ) {
	swal({
		title: "",
		text: msg,
		type: type,
		timer: timer,
		showConfirmButton: false
	})
}

function pageLoad() {
    history.pushState(null, null, location.href);
    
    window.onpopstate = function() {
    	history.go(1);
    };
}

function access_Visitor() {
	
	var item = sessionStorage.getItem("frozen_visitor");
	
	if( item != null ) {
		return false;
	}
	
	var browser = navigator.appName;
	var OSInfo = OSInfoDev();
	
	sessionStorage.setItem("frozen_visitor", browser);
	
	$.ajax({
		url: getContextPath() + '/visitor',
		type: 'POST',
		dataType: 'JSON',
		data: {
			'type': OSInfo
		},
		cache: false,
		success: function( data ) {
			if( data.state != 'success' ) {
				show_alert("warning", "서버에 문제가 발생했습니다.", 1500);
				
				setTimeout(function() {
					location.href = getContextPath() + '/error/error500';
				}, 1500);
			}
		},
		error: function( error ) {
			alert( "Server Error" );
			location.href = getContextPath() + '/error/error500';
		}
	});
}

function onScroll(event){
	var scrollPos = $(document).scrollTop() + 10;
	
	$('#scroll_nav a').each(function () {
		var currLink = $(this);
		var refElement = $(currLink.attr("href"));
		var section_id = $(refElement).attr("id");
		var section = $('section#' + section_id)
		
		if (section.position().top <= scrollPos && section.position().top + section.height() > scrollPos) {
			$('#scroll_nav li a').removeClass("active");
			currLink.addClass("active");
		} else {
			currLink.removeClass("active");
		}
    });
}

var oLanguageSetting = {
	"lengthMenu": " _MENU_ 목록 보기",
	"zeroRecords": "데이터가 존재하지 않습니다.",
	"info": " _PAGES_ 페이지 중 _PAGE_ 페이지",
	"infoEmpty": "데이터가 존재하지 않습니다.",
	"infoFiltered": "총 _MAX_ 개 데이터 중"
}

$(document).ready(function(){
	setPageName();
	setCurrentMenuActive();
	access_Visitor();
	//pageLoad();
});