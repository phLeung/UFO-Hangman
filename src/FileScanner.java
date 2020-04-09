import java.util.*;
import java.io.*;
/*
   This class scans the nouns.txt file
*/
public class FileScanner
{
   /*
      Reads all the lines in a text file
      @param - input filename
      @return - an array containing words from the dictionary text file
   */
   public static ArrayList<String> loadWords(String filename) throws FileNotFoundException
   {
      File file = new File(filename);
      Scanner scan = new Scanner(new FileReader(file));
      ArrayList<String> words = new ArrayList<String>(); //all the words from the text file are stored in the words list
      try
      {
         while(scan.hasNextLine())//go through every line in the dictionary text file
         {
            String line = scan.nextLine();//line in dictionary text file
            line = line.trim();
            words.add(line.toUpperCase());//add word from dictionary text file to words array list
         }
      }
      
      finally
      {
         scan.close();
      }
      return words;
   }
}