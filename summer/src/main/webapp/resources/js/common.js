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
			pageName = "방문기록 관리";
		} else if( second_path == 'pageSetting' ) {
			nav = "페이지 설정";
			pageName = "페이지 설정 관리"
		} else if( second_path == 'profile' ) {
			nav = "프로필";
			pageName = "프로필 관리";
		} else if( second_path == 'program' ) {
			nav = "프로그램";
			pageName = "프로그램 관리";
		} else if( second_path == 'skill' ) {
			nav = "기술";
			pageName = "기술 관리"
		} else if( second_path == 'activity' ) {
			nav = "활동";
			pageName = "활동 관리";
		} else if( second_path == 'contact' ) {
			nav = "연락처";
			pageName = "연락처 관리"
		} else if( second_path == 'openSource' ) {
			nav = "오픈소스";
			pageName = "오픈소스 관리";
		} else if( second_path == 'volunteer' ) {
			nav = "지원목록";
			pageName = "지원목록 관리";
		} else if( second_path == 'utilCollection' ) {
			nav = "유틸모음";
			pageName = "유틸 모음";
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
		} else if( path == 'utilCollection' ) {
			$('.utilCollection').addClass("active");
		}
		
	} else if( menu == 'portfolio' ) {
		
		$('.portfolio').addClass("menu-open");
		
		if( path == 'profile' ) {
			$('.profile').addClass("active");
		} else if( path == 'program' ) {
			$('.program').addClass("active");
		} else if( path == 'skill' ) {
			$('.skill').addClass("active");
		} else if( path == 'activity' ) {
			$('.activity').addClass("active");
		} else if( path == 'contact' ) {
			$('.contact').addClass("active");
		}
		
	} else if( menu == 'etc' ) {
		
		if( path == 'openSource' ) {
			$('.openSource').addClass("active");
		} else if( path == 'visitHistory' ) {
			$('.visitor').addClass("active");
		} else if( path == 'powerGeneration' ) {
			$('.powerGeneration').addClass("active");	
		} else if( path == 'volunteer' ) {
			$('.volunteer').addClass("active");
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

var delete_alert = function( func ) {
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
		
		func();
		
	});
}

var confirm_alert = function( func ) {
	swal({
		title: "진행 하시겠습니까?",
		text: "해당 작업을 진행 할 예정입니다.",
		type: "info",
		showCancelButton: true,
		confirmButtonClass: "btn-info",
		confirmButtonText: "확인",
		cancelButtonText: "취소",
		closeOnConfirm: true
	},
	function() {
		
		func();
		
	});
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
	"infoFiltered": "총 _MAX_ 개 데이터 중",
	"search": "검색 : _INPUT_"
}

var decreaseWord = function( value, index ) {
	
	var str = value;
	
	if( str.length > index ) {
		str = "<span class='tooltips' data-toggle='tooltip' data-placement='bottom' data-original-title='" + value + "'>" + str.substring(0, index) + " ...</span>";
	}
	
	return str;
}

var isEmpty = function( obj ) {
	for(var key in obj) {
        if(obj.hasOwnProperty(key))
            return false;
    }
    return true;
}

var func_RGBtoHEX = function( value ) {
	var a = value.toString(16);
	
	return a.length == 1 ? "0" + a : a;
}

var getConvertRGBtoHEX = function( r, g, b ) {
	return "#" + func_RGBtoHEX(r) + func_RGBtoHEX(g) + func_RGBtoHEX(b);
}

var getConvertHEXtoRGB = function( hex ) {
	var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
	
	return result ? {
		r: parseInt(result[1], 16),
		g: parseInt(result[2], 16),
		b: parseInt(result[3], 16)
	} : null;
}

var buttonBlur = function( object ) {
	
	$(object).blur();
	$('.tooltip').remove();
	
	return false;
}

//Promise
/*
setPromise(() => {
		
	})
	.then(() => {
		
	})
	.catch() {
	
	})
 */
function setPromise(callback) {
	
	return new Promise(function(resolve, reject) {
		if( typeof callback === "function" ) {
			setTimeout(() => {
				resolve(callback);
			}, 250)
		} else {
			reject("not a function");
		}
	});
}

var toggleDIV = function( object ) {

	if( object == null || object == undefined || object == '' ) {
		return false;
	}
	
	var id = $(object).attr("data-toggle-value");
	
	$('#' + id).slideToggle();
	
	$(object).blur();
}

function detectBrowser() {
	// Opera 8.0+
	var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;

	// Firefox 1.0+
	var isFirefox = typeof InstallTrigger !== 'undefined';

	// Safari 3.0+ "[object HTMLElementConstructor]" 
	var isSafari = /constructor/i.test(window.HTMLElement) || (function (p) { return p.toString() === "[object SafariRemoteNotification]"; })(!window['safari'] || (typeof safari !== 'undefined' && safari.pushNotification));

	// Internet Explorer 6-11
	var isIE = /*@cc_on!@*/false || !!document.documentMode;

	// Edge 20+
	var isEdge = !isIE && !!window.StyleMedia;

	// Chrome 1+
	var isChrome = !!window.chrome && !!window.chrome.webstore;

	// Blink engine detection
	var isBlink = (isChrome || isOpera) && !!window.CSS;
	
//	console.log( isOpera );
//	console.log( isFirefox );
//	console.log( isSafari );
//	console.log( isIE );
//	console.log( isEdge );
//	console.log( isChrome );
//	console.log( isBlink );
}

$(document).ready(function(){
	setPageName();
	setCurrentMenuActive();
	access_Visitor();
	detectBrowser();
});