/*
   Guessing strategy for getting possible words
*/

import java.util.*;
public interface GuessingStrategy
{
   /*
      generate possible words based on letter guess
      @param guess - the letter being guessed
      @return - set of possible words that satisfy all the guesses made so far
   */
   public Set<String> getpossibleWords(char guess);
}