<script id="registryDetailPreview-template" type="text/x-jsrender">
<div id="registryDetailPreview">
	<div class="widget-box">
		<div id='registryNotReviewed' class="widget-status registryNotReviewed">
			<div class='row'>
				<div class='col-sm-1 fa fa-check-circle'></div>
				<div class='col-sm-10'>
					<span class=''>提交成功，等待平台审核！</span>
				</div>
			</div>
			<div class='row'>
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<span class='previewColor1'>你还可以拨打我们的热线电话：</span> <span class='previewColor2'>020-37858486</span> <span class='previewColor1'>，或发送邮件到：</span> <span class='previewColor2'>service@micarshow.com</span>
				</div>
			</div>
			<div class="row">
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<button name="registryDetailBtnPreview" menuCode='registryEditDetail' value="Submit" class="btn btn btn-primary btn-block registryDetailBtnPreview" type="button"
						style="background: #ed0c01; color: white;">修改并重新提交</button>
				</div>
			</div>
		</div>

		<div id='registryReviewedSuccess' class="widget-status registryReviewedSuccess">
			<div class='row'>
				<div class='col-sm-1 fa fa-check-circle'></div>
				<div class='col-sm-10'>
					<span class=''>恭喜您，资料已审核通过！</span>
				</div>
			</div>
			<div class='row'>
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<span class='previewColor1'>你还可以拨打我们的热线电话：</span> <span class='previewColor2'>020-37858486</span> <span class='previewColor1'>，或发送邮件到：</span> <span class='previewColor2'>service@micarshow.com</span>
				</div>
			</div>
			<div class="row">
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<button name="registryDetailBtnPreview" menuCode='manufactureWorkbench' value="Submit" class="btn btn btn-primary btn-block registryDetailBtnPreview" type="button"
						style="background: #ed0c01; color: white;">进入工作台</button>
				</div>
			</div>

		</div>

		<div id='registryReviewedFalse' class="widget-status registryReviewedFalse">
			<div class='row'>
				<div class='col-sm-1 fa fa-times-circle'></div>
				<div class='col-sm-10'>
					<span class=''>很抱歉，您没有通过平台审核！</span>
				</div>
			</div>
			<div class='row'>
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<span id='auditFailure' class='previewColor1'></span>
				</div>
			</div>
			<div class='row'>
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<span class='previewColor1'>你还可以拨打我们的热线电话：</span> <span class='previewColor2'>020-37858486</span> <span class='previewColor1'>，或发送邮件到：</span> <span class='previewColor2'>service@micarshow.com</span>
				</div>
			</div>
			<div class="row">
				<div class='col-sm-1'></div>
				<div class='col-sm-10'>
					<button name="registryDetailBtnPreview" value="Submit" menuCode='registryEditDetail' class="btn btn btn-primary btn-block registryDetailBtnPreview" type="button"
						style="background: #ed0c01; color: white;">修改并重新提交</button>
				</div>
			</div>
		</div>

		<div id='content' class="widget-content">
			<h4>入驻信息</h4>
			<div class='message'>
				{{if serviceProviderName}}
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">所属服务商:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:serviceProviderName}}</span>
					</div>
				</div>
				{{/if}}
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">公司名称:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:companyName}}</span>
					</div>
				</div>

				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">登录手机:</span>
					</div>
					<div>
						<span id='cellphone' class='col-sm-7 registryDetailPreviewValue'>{{:~formatUtil.cellPhoneFormat(accountPhone)}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">法人代表:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:representative}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">法人代表身份证:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:~formatUtil.bankNoFormat(representativeCardNo)}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">公司地址:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:(province) ? province.name : ""}}{{:(city) ? city.name : ""}}{{:(area) ? area.name : ""}}{{:address}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">公司电话:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{if phone}}<span class="jianju">{{:phone}}</span>{{/if}}<span>{{:cellphone}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">业务联系人:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{:contact}}</span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">业务联系人电话:</span>
					</div>
					<div>
						<span class='col-sm-7 registryDetailPreviewValue'>{{if contactPhone}}<span class="jianju">{{:contactPhone}}</span>{{/if}}<span>{{:contactCellphone}}</span></span>
					</div>
				</div>
				<div class='row rowSpace'>
					<div class='col-sm-3 registryDetailPreviewName'>
						<span class="">营业执照:</span>
					</div>
					<div id='businessContacts'>
						{{for licensePictures}}
						<div id='' class="col-sm-2">
							<img class='registryImg' src="{{:~formatUtil.prdImgUrl(pictureUri)}}" />
							<input type="hidden" value="{{:~formatUtil.prdImgUrl(pictureUri)}}" />
						</div>
						{{/for}}
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</script>
