package detector;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import parser.Utils;
import fileio.FileOperator;

public class RegexLangDetector implements LangDetector {
	final static Logger LOG = Logger.getLogger(RegexLangDetector.class);

	// private static final String LANG_PATTERN = "^[a-zA-Z0-9ğüşöçİĞÜŞÖÇ]+$";
	// private static final String TWO_CONS_PATTERN =
	// "^[^aeiou]{2}.*|\\w*[^aeiou]{2}\\b";

	private static final String TR_LANG_PATTERN = ".*[ğşç].*";
	// öü are also in Turkish
	private static final String DE_UMLAUT_PATTERN = ".*[äß].*";

	public void evaluateLangDetection(String langType) {
		DecimalFormat decimalFormat = new DecimalFormat("###.##");
		ArrayList<String> evalList = new FileOperator()
				.readFromFolder(new Utils().FOLDER_EVAL_DOCS + langType + "/");

		int numOfcorrects = 0;
		for (String evalText : evalList) {
			String detectedLang = detectLanguage(evalText);
			if (detectedLang.equals(new Utils().TR_LANG))
				numOfcorrects++;
			else if (detectedLang.equals(new Utils().DE_LANG))
				numOfcorrects++;
			else
				numOfcorrects++;
		}
		LOG.info("-----------------------------------------------------");
		LOG.info(langType
				+ " -- Evaluation (%) : "
				+ decimalFormat.format((numOfcorrects / (double) evalList
						.size()) * 100));
		LOG.info("-----------------------------------------------------");
	}

	// identifies the language for the given text
	public String detectLanguage(String evalText) {
		String clearedSentence = new RegexLangDetector().clearSpaces(evalText);
		boolean isTurkish = new RegexChecker(TR_LANG_PATTERN)
				.check(clearedSentence);
		boolean validateUmlaut = new RegexChecker(DE_UMLAUT_PATTERN)
				.check(clearedSentence);
		String detectedLang = "";
		if (isTurkish) {
			LOG.info("Detected lang: tr " + " - Evaluated Text : " + evalText);
			detectedLang = new Utils().TR_LANG;
		} else if (validateUmlaut) {
			LOG.info("Detected lang: de " + " - Evaluated Text : " + evalText);
			detectedLang = new Utils().DE_LANG;
		} else {
			detectedLang = new Utils().EN_LANG;
			LOG.info("Detected lang: en " + " - Evaluated Text : " + evalText);
		}

		return detectedLang;
	}

	public String clearSpaces(String sentence) {
		return sentence.replaceAll("\\s+", "").toLowerCase();
	}
}
