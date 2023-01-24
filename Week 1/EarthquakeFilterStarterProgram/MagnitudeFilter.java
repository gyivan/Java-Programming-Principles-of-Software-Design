
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    
    private double minMag;
    private double maxMag;
    private String filterName;
    
    public MagnitudeFilter(String name, double min, double max) {
        filterName = name;
        minMag = min;
        maxMag = max;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return ( (qe.getMagnitude() > minMag) && (qe.getMagnitude() < maxMag) ) || (qe.getMagnitude() == minMag) || (qe.getMagnitude() == maxMag);
    }
    
    public String getName() {
        return filterName;
    }
    
}
