package top.Seiei.forAnnotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *	定义注解的时候，要定义 RUNTIME，以及作用域
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
	int min() default 1;

	int max() default 100;
}
