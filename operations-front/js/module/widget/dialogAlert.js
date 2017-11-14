define(['core/navigation', 'util/utils',
    'util/domUtil', 'util/dataUtil', 'core/base', 'widget/loadding'],
function(navigation, utils, domUtil, dataUtil, Base, loadding) {

  var TYPE_INFO = "info";
  var TYPE_WARNING = "warning";
  var TYPE_ERROR = "error";
  var ICON_MAP = {};
  ICON_MAP[TYPE_INFO] = "fa fa-info-circle";
  ICON_MAP[TYPE_WARNING] = "fa fa-exclamation-circle";
  ICON_MAP[TYPE_ERROR] = "fa fa-times-circle";

  var TITLE_MAP = {};
  TITLE_MAP[TYPE_INFO] = "消息";
  TITLE_MAP[TYPE_WARNING] = "警告";
  TITLE_MAP[TYPE_ERROR] = "错误";
  
  var DEFAULT_SETTING = {
      type: TYPE_ERROR,
      icon: "",
      title: "",
      content: ""
  };
  
  var Export = {
      TYPE_INFO : TYPE_INFO,
      TYPE_WARNING : TYPE_WARNING,
      TYPE_ERROR : TYPE_ERROR,
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
    return utils.loadTemplate(null, 'dialog-alert-template', 'widget/dialogAlert.tpl')
        .then(function(html) {
          $("#widget-container").append(html);
          return $.Deferred().resolve(html).promise();
        });
  };

  Export.bindEvent = function() {
    var $dlgDom = domUtil.getDomByName(this.template);
    var me = this;
    $dlgDom.ok.dom.bind("click", function() {
      me.template.modal("hide");
    });
  };

  Export.show = function(settings) {
    var me = this;
    this._init()
    .then(function () {
      if (!me.template.is(":visible")) {
        me.settings = $.extend(DEFAULT_SETTING, settings);
        var $dlgDom = domUtil.getDomByName(me.template);
        settings.icon = ICON_MAP[settings.type];
        if (!me.settings.title) {
          me.settings.title = TITLE_MAP[settings.type];
        }

        $dlgDom.title.val(me.settings.title);
        $dlgDom.icon.dom.removeAttr("class");
        $dlgDom.icon.dom.addClass(settings.icon);
        $dlgDom.content.val(me.settings.content);
        loadding.hide()
        .then(function() {
            me.template.modal();
        });
      }
    });
  };

  return Export;
});