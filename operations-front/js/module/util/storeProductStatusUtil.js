define(['util/utils'], function(utils) {
  var PRODUCT_CODE = {
	  'DRAFT_COMPLETE' :{name:'DRAFT_COMPLETE',text:'未提交'},
      'PENDING' :{name:'PENDING',text:'待审核'},
      'ONLINE' : {name: 'ONLINE', text: '已上架'},
      'OFFLINE' : {name: 'OFFLINE', text: '已下架'},
      'AUDITED' :{name: 'AUDITED', text: "已审核"},
      'REJECTED':{name:'REJECTED', text: "审核不通过"}
    };

  var productStatus = {
		  //未提交
		  "DRAFT_COMPLETE":{
			  "btns":[{
				  "name":"edit",
				  "description":"编辑",
			  },{
				  "name":"delete",
				  "description":"删除"
			  },{
				  "name":"commit",
				  "description":"提交审核"
			  },{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },
		  //待审核
		  "PENDING":{
			  "btns":[{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },
		  //审核通过
		  "AUDITED":{
			  "btns" : [{
				  "name":"edit",
				  "description":"编辑"
			  },{
				  "name":"online",
				  "description":"上架",
			  },{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },
		  //审核不通过
		  "REJECTED":{
			  "btns":[{
				  "name":"edit",
				  "description":"编辑",
			  },{
				  "name":"delete",
				  "description":"删除"
			  },{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },
		  // 上架
		  "ONLINE":{
			  "btns" : [{
				  "name":"edit",
				  "description":"编辑"
			  },{
				  "name":"offline",
				  "description":"下架"
			  },{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },
		  // 下架
		  "OFFLINE":{
			  "btns" : [{
				  "name":"edit",
				  "description":"编辑"
			  },{
				  "name":"online",
				  "description":"上架",
			  },{
				  "name":"detail",
				  "description":"查看详情"
			  }]
		  },

  };
  
  var productStatusUtil = {
    PRODUCT_CODE : PRODUCT_CODE,
    getStatusMap : function(status) {
      var result = productStatus[status];
      return result;        
    },
    updateStatus : function(productIds, status, callback) {
      var param = {};
      if (!productIds) {
        return;
      }

      if ($.isNumeric(productIds)) {
        param[productIds] = status;
      }

      if ($.isArray(productIds)) {
        for (var i = 0; i < productIds.length; i++) {
          param[productIds[i]] = status;
        };
      }
      
      utils.put("terminalStore/product/status", param).then(function(data) {
        if (data.code != "200") {
          alert("产品更新失败，请稍后重试");
          callback(false);
        } else {
          callback(true);
        }
      }); 
    }
  };
  
  return productStatusUtil;
});