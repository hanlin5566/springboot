package com.hzcf.operation.mybatis.pagination.helper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hzcf.operation.base.entity.PageInfo;
import com.hzcf.operation.mybatis.pagination.dialect.DBMS;
import com.hzcf.operation.mybatis.pagination.dialect.Dialect;
import com.hzcf.operation.mybatis.pagination.dialect.DialectClient;

/**
 * Create by hanlin on 2017年11月8日
 **/
@Configuration
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
@Component
public class PaginationInterceptor implements Interceptor, Serializable {
	/** serial Version */
	private static final long serialVersionUID = -6075937069117597841L;
	// private static final ThreadLocal<Integer> PAGINATION_TOTAL = new
	// ThreadLocal<Integer>();
	// private static final ThreadLocal<PagingCriteria> PAGE_REQUEST = new
	// ThreadLocal<PagingCriteria>();
	/** logging */
	private static final Log logger = LogFactory.getLog(PaginationInterceptor.class);
	/** mapped statement parameter index. */
	private static final int MAPPED_STATEMENT_INDEX = 0;
	/** sql id , in the mapper xml file. */
	private static String _sql_regex = "[*]";
	/** DataBase dialect. */
	protected Dialect _dialect;

	/**
	 * Gets pagination total.
	 *
	 * @return the pagination total
	 */
	// public static int getPaginationTotal() {
	// if (PAGINATION_TOTAL.get() == null) {
	// return 0;
	// }
	// return PAGINATION_TOTAL.get();
	// }

	/**
	 * Gets page request.
	 *
	 * @return the page request
	 */
	// public static PagingCriteria getPageRequest() {
	// return PAGE_REQUEST.get();
	// }

	/** clear total context. */
	public static void clean() {
		// PAGE_REQUEST.remove();
		// PAGINATION_TOTAL.remove();
	}

	// /**
	// * Set the paging information,to RowBuounds.
	// *
	// * @param rowBounds rowBounds.
	// * @return rowBounds.
	// */
	// private static RowBounds offset_paging(RowBounds rowBounds,
	// PagingCriteria pageRequest) {
	// // rowBuounds has offset.
	// if (rowBounds.getOffset() == RowBounds.NO_ROW_OFFSET) {
	// if (pageRequest != null) {
	// return new RowBounds(pageRequest.getDisplayStart(),
	// pageRequest.getDisplaySize());
	// }
	// }
	// return rowBounds;
	// }

	/**
	 * Sort and filter sql.
	 *
	 *
	 * @param sql
	 *            the sql
	 * @param pagingCriteria
	 *            the paging criteria
	 * @return the string
	 */
	// private static String sortAndFilterSql(String sql, PagingCriteria
	// pagingCriteria) {
	//
	// boolean order = SqlRemoveHelper.containOrder(sql);
	// final List<SearchField> searchFields = pagingCriteria.getSearchFields();
	// if (searchFields != null && !searchFields.isEmpty()) {
	// List<String> where_field = Lists.newArrayList();
	// for (SearchField searchField : searchFields) {
	// // fix inject sql
	// where_field.add(searchField.getField() + StringHelper.LIKE_CHAR +
	// StringHelper.likeValue(searchField.getValue()));
	// }
	// boolean where = SqlRemoveHelper.containWhere(sql);
	// String orderBy = StringHelper.EMPTY;
	// if (order) {
	// String[] sqls = sql.split(SqlRemoveHelper.ORDER_REGEX);
	// sql = sqls[0];
	// orderBy = CountHelper.SQL_ORDER + sqls[1];
	// }
	// sql = String.format((where ? CountHelper.OR_SQL_FORMAT :
	// CountHelper.WHERE_SQL_FORMAT), sql
	// , Joiner.on(CountHelper.OR_JOINER).skipNulls().join(where_field),
	// orderBy);
	// }
	//
	// final List<SortField> sortFields = pagingCriteria.getSortFields();
	// if (sortFields != null && !sortFields.isEmpty()) {
	// List<String> field_order = Lists.newArrayList();
	// for (SortField sortField : sortFields) {
	// field_order.add(sortField.getField() + StringHelper.BLANK_CHAR +
	// sortField.getDirection().getDirection());
	// }
	// return String.format(order ? CountHelper.SQL_FORMAT :
	// CountHelper.ORDER_SQL_FORMAT, sql
	// , Joiner.on(StringHelper.DOT_CHAR).skipNulls().join(field_order));
	// }
	//
	// return sql;
	// }

	/**
	 * Copy from bound sql.
	 *
	 * @param ms
	 *            the ms
	 * @param boundSql
	 *            the bound sql
	 * @param sql
	 *            the sql
	 * @return the bound sql
	 */
	public static BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
				boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	// see: MapperBuilderAssistant
	private static MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		String[] keyProperties = ms.getKeyProperties();
		builder.keyProperty(keyProperties == null ? null : keyProperties[0]);

		// setStatementTimeout()
		builder.timeout(ms.getTimeout());

		// setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());

		// setStatementResultMap()
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());

		// setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	/**
	 * perform paging intercetion.
	 *
	 * @param queryArgs
	 *            Executor.query params.
	 */
	private void processIntercept(final Object[] queryArgs) {
		final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		final Object parameter = queryArgs[1];

		// the need for paging intercept.
		boolean interceptor = ms.getId().matches(_sql_regex);
		if (!interceptor)
			return;
		// obtain paging information.

		if (queryArgs[2] == null) {
			queryArgs[2] = RowBounds.DEFAULT;
		}

		RowBounds rowBounds = (RowBounds) queryArgs[2];
		int offset = rowBounds.getOffset();
		int limit = rowBounds.getLimit();

		if (_dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
			final BoundSql boundSql = ms.getBoundSql(parameter);
			String sql = boundSql.getSql().trim();

			if (rowBounds instanceof PageInfo) {
				Connection connection = null;
				try {
					// get connection
					connection = ms.getConfiguration().getEnvironment().getDataSource().getConnection();
					int count = CountHelper.getCount(sql, connection, ms, parameter, boundSql, _dialect);
					((PageInfo) rowBounds).setTotalCount(count);
				} catch (SQLException e) {
					logger.error("The total number of access to the database failure.", e);
				} finally {
					try {
						if (connection != null && !connection.isClosed()) {
							connection.close();
						}
					} catch (SQLException e) {
						logger.error("Close the database connection error.", e);
					}
				}
			}

			// 由此开始，因为mybatis不知道过滤器已经增加了limit字段，所以要让mybatis从第0条记录开始获取，所以要改变RowBounds
			if (sql.endsWith(";")) {
				sql = sql.substring(0, sql.length() - 1);
			}

			String new_sql = sql; // sortAndFilterSql(sql, null);
			if (_dialect.supportsLimit()) {
				new_sql = _dialect.getLimitString(new_sql, offset, limit);
				offset = RowBounds.NO_ROW_OFFSET;
			} else {
				new_sql = _dialect.getLimitString(new_sql, 0, limit);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("pagination sql is :[" + new_sql + "]");
			}
			limit = RowBounds.NO_ROW_LIMIT;

			queryArgs[2] = new RowBounds(offset, limit);

			BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, new_sql);

			MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
			queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
		}
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// Object[] queryArgs = invocation.getArgs();
		// if (queryArgs[1] != null && queryArgs[1] instanceof PageInfo) {
		// mysqlIntercept(invocation);
		// } else {
		processIntercept(invocation.getArgs());
		// }
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object o) {
		// if (Executor.class.isAssignableFrom(o.getClass())) {
		// return Plugin.wrap(new PaginationExecutor((Executor) o), this);
		// }
		return Plugin.wrap(o, this);
	}
	// public Object plugin(Object target) {
	// // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
	// if (target instanceof StatementHandler) {
	// return Plugin.wrap(target, this);
	// } else {
	// return target;
	// }
	// }

	/**
	 * 设置属性，支持自定义方言类和制定数据库的方式
	 * <p>
	 * <code>dialectClass</code>,自定义方言类。可以不配置这项 <ode>dbms</ode> 数据库类型，插件支持的数据库
	 * <code>sqlRegex</code> 需要拦截的SQL ID
	 * </p>
	 * 如果同时配置了<code>dialectClass</code>和<code>dbms</code>,则以<code>dbms</code>为主
	 *
	 * @param p
	 *            属性
	 */
	@Override
	public void setProperties(Properties p) {
		String dialectClass = p.getProperty("dialectClass");
		DBMS dbms;
		if (StringHelper.isEmpty(dialectClass)) {
			String dialect = p.getProperty("dbms");
			dbms = DBMS.valueOf(dialect.toUpperCase());
		} else {
			Dialect dialect1 = (Dialect) Reflections.instance(dialectClass);
			DialectClient.putEx(dialect1);
			dbms = DBMS.EX;
		}

		_dialect = DialectClient.getDbmsDialect(dbms);

		String sql_regex = p.getProperty("sqlRegex");
		if (!StringHelper.isEmpty(sql_regex)) {
			_sql_regex = sql_regex;
		}
		clean();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	//
	//
	//// private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new
	// DefaultObjectFactory();
	//// private static final ObjectWrapperFactory
	// DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	// public void mysqlIntercept(Invocation invocation) throws Throwable {
	// Object[] queryArgs = invocation.getArgs();
	// final MappedStatement ms = (MappedStatement)
	// queryArgs[MAPPED_STATEMENT_INDEX];
	//// StatementHandler statementHandler = (StatementHandler)
	// invocation.getTarget();
	//// MetaObject metaStatementHandler =
	// MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
	//// DEFAULT_OBJECT_WRAPPER_FACTORY);
	//// // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
	//// while (metaStatementHandler.hasGetter("h")) {
	//// Object object = metaStatementHandler.getValue("h");
	//// metaStatementHandler = MetaObject.forObject(object,
	// DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	//// }
	//// // 分离最后一个代理对象的目标类
	//// while (metaStatementHandler.hasGetter("target")) {
	//// Object object = metaStatementHandler.getValue("target");
	//// metaStatementHandler = MetaObject.forObject(object,
	// DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
	//// }
	//// MappedStatement mappedStatement = (MappedStatement)
	// metaStatementHandler.getValue("delegate.mappedStatement");
	// // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的MappedStatement的sql
	// if (!ms.getId().matches(_sql_regex)) {
	// return;
	// }
	//// BoundSql boundSql = (BoundSql)
	// metaStatementHandler.getValue("delegate.boundSql");
	//
	// BoundSql boundSql = ms.getBoundSql(queryArgs[1]);
	// Object parameterObject = boundSql.getParameterObject();
	// if (parameterObject == null) {
	// throw new NullPointerException("parameterObject is null!");
	// } else {
	//// PageInfo page = (PageInfo) metaStatementHandler
	//// .getValue("delegate.boundSql.parameterObject");
	//
	// PageInfo page = (PageInfo)queryArgs[1];
	// String sql = boundSql.getSql();
	// // 重写sql
	// String pageSql = buildPageSql(sql, page);
	//
	// BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, pageSql);
	//
	// MappedStatement newMs = copyFromMappedStatement(ms, new
	// BoundSqlSqlSource(newBoundSql));
	// queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
	// Connection connection =
	// ms.getConfiguration().getEnvironment().getDataSource().getConnection();
	// // 重设分页参数里的总页数等
	// setPageParameter(sql, connection, ms, boundSql, page);
	////
	////
	//// metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
	//// // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
	//// metaStatementHandler.setValue("delegate.rowBounds.offset",
	// RowBounds.NO_ROW_OFFSET);
	//// metaStatementHandler.setValue("delegate.rowBounds.limit",
	// RowBounds.NO_ROW_LIMIT);
	// }
	// }
	//
	// /**
	// * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用者就可用通过 分页参数
	// * <code>PageParameter</code>获得相关信息。
	// *
	// * @param sql
	// * @param connection
	// * @param mappedStatement
	// * @param boundSql
	// * @param page
	// */
	// private void setPageParameter(String sql, Connection connection,
	// MappedStatement mappedStatement,
	// BoundSql boundSql, PageInfo page) {
	// // 记录总记录数
	// String countSql = "select count(*) from (" + sql + ") as total";
	// PreparedStatement countStmt = null;
	// ResultSet rs = null;
	// try {
	// countStmt = connection.prepareStatement(countSql);
	// BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
	// countSql,
	// boundSql.getParameterMappings(), boundSql.getParameterObject());
	// setParameters(countStmt, mappedStatement, countBS,
	// boundSql.getParameterObject());
	// rs = countStmt.executeQuery();
	// int totalCount = 0;
	// if (rs.next()) {
	// totalCount = rs.getInt(1);
	// }
	// page.setTotalCount(totalCount);
	// } catch (SQLException e) {
	// logger.error("Ignore this exception", e);
	// } finally {
	// try {
	// if (rs != null) rs.close();
	// } catch (SQLException e) {
	// logger.error("Ignore this exception", e);
	// }
	// try {
	// if (countStmt != null) countStmt.close();
	// } catch (SQLException e) {
	// logger.error("Ignore this exception", e);
	// }
	// }
	//
	// }
	//
	// /**
	// * 对SQL参数(?)设值
	// *
	// * @param ps
	// * @param mappedStatement
	// * @param boundSql
	// * @param parameterObject
	// * @throws SQLException
	// */
	// private void setParameters(PreparedStatement ps, MappedStatement
	// mappedStatement, BoundSql boundSql,
	// Object parameterObject) throws SQLException {
	// ParameterHandler parameterHandler = new
	// DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
	// parameterHandler.setParameters(ps);
	// }
	//
	// /**
	// * 根据数据库类型，生成特定的分页sql
	// *
	// * @param sql
	// * @param page
	// * @return
	// */
	// private String buildPageSql(String sql, PageInfo page) {
	// if (page != null) {
	// StringBuilder pageSql = new StringBuilder();
	// pageSql = buildPageSqlForMysql(sql, page);
	// return pageSql.toString();
	// } else {
	// return sql;
	// }
	// }
	//
	// /**
	// * mysql的分页语句
	// *
	// * @param sql
	// * @param page
	// * @return String
	// */
	// private StringBuilder buildPageSqlForMysql(String sql, PageInfo page) {
	// StringBuilder pageSql = new StringBuilder(100);
	// pageSql.append(sql);
	// pageSql.append(" limit
	// ").append(page.getOffset()).append(",").append(page.getLimit());
	// return pageSql;
	// }
}
