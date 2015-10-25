package fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import parser.Trigram;

public class FileOperator {

	final static Logger LOG = Logger.getLogger(FileOperator.class);
	
	// reads the entire content of the given file
	public String readEntireFile(String filePath) {
		String content = "";
		FileInputStream fis;
		File file = new File(filePath);
		byte[] data = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(data);
			fis.close();
			content = new String(data, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content.trim();
	}

	// reads file names from the evaluation dataset folder
	public ArrayList<String> readFromFolder(String folderPath) {
		ArrayList<String> temp = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().endsWith(".txt"))
				temp.add(readEntireFile(folderPath + file.getName()));

		}
		return temp;
	}

	// reads Wikipedia article links to prepare 3-Grams
	public ArrayList<String> readWikiLinksFile(String filePath) {
		BufferedReader br = null;
		ArrayList<String> temp = new ArrayList<String>();
		try {
			String sCurrentLine;
			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName("UTF-8"));
			br = new BufferedReader(isr);
			while ((sCurrentLine = br.readLine()) != null) {
				temp.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}

	// reads the content of 3-Gram file
	public ArrayList<Trigram> readTrigramFile(String filePath) {
		BufferedReader br = null;
		ArrayList<Trigram> temp = new ArrayList<Trigram>();
		try {
			String sCurrentLine;
			FileInputStream fr = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fr,
					Charset.forName("UTF-8"));
			br = new BufferedReader(isr);
			while ((sCurrentLine = br.readLine()) != null) {
				String[] split = sCurrentLine.split(",");
				temp.add(new Trigram(split[0], Integer.parseInt(split[1])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}

	// writes 3-Grams to the file
	public void writeToFile(Map<String, Integer> map, int threshold,
			String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				if (entry.getValue() >= threshold) {
					bw.write(entry.getKey());
					bw.write(",");
					bw.write(entry.getValue() + "\n");
				}
			}
			bw.close();
			LOG.info("Contents are written to the file :" + filePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
