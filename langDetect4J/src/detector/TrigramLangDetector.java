package detector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import parser.Similarity;
import parser.Trigram;
import parser.TrigramOperator;
import parser.Utils;
import fileio.FileOperator;

public class TrigramLangDetector implements LangDetector {

	final static Logger LOG = Logger.getLogger(TrigramLangDetector.class);

	public void evaluateLangDetection(String langType) {
		ArrayList<String> evalList = new FileOperator()
				.readFromFolder(new Utils().FOLDER_EVAL_DOCS + langType + "/");
		int numOfCorrects = 0;
		for (String evalText : evalList) {
			if (detectLanguage(evalText).equals(langType))
				numOfCorrects++;

		}
		LOG.info("-----------------------------------------------------");
		LOG.info(langType + " -- Evaluation (%) : "
				+ (numOfCorrects / (double) evalList.size()) * 100);
		LOG.info("-----------------------------------------------------");
	}

	// identifies the language of the given text
	public String detectLanguage(String evalText) {
		DecimalFormat decimalFormat = new DecimalFormat("###.##");
		ArrayList<Trigram> evalTrigramList = new TrigramOperator()
				.getEvalTrigList(evalText);
		TreeSet<Similarity> tree = new SimilarityOperator().computeSimilarities(evalTrigramList);

		LOG.info("Detected lang: " + tree.first().getLangType()
				+ " - Similarity Score : "
				+ decimalFormat.format(tree.first().getSimScore())
				+ " - Evaluated Text : " + evalText);
		return tree.first().getLangType();
	}

	}
