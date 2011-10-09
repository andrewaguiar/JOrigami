import java.util.Date;

import br.org.origami.annotation.OrigamiField;

public class BasicOrigami {

	public enum OrigamiTestType {
		SIMPLE, COMPLEX, NORMAL
	}

	@OrigamiField(start = 1, end = 30)
	private String name;

	@OrigamiField(start = 31, end = 33)
	private int idade;

	@OrigamiField(start = 37, end = 37)
	private char sexo;

	@OrigamiField(start = 38, end = 49, opt = "ddMMyyyyHHmm")
	private Date dataNascimento;

	@OrigamiField(start = 50, end = 53)
	private double altura;

	@OrigamiField(start = 54, end = 57)
	private float note;

	@OrigamiField(start = 58, end = 60)
	private short code;

	@OrigamiField(start = 61, end = 61)
	private byte digit;

	@OrigamiField(start = 62, end = 69)
	private OrigamiTestType type;

	public void setType(final OrigamiTestType type) {
		this.type = type;
	}

	public OrigamiTestType getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(final int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return this.sexo;
	}

	public void setSexo(final char sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(final Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public double getAltura() {
		return this.altura;
	}

	public void setAltura(final double altura) {
		this.altura = altura;
	}

	public void setCode(final short code) {
		this.code = code;
	}

	public short getCode() {
		return this.code;
	}

	public void setDigit(final byte digit) {
		this.digit = digit;
	}

	public byte getDigit() {
		return this.digit;
	}

	public float getNote() {
		return this.note;
	}

	public void setNote(final float note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "NAME:[" + this.name + "] IDADE[" + this.idade + "] SEXO[" + this.sexo + "] NASCIDO EM[" + this.dataNascimento + "] ALTURA[" + this.altura + "]";
	}
}