package top.Seiei.forJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *	JDBC 的 Update
 *	使用 resources 路径里的 init.sql 初始化数据库表
 *
 */

public class AboutUpdate {

	final static String pw = "123456";
	final static String userName = "root";
	// 设置时区，编码，不使用 SSL 协议
	final static String JDBCurl = "jdbc:mysql://localhost:3306/test?useSSL=false&charaterEncoding=utf8&serverTimezone=GMT";

	// 获取数据库链接
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBCurl, userName, pw);
		return connection;
	}

	// 更新id=1的学生的name
	public static int updateStudent() throws SQLException {
		try (Connection connection = getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("update students set name = 'Seiei' where id = 1")) {
				int num = preparedStatement.executeUpdate();
				return num;
			}
		}
	}

	// 增加Student
	public static int insertStudent() throws SQLException {
		Student newStudent = new Student("Taka", (long) 4, null);
		try (Connection connection = getConnection()) {
			// 创建preparedStatment 对象时，添加 Statement.RETURN_GENERATED_KEYS 常量
			try (PreparedStatement preparedStatement = connection.prepareStatement(
					"insert students (name, class_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setObject(1, newStudent.name);
				preparedStatement.setObject(2, newStudent.class_id);
				// 执行语句
				int num = preparedStatement.executeUpdate();
				// 获取自增主键
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					while (resultSet.next()) {
						// 使用索引的形式获取返回的自增主键
						System.out.println("返回的自增主键是：" + resultSet.getLong(1));
					}
				}
				return num;
			}
		}
	}

	// 删除Student
	public static int deleteStudent() throws SQLException {
		try (Connection connection = getConnection()) {
			// 创建preparedStatment 对象时，添加 Statement.RETURN_GENERATED_KEYS 常量
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("delete from students where id = 5")) {
				// 执行语句
				int num = preparedStatement.executeUpdate();
				return num;
			}
		}
	}

	public static void main(String[] args) throws SQLException {
//		System.out.println("执行更新语句，" + AboutUpdate.updateStudent() + "行被修改");
//		System.out.println("执行添加语句，" + AboutUpdate.insertStudent() + "行被修改");
//		System.out.println("执行删除语句，" + AboutUpdate.deleteStudent() + "行被修改");
	}

}
