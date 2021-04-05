
public class URLPair {
    private String URL;
    private int depth;

    public int getDepth()  {
        return depth;
    }

    public String getURL() {
        return URL;
    }

    public URLPair(String URL, int depth){
        this.URL = URL;
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "depth: " + depth + " URL: ["+ URL + "]";
    }
}