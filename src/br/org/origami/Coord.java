package br.org.origami;

import java.lang.reflect.Field;

import br.org.origami.annotation.OrigamiField;

class Coord {

	private final Field field;
	private final OrigamiField origamiField;
	private final StringBuilder content = new StringBuilder();
	private static OrigamiFormatter defaultOrigamiFormatter = new OrigamiFormatter();

	Coord(final Field field, final OrigamiField origamiField) {
		this.field = field;
		this.origamiField = origamiField;
	}

	void add(int currentIndex, final char c) {
		currentIndex = currentIndex + 1;
		if (currentIndex >= this.origamiField.start() && currentIndex <= this.origamiField.end()) {
			this.content.append(c);
		}
	}

	void flush(final Object origami) throws OrigamiException{
		try {
			this.field.setAccessible(true);

			String rawValue = this.content.toString();
			if (rawValue.isEmpty()) {
				rawValue = null;
			}

			final OrigamiFormatter formatter = this.findFormatter(this.origamiField.formatter());
			final Object value = formatter.convert(rawValue, this.field.getType(), this.origamiField.sub(), this.origamiField.opt());

			this.field.set(origami, value);
			this.content.delete(0, this.content.length());
		} catch (final IndexOutOfBoundsException e) {
			throw new OrigamiException(e);
		} catch (final InstantiationException e) {
			throw new OrigamiException(e);
		} catch (final IllegalAccessException e) {
			throw new OrigamiException(e);
		}
	}

	private OrigamiFormatter findFormatter(final Class<? extends OrigamiFormatter> formatter) throws InstantiationException, IllegalAccessException {
		if (formatter == OrigamiFormatter.class) {
			return Coord.defaultOrigamiFormatter;
		}
		return formatter.newInstance();
	}
}
