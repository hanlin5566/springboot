globalInit();

function globalInit() {
	enterApp(1.01);
//    requirejs.config({
//        urlArgs : 'v=' + (new Date()).getTime(),
//        waitSeconds: 0,
//        paths : {
//            'ver' : eduWechat.baseUrl + "data/ver"
//        }
//    });
//    
//    requirejs([ 'ver' ],function(ver) {
//        enterApp(ver.version);
//    });
}


function enterApp(appVersion) {
	requirejs.config({
	  baseUrl : '../js/module',
	  urlArgs : '_=' + appVersion,
	  paths : {
		'jquery' : '../lib/jquery-1.11.1.min',
		'jquery.jsrender' : '../lib/jsrender.min',
		'jquery.autocompleter' : '../lib/jquery.autocompleter',
		'jquery.sweet-dropdown.min' : '../lib/jquery.sweet-dropdown.min',
		'qq' : '../lib/fileuploader/fileuploader',
		'jquery.history.adapter' : '../lib/jquery.history.adapter.min',
		'jquery.history' : '../lib/jquery.history',
		'formatCurrency' : '../lib/jquery.formatCurrency',
		'jquery.cookie' : '../lib/jquery.cookie-1.4.1.min',
		'highcharts' : '../lib/highcharts',
		'ckeditorCtrl' : '../lib/ckeditor/adapters/jquery',
		'mobiscroll' : '../lib/mobiscroll.custom-2.17.0.min',
		'ssiUploader' : '../lib/ssi-uploader',
		'bootstrapTable' : '../lib/bootstrap-table',
		'bootstrap' : '../lib/bootstrap.min',
		'icheck' : '../lib/jquery.icheck.min',
		'echarts' : '../lib/echarts.min',
        'text' : '../lib/require_text',
        'css' : '../lib/css.min',
		'fastclick':'../lib/fastclick'
	  },
	  shim : {
		'jquery.history' : ['jquery'],
		'jquery.history.adapter' : ['jquery'],
		'jquery.history' : ['jquery.history.adapter'],
		'jquery.jsrender' : ['jquery'],
		'highcharts' : ['jquery'],
		'formatCurrency' : ['jquery'],
		'jquery.cookie' : ['jquery'],
		'mobiscroll' : ['jquery'],
		'ssiUploader' : ['jquery'],
		'qq' : ['jquery'],
		'ckeditorCtrl' : ['ckeditor']
	  },
	});

	requirejs(['util/requestUtil', 'util/utils', 'util/formatUtil', 'util/dataUtil',
		'util/dateUtil', 'fastclick', 'jquery.jsrender', '../lib/localization/messages_zh', 'jquery.cookie', "mobiscroll", "ssiUploader",
				'../lib/additional-methods'], function(requestUtil, utils, formatUtil, dataUtil, dateUtil, fastclick) {
	  requestUtil.setting.templateBase = "../template/";
	  requestUtil.setting.appVersion = appVersion;

	  // jsrender的设置方法，可以在模板里面使用js的共通类
	  $.views.helpers({utils: utils, formatUtil: formatUtil, dateUtil: dateUtil});
	  
	  var query = window.location.href;

	  // 获取app code
	  var index = query.lastIndexOf('/');
	  var appCode = query.substring(index);
	  index = appCode.indexOf('.');
	  if (index > -1) {
		appCode = appCode.substring(1, index);
	  }
	  
	  // 查询条件保存，需要区分各个应用
	  requestUtil.setting.appCode = appCode;
	  dataUtil.init(appCode);
	  
	  if (utils.startWith(query, "http://")) {
		query = query.substring(7);
	  }
	  
	  if (!appCode || appCode == 'passport') { // 登录
		
		requirejs(['portal/passport/initializer']);

	  } else if (appCode == 'main') { // 系统运维
		
		requirejs(['portal/main/initializer']);
		
	  } else if (appCode == 'analytics_quesnaire') { // 评价分析
		
		requirejs(['portal/analytics_quesnaire/initializer']);
		
	  } else if (appCode == 'analytics_score') { // 学业水平
		
		requirejs(['portal/analytics_score/initializer']);
		
	  } else if (appCode == 'personal_center') { // 个人中心
		
		requirejs(['portal/personal_center/initializer']);

	  } else if (appCode == 'system_manage') { // 系统管理
		
		requirejs(['portal/system_manage/initializer']);
		
	  }else {
		// TODO 去错误页面
	  }
	  
	  //引入fastclick，屏蔽平板的双击事件
	  fastclick.attach(document.body);
	});
}