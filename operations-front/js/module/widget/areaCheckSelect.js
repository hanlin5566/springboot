define(['util/utils', 'util/codeUtil', 'util/domUtil'],
    function(utils, codeUtil, domUtil) {

  var Export = {
    setcallback: null,
    getCallback: null,
    itemcallback: null,
    level: "area",
    param: {},
    template: null,
    activeTemplate: null,
    inited: false
  };
  
  var sort_no = {"province": 1, "city": 2, "area": 3};
  var ctrlIndex = 0;
  
  Export.getUId = function() {
    return "logistic-area-select-" + (++ctrlIndex);
  };

  /**
   * 初始化，加载popover template、省市区模板
   * @returns
   */
  Export.init = function() {
    if (this.inited) {
      return $.Deferred().resolve().promise();
    }
    var me = this;
    
    // 建立一个空div，保存template的结果
    return utils.loadTemplate(null, 'area-check-select-template', 'widget/areaCheckSelect.tpl')
    .then(function(html) {
      me.template = html;

      var pos = me.$owner.position();
      var width = me.$owner.width();
      me.template.find(".arrow").css("left", (pos.left + width/2 + 11) + "px"); // bootstrap向左移动了11px
      
      // 因为bootstrap的tab直接使用id做关联，无法实现同页面多个控件情况，所以加上命名空间
      var uid = me.getUId();
      me.activeTemplate = utils.loadTemplateLocal(null, 'area-check-select-content-template', {uid:uid});
      me.inited = true;
    });
  };
  
  /**
   * 从父元素获取初始参数、并初始化tab显示
   * 
   */
  Export.getParam = function() {
    var param = (this.getCallback && this.getCallback()) || {};
    param.type = "area";
    
    param.provinceId = parseInt(param.provinceId) || 0;
    param.cityId = parseInt(param.cityId) || 0;
    param.areaId = parseInt(param.areaId) || 0;
    
    if (!param.provinceId || this.level == "province") {
      param.type = "province";
    } else if (!param.cityId) {
      param.type = "province";
    } else if (!param.areaId) {
      param.type = "city";
    }
    
    if (sort_no[param.type] > sort_no[this.level]) {
      param.type = this.level;
    }
    
    // 根据level设置那些区域不可以选择和显示
    var me = this;
    switch(this.level) {
    case "province" :
      me.activeTemplate.find("#tabcontent-area-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-city-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-province-tab").parent().show();
      break;
    case "city" :
      me.activeTemplate.find("#tabcontent-area-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-city-tab").parent().show();
      me.activeTemplate.find("#tabcontent-province-tab").parent().show();
      break;
    case "area" :
      me.activeTemplate.find("#tabcontent-area-tab").parent().show();
      me.activeTemplate.find("#tabcontent-city-tab").parent().show();
      me.activeTemplate.find("#tabcontent-province-tab").parent().show();
      break;
    default:
      break;
    }
    
    if (this.area.provinceId == param.provinceId &&
      this.area.cityId == param.cityId &&
      this.area.areaId == param.areaId) {
      param.type = "init";
    }
    
    this.area = param;
    return param;
  };
  
  /**
   * 激活制定tab，并选中初始元素
   * @param eleId
   * @param areaId
   * @param typeKey
   * @param typeNameKey
   * @param $parent
   */
  Export.resetActivedArea = function(eleId, areaId, typeKey, typeNameKey) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".tab-content #" + eleId + " a.active").removeClass("active");
    if (areaId) {
      // 给选中的区域加点线状圈
      var activeElement = $popover.find(".tab-content #" + eleId + " a[areaId=" + areaId + "]");
      activeElement.addClass("active");
      var areaName = activeElement.html();
      $popover.find(".nav-tabs #" + eleId + "-tab").html(areaName);
      me.area[typeKey] = areaId;
      me.area[typeNameKey] = areaName;
    }
  };

  /**
   * 获取popover显示内容
   * @returns
   */
  Export.getTemplate = function() {
    var me = this;
    var $popover = this.$owner.data("bs.popover").tip();
    function activeTab(tabId) {
      var $tab = $popover.find("#" + tabId);
      $tab.parent().addClass("active");
      var target = $tab.data("target");
      $popover.find(target).addClass("active in");
    }
    
    function initAllTab() {
      var $tab = $popover.find(".nav-tabs li.active");
      $tab.removeClass("active");
      
      $tab = $popover.find(".tab-pane.active.in");
      $tab.removeClass("active in");
    }
    
    initAllTab();
    var param = me.area;
    me.resetActivedArea("tabcontent-province", param.provinceId, "provinceId", "provinceName");
    var defs = [];
    switch(param.type) {
    case "area":  // 区域
      var def = me.getArea(param.cityId, "tabcontent-area")
      .then(function(result) {
        return me.resetActivedArea("tabcontent-area", param.areaId, "areaId", "areaName");
      });
      defs.push(def);
    case "city":  // 城市
      var def = me.getArea(param.provinceId, "tabcontent-city")
      .then(function(result) {
        return me.resetActivedArea("tabcontent-city", param.cityId, "cityId", "cityName");
      });
      defs.push(def);
    case "province" : // 省份
      var def = me.getArea(0, "tabcontent-province")
      .then(function(result) {
        return me.resetActivedArea("tabcontent-province", param.provinceId, "provinceId", "provinceName");
      });
      defs.push(def);
    case "init" :  // 无变化
      if (param.type == "area") {
        activeTab("tabcontent-area-tab");
      } else if (param.type == "city") {
        me.clearPopoverTab("tabcontent-area");
        activeTab("tabcontent-city-tab");
      } else {
        me.clearPopoverTab("tabcontent-city");
        me.clearPopoverTab("tabcontent-area");
        activeTab("tabcontent-province-tab");
      }
      break;
    default:
      break;
    }

    return $.when.apply(me, defs)
    .then(function() {
      me.saveActiveTemplate();
    });
  };
  
  /**
   * 拼接区域名称
   * @returns
   */
  Export.getAreaName = function() {
    var areaName = [];
    if (this.area.provinceName) {
      areaName.push(this.area.provinceName);
      if (this.area.cityName) {
        areaName.push(this.area.cityName);
        if (this.area.areaName) {
          areaName.push(this.area.areaName);
        }
      }
    }
    
    return areaName.join("");
  };
  
  /**
   * 对外接口，供与父元素绑定
   * @param $owner
   * @param getCallback
   * @param setcallback
   * @param level
   * @param itemcallback
   * @returns {Export.bind}
   */
  Export.bind = function($owner, getCallback, setcallback, level, itemcallback) {
    var newUtil = $.extend(true, {}, this);
    newUtil._bind($owner, getCallback, setcallback, level, itemcallback);
    $owner.data("areaCheckSelect", newUtil);
    return newUtil;
  };
  
  Export._bind = function($owner, getCallback, setcallback, level, itemcallback) {
    var me = this;
    this.setcallback = setcallback;
    this.getCallback = getCallback;
    this.itemcallback = itemcallback;
    this.area = {};
    this.$owner = $owner;
    
    this.level = level || "area";
    
    this.init()
    .then(function() {
      me.getParam();
      $owner.popover({
        content: function() {
          return me.activeTemplate;
        },
        html: true,
        template: me.template,
        placement: "bottom"
      }).click(function(e) {
        e.preventDefault();
        e.stopPropagation();
      });
      
      me.bindPopoverEvent();
    });
  };
  
  /**
   * 绑定popover专属event
   */
  Export.bindPopoverEvent = function() {
    var me = this;
    me.$owner.on("shown.bs.popover", function() {
      if (me.templateInited) {
        me.bindCellClick();
        me.bindEvent();
      } else {
        me.getTemplate()
        .then(function() {
          me.bindCellClick();
          me.bindEvent();
          me.templateInited = true;
        });
      }
    });
    
    me.$owner.on("hide.bs.popover", function() {
      me.unbindEvent();
    });
  };
  
  /**
   * 对外接口，供将内容重用到父popover
   * @param $owner
   * @param $parent
   * @param getCallback
   * @param setcallback
   * @param level
   * @param itemcallback
   * @param initedCallback
   * @returns {Export.bindContent}
   */
  Export.bindContent = function($owner, $parent, getCallback, setcallback, level, itemcallback, initedCallback) {
    var newUtil = $.extend(true, {}, this);
    newUtil._bindContent($owner, $parent, getCallback, setcallback, level, itemcallback, initedCallback);
    $owner.data("areaCheckSelect", newUtil);
    return newUtil;
  };
  
  Export._bindContent = function($owner, $parent, getCallback, setcallback, level, itemcallback, initedCallback) {
    var me = this;
    this.setcallback = setcallback;
    this.getCallback = getCallback;
    this.itemcallback = itemcallback;
    this.area = {};
    this.$owner = $owner;
    this.$parent = $parent;
    
    this.level = level || "area";

    this.init()
    .then(function() {
      me.getParam();
      me.$parent.html(me.activeTemplate);
      return me.getTemplate();
    }).then(function() {
      me.bindCellClick();
      me.bindEvent();
      if (initedCallback) {
        initedCallback();
      }
      
      me.$owner.on("shown.bs.popover", function() {
        if (me.templateInited) {
          me.bindCellClick();
          me.bindEvent();
        } else {
          me.getTemplate()
          .then(function() {
            // 如果存在父panel，更新之
            me.$parent && me.$parent.html(me.activeTemplate);
            me.bindCellClick();
            me.bindEvent();
            me.templateInited = true;
          });
        }
      });
      
      me.$owner.on("hide.bs.popover", function() {
        me.unbindEvent();
      });
    });
  };
  
  /**
   * 清空指定tab
   * @param eleId
   */
  Export.clearPopoverTab = function(eleId) {
    var $popover = this.$owner.data("bs.popover").tip();
    $popover.find("#" + eleId).empty();
    $popover.find("#" + eleId + '-tab').html($popover.find("#" + eleId).attr("title"));
    if (eleId == "tabcontent-province") {
      this.area.provinceId = 0;
      this.area.provinceName = null;
    } else if (eleId == "tabcontent-city") {
      this.area.cityId = 0;
      this.area.cityName = null;
    } if (eleId == "tabcontent-area") {
      this.area.areaId = 0;
      this.area.areaName = null;
    }
  };

  /**
   * 缓存活动模板
   * @returns
   */
  Export.saveActiveTemplate = function() {
    var $popover = this.$owner.data("bs.popover").tip();
    
    this.activeTemplate = $popover.find(".area-check-select-list");
  };
  
  /**
   * 查询并渲染区域
   * @param dataId
   * @param tabId
   * @param $parent
   * @returns
   */
  Export.getArea = function(dataId, tabId) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    var data = null;
    return codeUtil.getArea(dataId)
    .then(function(result) {
      if (!result) {
        return null;
      }
      
      if (!result.items && $.isArray(result)) {
        result = {
          items: result
        };
      }
      
      data = result;
      return utils.loadTemplateLocal($popover.find('#' + tabId), 'area-check-select-item-template', result||{});
    }).then(function() {
      $popover.find("#" + tabId + "-tab", me.template).html($popover.find("#" + tabId).attr("title"));
      return data;
    });
  };
  
  Export.bindCellClick = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".area-check-select-list .tab-content a").off("click").on("click", function (e) {
      var tabPane = $(this).closest(".tab-pane");
      var tabPaneId = tabPane.attr("id");
      var areaId = parseInt($(this).attr("areaId")) || 0;
      var level = tabPane.attr("level");
      var nextId = tabPane.attr("nextId");
      me.area.type = level;
      
      // 请除下几个tab
      if (nextId) {
        me.clearPopoverTab(nextId);
        var subNextId = $("#" + nextId).attr("nextId");
        if (subNextId) {
          me.clearPopoverTab(subNextId);
        }
      }

      if (level === me.level) {
        me.resetActivedArea(tabPaneId, areaId, level + "Id", level + "Name");
        if (me.itemcallback) {
          me.itemcallback(me.area, me.getAreaName());
        }
        return;
      }

      me.getArea(areaId, nextId)
      .then(function(result) {
        me.resetActivedArea(tabPaneId, areaId, level + "Id", level + "Name");
        if (me.itemcallback) {
          me.itemcallback(me.area, me.getAreaName());
        }
        if (result && result.items && result.items.length > 0) {
          $("#" + nextId + "-tab").tab("show");
        }
        me.bindCellClick();
      });
    });
  };
  
  Export.saveValue = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    me.saveActiveTemplate();
    if (me.setcallback) {
      var $pane = $popover.find(".tab-pane.active");
      var provinceId = 0;
      if ($pane.attr("level") === "city") {
        provinceId = me.area.provinceId;
      }
      
      var $chkArr = $pane.find("input:checked");
      if ($chkArr.length == 0) {
        alert("请勾选省份或城市后点击确定按钮");
        return;
      }
      
      var valArr = [];
      
      if (provinceId) {
        $chkArr.each(function() {
          var obj = $(this).next();
          valArr.push({
            provinceId: provinceId,
            provinceName: me.area.provinceName,
            cityId: obj.attr("areaId"),
            cityName: obj.html()
          });
        });
      } else {
        $chkArr.each(function() {
          var obj = $(this).next();
          valArr.push({
            provinceId: obj.attr("areaId"),
            provinceName: obj.html()
          });
        });
      }
      
      me.setcallback({valArr: valArr});
    }
    
    me.$owner.popover("hide");
  };

  Export.bindEvent = function() {
    var me = this;

    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".area-check-select-list .nav-tabs a").on("shown.bs.tab", function (e) {
      me.saveActiveTemplate();
    });
    
    $popover.find(".area-check-select-list .nav-tabs a").on("click", function (e) {
      e.preventDefault();
      $(this).tab('show');
    });
    
    $popover.find("[name=checkConfirm]").on("click", function() {
      me.saveValue();
    });
    
    $popover.find("[name=checkSelectAll]").on("click", function() {
      if ($popover.find(".tab-pane.active input:checked").length > 0) {
        $popover.find(".tab-pane.active input:checked").prop("checked", false);
      } else {
        $popover.find(".tab-pane.active input").prop("checked", true);
      }
    });
  };

  Export.unbindEvent = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".area-check-select-list .nav-tabs a").off("shown.bs.tab");
    $popover.find(".area-check-select-list .nav-tabs a").off("click");
    $popover.find(".area-check-select-list .tab-content a").off("click");
    $popover.find("[name=checkConfirm]").off("click");
    $popover.find("[name=checkSelectAll]").off("click");
  };
  
  return Export;
});