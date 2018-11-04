package top.Seiei.forJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *	JDBC 的 Select
 *	使用 resources 路径里的 init.sql 初始化数据库表
 *
 */
public class AboutSelect {

	final static String pw = "123456";
	final static String userName = "root";
	// 设置时区，编码，不使用 SSL 协议
	final static String JDBCurl = "jdbc:mysql://localhost:3306/test?useSSL=false&charaterEncoding=utf8&serverTimezone=GMT";

	// 获取数据库链接
	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBCurl, userName, pw);
		return connection;
	}

	// 获取全部学生列表
	public static List<Student> getAllStudents() throws SQLException {
		// 获取连接
		try (Connection connection = getConnection()) {
			// 创建 prepareStatement 对象
			try (PreparedStatement preparedStatement = connection.prepareStatement("select * from students")) {
				// 执行语句，获取查询结果二维表
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<Student> students = new ArrayList<>();
					// 循环 resultSet，获取记录，使用类似 JSONfast 的形式获取具体信息
					while (resultSet.next()) {
						Long id = resultSet.getLong("id");
						Long class_id = resultSet.getLong("class_id");
						String name = resultSet.getString("name");
						Student student = new Student(name, class_id, id);
						students.add(student);
					}
					return students;
				}
			}
		}
	}

	// 根据班级获取学生列表
	public static List<Student> getStudentsByClass(String classId) throws SQLException {
		// 获取连接
		try (Connection connection = getConnection()) {
			// 创建 prepareStatement 对象
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("select * from students where class_id = ?")) {
				// preparedStatement 对象的替代索引从 1 开始
				preparedStatement.setObject(1, classId);
				// 执行语句，获取查询结果二维表
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<Student> students = new ArrayList<>();
					// 循环 resultSet，获取记录，使用类似 JSONfast 的形式获取具体信息
					while (resultSet.next()) {
						Long id = resultSet.getLong("id");
						Long class_id = resultSet.getLong("class_id");
						String name = resultSet.getString("name");
						Student student = new Student(name, class_id, id);
						students.add(student);
					}
					return students;
				}
			}
		}
	}

	// 获取学生总数
	public static long getStudentsCount() throws SQLException {
		// 获取连接
		try (Connection connection = getConnection()) {
			// 创建 prepareStatement 对象
			try (PreparedStatement preparedStatement = connection
					.prepareStatement("select count(*) count from students")) {
				// 执行语句，获取查询结果二维表
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					Long count = (long) 0;
					// 循环 resultSet，获取记录，使用类似 JSONfast 的形式获取具体信息
					while (resultSet.next()) {
						count = resultSet.getLong("count");
					}
					return count;
				}
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		System.out.println("获取学生总数:" + AboutSelect.getAllStudents().size());
		System.out.println("获取学生总数:" + AboutSelect.getStudentsCount());
		System.out.println("获取1班学生总数:" + AboutSelect.getStudentsByClass("1").size());
	}
}
