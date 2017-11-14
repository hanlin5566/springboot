<script id="category-select-template" type="text/x-jsrender">
<div class="popover category-select-list-popover" role="tooltip">
	<div class="arrow"></div>
  <h3 class="popover-title"></h3>
  <div class="popover-content">
	</div>
</div>
</script>

<script id="category-select-content-template" type="text/x-jsrender">
<div class="category-select-list" id="{{:uid}}">
	<ul class="nav nav-tabs" role="tablist">
		<li><a id="tabcontent-category1-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-category1">大类</a></li>
		<li><a id="tabcontent-category2-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-category2">中类</a></li>
		<li><a id="tabcontent-category3-tab" href="javascript:void(0)" data-target="#{{:uid}} #tabcontent-category3">小类</a></li>
	</ul>
	<div class="tab-content clearfix">
		<div class="tab-pane fade clearfix" level="category1" title="大类" nextId="tabcontent-category2" id="tabcontent-category1"></div>
		<div class="tab-pane fade clearfix" level="category2" title="中类" nextId="tabcontent-category3" id="tabcontent-category2"></div>
		<div class="tab-pane fade clearfix" level="category3" title="小类" id="tabcontent-category3"></div>
	</div>
	<div class="category-select-list-footer">
		<a href="javascript:void(0)" name="submit" class="btn btn-sm btn-primary">确定</a>
		<a href="javascript:void(0)" name="close" class="btn btn-sm btn-default">关闭</a>
	</div>
</div>
</script>

<script id="category-select-item-template" type="text/x-jsrender">  
  <ul class="clearfix">
    {{for items}}
			<li><a categoryId='{{:category3Id || category2Id || category1Id}}' href="javascript:void(0)">{{:category3Name || category2Name || category1Name}}</a></li>
  	{{/for}}
  </ul>
</script>
