public class Crawler {

    /** Хранит ссылку **/
    private String URL;
    /** Максимальная глубина поиска **/
    private static int maxDepth;
    /** Счетчик всех потоков **/
    public static int CountThreads;

    private URLPool pool;

    /** Ожидающие потоки **/
    public static int WaitingThreads = 0;
    /** Счетчик ссылок **/
    public static int CountURLs = 0;


    /** Геттер **/
    public static int getMaxDepth() { return maxDepth; }

    /** Конструктор **/
    public Crawler(String URL, int maxDepth, int countThreads){
        this.URL = URL;
        pool= new URLPool();
        Crawler.maxDepth = maxDepth;
        Crawler.CountThreads = countThreads;
    }

    /** Запуск программы **/
    public void run() {
        CrawlerTask task = new CrawlerTask(new URLDepthPair(URL,0));
        task.start();
    }

    /** Вывод результатов **/
    private static void printResult(){
        System.out.println();
        System.out.println("Всего ссылок: " + CountURLs);
    }

    /** Точка входа **/
    public static void main(String[] args){
        Crawler crawler = new Crawler("https://yandex.ru/",2 ,10);
        crawler.run();

        Runtime.getRuntime().addShutdownHook(new Thread(()->printResult()));
    }
}