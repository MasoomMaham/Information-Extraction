package athens.tutorial2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


/**
 * It defines the methods that define a Trie data structure.
 * @author Luis GalÃ¡rraga.
 *
 */
public class Trie {

    /**
     * Adds a string to the trie.
     * @param word
     * @return true if the trie changed as a result of this operation, that is if
     * the new string was not in the dictionary.
     */
    public boolean add(String str) {
        throw new UnsupportedOperationException("The method Trie.add has not been implemented.");
    }

    /**
     * Checks whether a string exists in the trie.
     * @param str
     * @return true if the string is in the trie, false otherwise.
     */
    public boolean contains(String str) {
        throw new UnsupportedOperationException("The method Trie.contains has not been implemented.");
    }

    /**
     * Given a string and a starting position (<var>startPos</var>), it returns the length
     * of the longest word in the trie that starts in the string at the given position.
     * For example,
     * if the trie contains words "New York", and "New York City", containedLength("I live in New York City!", 10)
     * returns 13, that is the length of the longest word ("New York State") registered in the
     * trie that starts at position 10.
     * Hint: proceed as in the lecture
     * @param s
     * @param startPos
     * @return int
     */
    public int containedLength(String s, int startPos) {
        throw new UnsupportedOperationException("The method Trie.containedLength has not been implemented.");
    }

    /** Constructs a Trie from the lines of a file*/
    public Trie(File file) throws IOException {
        try(BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"))) {
            String line;
            while((line=in.readLine())!=null) {
                add(line);
            }
        }
    }

    /** Constructs an empty Trie*/
    public Trie() {
    }

    /** Constructs a Trie from a collection*/
    public Trie(Iterable<String> collection) {
        for(String s : collection) add(s);
    }

    /** Use this to test your implementation. Provide the file with list of Wikipedia titles as argument to this program.*/
    public static void main(String[] args) throws IOException {
        Trie trie = new Trie(new File(args[0]));

        for(String test : Arrays.asList("Brooklyn","Dudweiler","Elvis Presley","Juan Pihuave")) {
            System.out.println(test + " is in trie: " + trie.contains(test));
        }

    }
}