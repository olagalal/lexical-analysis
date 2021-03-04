package lexicalanalysis;

/*
 * @author Ola Galal
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalysis {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner reader = new Scanner(new File("input.txt"));
        ArrayList<String> lines = new ArrayList<>();
        Map<String, List<String>> symbolTable = new HashMap<String, List<String>>();
        
        //reads lines and then splits tokens
        while (reader.hasNextLine()) {
            String str = reader.nextLine();
            if (str.startsWith("//")) {
                continue;
            }

            if (!(str.length() == 0)) {
                String[] strSplit = str.trim().split("\\s+|\\s*,\\s*|\\;+|\\\"+|\\:+|\\[+|\\]+");  //regex for splitting
                List<String> list = Arrays.asList(strSplit);
                lines.addAll(list);  //put List of tokens in ArrayList
            }
        }

        if (lines.contains("/*") || lines.contains("*/")) {
            if (lines.contains("/*")) {
                int index1 = lines.indexOf("/*");
                int index2 = lines.indexOf("*/");
                int interval = index2 - index1 + 1;

                while (interval > 0) {
                    lines.remove(index1);
                    interval--;
                }
            }
        }

        String[] linesArray = lines.toArray(new String[0]); //convert ArrayList to Array
        List<String> keywords = new ArrayList<String>();  //put values from ArrayList to HashMap Key keywords    
        for (int count = 0; count < linesArray.length; count++){
            if (linesArray[count].matches("\\bif\\b+|\\bint\\b+|\\bfor\\b+|\\bfloat\\b+|\\belse\\b")) {  //reserved words               
                keywords.add(linesArray[count]);
            }
        }
        symbolTable.put("Keywords", keywords);  //put the ArrayLists in HashMap for particular Keys

        List<String> operators = new ArrayList<String>();
        for (int count = 0; count < linesArray.length; count++){
            if (linesArray[count].matches("[< > = + - * <= >=]")) {                
                operators.add(linesArray[count]); 
            }
         }
        symbolTable.put("Operators", operators);

        List<String> numeric = new ArrayList<String>();
        for (int count = 0; count < linesArray.length; count++) {
            if (linesArray[count].matches("\\d+|\\d+\\.\\d+")) {  //regex for numbers                 
                numeric.add(linesArray[count]);
            }
        }
        symbolTable.put("Numerical Constants", numeric);

        List<String> identifiers = new ArrayList<String>();
        for (int count = 0; count < linesArray.length; count++) {
            if (linesArray[count].matches("\\w+") && !linesArray[count].matches("\\d+") && !linesArray[count].matches("for|int|float|if|else")) {  //Use regex here for variables  
                if (!identifiers.contains(linesArray[count])) {
                    identifiers.add(linesArray[count]);
                }
            }
        }
        symbolTable.put("Identifiers", identifiers);

        List<String> special = new ArrayList<String>();
        for (int count = 0; count < linesArray.length; count++) {
            if (linesArray[count].matches("\\(|\\)|\\{|\\}")) {  //regex for variables  
                if (!identifiers.contains(linesArray[count])) {
                    special.add(linesArray[count]);
                }
            }
        }
        symbolTable.put("Special Characters", special);

        //Prints the ArrayList
        System.out.print("Word Boundaries: ");
        System.out.println(lines);
        System.out.println();

        //Prints the Symbol Table
        for (Map.Entry<String, List<String>> entry : symbolTable.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            System.out.print(key + ": ");
            System.out.println(values);
        }
    }
}
