
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int pos) {
        int ans = pos;
        QuakeEntry temp = quakeData.get(ans);
        double tempDepth = temp.getDepth();
        for (int i = pos + 1; i < quakeData.size(); i++) {
            QuakeEntry qe = quakeData.get(i);
            if (qe.getDepth() < tempDepth) {
                tempDepth = qe.getDepth();
                ans = i;
            }
        }
        
        return ans; //returns an int representing the index position of the QE with the largest depth (considering those only from pos to the end of the ArrayList)
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        //sorts the QuakeEntry's in the ArrayList by depth using the selection sort algorithm, but in reverse order from largest depth to smallest depth
        //for (int i=0; i< in.size(); i++) {
        for (int i = 0; i < 70; i++) {
            int minIdx = getLargestDepth(in, i); //get index of QE with largest depth
            QuakeEntry qi = in.get(i); //get current QE
            QuakeEntry qmin = in.get(minIdx); //get min QE
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
        
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
        
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        //numSorted represents the number of times this method has already been called on this ArrayList
        //also represents nubmer of elements guaranteed to already be where they belong when the ArrayList is sorted by magnitude
        for (int i = 0; i < quakeData.size() - numSorted - 1; i++) {
            if (quakeData.get(i + 1).getMagnitude() < quakeData.get(i).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i + 1);
                quakeData.set(i + 1, quakeData.get(i));
                quakeData.set(i, temp);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        int n = in.size();
        
        for (QuakeEntry qe : in) {
            System.out.println(qe);
        }
        
        for (int i = 0; i < n - 1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass " + i);
            for (QuakeEntry qe : in) {
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            if (quakes.get(i + 1).getMagnitude() < quakes.get(i).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int n = in.size();
        
        int numPasses = 0;
        //for (QuakeEntry qe : in) {
        //    System.out.println(qe);
        //}
        
        for (int i = 0; i < n - 1; i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            onePassBubbleSort(in, i);
            numPasses++;
            //System.out.println("Printing Quakes after pass " + i);
            //for (QuakeEntry qe : in) {
            //    System.out.println(qe);
            //}
        }
        System.out.println("Number of passes needed: " + numPasses);
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int numPasses = 0;
        
        for (int i=0; i< in.size(); i++) {
            if (checkInSortedOrder(in)) {
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            numPasses++;
        }
        
        System.out.println("Number of passes needed: " + numPasses);
    }
    
}
