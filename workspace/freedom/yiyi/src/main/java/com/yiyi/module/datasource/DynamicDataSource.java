package com.yiyi.module.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @ClassName DynamicDataSource
 * @Description: TODO(动态数据源抽象实现类(获取数据源对应的key) )
 * @author HuGuangJun
 * @date 2016年4月20日 下午3:27:26
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSourceKey();
	}
}
