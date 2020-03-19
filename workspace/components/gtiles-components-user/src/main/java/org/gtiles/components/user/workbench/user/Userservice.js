var app = angular.module('Module.user', ['ngTasty','ngSanitize']).factory('Userservice', function($http) {
	var service = {};
	var CONTROLLER_URL = "../workbench/user";

	// 翻页查询方法
	service.findList = function(params, paramsObj) {
		var url = CONTROLLER_URL + "/findUserList.json";
		return $http.get(url + "?" + params).then(function(response) {
			return {
				'header' : [],
				'rows' : response.data.data.resultList,
				'pagination' : response.data.data.pagination,
				"sort-by" : "activeState",
				"sort-order" : "asc"
			}
		});
	}
	// 预新增
	service.preAdd = function(params){
		var url = CONTROLLER_URL+"/preAdd.json";
		return $http.get(url + "?" + params);
	}
	// 预更新
	service.preUpdate = function(params){
		var url = CONTROLLER_URL+"/findUser.json";
		return $http.get(url + "?" + params);
	}
	// 删除
	service.deleteById = function(params){
		var url = CONTROLLER_URL+"/deleteUserByIds.json";
		return $http.get(url + "?" + params);
	}
	// 新增或更新
	service.saveOrUpdate = function(params){
		var url = CONTROLLER_URL+"/saveOrUpdateUser.json";
		return $http({
			method : "post",
			url : url,
			data : $.param(params),
			headers:{'Content-Type': 'application/x-www-form-urlencoded'}
		});
	}

	return service;
});
