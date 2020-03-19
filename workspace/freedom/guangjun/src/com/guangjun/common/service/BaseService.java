package com.guangjun.common.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.guangjun.common.repository.BaseRepository;

public class BaseService<T, PK extends Serializable> {

	@Autowired
	protected BaseRepository<T, PK> baseRepository;

	/**
	 * 获取的一个实体类
	 */
	public T getBean(PK id) {

		return baseRepository.findOne(id);
	}

	/**
	 * 实现增加和修改
	 */
	public void saveAndUpdate(T entity) {
		baseRepository.saveAndFlush(entity);
	}

	/**
	 * 实现删除功能
	 * 
	 * @param id
	 */
	public void delete(PK id) {
		baseRepository.delete(id);
	}

}
