package detector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import parser.Similarity;
import parser.SimilarityComparator;
import parser.Trigram;
import parser.Utils;
import fileio.FileOperator;

public class SimilarityOperator {
	// computes Jaccard Similarities for all languages
	public TreeSet<Similarity> computeSimilarities(ArrayList<Trigram> temp) {

		// get the prepared 3-Grams for each language
		Utils utils = new Utils();
		ArrayList<Trigram> deTrigramList = new FileOperator()
				.readTrigramFile(utils.DE_TRIGRAM);
		ArrayList<Trigram> enTrigramList = new FileOperator()
				.readTrigramFile(utils.EN_TRIGRAM);
		ArrayList<Trigram> trTrigramList = new FileOperator()
				.readTrigramFile(utils.TR_TRIGRAM);

		TreeSet<Similarity> tree = new TreeSet<Similarity>(
				new SimilarityComparator());
		double deSim = computeJaccardSim(deTrigramList, temp);
		tree.add(new Similarity(new Utils().DE_LANG, deSim));

		double enSim = computeJaccardSim(enTrigramList, temp);
		tree.add(new Similarity(new Utils().EN_LANG, enSim));

		double trSim = computeJaccardSim(trTrigramList, temp);
		tree.add(new Similarity(new Utils().TR_LANG, trSim));

		// new language 3-Gram list can be added here...

		return tree;
	}

	// calculates Jaccard Similarity between language-input 3-Gram pair
	public double computeJaccardSim(ArrayList<Trigram> languageTrigrams,
			ArrayList<Trigram> inputTrigrams) {

		if (languageTrigrams.size() == 0 || inputTrigrams.size() == 0) {
			return 0.0;
		}
		ArrayList<String> langTrigramList = obtainList(languageTrigrams);
		ArrayList<String> inputTrigramList = obtainList(inputTrigrams);

		Set<String> unionOfLists = new HashSet<String>(langTrigramList);
		unionOfLists.addAll(inputTrigramList);

		Set<String> intersectionOfLists = new HashSet<String>(langTrigramList);
		intersectionOfLists.retainAll(inputTrigramList);

		double sumIntersect = getSum(languageTrigrams, intersectionOfLists);
		double sumUnion = getSum(languageTrigrams, unionOfLists);

		return (double) sumIntersect / (double) sumUnion;
	}

	private double getSum(List<Trigram> list, Set<String> set) {
		double sum = 0;
		for (String trigram : set) {
			for (Trigram trig : list) {
				if (trig.getTrigram().equals(trigram))
					sum += trig.getFreq();
			}
		}
		return sum;
	}

	private ArrayList<String> obtainList(List<Trigram> languageTrigrams) {
		ArrayList<String> xStrList = new ArrayList<String>();
		for (Trigram trigram : languageTrigrams) {
			xStrList.add(trigram.getTrigram());
		}
		return xStrList;
	}

}
