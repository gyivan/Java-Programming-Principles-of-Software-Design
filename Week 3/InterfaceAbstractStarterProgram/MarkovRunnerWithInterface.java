
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;
import java.util.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 792;
        
        EfficientMarkovModel emm = new EfficientMarkovModel(6);
        runModel(emm, st, size, seed);
        
        /*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);
        */
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000; //set length of text to generate to be 1000
        int seed = 42;
        
        //make both order-2 Markov models
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        MarkovModel m2 = new MarkovModel(2);

        //both should call runModel that generates random text three times for each
        
        System.out.println("Time started for normal model: " + System.nanoTime());
        runModel(m2, st, size, seed);
        System.out.println("Time ended for normal model: " + System.nanoTime());

        System.out.println("Time started for efficient model: " + System.nanoTime());
        runModel(emm, st, size, seed);
        System.out.println("Time ended for efficient model: " + System.nanoTime());
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource("data/confucius.txt");
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        emm.setRandom(531);
        emm.setTraining(st);
        //emm.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
        emm.buildMap();
        //String res = emm.getRandomText(50);
        emm.printHashMapInfo();
    }
    
    
}
