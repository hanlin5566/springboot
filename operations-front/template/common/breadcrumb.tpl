<script id="breadcrumb-template" type="text/x-jsrender">
	<div class="pull-left">
		<a href="javascript:void(0)" menucode="manufactureWorkbench" class="fa fa-home"></a>
		{{if menu.getParent().level > 0}}
		<a href="javascript:void(0)" menucode="{{:menu.getParent().menuCode}}">{{:menu.getParent().getTitle()}}</a>
		{{/if}}
		<a href="javascript:void(0)" class="current">{{:menu.getTitle()}}</a>
  </div>
  <div class="pull-right navbar-default">
  </div>
  <div class="clearfix"></div>
</script>