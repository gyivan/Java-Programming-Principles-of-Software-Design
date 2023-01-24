
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        //compare the title of q1 and q2
        if (q1.getInfo() != q2.getInfo()) {
            return q1.getInfo().compareTo(q2.getInfo());
        } else { //compare depth
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
    }

}
