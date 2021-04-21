import java.text.SimpleDateFormat;
import java.util.*;

public class Tasks5 {

    //1
    public static boolean sameLetterPattern(String a, String b) {

        if (a.length() == b.length()) {
            HashMap<Character, Character> match = new HashMap<>();

            for (int i = 0; i < a.length(); i++) {
                if (!match.containsKey(a.charAt(i))) {
                    match.put(a.charAt(i), b.charAt(i));
                }
                if (b.charAt(i) != match.get(a.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //2
    public static String spiderVsFly(String spider, String fly) {
        int sx = spider.charAt(0) - 65;
        int sy = spider.charAt(1) - 48;
        int fx = fly.charAt(0) - 65;
        int fy = fly.charAt(1) - 48;

        double strategyDist1 = sy + fy;
        double strategyDist2 = Math.abs(sy - fy) + ((sx + fx) % 8) * fy * 0.76536686473;

        String path = "";

        if (strategyDist1 <= strategyDist2) {
            for (int i = 0; i < sy; i++) {
                path += spider.charAt(0);
                path += sy - i;
                path += '-';
            }
            path += "A0-";
            for (int i = 0; i < fy; i++) {
                path += fly.charAt(0);
                path += i + 1;
                path += '-';
            }
        } else {
            for (int i = 0; i < Math.abs(sy - fy); i++) {
                path += spider.charAt(0);
                if (sy > fy) path += sy - i;
                else path += sy + i;
                path += '-';
            }
            for (int i = 0; i <= (sx + fx) % 8; i++) {
                path += (char)(65 + (sx + i) % 8);
                path += fly.charAt(1);
                path += '-';
            }
        }

        return path.substring(0, path.length() - 1);
    }

    //3
    private static int _digitsCount(long number) {
        if (number == 0) return 0;
        return 1 + _digitsCount(number / 10);
    }

    public static int digitsCount(long number) {
        return 1 + _digitsCount(number / 10);
    }

    //4
    private static String deleteLetter(String text,int index){
        if(text.length()>0) {

            if (index > 0 && index < text.length() - 1) {
                return text.substring(0, index) + text.substring(index + 1);
            } else if (index == 0) {
                return text.substring(1);
            } else if(index==text.length()-1){
                return text.substring(0,index);
            }
        }
        return null;
    }
    public static int totalPoints(String[] words,String lastWord){
        int score=0;
        boolean invalid=false;
        String tempWord;

        for(String oneWord:words){
            tempWord=lastWord;
            for(int i=0;i<oneWord.length();i++){
                if(!tempWord.contains(""+oneWord.charAt(i))){
                    invalid=true;
                    break;
                }
                else{
                    tempWord=deleteLetter(tempWord,tempWord.indexOf(oneWord.charAt(i)));
                }
            }

            if(!invalid && oneWord.length()>=3){
                score+=oneWord.length()==6? 54:oneWord.length()-2;
            }
            invalid=false;
        }
        return score;
    }

    //5
    public static int longestRun(int[] arr) {
        int max = 1;
        int cur = 1;
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i+1] - arr[i] == 1 || arr[i+1] - arr[i] == -1) {
                cur++;
                if (max < cur) max = cur;
            } else cur = 1;
        return max;
    }

    //6
    public static String takeDownAverage(String[] percents) {
        int avg = 0;
        for (String s : percents)
            avg += Integer.parseInt(s.substring(0, s.length() - 1));
        return (avg / percents.length - percents.length * 5 - 5) + "%";
    }

    //7
    public static String rearrange(String str) {
        String[] words = str.split(" ");
        String[] answer = new String[words.length];

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) {
                    answer[Integer.parseInt(""+word.charAt(i))  - 1] = word.substring(0, i) + word.substring(i+1);
                    break;
                }
            }
        }
        return String.join(" ", answer);
    }



    //8
    public static int maxPossible(int first, int second){
        String answer="";
        int index=-1;

        String firstStr=String.valueOf(first);
        String secondStr=String.valueOf(second);

        int[] firstArr=new int[firstStr.length()];
        int[] secondArr=new int[secondStr.length()];

        for (int i=0;i<firstStr.length();i++){
            firstArr[i]=Integer.parseInt(""+firstStr.charAt(i));
        }
        for (int i=0;i<secondStr.length();i++){
            secondArr[i]=Integer.parseInt(""+secondStr.charAt(i));
        }

        for (int i=0;i<firstArr.length;i++){
            for(int j=0;j<secondArr.length;j++){
                if(firstArr[i]<secondArr[j]){
                    firstArr[i]=secondArr[j];
                    index=j;
                }
            }
            if(index>-1) {
                secondArr[index] = 0;
            }
            answer+=firstArr[i];
            index=-1;
        }

        return Integer.parseInt(answer);

    }


    //9
    private static String getGMT(String city) {
        return switch (city) {
            case "Los Angeles" -> "GMT-08:00";
            case "New York" -> "GMT-05:00";
            case "Caracas" -> "GMT- 04:30";
            case "Buenos Aires" -> "GMT-03:00";
            case "London" -> "GMT00:00";
            case "Rome" -> "GMT+01:00";
            case "Moscow" -> "GMT+03:00";
            case "Tehran" -> "GMT+03:30";
            case "New Delhi" -> "GMT+05:30";
            case "Beijing" -> "GMT+08:00";
            case "Canberra" -> "GMT+10:00";
            default -> "GMT";
        };
    }
    public static String timeDifference(String cityA, String timestamp, String cityB) {
        SimpleDateFormat parseDate = new SimpleDateFormat("MMMM d, yyyy HH:mm");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-M-d HH:mm");
        try {
            parseDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityA)));
            formatDate.setTimeZone(TimeZone.getTimeZone(getGMT(cityB)));
            Date date = parseDate.parse(timestamp);
            return formatDate.format(date);
        } catch(Exception e) {}

        return null;
    }

    //10
    public static boolean isNew(int number) {

        String str=String.valueOf(number);
        int[] oldNum=new int[str.length()];
        int[] newNum=new int[str.length()];

        for (int i=0;i<str.length();i++){
            oldNum[i]=Integer.parseInt(""+str.charAt(i));
            newNum[i]= Integer.parseInt(""+str.charAt(i));
        }

        Arrays.sort(newNum);

        if (newNum[0] > 0) {
            for (int i = 0; i < oldNum.length; i++) {
                if (oldNum[i] != newNum[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
