package br.org.origami;

/** Define classes that can format the field value.
 * @author andrew */
public interface OrigamiFormater {
	Object convert(final String value, final Class transformType, final String sub, final String opt) throws Exception;
}
