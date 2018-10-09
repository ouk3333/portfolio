<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.min.js"></script><!-- jquery -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery-ui.min.js"></script><!-- jquery-ui -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/bootstrap.min.js"></script><!-- bootstrap -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/bootstrap.bundle.min.js"></script><!-- bootstrap bundle -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.easing.min.js"></script><!-- jquery easing -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.roadmap.js"></script><!-- timeline -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.typeout.js"></script><!-- typeout -->

<script src="<%= request.getContextPath() %>/resources/js/portfolio/resume.js"></script><!-- resume -->
<script src="<%= request.getContextPath() %>/resources/js/sweetalert.min.js"></script><!-- sweetalert -->
<script src="<%= request.getContextPath() %>/resources/js/portfolio/jquery.blockUI.js"></script><!-- block UI -->
<script src="<%= request.getContextPath() %>/resources/js/Osinfo.js"></script><!-- OS type -->
<script src="<%= request.getContextPath() %>/resources/js/common.js"></script><!-- common -->

<script>

var g_nav_img = new Image();
var g_nav_img_sw = false;

g_nav_img.src = getContextPath() + "/resources/css/img/portfolio-main-compress.jpg";

$(g_nav_img).on("load", function() {
	g_nav_img_sw = true;
	$.unblockUI();
});

// timeline
var func_timeline = function() {
	
	var myEvents = [{
			date: '출생',
			content: '1995-01-17<br/>인천 간석동'
		},{
			date: '중학교 졸업',
			content: '2010-02-26<br/>동산 중학교'
		},{
			date: '고등학교 졸업',
			content: '2013-02-22<br/>동산 고등학교'
		},
		{
			date: '대학교 입학',
			content: '2013-02-28<br/>유한대학교<br/>컴퓨터정보과'
		},
		{
			date: '대학교 졸업',
			content: '2018-02-22<br/>유한대학교<br/>IT소프트웨어과'
		}
	];

	$('#timeline').roadmap(myEvents, {
		eventsPerSlide: 5, // 한 슬라이드에 보여줄 contents
		slide: 1, // 슬라이드 수
		prevArrow: null, // 이전 슬라이드 버튼
		nextArrow: null, // 다음 슬라이드 버튼
		orientation: 'horizontal' // auto, horizontal, vertical 로 타임라인 정렬
	});
}

// typeit
var func_typeout = function() {
	
	$('#typeout_box').append("가나다라마바사<br/>아자차카타파하");
	$('#typeout_box').typeOut({
		delay: 150, // 타이핑 딜레이
		preserve: true, // html tag 반영여부
		marker: '_' // carot 모양
	});
	
}

$(document).on("scroll", onScroll);
$(document).ready(function() {
	$.blockUI({ message: '화면 준비중입니다.' });
	
	setTimeout(function() {
		if( g_nav_img_sw == true ) {
			$.unblockUI();
		}
	}, 500);
	
	func_timeline();
	func_typeout();
});

</script>