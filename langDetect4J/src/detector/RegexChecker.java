package detector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker {
	private Pattern pattern;
	private Matcher matcher;

	public RegexChecker(String langPattern) {
		pattern = Pattern.compile(langPattern);
	}

	// validates text with regex
	public boolean check(final String text) {
		matcher = pattern.matcher(text);
		return matcher.matches();
	}
}
