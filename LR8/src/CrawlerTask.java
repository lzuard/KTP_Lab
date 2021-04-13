import java.net.*;
import java.util.Scanner;


public class CrawlerTask extends Thread {
    /** Ссылка на пул адресов **/
    private URLPool pool;

    /** Конструктор **/
    public CrawlerTask(URLDepthPair link) {
        //this.pool =pool;
        this.pool = new URLPool();
        pool.addLink(link);
    }

    /** Запуск потока **/
    @Override
    public void run() {
        URLDepthPair link = pool.getLink();
        System.out.println(link.toString());  ////TODO
        System.out.println(Thread.activeCount());  /////
        Crawler.CountURLs++;
        if (link.getDepth() == Crawler.getMaxDepth()) return;

        findLinks(link);
    }

    /** Поиск новых ссылок **/
    private void findLinks(URLDepthPair link) {
        try {
            URL url = new URL(link.getURL());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());

            while (scanner.findWithinHorizon("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1", 0) != null) {
                String newURL = scanner.match().group(2);
                URLDepthPair newLink = createNewLink(newURL, link);
                if (newLink == null) continue;
                //Если ссылка рабочая, запсукаем новый поток с анализом новой ссылки
                CreateNewThread(newLink);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /** Строит новую ссылку **/
    private URLDepthPair createNewLink(String newURL, URLDepthPair link) {
        if (newURL.startsWith("/")) {
            newURL = link.getURL() + newURL;
        } else if (!newURL.startsWith("https")) return null;

        return new URLDepthPair(newURL, link.getDepth() + 1);
    }

    /** Создает и запускает новый поток с анализом ссылки **/
    private void CreateNewThread(URLDepthPair link) {

        CrawlerTask task = new CrawlerTask(link);
        task.start();
    }
}