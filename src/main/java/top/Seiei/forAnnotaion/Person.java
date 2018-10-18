package top.Seiei.forAnnotaion;

public class Person {

	public Person(String name, int age) {
		this.age = age;
		this.name = name;
	}

	@NotNull
	public String name;

	@Range(min = 1, max = 50)
	public int age;

}
