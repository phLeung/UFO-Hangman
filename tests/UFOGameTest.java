import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;


public class UFOGameTest {
   private UFOGame testgame;
   private final int maxwrong = 6;
   private String testword;
   
   @Test
   public void testGetCodeWord()
   {
     this.testword = "train".toUpperCase();
     this.testgame = new UFOGame(this.testword,this.maxwrong);
     Assert.assertEquals(this.testgame.getCodeWord(),this.testword);
   }
   @Test
   public void testCodeWordLength()
   {
      this.testword = "trade";
      this.testgame = new UFOGame(this.testword,this.maxwrong);
      Assert.assertEquals(this.testgame.getCodeWordLength(),this.testword.length());
   }
   @Test
   public void testWrongGuesses()
   {
     this.testword = "crown";
     this.testgame = new UFOGame(this.testword,this.maxwrong);
     Assert.assertEquals(this.testgame.getMaxWrongGuesses(), this.maxwrong);
   }
   @Test
   public void testWordGuessed1()
   {
      this.testword = "brain";
      this.testgame = new UFOGame(this.testword,this.maxwrong);
      Assert.assertFalse(this.testgame.isWordGuessed(this.testword));//no letters have been guessed
   }
   @Test
   public void testWordGuessed2()
   {
     this.testword = "brain";
     this.testgame = new UFOGame(this.testword,this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('b');
     String guesstest2 = this.testgame.guessLetter('r');
     String guesstest3 = this.testgame.guessLetter('a');
     String guesstest4 = this.testgame.guessLetter('i');
     String guesstest5 = this.testgame.guessLetter('n');
     Assert.assertTrue(this.testgame.isWordGuessed(this.testword));//correct words have been guessed
     
   }
   @Test
   public void testWordGuessed3()
   {
     this.testword = "train";
     this.testgame = new UFOGame(this.testword,this.maxwrong);
     String guesstest1 = this.testgame.guessLetter('q');
     String guesstest2 = this.testgame.guessLetter('b');
     String guesstest3 = this.testgame.guessLetter('m');
     String guesstest4 = this.testgame.guessLetter('p');
     String guesstest5 = this.testgame.guessLetter('w');
     String guesstest6 = this.testgame.guessLetter('o');
     Assert.assertFalse(this.testgame.isWordGuessed(this.testword));
   }

}
