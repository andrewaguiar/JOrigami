import br.org.origami.OrigamiFormater;

public class CustomizedNameFormatter implements OrigamiFormater {

	@Override
	public Object convert(final String value, final Class transformType, final String sub, final String opt) throws Exception {
		return "---" + value.toUpperCase() + "---";
	}
}
