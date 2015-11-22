package athens.tutorial5;

import athens.Page;
import athens.Triple;
import athens.tutorial3.Extractor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Masoom on 19.11.2015.
 */
public class ImprovedTypeExtractor extends Extractor {

    String expression = "(\\s\\w*/NNP\\s\\w*/VB\\w?\\s\\w*/DT\\s\\w*/JJ)\\s(\\w*)/NN\\s((\\w*)/NN)";

    @Override
    public Triple extract(Page page) {

        Pattern pattern=Pattern.compile(expression);
        Matcher matcher=pattern.matcher(page.firstSentence());
        String match;
        if(matcher.find()) {
            match = matcher.group(2) + " " + matcher.group(4);
            return new Triple(page.title, "type", match);
        }
        return null;
    }

    public static void main(String args[]) throws Exception{
        new ImprovedTypeExtractor().run(new File(args[0]));


    }
}
