package parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fileio.FileOperator;

public class TrigramOperator {

	public void getTrigramLists() {
		Utils utils = new Utils();
		createTrigramFile(utils.WIKI_LINKS_TR, utils.TR_TRIGRAM);
		createTrigramFile(utils.WIKI_LINKS_EN, utils.EN_TRIGRAM);
		createTrigramFile(utils.WIKI_LINKS_DE, utils.DE_TRIGRAM);
	}

	// creates 3-Gram file from Wikipedia Links
	public void createTrigramFile(String linksFile, String trigramFile) {
		ArrayList<String> wikiLinks = new FileOperator()
				.readWikiLinksFile(linksFile);
		Map<String, Integer> map = new HashMap<>();
		for (String url : wikiLinks) {
			String extractedWikiContent = new WebParser()
					.extractWikiContent(url);
			ArrayList<String> splittedContentList = new WebParser()
					.splitContent(extractedWikiContent);
			for (String word : splittedContentList) {
				ArrayList<String> generate3Grams = generate3Grams(word);
				for (String trigram : generate3Grams) {
					Integer n = map.get(trigram);
					n = (n == null) ? 1 : ++n;
					map.put(trigram, n);
				}
			}
		}
		Map<String, Integer> sortedMap = rankByComparator(map);
		new FileOperator().writeToFile(sortedMap, new Utils().THRESHOLD_FREQ, trigramFile);
	}

	// generates sorted 3-Grams for the given text
	public ArrayList<Trigram> getEvalTrigList(String text) {
		ArrayList<Trigram> temp = new ArrayList<Trigram>();
		Map<String, Integer> sortedMap = getTextTrigram(text);
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			temp.add(new Trigram(entry.getKey(), entry.getValue()));
		}
		return temp;
	}

	// generates unsorted 3-Grams for the given text
	public Map<String, Integer> getTextTrigram(String text) {
		Map<String, Integer> map = new HashMap<>();
		ArrayList<String> splittedContentList = new WebParser()
				.splitContent(text);
		for (String word : splittedContentList) {
			ArrayList<String> generate3Grams = generate3Grams(word);

			for (String trigram : generate3Grams) {
				Integer n = map.get(trigram);
				n = (n == null) ? 1 : ++n;
				map.put(trigram, n);
			}
		}
		Map<String, Integer> sortedMap = rankByComparator(map);
		return sortedMap;
	}

	// generates 3-Grams of the given word
	public ArrayList<String> generate3Grams(String word) {
		ArrayList<String> ngramList = new ArrayList<String>();
		int length = word.length();
		if (length > 1) {
			String ngram = "_" + word.substring(0, 2);
			ngramList.add(ngram);
			for (int i = 1; i < length - 2; i++) {
				ngram = word.substring(i, i + 3);
				ngramList.add(ngram);
			}
			ngramList.add(word.substring(length - 2, length) + "_");
		}
		return ngramList;
	}

	// converts unsorted 3-Grams to sorted ones
	public Map<String, Integer> rankByComparator(
			Map<String, Integer> unsortedMap) {
		List<Map.Entry<String, Integer>> tempList = new LinkedList<Map.Entry<String, Integer>>(
				unsortedMap.entrySet());
		Collections.sort(tempList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> o1,
							Map.Entry<String, Integer> o2) {
						return (o2.getValue()).compareTo(o1.getValue());
					}
				});
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = tempList.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	// converts frequencies to normalized values
	public ArrayList<Trigram> getNormalizedFreq(ArrayList<Trigram> list) {
		ArrayList<Trigram> normalizedList = new ArrayList<Trigram>();
		double bigFreqTemp = list.get(0).getFreq();
		for (Trigram trig : list) {
			normalizedList.add(new Trigram(trig.getTrigram(), trig.getFreq()
					/ bigFreqTemp));
		}
		return normalizedList;
	}

	public void printMap(Map<String, Integer> map, int threshold) {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() > threshold) {
				System.out.println("Key : " + entry.getKey() + " Value : "
						+ entry.getValue());
			}
		}
	}

}
