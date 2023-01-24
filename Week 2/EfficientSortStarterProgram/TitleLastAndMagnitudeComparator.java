
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        //compare the last word in the title of q1 and q2
        String[] q1Title = q1.getInfo().split(" ");
        String[] q2Title = q2.getInfo().split(" ");
        
        String q1Last = q1Title[q1Title.length - 1];
        String q2Last = q2Title[q2Title.length - 1];
        
        if (q1Last != q2Last) {
            return q1Last.compareTo(q2Last);
        } else { //compare magnitude
            MagnitudeComparator mc = new MagnitudeComparator();
            return mc.compare(q1, q2);
        }
    }
    
}
