package br.org.origami.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.org.origami.OrigamiFormatter;

/**
 * Represents a part of text file.
 * 
 * Example:
 * 
 * with the following line:
 * 
 * 'TESTE 21.45'
 * 
 * We can create a Origami class like this:
 * 
 * <pre>
 * TESTE  21.45
 * public class TestOrigami {
 * 
 * 	public enum OrigamiTestType {
 * 		SIMPLE, COMPLEX, NORMAL
 * 	}
 * 
 * 	@OrigamiField(start = 1, end = 5)
 * 	private String name;
 * 
 * 	@OrigamiField(start = 6, end = 8)
 * 	private int number;
 * 
 * 	@OrigamiField(start = 9, end = 12)
 * 	private double decimalValue;
 * }
 * </pre>
 * 
 * This way you will get a TestOrigami object filled like.
 * name = "TESTE"
 * int = 1
 * double = 1.45
 * 
 * @author andrew
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface OrigamiField {

	/** @return the initial index of information */
	int start();

	/** @return the final index of information */
	int end();

	/** @return the value processed used to create some customized Formatters to
	 * non simple field types */
	Class<? extends OrigamiFormatter> formatter() default OrigamiFormatter.class;

	/** @return the alternative value when the value is null. */
	String sub() default "";

	/** @return a opt that is used by Formatter */
	String opt() default "";
}