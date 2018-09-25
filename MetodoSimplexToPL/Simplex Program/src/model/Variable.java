package model;

public class Variable {

	public static final byte BASIC = 0;
	public static final byte NON_BASIC = 1;
	public static final byte FICTI = 2;
	
	private double coefficient;
	private boolean sign;
	private byte type;
	
	public Variable(boolean signo, double coeficiente, byte tipo) {
		sign = signo;
		coefficient = coeficiente;
		type = tipo;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public boolean isSign() {
		return sign;
	}

	public void setSign(boolean sign) {
		this.sign = sign;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}
	
}
