define(["model/menu", "core/navigation", "util/utils", "util/requestUtil"],
    function (Menu, navigation, utils, requestUtil) {

        var Config = function () {
            var me = this;
            navigation.registerConfig(me);

            me.DEFAULT_PAGE = "operaVarList";

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
                pageCode: "my_work",
                label: "系统运维",
                isMenu: true
            });

            var nodeNavbar = new Menu({
                pageCode: "my_work_main",
                isMenu: true,
            });
            level1 = new Menu({
                pageCode: "operaVarList",
                positionId: "layout_manage",
                layoutId: "layout_manage",
                label: "衍生变量"
            });
            var level2 = new Menu({
                pageCode: "operaVarDetail",
                positionId: "layout_manage",
                layoutId: "layout_manage",
                label: "活动消息"

            });
            level1.addChild(level2);
            nodeNavbar.addChild(level1);
            nodeHeader.addChild(nodeNavbar);
            pageTree.addChild(nodeHeader);
            /*******************************/
            nodeHeader = new Menu({
                pageCode: "personal_center",
                label: "个人中心",
                isMenu: true
            });

            nodeNavbar = new Menu({
                pageCode: "personal_center_main",
                isMenu: true
            });

            level1 = new Menu({
                pageCode: "info_myinfo",
                positionId: "layout_manage",
                layoutId: "layout_manage",
                label: "个人信息",
            });

            nodeNavbar.addChild(level1);
            nodeHeader.addChild(nodeNavbar);

            level1 = new Menu({
                pageCode: "system_manage_log",
                positionId: "layout_manage",
                layoutId: "layout_manage",
                label: "日志管理",
            });

            nodeNavbar.addChild(level1);
            nodeHeader.addChild(nodeNavbar);
            pageTree.addChild(nodeHeader);
            me.pageTree = pageTree;

            me.getPageMapping();

            // layout与页面对应关系，每个layout都显示哪些widget，粗匹配，如果涉及到西匹配，这个配置要移动到每个页面内部配置（pageList）
            me.layoutPageMapping = {
                layout_work: [
                    "header",
                    "navbar",
                    "notice",
                    "timetable",
                    "workNavbar",
                    "messageNavbar"
                ],
                layout_manage: [
                    "header",
                    "navbar"
                ]
            }

            // 全部可组合layout container
            me.allLayout = ["layout_header", "layout_navbar", "layout_work", "layout_rightPanel", "layout_manage"];

            // 定制layout，用于配合allLayout控制显示隐藏只有在存在的才显示
            me.layoutMapping = {
                layout_work: [
                    "layout_header",
                    "layout_navbar",
                    "layout_work",
                    "layout_rightPanel"
                ],
                layout_manage: [
                    "layout_header",
                    "layout_navbar",
                    "layout_manage"
                ]
            }
        };

        Config.prototype.getPageMapping = function () {
            var me = this;
            var pageMapping = me.pageTree.exportChildMapping();

            /***********通用页面追加，以下widget均不与地址栏pageCode产生交互************/
            pageMapping["header"] = new Menu({
                // 上部菜单
                pageCode: "header",
                positionId: "layout_header",
                layoutId: "layout_work",
            });

            pageMapping["navbar"] = new Menu({
                // 左侧导航
                pageCode: "navbar",
                positionId: "layout_navbar",
                layoutId: "layout_work",
            });

            pageMapping["workNavbar"] = new Menu({
                // 我的工作导航
                pageCode: "workNavbar",
                positionId: "pageContainer_workNavbar",
                layoutId: "layout_work",
            });

            pageMapping["messageNavbar"] = new Menu({
                // 底部消息列表导航
                pageCode: "messageNavbar",
                positionId: "pageContainer_messageNavbar",
                layoutId: "layout_work",
            });

            pageMapping["deployHomework"] = new Menu({
                // 发布作业
                pageCode: "deployHomework",
                positionId: "pageContainer_workDeploy",
                layoutId: "layout_work",
            });

            pageMapping["deployClassInform"] = new Menu({
                // 发布班级消息
                pageCode: "deployClassInform",
                positionId: "pageContainer_workDeploy",
                layoutId: "layout_work",
            });

            pageMapping["deployRequestLeave"] = new Menu({
                // 提交请假
                pageCode: "deployRequestLeave",
                positionId: "pageContainer_workDeploy",
                layoutId: "layout_work",
            });

            pageMapping["notice"] = new Menu({
                // 右侧通知通告
                pageCode: "notice",
                positionId: "pageContainer_notice",
                layoutId: "layout_work",
            });

            pageMapping["timetable"] = new Menu({
                // 课程表
                pageCode: "timetable",
                positionId: "pageContainer_timetable",
                layoutId: "layout_work",
            });


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

        Config.prototype.getAnalysisMenu = function (root, url) {
            var me = this;
            return $.get("../data/" + url + "/" + url + ".json")
                .then(function (data) {
                    if (typeof data !== 'object') {
                        data = JSON.parse(data);
                    }
                    if (!data.success) {
                        return;
                    }

                    me.concatAnalysisNavbar(root, url, data.data);
                    me.getPageMapping();
                });
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

        Config.prototype.concatAnalysisNavbar = function (root, prefix, data) {
            var me = this;
            root.isMenu = true;
            var nodeNav = new Menu({
                pageCode: prefix + "_group",
                label: "评价分析",
                isMenu: true
            });
            root.addChild(nodeNav);
            for (var i = 0; i < data.length; i++) {
                var d = data[i];
                var c = d.children;
                d.children = null;
                d.pageCode = prefix + "_" + d.pageCode;

                var menu = new Menu($.extend({
                    // 底部消息-已完成作业
                    scriptPath: prefix,
                    positionId: "layout_manage",
                    layoutId: "layout_manage",
                }, d));

                nodeNav.addChild(menu);
                if (c && c.length) {
                    me.concatAnalysisNavbarChild(menu, prefix, c);
                }
            }
        };

        Config.prototype.concatAnalysisNavbarChild = function (root, prefix, data) {
            var me = this;
            for (var i = 0; i < data.length; i++) {
                var d = data[i];
                d.pageCode = prefix + "_" + d.pageCode;
                var menu = new Menu($.extend({
                    // 底部消息-已完成作业
                    scriptPath: prefix,
                    positionId: "layout_manage",
                    layoutId: "layout_manage",
                }, d));

                root.addChild(menu);
            }
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

            var analysisMenu = "";
            if (pageCode.indexOf("analysis_ques") > -1) {
                analysisMenu = "analysis_ques";
            } else if (pageCode.indexOf("analysis_exam") > -1) {
                analysisMenu = "analysis_exam";
            } else {
                alert("config:error pageCode : " + pageCode);
                return def.promise();
            }

            var root = me.pageMapping[analysisMenu];
            me.getAnalysisMenu(root, analysisMenu)
                .then(function () {
                    def.resolve(me.pageMapping[pageCode]);
                });

            return def.promise();
        };

        return new Config();
    });