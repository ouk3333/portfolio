<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<link href="<%= request.getContextPath() %>/resources/css/adminlte.min.css" rel="stylesheet">

<html>
<head>
  <meta charset="utf-8">
  <title>페이지를 찾을 수 없습니다.</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Content Wrapper. Contains page content -->
  <div class="container">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <!-- <h1>페이지를 찾을수 없습니다.</h1> -->
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="${ pageContext.request.contextPath }">Home</a></li>
              <li class="breadcrumb-item active">잘못된 접근</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="error-page">
        <h2 class="headline text-warning"> 404 </h2>

        <div class="error-content">
        
          <h3><i class="fa fa-warning text-warning"></i> 페이지를 찾을 수 없습니다. </h3>
          
          <p>
           	잘못된 접근입니다. 뒤로 가시려면  <a href="#" onclick="javascript:history.go(-1); return false;"> 여기를 </a> 클릭 해주세요.
          </p>
          
          <p><span style="font-size: 24px;"> 사용자의 잘못된 접근을 </span></p>
          
          <p><span style="font-size: 24px;"> 방지하도록 최선을 다하겠습니다. </span></p>
          
        </div>
        <!-- /.error-content -->
      </div>
      <!-- /.error-page -->
    </section>
    <!-- /.content -->
  </div>

  <aside class="control-sidebar control-sidebar-dark">
  </aside>
</div>
</body>
</html>