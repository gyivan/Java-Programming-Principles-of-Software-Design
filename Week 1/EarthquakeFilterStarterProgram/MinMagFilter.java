
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String filterName;
    
    public MinMagFilter(String name, double min) { 
        magMin = min;
        filterName = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    }
    
    public String getName() {
        return filterName;
    }

}
