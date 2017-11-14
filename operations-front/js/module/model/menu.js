define(function() {

	/**
	 * nodeHeader ：主header
	 * nodeNavbar : 左侧导航 
	 * level1 : 一级页面 
	 * level2 : 二级页面 
	 * level3 : 三级页面
	 * 
	 */
	var CONST_nodeHeaderLevel = 1;
	var CONST_nodeNavbarLevel = 3;
	var CONST_firstLeafPage = 4;
	
	var Menu = function(setting) {
		// 是否有css文件（大多数页面用统一style.css，所以没有）
		this.hasCss = false;
		// 标志该页面的唯一id，要与文件名一致
		this.pageCode = null;
		// 脚本文件路径
		this.scriptPath = null;
		// 菜单内容
		this.label = null;
		// 浏览器标题
		this.pageTitle = null;
		// 图标
		this.icon = null;
		// 是否不在菜单里显示，用于子页面，只能由页面点进去，而不是菜单
		this.hide = false;
		// 需要什么权限
		this.permission = null;
		// 略过权限验证，例如登录页面
		this.skipAuth = false;
		// 模版加载后刷新主页面什么区域
		this.positionId = null;
		// 所属布局名称
		this.layoutId = null;
		// 所属菜单code（父页面pageCode）
		this.menuCode = null;
		
		// 当前node是否菜单项
		this.isMenu = false;
		// 触发另一个菜单项，非自己父子关系跳转
		this.triggerCode = null;

		this.level = 0;

		// 扩展
		$.extend(this, setting);

		this.scriptPath = this.scriptPath || this.pageCode;
		this.menuCode = this.menuCode || this.pageCode;

		// 参数调整
		this.skipAuth = !!this.skipAuth;
		this.hide = !!this.hide;
		this.pageTitle = this.pageTitle || this.label;

		this.parent = null;
		this.children = [];
		this.firstChild = null;
	};

	Menu.prototype.addChild = function(child) {
		if (!child) {
			alert("child cannot be null");
		}
		if (this.children.length == 0) {
			this.firstChild = child;
		}
		this.children.push(child);

		child.setParent(this);
		child.setLevel();
	};

	Menu.prototype.setParent = function(parent) {
		this.parent = parent;
	};

	Menu.prototype.getParent = function() {
		return this.parent;
	};

	Menu.prototype.getChildren = function() {
		return this.children;
	};

	Menu.prototype.hasVisibleChildren = function() {
		var sub = this.children;
		if (sub) {
			for ( var k in sub) {
				if (!sub[k].hide) {
					return true;
				}
			}
		}

		return false;
	};

	Menu.prototype.getVisibleParent = function() {
		if (this.hide) {
			return this.parent && this.parent.getVisibleParent();
		} else {
			return this;
		}
	};

	Menu.prototype.getTitle = function() {
		var header = this.getHeaderLevel();
		var title = header.pageTitle || header.label;
		title += "-"
		title += this.pageTitle || this.label;
		title += "(汇中集团-风控系统运维中心)";
		return title;
	};

	Menu.prototype.setLevel = function() {
		this.level = this.getParent().getLevel() + 1;
		for ( var k in this.children) {
			this.children[k].setLevel();
		}
	};

	Menu.prototype.getLevel = function() {
		return this.level;
	};

	Menu.prototype.getHeaderLevel = function() {
		return this.getParentLevelNode(CONST_nodeHeaderLevel);
	};

	Menu.prototype.getParentLevelNode = function(level) {
		var node = this;
		while (node.getLevel() > level) {
			node = node.getParent();
		}

		return node;
	};

	Menu.prototype.getNavLevel = function() {
		return this.getParentLevelNode(CONST_nodeNavbarLevel);
	};

	Menu.prototype.getClickPath = function() {
		var clickPath = [ this.label ];
		var travelNode = this.getParent();
		while (travelNode) {
			clickPath.push(travelNode.label);
			travelNode = travelNode.getParent();
		}
		clickPath.reverse();

		return clickPath.join('/');
	};
	
	Menu.prototype.getFirstLeafNode = function() {
		var node = this;
		if (!node.isMenu) {
			return node;
		}
		
		var lvl = CONST_firstLeafPage - node.getLevel();
		for (var i=0; i<lvl; i++) {
			if (!node) break;
			
			node = node.firstChild;
			if (node && !node.isMenu) {
				return node;
			}
		}
		
		return node;
	};
	
	Menu.prototype.getSibling = function() {
		var node = this;
		return node.getParent().children;
	};

	Menu.prototype.exportChildMapping = function() {
		var map = {};
		this.exportChildMapping_(this, map);
		
		return map;
	};
	
	Menu.prototype.exportChildMapping_ = function(node, map) {
		for (var i=0; i<node.children.length; i++) {
			map[node.children[i].pageCode] = node.children[i];
			this.exportChildMapping_(node.children[i], map);
		}
	};

	Menu.prototype.remove = function() {
		if (!this.parent) {
			return;
		}

		var children = this.parent.getChildren();
		var index = -1;
		for ( var k in children) {
			if (children[k].pageCode == this.pageCode) {
				index = k;
				break;
			}
		}
		if (index !== -1) {
			children.splice(index, 1);
		}
		if (children.length == 0) {
			this.parent.remove();
		}
	};

	return Menu;
});