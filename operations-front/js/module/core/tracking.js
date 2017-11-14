define(['core/navigation'], function(navigation) {
  
  var Tracking = function() {
  	this.bindEvent();
  	this.loadCnzzScript();
  };
  
  Tracking.prototype.bindEvent = function() {
  	var me = this;
//    eventBus.bind('navigate', function (event, menuData) {
//    	var menu =  navigation.getMenuByMenuCode(menuData.menuCode);
//	    	if (menu) {
//	    		me.trackPageview(menu.getClickPath(), menuData.menuCode);
//	    	}
//      });
  };

  Tracking.prototype.trackPageview = function(title, refer) {
  	window._czc = window._czc || [];
		window._czc.push(["_trackPageview", title, refer])
  };

  Tracking.prototype.loadCnzzScript = function() {
//  	$.getScript('http://s9.cnzz.com/z_stat.php?id=1253213019&web_id=1253213019');
  };

  return new Tracking();
});