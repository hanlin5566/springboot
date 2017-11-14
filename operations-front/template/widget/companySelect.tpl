<script id="company-select-template" type="text/x-jsrender">
<div class="popover company-select-container-popover" role="tooltip">
	<div class="arrow"></div>
  <h3 class="popover-title"></h3>
  <div class="popover-content company-select-container company">
	</div>
</div>
</script>

<script id="company-select-content-template" type="text/x-jsrender">
<div class="area-select-list-plugin">
</div>
<div class="company-list">
	<ul>
	</ul>
</div>
<div class="operation-footer">
	<div class="select-area">
		<a href="javascript:void(0)" name="select-area-btn"><span>更改过滤条件：</span><span name="area"></span></a>
	</div>
	<div class="select-company">
		<a href="javascript:void(0)" name="search-company-btn"><span>点击查询：</span><span name="area"></span></a>
	</div>
</div>
</script>

<script id="company-select-item-template" type="text/x-jsrender">
		{{if items && items.length > 0}}
    {{for items}}
			<li companyId='{{:companyId}}'><span>{{:#index+1}}</span><a href="javascript:void(0)">{{:companyName}}</a></li>
  	{{/for}}
		{{else}}
			<div class="nodata">无符合条件数据</div>
		{{/if}}
</script>
