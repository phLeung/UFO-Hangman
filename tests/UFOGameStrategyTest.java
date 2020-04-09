import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class UFOGameStrategyTest {
   private UFOGameConsole testconsole = new UFOGameConsole("nouns.txt");
   private int maxwrong = 6;
   private List<String> testwordslist;
   private List<String>testwords = new ArrayList<String>();
   private UFOGame testgame;
   private UFOGameStrategy teststrategy;
   @Test
   public void testWordsLength1()
   {
      this.testconsole.LoadWordsList();
      this.testwordslist = this.testconsole.getWordsList();
      Assert.assertNotEquals(this.testwordslist.size(),0);
   }
   @Test
   public void testWordsLength2()
   {
     this.testconsole.LoadWordsList();
     this.testwordslist = this.testconsole.getWordsList();
     Assert.assertTrue(this.testwordslist.size() > 0);
   }
   @Test
   public void testCodeWordLength()
   {
     this.testgame = new UFOGame("train",this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('r');
     String guesstest2 = this.testgame.guessLetter('d');
     Assert.assertEquals(this.testgame.getCodeWordLength(),5);
   }
   @Test
   public void testgetGuessedWordsSoFar()
   {
     this.testgame = new UFOGame("train",this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('r');
     String guesstest2 = this.testgame.guessLetter('d');
     Assert.assertNotNull(this.testgame.getGuessedWordSoFar());
   }
   @Test
   public void testgetPossibleWords()
   {
     this.testgame = new UFOGame("train",this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('r');
     String guesstest2 = this.testgame.guessLetter('d');
     testwords.add("TRAIN");
     testwords.add("CLASP");
     testwords.add("ACT");
     testwords.add("BRAIN");
     testwords.add("CROWN");
     testwords.add("TRADE");
     teststrategy = new UFOGameStrategy(testwords, testgame.getCodeWord(), testgame.getCodeWordLength(),testgame);
     Assert.assertEquals(teststrategy.getpossibleWords('r').size(),4);
     
     
   }
   @Test
   public void testgetPossibleWords1()
   {
     this.testgame = new UFOGame("train",this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('r');
     String guesstest2 = this.testgame.guessLetter('d');
     testwords.add("TRAIN");
     testwords.add("CLASP");
     testwords.add("ACT");
     testwords.add("BRAIN");
     testwords.add("CROWN");
     testwords.add("TRADE");
     teststrategy = new UFOGameStrategy(testwords, testgame.getCodeWord(), testgame.getCodeWordLength(),testgame);
     Assert.assertEquals(teststrategy.getpossibleWords('r').size(),4);
     Assert.assertEquals(teststrategy.getpossibleWords('d').size(),3);
     
     
   }
 

   
   
   
}
