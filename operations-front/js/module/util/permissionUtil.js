define(['util/sessionUtil', 'util/domUtil'],
    function(sessionUtil, domUtil) {
  var Export = {};
  
  Export.hasPermission = function(menuPermission, permissions) {
    if (!menuPermission) {
      return true;
    }
    if (permissions.indexOf(menuPermission) != -1) {
      return true;
    }
    var prefix = this.getPermissionPrefix(menuPermission);
    if (permissions.indexOf(prefix + "*") != -1) {
      return true;
    }
    
    return false;
  };

  Export.getPermissionPrefix = function(permission) {
    var index = permission.indexOf("::");
    if (index != -1) {
      return permission.substring(0, index + 2);
    }
    
    return null;
  };
  
  Export.checkDomPermission = function($dom) {
    var userInfo = sessionUtil.get(sessionUtil.KEY_USER_INFO);
    if (!userInfo || !userInfo.permissions || userInfo.permissions.length == 0) {
      return null;
    }
    
    var permissions = userInfo.permissions;
    var $subdom = domUtil.getDomByAttr($dom, "permission");
    for (var k in $subdom) {
      if (!this.hasPermission(k, permissions)) {
        $subdom[k].dom.remove();
      }
    }
  };
  
  return Export;
});