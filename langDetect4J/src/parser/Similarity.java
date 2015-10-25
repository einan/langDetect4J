package parser;

public class Similarity {
	private String langType;
	private double simScore;

	public Similarity(String langType, double simScore) {
		setLangType(langType);
		setSimScore(simScore);
	}

	public String getLangType() {
		return langType;
	}

	public void setLangType(String langType) {
		this.langType = langType;
	}

	public double getSimScore() {
		return simScore;
	}

	public void setSimScore(double simScore) {
		this.simScore = simScore;
	}

	@Override
	public String toString() {
		return getLangType() + "--" + getSimScore();
	}
}
