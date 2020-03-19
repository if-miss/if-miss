package com.guangjun.common.handler;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.guangjun.common.service.BaseService;

public abstract class BaseHandler<T, PK extends Serializable> {

	@Autowired
	protected BaseService<T, PK> baseService;

	/**
	 * 获取的一个实体类
	 */
	public T getBean(PK id) {
		return baseService.getBean(id);
	}

	/**
	 * 实现增加和修改
	 */
	public void saveAndUpdate(T entity) {
		baseService.saveAndUpdate(entity);
	}

	/**
	 * 实现删除功能
	 * 
	 * @param id
	 */
	public void delete(PK id) {
		baseService.delete(id);
	}

}
