package br.org.origami;

public interface FaultListener {

	void catches(Exception exception, int row);
}
