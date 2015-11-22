package athens.tutorial3;

import athens.Page;
import athens.Triple;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Masoom on 18.11.2015.
 */
public class DateExtractor extends  Extractor {

    String name = "[A-Z][a-z]* [A-Z][a-z]*";
    String month = "[A-Z][a-z]*";
    String day = "(0?[1-9]|[12][0-9]|3[01])";
    String year = "((19|20)\\d\\d)";
    String date = month + " " + day + ", " + year;

    Triple triple;

    ArrayList<Triple> firstTwenty = new ArrayList<>();

    @Override
    public Triple extract(Page page){
        Pattern pattern=Pattern.compile(date);
        Matcher matcher=pattern.matcher(page.content);
        String match;
        if(matcher.find()) {
            match = matcher.group();
            firstTwenty.add(new Triple(page.title, "hasDate", match));
            return new Triple(page.title, "hasDate", match);

        }
        else {
            return null;
        }
    }


    public static void main(String args[]) throws Exception{
        new DateExtractor().run(new File(args[0]));
    }
}
