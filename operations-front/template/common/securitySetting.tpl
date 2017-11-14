<script id="securitySetting-template" type="text/x-jsrender">

<div class='' id='securitySetting'>
	<fieldset>
		<legend>账号信息</legend>
		<div class='accountMessage'>
			<div class='row rowSpan'>
				<div class='account'>
					<span>当前账号:</span>
				</div>
				<div class='account'>
				<span id='accountMobile' class='accountMobile'>{{:cellphone}}</span>
				</div>
				<div class='account'>
					<button id="liftingMobileBtn" menucode="registryEditDetail" value="Submit" class="btn btn-primary btn-block" type="button" style="background:#1C95E5; color: white;width: 80px;margin-top: 11px;">修改</button>			
				</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>密码修改</legend>
		  <div class='passwordUpdate'>
			<div class='passwordUpdateDiv'>
      		<form class="form-horizontal" id="passwordForm" name="passwordForm" method="get" action="">
                <div class="">
                  <span class="" for="">旧的密码：</span>
                  <div class="">
                    <input class="form-control data-rulerequrequired" maxlength="15" type="password" id="oldPassword" name="oldPassword" placeholder="旧的密码">
                  </div>
                </div>
				<div class="">
                  <span class="" for="">新的密码：</span>
                  <div>
                    <input class="form-control data-rulerequrequired" maxlength="15" type="password" id="newPassword" name="newPassword" placeholder="新的密码">
                  </div>
                </div>
				<div>
                  <span for="">再次确认：</span>
                  <div>
                    <input class="form-control data-rulerequrequired" maxlength="15" type="password" id="confirmPassword" name="confirmPassword" placeholder="确认密码">
                  </div>
                </div>
				<div class="passwordBtns">
                  	<button name='passwordBtn' value="Submit" class="btn btn-primary btn-block passwordBtn" type="button">修改</button>			
                </div>
            </form> 
			</div>
        </div>     
	</fieldset>
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
			      		<input class="form-control" type="text" name="mobile" disabled="disabled"  maxlength="11" placeholder="手机号">
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


