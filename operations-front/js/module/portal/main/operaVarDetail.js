define([ 'util/requestUtil', 'core/base', 'util/sessionUtil', 'util/domUtil',
		'portal/main/config', '../../../lib/ace/ace'], function(requestUtil, Base,
		sessionUtil, domUtil, config) {
	var OperaVarDetail = function() {
	};

	OperaVarDetail.prototype = new Base();
	// 页面初始化
	OperaVarDetail.prototype.create = function() {
		var me = this;
		me.renderMainContent("tpl_operaVarDetail");
		me.initAceEditor();
		me.bindInitEvent();
		me.switchState(me.parameter.state);
	};
	//根据传入的状态设置右上角按钮文字及传入后台的内容
	OperaVarDetail.prototype.switchState = function(state) {
		var me = this;
		//点击详情时如果为已发布则置为只读
		if ('PUBLISHED' == state) {
			me.find(".varName").attr("readonly", "readonly");
			me.find(".varCode").attr("readonly", "readonly");
			me.find(".queryIface").attr("readonly", "readonly");
			me.find(".clazzName").attr("readonly", "readonly");
			editor.setReadOnly(true);
			me.find('.default-btn').hide();
		} else if('SAVED' == state){
			//保存状态右上角应该为编译，可以修改
			me.find('.default-btn').text("编译");
			me.find('.default-btn').attr("deployStatus","COMPILED");
		}else if('COMPILED' == state){
			//编译通过，则右上方按钮应该为发布，并且不能修改。
			me.find('.default-btn').text("发布");
			me.find('.default-btn').attr("deployStatus","PUBLISHED");
		}else{
			//新建
			me.find('.default-btn').attr("deployStatus","SAVED");
		}
	}
	//提交内容
	OperaVarDetail.prototype.postContent = function(state) {
		var me = this;
		var url = "derived";
		var varName = me.find(".varName").val();
		var varCode = me.find(".varCode").val();
		var queryIface = me.find(".queryIface").val();
		var clazzName = me.find(".clazzName").val();
		var clazzPath = me.find(".clazzPath").val();
		var content = editor.getValue();
		// 验证标题与内容
		if (varName | varName.length <= 0) {
			alert('请填写变量名称');
			me.find(".varName").focus();
			return;
		}
		if (varCode | varCode.length <= 0) {
			alert('请填写变量标识');
			me.find(".varCode").focus();
			return;
		}
		if (queryIface | queryIface.length <= 0) {
			alert('请填写取数接口');
			me.find(".queryIface").focus();
			return;
		}
		if (clazzName | clazzName.length <= 0) {
			alert('请填写变量类名');
			me.find(".clazzName").focus();
			return;
		}
		if (content | content.length <= 0) {
			editor.focus();
			alert('请填写算法代码');
			return;
		}
		var data = {
			"varId" : me.find("#varId").val(),
			"varName" : varName,
			"varCode" : varCode,
			"queryIface" : queryIface,
			"clazzName" : clazzName,
			"content" : content,
			"clazzPath" : clazzPath,
			"state" : state
		};
		requestUtil.post(url, data).then(function(result) {
			if (result.code == 200) {
				me.find("#varId").val(result.data);
				if (state == 'SAVED') {
					// 保存提示
					alert("保存成功");
					//保存成功将右上角和当前状态修改为编译
					me.find('.default-btn').text("编译");
					me.find('.default-btn').attr("deployStatus","COMPILED");
				} else {
					// 发布跳转
					me.moveTo('operaVarList');
				}
			} else {
			}
		});
	};

	OperaVarDetail.prototype.bindInitEvent = function() {
		var me = this;
		me.find('.default-btn').on('click', function() {
			var postStatus = me.find('.default-btn').attr('deployStatus');
			me.postContent(postStatus);
		});
	};

	// 重新显示 绑定数据后绑定点击事件（暂时这么做）
	OperaVarDetail.prototype.show = function() {
		var me = this;
		me.renderPage();
	};

	OperaVarDetail.prototype.renderPage = function() {
		var me = this;
	};

	// 清空数据
	OperaVarDetail.prototype.clearList = function() {
		var me = this;
	};

	// 页面隐藏
	OperaVarDetail.prototype.hide = function() {
		var me = this;
	};

	// 页面弹窗
	OperaVarDetail.prototype.popupWindow = function() {
		var me = this;

	};

	// 初始化ace
	OperaVarDetail.prototype.initAceEditor = function() {
		var me = this;
		// 初始化对象
		editor = ace.edit("aceEditor");

		// 设置风格和语言（更多风格和语言，请到github上相应目录查看）
//		theme = "clouds"
		theme = "twilight"
		language = "java"
		editor.setTheme("ace/theme/" + theme);
		editor.session.setMode("ace/mode/" + language);

		// 字体大小
		editor.setFontSize(18);

		// 设置只读（true时只读，用于展示代码）
		editor.setReadOnly(false);

		// 自动换行,设置为off关闭
		editor.setOption("wrap", "free")

		// 启用提示菜单 ext-language_tools 无法引入
//		editor.setOptions({
//			enableBasicAutocompletion : true,
//			enableSnippets : true,
//			enableLiveAutocompletion : true
//		});
		//如果有ID则填充内容
		if(me.parameter.varId){
        	var url = "derived/"+me.parameter.varId;
        	requestUtil.get(url,null).then(function(result) {
				if (result.code == 200) {
					var varId = result.data.varId;
					var varName = result.data.varName;
					var varCode = result.data.varCode;
					var queryIface = result.data.queryIface;
					var clazzName = result.data.clazzName;
					var clazzPath = result.data.clazzPath;
					var content = result.data.content;
					me.find("#varId").val(varId);
					me.find(".varName").val(varName);
					me.find(".varCode").val(varCode);
					me.find(".queryIface").val(queryIface);
					me.find(".clazzName").val(clazzName);
					me.find(".clazzPath").val(clazzPath);
					editor.setValue(content);
				}else{
				}
			});
        }
	};

	return new OperaVarDetail();
})