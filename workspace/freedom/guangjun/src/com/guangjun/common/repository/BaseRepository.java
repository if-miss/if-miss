package com.guangjun.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @author 光军 JpaSpecificationExecutor实现带条件分页
 * @param <T>
 * @param <PK>
 */

public interface BaseRepository<T, PK extends Serializable> extends
		JpaSpecificationExecutor<T>, JpaRepository<T, PK> {

}
