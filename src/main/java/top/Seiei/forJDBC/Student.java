package top.Seiei.forJDBC;

public class Student {
	public Student(String name, Long class_id, Long id) {
		super();
		this.name = name;
		this.class_id = class_id;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getClass_id() {
		return class_id;
	}

	public void setClass_id(Long class_id) {
		this.class_id = class_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String name;
	public Long class_id;
	public Long id;
}
