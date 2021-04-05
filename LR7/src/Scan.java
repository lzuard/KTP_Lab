import java.net.*;
import java.util.*;

public class Scan {

    private HashMap<String, URLPair> visited;
    private LinkedList <URLPair> notVisited;
    private int maxDepth;

    public Scan(String URL, int maxDepth){
        visited = new HashMap<>();
        notVisited = new LinkedList<>();
        notVisited.add(new URLPair(URL,0));
        this.maxDepth = maxDepth;
    }

    public void run() {
        while(notVisited.size() > 0) {
            URLPair link = notVisited.pop();
            if(visited.containsKey(link.getURL())) continue;
            visited.put(link.getURL(), link);
            System.out.println(link);
            if(link.getDepth() != maxDepth)
                findLinks(link);
        }

        System.out.println();
        System.out.print("Всего ссылок: " + visited.size());
    }

    private void findLinks(URLPair link)
    {
        try {
            URL url = new URL(link.getURL());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            java.util.Scanner scanner = new java.util.Scanner(connection.getInputStream());

            while (scanner.findWithinHorizon("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1", 0) != null) {
                String newURL = scanner.match().group(2);
                createNewLink(newURL, link);
            }
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void createNewLink(String newURL, URLPair link){
        if (newURL.startsWith("/")) {
            newURL = link.getURL() + newURL;
        }
        else if (!newURL.startsWith("https")) return;

        URLPair newLink = new URLPair(newURL, link.getDepth() + 1);
        notVisited.add(newLink);
    }

    public static void main(String[] args) {
        Scan crawler = new Scan("https://mtuci.ru",2);
        crawler.run();
    }
}