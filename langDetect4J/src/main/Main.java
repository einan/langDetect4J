package main;

import parser.TrigramOperator;
import parser.Utils;
import detector.RegexLangDetector;
import detector.TrigramLangDetector;

public class Main {

	public static void main(String[] args) {
		
//		new TrigramOperator().getTrigramLists();

		Utils utils = new Utils();
		// experiments 3-Gram method with evaluation dataset for German
		new TrigramLangDetector().evaluateLangDetection(utils.DE_LANG);
		// experiments 3-Gram method with evaluation dataset for English
		new TrigramLangDetector().evaluateLangDetection(utils.EN_LANG);
		// experiments 3-Gram method with evaluation dataset for Turkish
		new TrigramLangDetector().evaluateLangDetection(utils.TR_LANG);

		// experiments Regex method with evaluation dataset for German
		new RegexLangDetector().evaluateLangDetection(utils.DE_LANG);
		// experiments Regex method with evaluation dataset for English
		new RegexLangDetector().evaluateLangDetection(utils.EN_LANG);
		// experiments Regex method with evaluation dataset for Turkish
		new RegexLangDetector().evaluateLangDetection(utils.TR_LANG);

	}

}
