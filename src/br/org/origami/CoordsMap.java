package br.org.origami;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import br.org.origami.annotation.OrigamiField;

class CoordsMap {

	private final List<Coord> coords = new LinkedList<Coord>();
	private final Class<?> origamiClass;

	CoordsMap(final Class<?> origamiClass) {
		this.origamiClass = origamiClass;
		for (final Field field : origamiClass.getDeclaredFields()) {
			final OrigamiField origamiField = field.getAnnotation(OrigamiField.class);
			this.coords.add(new Coord(field, origamiField));
		}
	}

	void add(final int currentIndex, final char c) {
		for (final Coord coordInformation : this.coords) {
			coordInformation.add(currentIndex, c);
		}
	}

	Object consolidateRecord() throws InstantiationException, IllegalAccessException {
		final Object origami = this.origamiClass.newInstance();
		for (final Coord coordInformation : this.coords) {
			coordInformation.flush(origami);
		}
		return origami;
	}
}