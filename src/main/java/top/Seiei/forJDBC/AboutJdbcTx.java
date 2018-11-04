package top.Seiei.forJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *	创建 JDBC 事务
 *
 */

public class AboutJdbcTx {

	final static String pw = "123456";
	final static String userName = "root";
	// 设置时区，编码，不使用 SSL 协议
	final static String JDBCurl = "jdbc:mysql://localhost:3306/test?useSSL=false&charaterEncoding=utf8&serverTimezone=GMT";

	// 获取数据库链接
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBCurl, userName, pw);
		return connection;
	}

	// 更新
	static void updateName(Connection connection, String name, long id) throws SQLException {
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("update students set name = ? where id = ?")) {
			preparedStatement.setObject(1, name);
			preparedStatement.setObject(2, id);
			preparedStatement.executeUpdate();
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		try {
			// 获取连接
			connection = getConnection();
			// 设置事务级别
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			// 开启事务
			connection.setAutoCommit(false);
			// 执行sql
			updateName(connection, "curry", (long) 3);
			updateName(connection, "james", (long) 4);
			// 提交事务
			connection.commit();
		} catch (Exception e) {
			// 事务回滚
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null) {
				// 关闭事务
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
