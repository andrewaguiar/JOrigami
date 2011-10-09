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
public class OrigamiFormatter {

	public Object convert(final String value, final Class transformType, final String sub, final String opt) {
		Object r = null;

		if (String.class == transformType) {
			r = (value != null ? value : sub);

		} else if (StringBuilder.class == transformType) {
			r = new StringBuilder(value != null ? value : sub);

		} else if (StringBuilder.class == transformType) {
			r = new StringBuilder(value != null ? value : sub);

		} else if (Enum.class.isAssignableFrom(transformType)) {
			r = OrigamiFormatter.transformAsEnum(value, transformType, sub);

		} else if ((Boolean.class == transformType) || "boolean".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsBoolean(value, sub);

		} else if ((Byte.class == transformType) || "byte".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsByte(value, sub);

		} else if ((Short.class == transformType) || "short".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsShort(value, sub);

		} else if ((Character.class == transformType) || "char".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsChar(value, sub);

		} else if ((Integer.class == transformType) || "int".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsInt(value, sub);

		} else if ((Long.class == transformType) || "long".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsLong(value, sub);

		} else if ((Float.class == transformType) || "float".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsFloat(value, sub);

		} else if ((Double.class == transformType) || "double".equals(transformType.getName())) {
			r = OrigamiFormatter.transformAsDouble(value, sub);

		} else if (Date.class.isAssignableFrom(transformType)) {
			final String dateformat = opt;
			r = OrigamiFormatter.transformAsDate(value, dateformat, sub);
		}

		return r;
	}

	protected static Boolean transformAsBoolean(final String value, final String sub) {
		if (value != null) {
			return Boolean.parseBoolean(value.trim());
		}
		if (sub != null) {
			return Boolean.parseBoolean(sub.trim());
		}
		return null;
	}

	protected static Byte transformAsByte(final String value, final String sub) {
		if (value != null) {
			return Byte.parseByte(value.trim());
		}
		if (sub != null) {
			return Byte.parseByte(sub.trim());
		}
		return null;
	}

	protected static Short transformAsShort(final String value, final String sub) {
		if (value != null) {
			return Short.parseShort(value.trim());
		}
		if (sub != null) {
			return Short.parseShort(sub.trim());
		}
		return null;
	}

	protected static Character transformAsChar(final String value, final String sub) {
		if (value != null) {
			return value.trim().toCharArray()[0];
		}
		if (sub != null) {
			return sub.trim().toCharArray()[0];
		}
		return null;
	}

	protected static Integer transformAsInt(final String value, final String sub) {
		if (value != null) {
			return Integer.parseInt(value.trim());
		}
		if (sub != null) {
			return Integer.parseInt(sub.trim());
		}
		return null;
	}

	protected static Long transformAsLong(final String value, final String sub) {
		if (value != null) {
			return Long.parseLong(value.trim());
		}
		if (sub != null) {
			return Long.parseLong(sub.trim());
		}
		return null;
	}

	protected static Float transformAsFloat(final String value, final String sub) {
		if (value != null) {
			return Float.parseFloat(value.trim());
		}
		if (sub != null) {
			return Float.parseFloat(sub.trim());
		}
		return null;
	}

	protected static Double transformAsDouble(final String value, final String sub) {
		if (value != null) {
			return Double.parseDouble(value.trim());
		}
		if (sub != null) {
			return Double.parseDouble(sub.trim());
		}
		return null;
	}

	protected static Enum transformAsEnum(final String value, final Class clazz, final String sub) {
		if (value != null) {
			return Enum.valueOf(clazz, value.trim());
		}
		if (sub != null) {
			return Enum.valueOf(clazz, sub.trim());
		}
		return null;
	}

	protected static Date transformAsDate(final String value, final String df, final String sub) {
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