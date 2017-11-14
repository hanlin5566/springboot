define(['util/cacheUtil'],
    function(cacheUtil) {
  var sessionUtil = {
      KEY_USER_INFO : "data_user_info",
      KEY_PRIVILEGE : "data_user_privilege"
  };

  sessionUtil.set = function(key, data) {
    var str = JSON.stringify(data);
    sessionStorage.setItem(key, str);
    cacheUtil.removeKey(key);
  };

  sessionUtil.get = function(key) {
    return cacheUtil.localCacheGet(key, function() {
      var str = sessionStorage.getItem(key);
      if (str) {
        return JSON.parse(str);
      }
      return undefined;
    });
  };

  sessionUtil.clear = function(key) {
    sessionStorage.removeItem(key);
  };
  
  return sessionUtil;
});