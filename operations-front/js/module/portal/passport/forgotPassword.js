define(['core/eventbus', 'core/navigation', 'core/subheader',
        'core/base', 'core/breadcrumb', 'util/utils','util/sessionUtil', 'util/domUtil','util/dataUtil', 'util/codeUtil','widget/dialogAlert','widget/tooltipPrompt', 'widget/loadding'],
        function(eventBus, navigation, subHeader, Base, breadcrumb, utils,sessionUtil, domUtil,dataUtil,codeUtil,dialogAlert,tooltipPrompt,loadding) {


  var ForgotPassword = function() {
    this.bindInitEvent();
  };

  ForgotPassword.prototype = new Base();

  ForgotPassword.prototype.bindInitEvent = function() {
    var me = this;
    eventBus.bind('navigate', function (event, data) {
      if (data && data.menuCode == 'forgotPassword') {
        me.userType = data.userType;
        var para = {
            menu: me.navigation.getMenuByMenuCode(data.menuCode),
            btns: []
        };
        subHeader.refreshHeader(para);
        me._init();
        logger.trace('Product - ', data);
      }
    });

  };
  ForgotPassword.prototype.bindEvent = function(){
    var me = this;
    me.validator = $("#passwordForm").validate({
      rules: {
        newPassword: {
          required: true,
          rangelength:[5,15]
        },
        confirmPassword: {
          required: true,
          rangelength:[5,15],
          equalTo: "#newPassword"
        }
      },
      messages: {
        newPassword: {
          required: "请输入新密码",
          rangelength:"长度必须在5至15位之间"
        },
        confirmPassword: {
          required: "请输入确认密码",
          rangelength: "长度必须在5至15位之间",
          equalTo: "密码不一致"
        }
      },
      errorPlacement: function(error, element) {
        if ( element.is(":input[name=cellphone]") )
          error.insertAfter(element.parent().parent());
        else
          error.insertAfter(element.parent());
      }
    });

    var me = this;

    var userPropType = dataUtil.getUserPropType();
    var userType = codeUtil.mapUserType(userPropType);

    var cellphone;
    var inpVerify = $("#inpVerify");
    var modalMsg = $("#modalMsg");

    //刷新
    $('#refreshVerify').click(function(){
      me.refreshVerify();
      inpVerify.val("");
      modalMsg.text("");
    });

    //弹出验证码(start)
    $("#btnSendVerify").bind("click", function() {
      cellphone = $.trim($("#cellphone").val());
      if (!cellphone){
        dialogAlert.show({
          type : dialogAlert.TYPE_ERROR,
          title : "错误",
          content : "请输入手机号码"
        });
        return;
      }else{
        $("#modalImgVerify").modal('show');
        me.refreshVerify();
      }
    });
    //弹出验证码(end)
    //提交验证码(start)
    $("#btnCheckVerify").click(function(){
      var vcode = inpVerify.val();
      if(vcode ==""){
        modalMsg.text("请输入验证码");
      }else{
        utils.post('phoneCode/forverify', {cellphone:cellphone, checkPhone:false, vcode:vcode}).then(function(result){
          if(result.code == 200){
            modalMsg.text("");
            $("#modalImgVerify").modal('hide');
            me.createTimer("#btnSendVerify");
          }else{
            modalMsg.text(result.message);
            //一旦验证码错误，立即刷新
            me.refreshVerify();
            inpVerify.val("");
          }
        });
      }
    })
    //提交验证码(end)

    //下一步
    $("#checkPhoneCodeBtn").bind("click", function() {
      var para = {
        cellphone : cellphone,
        phoneCode : $('#phoneCode').val()
      };
      me.unbindPara = para;
      utils.post("phoneCode/check", para).then(function(result) {
        if(result.code=="200"){
          $('.checkPhoneCode').hide();
          $('.passwordUpdate').show();
        }else if(result.code=="1200"){
          dialogAlert.show({
            type : dialogAlert.TYPE_ERROR,
            title : "提交失败",
            content : "验证码错误，请重新输入！"
          });
        }
      });
    });


    $('#updataPasswordbtn').bind('click', function(){
      if(me.validator.form()){
        if (loadding.show()) {
          return;
        }
        var para = {};
        para.newPassword = $("#newPassword").val();
        para.cellphone = me.unbindPara.cellphone;
        para.phoneCode = me.unbindPara.phoneCode;
        para.userType = userType;
        utils.put("profile/forgotPassword", para).then(function(result) {
          if (result.code == "200") {
            loadding.hide();
            tooltipPrompt.show();
            me.navigation.go('login');
          } else {
            loadding.hide();
            dialogAlert.show({
              type : dialogAlert.TYPE_ERROR,
              title : "修改失败",
              content : "更新密码失败"
            });
          }
        });
      }
    });
  };

  ForgotPassword.prototype.beforeLoad = function() {
    var def = $.Deferred();
    def.resolve(sessionUtil.get(sessionUtil.KEY_USER_INFO));
    return def.promise();
  };
  ForgotPassword.prototype.afterLoad = function() {
    $('.passwordUpdate').hide();

    //找回密码介面头部的角色名
    $("#login-name img[data-userproptype=" + dataUtil.getUserPropType() + "]").addClass("active");
  };

  ForgotPassword.prototype.loadTemplate = function(data) {
    return utils.loadTemplate($('#content'), 'forgotPassword-template', 'portal/passport/forgotPassword.tpl', data);
  };

  ForgotPassword.prototype.hide = function() {
    this.clearTimer("btnSendVerify");
  };

  ForgotPassword.prototype.clearTimer = function(obj) {
    var countDown = $(obj);
    var me = this;
    if (me.timerHandle) {
      clearInterval(me.timerHandle);
      me.timerHandle = null;
      countDown.html('获取验证码');
      countDown.removeClass("inactive").removeAttr("disabled", "");
    }
  };

  ForgotPassword.prototype.createTimer = function(obj) {
    var countDown = $(obj);
    if (countDown.hasClass("inactive")) {
      return false;
    }
    var waitTime = 60;
    var me = this;
    countDown.addClass("inactive").attr("disabled", "");
    me.timerHandle = setInterval(function() {
      countDown.html('已经发送(' + waitTime + ')');
      waitTime --;
      if (waitTime < 0) {
        me.clearTimer("#btnSendVerify");
      }
    }, 1000);

    return true;
  };

  //刷新验证码
  ForgotPassword.prototype.refreshVerify = function(img) {
    var imgVerify = img || $("#imgVerify");
    imgVerify.attr(
      'src', utils.setting.SERVER_URI + "image/vcode.png?date=" + new Date().getTime()
    );
  };

  return new ForgotPassword();
});