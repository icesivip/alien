package model;

public class Simplex {

	public static final boolean MAX = true;
	public static final boolean MIN = false;
	
	private boolean optimization;
	private int restrictions;
	private Ecuation [] ecuations;
	
	/**
	 * pre: cada ecuación está en una línea
	 * @param opti
	 * @param nRes
	 * @param ecuas
	 */
	public Simplex (boolean opti, int nRes, String ecuas) {
		optimization = opti;
		restrictions = nRes;
		String[] ecuations = ecuas.split("\n");
		this.ecuations = new Ecuation[ecuations.length];
		for (int i = 0; i < ecuations.length; i++) {
			this.ecuations[i] = new Ecuation(ecuations[i]);
		}
	}
	
}
