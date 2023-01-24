
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class MarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String toString() {
        return "MarkovWord model of order " + myOrder;
    }
    
    public int indexOf(String[] words, WordGram target, int start) {
        //return the first position from start that has words in the array words that match the WordGram target
        
        for (int i = start; i < words.length - myOrder; i++) {
            //create current WordGram
            WordGram current = new WordGram(words, i, myOrder);
            
            if (current.equals(target)) {
                return i;
            }
            
        }
        
        //if there is no such match then return -1
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int startPos = 0;
        
        while (true) {
            int index = indexOf(myText, kGram, startPos);
            
            if (index == -1 || index == myText.length - myOrder) {
                break;
            }
            
            follows.add(myText[index + myOrder]);
            startPos = index + 1;
        }
        
        return follows;
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        //String key = myText[index];
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
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
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    public void testIndexOf() {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        String[] targetWords = {"test", "yes"};
        WordGram target = new WordGram(targetWords, 0, 2);
        int start = 0;
        System.out.println("Index of " + target + " beginning from start index " + start + " is: " + indexOf(test, target, start));
    }
    
}
