define(['util/utils', 'util/carBrandUtil', 'util/domUtil'],
    function(utils, carBrandUtil, domUtil) {

  var Export = {
      setting : {
        setcallback: null,
        getCallback: null,
        level: "carModel"
      },
      $owner: null,
      param: {},
      template: null,
      activeTemplate: null,
      inited: false
  };
  
  var sort_no = {"carBrand": 1, "carSeries": 2, "carModel": 3};
  var ctrlIndex = 0;
  
  Export.getUId = function() {
    return "carbrand-multiselect-" + (++ctrlIndex);
  };

  /**
   * 初始化，加载popover template
   * @returns
   */
  Export.init = function() {
    if (this.inited) {
      return $.Deferred().resolve().promise();
    }
    var me = this;
    
    // 建立一个空div，保存template的结果
    return utils.loadTemplate(null, 'carbrand-multiselect-template', 'widget/carBrandMultiSelect.tpl')
    .then(function(html) {
      me.template = html;
      // 因为bootstrap的tab直接使用id做关联，无法实现同页面多个控件情况，所以加上命名空间
      var uid = me.getUId();
      me.activeTemplate = utils.loadTemplateLocal(null, 'carbrand-multiselect-content-template', {uid:uid});
      me.inited = true;
    });
  };
  
  /**
   * 从父元素获取初始参数、并初始化tab显示
   * 
   */
  Export.getParam = function() {
    var param = this.setting.getcallback ? this.setting.getcallback() : {};
    param.type = "carModel";
    
    param.carBrandId = parseInt(param.carBrandId) || 0;
    param.carSeriesId = parseInt(param.carSeriesId) || 0;
    param.carModelId = parseInt(param.carModelId) || 0;
    
    if (!param.carBrandId || this.setting.level == "carBrand") {
      param.type = "carBrand";
    } else if (!param.carSeriesId) {
      param.type = "carBrand";
    } else if (!param.carModelId) {
      param.type = "carSeries";
    }
    
    if (sort_no[param.type] > sort_no[this.setting.level]) {
      param.type = this.setting.level;
    }
    
    // 根据level设置那些tab不可以选择和显示
    var me = this;
    switch(this.setting.level) {
    case "carBrand" :
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-carSeries-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().show();
      break;
    case "carSeries" :
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-carSeries-tab").parent().show();
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().show();
      break;
    case "carModel" :
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().show();
      me.activeTemplate.find("#tabcontent-carSeries-tab").parent().show();
      me.activeTemplate.find("#tabcontent-carBrand-tab").parent().show();
      break;
    default:
      break;
    }
    
    if (this.carbrand.carBrandId == param.carBrandId &&
      this.carbrand.carSeriesId == param.carSeriesId &&
      this.carbrand.carModelId == param.carModelId) {
      param.type = "init";
    }
    
    this.carbrand = param;
    return param;
  };
  
  /**
   * 激活制定tab，并选中初始元素
   * @param eleId
   * @param carbrandId
   * @param typeKey
   * @param typeNameKey
   * @param $parent
   */
  Export.resetActivedCarbrand = function(eleId, carbrandId, typeKey, typeNameKey) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".tab-content #" + eleId + " a.active").removeClass("active");
    if (carbrandId) {
      // 给选中的加点线状圈
      var activeElement = $popover.find(".tab-content #" + eleId + " a[carbrandId=" + carbrandId + "]");
      activeElement.addClass("active");
      var carbrandName = activeElement.html();
      $popover.find(".nav-tabs #" + eleId + "-tab").html(carbrandName);
      me.carbrand[typeKey] = carbrandId;
      me.carbrand[typeNameKey] = carbrandName;
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
    var param = me.carbrand;
    me.resetActivedCarbrand("tabcontent-carBrand", param.carBrandId, "carBrandId", "carBrandName");
    var defs = [];
    switch(param.type) {
    case "carModel":  // 车型
      var def = me.getCarbrand(param.carSeriesId, "tabcontent-carModel")
      .then(function(result) {
        return me.resetActivedCarbrand("tabcontent-carModel", param.carModelId, "carModelId", "carModelName");
      });
      defs.push(def);
    case "carSeries":  // 车款
      var def = me.getCarbrand(param.carBrandId, "tabcontent-carSeries")
      .then(function(result) {
        return me.resetActivedCarbrand("tabcontent-carSeries", param.carSeriesId, "carSeriesId", "carSeriesName");
      });
      defs.push(def);
    case "carBrand" : // 品牌
      var def = me.getCarbrand(0, "tabcontent-carBrand")
      .then(function(result) {
        return me.resetActivedCarbrand("tabcontent-carBrand", param.carBrandId, "carBrandId", "carBrandName");
      });
      defs.push(def);
    case "init" :  // 无变化
      if (param.type == "carModel") {
        activeTab("tabcontent-carBrand-tab");
        $popover.find(".carbrand-multiselect-list-footer [name=selectAll]").show();
        $popover.find(".carbrand-multiselect-list-footer [name=submit]").removeClass('disabled');
      } else if (param.type == "carSeries") {
        me.clearPopoverTab("tabcontent-carModel");
        activeTab("tabcontent-carSeries-tab");
      } else {
        me.clearPopoverTab("tabcontent-carSeries");
        me.clearPopoverTab("tabcontent-carModel");
        activeTab("tabcontent-carBrand-tab");
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
   * 拼接名称
   * @returns
   */
  Export.getSelectCarbrand = function() {
    var carBrand = {};
    var dom = $("#tabcontent-carBrand a.active");
    carBrand.carBrandId = dom.attr("carBrandId");
    carBrand.carBrandName = $.trim(dom.html());
    dom = $("#tabcontent-carSeries a.active");
    carBrand.carSeriesId = dom.attr("carBrandId");
    carBrand.carSeriesName = $.trim(dom.html());
    carBrand.carModel = [];
    
    $("#tabcontent-carModel input[type=checkbox]:checked").each(function() {
      carBrand.carModel.push({
        carModelId : $(this).val(),
        carModelName : $(this).attr("carModelName")
      });
    });
    
    return carBrand;
  };
  
  /**
   * 对外接口，供与父元素绑定
   * @param $owner
   * @param getCallback
   * @param setcallback
   * @param level
   * @returns {Export.bind}
   */
  Export.bind = function($owner, setting) {
    var newUtil = $.extend(true, {}, this);
    newUtil._bind($owner, setting);
    newUtil.destory = false;
    $owner.data("carbrandSelect", newUtil);
    return newUtil;
  };
  
  Export._bind = function($owner, setting) {
    this.setting = $.extend({}, this.setting, setting);
    this.setting.level = this.setting.level || "carModel";
    this.carbrand = {};
    this.$owner = $owner;

    var me = this;
    this.init()
    .then(function() {
      me.getParam();
      $owner.popover({
        content: function() {
          return me.activeTemplate;
        },
        html: true,
        template: me.template,
        placement: me.setting.placement || "bottom"
      }).click(function(e) {
        e.preventDefault();
        e.stopPropagation();
      });
      
      me.bindPopoverEvent();
    });
  };
  
  Export.enable = function(enable) {
    this.$owner.popover({enable:enable});
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
   * 清空指定tab
   * @param eleId
   */
  Export.clearPopoverTab = function(eleId) {
    var $popover = this.$owner.data("bs.popover").tip();
    $popover.find("#" + eleId).empty();
    $popover.find("#" + eleId + '-tab').html($popover.find("#" + eleId).attr("title"));
    if (eleId == "tabcontent-carBrand") {
      this.carbrand.carBrandId = 0;
      this.carbrand.carBrandName = null;
    } else if (eleId == "tabcontent-carSeries") {
      this.carbrand.carSeriesId = 0;
      this.carbrand.carSeriesName = null;
    } if (eleId == "tabcontent-carModel") {
      this.carbrand.carModelId = 0;
      this.carbrand.carModelName = null;
    }
  };

  /**
   * 缓存活动模板
   * @returns
   */
  Export.saveActiveTemplate = function() {
    var $popover = this.$owner.data("bs.popover").tip();
    this.activeTemplate = $popover.find(".carbrand-multiselect-list");
  };
  
  /**
   * 查询并渲染
   * @param dataId
   * @param tabId
   * @param $parent
   * @returns
   */
  Export.getCarbrand = function(dataId, tabId) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    var def = null;
    if (tabId.indexOf("carBrand") > -1) {
      def = carBrandUtil.getCarBrand(dataId);
    } else if (tabId.indexOf("carSeries") > -1) {
      def = carBrandUtil.getCarSeries(dataId, true);
    } else if (tabId.indexOf("carModel") > -1) {
      def = carBrandUtil.getCarModel(dataId);
    } else {
      return;
    }
    
    return def.then(function(result) {
      if (!result) {
        return null;
      }
      
      if (tabId.indexOf("carBrand") > -1) {
        utils.loadTemplateLocal($popover.find('#' + tabId), 'carbrand-multiselect-carBrand-item-template', {items: result});
      } else if (tabId.indexOf("carSeries") > -1) {
        var items = [];
        for (var k in result) {
          items.push({
            carSeries: k,
            items: result[k]
          });
        }
        
        utils.loadTemplateLocal($popover.find('#' + tabId), 'carbrand-multiselect-carSeries-item-template', {items: items});
      } else {
        utils.loadTemplateLocal($popover.find('#' + tabId), 'carbrand-multiselect-carModel-item-template', {items: result});
      }
      
      $popover.find("#" + tabId + "-tab", me.template).html($popover.find("#" + tabId).attr("title"));
      return result;
    });
  };
  
  Export.bindCellClick = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".carbrand-multiselect-list .tab-content a").off("click").on("click", function (e) {
      var tabPane = $(this).closest(".tab-pane");
      var tabPaneId = tabPane.attr("id");
      var carbrandId = parseInt($(this).attr("carbrandId")) || 0;
      var level = tabPane.attr("level");
      var nextId = tabPane.attr("nextId");
      me.carbrand.type = level;
      
      // 请除下几个tab
      if (nextId) {
        me.clearPopoverTab(nextId);
        var subNextId = $("#" + nextId).attr("nextId");
        if (subNextId) {
          me.clearPopoverTab(subNextId);
        }
      }

      if (level === me.setting.level) {
        me.resetActivedCarbrand(tabPaneId, carbrandId, level + "Id", level + "Name");
        me.saveValue();
        return;
      }

      me.getCarbrand(carbrandId, nextId)
      .then(function(result) {
        me.resetActivedCarbrand(tabPaneId, carbrandId, level + "Id", level + "Name");

        $("#" + nextId + "-tab").tab("show");
        me.bindCellClick();
      });
    });
  };

  Export.saveValue = function() {
    var me = this;
    me.saveActiveTemplate();
    if (me.setting.setcallback) {
      if (me.setting.setcallback(me.getSelectCarbrand()) === false) {
        return;
      }
    }
    me.$owner.popover("hide");
  };

  Export.bindEvent = function() {
    var me = this;

    var $popover = me.$owner.data("bs.popover").tip();
    var $dom = domUtil.getDomByName($popover.find(".carbrand-multiselect-list-footer"));
    
    $popover.find(".carbrand-multiselect-list .nav-tabs a").on("shown.bs.tab", function (e) {
      me.saveActiveTemplate();
      if ($(e.target).is("#tabcontent-carModel-tab")) {
        $dom.selectAll.dom.show();
        $dom.submit.dom.removeClass('disabled');
      } else {
        $dom.selectAll.dom.hide();
        $dom.submit.dom.addClass('disabled');
      }
    });
    
    $popover.find(".carbrand-multiselect-list .nav-tabs a").on("click", function (e) {
      e.preventDefault();
      $(this).tab('show');
    });
    
    $dom.submit.dom.on("click", function() {
      if (me.setting.setcallback) {
        if (me.setting.setcallback(me.getSelectCarbrand()) === false) {
          return;
        }
      }
      me.$owner.popover("hide");
    });
    
    $dom.close.dom.on("click", function() {
      me.$owner.popover("hide");
    });
    
    $dom.selectAll.dom.on("click", function() {
      var $carModel = $popover.find("#tabcontent-carModel");
      var chkCount = $carModel.find("input[type=checkbox]:checked").length;
      $carModel.find("input[type=checkbox]").prop("checked", !chkCount);
    });
  };
  
  Export.unbind = function() {
    this.$owner.removeData("carbrandSelect");
    this.destory = true;
    this.$owner.popover("destroy");
  };

  Export.unbindEvent = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".carbrand-multiselect-list .nav-tabs a").off("shown.bs.tab");
    $popover.find(".carbrand-multiselect-list .nav-tabs a").off("hidden.bs.tab");
    $popover.find(".carbrand-multiselect-list .nav-tabs a").off("click");
    $popover.find(".carbrand-multiselect-list .tab-content a").off("click");
    
    var $footer = $popover.find(".carbrand-multiselect-list-footer");
    if ($footer.length > 0) {
      var $dom = domUtil.getDomByName($footer);
      logger.trace($popover);
      $dom.submit.dom.off("click");
      $dom.close.dom.off("click");
      $dom.selectAll.dom.off("click");
    }
    if (this.destory) {
      this.$owner = null;
    }
  };
  
  return Export;
});