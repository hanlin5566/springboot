define(['util/formatUtil', 'jquery'], function(formatUtil) {

  var DomUtil = {
      // 纯数字
      TYPE_NUMBER : 1,
      // 带小数
      TYPE_DECIMAL : 2,
      // 带小数，需要10000换算
      TYPE_DECIMAL_SERVER : 3,
      // 从server返回，或者将要传给server的金额，需要10000换算
      TYPE_CURRENCY_SERVER : 4,
      // 调整过金额，不需要除以10000
      TYPE_CURRENCY: 5
  };

  DomUtil.getDomByName = function(dom) {
    return this.getDomByAttr(dom, "name");
  };
  
  DomUtil.getDomByAttr = function(dom, attrName) {
    if (!dom || dom.length == 0) return null;
    
    var ret = {};
    $("[" + attrName + "]", dom).each(function() {
      var $this = $(this);
      ret[$this.attr(attrName)] = {
          dom : $this,
          val : function(val, type) {
            if (!arguments || arguments.length == 0) {
              return this.getVal();
            }
            if ($this.is("input") || $this.is("select") || $this.is("textarea")) {
              $this.val(val);
            } else {
              var newVal = val;
              if (val !== '--') {
                if (type == DomUtil.TYPE_NUMBER) {
                  newVal = formatUtil.formatNumber(val);
                } else if (type == DomUtil.TYPE_DECIMAL) {
                  newVal = formatUtil.formatCurrency(val).toFix(2);
                } else if (type == DomUtil.TYPE_DECIMAL_SERVER) {
                  newVal = formatUtil.fromCurrency(val);
                } else if (type == DomUtil.TYPE_CURRENCY_SERVER) {
                  newVal = formatUtil.fromCurrency(val);
                } else if (type == DomUtil.TYPE_CURRENCY) {
                  newVal = formatUtil.formatCurrency(val);
                }
              }
              
              $this.html(newVal);
            }
          },
          getVal : function(type) {
            var val = null;
            if ($this.is("input") || $this.is("select") || $this.is("textarea")) {
              val = $this.val();
            } else {
              val = $.trim($this.html());
            }
            
            if (type == DomUtil.TYPE_NUMBER) {
              val = Number(val);
            } else if (type == DomUtil.TYPE_DECIMAL) {
              val = Number(val);
            } else if (type == DomUtil.TYPE_DECIMAL_SERVER) {
              val = formatUtil.toCurrency(val);
            } else if (type == DomUtil.TYPE_CURRENCY_SERVER) {
              val = formatUtil.toCurrency(val);
            } else if (type == DomUtil.TYPE_CURRENCY) {
              val = Number(val);
            }
            
            return val;
          }
      };
    });
    
    return ret;
  };
  
  DomUtil.setPosition = function($dom, prop, val) {
    var p = $dom.css(prop);
    p = Number(p.substring(0, p.indexOf("px")));
    $dom.css(prop, (p + val)) + "px";
  };
  
  DomUtil.getPosition = function($dom, prop) {
    var p = $dom.css(prop);
    p = Number(p.substring(0, p.indexOf("px")));
    return p;
  };
  
  DomUtil.getValuesByName = function(dom) {
    var $dom = this.getDomByName(dom);
    var $toggle = dom.find(".dropdown-toggle");
    for (var i=0; i<$toggle.length; i++) {
      var $tmp = this.getDomByName($toggle[i]);
      for (var k in $tmp) {
        $dom[k] && (delete $dom[k]);
      }
    }
    
    var ret = null;
    if ($dom != null) {
      ret = {};
      for (var k in $dom) {
        ret[k] = $dom[k].val();
      }
    }
    
    return ret;
  };
  
  DomUtil.setValuesByName = function(dom, val) {
    var $dom = this.getDomByName(dom);
    if ($dom && val) {
      for (var k in val) {
        if ($dom[k]) {
          $dom[k].val(val[k]);
        }
      }
    }
  };
  
  return DomUtil;
});
