package br.org.origami;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.org.origami.annotation.OrigamiField;

class Coord {

	private final Field field;
	private final OrigamiField origamiField;
	private final StringBuilder content = new StringBuilder();
	private final Map<Class<? extends OrigamiFormater>, OrigamiFormater> formatters = new HashMap<Class<? extends OrigamiFormater>, OrigamiFormater>();
	private boolean faultTolerant;
	private boolean error;
	private static OrigamiFormater defaultOrigamiFormatter = new DefaultOrigamiFormatter();

	Coord(final Field field, final OrigamiField origamiField) {
		this.field = field;
		this.origamiField = origamiField;
	}

	void setFaultTolerant(final boolean faultTolerant) {
		this.faultTolerant = faultTolerant;
	}

	void add(int currentIndex, final char c) {
		currentIndex = currentIndex + 1;
		if (currentIndex >= this.origamiField.start() && currentIndex <= this.origamiField.end()) {
			this.content.append(c);
		}
	}

	void flush(final Object origami) throws Exception {
		try {
			this.field.setAccessible(true);

			String rawValue = this.content.toString();
			if (rawValue.isEmpty()) {
				rawValue = null;
			}
			boolean successfully = false;

			final OrigamiFormater formatter = this.findFormatter(this.origamiField.formatter());
			Object value = null;
			try {
				value = formatter.convert(rawValue, this.field.getType(), this.origamiField.sub(), this.origamiField.opt());
				successfully = true;
			} catch (final Exception e) {
				if (!this.faultTolerant) {
					throw e;
				}
				this.error = true;
				successfully = false;
			}
			if (successfully) {
				this.field.set(origami, value);
			}

		} catch (final IndexOutOfBoundsException e) {
			throw new OrigamiException(e);
		} catch (final InstantiationException e) {
			throw new OrigamiException(e);
		} catch (final IllegalAccessException e) {
			throw new OrigamiException(e);
		}
	}

	void clean() {
		this.content.delete(0, this.content.length());
	}

	private OrigamiFormater findFormatter(final Class<? extends OrigamiFormater> formatter) throws InstantiationException, IllegalAccessException {
		if (formatter == DefaultOrigamiFormatter.class) {
			return Coord.defaultOrigamiFormatter;
		}

		OrigamiFormater origamiFormatter = this.formatters.get(formatter);
		if (origamiFormatter == null) {
			this.formatters.put(formatter, (origamiFormatter = formatter.newInstance()));
		}
		return origamiFormatter;
	}

	public boolean wasError() {
		return (this.error ? (this.error = false) == false : false);
	}
}
