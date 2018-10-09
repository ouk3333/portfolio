<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>

/* custom properties */
/* var point-color = window.getComputedStyle(document.documentElement).getPropertyValue('--point-color-val') */
:root {
	--point-color-val: ${ point_color };
	--main-color-val: ${ main_color };
	--title-color-val: ${ title_color };
	--sidemenu-image-val: ${ sidemenu_image };
	--page-title-val: ${ page_title }
}

</style>

<!-- CSS -->
<link href="<%= request.getContextPath() %>/resources/css/portfolio/bootstrap.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/font-awesome.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/resume.min.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/font.css" rel="stylesheet">
<link href="<%= request.getContextPath() %>/resources/css/portfolio/jquery.roadmap.css" rel="stylesheet"><!-- timeline -->

<style>

body { font-family: "Nanum Gothic" !important; color: var(--title-color-val) }

/* .bg-primary { background-color: #00D8C8 !important; } */
.bg-primary { background-color: var(--main-color-val) !important; }

/* a.nav-link { color: #fff; opacity: 0.8; } */
/* a.nav-link:hover { color: #F29661 !important; opacity: 1.0 !important; }
a.active { color: #F29661 !important; opacity: 1.0 !important; } */

/* a { color: #F29661 !important; opacity: 1.0 !important; } */

/* main, title, point color 집합 */
a.main-color { color: var(--main-color-val); opacity: 1.0; }
a.point-color { color: var(--point-color-val) !important; opacity: 1.0 !important; }
a.active { color: var(--point-color-val) !important; opacity: 1.0 !important; }
a.point-color:hover { color: var(--point-color-val) !important; opacity: 1.0 !important; }
.title-color { color: var(--title-color-val) !important; opacity: 1.0 !important; }

.nav-image { background: url('/frozen/resources/css/img/portfolio-main-compress.jpg'); background-size: cover; }
.section-color-setting { background-color: #FAFAFA !important; }

.content-left { text-align: left; }
.content-right { text-align: right; }
.content-center { text-align: center; }

.content-title { font-size: 50px; font-weight: bold; }

/* timeline */
.roadmap.roadmap--orientation-horizontal { width: 100% !important; }

/* license table */
table.license {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid var(--title-color-val);
	border-left: 3px solid var(--title-color-val);
	margin : 20px 10px;
	width: 100%;
}
table.license th {
	width: 30%;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: var(--title-color-val);
	border-right: 1px solid var(--title-color-val);
	border-bottom: 1px solid var(--title-color-val);

}
table.license td {
	width: 70%;
	padding: 10px;
	vertical-align: top;
	color: var(--title-color-val);
	border-right: 1px solid var(--title-color-val);
	border-bottom: 1px solid var(--title-color-val);
}

/* div.event {
	width: 120px !important;
	position: absolute !important;
	margin: 0;
	transform: translate(-50%, -50%) !important;
	top: 50% !important;
	left: 50%;
} */

</style>
