define([ 'util/logger', 'core/navigation',
		'portal/passport/config', 
		'core/tracking' ], function(logger, navigation,
		 config, tracking) {

//	var defs = [];
//	
//	var containerList = config.containerList;
//	for (var k in containerList) {
//		defs.push(new Container(config, k));
//	}
//
//	$.when.apply(this, defs).then(function() {
//		logger.trace("navigation.start was called!");
		navigation.start();
//	}, function() {
//		alert("initialized error!");
//	});
});
