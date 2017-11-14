define([ 'util/requestUtil', 'core/base', 'util/sessionUtil', 'util/domUtil',
        'portal/main/config', 'widget/dropDown', 'widget/table', 'util/dateUtil', 'bootstrapTable', 'icheck'],
    function(requestUtil, Base, sessionUtil, domUtil, config, dropDown, Table, dateUtil) {
        var ManageLog = function() {
        };
        ManageLog.prototype = new Base();

        ManageLog.prototype.queryParams = function(params) {
            var me = this;
            var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit : params.limit, // 页面大小
                offset : params.offset, // 页码
                realName : me.find('input[name="realName"]').val(),
                userName : me.find('input[name="userName"]').val(),
                userType : me.find('input[name="userType"]').val()
            };
            return temp;
        };

        // 页面初始化
        ManageLog.prototype.create = function () {
            var me = this;
            me.renderMainContent("tpl_manage_log");

            // 日历初始化
            var date = new Date();
            var today = dateUtil.dateFormat(dateUtil.DATE_PATTERN, date);
            me.find("#calendar").val(today);
            me.find('#calendar').mobiscroll().calendar({
                theme: 'mobiscroll',
                lang: 'zh',
                display: 'bubble',
                closeOnSelect: true,
                mode: 'clickpick',
                buttons: [],
                dateFormat: 'yy-mm-dd'
            });

            // 加载日志信息
            me.renderPage();

            // 搜索点击事件
            me.find("a[name='searchBtn']").click(function() {
                //根据查询条件重新加载
                $('#tb_log').bootstrapTable('refresh',me.queryParams);
            });
        };

        // 用table插件加载数据
        ManageLog.prototype.renderPage = function () {
            var me = this;

            // table里面需要绑定的评价加载事件  查看详情
            var operateEvents = {
                'click .default-btn.tc': function (e, value, row, index) {
                    me.timeCompare();
                    me.clearClassAssesss();
                    me.classAssessFrame(row.studentId);
                    if (!row.states) return;
                    if (row.states.name == 'NO') return;
                    var timeTableId = me.find(".subjectList li.checked").attr("timeTableId");
                    var data = {
                        "extId": timeTableId,
                        "subId": row.studentId
                    };
                    me.getStudentAssess(data);
                }
            };

            var url = " /log/login";
            var $table = new Table(
                me.find("#tb_log"),
                {
                    url : "http://192.168.0.59:8380" + url, // 请求后台的URL（*）
                    toolbar: me.find('#toolbar'), // 工具按钮用哪个容器
                    queryParams: $.proxy(me.queryParams, this),// 传递参数（*）
                    sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）假数据用client
                    responseHandler: function (res) {
                        if (!res.success) {
                            alert('获取日志发生异常');
                            return;
                        }
                        return {
                            rows: res.data,
                            total: res.pageInfo.totalCount
                        };
                    },
                    uniqueId: "userId", // 每一行的唯一标识，一般为主键列
                    columns: [{
                        checkbox: true,
                    }, {
                        field: 'realName',
                        title: '姓名',
                    }, {
                        field: 'applicationId.name',
                        title: '用户名',
                    }, {
                        field: 'userType.text',
                        title: '用户类型',
                    }, {
                        field: 'ipAdress',
                        title: 'IP地址'
                    }, {
                        field: 'loginTime',
                        title: '登录时间'
                    },{
                        field: 'status.name',
                        title: '操作',
                        events: operateEvents,
                        formatter: function (value, row, index) {
                            return '<a class="default-btn tc" state="' + value + '">查看详情</a>';
                        }
                    }]
                });
        };

        // 重新显示
        ManageLog.prototype.show = function() {

        };

        // 页面隐藏
        ManageLog.prototype.hide = function() {
        };

        return new ManageLog();
    });
