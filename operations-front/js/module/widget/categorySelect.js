define(['util/utils', 'util/categoryUtil', 'util/domUtil'],
    function(utils, categoryUtil, domUtil) {

  var Export = {
    hideFooter : false,
    setCallback: null,
    getCallback: null,
    itemcallback: null,
    level: "category3",
    param: {},
    template: null,
    activeTemplate: null,
    inited: false,
    categoryUtil : categoryUtil
  };
  
  var sort_no = {"category1": 1, "category2": 2, "category3": 3};
  var ctrlIndex = 0;
  
  Export.getUId = function() {
    return "category-select-" + (++ctrlIndex);
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
    return utils.loadTemplate(null, 'category-select-template', 'widget/categorySelect.tpl')
    .then(function(html) {
      me.template = html;
      
      // 因为bootstrap的tab直接使用id做关联，无法实现同页面多个控件情况，所以加上命名空间
      var uid = me.getUId();
      me.activeTemplate = utils.loadTemplateLocal(null, 'category-select-content-template', {uid:uid});
      
      if (me.hideFooter) {
        me.activeTemplate.find(".category-select-list-footer").hide();
      }
      me.inited = true;
    });
  };
  
  /**
   * 从父元素获取初始参数、并初始化tab显示
   * 
   */
  Export.getParam = function() {
    var param = this.getCallback();
    param.type = "category3";
    
    param.category1Id = parseInt(param.category1Id) || 0;
    param.category2Id = parseInt(param.category2Id) || 0;
    param.category3Id = parseInt(param.category3Id) || 0;
    
    if (!param.category1Id || this.level == "category1") {
      param.type = "category1";
    } else if (!param.category2Id) {
      param.type = "category1";
    } else if (!param.category3Id) {
      param.type = "category2";
    }
    
    if (sort_no[param.type] > sort_no[this.level]) {
      param.type = this.level;
    }
    
    // 根据level设置那些tab不可以选择和显示
    var me = this;
    switch(this.level) {
    case "category1" :
      me.activeTemplate.find("#tabcontent-category1-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-category2-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-category1-tab").parent().show();
      break;
    case "category2" :
      me.activeTemplate.find("#tabcontent-category1-tab").parent().hide();
      me.activeTemplate.find("#tabcontent-category2-tab").parent().show();
      me.activeTemplate.find("#tabcontent-category1-tab").parent().show();
      break;
    case "category3" :
      me.activeTemplate.find("#tabcontent-category1-tab").parent().show();
      me.activeTemplate.find("#tabcontent-category2-tab").parent().show();
      me.activeTemplate.find("#tabcontent-category1-tab").parent().show();
      break;
    default:
      break;
    }
    
    if (this.category.category1Id == param.category1Id &&
      this.category.category2Id == param.category2Id &&
      this.category.category3Id == param.category3Id) {
      param.type = "init";
    }
    
    this.category = param;
    return param;
  };
  
  /**
   * 激活制定tab，并选中初始元素
   * @param eleId
   * @param categoryId
   * @param typeKey
   * @param typeNameKey
   * @param $parent
   */
  Export.resetActivedCategory = function(eleId, categoryId, typeKey, typeNameKey) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".tab-content #" + eleId + " a.active").removeClass("active");
    if (categoryId) {
      // 给选中的加点线状圈
      var activeElement = $popover.find(".tab-content #" + eleId + " a[categoryId=" + categoryId + "]");
      activeElement.addClass("active");
      var categoryName = activeElement.html();
      $popover.find(".nav-tabs #" + eleId + "-tab").html(categoryName);
      me.category[typeKey] = categoryId;
      me.category[typeNameKey] = categoryName;
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
    var param = me.category;
    me.resetActivedCategory("tabcontent-category1", param.category1Id, "category1Id", "category1Name");
    var defs = [];
    switch(param.type) {
    case "category3":  // 类别3
      var def = me.getCategory(param.category2Id, "tabcontent-category3")
      .then(function(result) {
        return me.resetActivedCategory("tabcontent-category3", param.category3Id, "category3Id", "category3Name");
      });
      defs.push(def);
    case "category2":  // 类别2
      var def = me.getCategory(param.category1Id, "tabcontent-category2")
      .then(function(result) {
        return me.resetActivedCategory("tabcontent-category2", param.category2Id, "category2Id", "category2Name");
      });
      defs.push(def);
    case "category1" : // 类别1
      var def = me.getCategory(0, "tabcontent-category1")
      .then(function(result) {
        return me.resetActivedCategory("tabcontent-category1", param.category1Id, "category1Id", "category1Name");
      });
      defs.push(def);
    case "init" :  // 无变化
      if (param.type == "category3") {
        activeTab("tabcontent-category1-tab");
      } else if (param.type == "category2") {
        me.clearPopoverTab("tabcontent-category3");
        activeTab("tabcontent-category2-tab");
      } else {
        me.clearPopoverTab("tabcontent-category2");
        me.clearPopoverTab("tabcontent-category3");
        activeTab("tabcontent-category1-tab");
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
   * 拼接类别名称
   * @returns
   */
  Export.getCategoryName = function() {
    var categoryName = [];
    if (this.category.category1Name) {
      categoryName.push(this.category.category1Name);
      if (this.category.category2Name) {
        categoryName.push(this.category.category2Name);
        if (this.category.category3Name) {
          categoryName.push(this.category.category3Name);
        }
      }
    }
    
    return categoryName;
  };
  
  /**
   * 对外接口，供与父元素绑定
   * @param $owner
   * @param getCallback
   * @param setCallback
   * @param level
   * @param itemcallback
   * @returns {Export.bind}
   */
  Export.bind = function(setting) {
    var newUtil = $.extend(true, {}, this);
    newUtil._bind(setting);
    setting.$owner.data("categorySelect", newUtil);
    return newUtil;
  };
  
  Export._bind = function(setting) {
    var me = this;
    this.setCallback = setting.setCallback;
    this.getCallback = setting.getCallback;
    this.itemcallback = setting.itemcallback;
    this.hideFooter = setting.hideFooter;
    this.category = {};
    this.$owner = setting.$owner;
    this.level = setting.level || "category3";
    
    this.init()
    .then(function() {
      me.getParam();
      setting.$owner.popover({
        content: function() {
          return me.activeTemplate;
        },
        html: true,
        template: me.template,
        placement: "bottom"
      }).click(function(e) {
        e.preventDefault();
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
   * 清空指定tab
   * @param eleId
   */
  Export.clearPopoverTab = function(eleId) {
    var $popover = this.$owner.data("bs.popover").tip();
    $popover.find("#" + eleId).empty();
    $popover.find("#" + eleId + '-tab').html($popover.find("#" + eleId).attr("title"));
    if (eleId == "tabcontent-category1") {
      this.category.category1Id = 0;
      this.category.category1Name = null;
    } else if (eleId == "tabcontent-category2") {
      this.category.category2Id = 0;
      this.category.category2Name = null;
    } if (eleId == "tabcontent-category3") {
      this.category.category3Id = 0;
      this.category.category3Name = null;
    }
  };

  /**
   * 缓存活动模板
   * @returns
   */
  Export.saveActiveTemplate = function() {
    var $popover = this.$owner.data("bs.popover").tip();
    this.activeTemplate = $popover.find(".category-select-list");
  };
  
  /**
   * 查询并渲染
   * @param dataId
   * @param tabId
   * @param $parent
   * @returns
   */
  Export.getCategory = function(dataId, tabId) {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    var data = null;
    var def = null;
    if (tabId.indexOf("category1") > -1) {
      def = me.categoryUtil.getCategory1(dataId);
    } else if (tabId.indexOf("category2") > -1) {
      def = me.categoryUtil.getCategory2(dataId);
    } else if (tabId.indexOf("category3") > -1) {
      def = me.categoryUtil.getCategory3(dataId);
    } else {
      return;
    }
    
    return def
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
      return utils.loadTemplateLocal($popover.find('#' + tabId), 'category-select-item-template', result||{});
    }).then(function() {
      $popover.find("#" + tabId + "-tab", me.template).html($popover.find("#" + tabId).attr("title"));
      return data;
    });
  };
  
  Export.bindCellClick = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".category-select-list .tab-content a").off("click").on("click", function (e) {
      var tabPane = $(this).closest(".tab-pane");
      var tabPaneId = tabPane.attr("id");
      var categoryId = parseInt($(this).attr("categoryId")) || 0;
      var level = tabPane.attr("level");
      var nextId = tabPane.attr("nextId");
      me.category.type = level;
      
      // 请除下几个tab
      if (nextId) {
        me.clearPopoverTab(nextId);
        var subNextId = $("#" + nextId).attr("nextId");
        if (subNextId) {
          me.clearPopoverTab(subNextId);
        }
      }

      if (level === me.level) {
        me.resetActivedCategory(tabPaneId, categoryId, level + "Id", level + "Name");
        if (me.itemcallback) {
          me.itemcallback(me.category, me.getCategoryName());
        }
        me.saveValue();
        return;
      }

      me.getCategory(categoryId, nextId)
      .then(function(result) {
        me.resetActivedCategory(tabPaneId, categoryId, level + "Id", level + "Name");
        if (me.itemcallback) {
          me.itemcallback(me.category, me.getCategoryName());
        }
        if (!result || !result.items || result.items.length == 0) {
          me.saveValue();
        } else {
          $("#" + nextId + "-tab").tab("show");
        }
        me.bindCellClick();
      });
    });
  };
  
  Export.saveValue = function() {
    var me = this;
    me.saveActiveTemplate();
    if (me.setCallback) {
      me.setCallback(me.category, me.getCategoryName());
    }
    me.$owner.popover("hide");
  };

  Export.bindEvent = function() {
    var me = this;

    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".category-select-list .nav-tabs a").on("shown.bs.tab", function (e) {
      me.saveActiveTemplate();
    });
    
    $popover.find(".category-select-list .nav-tabs a").on("click", function (e) {
      e.preventDefault();
      $(this).tab('show');
    });
    
    var $dom = domUtil.getDomByName($popover.find(".category-select-list-footer"));
    $dom.submit.dom.on("click", function() {
      if (me.setCallback) {
        me.setCallback(me.category, me.getCategoryName());
      }
      me.$owner.popover("hide");
    });
    
    $dom.close.dom.on("click", function() {
      me.$owner.popover("hide");
    });
  };

  Export.unbindEvent = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".category-select-list .nav-tabs a").off("shown.bs.tab");
    $popover.find(".category-select-list .nav-tabs a").off("click");
    $popover.find(".category-select-list .tab-content a").off("click");
    
    var $dom = domUtil.getDomByName($popover.find(".category-select-list-footer"));
    $dom.submit.dom.off("click");
    $dom.close.dom.off("click");
  };
  
  
  var serviceInstance = $.extend(true, {}, Export);
  serviceInstance.categoryUtil = categoryUtil.getServiceType();
  
  Export.getServiceInstance = function() {
    return serviceInstance;
  };
  
  return Export;
});