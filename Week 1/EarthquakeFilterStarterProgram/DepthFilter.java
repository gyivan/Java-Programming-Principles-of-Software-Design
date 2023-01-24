
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    
    private double minDepth;
    private double maxDepth;
    private String filterName;
    
    public DepthFilter(String name, double min, double max) {
        minDepth = min;
        maxDepth = max;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return ( (qe.getDepth() > minDepth) && (qe.getDepth() < maxDepth) ) || (qe.getDepth() == minDepth) || (qe.getDepth() == maxDepth);
    }
    
    public String getName() {
        return filterName;
    }
    
}
