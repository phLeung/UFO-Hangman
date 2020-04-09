import java.util.*;
import java.io.*;
import java.lang.*;
import java.lang.Math;


public class UFOGame
{
   /*
      An indicator for the letters in the code words that have not been guessed yet.
   */
   public static final char UNKNOWN_LETTER = '_';//indicator for unknown letter
   private final String codeword; //Word that needs to be guessed
   private final int maxWrongGuesses;//the maximum number of wrong guesses a player can make is six
   private final char[] guessedLetters; //character array of guessed letters so far. For example : th_ i_ g _ (unknown letters marked by UNKNOWN_LETTER  constant)
   private Set<Character> incorrectLetters = new HashSet<Character>();//set of incorrect letters guessed so far.
   private Set<Character> correctLetters = new HashSet<Character>();//set of correct letters guessed so far.
   
   /*
    Class Constructor for UFOGame
    @param codeword - The word that needs to be decoded or guessed
    @param maxWrongGuesses - The maximum number of incorrect letter guesses that are allowed is 6 
   */
   public UFOGame(String codeword, int maxWrongGuesses)
   {
      this.codeword = codeword.toUpperCase();//secret codeword is has all caps
      this.guessedLetters = new char[codeword.length()];//number of guessed letters is equal to the number of letters in secret code word
      for(int i = 0; i < codeword.length(); i++)
      {
         guessedLetters[i] = UNKNOWN_LETTER;//initialize all letters as unknown characters when new game begins
      }
   
      this.maxWrongGuesses = maxWrongGuesses;
   }
   
   /*
     Get the guessed word so far. the guessed word will contain UNKNOWN_LETTER constants in place of unknown letters
     @return - the string representation of the guessed word so far
   */
   public String getGuessedWordSoFar()
   {
       String words = new String(guessedLetters);
       words = words.replace("", " ").trim();//handles space formatting
       words = words.replace("_"," _ ").trim();//handles space formatting
       return words;
   }
  
   /*
      Guess the specified letter and update the game 
      @return - the string representation of guessed word so far that gets updated if the guess is correct
   */
   public String guessLetter(char ch)
   {
      ch = Character.toUpperCase(ch);
      //updates guessedLetters array with new letter or character
      boolean correct = false;
      for(int i = 0; i < codeword.length(); i++)
      {
         //if the specified letter matches with the code word
         //update the guessedLetters array
         if(codeword.charAt(i) == ch)
         {
            guessedLetters[i] = ch;
            correct = true;
         }
      }
      //update the set of guessed characters
      if(correct)
      {
         //add letters to correct letters set if guess is correct
         correctLetters.add(ch);
       
      }
      else
      {
         //add letters to incorrect letters set if guess is incorrect
         incorrectLetters.add(ch);
      }
      return getGuessedWordSoFar();
   }
   /*
      Get the max number of wrong guesses
      @return - max number of wrong guesses allowed
   */
   public int getMaxWrongGuesses()
   {
      return maxWrongGuesses;
   }
   /*
      Determine if the letter has been correctly guessed
      @param letter - user input letter
      @return - determines if the letter is correctly guessed
   */
   public boolean isletterCorrect(char letter)
   {
      boolean correct = false;
      for(int i = 0; i < codeword.length(); i++)
      {
         if(codeword.charAt(i) == letter)
         {
            correct = true;
         }
         
      }
      return correct;
        
   }
  
   /*
      Determine if the word has been correctly guessed
      @param codeword - the code word that must be decoded 
      @param guessedLetters - the correct guessed letters so far
      @return - returns true if the word is guessed correctly
   */
   public boolean isWordGuessed(String codeword)
   {
      Set<Character> letterSet = getCorrectLetters();//set of correct letters
      List<Character> list = new ArrayList<Character>();
      list.addAll(letterSet);//add set elements or correct letters to list 
      int length = codeword.length();//number of characters in codeword
      int count = 0; //counter variable
      int size = list.size();
      String result = "";
      for(int i = 0; i < length; i++)
      {
         if(!result.contains(String.valueOf(codeword.charAt(i))))
         {
            //add all the correct letters to the resulting string
            //converts code character of secret codeword as string: -> String.valueOf(char)
            result += String.valueOf(codeword.charAt(i));
         }
      }
      result = result.toUpperCase();
      for(int i = 0; i < result.length(); i++)
      {
         for(int j = 0; j < size; j++)
         {
            //if the letters in code word match up
            // with the correct letters in the list
            // increment to the count variable
            if(result.charAt(i) == list.get(j))
            {
               count++;
            }
         } 
         
      }
      if(count == result.length())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
  
   /* 
   Get the set of correctly guessed letters
   @return - set of all correctly guessed letters
   */
   public Set<Character> getCorrectLetters()
   {
      return Collections.unmodifiableSet(correctLetters);
   }
   /*
      Get the set of incorrectly guessed letters
      @return - set of all incorrectly guessed letters
   */
   public Set<Character> getIncorrectLetters()
   {
      return Collections.unmodifiableSet(incorrectLetters);
   }
   /*
    Get the length of the code word
    @return - length of the code word
   */
   public int getCodeWordLength()
   {
      return codeword.length();
   }
   /*
      Get the codeword
      @return - get the codeword
   */
   public String getCodeWord()
   {
      return codeword;
   }
 
   
   /*
      Note: this should prove useful for the bonus
      Get all the guessed letters so far. both correct and incorrect
      @return Set of all guessed letters
   */
   public Set<Character> getAllGuessedLetters()
   {
      Set<Character> guessed = new HashSet<Character>();
      guessed.addAll(correctLetters);
      guessed.addAll(incorrectLetters);
      return guessed;
      
   }
   
   
  
   
   
}