define(['util/utils', 'util/orderStatusSelfUtil', 'util/sessionUtil'],
		function(utils, orderStatusSelfUtil, sessionUtil) {
	var COMPANYSALETYPEE_CODE = {
		"PLATFORM":"平台",
		"SEPCIAL":"专用",
		"SHARE":"共享"
	};

  var companySaleTypeUtil = {
	  COMPANYSALETYPEE_CODE:COMPANYSALETYPEE_CODE,
  	  get:function(code){
  		return COMPANYSALETYPEE_CODE[code];
  	}
  };
 
  return companySaleTypeUtil;
});