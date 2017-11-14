define(['util/utils'], function(utils) {
  var PRODUCT_CODE = {
    'DRAFT_COMPLETE' : {name: 'DRAFT_COMPLETE', text: '未提交'}, // 新建已经修改价格
    'PENDING' : {name: 'PENDING', text: '待审核'},
    'AUDITED' : {name: 'AUDITED', text: '已审核'},
    'REJECTED' : {name: 'REJECTED', text: '审核不通过'},
    'ONLINE' : {name: 'ONLINE', text: '已上架'},
    'OFFLINE' : {name: 'OFFLINE', text: '已下架'},
    'UNLINE' : {name: 'OFFLINE', text: '未上架'}
  };
  
  var productStatus = {
      // 未提交
      "DRAFT_COMPLETE":{
        "funcs" : {
          "ratio" : true,
          "priceEdit" : true,
          "audit" : true
        },
        "btns" : [{
          "name":"editSub",
          "description":"编辑商品"
        },{
          "name":"edit",
          "description":"编辑模板"
        },{
          "name":"priceEdit",
          "description":"改价"
        },{
          "name":"delete",
          "description":"作废"
        },{
          "name":"audit",
          "description":"提交审核"
        }]
      },
      // 待审核
      "PENDING":{
        "funcs" : {
          "priceEdit" : true,
        },
        "btns" : [{
          "name":"editSub",
          "description":"编辑商品"
        },{
          "name":"edit",
          "description":"编辑模板"
        },{
         "name":"priceEdit",
         "description": "改价"
        }]
      },
      // 审核通过
      "AUDITED":{
        "funcs" : {
          "priceEdit" : true,
          "ratio" : true
        },
        "btns" : [{
          "name":"editSub",
          "description":"编辑商品"
        },{
          "name":"edit",
          "description":"编辑模板"
        },{
          "name":"priceEdit",
          "description":"改价"
        },{
          "name":"onoffline",
          "description":"上下架"
        }]
      },
      // 审核未通过
      "REJECTED":{
        "funcs" : {
          "priceEdit" : true,
          "ratio" : true,
          "audit" : true
        },
        "btns" : [{
          "name":"editSub",
          "description":"编辑商品"
        },{
          "name":"edit",
          "description":"编辑模板"
        },{
          "name":"priceEdit",
          "description":"改价"
        },{
          "name":"audit",
          "description":"提交审核"
        }]
      },
      // 以下目前是子商品才使用
      // 上架
      "ONLINE":{
        "funcs" : {
        },
        "btns" : [{
          "name":"editSub",
          "description":"编辑商品"
        },{
          "name":"edit",
          "description":"编辑模板"
        },{
          "name":"onoffline",
          "description":"上下架"
        }]
      },
      // 下架
      "OFFLINE":{
       "funcs" : {
         "ratio" : true,
         "priceEdit" : true,
         "audit" : true,
       },
       "btns" : [{
         "name":"editSub",
         "description":"编辑商品"
       },{
         "name":"edit",
         "description":"编辑模板"
       },{
         "name":"priceEdit",
         "description":"改价"
       },{
         "name":"onoffline",
         "description":"上下架"
       },{
         "name":"audit",
         "description":"提交审核"
       }]
     },
     // 下架
     "UNLINE":{
      "funcs" : {
        "ratio" : true,
        "priceEdit" : true,
        "audit" : true,
      },
      "btns" : [{
        "name":"editSub",
        "description":"编辑商品"
      },{
        "name":"edit",
        "description":"编辑模板"
      },{
        "name":"priceEdit",
        "description":"改价"
      },{
        "name":"onoffline",
        "description":"上下架"
      },{
        "name":"audit",
        "description":"提交审核"
      }]
    }
  };
  
  var productStatusUtil = {
    PRODUCT_CODE : PRODUCT_CODE,
    getStatusMap : function(status, isForAll) {
      var result = $.extend(true, {}, productStatus[status] || {btns:[]});
      
      if (isForAll === 0 || isForAll === "0") {
        result.btns.push({
          "name": "specialMode",
          "description": "专车专用"
        });
      }
      
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
      
      utils.put("manufacturer/product/status", param).then(function(data) {
        if (data.code != "200") {
          alert("产品更新失败，请稍后重试");
          callback(false);
        } else {
          callback(true);
        }
      }); 
    },
    adminUpdateStatus : function(productIds, status, callback) {
      var param = [];      
      if(!productIds){
        return;        
      }
      if($.isNumeric(productIds)){
        var productStatus = {};
        productStatus.productId = productIds;          
        productStatus.productStatus = status;
        param.push(productStatus);
      }
      if($.isArray(productIds)){
        for (var i = 0; i < productIds.length; i++) {
          var productStatus = {};
          productStatus.productId = productIds[i];          
          productStatus.productStatus = status;
          param.push(productStatus);
        } 
      }
      utils.put("admin/main/product/status", param).then(function(data) {
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
