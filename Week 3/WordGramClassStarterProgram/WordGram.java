
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder();
        // TODO: Complete this method
        for (String word : myWords) {
            ret.append(word);
            ret.append(" ");
        }
        return ret.toString().trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (other.length() != length()) {
            return false;
        }
        
        for (int i = 0; i < length(); i++) {
            if (!other.wordAt(i).equals(wordAt(i))) {
                return false;
            }
        }
        
        return true;

    }

    public WordGram shiftAdd(String word) {
        String[] myWordsCopy = new String[myWords.length];
        
        for (int i = 0; i < length() - 1; i++) {
            myWordsCopy[i] = wordAt(i + 1);
        }
        
        myWordsCopy[length() - 1] = word;
        
        WordGram out = new WordGram(myWordsCopy, 0, length());
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        
        return out;
    }
    
    public int hashCode() {
        //create a string from the WordGram
        String wgString = toString();
        
        //use the string hashCode method
        return wgString.hashCode(); //return an integer that is a hash code that represents the WordGram
    }

}