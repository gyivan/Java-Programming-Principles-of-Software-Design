
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            
            //System.out.println("Key 1 is: " + key1);
            //System.out.println("Key 2 is: " + key2);
            //System.out.println("Resulting ArrayList is: " + follows);
            
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start) {
        //look at the start position
        for (int i = start; i < words.length - 1; i++) {
        
            //return the first index location in array that matches target
            if ( (words[i].equals(target1)) && (words[i + 1].equals(target2)) ) {
                return i;
            }
            
        }
        
        //if no word is found, return -1
        return -1;
    }
    
    public void testIndexOf() {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        String target1 = "this";
        String target2 = "is";
        int start = 1;
        System.out.println("Index of " + target1 + " " + target2 + " beginning from start index " + start + " is: " + indexOf(test, target1, target2, start));
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        
        //call the indexOf method
        int i = indexOf(myText, key1, key2, 0);
        
        while ( (i != -1) && (i < myText.length - 2) ) {
            //get the single word that follows the key
            String followingWord = myText[i + 2];
            follows.add(followingWord);
            
            //update the value of i
            i = indexOf(myText, key1, key2, i + 2);
        }
        
        //return an ArrayList of all the single words that immediately follow the key in the training text
        return follows;
    }

}
