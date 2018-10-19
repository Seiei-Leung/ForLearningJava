package top.Seiei.learningJava;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AboutJunitDemoTest {

	AboutJunitDemo aboutJunitDemo;
	
	@Before
	public void setup() {
		System.out.println("Before");
		aboutJunitDemo = new AboutJunitDemo(123);
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
		aboutJunitDemo = null;
	}
	
	@Test
	public void testGetNum() {
		System.out.println("Test");
		assertEquals(123, aboutJunitDemo.getNum());
		assertNotNull(aboutJunitDemo.getNum());
	}

}
