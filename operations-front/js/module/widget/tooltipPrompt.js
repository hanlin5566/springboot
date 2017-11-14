define(['util/requestUtil'], function(requestUtil) {
  var Export = {
  };
  
  Export.init = function() {
    var me = this;
    requestUtil.loadTemplate($("#widget-container"), "tooltip-prompt-template", "widget/tooltipPrompt.tpl")
    .then(function(html) {
      me.template = $("#tooltip-prompt");
    });
  };
  
  Export.show = function(msg) {
    if (msg) {
      this.template.find(".popover-content div").html(msg);
    }
    
    this.template.removeAttr("style");
    this.template.show();
    this.template.fadeOut(1500);
  };

  Export.hide = function() {
    this.template.hide();
  };
  
  Export.init();
  return Export;
});