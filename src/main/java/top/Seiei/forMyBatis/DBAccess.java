package top.Seiei.forMyBatis;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import top.Seiei.forMyBatis.bean.Student;
import top.Seiei.forMyBatis.dao.StudentDao;
import top.Seiei.forMyBatis.entity.Page;

/*
 * 连接数据库
 * 创建 SqlSession
 * SqlSession 是 mybatis 的核心，作用能替代 jdbc
 * 可以向 sql 语句传参
 * 执行 sql 语句
 * 获取结果
 * 事务的控制
 * 
 */

public class DBAccess {
	public static SqlSession getSqlSession() throws IOException {

		// 通过核心配置文件获取连接信息
		// 核心配置文件包含了 连接数据库的基本信息，以及 mapping 映射配置文件的文件位置
		Reader reader = Resources.getResourceAsReader("mybatisConfig/config.xml");
		// 创建 SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 打开 SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

	// 读取数据库
	public static void main(String[] args) {
		SqlSession sqlSession = null;
		List<Student> studentList = new ArrayList<>();
		Page page = null;
		try {
			// 获取 SqlSession 对象
			sqlSession = DBAccess.getSqlSession();
			// 创建实现类
			StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
			int pageSize = 6;
			int pageIndex = 1;
			page = new Page(pageSize, pageIndex);
			Map<String, Object> params = new HashMap<>();
			params.put("page", page);
			studentList = studentDao.getAllByPage(params);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭 SqlSession 对象
			if (sqlSession != null) {
				sqlSession.close();
			}
			System.out.println("读取到的学生人数：" + studentList.size());
			System.out.println("总页数" + page.getPagesCount());
		}
	}
}
