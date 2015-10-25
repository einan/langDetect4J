package parser;


public class Trigram {
	private String trigram;
	private double freq;

	public Trigram(String trigram, double d) {
		setTrigram(trigram);
		setFreq(d);
	}

	public String getTrigram() {
		return trigram;
	}

	public void setTrigram(String trigram) {
		this.trigram = trigram;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double d) {
		this.freq = d;
	}

	@Override
	public String toString() {
		return getTrigram() + "--" + getFreq();
	}

}
