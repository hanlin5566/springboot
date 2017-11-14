<script id="forgotPassword-template" type="text/x-jsrender">
<div id="forgotPassword" class="forgot-password">
  <div class="checkPhoneCode">
    <h3 class="title">找回密码</h3>
    <form class="form-horizontal" id="" name="" method="get" action="">
      <div class="form-group">
        <div class="input-group authcode-container">
          <input id="cellphone" name="cellphone" class="form-control" type="text" maxlength="11" placeholder="手机号" />
          <span class="input-group-addon">
            <button id="btnSendVerify" name="unbindSendAuthCodeBtn" class="mobile-check" type="button">获取验证码</button>
          </span>
        </div>
        <p class="hint">
          (如未收到短信，请查看是否被拦截)
        </p>
      </div>
      <div class="form-group phoneCode">
        <input class="form-control data-rulerequrequired" maxlength="6" type="text" id="phoneCode" name="phoneCode" placeholder="验证码" />
      </div>
      <div class="form-group">
        <button id='checkPhoneCodeBtn' name="bindingSendAuthCodeBtn" class="btn btn-block btn-primary" type="button">下一步</button>
      </div>
    </form>
  </div>
  <div class="passwordUpdate">
    <h3 class="title">重置密码</h3>
    <form class="form-horizontal" id="passwordForm" name="passwordForm" method="get" action="">
      <div class="form-group">
        <label class="col-sm-2 control-label" for="newPassword">新的密码：</label>
        <div class="col-sm-6">
          <input class="form-control" maxlength="15" type="password" id="newPassword" name="newPassword" placeholder="新的密码" />
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label" for="confirmPassword">再次确认：</label>
        <div class="col-sm-6">
          <input class="form-control" maxlength="15" type="password" id="confirmPassword" name="confirmPassword" placeholder="确认密码" />
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-6">
          <button id="updataPasswordbtn" name="bindingSendAuthCodeBtn" class="btn btn-block btn-success" type="button">完成</button>
        </div>
      </div>
    </form>
  </div>
  <!--dialog(start)-->
  <div id="modalImgVerify" class="modal fade modal-img-verify" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body">
          <input id="inpVerify" name="inpVerify" class="inp-verify" type="text" maxlength="4" placeholder="验证码" />
          <a title="点击图片可刷新" id="refreshVerify" href="javascript:;">
            <img id="imgVerify" class="img-verify" width="100px" />
          </a>
          <div id="modalMsg" class="error">
            <!--提示错误-->
          </div>
        </div>
        <div class="modal-footer">
          <button id="btnCheckVerify" name='btnCheckVerify' type="button" class="btn btn-primary">提交</button>
        </div>
      </div>
    </div>
  </div>
  <!--dialog(end)-->
</div>
</script>

<script id="checkMobile-template" type="text/x-jsrender">
<!-- Modal -->
<div class="modal fade personal-info" id="checkMobile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div name='modalContent' class="modal-content controlStep1">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<div>
				<h4 class="modal-title settingStep1">身份验证</h4>
				<h4 class="modal-title settingStep2">立即绑定</h4>
				</div>
				<span id='errMessage'></span>
			</div>
			<div class="modal-body">
				<form id='checkForm' name="checkForm">
				<div class='childCheckMobile settingStep1'>
					<div class="authcode-container clearfix">
						<div>当前账号：</div>
						<div class="input-group">
			        <input class="form-control" type="text" name="mobile" disabled="disabled"  maxlength="11" placeholder="手机号" />
     					<span class="input-group-btn">
       					<button name="unbindSendAuthCodeBtn" id='unbindSendAuthCodeBtn' class="btn btn-default btn-default-grey" type="button">获取验证码</button>
     					</span>
  				 	</div>
					</div>
					<div class="clearfix">
						<div>验证码：</div>
						<div><input class="form-control" type="text" maxlength="4" name="unbindCode" placeholder="验证码"></div>
					</div>
				</div>

				<div class='childLiftingMobile settingStep2'>
					<div class="authcode-container clearfix">
						<div>新的账号：</div>
						<div class="input-group">
			      	<input class="form-control" type="text" name="cellphone"  maxlength="11" placeholder="手机号">
     					<span class="input-group-btn">
       					<button name="bindingSendAuthCodeBtn" id='bindingSendAuthCodeBtn' class="btn btn-default btn-default-grey" type="button">获取验证码</button>
     					</span>
  				  </div>
					</div>
					<div class="clearfix">
						<div>验证码：</div>
						<div><input class="form-control" type="text" name="bindingCode" maxlength="4" placeholder="验证码"></div>
					</div>
				</div>
				</form>
			</div>
			<div class="modal-footer">
				<button name="unbindBtn" menucode="registryEditDetail" value="Submit" class="btn btn-primary btn-block settingStep1" type="button">确&nbsp;认</button>
				<button name="bindingBtn" menucode="registryEditDetail" value="Submit" class="btn btn-primary btn-block settingStep2" type="button">绑&nbsp;定</button>
			</div>
		</div>
	</div>
</div>
</script>


