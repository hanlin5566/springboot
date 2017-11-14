<script id="carbrand-select-template" type="text/x-jsrender">
<div class="popover carbrand-select-list-popover" role="tooltip">
	<div class="arrow"></div>
  <h3 class="popover-title"></h3>
  <div class="popover-content">
	</div>
</div>
</script>

<script id="carbrand-select-content-template" type="text/x-jsrender">
<div class="carbrand-select-list" id="{{:uid}}">
	<ul class="nav nav-tabs" role="tablist">
		<li><a id="tabcontent-carBrand-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-carBrand">品牌</a></li>
		<li><a id="tabcontent-carSeries-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-carSeries">车型</a></li>
		<li><a id="tabcontent-carModel-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-carModel">车款</a></li>
	</ul>
	<div class="tab-content clearfix">
		<div class="tab-pane fade clearfix" level="carBrand" title="品牌" nextId="tabcontent-carSeries" id="tabcontent-carBrand"></div>
		<div class="tab-pane fade clearfix" level="carSeries" title="车型" nextId="tabcontent-carModel" id="tabcontent-carSeries"></div>
		<div class="tab-pane fade clearfix" level="carModel" title="车款" id="tabcontent-carModel"></div>
	</div>
	<div class="carbrand-select-list-footer">
		<a href="javascript:void(0)" name="submit" class="btn btn-sm btn-primary">确定</a>
		<a href="javascript:void(0)" name="close" class="btn btn-sm btn-default">关闭</a>
	</div>
</div>
</script>

<script id="carbrand-select-item-template" type="text/x-jsrender">  
  <ul class="clearfix">
    {{for items}}
			<li><a carBrandId='{{:carModelId || carSeriesId || carBrandId}}' href="javascript:void(0)">{{:carModelName || carSeriesName || carBrandName}}</a></li>
  	{{/for}}
  </ul>
</script>
