package br.org.origami;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Default formatter can format the basic types: - byte - short - int - long -
 * float - double - String - Date - String - StringBuilder - StringBuffer.
 * 
 * @author andrew
 */
public class DefaultOrigamiFormatter implements OrigamiFormater {

	public Object convert(final String value, final Class transformType, final String sub, final String opt) throws Exception {
		Object r = null;

		if (String.class == transformType) {
			r = (value != null ? value : sub);

		} else if (StringBuilder.class == transformType) {
			r = new StringBuilder(value != null ? value : sub);

		} else if (StringBuilder.class == transformType) {
			r = new StringBuilder(value != null ? value : sub);

		} else if (Enum.class.isAssignableFrom(transformType)) {
			r = this.transformAsEnum(value, transformType, sub);

		} else if ((Boolean.class == transformType) || "boolean".equals(transformType.getName())) {
			r = this.transformAsBoolean(value, sub);

		} else if ((Byte.class == transformType) || "byte".equals(transformType.getName())) {
			r = this.transformAsByte(value, sub);

		} else if ((Short.class == transformType) || "short".equals(transformType.getName())) {
			r = this.transformAsShort(value, sub);

		} else if ((Character.class == transformType) || "char".equals(transformType.getName())) {
			r = this.transformAsChar(value, sub);

		} else if ((Integer.class == transformType) || "int".equals(transformType.getName())) {
			r = this.transformAsInt(value, sub);

		} else if ((Long.class == transformType) || "long".equals(transformType.getName())) {
			r = this.transformAsLong(value, sub);

		} else if ((Float.class == transformType) || "float".equals(transformType.getName())) {
			r = this.transformAsFloat(value, sub);

		} else if ((Double.class == transformType) || "double".equals(transformType.getName())) {
			r = this.transformAsDouble(value, sub);

		} else if (Date.class.isAssignableFrom(transformType)) {
			final String dateformat = opt;
			r = this.transformAsDate(value, dateformat, sub);
		}

		return r;
	}

	protected Boolean transformAsBoolean(final String value, final String sub) {
		if (value != null) {
			return Boolean.parseBoolean(value.trim());
		}
		if (sub != null) {
			return Boolean.parseBoolean(sub.trim());
		}
		return null;
	}

	protected Byte transformAsByte(final String value, final String sub) {
		if (value != null) {
			return Byte.parseByte(value.trim());
		}
		if (sub != null) {
			return Byte.parseByte(sub.trim());
		}
		return null;
	}

	protected Short transformAsShort(final String value, final String sub) {
		if (value != null) {
			return Short.parseShort(value.trim());
		}
		if (sub != null) {
			return Short.parseShort(sub.trim());
		}
		return null;
	}

	protected Character transformAsChar(final String value, final String sub) {
		if (value != null) {
			return value.trim().toCharArray()[0];
		}
		if (sub != null) {
			return sub.trim().toCharArray()[0];
		}
		return null;
	}

	protected Integer transformAsInt(final String value, final String sub) {
		if (value != null) {
			return Integer.parseInt(value.trim());
		}
		if (sub != null) {
			return Integer.parseInt(sub.trim());
		}
		return null;
	}

	protected Long transformAsLong(final String value, final String sub) {
		if (value != null) {
			return Long.parseLong(value.trim());
		}
		if (sub != null) {
			return Long.parseLong(sub.trim());
		}
		return null;
	}

	protected Float transformAsFloat(final String value, final String sub) {
		if (value != null) {
			return Float.parseFloat(value.trim());
		}
		if (sub != null) {
			return Float.parseFloat(sub.trim());
		}
		return null;
	}

	protected Double transformAsDouble(final String value, final String sub) {
		if (value != null) {
			return Double.parseDouble(value.trim());
		}
		if (sub != null) {
			return Double.parseDouble(sub.trim());
		}
		return null;
	}

	protected Enum transformAsEnum(final String value, final Class clazz, final String sub) {
		if (value != null) {
			return Enum.valueOf(clazz, value.trim());
		}
		if (sub != null) {
			return Enum.valueOf(clazz, sub.trim());
		}
		return null;
	}

	protected Date transformAsDate(final String value, final String df, final String sub) {
		if (df == null || df.trim().isEmpty()) {
			throw new IllegalArgumentException("");
		}
		final SimpleDateFormat d = new SimpleDateFormat(df);
		if (value != null) {
			try {
				return d.parse(value);
			} catch (final ParseException e) {
				throw new IllegalArgumentException(e.toString(), e);
			}
		}
		if (sub != null) {
			try {
				return d.parse(sub);
			} catch (final ParseException e) {
				throw new IllegalArgumentException(e.toString(), e);
			}
		}
		return null;
	}
}