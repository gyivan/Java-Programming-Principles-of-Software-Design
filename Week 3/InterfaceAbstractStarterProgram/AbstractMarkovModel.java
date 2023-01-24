
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {
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
