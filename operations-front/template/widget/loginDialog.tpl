<script id="login-timeout-dialog-template" type="text/x-jsrender">
<div class="modal fade loginTimeOutDlg" id="login-timeout-dlg" tabindex="-1" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title">登录已超时，请重新登录</h4>
      </div>
      <div class="modal-body">
				<form class="form-signin manufacturer" name="loginform">
					<h2 class="h-manufacturer form-signin-heading">供应商登录</h2>
					<h2 class="h-serviceProvider form-signin-heading">服务商登录</h2>
					<h2 class="h-terminalstore form-signin-heading">终端店登录</h2>
					<h2 class="h-micarshow form-signin-heading">米卡会登录</h2>
					<h5 class="loginError" id="loginError"></h5>
					<div>
						<input class="form-control" name="userName" maxlength="13" value="" type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" placeholder="手机号登录">
					</div>
					<div>
						<input class="form-control" type="password" maxlength="20" name="password" value="" placeholder="请输入密码">
					</div>
					<div id="verifyBox" class="verify-box clearfix">
        					<input id="inpVerify" name="inpVerify" type="text" maxlength="4" class="inp-verify" placeholder="验证码" />
        					<a id="refreshVerify" href="javascript:;">
          					<img class="img-verify" src="{{:url}}" width="100px" />
        					</a>
      				</div>
				</form>
      </div>
      <div class="modal-footer">
				<button type="button" class="btn btn-submit btn-search-width" name="ok">确&nbsp;定</button>
				<button type="button" class="btn btn-default btn-default-grey btn-search-width" name="cancel">取&nbsp;消</button>
      </div>
    </div>
  </div>
</div>
</script>