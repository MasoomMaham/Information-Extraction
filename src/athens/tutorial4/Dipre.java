package athens.tutorial4;

import athens.Page;
import athens.Parser;
import athens.Triple;
import athens.tutorial2.solution.Trie;
import sun.java2d.pipe.AATextRenderer;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dipre {

	/**
	 * Finds patterns in Wikipedia from seed facts.
	 * For example:
	 *   Wikipedia="blah blah Elvis is married to Priscilla blah blah"
	 *   seeds={<Elvis,hasSpouse,Priscilla>}
	 *   Result={" is married to "}
	 * Hint: Use a regular expression to find patterns,
	 * and restrict patterns to 10-25 letters and spaces.
	 * Include the boundary spaces in the pattern.
	 */
	public static Trie findPatterns(File wikipedia, Set<Triple> seeds) throws IOException {
		Trie result = new Trie();
		try (Parser pages = new Parser(wikipedia)) {
			while (pages.hasNext()) {

				for (Triple triple : seeds){

					String expression = triple.subject + " ([[a-z] ]*{10,25}) " + triple.object;

					Pattern pattern=Pattern.compile(expression);
					Matcher matcher=pattern.matcher(pages.next().content);

					while(matcher.find()){
						System.out.println(matcher.group(1));
						result.add(matcher.group(1));
					}

				}
			}
		}
		return (result);
	}

	/**
	 * Given Wikipedia, a set of patterns, a relation name,
	 * and a set of entities, finds facts.
	 *
	 * For example:
	 *   Wikipedia = "blah blah Barack is married to Michelle blah blah"
	 *   patterns = {"is married to", "is in love with"}
	 *   relation = "hasSpouse"
	 *   entities = {Michelle, Barack, Elvis, Priscilla}
	 *   Result = {<Barack, hasSpouse, Michelle>}
	 *
	 * Hint: First find the subject, then the pattern, and then the object
	 */
	public static Set<Triple> findFacts(File wikipedia, Trie patterns, String relation, Trie entities) throws IOException {
		Set<Triple> result = new HashSet<>();
		try (Parser pages = new Parser(wikipedia)) {
			while (pages.hasNext())
			{
				String content = pages.next().content;

				for (int i = 0; i < content.length(); i++) {
					int subjectLength = entities.containedLength(content, i);
					if (subjectLength > 0){
						String subject = content.substring(i, i+subjectLength);
						i += subjectLength + 1;
						int patternLength = patterns.containedLength(content, i);
						if(patternLength > 0){
							String pattern = content.substring(i, i+patternLength);
							i += patternLength + 1;
							int objectLength = entities.containedLength(content, i);
							if(objectLength > 0){
								String object = content.substring(i, i+objectLength);
								System.out.println(new Triple(subject, pattern, object).toString());
								result.add(new Triple(subject, pattern, object));
							}

						}
					}
				}
			}
		}
		return (result);
	}

	/**
	 * Given
	 * - a wikipedia file
	 * - a file with entity names
	 * - a seed subject
	 * - a seed relation
	 * - a seed object
	 * - a number of iterations
	 * in the args[], runs DIPRE
	 *
	 * Hints:
	 * - first find patterns for the facts
	 * - then find facts for the patterns
	 * - iterate this
	 * - Keep one single set of facts, and one single Trie of patterns.
	 *   It is OK if this makes the algorithm find again the same patterns,
	 *   and find again the same facts.
	 */
	public static void main(String args[]) throws IOException {
		Dipre dipre = new Dipre();
		Set<Triple> seeds = new HashSet<>();
		seeds.add(new Triple("Dublin", null, "the Republic of Ireland"));
		Trie trie = new Trie(new File("C:\\Users\\Masoom\\Documents\\Athens\\entities.txt"));

		//findFacts(new File(args[0]), findPatterns(new File(args[0]), seeds), "Is the capital city of", trie);

		Trie fp = findPatterns(new File(args[0]), seeds);
		//findPatterns(new File(args[0]), seeds);

		for (int i = 0; i < 2; i++) {
			seeds = findFacts(new File(args[0]), fp, "Is the capital city of", trie);
			fp = findPatterns(new File(args[0]), seeds);
		}

		for (Triple seed : seeds){
			System.out.println(seed);
		}
	}
}