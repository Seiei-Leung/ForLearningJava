package top.Seiei.forIO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class AboutFileDemo {

	public static void main(String[] args) throws IOException {
		File imgFile = new File("F:\\Desert.jpg");
		File filePath = new File(".\\"); // 会输出当前项目的根目录的路径
		System.out.println("imgFile 是不是一个文件：" + imgFile.isFile());
		System.out.println("filePath 是不是一个个目录：" + filePath.isDirectory());
		System.out.println("获取 imgFile 的路径：" + imgFile.getPath());
		System.out.println("获取 imgFile 的绝对路径：" + imgFile.getAbsolutePath());
		System.out.println("获取 imgFile 的规范路径：" + imgFile.getCanonicalPath());
		System.out.println("获取 filePath 的路径：" + filePath.getPath());
		System.out.println("获取 filePath 的绝对路径：" + filePath.getAbsolutePath());
		System.out.println("获取 filePath 的规范路径：" + filePath.getCanonicalPath());
		System.out.println("=====================================");
		System.out.println("文件的操作");
		System.out.println("imgFile 是否可读：" + imgFile.canRead());
		System.out.println("imgFile 是否可写：" + imgFile.canWrite());
		System.out.println("imgFile 是否可运行：" + imgFile.canExecute());
		System.out.println("imgFile 的大小：" + imgFile.length());
		File newFile = new File("F:\\newFile.jpg");
		newFile.createNewFile(); // 创建文件，返回一个布尔值，创建成功返回true，如果存在与此文件名相同的文件，则返回false
		newFile.delete(); // 删除文件

		System.out.println("=====================================");
		System.out.println("目录的操作");
		File filepath1 = new File("F:\\");
		System.out.println("输出 F:\\ 目录下的所有文件名和子目录：");

		// 使用 list() 方法返回字符串， listFiles() 方法返回 File 对象
//		for (String item : filepath1.list()) {
//			System.out.println(item);
//		}

		// 使用 list(FileFilter filter) 方法过滤文件，
		// 输出文件后缀为 pdf 的文件名
		for (String item : filePath.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith("pdf")) {
					return true;
				}
				return false;
			}
		})) {
			System.out.println(item);
		}

		// mkdir() 创建目录， mkdirs() 必要时把父目录也创建出来
		File filepath2 = new File("F:\\Seiei");
		filepath2.mkdir();
		filepath2.delete(); // 删除目录
	}

}
