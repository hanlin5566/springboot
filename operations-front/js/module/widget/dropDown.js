define([], function() {

	$(document).click(function() {
		$('.wrapper-dropdown-3').removeClass('active');
	});
	
	var DropDown = function (el, callback) {
		this.dd = el;
		this.placeholder = this.dd.children('span');
		this.opts = this.dd.find('ul.dropdown > li');
		this.val = '';
		this.index = -1;
		this.initEvents(callback);
	};
	
	DropDown.prototype = {
		initEvents : function(callback) {
			var obj = this;
			obj.dd.on('click', function(event){
				$(this).toggleClass('active');
				return false;
			});

			obj.opts.on('click',function(){
				var opt = $(this);
				obj.val = opt.text();
				obj.index = opt.index();
				obj.code = opt.attr("code");
				obj.placeholder.text(obj.val);
				callback(obj.code, obj.val);
			});
		},
		getValue : function() {
			return this.val;
		},
		getIndex : function() {
			return this.index;
		}
	}
	
	return DropDown;
});