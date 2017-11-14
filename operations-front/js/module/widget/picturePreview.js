define(['util/utils', 'util/codeUtil', 'util/domUtil'],
    function(utils, codeUtil, domUtil) {

  var Export = {
    param: {},
    template: null,
    inited: false
  };

  Export._init = function() {
    var me = this;
    if (!me.inited) {
      return me.loadTemplate().then(function(html) {
        me.template = html;
//        me.bindEvent();
        me.inited = true;
      });
    }
    
    return $.Deferred().resolve().promise();
  };

  Export.loadTemplate = function() {
    return utils.loadTemplate(null, 'picture-preview-template', 'widget/picturePreview.tpl')
        .then(function(html) {
          $("#widget-container").append(html);
          return $.Deferred().resolve(html).promise();
        });
  };

  Export.show = function(picSrc) {
    var me = this;
    this._init()
    .then(function () {
      if (!me.template.is(":visible")) {
        me.template.find(".modal-body").remove();
        me.template.find(".modal-header").after("<div class='modal-body'><img /></div>");
        me.template.find("img").attr("src", picSrc);
        me.template.modal();
      }
    });
  };
  
  return Export;
});