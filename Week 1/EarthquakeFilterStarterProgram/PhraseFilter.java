
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {

    private String request; //"start", "end", "any"
    private String phrase;
    private String filterName;
    
    public PhraseFilter(String name, String req, String ph) {
        request = req;
        phrase = ph;
        filterName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        if ( (request == "start") && (qe.getInfo().startsWith(phrase)) ) {
            return true;
        } else if (request == "end" && (qe.getInfo().endsWith(phrase)) ) {
            return true;
        } else if (request == "any" && (qe.getInfo().indexOf(phrase) != -1) ) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return filterName;
    }
    
}
