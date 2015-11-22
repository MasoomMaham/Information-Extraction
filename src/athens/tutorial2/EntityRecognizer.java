package athens.tutorial2;

import athens.Parser;

import java.io.*;

/**
 * This class implements a entity recognizer based on dictionary of 
 * entities.
 */
class EntityRecognizer {

	/**
	 * Given as arguments (1) a Wikipedia file and (2) a file with a list of entities, 
	 * this program reports mentions of entities in the content of articles. 
	 * An output record consists of:
	 * <ul>
	 * <li>The entity mentioned</li>
	 * <li>TAB (\t)
	 * <li>The title of the article where the mention occurs.</li>
	 * <li>NEWLINE (\n)
	 * </ul>
	 * The method should print one record per line.
	 */
	public static void main(String args[]) throws IOException {

		String path1 = "C:\\Users\\Masoom\\Documents\\Athens\\text.txt" ;
		String path2 = "C:\\Users\\Masoom\\Documents\\Athens\\entities.txt" ;

		Parser parser = new Parser(new File(path1));
		Trie trie = new Trie(new File(path2));
		File file = new File("test");

		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		while (parser.hasNext()) {
			String content = parser.next().content;
			String title = parser.next().title;

			for (int i = 0; i < content.length(); i++) {
				int length = trie.containedLength(content, i);
				if (length == 0)
					continue;
				bufferedWriter.write(content.substring(i, i + length));
				bufferedWriter.write("\t");
				System.out.println(content.substring(i, i + length));
				i += length;
			}
		}

		parser.close();
		bufferedWriter.close();
		fileWriter.close();

	}

}