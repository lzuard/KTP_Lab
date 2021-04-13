/** Представляет адресса в виде пар глубина-адрес **/
public class URLDepthPair {
    /** Адрес */
    private String URL;
    /** Глубина **/
    private int depth;

    /** Геттеры **/
    public int getDepth()  {
        return depth;
    }
    public String getURL() {
        return URL;
    }

    /** Констркутор **/
    public URLDepthPair(String URL, int depth){
        this.URL = URL;
        this.depth = depth;
    }

    /** Возвращает строковое представление пары **/
    @Override
    public String toString() {
        return "depth: " + depth + " URL: ["+ URL + "]";
    }
}