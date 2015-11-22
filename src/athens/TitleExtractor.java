package athens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Masoom on 16.11.2015.
 */
public class TitleExtractor {

    public static void main(String[] args) {
        try{

            Parser parser = new Parser(new File(args[0]));
            File file = new File("titles");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (parser.hasNext()) {
                bufferedWriter.write(parser.next().title);
                bufferedWriter.write("\n");
            }

            parser.close();
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException e){
            System.err.print(e);
        }
    }



}
