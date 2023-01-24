
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        return list;
    }
    
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        int minIndex = 0;
        for (int j = 0; j < howMany; j++) {
            for (int i = 1; i < quakeData.size(); i++) {
                QuakeEntry quake = quakeData.get(i);
                Location loc = quake.getLocation();
                float currEntryDist = loc.distanceTo(current);
                
                QuakeEntry currMinEntry = quakeData.get(minIndex);
                Location currMinLoc = currMinEntry.getLocation();
                float currMinDist = currMinLoc.distanceTo(current);
                
                if (currEntryDist < currMinDist) { //if current quake is closer than the closest quake so far
                    minIndex = i; //upadate the new index of the closest quake
                }
                
            }
            
            ret.add(quakeData.get(minIndex));
            quakeData.remove(minIndex);
            
        }

        return ret;
    }

    public void findClosestQuakes() {
        //EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //ArrayList<QuakeEntry> list  = parser.read(source);
        //System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> list  = getQuakeData();
        
        Location jakarta  = new Location(-6.211,106.845);

        //ArrayList<QuakeEntry> close = getClosest(list,jakarta,10);
        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
