define(['util/requestUtil', 'core/base', 'util/sessionUtil', 'util/domUtil',
        'portal/main/config', 'widget/dropDown', 'widget/table', 'bootstrapTable', 'icheck'],
    function (requestUtil, Base, sessionUtil, domUtil, config, dropDown, Table) {
        var Login = function () {
        };
        var homeworkId = '';

        Login.prototype = new Base();

        Login.prototype.create = function () {
            var me = this;
            this.renderMainContent("tpl_login");
            
            me.find(".login-btn").on("click", function() {
            	var para = {
            			cellphone: me.find(".cellphone").val(),
            			password: me.find(".password").val()
            	};
            	
            	if (!para.cellphone || !para.password) {
            		alert("没输入登录不了");
            		return;
            	}
            	
            	requestUtil.post("auth/login", para)
            	.then(function() {
            		location.href = "./main.html";
            	});
            });
            
        };

        // 重新显示
        Login.prototype.show = function () {
            var me = this;
            me.renderPage();
        };

        Login.prototype.renderPage = function () {
            var me = this;
        };

        // 页面隐藏
        Login.prototype.hide = function () {
        };

        return new Login();
    });