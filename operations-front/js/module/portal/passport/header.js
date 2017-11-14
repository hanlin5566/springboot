define([ 'util/requestUtil', 'core/base',
		'util/sessionUtil', 'util/domUtil', 'portal/main/config' ],

function( requestUtil, Base, sessionUtil, domUtil, config) {

	var Header = function() {
	};

	Header.prototype = new Base();

	// 页面初始化
	Header.prototype.create = function() {
		this.renderMainContent("tpl_header");

		var me = this;

		this.find(".nav-content li").on("click", function() {
			var pageCode = $(this).attr("pageCode");
			if (!pageCode) return;
			
			// 允许重复点击
//			if ($(this).find("a").hasClass("active")) return;
			$(this).find("a").addClass("active");
			$(this).siblings().find("a.active").removeClass("active");
			
			config.getPageInfo(pageCode)
			.then(function(page) {
				page = page.getFirstLeafNode();
				
				me.moveTo(page.pageCode);
			});
		});
	};

	// 重新显示
	Header.prototype.show = function() {
		var me = this;
		var pageCode = me.parameter.pageCode;
		if (!pageCode) {
			pageCode = config.DEFAULT_PAGE;
		}
		
		config.getPageInfo(pageCode)
		.then(function(page) {
			var menu = page.getHeaderLevel();
			
			// 选中item
			var $li = me.find(".nav-content li[pageCode=" + menu.pageCode + "]");
			if (!$li.find("a").hasClass("active")) {
				$li.siblings().find("a.active").removeClass("active");
				$li.find("a").addClass("active");
			}
		});
	};

	// 页面隐藏
	Header.prototype.hide = function() {
	};

	return new Header();
});