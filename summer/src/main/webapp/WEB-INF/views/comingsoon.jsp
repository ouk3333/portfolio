<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
	<meta charset="utf-8">
	<title>${ title }</title>
</head>
<style type="text/css">
body{
	 background:#202020;
	 font:bold 12px Arial, Helvetica, sans-serif;
	 margin:0;
	 padding:0;
	 min-width:960px;
	 color:#bbbbbb; 
}

a { 
	text-decoration:none; 
	color:#00c6ff;
}

h1 {
	font: 4em normal Arial, Helvetica, sans-serif;
	padding: 20px;	margin: 0;
	text-align:center;
}

h1 small{
	font: 0.2em normal  Arial, Helvetica, sans-serif;
	text-transform:uppercase; letter-spacing: 0.2em; line-height: 5em;
	display: block;
}

h2 {
    font-weight:700;
    color:#bbb;
    font-size:20px;
}

h2, p {
	margin-bottom:10px;
}

.container {width: 960px; margin: 0 auto; overflow: hidden;}

.clock {width:800px; margin:0 auto; padding:30px; border:1px solid #333; color:#fff; }

.Date { font-family:Arial, Helvetica, sans-serif; font-size:36px; text-align:center; text-shadow:0 0 5px #00c6ff; }

ul { width:800px; margin:0 auto; padding:0px; list-style:none; text-align:center; }
ul li { display:inline; font-size:10em; text-align:center; font-family:Arial, Helvetica, sans-serif; text-shadow:0 0 5px #00c6ff; }

#point { position:relative; -moz-animation:mymove 1s ease infinite; -webkit-animation:mymove 1s ease infinite; padding-left:10px; padding-right:10px; }

@-webkit-keyframes mymove 
{
	0% {opacity:1.0; text-shadow:0 0 20px #00c6ff;}
	50% {opacity:0; text-shadow:none; }
	100% {opacity:1.0; text-shadow:0 0 20px #00c6ff; }	
}


@-moz-keyframes mymove 
{
	0% {opacity:1.0; text-shadow:0 0 20px #00c6ff;}
	50% {opacity:0; text-shadow:none; }
	100% {opacity:1.0; text-shadow:0 0 20px #00c6ff; }	
}

</style>
<body>
	<div class="container">
		<div class="page-wrapper">
			<div class="row">
				<div class="col-md-12" style="text-align: center; padding-top: 30%;">
					<div class="clock">
						<div id="point" class="Date loading_div" style="z-index: 10000; margin-top: -10px; text-align: center;">
							<span> Loading... </span>
						</div>
						
						<div id="clock_div" style="display: none;">
							<div id="Date" class="Date"></div>
	
							<ul>
								<li id="hours">00</li>
							    <li id="point">:</li>
							    <li id="min">00</li>
							    <li id="point">:</li>
							    <li id="sec">00</li>
							</ul>
						
							<div class="Date" id="admin">
								준비중입니다
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<% response.sendRedirect(request.getContextPath() + "/portfolio"); %>

<script>
$(document).ready(function() {
	var monthNames = [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]; 
	var dayNames= ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"]

	var newDate = new Date();
	newDate.setDate(newDate.getDate());    
	$('#Date').html(newDate.getFullYear() + '년 ' + monthNames[newDate.getMonth()] + ' ' + newDate.getDate() + '일 ' + dayNames[newDate.getDay()]);

	setInterval( function() {
		var seconds = new Date().getSeconds();
		$("#sec").html(( seconds < 10 ? "0" : "" ) + seconds);
		$(".loading_div").css("display", "none");
		$("#clock_div").css("display", "unset");
	},1000);
		
	setInterval( function() {
		var minutes = new Date().getMinutes();
		$("#min").html(( minutes < 10 ? "0" : "" ) + minutes);
    },1000);
		
	setInterval( function() {
		var hours = new Date().getHours();
		$("#hours").html(( hours < 10 ? "0" : "" ) + hours);
    }, 1000);
	
	$('#admin').on('dblclick', function(){
		location.href = getContextPath() + "/admin/login";
	});
	
	$('#Date').on('dblclick', function() {
		location.replace( getContextPath() + "/portfolio" );
	});
		
}); 
</script>