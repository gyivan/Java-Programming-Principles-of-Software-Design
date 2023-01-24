
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    
    private int N;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int num) {
        N = num;
        myRandom = new Random();
        map = new HashMap<String, ArrayList<String>>();
    }
    
    public int getOrder() {
        return N;
    }
    
    public String toString() {
        return "EfficientMarkovModel of order " + getOrder();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        /*
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }
        */
        int index = myRandom.nextInt(myText.length() - N);
        String key = myText.substring(index, index + N);
        sb.append(key);
        
        for (int k = 0; k < numChars - N; k++) {
            ArrayList<String> follows = getFollows(key);
            
            if (follows.size() == 0) {
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public void buildMap() {

        for (int i = 0; i <= myText.length() - N; i++) {
            
            String key = myText.substring(i, i + N); //find each new key
            ArrayList<String> follows;
            
            if (i == myText.length() - N) { //if reached the end of the text
                map.put(key, new ArrayList<String>());
                continue;
            }
            
            if (!map.containsKey(key)) {//if key is not yet in HashMap
                //map.put(key, new ArrayList<String>());
                follows = new ArrayList<String>();
            } else {
                follows = map.get(key);
            }
            
            follows.add(myText.substring(i + key.length(), i + key.length() + 1));
            map.put(key, follows);
            
        }
        
    }
    
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public void printHashMapInfo() {
        int largestSize = 0;
        ArrayList<String> largeKeys = new ArrayList<String>();
        //System.out.println(map);
        System.out.println("Size of HashMap: " + map.keySet().size());
        for (ArrayList<String> al : map.values()) {
            if (al.size() > largestSize) {
                largestSize = al.size();
            }
        }
        System.out.println("Size of the largest value in the HashMap is: " + largestSize);
        for (String key : map.keySet()) {
            if (map.get(key).size() == largestSize) {
                largeKeys.add(key);
            }
        }
        System.out.println("Keys with maximum size value: " + largeKeys);
    }
    
}
