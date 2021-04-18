public class Tasks1 {

    public static int convert(int minutes){
        return minutes*60;
    }

    public static int points(int x2, int x3){
        return x2*2+x3*3;
    }

    public static int footballPoints(int wins, int draws, int loses){
        return wins*4+draws*1+loses*0;
    }

    public static boolean divisibleByFive(int num){
        return num%5==0;
    }

    public static boolean and (boolean first, boolean second){
        return first && second;
    }

    public static int howManyWalls (int n, int w, int h){
        return n/(w*h);
    }

    public static int squaed(int a) {
        return a * a;
    }

    public static boolean profitAbleGame(double prob, double prize, double pay){
        return prob*prize>pay;
    }

    public static int frames(int min, int fps){
        return fps*60*min;
    }

    public static int mod(int a, int b){
        return a-(a/b)*b;
    }
}

