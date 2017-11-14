<script id="securitySetting-template" type="text/x-jsrender">
	<div id="securitySetting">
		<div id="passwordUpdate" class="password-edit">
			<h3 class="heading">修改初始密码</h3>
			<form class="form-horizontal" id="passwordForm" name="passwordForm" method="get" action="">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="password">旧的密码</label>
					<div class="col-sm-7">
						<input class="form-control" type="password" id="oldPassword" name="oldPassword" maxlength="15" placeholder="旧的密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="password">新的密码</label>
					<div class="col-sm-7">
						<input class="form-control" type="password" id="newPassword" name="newPassword" maxlength="15" placeholder="新的密码">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="confirmPassword">再次确认</label>
					<div class="col-sm-7">
						<input class="form-control" type="password" id="confirmPassword" name="confirmPassword" maxlength="15" placeholder="再次确认">
					</div>
				</div>
				<div class="form-group updataPassword">
					<div class="col-sm-offset-2 col-sm-7">
						<span class='error updataPasswordError'><span>
					</div>
				</div>
				<div class="form-group">
				  <div class="col-sm-offset-2 col-sm-7">
				    <button id="updataPasswordBtn" menucode='registryEditDetail' class="btn btn-block btn-primary" type="button">提交</button>
				  </div>
				</div>
			</form>
		</div>
	</div>
</script>