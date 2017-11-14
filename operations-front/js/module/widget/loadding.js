define(['util/utils', 'util/codeUtil', 'util/domUtil', 'widget/tooltipPrompt'],
    function(utils, codeUtil, domUtil, tooltipPrompt) {

  var Export = {
    callback: null,
    template: null,
    inited: false,
    showing: false
  };

  Export.init = function() {
    this.initDef = $.Deferred();
    if (this.inited) {
      var def = $.Deferred();
      def.resolve();
      return def.promise();
    }
    
    var me = this;
    
    return utils.loadTemplate(null, 'loadding-template', 'widget/loadding.tpl')
    .then(function(html) {
      me.template = html;
      $("#widget-container").append(html);
      return $.Deferred().resolve(html).promise();
    });
  };
  
  Export.show = function(callback) {
    var me = this;
    if (me.showing) {
      return true;
    }
    me.showing = true;
    this.init()
    .then(function() {
      me.template.show();
      callback && callback();
      me.handle = setTimeout(function() {
        me.handle = null;
        me.hideSync();
      }, 10000);
      
      if (!me.inited) {
        me.initDef.resolve();
        me.initDef = null;
        me.inited = true;
      }
    });
    
    return false;
  };
  
  Export.hide = function(showSuccess, successMessage) {
    var def = $.Deferred();
    if (this.inited) {
      this.hideSync(showSuccess, successMessage);
      def.resolve();
    } else {
      // 初始化尚未完成，等待
      var me = this;
      if (me.initDef) {
        me.initDef.then(function() {
          me.hideSync(showSuccess, successMessage);
          me.initDef = null;
          def.resolve();
        });
      } else {
        me.hideSync(showSuccess, successMessage);
        def.resolve();
      }
    }
    
    return def.promise();
  };
  
  Export.hideSync = function(showSuccess, successMessage) {
    this.handle && clearTimeout(this.handle);
    this.handle = null;
    this.showing = false;
    this.template && this.template.hide();
    showSuccess && tooltipPrompt.show(successMessage);
  };
  
  return Export;
});