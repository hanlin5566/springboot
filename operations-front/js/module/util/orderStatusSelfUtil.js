define(['util/utils'], function(utils) {
  var orderStatusSelf = {
    // 米卡会审核通过
    "SERVICEPROVIDER_APPROVED_BY_PLATFORM" : [{
      "description" : "审核通过",
      "className" : "orderStatusAuditPassed",
      "nextStatus" : "APPROVED_BY_SERVICE_PROVIDER"
    }, {
      "description" : "审核不通过",
      "className" : "orderStatusAuditUnPass",
      "nextStatus" : "REJECTED",
      "needReason" : true
    }, {
      "description" : "取消",
      "className" : "orderStatusCancel",
      "nextStatus" : "CANCELLED",
      "needReason" : true
    }],
    //申请退货
    "SERVICEPROVIDER_REFUNDING" : [{
      "description":"同意退货",
      "className":"orderStatusRefunded",
      "nextStatus":"REFUND_WAIT" 
    },
    {
      "description":"拒绝退货",
      "className":"orderStatusRefundRejected",
      "nextStatus":"REFUND_REJECTED" 
    }
    ],
    // 已发货
    "SERVICEPROVIDER_SHIPPED" : [],
    // 已收货
    "SERVICEPROVIDER_RECEIVED" : [],
    
  };
  
  //出库管理状态
  var orderInventoryStatusSelf = {
      // 待发货
      "SERVICEPROVIDER_APPROVED_BY_SERVICE_PROVIDER" : [{
        "description" : "发货",
        "className" : "orderStatusDelivery",
        "nextStatus" : "SHIPPED"
      }],
      // 已发货
      "SERVICEPROVIDER_SHIPPED" : [{
        "description" : "更改发货信息",
        "className" : "orderStatusEditShipMessage",
        "nextStatus" : "EDITDELIVERYINFO"
      }],
      
      // 已收货
      "SERVICEPROVIDER_RECEIVED" : [],
      
      // 待发货
      
      "MANUFACTURER_APPROVED_BY_MANUFACTURER" : [{
        "description" : "发货",
        "className" : "orderStatusDelivery",
        "nextStatus" : "SHIPPED"
      }],
      // 已发货
      "MANUFACTURER_SHIPPED" : [{
        "description" : "更改发货信息",
        "className" : "orderStatusEditShipMessage",
        "nextStatus" : ""
      }],
      // 已收货
      "MANUFACTURER_RECEIVED" : []
    };


  var orderStatusSelfUtil = {
    getOrderFuncList : function(userPropType, status) {
      return orderStatusSelf[userPropType.toUpperCase() + "_" + status];
    },
    getOrderInventoryFuncList : function(userPropType, status) {
      return orderInventoryStatusSelf[userPropType.toUpperCase() + "_" + status];
    }
  };

  return orderStatusSelfUtil;
});