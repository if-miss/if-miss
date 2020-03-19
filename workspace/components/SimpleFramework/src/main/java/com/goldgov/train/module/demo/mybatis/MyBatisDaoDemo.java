package com.goldgov.train.module.demo.mybatis;

import java.util.List;

import com.goldgov.train.annotation.MybatisRepository;

@MybatisRepository
public interface MyBatisDaoDemo {

	public List findAllDatas();
}
