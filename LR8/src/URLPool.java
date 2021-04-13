import java.util.*;

/** Хранит список всех ссылок **/
public class URLPool {
    /** Список посещенных ссылок **/
    private HashMap<String, URLDepthPair> visited;
    /** Связанный список всех ссылок **/
    //TODO: пул был в потоке, обосрался с доступом мб синхронизировать тут
    private LinkedList<URLDepthPair> pool;

    /** Констрктор **/
    public URLPool(){
        visited = new HashMap<>();
        pool = new LinkedList<>();
    }


    /** Возвращает первую доступную для анализа ссылку **/
    public synchronized URLDepthPair getLink(){
        boolean isWaiting = false;

        // Если ссылок нет, поток ждет
        if(pool.size() == 0) {
            try {
                Crawler.WaitingThreads++;
                isWaiting = true;
                if(Crawler.WaitingThreads == Thread.activeCount()) {
                    System.err.println("Все потоки заняты");
                    System.exit(0);
                };
                this.wait();
            }
            catch (Exception e) { return null; }
        }

        //Если поток ждал, нужно уменьшить счетчик
        if(isWaiting) Crawler.WaitingThreads--;

        //Удаляем полученную ссылку
        URLDepthPair link = pool.pop();
        //Добавляем ссылку в набор посещенных
        visited.put(link.getURL(),link);
        return link;
    }

    /** Добавляет ссылку в пул и уведомляет потоки **/
    public synchronized void addLink(URLDepthPair link){
        if(!visited.containsKey(link.getURL())) {
            pool.add(link);
            this.notify();
        }
    }
}