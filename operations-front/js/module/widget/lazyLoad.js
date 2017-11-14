define([], function() {
	var lazyLoad = function($dom, className, beginHeight, callback) {
		// 功能：当div完整出现在屏幕时，加载。
		// 参数className，需要进行懒加载的元素的类名，要取一样的名字
		// 参数beginHeight，滚动条滚到哪里，开始监听
		if (!className) {
			console.error("lazyload方法中缺少className参数");
			return;
		}
		if (!beginHeight)
			beginHeight = 0;

		lazyDivList = $dom;

		var srcTop = 0;
		$(window).scroll(function() {
			srcTop = $(window).scrollTop();
			if (srcTop >= beginHeight) {
				lazyDivList.trigger("lazyme", $(window).scrollTop());
			}
		});
		
		lazyDivList.bind("lazyme", function(e, scrTop) {
			// 该元素距离顶部距离
			var offset = $(this).offset().top;
			// 滚动距离是 自己离顶部高度 + 自身高度，就会隐藏到浏览器上方，不可见
			var hideTop = offset + $(this).height();
			// 滚动距离是 自己离顶部高度 - 窗口高度，就会隐藏在浏览器下方，不可见
			var hideBottom = offset - $(window).height() + 100;
			
			if (scrTop >= hideBottom && scrTop <= hideTop) {
				$.proxy(callback, this).apply();
				$(this).removeClass(className);
				$(this).unbind("lazyme");
			}
		});
		
		srcTop = $(window).scrollTop();
		if (srcTop >= beginHeight) {
			lazyDivList.trigger("lazyme", $(window).scrollTop());
		}
	}
	return lazyLoad;
});