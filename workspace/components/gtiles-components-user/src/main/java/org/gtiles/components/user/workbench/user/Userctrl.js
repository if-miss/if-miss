app.controller('userctrl',function($scope,$state,$injector,Userservice,ValidationService) {
	
	// 调用列表查询方法
	$scope.getResource = function(params, paramsObj) {
		var searchField = $scope.searchCriteria;
		var searchValue = $scope.searchForm;
		if (searchField && searchValue && $.trim(searchValue)) {
			params += "&" + searchField + "=" + searchValue;
		}
		return Userservice.findList(params, paramsObj);
	}
	
	$scope.isFormDisabled = true;
	//预新增
	$scope.preAdd = function(){
		$scope.isFormDisabled = false;
		Userservice.preAdd("").success(function(data){
			$scope.userInfo = data.data;
			$scope.requestError = "";
			$("#formModel").modal("show");
		});
	}
	//预更新
	$scope.preUpdate = function(id,isDisabled){
		$scope.isFormDisabled = isDisabled==1?true:false;
		Userservice.preUpdate("id="+id).success(function(data){
			$scope.userInfo = data.data;
			$("#formModel").modal("show");
		});
	}
	//删除
	$scope.deleteById = function(id){
		Userservice.deleteById("ids="+id).success(function(data){
			$scope.refreshsystem();
		});
	}
	//表单提交
	$scope.formSubmit = function(){
		if(new ValidationService().checkFormValidity($scope.validateForm)) {
			var formInfo = $scope.userInfo;
			Userservice.saveOrUpdate(formInfo).success(function(data){
				if(data.success){
					$("#formModel").modal("hide");
					$scope.refreshsystem();
				}
			});
		}
	}
	
	// 排序
	$scope.init = {
		'sortBy' : 'user_id',
		'sortOrder' : 'asc'
	}; 
	
	// 刷新
	$scope.refreshsystem = function() {
		$scope.reloadCallback();
	}
	
});
