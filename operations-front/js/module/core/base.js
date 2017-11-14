define([ 'core/navigation', 'util/utils', 'util/dataUtil',
		'util/domUtil', 'util/sessionUtil' ], function(navigation,
		utils, dataUtil, domUtil, sessionUtil) {
	/**
	 * constructure
	 */
	var Base = function() {
		this.PAGE_ID_REFIX = "page_";
		this.TEMPLATE_ID_REFIX = "tpl_";
		this.navigation = navigation;
	};

	Base.prototype.baseShow = function() {		
		if ($("#" + this.PAGE_ID_REFIX + this.pageCode).length == 0) {
			this.create();
		}
		this.show();
	};

	Base.prototype.renderMainContent = function(tplId, data) {
		var tpl = this.$template.filter("#" + tplId);
		if (tpl.length == 0) {
			alert("error");
			// TODO
			return;
		}
		this.$html = $(tpl.render(data || {}));
		$(this.$html[0]).attr("id", this.PAGE_ID_REFIX + this.pageCode)
		var $parentContainer = $("#" + this.config.positionId);
		$parentContainer.empty();
		$parentContainer.append(this.$html);

		// TODO 恢复搜索条件
		var $searchContainer = this.$html.find(".search-container:not(.disable-auto) form");
		if ($searchContainer.length > 0) {
			var searchPara = sessionUtil.get(dataUtil.KEY_SEARCH_CONDATION) || {};
			domUtil.setValuesByName($searchContainer, searchPara[me.pageCode]);
		}
	};
	
	Base.prototype.find = function(cond) {
		return this.$html.find(cond);
	};
	
	Base.prototype.renderTpl = function(tplId, data) {
		var tpl = this.$template.filter("#" + tplId);
		if (tpl.length == 0) {
			alert("error");
			// TODO
			return;
		}
		return $(tpl.render(data || {}));
	};

	Base.prototype.baseHide = function() {
		this.hide();
	};
	
	Base.prototype.moveTo = function(pageCode, para) {
		this.navigation.go(pageCode, para);
	};
	
	Base.prototype.hide = function() {
		// for subpage
	};

	return Base;
});