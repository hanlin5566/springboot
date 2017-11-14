<script id="registryEditDetail-template" type="text/x-jsrender">
<div id="registryEditDetail" class="widget-box">
	<div class="widget-content">
		<div id="tab1" class="tab">
			<form class="form-horizontal" id="registerDetailform" name="registerDetailform" method="get" action="">
				<h2 class="heading">请填写审核资料</h2>
				<div class='row welcome-div'>
					<span class="welcome">欢迎您</span> <span class='companyName' name='companyName'>{{:companyName}}</span>
				</div>
				<h5 class="registryEditDetailError" id="registryEditDetailError"></h5>
				<div class="line"></div>
				{{if getWay == 'explorer'}}
				<div class="form-group">
					<label class="col-sm-3 control-label" for="companyName"><span class="required" aria-required="true"></span>公司名称：</label>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" id="companyName" name="companyName" placeholder="公司名称" maxlength="30" value={{:companyName}}>
					</div>
					<div name="msg"></div>
				</div>
				{{/if}}
				<div class="form-group">
					<label class="col-sm-3 control-label" for="representative"><span class="required" aria-required="true"></span>法人代表：</label>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" id="representative" name="representative" placeholder="法人代表" maxlength="30" value={{:representative}}>
					</div>
					<div name="msg"></div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label data-rulerequrequired" for="legalIdCard"><span class="required" aria-required="true"></span>法人代表身份证：</label>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" id='representativeCardNo' name="representativeCardNo" placeholder="法人代表身份证" maxlength="18" value={{:representativeCardNo}}>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label data-rulerequrequired" for="contractNo"><span aria-required="true"></span>合同编号：</label>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" id='contractNo' name="contractNo" placeholder="合同编号" maxlength="20" value={{:contractNo}}>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"><span class="required" aria-required="true"></span>公司地址：</label>
					<div class="col-sm-9">
						<button type="button" name="areaBtn" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="registryEdit_selectArea">
							<input type="hidden" name='provinceId' value='{{:(province) ? province.areaId : ""}}' />
							<input type="hidden" name='cityId' value='{{:(city) ? city.areaId : ""}}' />
							<input type="hidden" name='areaId' value='{{:(area) ? area.areaId : ""}}' />
							<input type="hidden" name='provinceName' value='{{:(province) ? province.name : ""}}' />
							<input type="hidden" name='cityName' value='{{:(city) ? city.name : ""}}' />
							<input type="hidden" name='areaName' value='{{:(area) ? area.name : ""}}' />
							<span name="areaNameBtn">{{:(areaBtnName) ? areaBtnName : "请选择地区"}}</span> <span class="caret"></span>
						</button>
						<div class="error-container-area"> <span class="error">请选择公司地址</span></div>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<textarea class="form-control data-rulerequrequired" type="text" name="address" placeholder="请输入详细地址" maxlength="50">{{:address}}</textarea>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label" for=""><span class="required" aria-required="true"></span>公司电话：</label>
					<div class="col-sm-2">
						<input class="form-control data-rulerequrequired" type="text" id='areaCode' name="areaCode"  placeholder="区号" maxlength="4" value={{:areaCode}}>
					</div>
					<div class='col-sm-4'>
						<input class="form-control data-rulerequrequired" type="text" id='companyTelephone' name="companyTelephone"  maxlength="9" placeholder="电话号码"
							value={{:companyTelephone}}>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<span>或</span><span class='data-rulerequrequired'>（注：电话及手机至少有一项不能为空）</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" id='cellphone' name="cellphone"  placeholder="手机号码" maxlength="11"
							value={{:cellphone}}>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="businessContacts"><span class="required" aria-required="true"></span>业务联系人：</label>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text" name="contact" placeholder="业务联系人" maxlength="6" value={{:contact}}>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="businessAreaCode"><span class="required" aria-required="true"></span>业务联系电话：</label>
					<div class="col-sm-2">
						<input class="form-control" type="text" id="businessAreaCode" name="businessAreaCode"  placeholder="区号" maxlength="4" value={{:businessAreaCode}}>
					</div>
					<div class='col-sm-4'>
						<input class="form-control data-rulerequrequired" type="text" id='businessTelephone' name="businessTelephone"  placeholder="电话号码" maxlength="9"
							value={{:businessTelephone}}>
					</div>

				</div>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<span>或</span><span class=''>（注：电话及手机至少有一项不能为空）</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3"></div>
					<div class="col-sm-6">
						<input class="form-control data-rulerequrequired" type="text"  id='contactCellphone' name="contactCellphone" maxlength="11" placeholder="手机号码"
							value={{:contactCellphone}}>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="businessContacts"><span class="required" aria-required="true"></span>营业执照：</label>
					<div class="col-sm-9">
						<div id='businessContacts' class="businessLicense">
						{{if licensePictures}}
							{{for licensePictures}}
								<div>
									<i class="fa fa-times"></i>
									<img src="{{:~formatUtil.photoImgUrl(pictureUri)}}">
									<input type="hidden" value="{{:pictureUri}}" />
								</div>
							{{/for}}
						{{/if}}
						</div>
						<div class="error-container"> <span class="error">请上传营业执照</span></div>
						<div class="upload-license">
							<div id="registry_license_upload"></div>
							<p>
								请上传彩色红色印章图片，图片格式*png，*jpg，*jpeg，*gif，图片大小不能超过2MB
							</p>
						</div>
					</div>
				</div>
				<div class="line"></div>
				<div class="button">
					<button id="registerEditDetailBtn" value="Submit" class="btn btn btn-primary btn-block" type="button" style="background: #1C95E5; color: white;">提交审核</button>
				</div>
			</form>
		</div>
	</div>
</div>
</script>
