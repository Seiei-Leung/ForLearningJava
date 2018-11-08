package top.Seiei.forMyBatis.dao;

import java.util.List;
import top.Seiei.forMyBatis.bean.Student;

// 使用 SqlSession 对象的 getMapper 方法，可以让 myBatis 会自动生成实现类

public interface StudentDao {
	public List<Student> getAllByPage();
}
