package br.org.origami;

public class OrigamiException extends RuntimeException {

	public OrigamiException(final Throwable cause) {
		super(cause.toString(), cause);
	}
}