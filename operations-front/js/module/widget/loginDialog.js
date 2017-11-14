define(['core/navigation', 'core/base', 'util/utils', 'util/codeUtil',
        'util/domUtil', 'util/dataUtil', 'widget/loadding', 'portal/passport/loginLogic'],
    function(navigation, Base, utils, codeUtil, domUtil, dataUtil, loadding, loginLogic) {
  
  var inited = false;
  
  var Export = function() {
  };
  
  Export.prototype._init = function() {
    loadding.hide();
    var me = this;
    if (!inited) {
      me.loadTemplate()
      .then(function(html) {
        me.template = html;
        me.bindEvent();
        me.afterLoad();
        me.inited = true;
      });
    } else {
      me.afterLoad();
    }
  };
  
  Export.prototype.loadTemplate = function(data) {
    return utils.loadTemplate(null, 'login-timeout-dialog-template', 'widget/loginDialog.tpl',data || {})
    .then(function(html) {
      $("#widget-container").append(html);
      return $.Deferred().resolve(html).promise();
    });
  };

  Export.prototype.bindEvent = function() {
    var $dlgDom = domUtil.getDomByName(this.template);
    var validator = $dlgDom.loginform.dom.validate({
      rules: {
        userName: {
          required:true,
          rangelength:[11,11]
        },
        password: {
          required: true,
          rangelength:[5,15]
        },
        inpVerify: {
		  required:true,
		  rangelength:[4,4]
		}
      },
      messages: {
        userName: {
          required:"请输入用户名",
          rangelength:"长度必须为11位"
        },
        password:{
          required:"请输入密码",
          rangelength:"长度必须在5至15位之间"
        },
			  inpVerify: {
				  required:"请输入验证码",
				  rangelength:"长度必须4位"
			  }
      },
      errorPlacement: function(error, element) { 
        element.before(error);
      }
    });
    //刷新图形验证码
    $('#refreshVerify').click(function(){
      $(this).find(".img-verify").attr(
        'src', utils.setting.SERVER_URI + "image/vcode.png?date=" + new Date().getTime()
      );
      $("#inpVerify").val("");
      $("#inpVerify-error").text("");
      //alert('ok');
    })
    $dlgDom.ok.dom.bind("click", function() {
      if (validator.form()) {
        // 拼接参数
        var para = {};
        para.cellphone = $dlgDom.userName.val();
        para.password = $dlgDom.password.val();
        para.verify = $('#inpVerify').val();
        loginLogic.authLogin(para)
        .then(function(result) {
          if (!result.result) {
            if (result.page) {
              $("#login-timeout-dlg")
              .one('hidden.bs.modal', function() {
                me.navigation.go(result.page);
              });
            }
            
            if (result.msg) {
              $("#loginError").html(result.msg);
              //刷新图形验证码
              $("#refreshVerify .img-verify").attr(
                'src', utils.setting.SERVER_URI + "image/vcode.png?date=" + new Date().getTime()
              );
              $("#inpVerify").val("");
              $("#inpVerify-error").text("");
            }
          }
          $("#login-timeout-dlg").modal('hide');
        });
      }
    });
    
    $dlgDom.cancel.dom.bind("click", function() {
      $("#login-timeout-dlg").modal('hide');
    });
  };
  
  Export.prototype.afterLoad = function() {
    var userPropType = dataUtil.getUserPropType();
    if (userPropType) {
      if (!this.template.is(":visible")) {
        var $dlgDom = domUtil.getDomByName(this.template);
        $dlgDom.loginform.dom[0].reset();
        $dlgDom.loginform.dom.removeClass(codeUtil.USER_PROP_TYPE.join(" "));
        $dlgDom.loginform.dom.addClass(userPropType);

        $('.form-signin-heading').hide();
        $('.h-' + userPropType.toLowerCase()).show();

        $("#login-timeout-dlg").modal();
      }
    } else {
      window.location.href = utils.setting.LOGIN_URI;
    }
    $("#refreshVerify .img-verify").attr(
      'src', utils.setting.SERVER_URI + "image/vcode.png?date=" + new Date().getTime()
    );
  };
  
  return new Export();
});