
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    
    private Location location;
    private float maxDist;
    private String filterName;
    
    public DistanceFilter(String name, Location loc, float maxDistance) {
        location = loc;
        maxDist = maxDistance;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDist;
    }
    
    public String getName() {
        return filterName;
    }
    
}
