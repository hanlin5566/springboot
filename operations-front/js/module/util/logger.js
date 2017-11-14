define([],	function() {
		var _startTimer = null;   //start time for a function
		var _endTimer = null;     //end time for a function
		var globalLevel = 5;
		
		var _traceLevelName = {
			"1" : "FATAL",
			"2" : "ERROR",
			"3" : "WARNING",
			"4" : "INFO",
			"5" : "TRACE"
		};

		//record start time
		var startTimer = function(){
			_startTimer = new Date();
			return _startTimer;
		};
		
		//record end time
		var endTimer = function(){
			_endTimer = new Date();
			return _endTimer;
		};
		
		//calculate time between start and end
		var getTimeElapse = function(){
			return _endTimer - _startTimer;
		};
		
		//error message
		var fatal = function(){
			var traceLevel = 1;
			log(traceLevel, arguments);
		};

		var error = function(){
			var traceLevel = 2;
			log(traceLevel, arguments);
		};
		
		var warning = function(){
			var traceLevel = 3;
			log(traceLevel, arguments);
		};
		
		//information message
		var info = function(){
			var traceLevel = 4;
			log(traceLevel, arguments);
		};

		var trace = function(){
			var traceLevel = 5;
			log(traceLevel, arguments);
		};

		var log = function(traceLevel, arguments){
			if(globalLevel >= traceLevel){
				var arguments = Array.prototype.slice.call(arguments);
				var currDate = new Date();
				arguments.splice(0, 0, currDate.toLocaleString() + " -" + _traceLevelName[traceLevel + ""] + ": ");
				if(console){
					console.log.apply(console, arguments);
				}
			}
		};

		var startPerfLog = function(){
		  var traceLevel = 5;
      log(traceLevel, arguments);
		};

		var endPerfLog = function(){
		  var traceLevel = 5;
      log(traceLevel, arguments);
		};

		return { 
			startTimer	   : startTimer,
			endTimer		   : endTimer,
			getTimeElapse	 : getTimeElapse,
			fatal          : fatal,
			error			     : error,
			warning			   : warning,
			info 			     : info,
			trace			     : trace,
			startPerfLog	 : startPerfLog,
			endPerfLog		 : endPerfLog,
			globalLevel    : globalLevel
		};
	}
);