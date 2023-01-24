
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
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
    
    public String getRandomText(int numWords){
        if (myText == null) {
            return "";
        }
        
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
    
    public void buildMap() {
        map.clear();
        
        for (int i = 0; i <= myText.length - myOrder; i++) {
            
            WordGram key = new WordGram(myText, i, myOrder);
            ArrayList<String> follows;
            
            if (i == myText.length - myOrder) {
                map.put(key, new ArrayList<String>());
                continue;
            }
            
            if (!map.containsKey(key)) { //if key is not yet in HashMap
                follows = new ArrayList<String>();
            } else {
                follows = map.get(key);
            }
            
            follows.add(myText[i + myOrder]);
            map.put(key, follows);
            
        }
        
    }
    
    public ArrayList<String> getFollows(WordGram key) {
        return map.get(key);
    }
    
    public void printHashMapInfo() {
        int largestSize = 0;
        ArrayList<WordGram> largeKeys = new ArrayList<WordGram>();
        //System.out.println(map);
        System.out.println("Size of HashMap: " + map.keySet().size());
        for (ArrayList<String> al : map.values()) {
            if (al.size() > largestSize) {
                largestSize = al.size();
            }
        }
        System.out.println("Size of the largest value in the HashMap is: " + largestSize);
        for (WordGram key : map.keySet()) {
            if (map.get(key).size() == largestSize) {
                largeKeys.add(key);
            }
        }
        System.out.println("Keys with maximum size value: " + largeKeys);
    }
    
    public void testIndexOf() {
        String[] test = {"this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test"};
        String[] targetWords = {"test", "yes"};
        WordGram target = new WordGram(targetWords, 0, 2);
        int start = 0;
        System.out.println("Index of " + target + " beginning from start index " + start + " is: " + indexOf(test, target, start));
    }
    
}
