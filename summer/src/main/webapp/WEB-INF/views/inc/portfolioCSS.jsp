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
<link href="<%= request.getContextPath() %>/resources/css/portfolio/w3school-slider.css" rel="stylesheet"><!-- image slider -->

<style>

body { font-family: "Nanum Gothic" !important; color: var(--title-color-val) }

.bg-primary { background-color: var(--main-color-val) !important; }

/* main, title, point color 집합 */
a.main-color { color: var(--main-color-val); opacity: 1.0; }
a.point-color { color: var(--point-color-val) !important; opacity: 1.0 !important; }
a.active { color: var(--point-color-val) !important; opacity: 1.0 !important; }
a.point-color:hover { color: var(--point-color-val) !important; opacity: 1.0 !important; }
.title-color { color: var(--title-color-val) !important; opacity: 1.0 !important; }

.nav-item { padding: 5px !important; }

.nav-image { background: url('/frozen/resources/css/img/portfolio-main-compress.jpg'); background-size: cover; }
.section-color-setting { background-color: #FAFAFA !important; }

.content-left { text-align: left; }
.content-right { text-align: right; }
.content-center { text-align: center; }

.content-title { font-size: 50px; font-weight: bold; }

/* timeline */
.roadmap.roadmap--orientation-horizontal { width: 100% !important; }

/* license table */
table.license-table {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid var(--title-color-val);
	border-left: 3px solid var(--title-color-val);
	margin : 20px 10px;
	width: 100%;
}
table.license-table th {
	width: 30%;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: var(--title-color-val);
	border-right: 1px solid var(--title-color-val);
	border-bottom: 1px solid var(--title-color-val);

}
table.license-table td {
	width: 70%;
	padding: 10px;
	vertical-align: top;
	color: var(--title-color-val);
	border-right: 1px solid var(--title-color-val);
	border-bottom: 1px solid var(--title-color-val);
}

.skill-bar { margin: 30px auto; height: 30px; }
.sb_label { text-align: right !important; }

.image {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    /* width: 200px; */
}

.image:hover {
    box-shadow: 0 0 2px 1px currentColor;
    cursor: pointer;
}

.cool-link {
    display: inline-block;
    color: var(--title-color-val);
    text-decoration: none;
}

.cool-link::after {
    content: '';
    display: block;
    width: 0;
    height: 2px;
    background: var(--title-color-val);
    transition: width .3s;
}

.cool-link:hover::after {
    width: 100%;
    /* transition: width .3s; */
}

</style>
