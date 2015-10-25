package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebParser {
	private static final String WIKI_DIV_TAG = "div#mw-content-text";

	public String extractWikiContent(String pageUrl) {
		Document doc;
		String content = "";
		try {
			doc = Jsoup.connect(pageUrl).get();
			content = doc.select(WIKI_DIV_TAG).text();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	public ArrayList<String> splitContent(String content) {
		// remove punctuation and divide text into lower case words
		String[] splittedContent = content.replaceAll("\\p{P}", "")
				.toLowerCase().split("\\s");
		return new ArrayList<String>(Arrays.asList(splittedContent));
	}

}
