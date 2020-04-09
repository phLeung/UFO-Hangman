import java.io.*;
import java.util.*;
/*
This is the main application where the game runs
*/
public class UFOGameConsole
{
   private String filename; //file containing the dictionary
   private List<String> words = new ArrayList<String>(); //list of words found in dictionary
   /*
      Constructor class
      @param filename - file containing dictionary file
   */
   public UFOGameConsole(String filename)
   {
      this.filename = filename;
   }
   /*
      Get all the words from the dictionary file (nouns.txt)
      and store them in words list
   */
   public void LoadWordsList()
   {
      try
      {
         this.words = FileScanner.loadWords(this.filename);
      }
      catch(FileNotFoundException e)
      {
         e.printStackTrace();
      }
   }
   /*
      Get list of words
      @return - list of all the words from the dictionary file
   */
   public List<String> getWordsList()
   {
      assert(words.size() > 0);//makes sure words list is not zero
      return words;
   }
   /*
    Get a word randomly from the wordsList
    @param wordsList - list that has all the words from the dictionary file
    @return - get the randomly selected word
   */
   public String getWord(List<String> wordsList)
   {
      Random rand = new Random();
      String word = wordsList.get(rand.nextInt(wordsList.size()));
      return word;
   }
  
   public static void main(String[] args)
   {
       String dictionaryFile = "nouns.txt";
       UFOGameConsole console = new UFOGameConsole(dictionaryFile);
       console.LoadWordsList();//gets all the words from the dictionary file into the words list
       List<String> wordsList = console.getWordsList();//gets the word list 
       String codeword = "";//a word is randomly selected from the dictionary
       int max;//you only get 6 wrong guesses and you lose
       List<String> frames = UfoPrinter.ufoFrames();//list of pictures
       String picture = "";
       int wrongCount;//counts the number of wrong guesses
       boolean isPlaying = true;//determines if the user is playing or not
       String incorrectLetters = "None " + " ";
       String wordSoFar = "";
       Scanner input = new Scanner(System.in);
       String guess = "";
       Scanner scan = new Scanner(System.in);
       int possibleMatches = 0; //the number of possible matches for the word guessed so far based on length and letters guessed so far
       String binaryChoice = "";
       Loop://this is a label for first loop
       while(isPlaying)
       {
          
          
          frames = UfoPrinter.ufoFrames();//list of pictures
          wrongCount = 0;
          max = 6;//you only get 6 wrong guesses and you lose
          codeword =  console.getWord(wordsList);
          UFOGame game = new UFOGame(codeword,max);
          wordSoFar = game.getGuessedWordSoFar();
          UFOGameStrategy strategy = new UFOGameStrategy(wordsList,game.getCodeWord(),game.getCodeWordLength(),game);
          possibleMatches = 0;
          incorrectLetters = "None";//initialize this variable with the string "None"
          isPlaying = true;//determines if the user is playing or not
          System.out.println("UFO: The Game");
          System.out.println("Instructions: save us from alien abduction by guessing letters in the codeword.");
          picture = frames.get(wrongCount);//get the starting picture
          System.out.println(picture);
          System.out.println("Incorrect Guesses: ");
          System.out.println(incorrectLetters);
          System.out.println();
          System.out.println("Codeword: ");
          System.out.println(wordSoFar);//this is the code word so far
          System.out.println();
          System.out.print("Please Enter your guess: ");
          guess = input.nextLine().toUpperCase();
          //System.out.println(codeword);//testing purposes
          LOOP1:
          while(wrongCount != game.getMaxWrongGuesses())//the user can keep playing as long as the user has not made 6 incorrect guesses
          {
            //checks if the letter input is valid and the letter is correct
            if(isValid(guess) && game.isletterCorrect(guess.charAt(0)))
            {
             
               System.out.println("Correct! You're closer to cracking the codeword.");
               wordSoFar = game.guessLetter(guess.charAt(0));
               wrongCount = wrongCount;//don't change the wrong count
               picture = frames.get(wrongCount);
               System.out.println(picture);//display picture
               System.out.println("Incorrect Guesses: ");
               System.out.println(incorrectLetters);
               System.out.println("Codeword: ");
               System.out.println(wordSoFar);
               possibleMatches = strategy.getpossibleWords(guess.charAt(0)).size();
               System.out.println("Number of possible dictionary matches: " + possibleMatches);
               System.out.println();
               if(game.isWordGuessed(codeword))//check if the user guessed all the words correctly
               {
                  System.out.println("Correct! You saved the person and earned a medal of honor!");
                  System.out.println("The codeword is: " + codeword + ".");
                  System.out.print("Would you like to play again (Y/N)? ");
                  binaryChoice = scan.next().toUpperCase();
                  while(isValidInput(binaryChoice) || wrongCount != game.getMaxWrongGuesses())//makes sure the user enters y or n
                  {
                     if(binaryChoice.equals("Y"))
                     {
                        break LOOP1;//restart the game if user enters Y or y
                     }
                     else if(binaryChoice.equals("N"))
                     {
                        isPlaying = false;//user does not want to play anymore
                        System.out.println("Goodbye!");
                        break Loop; //exit the game
                        
                     }
                     else
                     {
                          //cannot get out of this while loop unless the user enters a valid input
                          System.out.print("I cannot understand. Would you like to play again (Y/N)? ");
                          binaryChoice = scan.next().toUpperCase();
                          
                     }
                  }
               }
               System.out.print("Please enter your guess: ");
               guess = input.nextLine().toUpperCase();
               
              
            }
               
          
            //checks if the letter input is valid and the letter is incorrect
            else if(isValid(guess) && !game.isletterCorrect(guess.charAt(0)))
            {
               
               System.out.println("Incorrect! The tractor beam pulls the person in further.");    
               wordSoFar = game.guessLetter(guess.charAt(0));
               wrongCount++;//increment or update the wrong count
               picture = frames.get(wrongCount);//get a new picture
               System.out.println(picture);
               //update incorrectLetters variable
               if(incorrectLetters.startsWith("None"))//if this variable starts with None, change it
               {
                  incorrectLetters = "";
               } 
               //if the incorrect letter is in the incorrect letter set, then 
               if(game.getIncorrectLetters().contains(guess.charAt(0)))
               {
                  incorrectLetters += guess.toUpperCase() + " ";
               }
               System.out.println("Incorrect Guesses: ");
               System.out.println(incorrectLetters);
               System.out.println("Codeword: ");
               System.out.println(wordSoFar);
               possibleMatches = strategy.getpossibleWords(guess.charAt(0)).size();
               System.out.println("Number of possible dictionary matches: " + possibleMatches);
               System.out.println();
               //check if user has made six incorrect guesses
               if(wrongCount == game.getMaxWrongGuesses())
               {
                  System.out.println("Incorrect! The person has been abducted! Never to be seen again!");
                  System.out.println("The codeword is: " + game.getCodeWord() + ".");
                  System.out.print("Would you like to play again (Y/N)? ");
                  binaryChoice = scan.next().toUpperCase();
                  while(isValidInput(binaryChoice) || wrongCount == game.getMaxWrongGuesses())//makes sure the user types in y or n
                  {
                      /*
                        This restarts the game by breaking out of the inner while loop 
                        and going to the outer while loop.
                      */
                      if(binaryChoice.equals("Y"))
                      {
                           break LOOP1; //restart the game
                      }
                      else if(binaryChoice.equals("N"))
                      {
                         isPlaying = false;//user does not want to play anymore
                         System.out.println("Goodbye!");
                         break Loop;//exit the game
                      }
                      else
                      {
                          //cannot get out of this while loop unless the input is valid
                          System.out.print("I cannot understand. Would you like to play again (Y/N)? ");
                          binaryChoice = scan.next().toUpperCase();
                      }
                  }
               }
                         
               System.out.print("Please enter your guess: ");
               guess = input.nextLine().toUpperCase();
              
            }
             //checks if the letter has already been guessed
             if(game.getAllGuessedLetters().contains(guess.charAt(0)))
             {
                 
                while(game.getAllGuessedLetters().contains(guess.charAt(0)))
                {
                   System.out.println("You can only guess that letter once, please try again.");
                   System.out.print("Please enter your guess: ");
                   guess = input.nextLine().toUpperCase();
                   if(!game.getAllGuessedLetters().contains(guess.charAt(0)))
                   {
                      break;
                   }
                }
                  
              }
               //check if the letter is available
               if(!isValid(guess))
               {
                  while(!isValid(guess))
                  {
                     System.out.println("I cannot understand your input. Please guess a single letter.");
                     System.out.print("Please enter your guess: ");
                     guess = input.nextLine().toUpperCase();
                  
                     if(isValid(guess))
                     {
                        break;
                     }
                  }
               }
                
            
           }
                         
       }                 
    }
    
   
   /*
    Checks if user input is valid for restarting the game when finished
    @param s - input string
    @return - determines validity of user input to be yes or no
   */
   public static boolean isValidInput(String s)
   {
       if(s.length() != 1)
       {
          return false;
       }
       char c = Character.toUpperCase(s.charAt(0));
       return c == 'Y' || c == 'N';
    }
    /*Checks if user input is valid while the user is playing the game.
      Makes sure that the user is entering a letter while playing the game
      @param s - input string
      @return - determines validity of user input while the game is being played
    */
    public static boolean isValid(String s)
    {
      if(s.length() != 1)
      {
         return false;
      }
      char c = Character.toUpperCase(s.charAt(0));
      return c >= 'A' && c <= 'Z';
    }
       
         


         
         
       
       
       
       
       
       
   
}