define([ 'util/requestUtil', 'core/base','util/formatUtil',
		'util/sessionUtil', 'util/domUtil','util/dateUtil','mobiscroll', 'portal/main/config','widget/table', 'bootstrapTable'], function(
		requestUtil, Base,formatUtil, sessionUtil, domUtil, dateUtil, mobiscroll,config,Table) {
	
	var OperaVarList = function() {
		
	};

	OperaVarList.prototype = new Base();
	
	var sessionId;
	
	OperaVarList.prototype.queryParams = function(params) {
        var me = this;
        var sessionIdParameter = sessionId;
        var temp = { // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit : params.limit, // 页面大小
            offset : params.offset, // 页码
            sessionId : sessionIdParameter,
        };
        var varName = me.find("#varName").val();
        if(varName.length > 0){
        	 temp.varName = varName;
        }
        var queryIface = me.find("#queryIface").val();
        if(queryIface.length > 0){
        	temp.queryIface = queryIface;
        }
        var state = me.find("#state").val();
        if(state.length > 0){
        	temp.state = state;
        }
        var calendar = me.find("#calendar").val();
        if(calendar.length > 0){
        	 temp.deployTime = calendar;
        }
        return temp;
    };
	
	// 页面初始化
	OperaVarList.prototype.create = function() {
		var me = this;
		me.renderMainContent("tpl_operaVarList");
		me.renderPage();
		me.bindEvent();
		me.find('#calendar').mobiscroll().calendar({
			theme: 'mobiscroll',
			lang: 'zh', 
			display: 'bubble',
			buttons:[],
			showOnFocus: false,
			mode:'clickpick',
			dateFormat: 'yy-mm-dd',
			onDayChange : function(day, inst) {
				date = dateUtil.dateFormat(dateUtil.DATE_PATTERN, day.date);
				me.find("#calendar").val(date);
				inst.hide();
			}
		});
	};
	
	OperaVarList.prototype.renderPage = function() {
		var me=this;
		var operateEvents = {
				'click #PUBLISHED': function (e, value, row, index) {
					var url = "msg";
					var data = {
						"varId":value,
						"state":'PUBLISHED'
					};
					requestUtil.post(url, data).then(function(result) {
						if(result.code==200){
							me.find('#tb_var').bootstrapTable('refresh', me.queryParams);
						}
					});
				},
				'click #varDetail': function (e, value, row, index) {
					me.moveTo('operaVarDetail',{
						'varId' : value,
						'state': row.state.name
					});
				}
		};
		
		var url = "/derived"
		var $table = new Table(
				me.find("#tb_var"),
				{
					url: url,// 请求后台的URL（*）
					toolbar: me.find('#toolbar'), // 工具按钮用哪个容器
					queryParams: $.proxy(me.queryParams, this),//传递参数（*）
					sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）假数据用client
					uniqueId: "varId", // 每一行的唯一标识，一般为主键列
					columns:[
					      {
					    	  checkbox: true,
					      },
					      {
					    	  field: 'varName',
		                      title: '名称'
					      },
					      {
					    	  field: 'varCode',
		                      title: '标识'
					      },
					      {
					    	  field: 'queryIface',
		                      title: '取数接口'
					      },
					      {
					    	  field: 'clazzName',
					    	  title: '算法类'
					      },
					      {
					    	  field: 'state',
					    	  title: '状态',
					    	  formatter: function (value, row, index) {
		                            return value.text;
		                      }
					      },
					      {
					    	  field: 'deployTime',
		                      title: '发布时间'
					      },
					      {
					    	  field: 'varId',
		                      title: '操作',
		                      events: operateEvents,
		                      formatter: function (value, row, index) {
		                    	  
		                    	  //已保存
		                    	  if(row.state.name == 'SAVED'){
		                    		  return '<a class="state-link" id="varDetail" varId="'+value+'">详情</a>';
		                    	  //编译通过//只有编译通过才有发布按钮
		                    	  }else if(row.state.name == 'COMPILED'){
		                    		  return '<a class="state-link" id="PUBLISHED" varId="'+value+'">发布</a>' + '<a class="state-link" id="varDetail" varId="'+value+'">详情</a>';
		                    	  //已发布
		                    	  }else if(row.state.name == 'PUBLISHEDED'){
		                    		  return '<a class="state-link" id="varDetail" varId="'+value+'">详情</a>';
		                    	  }else{
		                    		  return '<a class="state-link" id="varDetail" varId="'+value+'">详情</a>';
		                    	  }
		                      }
					      },
					],
					onCheck: function(){
						 if($("#remove").attr("disabled")){
							 $('#remove').removeAttr("disabled");
						 }
					 },
					 onUncheck: function(){
						 if($('#tb_student').bootstrapTable('getSelections').length == 0){
							 if(!$("#remove").attr("disabled")){
								 $('#remove').attr("disabled",'disabled');
							 }
						 }
					 },
					 onUncheckAll: function(){
						 if(!$("#remove").attr("disabled")){
							 $('#remove').attr("disabled",'disabled');
						 }
					 },
					 onCheckAll: function(){
						 if($("#remove").attr("disabled")){
							 $('#remove').removeAttr("disabled");
						 }
					 },
					 responseHandler:function(res){
				         //在ajax获取到数据，渲染表格之前，修改数据源
						 res.total = res.pageInfo.totalCount;
						 res.rows = res.data;
				         return res;
				     },
				}
		);
	};
	
	//清空数据
	OperaVarList.prototype.clearList = function() {
        var me = this;
    };
    
    
	// 重新显示
	OperaVarList.prototype.show = function() {
		
	};
	
	//页面点击
	OperaVarList.prototype.bindEvent = function() {
		var me = this;
		me.find("#remove").click(function() {
			var selections = $('#tb_var').bootstrapTable('getSelections');
			var varIds = new Array();
			for(var i in selections){
				varIds.push(selections[i].varId);
            } 
			var url = "msg";
			requestUtil.del(url, varIds).then(function(result) {
				if(result.success){
             	   $('#tb_var').bootstrapTable('refresh',me.queryParams);
                }
			});
		});
		
		me.find("a[name='searchBtn']").click(function() {
			me.find('#tb_var').bootstrapTable('refresh', me.queryParams);
		});
		
		me.find(".add").click(function() {
			me.moveTo('operaVarDetail');
		});
	};
	
	// 页面隐藏
	OperaVarList.prototype.hide = function() {
		
	};

	return new OperaVarList();
});