import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;


public class FileScannerTest {
   //test array length
   private ArrayList<String> testwordslist = new ArrayList<String>();
   @Test
   public void testloadWordsLength()
   {
     try
     {
        this.testwordslist = FileScanner.loadWords("nouns.txt");
        Assert.assertTrue(this.testwordslist.size() > 0);
     }
     catch(FileNotFoundException e)
     {
       e.printStackTrace();
     }
     
   }
   @Test
   public void testloadWordsLength2()
   {
      try
      {
         this.testwordslist = FileScanner.loadWords("nouns.txt");
         Assert.assertNotEquals(this.testwordslist.size(),0);
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
}
