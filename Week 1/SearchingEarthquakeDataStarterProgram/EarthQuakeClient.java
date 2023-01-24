import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if ( (qe.getDepth() > minDepth) && (qe.getDepth() < maxDepth) ) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> getQuakeData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        return list;
    }
    
    public void quakesOfDepth() {
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> answer = filterByDepth(getQuakeData(), minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth + " and " + maxDepth);
        for (QuakeEntry qe : answer) {
            System.out.println(qe.toString());
        }
        System.out.println("Found " + answer.size() + " quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if ( (where == "start") && (qe.getInfo().startsWith(phrase)) ) {
                answer.add(qe);
            } else if (where == "end" && (qe.getInfo().endsWith(phrase)) ) {
                answer.add(qe);
            } else if (where == "any" && (qe.getInfo().indexOf(phrase) != -1) ) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> answer = filterByPhrase(getQuakeData(), where, phrase);
        for (QuakeEntry qe : answer) {
            System.out.println(qe.toString());
        }
        System.out.println("Found " + answer.size() + " quakes that match " + phrase + " at " + where);
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        //EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = getQuakeData(); //parser.read(source);
        //System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> largeQuakes = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : largeQuakes) {
            System.out.println(qe.toString());
        }
        System.out.println("Found " + largeQuakes.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        //EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = getQuakeData(); //parser.read(source);
        //System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeQuakes = filterByDistanceFrom(list, 1000.0*1000, city);
        for (QuakeEntry qe : closeQuakes) {
            System.out.println(qe.getLocation().distanceTo(city)/1000 + " " + qe.getInfo());
        }
        System.out.println("Found " + closeQuakes.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        //EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = getQuakeData(); //parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
