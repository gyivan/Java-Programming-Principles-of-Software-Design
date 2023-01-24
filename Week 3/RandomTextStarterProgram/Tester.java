
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    
    public MarkovOne createMarkovOne() {
        MarkovOne markov = new MarkovOne();
        //m1.setTraining("this is a test yes this is a test.");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        markov.setTraining(st);
        return markov;
    }
    
    public void testGetFollows() {
        MarkovOne m1 = createMarkovOne();
        ArrayList<String> res = m1.getFollows("t.");
        for (String s : res) {
            System.out.println(s);
        }
        //System.out.println("Test done");
    }
    
    public void testGetFollowsWithFile() {
        MarkovOne m1 = createMarkovOne();
        ArrayList<String> res = m1.getFollows("he");
        System.out.println("ArrayList size: " + res.size());
    }
    
}
