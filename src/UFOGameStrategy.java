import java.util.*;

/*
   Strategy for getting possible word matches.
   I start off by dividing the dictionary by the length of the codeword.
   As soon as the game starts you get a large number of possible word matches.
   When letters get guessed, the number of matches get smaller.
   I used a hash table or hashmap where the key is  patternkey, which is the pattern of the word guessed so far,
   and the value is the set of possible words with the same length as the code word.
   The key to this bonus is pairing the pattern of the guessed word so far with a set of words that matches with
   that particular pattern.
   
   
*/
import java.util.*;
public class UFOGameStrategy implements GuessingStrategy
{
   private final Map<Integer, List<String>> wordsByLength;
   private String codeword;
   private int wordLength;
   private String key;
   private Set<String> wordsWithSameLength;
   /*
      Constructor
   */
   public UFOGameStrategy(List<String> words,String codeword, int codewordLength,UFOGame game)
   {
      this.wordsByLength = divideWordsByLength(words);
      this.codeword = codeword;
      this.wordLength = codewordLength;
      this.wordsWithSameLength = getWordsWithSameLength(words,codewordLength);
      StringBuilder newKey = new StringBuilder();
      this.key = game.getGuessedWordSoFar();
   }
  
   /*
      Get a set of words with the same length as the codeword
      @param list - dictionary words list
      @param wordLength - length of the codeword
      @return - set of words with the same length as the codeword.
   */
   private static Set<String> getWordsWithSameLength(List<String> list, int wordLength)
   {
      Set<String> result = new HashSet<String>();
      
      for(String word: list)
      {
         if(word.length() == wordLength)
         {
            result.add(word);
         }
      }
      return result;
  
   }
   /*
      Note: this function is optional for finding words with the same length as the codeword.
      However, I think this function as of right now does not serve a purpose any longer.
      Make a HashTable or HashMap with the length of the words as key and its corresponding list of words as value
      @param words - words list
      @return - return a map of words associated by the word character length
   */
   private static Map<Integer,List<String>> divideWordsByLength(List<String> words)
   {
      Map<Integer, List<String>> dictionary = new HashMap<Integer, List<String>>();
      for(String word : words)
      {
         Integer length = word.length();//length or size of the word
         if(dictionary.containsKey(length))
         {
            //add to existing list of certain length or size
            dictionary.get(length).add(word);
         }
         else
         {
            //Make a new list for this length
            List<String> new_wordsList = new ArrayList<String>();
            new_wordsList.add(word);
            dictionary.put(length,new_wordsList);
            
         }
      }
      return dictionary;
   }
   @Override
   /*
      Every letter guessed narrows the number of possible words that could be the codeword.
      Get the set of possible words. 
      The size() method for the Set data structure is considered the number of possible word matches
      @param guess - letter that is guessed by the user
      @return - set of possible words
      
   */
   public Set<String> getpossibleWords(char guess)
   {
      guess = Character.toUpperCase(guess);
      Map<String,Set<String>> wordGroup = new HashMap<String, Set<String>>();//initialize wordGroup
      for(String word : wordsWithSameLength)
      {
         String key = makeKey(word,guess);
         if(!wordGroup.containsKey(key))
         {
            wordGroup.put(key,new HashSet<String>());
         }  
         wordGroup.get(key).add(word);//add word that is a possible match to the key pattern to wordGroup
      }
      wordGroup = getLargestSets(wordGroup);
      return findPrioritySet(wordGroup,guess);
   }
   /*
      Get the set of words that are most likely possible possible matches.
      Should increase the odds that the words in the set are possible matches for the code word
      @param map - wordGroup with matching pattern key
      @param guess - letter that is guessed
      
   */
   private Set<String> findPrioritySet(Map<String,Set<String>> map, char guess)
   {
      int minScore = Integer.MAX_VALUE;
      for(String tempKey : map.keySet())
      {
         int weight = 1;
         int count = 0;
         int weightedScore = 0;
         for(int i = 0; i < tempKey.length(); i++)
         {
            if(tempKey.charAt(i) == guess)
            {
               weightedScore += ++count*weight;//get the weighted score of matched patterns 
            }
            weight *= 2;
         }
         if(weightedScore < minScore)
         {
            key = tempKey;
            minScore = weightedScore;
         }
      }
      wordsWithSameLength = map.get(key);
      return wordsWithSameLength;
      
      
      
   }
   /*
      Get the the largest set of possible matched words paired with the matching key pattern
      @param subsets - word family groups 
      @return - key value structure with pattern with largest word family group
   */
   private Map<String,Set<String>> getLargestSets(Map<String,Set<String>> subsets)
   {
      int largest = 0;
      for(Set<String> s : subsets.values())
      {
         largest = s.size() > largest ? s.size() : largest; //largest is set to the size of set if size is bigger. else largest remains 0
      }
      Map<String, Set<String>> map = new HashMap<String, Set<String>>();
      for(String key : subsets.keySet())
      {
         Set<String> set = subsets.get(key);
         if(set.size() == largest)
         {
            map.put(key,set);//populate the map
         }
      }
      return map;
   }
   /*
      Make a pattern that is to be matched by a group of words
      @param word - word under examination
      @param guess - guess letter 
      @return - a new key whose pattern is to be matched by a group of words
   */
   private String makeKey(String word, char guess)
   {
      StringBuilder newKey = new StringBuilder();
      for(int i = 0; i < word.length(); i++)
      {
         char c = word.charAt(i);
         if(c == guess)
         {
            newKey.append(c);
         }
         else
         {
            newKey.append(key.charAt(i));
         }
      }
     
      return newKey.toString();
   }

   
 } 
   
   
   
