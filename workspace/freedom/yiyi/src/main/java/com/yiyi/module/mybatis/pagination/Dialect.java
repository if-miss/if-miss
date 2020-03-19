package com.yiyi.module.mybatis.pagination;

import com.yiyi.entity.common.Page;

/**
 * 数据库方言基类
 * 
 * @Copyright 北京瑞友科技股份有限公司上海分公司-2014
 * @author majun
 * @date 2014-9-28 =================Modify Record=================
 * @Modifier @date @Content majun 2014-9-28 新增
 */
public abstract class Dialect {
	public static enum DialectType {
		MYSQL(MySQLDialect.class), ORACLE(OracleDialect.class);

		DialectType(Class<? extends Dialect> clazz) {
			this.clazz = clazz;
		}

		/** 方言实现类字节码 */
		private Class<? extends Dialect> clazz;

		public Class<? extends Dialect> getClazz() {
			return clazz;
		}

		public void setClazz(Class<? extends Dialect> clazz) {
			this.clazz = clazz;
		}
	}

	/**
	 * 构造分页SQL
	 * 
	 * @author majun
	 * @date 2014-9-28
	 * @param sql
	 *            原始SQL语句
	 * @param page
	 *            分页对象
	 * @return String 构造后的分页SQL语句
	 */
	public abstract String constructPageSql(String sql, Page<?> page);
}
