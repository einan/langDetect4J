package detector;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import parser.Utils;

public class RegexLangDetectorTest {

	@Test
	public void deRegexTest() throws Exception {
		String detectedLangDE = new RegexLangDetector()
				.detectLanguage("Eine ideale Ergänzung zu Beckenbauer, dem Mann für die große Bühne.");
		assertEquals(new Utils().DE_LANG, detectedLangDE);
	}

	@Test
	public void enRegexTest() throws Exception {
		String detectedLangEN = new RegexLangDetector()
				.detectLanguage("We all know that Washington needs to change the way it does business.");
		assertEquals(new Utils().EN_LANG, detectedLangEN);
	}
	
	@Test
	public void trRegexTest() throws Exception {
		String detectedLangTR = new RegexLangDetector()
				.detectLanguage("Kaş, Finike ve Demre ilçelerinin yağıştan etkileneceğini söyledi.");
		assertEquals(new Utils().TR_LANG, detectedLangTR);

	}

}
