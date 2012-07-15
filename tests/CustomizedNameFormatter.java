import br.org.origami.DefaultOrigamiFormatter;

public class CustomizedNameFormatter extends DefaultOrigamiFormatter {

	@Override
	public Object convert(final String value, final Class transformType, final String sub, final String opt) throws Exception {
		return "---" + super.convert(value, transformType, sub, opt).toString().toUpperCase() + "---";
	}
}
