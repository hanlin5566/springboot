define(['util/logger', ],
        function(logger) {

	// 系统运维
	var DOMAIN_main = "localhost/main";
	// 评价分析
	var DOMAIN_ANALYTICS_QUESNAIRE = "localhost/analytics_quesnaire";
	// 学业水平
	var DOMAIN_ANALYTICS_SCORE = "localhost/analytics_score";
	// 个人中心
	var DOMAIN_PERSONAL_CENTER = "localhost/personal_center";
	// 系统管理
	var DOMAIN_SYSTEM_MANAGE = "localhost/system_manage";

    var TEST_DOMAIN = false;
    var FRONT_DOMAIN = "operationsWebIf";
    var SERVER_DOMAIN = document.domain;
    if (SERVER_DOMAIN.indexOf('edupj') < 0) {
        SERVER_DOMAIN = 'localhost';
        TEST_DOMAIN = true;
    }

    var SERVER_URI = "http://" + SERVER_DOMAIN +"/"+ FRONT_DOMAIN;
    var LOGIN_URI = "./passport.html?pageCode=login";
    var ERR_SECURITY_URI = "./err_security.html";
    var ERR_INTERNAL_URI = "./err_internal.html";
    var ERR_PAGE_NOT_FOUND_RUI = "./err_page_not_found.html";
    var ERR_OTHER = "./error.html";
    var HOMEPAGE_URI = "./index.html";

    var Export = {};
    Export.HOMEPAGE_URI = HOMEPAGE_URI;

    Export.setting = {
            templateBase : "",
            appVersion : null, // 版本号会在config.js里面设置，不要在这里更改，这里只是声明
            SERVER_URI : SERVER_URI,
            TEST_DOMAIN : TEST_DOMAIN,
            HOMEPAGE_URI: HOMEPAGE_URI,
            SERVER_DOMAIN : SERVER_DOMAIN,
            LOGIN_URI : LOGIN_URI,

            ERR_SECURITY_URI : ERR_SECURITY_URI,
            ERR_INTERNAL_URI : ERR_INTERNAL_URI,
            ERR_PAGE_NOT_FOUND_RUI : ERR_PAGE_NOT_FOUND_RUI,
            ERR_OTHER : ERR_OTHER,
			
            DOMAIN_main : DOMAIN_main,
            DOMAIN_ANALYTICS_QUESNAIRE : DOMAIN_ANALYTICS_QUESNAIRE,
            DOMAIN_ANALYTICS_SCORE : DOMAIN_ANALYTICS_SCORE,
            DOMAIN_PERSONAL_CENTER : DOMAIN_PERSONAL_CENTER,
            DOMAIN_SYSTEM_MANAGE : DOMAIN_SYSTEM_MANAGE,
			EOF_ : null
    };
    
    function loadTemplateComplete(def, ele, content, data) {
        var html = $(content.render(data));
        if (ele) {
            ele.empty();
            ele.append(html);
            def.resolve(ele);
        } else {
            def.resolve(html);
        }

        // 图片不能成功显示的时候
        /*$("img", html).each(function() {
            var error = false;
            if (!this.complete) {
                error = true;
            }

            if (typeof this.naturalWidth != "undefined" && this.naturalWidth == 0) {
                error = true;
            }

            if (error) {
                $(this).bind('error.replaceSrc', function() {
                    this.src = "default_image_here.png";
                    $(this).unbind('error.replaceSrc');
                }).trigger('load');
            }
        });*/
    }

    Export.loadTemplate = function(ele, tplId, url, data) {
        url = Export.setting.templateBase + url;
        var newDef = $.Deferred();
        if ($("#" + tplId).length > 1) {
            alert("duplicate template error");
            return;
        }
        if ($("#" + tplId).length > 0) {
            loadTemplateComplete(newDef, ele, $('#' + tplId), data);
            return newDef.promise();
        }

        $.get(url, {v: this.setting.appVersion}, function(content) {
            $('#template-container').append($(content));
            loadTemplateComplete(newDef, ele, $('#' + tplId), data);
        });

        return newDef.promise();
    };

    // 直接加载已经load完毕的template，例如商品管理，在主模板load之后，table模板也已经load完毕，所以就是同步获取模板并render，简化datatable操作.
    Export.loadTemplateLocal = function(ele, tplId, data) {
        if ($("#" + tplId).length == 0) {
            logger.error("===========:", ele, tplId, data);
            alert("no template error");
            return;
        }
        if ($("#" + tplId).length > 1) {
            alert("duplicate template error");
            return;
        }

        data = data || {};
        var html = $($('#' + tplId).render(data));
        if (ele) {
            ele.empty();
            ele.append(html);
            return ele;
        }

        return html;
    };

    Export.loadDialogTemplate = function(dlgId, tplId, data) {
        if ($("#" + dlgId).length == 0) {
            $("#content").append($('#' + tplId).render(data));
        }
        if ($("#" + tplId).length > 1) {
            alert("duplicate template error");
            return;
        }

        var form = $("form", $("#" + dlgId))[0];
        form && form.reset();

        return $("#" + dlgId);
    };

    function openLoginDialog() {
//        eventbus.trigger("navigateNoHistory", {menuCode: "loginDialog"});
    }

    var errorMessage = {
        "401": "登录超时 弹出登录对话框",
        "403": "您无权进行此操作，请与管理员联系",
        "404": "您的操作无法处理，请稍候再试",
        "500": "发生系统错误，请与管理员联系"
    };

    function processErrorLink(code, url) {
    	requirejs(["widget/dialogAlert"], function(dialogAlert) {
    		var msg = errorMessage[code] || "发生网络错误，请与管理员联系";
    		dialogAlert.show({
    		    type : dialogAlert.TYPE_ERROR,
    		    title : "发生错误(" + code + ")",
    		    content : msg
    		});
    	});
    }

    function handleApiResponseStatus(data) {
        if (!data || !data.code) {
            logger.error(data);
            return false;
        }

        var code = Number(data.code);
        if (code == 401) { // 登录超时 弹出登录对话框
            openLoginDialog();
            return false;
        }
        else if (code == 403) { // 权限验证失败
            processErrorLink(code, ERR_SECURITY_URI);
            return false;
        }

        return true;
    }

    function handleHttpResponseStatus(status) {
        status = Number(status);
        if (status == 404) { // 页面未找到
            processErrorLink(status, ERR_PAGE_NOT_FOUND_RUI);
        } else if (status >= 500) { // 内部错误
            processErrorLink(status, ERR_INTERNAL_URI);
        } else { // 其他错误
            processErrorLink(status, ERR_OTHER);
        }
    }
    
    Export.ajax = function(url, param, method, skipValidation, useCache) {
        var def = $.Deferred();
        var me = this;

        me.ajaxInternal_(def, url, param, method, skipValidation, useCache);
        
        return def.promise();
    };
    
    Export.ajaxInternal_ = function(newDef, url, param, method, skipValidation, useCache) {
        if (url) {
            if (url.indexOf("http") == -1) {
                url = SERVER_URI + url;
            }
        } else {
            alert('error');
            throw 'no url';
        }
        
        // 加上userType参数
        if (method === "GET" || method === "get") {
            // ignore
        } else {
            param = (typeof param === "string") ? param : JSON.stringify(param);
        }

        $.ajax({
            url: url,
            type: method.toUpperCase(),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: param,
            cache: !!useCache,
            success: function(data) {
                if (skipValidation) {
                    newDef.resolve(data);
                } else if (handleApiResponseStatus(data)) {
                    newDef.resolve(data);
                } else {
					alert("system error");
				}
            },
            error: function(request, textStatus) {
                handleHttpResponseStatus(request.status);
                logger.error(request, textStatus);
            }
        });
    };

    Export.post = function(url, param, skipValidation) {
        return Export.ajax(url, param, 'POST', skipValidation);
    };

    Export.put = function(url, param, skipValidation) {
        return Export.ajax(url, param, 'PUT', skipValidation);
    };

    Export.get = function(url, param, useCache, skipValidation) {
        if (!useCache) {
            if (!param) {
                param = "v=" + this.setting.appVersion;
            } else {
                if (typeof param == 'string') {
                    param += "&v=" + this.setting.appVersion;
                } else    {
                    param.v = this.setting.appVersion;
                }
            }
        }
        return Export.ajax(url, param, "GET", skipValidation, useCache);
    };

    Export.del = function(url, param, skipValidation) {
        return Export.ajax(url, param, "DELETE", skipValidation);
    };

    return Export;
});