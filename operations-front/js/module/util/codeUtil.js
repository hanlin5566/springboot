define(['util/cacheUtil'], function(cacheUtil) {
  var Export = {
      STATUS_PRODUCT : 20,
      STATUS_ORDER : 10,
      PRODUCT_CODE : {
        'PENDING' : {code: 'PENDING', text: '待审核'},
        'ONLINE' : {code: 'ONLINE', text: '已上架'},
        'OFFLINE' : {code: 'OFFLINE', text: '已下架'},
        'AUDITED' : {code: 'AUDITED', text: '已审核'},
        'DELIVERING' : {code: 'DELIVERING', text: '待发货'},
        'DELIVERED': {code: 'DELIVERED', text: '已发货'},
        'REJECTED' : {code: 'REJECTED', text: '审核不通过'}
      },
      ACCOUNT_CODE : {
        'NORMAL' : {code: 'NORMAL', text: '正常'},
        'CLOSED' : {code: 'CLOSED', text: '冻结'}
      },
      USER_PROP_TYPE : ['manufacturer',
                        'serviceProvider', 
                        'micarshow',
                        'terminalstore']
  };
  
  var optCache = {};

  function resolveGetOptionList(selectObj, selectValue, optHtml, hasBlank, def) {
    if (hasBlank) {
      optHtml = "<option value=''>--请选择--</option>" + optHtml;
    }
    selectObj.html(optHtml);
    selectValue && selectObj.val(selectValue);
    def.resolve(selectValue);
  }
  
  Export.getConstCodeList = function(type) {
    return cacheUtil.cacheGet("code", {type: type});
  };
  
  Export.getConstOptionList = function(selectObj, type, hasBlank, selectValue) {
    var def = $.Deferred();
    if (optCache[type]) {
      resolveGetOptionList(selectObj, selectValue, optCache[type], hasBlank, def);
    } else {
      this.getCodeList(type).then(function(result) {
        var html = [];
        if (result) {
          $(result).each(function() {
            html.push("<option value='" + this.code + "'>" + this.text + "</option>");
          });
        }
        optCache[type] = html.join("");
        resolveGetOptionList(selectObj, selectValue, optCache[type], hasBlank, def);
      });
    }
    
    return def.promise();
  };
  
  Export.getRatioSetting = function() {
    var def = $.Deferred();
    var ratio = {
        realRatio : {
          serviceProvider : 25,
          platform : 3,
          terminalStore : 75  
        },
        formatRatio : {
          serviceProvider : 250000,
          platform : 30000,
          terminalStore : 750000
        }
    };
    
    def.resolve(ratio);
    return def.promise();
  };
  
  Export.getArea = function(id) {
    return cacheUtil.cacheGet("area" + (id ? ("/" + id) : ""));
  };
  
  Export.getCarBrand = function() {
    return cacheUtil.cacheGet("carBrand");
  };
  
  Export.getCarBrandSeries = function(carBrandId) {
    return cacheUtil.cacheGet("carBrand/series", {carBrandId: carBrandId});
  };
  
  Export.getCarBrandModel = function(carSeriesId) {
    return cacheUtil.cacheGet("carBrand/model", {carSeriesId: carSeriesId});
  };

  Export.mapUserType = function(userPropType) {
    var userType = null;
    if (userPropType == 'serviceProvider') {
      userType = 'SERVICE_PROVIDER';
    } else if (userPropType == "manufacturer") {
      userType = "MANUFACTURER";
    } else if (userPropType == "micarshow") {
      userType = "EMPLOYEE";
    } else if (userPropType == "terminalstore") {
      userType = "TERMINAL_STORE";
    }
    
    return userType;
  };
  
  Export.mapUserPropType = function(userType) {
    var userPropType = null;
    if (userType == 'SERVICE_PROVIDER') {
      userPropType = 'serviceProvider';
    } else if (userType == "MANUFACTURER") {
      userPropType = "manufacturer";
    } else if (userType == "EMPLOYEE") {
      userPropType = "micarshow";
    } else if (userType == "TERMINAL_STORE") {
      userPropType = "terminalstore";
    }
    
    return userPropType;
  };
  
  
  Export.mapAppPageByUserPropType = function(userPropType) {
    var href = "";
    if (userPropType == 'serviceProvider') {
      href = "serviceProvider.html";
    } else if (userPropType == "manufacturer") {
      href = "manufacturer.html";
    } else if (userPropType == "micarshow") {
      href = "micarshow.html";
    } else if (userPropType == "terminalstore") {
      href = "terminalStore.html";
    }
    
    return href;
  };
  Export.mapUrlPrefix = function(userPropType) {
    var userType = null;
    if (userPropType == 'serviceProvider') {
      userType = 'serviceProvider';
    } else if (userPropType == "manufacturer") {
      userType = "manufacturer";
    } else if (userPropType == "micarshow") {
      userType = "micarshow";
    } else if (userPropType == "terminalstore") {
      userType = "terminalStore";
    }
    
    return userType;
  };
  return Export;
});