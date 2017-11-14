define(['core/navigation', 'util/utils',
    'util/domUtil', 'util/dataUtil', 'core/base', 'widget/loadding'],
function(navigation, utils, domUtil, dataUtil, Base, loadding) {
  
  var DEFAULT_SETTING = {
      title: "",
      content: "",
      valueCallback: null
  };
  
  var Export = {
      inited : false,
      settings : {}
  };

  Export._init = function() {
    var me = this;
    if (!me.inited) {
      return me.loadTemplate().then(function(html) {
        me.template = html;
        me.bindEvent();
        me.inited = true;
      });
    }
    
    return $.Deferred().resolve().promise();
  };

  Export.loadTemplate = function() {
    return utils.loadTemplate(null, 'dialog-confirm-template', 'widget/dialogConfirm.tpl')
        .then(function(html) {
          $("#widget-container").append(html);
          return $.Deferred().resolve(html).promise();
        });
  };

  Export.bindEvent = function() {
    var $dlgDom = domUtil.getDomByName(this.template);
    var me = this;
    $dlgDom.ok.dom.bind("click", function(e) {
      me.settings.valueCallback();
      me.template.modal("hide");
      e.preventDefault();
    });
    $dlgDom.cancel.dom.bind("click", function(e) {
      me.template.modal("hide");
      e.preventDefault();
    });
  };

  Export.show = function(settings) {
    var me = this;
    this._init()
    .then(function () {
      if (!me.template.is(":visible")) {
        me.settings = $.extend(DEFAULT_SETTING, settings);
        var $dlgDom = domUtil.getDomByName(me.template);
        $dlgDom.title.val(me.settings.title);
        $dlgDom.content.val(me.settings.content);
        loadding.hide().then(function() {
          me.template.modal();
        });
      }
    });
  };

  return Export;
});