package br.org.origami;

import java.io.InputStream;
import java.util.Arrays;

public class OrigamiFactory<T> {

	private final String breakLineString;
	private final Class<?> origamiClass;
	private final ConsolidatingListener listener;
	private boolean faultTolerant;
	private FaultListener faultListener;

	public static <T> OrigamiFactory<T> createLinesBasedFactory(final Class<T> origamiClass, final ConsolidatingListener<T> listener) {
		return new OrigamiFactory(origamiClass, "\n", listener);
	}

	public static <T> OrigamiFactory<T> createSharpBasedFactory(final Class<T> origamiClass, final ConsolidatingListener<T> listener) {
		return new OrigamiFactory(origamiClass, "#", listener);
	}

	public static <T> OrigamiFactory<T> createCommaBasedFactory(final Class<T> origamiClass, final ConsolidatingListener<T> listener) {
		return new OrigamiFactory(origamiClass, ",", listener);
	}

	public OrigamiFactory(final Class<?> origamiClass, final String breakLineString, final ConsolidatingListener listener) {
		this.breakLineString = breakLineString;
		this.origamiClass = origamiClass;
		this.listener = listener;
		if (origamiClass == null) {
			throw new IllegalArgumentException("origamiClass can NOT be null");
		}
	}

	public void mount(final InputStream in) throws Exception {
		final CoordsMap coords = new CoordsMap(this.origamiClass);
		coords.setFaultTolerant(this.faultTolerant);

		final LastCharsBuffer lastCharsBuffer = new LastCharsBuffer(this.breakLineString);

		int currentRow = 0;
		int currentIndex = 0;
		for (int i = -1; (i = in.read()) != -1;) {
			final char c = (char) i;
			lastCharsBuffer.add(c);

			if (this.faultListener != null) {
				try {
					coords.add(currentIndex, c);
				} catch (final Exception e) {
					this.faultListener.catches(e, currentRow);
				}
			} else {
				coords.add(currentIndex, c);
			}

			currentIndex++;
			if (lastCharsBuffer.endsWithReference()) {
				currentRow++;

				if (this.faultListener != null) {
					try {
						this.consolidate(coords);
					} catch (final Exception e) {
						this.faultListener.catches(e, currentRow);
					}
				} else {
					this.consolidate(coords);
				}

				currentIndex = 0;
			}
		}
		if (currentIndex > 0) {
			currentRow++;
			if (this.faultListener != null) {
				try {
					this.consolidate(coords);
				} catch (final Exception e) {
					this.faultListener.catches(e, currentRow);
				}
			} else {
				this.consolidate(coords);
			}
		}
	}

	public void setFaultTolerant(final boolean faultTolerant) {
		this.faultTolerant = faultTolerant;
	}

	public void setFaultListener(final FaultListener faultListener) {
		this.faultListener = faultListener;
	}

	private void consolidate(final CoordsMap coords) throws Exception {
		try {
			final Object obj = coords.consolidateRecord();
			if (obj != null) {
				this.listener.process(obj);
			}
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("origamiClass do NOT have a public valid constructor", e);
		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("origamiClass do NOT have a public valid constructor", e);
		}
	}

	private static class LastCharsBuffer {
		private final char[] lastString;
		private final String reference;

		public LastCharsBuffer(final String reference) {
			this.reference = reference;
			this.lastString = new char[reference.length()];
		}

		public boolean endsWithReference() {
			return Arrays.equals(this.lastString, this.reference.toCharArray());
		}

		public void add(final char nc) {
			for (int i = 0; i < this.lastString.length - 1; i++) {
				this.lastString[i] = this.lastString[i + 1];
			}
			this.lastString[this.lastString.length - 1] = nc;
		}

		@Override
		public String toString() {
			return new String(this.lastString);
		}
	}
}