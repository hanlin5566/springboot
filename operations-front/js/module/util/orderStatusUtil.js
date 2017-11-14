define(['util/utils', 'util/orderStatusSelfUtil', 'util/sessionUtil'],
		function(utils, orderStatusSelfUtil, sessionUtil) {
	var ORDER_CODE = {
		"NEW":{name:"NEW",text:"待支付"},
		"PAID":{name:"PAID",text:"待审核"},
		"APPROVED_BY_PLATFORM":{name:"APPROVED_BY_PLATFORM",text:"待审核"},      
		"APPROVED_BY_SERVICE_PROVIDER":{name:"APPROVED_BY_SERVICE_PROVIDER",text:"待审核"},
		"APPROVED_BY_MANUFACTURER":{name:"APPROVED_BY_MANUFACTURER",text:"待发货"},
		"SHIPPED":{name:"SHIPPED",text:"已发货"},
		"RECEIVED":{name:"RECEIVED",text:"已收货"},
		"REFUNDING":{name:"REFUNDING",text:"申请退货"},
		"REFUND_WAIT":{name:"REFUND_WAIT",text:"已退货"},
		"REFUND_REJECTED":{name:"REFUND_REJECTED",text:"拒绝退货"},
		"DEPRECATED":{name:"DEPRECATED",text:"已作废"},
		"REJECTED":{name:"REJECTED",text:"审核不通过"},
		"CANCELLED":{name:"CANCELLED",text:"已取消"}
	};
	var orderStatus = {        
			// 预约单已预约
       "TERMINALSTORE_COMPLETED":[
        {
          "name":"terminalStore_cancel",
          "description":"取消预约",
          "className":"orderStatusDelete",
          "nextStatus":"CANCELLED",
          "confirmMessage":"确认要取消预约吗?",
          "needReason":true
        },
        {
          "name":"terminalStore_commit",
          "description":"提交订单",
          "className":"orderStatusCommit",
          "nextStatus":"CONVERTORDER" 
        }
       ],
       // 带支付
       "TERMINALSTORE_NEW":
       [
        {
          "name":"terminalStore_delete",
          "description":"删除",
          "className":"orderStatusDelete",
          "nextStatus":"DEPRECATED",
          "confirmMessage":"确认要删除该订单吗?" 
        }        
       ],
       // 审核不通过
       "TERMINALSTORE_REJECTED":[
       	{
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING",
          "needReason":true
        },
        {
          "name":"terminalStore_delete",
          "description":"删除",
          "className":"orderStatusDelete",
          "nextStatus":"DEPRECATED",
          "confirmMessage":"确认要删除该订单吗?"
        },
        
        {
          "name":"terminalStore_commit",
          "description":"修改",
          "className":"orderStatusCommit",
          "nextStatus":"APPROVED_BY_PLATFORM" 
        }
       ],  
       // 米卡会审核通过
       "TERMINALSTORE_APPROVED_BY_PLATFORM":[
        {
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING",
          "needReason":true
        }
        ],
        //服务商审核通过
        "TERMINALSTORE_APPROVED_BY_SERVICE_PROVIDER":[
        {
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING",
          "needReason":true
        }
        ],
        //供应商审核通过
        "TERMINALSTORE_APPROVED_BY_MANUFACTURER":[
        {
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING",
          "needReason":true
        }
        ],
        // 已发货
        "TERMINALSTORE_SHIPPED":[
        {
          "name":"terminalStore_confirmReceive",
          "description":"确认收货",
          "className":"orderStatusConfirmReceive",
          "nextStatus":"RECEIVED" 
        },
        {
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING",
          "needReason":true
        }
        ],
        "TERMINALSTORE_RECEIVED":[
        {
          "name":"terminalStore_refund",
          "description":"申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING" ,
          "needReason":true
        }
        ],
        //供应商拒绝退货
        "TERMINALSTORE_REFUND_REJECTED":[
        {
          "name":"terminalStore_refund_rejected",
          "description":"重新申请退货",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDING" ,
          "needReason":true
        }
        ],
        "MICARSHOW_REFUND_WAIT":[
        {
          "name":"micarshow_refunded_wait",
          "description":"退款",
          "className":"orderStatusRefund",
          "nextStatus":"REFUNDED" 
        }
        ],
        
        // 米卡会审核通过
        "SERVICEPROVIDER_APPROVED_BY_PLATFORM":[
        {
          "name":"serviceProvider_auditPassed",
          "description":"审核通过",
          "className":"orderStatusAuditPassed",
          "nextStatus":"APPROVED_BY_SERVICE_PROVIDER" 
        },
        {
          "name":"serviceProvider_auditUnPass",
          "description":"审核不通过",
          "className":"orderStatusAuditUnPass",
          "nextStatus":"REJECTED",
          "needReason":true
        },
        {
          "name":"serviceProvider_cancel",
          "description":"取消",
          "className":"orderStatusCancel",
          "nextStatus":"CANCELLED",
          "needReason":true
        }
        ],
        
        // 服务商审核通过
        "SERVICEPROVIDER_APPROVED_BY_SERVICE_PROVIDER":[
        {
          "name":"serviceProvider_cancel",
          "description":"取消",
          "className":"orderStatusCancel",
          "nextStatus":"CANCELLED",
          "needReason":true
        },
        {
          "name":"serviceProvider_cancelPass",
          "description":"撤回审核",
          "className":"orderStatusCancelPass",
          "nextStatus":"APPROVED_BY_PLATFORM",
          "needReason":true
        }
        ],
        // 待发货
        "SERVICEPROVIDER_APPROVED_BY_MANUFACTURER":[
        {
          "name":"serviceProvider_cancel",
          "description":"取消",
          "className":"orderStatusCancel",
          "nextStatus":"CANCELLED",
          "needReason":true
        }
        ],
        // 服务商审核通过
        "MANUFACTURER_APPROVED_BY_SERVICE_PROVIDER":[
        {
          "name":"manufacturer_auditPassed",
          "description":"审核通过",
          "className":"orderStatusAuditPassed",
          "nextStatus":"APPROVED_BY_MANUFACTURER" 
        },
        {
          "name":"manufacturer_auditUnPass",
          "description":"审核不通过",
          "className":"orderStatusAuditUnPass",
          "nextStatus":"REJECTED",
          "needReason":true
        }
        ],
        //申请退货
        "MANUFACTURER_REFUNDING":[
        {
          "name":"manufacturer_refunded",
          "description":"同意退货",
          "className":"orderStatusRefunded",
          "nextStatus":"REFUND_WAIT" 
        },
        {
          "name":"manufacturer_refund_rejected",
          "description":"拒绝退货",
          "className":"orderStatusRefundRejected",
          "nextStatus":"REFUND_REJECTED" 
        }
        ],
        // 已收货
        "MANUFACTURER_RECEIVED":[
        ]
  };
  var termianlSelectStatus = {
      //待支付 待审核 待发货 已发货 已完成 审核不通过 已取消
      "TOBEPAID":['NEW'],
      "PENDING":['APPROVED_BY_PLATFORM','APPROVED_BY_SERVICE_PROVIDER'],
      "APPROVED":['APPROVED_BY_MANUFACTURER'],      
      "SHIPPED":['SHIPPED'],
      "RECEIVED":['RECEIVED'],
      "DEPRECATED":['DEPRECATED'],
  	  "REJECTED" : ['REJECTED'],
  	  "CANCELLED" : ['CANCELLED'],
  	  "REFUNDING":['REFUNDING']
  	  
  };

  // 订单进度条
  var previewStatus = {
      //待支付 待审核 待发货 已发货 已完成 审核不通过
      "NEW":[{'description':"待支付","name":"TOBEPAID"}],
      "PAID":[{'description':"待审核","name":"PENDING"}],
      "APPROVED_BY_PLATFORM":[{'description':"待审核","name":"PENDING"}],      
      "APPROVED_BY_SERVICE_PROVIDER":[{'description':"待审核","name":"PENDING"}],
      "APPROVED_BY_MANUFACTURER":[{'description':"待发货","name":"APPROVED"}],
      "SHIPPED":[{'description':"已发货","name":"SHIPPED"}],
      "RECEIVED":[{'description':"已收货","name":"RECEIVED"}],
      "REFUNDING":[{'description':"申请退货","name":"RECEIVED"}],
      "REFUND_REJECTED":[{'description':"拒绝退货","name":"RECEIVED"}],
      "REFUND_WAIT":[{'description':"退款中","name":"RECEIVED"}],
      "REFUND_WAIT_NOTIFY":[{'description':"已退货","name":"RECEIVED"}],
      "REFUNDED":[{'description':"已退货","name":"RECEIVED"}],
      "DEPRECATED":[{'description':"已作废","name":"DEPRECATED"}],
  	  "REJECTED":[{'description':"审核不通过","name":"REJECTED"}],
  	  "CANCELLED":[{'description':"已取消","name":"CANCELLED"}]
  	  
  };
  //服务商自营商品订单进度条
  var serviceProviderpreviewStatus = {
	      //待支付 待审核 待发货 已发货 已完成 审核不通过
	      "NEW":[{'description':"待支付","name":"TOBEPAID"}],
	      "PAID":[{'description':"待审核","name":"PENDING"}],
	      "APPROVED_BY_PLATFORM":[{'description':"待审核","name":"PENDING"}],      
	      "APPROVED_BY_SERVICE_PROVIDER":[{'description':"待发货","name":"APPROVED"}],
	      "SHIPPED":[{'description':"已发货","name":"SHIPPED"}],
	      "RECEIVED":[{'description':"已收货","name":"RECEIVED"}],
	      "REFUNDING":[{'description':"申请退货","name":"RECEIVED"}],
	      "REFUND_REJECTED":[{'description':"拒绝退货","name":"RECEIVED"}],
	      "REFUND_WAIT":[{'description':"退款中","name":"RECEIVED"}],
	      "REFUND_WAIT_NOTIFY":[{'description':"已退货","name":"RECEIVED"}],
      	  "REFUNDED":[{'description':"已退货","name":"RECEIVED"}],
	      "DEPRECATED":[{'description':"已作废","name":"DEPRECATED"}],
	  	  "REJECTED":[{'description':"审核不通过","name":"REJECTED"}],
	  	  "CANCELLED":[{'description':"已取消","name":"CANCELLED"}],
	  };
  
  var orderStatusUtil = {
      ORDER_CODE:ORDER_CODE,
      getOrderFuncList : function(userPropType, status, ownerId){
        var userInfo = sessionUtil.get(sessionUtil.KEY_USER_INFO);
        if (ownerId && userInfo.companyId == ownerId) {
          return orderStatusSelfUtil.getOrderFuncList(userPropType, status);
        }
        return orderStatus[userPropType.toUpperCase() + "_" + status];
      },

      getTermianlSelectStatus : function(status){
        return termianlSelectStatus[status];
      },
      getPreviewStatus : function(status){
        return previewStatus[status];
      },
      getServiceProviderpreviewStatus : function(status) {
    	return serviceProviderpreviewStatus[status];
      }
  };
  
  return orderStatusUtil;
});