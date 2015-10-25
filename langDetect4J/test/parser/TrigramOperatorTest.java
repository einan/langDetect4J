package parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TrigramOperatorTest {

	@Test
	public void trigramTest() throws Exception {
		ArrayList<String> expectedNgramList = new ArrayList<String>(
				Arrays.asList("_th", "he_"));
		assertEquals(expectedNgramList,
				new TrigramOperator().generate3Grams("the"));

	}

}
