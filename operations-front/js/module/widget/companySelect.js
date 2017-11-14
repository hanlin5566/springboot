define(['util/utils', 'util/codeUtil', 'util/domUtil', 'util/cacheUtil', 'widget/areaSelect'],
    function(utils, codeUtil, domUtil, cacheUtil, areaSelect) {

  var Export = {
      
  };
  
  Export.init = function() {
    if (this.inited) {
      var def = $.Deferred();
      def.resolve();
      return def.promise();
    }
    var me = this;
    
    // 建立一个空div，保存template的结果
    return utils.loadTemplate(null, 'company-select-template', 'widget/companySelect.tpl')
    .then(function(html) {
      me.template = html;
      me.activeTemplate = utils.loadTemplateLocal(null, 'company-select-content-template');
      me.inited = true;
    });
  };
  
  Export.getParameter = function() {
    if (this.getCallback) {
      this.company = this.getCallback() || this.company;
    }
  };
  
  Export.bind = function($owner, getCallback, setcallback, type, title) { 
    var newSelect = $.extend(true, {}, this);
    newSelect._bind($owner, getCallback, setcallback, type, title);
    return newSelect;
  };
  
  Export.changeType = function(type) {
    var me = this;
    me.type = type;
    
    var $parent = me.$owner.data("bs.popover").tip();
    
    var $ele = $parent.find(".company-list ul");
    if (!$ele || $ele.length == 0) {
      $ele = me.activeTemplate.filter(".company-list").find("ul");
    }
    
    $ele.empty();
  };
  
  Export._bind = function($owner, getCallback, setcallback, type, title) {
    if (!type) {
      alert("no type will die.");
    }
    var me = this;
    this.setcallback = setcallback;
    this.getCallback = getCallback;
    this.company = {};
    this.$owner = $owner;
    this.type = type;

    this.getParameter();
    this.init()
    .then(function() {
      $owner.popover({
        content: function() {
          return me.activeTemplate;
        },
        html: true,
        title: title,
        template: me.template,
        placement: "bottom"
      }).click(function(e) {
        e.preventDefault();
      });
    });
    
    $owner.on("show.bs.popover", function() {
    });
    
    $owner.on("shown.bs.popover", function() {
      me.renderCompany();
      me.bindEvent();
    });
    
    $owner.on("hidden.bs.popover", function() {
      me.resetPopoverStyle();
      me.unbindEvent();
    });
  };
  
  Export.resetPopoverStyle = function() {
    var $popover = this.$owner.data("bs.popover").tip();
    var $tpl = $popover.find(".company-select-container");
    if ($tpl.hasClass("area")) {
      $tpl.removeClass("area");
      $tpl.addClass("company");
    }
  };
  
  Export.renderCompany = function() {
    var me = this;
    var para = {offset:0, limit: 1000};
    if (me.area) {
      if (me.area.provinceId) {
        para.provinceId = me.area.provinceId;
      }
      if (me.area.cityId) {
        para.cityId = me.area.cityId;
      }
      if (me.area.areaId) {
        para.areaId = me.area.areaId;
      }
    }
    
    var url = null;
    if (me.type == "manufacturer") {
      url = "admin/main/search";
    } else if (me.type == "terminalStore") {
      url = "admin/terminalStore/search";
    } else if (me.type == "serviceProvider") {
      url = "admin/serviceProvider/search";
    } else if(me.type == "terminalStore-serviceProvider") {
      url = "terminalStore/serviceProvider/search";
    } else if(me.type == "terminalStore-manufacturer") {
      url = "terminalStore/main/search";
    } else if(me.type == "serviceProvider-manufacture") {
      url = "serviceProvider/main/search";
    } else if(me.type == "serviceProvider-terminalStore") {
      url = "serviceProvider/terminalStore/search";
    } else {
      return;
    }
    
    return cacheUtil.cacheGet(url + "?auditStatus=APPROVED", para, "POST")
    .then(function(result) {
      var $parent = me.$owner.data("bs.popover").tip();
      
      var $ele = $parent.find(".company-list ul");
      if (!$ele || $ele.length == 0) {
        $ele = me.activeTemplate.filter(".company-list").find("ul");
      }
      
      me.companyList = result.items;
      
      utils.loadTemplateLocal($ele, "company-select-item-template", {items: result.items});
      me.bindCellClick();
      me.saveActiveTemplate();
    });
  };
  
  Export.bindCellClick = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    $popover.find(".company-list ul li").on("click", function() {
      if (me.setcallback) {
        var companyId = $(this).attr("companyId");
        var company = null;
        for (var k in me.companyList) {
          var data = me.companyList[k];
          if (data.companyId == companyId) {
            company = data;
          }
        }
        me.setcallback(company, me.area);
        me.$owner.popover("hide");
      }
    });
  };

  Export.saveActiveTemplate = function() {
    var $popover = this.$owner.data("bs.popover").tip();
    this.activeTemplate = $popover.find(".popover-content").children();
  };
  
  Export.renderAreaSelect = function() {
    if (this.areaSelectInited) {
      return;
    }
    this.areaSelectInited = true;
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    areaSelect.bindContent(me.$owner, $popover.find(".area-select-list-plugin"), function() {
      return me.area || {};
    }, function(area, areaName) {
      var $container = $popover.find(".company-select-container");
      if ($container.hasClass("area")) {
        $container.removeClass("area");
        $container.addClass("company");
        me.renderCompany();
      }
    }, 'area', function(area, areaName) {
      me.area = area;
      $popover.find(".operation-footer span[name=area]").html(areaName);
      me.saveActiveTemplate();
    }, function() {
      me.saveActiveTemplate();
    });
  };

  Export.bindEvent = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    var $container = $popover.find(".company-select-container");
    var $dom = domUtil.getDomByName($container);
    if ($dom["select-area-btn"]) {
      $dom["select-area-btn"].dom.on("click", function() {
        if ($container.hasClass("company")) {
          $container.removeClass("company");
          $container.addClass("area");
          me.renderAreaSelect();
        }
      });
  
      $dom["search-company-btn"].dom.on("click", function() {
        if ($container.hasClass("area")) {
          $container.removeClass("area");
          $container.addClass("company");
          me.renderCompany();
        }
      });
    }
  };

  Export.unbindEvent = function() {
    var me = this;
    var $popover = me.$owner.data("bs.popover").tip();
    var $container = $popover.find(".company-select-container");
    var $dom = domUtil.getDomByName($container);
    if ($dom["select-area-btn"]) {
      $dom["select-area-btn"].dom.off("click");
      $dom["search-company-btn"].dom.off("click");
    }
  };
  
  return Export;
});