<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- Default to the left -->
    <strong>Copyright &copy; 2018 <a href="#">Frozen-admin</a>.</strong>
  </footer>
</div>

<div class="modal fade in" id="sha_modal">
	<div class="modal-dialog" style="max-width: 1000px;">
			
		<!-- modal contents -->
		<div class="modal-content">
			<div class="modal-header">
				<span> SHA-256 생성기 </span>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body">
				
				<div class="row">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 dt-right">
						<span class="line-height"> 문자열 입력 </span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 col-xs-9">
						<input type="text" class="form-control" id="convert_str">
					</div>
					<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
						<input type="button" class="btn btn-info" value="변환" onclick="convertToSHA()">
					</div>
				</div>
				
				<div class="padding-lg"></div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 dt-center">
						<i class="fa fa-arrow-down" style="font-size: 28px;"></i>
					</div>
				</div>
				
				<div class="padding-lg"></div>
				
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<input type="text" class="form-control" id="convert_result" readonly="readonly">
					</div>
				</div>
				
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>

<!-- ./wrapper -->
</body>
</html>