package com.yiyi.module.mybatis.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiyi.entity.common.BaseObject;
import com.yiyi.entity.common.Page;

/**
 * 
 * @ClassName MybatisBaseDao
 * @Description: TODO(MyBatis基类Dao)
 * @author HuGuangJun
 * @date 2016年4月20日 下午3:09:02
 *
 * @param <T>
 */
public abstract class MybatisBaseDao<T extends BaseObject> extends
		SqlSessionDaoSupport {
	/** 接口泛型T对应的Class字节码 */
	private Class<T> clazz;

	/**
	 * 基类Dao接口实现构造函数，主要用于获取接口泛型T对应的Class字节码
	 */
	@SuppressWarnings("unchecked")
	public MybatisBaseDao() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		Type[] typeArguments = parameterizedType.getActualTypeArguments();
		clazz = (Class<T>) typeArguments[0];
	}

	/**
	 *  自动注入SessionFactory,使用配置文件注入无效（至今未找到是什么原因）
	 */
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 
	 * @Title: selectOne
	 * @Description: TODO(查询单个接口泛型对象T)
	 * @param sqlId
	 * @param parameter
	 * @return
	 * @author HuGuangJun
	 * @date 2016年4月20日 下午3:09:45
	 */
	public T selectOne(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().selectOne(statement, parameter);

		return super.getSqlSession().selectOne(statement);
	}

	/**
	 * 
	 * @Title: selectOneByParameter
	 * @Description: TODO(查询单个非接口泛型对象)
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 * @return Object 单个非接口泛型对象
	 * @author HuGuangJun
	 * @date 2016年4月20日 下午3:10:03
	 */
	public Object selectOneByParameter(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().selectOne(statement, parameter);

		return super.getSqlSession().selectOne(statement);
	}

	/**
	 * 
	 * @Title: selectList
	 * @Description: (查询接口泛型对象T列表)
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 *            参数
	 * @return List<T> 接口泛型对象T列表
	 * @author HuGuangJun
	 * @date 2016年4月20日 下午3:10:26
	 */
	public List<T> selectList(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().selectList(statement, parameter);

		return super.getSqlSession().selectList(statement);
	}

	/**
	 * 查询非接口泛型对象T列表
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 *            参数
	 * @return List<?> 非接口泛型对象T列表
	 */
	public List<?> selectListByParameter(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().selectList(statement, parameter);

		return super.getSqlSession().selectList(statement);
	}

	/**
	 * 分页查询(基于方言的分页)
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param page
	 *            分页对象参数
	 * @return Page<T> 查询后返回的分页对象
	 */
	public Page<T> selectForPage(String sqlId, Page<T> page) {
		String statement = constructStatement(sqlId);
		// 设置分页统计标识为true，让分页插件执行分页统计
		Map<String, Object> paramMap = page.getParamMap();
		paramMap.put("pageCountFlag", true);
		List<T> resultList = super.getSqlSession().selectList(statement, page);
		page.setResultList(resultList);
		return page;
	}

	/**
	 * 分页查询(基于自定义的sql语句分页)
	 * 
	 * @author HuGuangJun
	 * @date 2014-10-14
	 * @param pageCountSqlId
	 *            分页统计SQL语句id
	 * @param pageSqlid
	 *            分页查询SQL语句id
	 * @param page
	 *            查询后返回的分页对象
	 * @return Page<T> 查询后返回的分页对象
	 */
	public Page<T> selectForPage(String pageCountSqlId, String pageSqlid,
			Page<T> page) {
		// 1、查询总记录数、设置总页数
		String statement = constructStatement(pageCountSqlId);
		Long totalCount = super.getSqlSession().selectOne(statement, page);
		page.setTotalCount(totalCount);
		long totalPage = Page.computeTotalPage(totalCount, page.getPageSize());
		page.setTotalPage(totalPage);

		// 2、通过分页插件查询分页记录(设置分页统计标识为false，让分页插件不执行分页统计)
		statement = constructStatement(pageSqlid);
		Map<String, Object> paramMap = page.getParamMap();
		paramMap.put("pageCountFlag", false);
		List<T> resultList = super.getSqlSession().selectList(statement, page);
		page.setResultList(resultList);

		return page;
	}

	/**
	 * 根据接口泛型对象T插入
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param T
	 *            接口泛型对象T
	 * @return int 插入动作受影响的行数
	 */
	public int insert(String sqlId, T T) {
		String statement = constructStatement(sqlId);
		if (T == null)
			throw new IllegalArgumentException("接口泛型对象T不能为空！");

		return super.getSqlSession().insert(statement, T);
	}

	/**
	 * 根据非接口泛型对象参数插入
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 *            非接口泛型对象参数
	 * @return int 插入动作受影响的行数
	 */
	public int insertObject(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().insert(statement, parameter);

		return super.getSqlSession().insert(statement);
	}

	/**
	 * 根据接口泛型对象T更新
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param T
	 *            接口泛型对象T
	 * @return int 更新动作受影响的行数
	 */
	public int update(String sqlId, T T) {
		String statement = constructStatement(sqlId);
		if (T == null)
			throw new IllegalArgumentException("接口泛型对象T不能为空！");

		return super.getSqlSession().update(statement, T);
	}

	/**
	 * 根据非接口泛型对象参数更新
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 *            非接口泛型对象参数
	 * @return int 更新动作受影响的行数
	 */
	public int updateByParameter(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().update(statement, parameter);

		return super.getSqlSession().update(statement);
	}

	/**
	 * 根据接口泛型对象T删除
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param T
	 *            接口泛型对象T
	 * @return int 删除动作受影响的行数
	 */
	public int delete(String sqlId, T T) {
		String statement = constructStatement(sqlId);
		if (T == null)
			throw new IllegalArgumentException("接口泛型对象T不能为空！");

		return super.getSqlSession().delete(statement, T);
	}

	/**
	 * 根据非接口泛型对象参数删除
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @param parameter
	 *            非接口泛型对象参数
	 * @return int 删除动作受影响的行数
	 */
	public int deleteByParameter(String sqlId, Object parameter) {
		String statement = constructStatement(sqlId);
		if (parameter != null)
			return super.getSqlSession().delete(statement, parameter);

		return super.getSqlSession().delete(statement);
	}

	/**
	 * 构造SQL语句表达式
	 * 
	 * @author HuGuangJun
	 * @date 2014-9-28
	 * @param sqlId
	 *            SQL语句id
	 * @return String SQL语句表达式
	 */
	private String constructStatement(String sqlId) {
		if (StringUtils.isBlank(sqlId))
			throw new IllegalArgumentException("SQL语句id不能为空！");

		return clazz.getName() + "." + sqlId;
	}
}
