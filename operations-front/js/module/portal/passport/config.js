define(["model/menu", "core/navigation", "util/utils", "util/requestUtil"],
    function (Menu, navigation, utils, requestUtil) {

        var Config = function () {
            var me = this;
            navigation.registerConfig(me);

            me.DEFAULT_PAGE = "login";

            // 页面列表，这里配置多于实际页面数，例如消息列表页分为三种pageCode但是共用一个js文件，所以scriptPath相同pageCode不同
            // 如果scriptPath不设置，默认为和pageCode一致
            var pageTree = new Menu({
                pageCode: "__ROOT__"
            });

            /**
             * nodeHeader ：主header
             * nodeNavbar : 左侧导航
             * level1 : 一级页面
             * level2 : 二级页面
             * level3 : 三级页面
             */
            /**************系统运维*****************/
            var nodeHeader = new Menu({
                pageCode: "common",
                label: "通用",
                isMenu: true
            });

            var nodeNavbar = new Menu({
                pageCode: "common_nav",
                isMenu: true,
            });

            var level1 = new Menu({
                pageCode: "login",
                label: "登录",
                positionId: "main_container",
                layoutId: "layout_main",
            });

            nodeNavbar.addChild(level1);
            nodeHeader.addChild(nodeNavbar);
            pageTree.addChild(nodeHeader);
            me.pageTree = pageTree;

            me.getPageMapping();

            // layout与页面对应关系，每个layout都显示哪些widget，粗匹配，如果涉及到细匹配，这个配置要移动到每个页面内部配置（pageList）
            me.layoutPageMapping = {
                layout_main: []
            }

            // 全部可组合layout container
            me.allLayout = ["layout_main"];

            // 定制layout，用于配合allLayout控制显示隐藏只有在存在的才显示
            me.layoutMapping = {
            	layout_main: [
                    "main_container"
                ]
            }
        };

        Config.prototype.getPageMapping = function () {
            var me = this;
            var pageMapping = me.pageTree.exportChildMapping();

            // 把page列表映射为Menu对象
            me.pageMapping = pageMapping;
        };

        /**
         * pageCode
         */
        Config.prototype.refreshLayout = function (para) {
            var me = this;
            return me.getPageInfo(para.pageCode)
                .then(function (page) {
                    if (!page) {
                        return;
                    }
                    var layout = me.layoutMapping[page.layoutId];
                    if (!layout) {
                        // TODO
                        alert("no layout found");
                        return;
                    }

                    for (var k in me.allLayout) {
                        var val = me.allLayout[k];
                        var flag = utils.inArr(layout, val);
                        $("#" + val)[flag ? "show" : "hide"]();
                    }
                });
        };

        Config.prototype.refreshPages = function (para) {
            var me = this;
            return me.getPageInfo(para.pageCode)
                .then(function (page) {
                    var pageArr = me.layoutPageMapping[page.layoutId];
                    if (!pageArr) {
                        return;
                    }

                    pageArr = pageArr.concat(para.pageCode);

                    var oldPageList = me.curPageList;

                    // 发送hide通知
                    if (oldPageList) {
                        for (var k in oldPageList) {
                            var page = oldPageList[k];
                            if (!utils.inArr(pageArr, page)) {
                                page.baseHide();
                            }
                        }
                    }

                    me.curPageList = [];

                    // 发送show通知
                    for (var k in pageArr) {
                        var pageCode = pageArr[k];
                        var pageConfig = me.getPageInfo(pageCode)
                            .then(function (pageConfig) {
                                me.loadPage(pageCode, pageConfig, para, loadCb);
                            });
                    }
                });

            function loadCb(widget) {
                me.curPageList.push(widget);
            };
        };

        Config.prototype.loadPage = function (pageCode, pageConfig, para, callback) {
            var me = this;
            if (!pageCode) {
                alert("config loadPage pageCode undefined");
                return;
            }

            var reqArr = [];
            reqArr.push('portal/' + requestUtil.setting.appCode + "/" + pageConfig.scriptPath);
            reqArr.push('text!../../template/portal/' + requestUtil.setting.appCode + "/" + pageConfig.scriptPath + ".html");
            if (pageConfig && pageConfig.hasCss) {
                reqArr.push('css!../../theme/default/css/' + requestUtil.setting.appCode + "/" + pageConfig.scriptPath);
            }
            requirejs(reqArr, function (widgt, tpl) {
                widgt.pageCode = pageCode;
                widgt.config = pageConfig;
                widgt.parameter = para;
                widgt.$template = $(tpl);
                widgt.baseShow();
                callback(widgt);
            });
        };

        Config.prototype.getMenuMapping = function (code) {
            var me = this;
            return me.layoutPageMapping[code];
        };

        Config.prototype.getPageInfo = function (pageCode) {
            var me = this;
            var def = new $.Deferred();
            var page = me.pageMapping[pageCode];
            if (page && (!(page.isMenu && !page.children.length) || page.triggerCode)) {
                return def.resolve(page).promise();
            }

            alert("config:error pageCode : " + pageCode);
            return def.promise();
        };

        return new Config();
    });