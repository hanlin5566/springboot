define(['util/utils', 'util/sessionUtil', 'util/dataUtil', 'util/codeUtil'],
  function(utils, sessionUtil, dataUtil, codeUtil){
  
  var LoginLogic = {};
  
  /**
   * 获取帐户信息
   */ 
  LoginLogic.getAccountinfo = function() {
    var userPropType = dataUtil.getUserPropType();
    if (!userPropType) {
      return $.Deferred().resolve({result:false}).promise();
    }
    return utils.get('profile')
    .then(function(result) {
      if (result && result.code == "200") {
        sessionUtil.set(sessionUtil.KEY_USER_INFO, result.data); 

        var userStatus = result.data.userStatus.name;
        if (result.data.passwordStatus && result.data.passwordStatus.name == "FORCE_UPDATE") {
          return {result: false, page:"securitySetting"};
        } 
        
        if (userStatus == "CLOSED") {
          return {result: false, msg: "该帐号已冻结"};
        } else if (userStatus == "NORMAL") {
          var auditStatus = null;
          if (result.data.company && result.data.company.auditStatus) {
            auditStatus = result.data.company.auditStatus.name;
          }
          
          // 米卡会没有auditStatus
          if (auditStatus == null) {
            var userPropType = dataUtil.getUserPropType();
            if (userPropType == 'micarshow' || userPropType == 'terminalstore' || userPropType == 'serviceProvider' || userPropType == 'manufacturer') {
              return {result: true};
            } else {
              return {result: false, msg:'获取用户信息失败'};
            }
          } else if (auditStatus == 'APPROVED') {
            return {result: true};
          } else if (auditStatus == 'UNKNOWN') {
            return {result: false, page:"registryEditDetail"};
          } else {
            return {result: false, page:"registryDetailPreview"};
          }
        }
      }else{
        return {result: false, msg:'获取用户信息失败'};
      }
    });
  };
  
  /**
   * 登录验证
   * 
   * @param $errEle
   * @param para
   * @returns
   */
  LoginLogic.authLogin = function(para) {
    me = this;
    var userPropType = dataUtil.getUserPropType();
    para.userType = codeUtil.mapUserType(userPropType);
    return utils.post('auth', para, true).then(function(result) {
      if (result.code == "200") {
        dataUtil.set(dataUtil.KEY_LOGINVO, result.data); 
        return me.getAccountinfo(); 
      } else {
        return {result: false, msg: '登录失败，请确认信息后重新登录'};
      }
    });
  };
  
  return LoginLogic;
});
