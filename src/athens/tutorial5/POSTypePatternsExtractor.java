package athens.tutorial5;

import  java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import athens.Page;
import athens.Parser;
import athens.Triple;

public class POSTypePatternsExtractor {

	/**
	 * A suggested value for the maximum length of the textual patterns.
	 */
	private static final int MAXLENGTH = 70;

	public static List<String> STOPCHARS = Arrays.asList("./.", ",/,", ";/;");

	public static void main(String args[]) throws IOException {
		File posTaggedWikipedia = new File(args[0]);
		List<String> seeds = Files.readAllLines(FileSystems.getDefault().getPath("C:\\Users\\Masoom\\Documents\\Athens\\pos.txt"),
				Charset.defaultCharset());
		for (String seed : seeds) {
			String[] seedFact = seed.split("\\t");
			Triple triple = new Triple(seedFact[0], seedFact[1], seedFact[2]);
			findPatterns(triple, posTaggedWikipedia);
		}
 	}

	/**
	 * Given a POS-tagged triple (e.g. Rio/NNP de/NNP Janeiro/NNP type	city/NN) and a
	 * POS-tagged corpus, it extracts all the textual patterns lying between the subject and prints them out
	 * and the object of the triple that occur in the corpus.
	 * @param seedFact
	 * @param posTaggedWikipedia
	 * @throws IOException
	 */
	private static void findPatterns(Triple seedFact, File posTaggedWikipedia) throws IOException {
		try (Parser pages = new Parser(posTaggedWikipedia)) {
			while (pages.hasNext()) {
				Page page = pages.next();
				// TODO(Extract textual patterns between seedFact.subject and seedFact.object)

				String subject = seedFact.subject;
				String object = seedFact.object;
				String content = page.content;
				String expression = subject + " ((\\w*\\/\\w{1,3})(\\s\\w*\\/\\w{1,3})*) " + object;

				Pattern pattern=Pattern.compile(expression);
				Matcher matcher=pattern.matcher(content);

				while(matcher.find()){
					System.out.println(new Triple(subject, matcher.group(1), object).toString());
				}
			}
		}
	}
}