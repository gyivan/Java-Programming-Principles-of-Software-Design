
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            
            //System.out.println("Key is: " + key);
            //System.out.println("Resulting ArrayList is: " + follows);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target, int start) {
        //look at the start position
        for (int i = start; i < words.length; i++) {
        
            //return the first index location in array that matches target
            if (words[i].equals(target)) {
                return i;
            }
            
        }
        
        //if no word is found, return -1
        return -1;
    }
    
    public void testIndexOf() {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        String target = "test";
        int start = 5;
        System.out.println("Index of " + target + " beginning from start index " + start + " is: " + indexOf(test, target, start));
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        //call the indexOf method
        int i = indexOf(myText, key, 0);
        
        while ( (i != -1) && (i < myText.length - 1) ) {
            //get the single word that follows the key
            String followingWord = myText[i + 1];
            follows.add(followingWord);
            
            //update the value of i
            i = indexOf(myText, key, i + 1);
        }
        
        //return an ArrayList of all the single words that immediately follow the key in the training text
        return follows;
    }

}
