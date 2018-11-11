package top.Seiei.forMyBatis.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

import top.Seiei.forMyBatis.entity.Page;

/*
 *	MyBatis 分页拦截器
 *	实现 Mybatis 的 Interceptor 接口
 * 	选中接口后，按 F2 可搜索其实现类
 * 
 */

/* 
 * MyBatis 的 @Interceptes 注释用于声明要拦截的位置（查看MyBatis源码）
 * @Signature 的 type 指向所要拦截的接口的 Class, method 指向所要拦截的方法, args 是该拦截方法的参数
 * MyBatis 的 StatementHandler 主要用于制作 Statement 对象，即装载 sql 语句
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

	/*
	 * 判断是否为要拦截的对象，参数是被检测的对象 如果不是要拦截的对象，则返回，如果是，则返回达成协议，具有代理权力的代理类 这里就是拦截要触发 prepare
	 * 方法的对象，增删改查都需要这个方法，所以这里只是初步过滤
	 */
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this); // this 就是本拦截器，查看源码
	}

	/*
	 * 参数 invocation 为被拦截下来的对象 首先，需要进一步筛选，比如分页功能只是查，就要把增删改这些给过滤掉 如何过滤？ invocation
	 * 是被拦截的对象，使用反射就可以获取它的一切信息 此时只要命名需要分页功能的查询方法名称加入特定的识别字母（如 ByPage 字母作后缀）,进行筛选即可
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// 获取 Statement
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		// MyBatis 的包装方法用于反射获取信息
		MetaObject metaObject = MetaObject.forObject(statementHandler, new DefaultObjectFactory(),
				new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		// 查看源码，其实先拦截了 RoutingStatementHandler，再通过它的 delegate 访问到 BaseStatementHandler
		// 而 BaseStatementHandler 通过 MappedStatement 对象访问到具体方法的具体属性
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement"); // 字符串是要获取信息的OGNL表达式，注意小写
		// 获取 Dao 接口的名称（sql 的 xml 配置文件中的 id 属性）
		String id = mappedStatement.getId();
		// 筛选匹配后
		if (id.matches(".+ByPage$")) {

			// 获取 BoundSql 类，它包含 Sql 的信息，因为 BoundSql 类 statementHandler 有直接获取的获取的方法，所以不用反射
			BoundSql boundSql = statementHandler.getBoundSql();

			// 获取 sql 参数，sql 语句就算不用参数，也要使用 Map 的形式，目的是为其他可能需要参数 sql 着想
			@SuppressWarnings("unchecked")
			Map<String, Object> paramsMap = (Map<String, Object>) boundSql.getParameterObject();
			Page page = (Page) paramsMap.get("page"); // 获取页数对象
			// 获取原始 sql 语句
			String sql = boundSql.getSql();

			// 查询拦截数据在数据库中的总条数并装置到 Page 对象中，用于传回拦截方
			// 获取总条数，注意查询sql语句返回的总条数时要声明别名
			// 拿出来的这条 sql 语句可能需要参数，mybatis 先会把 sql 语句中参数替代成 ？ 号，需要使用 ParameterHandler 的 set
			// 填充参数
			String countSql = "select count(*) from (" + sql + ") a";
			// 获取当前拦截对象的 Connection 数据库链接
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement preparedStatement = connection.prepareStatement(countSql);
			// 填充参数
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler"); // 注意小写
			parameterHandler.setParameters(preparedStatement);
			// 执行 sql 语句，获取总条数
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				page.setDataCount(resultSet.getInt(1));
			}

			// 改造后的 sql 语句
			String PageSql = sql + " limit " + page.getFirstIndex() + "," + page.getPageSize();
			// 把修改的 sql 语句替代原始的，BoundSql 没有修改 sql 的方法，只能用反射了
			// OGNL 表达式可以参考上面
			metaObject.setValue("delegate.boundSql.sql", PageSql);
		}

		// 返回主权，其实就是重新调用被拦截的方法
		return invocation.proceed();
	}

	// 用于读取注册时传入的参数
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}

}
