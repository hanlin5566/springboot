define(['core/base'], function(Base) {
	/**
	 * constructure
	 */
	var baseNavbar = function() {
	};

	baseNavbar.prototype = new Base();

	// 页面隐藏
	baseNavbar.prototype.baseHide = function() {
		if (this.curPage) {
			this.curPage.baseHide();
		}
		this.hide();
	};

	return baseNavbar;
});