define([ 'util/requestUtil', 'core/base', 'util/utils',
		'util/sessionUtil', 'util/domUtil', 'portal/main/config' ], function(
		requestUtil, Base, utils, sessionUtil, domUtil, config) {
	var Navbar = function() {
	};

	Navbar.prototype = new Base();
	
	// 页面初始化
	Navbar.prototype.create = function() {
		var me = this;
		me.renderMainContent("tpl_navbar");
	};
	
	Navbar.prototype.renderNavbar = function(conf, navArr) {
		var me = this;
		me.find("li.userheader").siblings().remove();
		for (var k in navArr) {
			var $item = me.renderTpl("tpl_navbar_item", navArr[k]);
			me.$html.append($item);
		}

		// 选中item
		var navMenu = conf.getNavLevel();
		var $li = me.find("li[pageCode=" + navMenu.pageCode + "]");
		if (!$li.hasClass("active")) {
			$li.siblings().removeClass("active");
			$li.addClass("active");
		}

		this.find("li").not(".userheader").on("click", function() {
			var pageCode = $(this).attr("pageCode");
			if (!pageCode) return;
			
			if ($(this).hasClass("active")) return;
			$(this).addClass("active");
			$(this).siblings().removeClass("active");
			
			config.getPageInfo(pageCode)
			.then(function(conf) {
				if (conf.triggerCode) {
					return config.getPageInfo(conf.triggerCode);
				}
				
				return conf;
			}).then(function(conf) {
				var page = conf.getFirstLeafNode();
				me.moveTo(page.pageCode);
			});
		});
	};

	// 重新显示
	Navbar.prototype.show = function() {
		var me = this;
		var pageCode = me.parameter.pageCode;
		if (!pageCode) {
			pageCode = config.DEFAULT_PAGE;
		}
		
		config.getPageInfo(pageCode)
		.then(function(page) {
			var nav = page.getNavLevel();
			
			if (me.find("li.active").attr("pageCode") === nav.pageCode) {
				return;
			}
			
			var navArr = nav.getSibling();
			
			if (!navArr) {
				alert("error pageCode");
				// TODO
				return;
			}
			
			me.renderNavbar(page, navArr);
		});
	};

	// 页面隐藏
	Navbar.prototype.hide = function() {
	};

	return new Navbar();
})