define([ 'util/requestUtil', 'core/base',
		'util/sessionUtil', 'util/domUtil', 'portal/main/config' ], function(
		requestUtil, Base, sessionUtil, domUtil, config) {
	var InfoMyinfo = function() {
	};

	InfoMyinfo.prototype = new Base();
	var req;
	// 页面初始化
	InfoMyinfo.prototype.create = function() {
		this.renderMainContent("tpl_info_myinfo");
		this.bindInitEvent();
	};

	InfoMyinfo.prototype.bindInitEvent = function() {
	};
	
	// 重新显示  绑定数据后绑定点击事件（暂时这么做）
	InfoMyinfo.prototype.show = function() {
		var me = this;
		me.renderPage();
	};
	
	InfoMyinfo.prototype.renderPage = function() {
		var me = this;
		this.clearList();
		var data;
		var isSet;
		var isValidate;
		var weichatIsBind;
		var qqIsBind;
		var bindUrl = "user/message/bind";
		requestUtil.get(bindUrl).then(function(bindresult){
			//TODO 
			isSet = "未设定";
			if (bindresult.data.loginName) {
				isSet = "已设定";
			}
			isValidate = "未验证";
			if (bindresult.data.cellphone) {
				isValidate = "已验证";
			}
			weichatIsBind = bindresult.data.wechatName || "未绑定";
			qqIsBind = bindresult.data.qqNumber || "未绑定";
			
			var url = "user/info";
			requestUtil.get(url).then(function(result){
				if (result.success) {
					req = result.data;
					var classInfoList = result.data.classInfoList;
					var subjectVos = result.data.subjectVos;
					var schoolName;
					var classNames = '';
					if(classInfoList){
						schoolName = classInfoList[0].schoolName || ''
						for(var i in classInfoList){
							if(classInfoList[i].teacherClassRelationType.code == 1010 || classInfoList[i].teacherClassRelationType.code == 1020){
								classNames += classInfoList[i].className + " ";
							}
						}
					} 
					
					data = {
							 realName:req.realName,portrait:req.portrait,gender:req.gender.text,
							 userType:req.userType.text,schoolName:schoolName,
							 classNames:classNames,email:req.email,isValidate:isValidate,
						     cellphone:req.cellphone,isSet:isSet,qqIsBind:qqIsBind,weichatIsBind:weichatIsBind	
						    };
					var content = me.renderTpl("tpl_info_myinfo_item", data);
					me.find(".personinfo-con ul").append(content);
					me.popupWindow();
					me.changePassword();
					me.oldCellphoneAuth();
					me.newCellphoneAuth();
					me.sendAuthCode();
				}
				
			});
			
		});
	};
	
	//清空数据
	InfoMyinfo.prototype.clearList = function () {
        var me = this;
        me.find(".personinfo-con ul").empty();
    };

	// 页面隐藏
	InfoMyinfo.prototype.hide = function() {
		var me = this;
		me.find("#gray").hide();
		me.find("#popup,#popup2,#popup3").hide();//查找ID为popup和popup2的DIV hide()隐藏
	};
	
	// 页面弹窗
	InfoMyinfo.prototype.popupWindow = function() {
		var me = this;
		me.find('a.tc').click(function(){
			me.find("#gray").show();
			me.find("#popup3").show();//查找ID为popup的DIV show()显示#gray
			me.tc_center();
			me.find('input[name="oldCellPhone"]').val(req.cellphone);
		});
		
		me.find('a.changepass').click(function(){
			me.find(':password').val('');//清空上次记录
			me.find("#gray").show();
			me.find("#popup2").show();//查找ID为popup2的DIV show()显示#gray
			me.tc_center();
		});
		
		me.find('a.close-popup,#gray,.disabled-btn').click(function(){
			me.find("#gray").hide();
			me.find("#popup,#popup2,#popup3").hide();//查找ID为popup和popup2的DIV hide()隐藏
		});
		
	};
	
	InfoMyinfo.prototype.tc_center = function() {
		var _top=($(window).height()-$(".popup").height())/2;
		var _left=($(window).width()-$(".popup").width())/2;
		$(".popup").css({top:_top,left:_left});
	};

	// 修改密码
	InfoMyinfo.prototype.changePassword = function() {
		var me = this;
		me.find("a[name='pwdConfirm']").click(function(){
			var password = me.find('input[name="password"]').val();
			var newPassword = me.find('input[name="newPassword"]').val();
			var newPasswordConfirm = me.find('input[name="newPasswordConfirm"]').val();
			
			if(!password || !newPassword || !newPasswordConfirm){
				alert("密码不能为空");
				return;
			}
			if(newPassword != newPasswordConfirm){
				alert("请输入相同的新密码");
				return;
			}
			if(newPassword == password){
				alert("新旧密码不能相同");
				return;
			}
			var putdata = {
				password : password,
				newPassword : newPassword	
			};
			var url = "user/password";
			requestUtil.put(url,putdata).then(function(result){
				if (result.success) {
					me.moveTo('info_myinfo');
				}
			});
			me.hide();
		});
	};
	
	// 旧手机验证
	InfoMyinfo.prototype.oldCellphoneAuth = function() {
		var me = this;
		//点击保存
		me.find('input[name="oldAuthCode"]').val('');
		me.find("a[name='oldPhoneConfirm']").click(function(){
			var oldCellPhone = me.find('input[name="oldCellPhone"]').val();
			var oldAuthCode = me.find('input[name="oldAuthCode"]').val();
			
			if(!oldAuthCode){
				alert("请输入验证码");
				return;
			}
			//TODO 获取验证码并比对
			var url = "auth/unbind/phone";
			var putdata = {
				cellphone : oldCellPhone,
				authCode : oldAuthCode,
			};
			requestUtil.get(url,putdata).then(function(result){
				if (result.success) {
					alert('验证成功');
					me.hide();
					me.find("#gray").show();
					me.find("#popup").show();//查找ID为popup的DIV show()显示#gray
					me.tc_center();
				}
			});
		});
	};
	
	// 新手机验证
	InfoMyinfo.prototype.newCellphoneAuth = function() {
		var me = this;
		//点击保存
		me.find("a[name='phoneConfirm']").click(function(){
			var cellPhone = me.find('input[name="cellPhone"]').val();
			var authCode = me.find('input[name="authCode"]').val();
			
			if(!cellPhone || !authCode){
				alert("请输入手机号和验证码");
				return;
			}
			//TODO 获取验证码并比对
			var data = {
		    	cellphone : cellPhone,
		    	authCode : authCode
			}
			requestUtil.get("auth/bind/phone", data).then(function(result){
				if (result.success) {
					//TODO 这里待斟酌，先下班。。。
					me.hide();
					me.tc_center();
					me.show();
					me.moveTo('info_myinfo');
				}
			});
		});
	};
	
	// 发送并获取验证码
	InfoMyinfo.prototype.sendAuthCode = function() {
		var me = this;
		var timer="";
		var nums=60;
		var validCode=true;//定义该变量是为了处理后面的重复点击事件
		me.find('a[name="oldYzm"]').click(function(){
			var cellphone = me.find('input[name="oldCellPhone"]').val();
			if(cellphone.length != 11){
				alert("请输入正确的手机号");
				return;
			}
			//TODO 发送验证码
			requestUtil.get("phone/code/" + cellphone + "?template=UNBIND_PHONE_AUTH").then(function(result){
				if (result.success) {
					alert('发送成功');
				}
			});
			var code=$(this);
			if(validCode){
				validCode=false;
				timer=setInterval(function(){
					if(nums>0){
						nums--;
						code.text(nums+"秒");
						code.addClass("gray-bg");
					}
					else{
						clearInterval(timer);
						nums=60;//重置回去
						validCode=true;
						code.removeClass("gray-bg");
						code.text("重新发送");
			}
				},1000)
			}
		});
		
		me.find('a[name="yzm"]').click(function(){
			var cellphone = me.find('input[name="cellPhone"]').val();
			if(cellphone.length != 11){
				alert("请输入正确的手机号");
				return;
			}
			//TODO 发送验证码
			requestUtil.get("phone/code/" + cellphone + "?template=BIND_PHONE_AUTH").then(function(result){
				if (result.success) {
					alert('发送成功');
				}
			});
			var code=$(this);
			if(validCode){
				validCode=false;
				timer=setInterval(function(){
					if(nums>0){
						nums--;
						code.text(nums+"秒");
						code.addClass("gray-bg");
					}
					else{
						clearInterval(timer);
						nums=60;//重置回去
						validCode=true;
						code.removeClass("gray-bg");
						code.text("重新发送");
			}
				},1000)
			}
		});
	};
	
	return new InfoMyinfo();
})