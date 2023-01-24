
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
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
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        
        for (int k = 0; k < numChars - 1; k++) {
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
        
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        int index = myText.indexOf(key, pos);
        
        while (index != -1) {
            if (index + key.length() + 1 <= myText.length()) {
                follows.add(myText.substring(index + key.length(), index + key.length() + 1));
                //System.out.println("Added: " + myText.substring(index + key.length(), index + key.length() + 1));
            }
            
            pos = index + 1;
            index = myText.indexOf(key, pos);
        }
        //System.out.println("Size of ArrayList: " + follows.size());
        return follows;
    }
    
}
