package detector;

public interface LangDetector {
	public void evaluateLangDetection(String langType);

	public String detectLanguage(String evalText);
}
