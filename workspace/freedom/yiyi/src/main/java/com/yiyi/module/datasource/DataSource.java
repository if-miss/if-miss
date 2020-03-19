package com.yiyi.module.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName DataSource
 * @Description: TODO(数据源注解)
 * @author HuGuangJun
 * @date 2016年4月20日 下午3:42:55
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface DataSource {
	/** 数据源名称 */
	DataSourceEnum value();
}
