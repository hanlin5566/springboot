define(['core/navigation', 'util/requestUtil', 'core/base'],
    function(navigation, requestUtil, Base) {
  
  var Export = function() {
    this.bindInitEvent();
  };
  
  Export.prototype = new Base();
  
  Export.prototype.bindInitEvent = function() {
    var me = this;
    eventBus.bind('navigate', function (event, menu) {
      me.refreshHeader();
    });
  };
  
  Export.prototype.loadTemplate = function() {
    return requestUtil.loadTemplate($('#subHeader'), 'subheader-template', 'common/subheader.tpl');
  };
  
  Export.prototype.addButton = function(btns, btnContainer, reverse) {
    if (!btns) {
      return;
    }
    
    btnContainer = btnContainer || $("div.pull-right", this.template);
    $(btns).each(function() {
      var button = $("<a href='javascript:void(0)' class='btn " + (this.btnIcon || "btn-danger") + "'></a>");
      button.html(this.btnName);
      if (reverse) {
        btnContainer.prepend(button);
      } else {
        btnContainer.append(button);
      }
      
      if (this.href) {
        button.attr("href", this.href);
      }
      
      if (this.target) {
        button.attr("target", this.target);
      }
      
      if (this.btnClick) {
        var ele = this;
        button.bind("click", function(e) {
          ele.btnClick();
          e.preventDefault();
        });
      }
    });
  };
  
  Export.prototype.refreshHeader = function(para) {
    if (!this.template) {
      return;
    }
    var me = this;
    var btnContainer = $("div.pull-right", me.template);
    btnContainer.empty();
    
    if (para) {
      $('#subHeader').show();
      $("div.pull-left", me.template).html(para.menu.getTitle());
      me.addButton(para.btns, btnContainer);
    } else {
      $('#subHeader').hide();
    }
  };

  return new Export();
});