define(['util/sessionUtil'], function(sessionUtil) {
  var dataUtil = {
  };
  
  dataUtil.init = function(appCode) {
    this.KEY_APP_CONST = "app_const";
    this.KEY_LAST_LOGIN_ID = "last_login_id";
    this.KEY_CART = "cart_";
    this.KEY_SEARCH_CONDATION = "search_condation_" + appCode;
    this.KEY_LOGINVO = "data_user_loginvo";
  };

  dataUtil.set = function(key, data) {
    var local = dataUtil.get(key);
    local = $.extend(local || {}, data);
    var str = JSON.stringify(local);
    localStorage.setItem(key, str);
  };

  dataUtil.get = function(key) {
    var str = localStorage.getItem(key);
    if (str) {
      return JSON.parse(str);
    }
    return undefined;
  };
  
  // userType是给后台接口使用，是大写的，userPropType是给前台页面控制用是小写
  // 这里返回的是userPropType，是页面控制用的
  dataUtil.getUserPropType = function() {
    var para = this.get(this.KEY_APP_CONST);
    
    // TODO
    if (para && para.userPropType) {
      return para.userPropType;
    }
    
    return null;
  };
  
  dataUtil.clear = function(key) {
    localStorage.removeItem(key);
  };
  
  dataUtil.getCart = function() {
    var userInfo = sessionUtil.get(sessionUtil.KEY_USER_INFO);
    if (!userInfo) {
      return;
    }
    return this.get(this.KEY_CART + userInfo.userId) || {productList:[]};
  };
  
  dataUtil.setCart = function(cart) {
    var userInfo = sessionUtil.get(sessionUtil.KEY_USER_INFO);
    cart.userId = userInfo.userId;
    cart.updateDate = new Date();
    
    this.set(this.KEY_CART + userInfo.userId, cart);
  };
  dataUtil.clearCart = function() {
    var userInfo = sessionUtil.get(sessionUtil.KEY_USER_INFO);
    localStorage.removeItem(this.KEY_CART + userInfo.userId);
  };
  return dataUtil;
});