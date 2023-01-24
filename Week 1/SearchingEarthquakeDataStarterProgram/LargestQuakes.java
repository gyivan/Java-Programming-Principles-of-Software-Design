
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    
    public ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        return list;
    }
    
    public void findLargestQuakes() {
        ArrayList<QuakeEntry> quakeData = getQuakeData();
        int numQuakes = 50;
        ArrayList<QuakeEntry> largestQuakes = getLargest(quakeData, numQuakes);
        for (QuakeEntry qe : largestQuakes) {
            System.out.println(qe.toString());
        }
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int res = 0;
        double largestMag = 0.0;
        
        for (QuakeEntry qe : data) {
            if (qe.getMagnitude() > largestMag) {
                largestMag = qe.getMagnitude();
                res = data.indexOf(qe);
            }
        }
        //System.out.println("Index of Largest quake is: " + res);
        //System.out.println("Quake has magnitude of: " + largestMag);
        return res; //returns an integer represending the index location in data of the quake with the largest magnitude
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> ans = new ArrayList<QuakeEntry>();
        
        for (int i = 0; i < howMany; i++) {
            int largestIdx = indexOfLargest(quakeData);
            ans.add(quakeData.get(largestIdx));
            quakeData.remove(largestIdx);
        }
        
        return ans;
    }

}
