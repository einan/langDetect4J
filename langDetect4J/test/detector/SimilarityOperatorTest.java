package detector;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import parser.Trigram;
import parser.Utils;
import fileio.FileOperator;

public class SimilarityOperatorTest {

	@Test
	public void computeSimilarityTest() throws Exception {
		ArrayList<Trigram> trTrigramList = new FileOperator()
				.readTrigramFile(new Utils().TR_TRIGRAM);

		ArrayList<Trigram> temp = new FileOperator()
				.readTrigramFile(new Utils().TR_TRIGRAM);

		double trSim = new SimilarityOperator().computeJaccardSim(
				trTrigramList, temp);
		assertEquals(1.0, trSim, 0);

	}

}
