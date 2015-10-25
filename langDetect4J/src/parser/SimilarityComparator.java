package parser;

import java.util.Comparator;

public class SimilarityComparator implements Comparator<Similarity> {

	@Override
	public int compare(Similarity s1, Similarity s2) {
		if (s2.getSimScore() > s1.getSimScore()) {
			return 1;
		} else {
			return -1;
		}
	}

}
