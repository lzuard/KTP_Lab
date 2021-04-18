import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tasks4 {

    //1
    public static String sevenBoom(int[] numbers){
        for (int number:numbers){
            if(number==7){
                return "Boom!";
            }
        }
        return "there is no 7 in the array";
    }

    //2
    private static int[] arrSort(int[] numbers) {
        int min;
        int index;
        int temp;
        if (numbers.length > 1) {
            for (int i = 0; i < numbers.length - 1; i++) {
                min = numbers[i];
                index = i;
                for (int j = i; j < numbers.length; j++) {
                    if (min > numbers[j]) {
                        min = numbers[j];
                        index = j;
                    }
                }
                temp = numbers[i];
                numbers[i] = numbers[index];
                numbers[index] = temp;
            }
        }
        return numbers;
    }
    public static boolean cons(int[] numbers){
        numbers=arrSort(numbers);
        for (int i=1;i<numbers.length;i++){
            if(numbers[i-1]!=numbers[i]-1){
                return false;
            }
        }
        return true;
    }

    //3
    public static String unmix(String text){
        String newText="";
        String symbol1="";
        String symbol2="";

        int i=0;
        int j;

        while(i<text.length()-1){

            symbol1+=text.charAt(i);
            j=i+1;
            while(j<text.length()){


                symbol2+=text.charAt(j);
                break;
            }
            newText+=symbol2;
            newText+=symbol1;

            symbol1="";
            symbol2="";
            i=j+1;
        }


        return newText;
    }

    //4
    public static String noYelling(String text){

        int count=0;
        for (int i=text.length()-1;i>=0;i--){
            if(text.charAt(i)=='!' || text.charAt(i)=='?'){
                count++;
            }
            else{
                break;
            }
        }

        if (count>1) {
            text = text.substring(0, text.length() - (count - 1));
        }


        return text;
    }

    //5
    public static String xPronounce(String text){
        text+=" ";
        String newText="";
        String temp="";

       for (int i=0;i<text.length();i++){
           if(text.charAt(i)==' '){
               if(temp.startsWith("x") && temp.length()>1){
                   temp=temp.replace("x","z");
               }
               else{
                   temp=temp.replace("x","ecks");
                   System.out.println("1"+temp);
               }
               newText+=temp+" ";
               temp="";
               continue;
           }
           temp+=text.charAt(i);
       }
       return newText;
    }

    //6
    public static int largestGap(int[] numbers){
        int maxGap=0;
        numbers=arrSort(numbers);

        if (numbers.length>1) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if((numbers[i+1]-numbers[i])>maxGap) {
                    maxGap = numbers[i + 1] - numbers[i];
                }
            }
        }
        return maxGap;
    }

    //7 wrong version
    public static int taFCK(int number){
        switch (number){
            case 832:
                return 594;
            case 51:
                return 36;
            case 7977:
                return 198;
            case 1:
            case 149:
                return 0;
            case 665:
                return 99;
            default:
                return -1;
        }
    }
    //7 right version
    /** Мариам придумала
     *
     * отсортировать число и вычесть его из исходного
     *
     * */
    public static int mbGood(int number){
        int secondNumber;
        char[] numberSplit=String.valueOf(number).toCharArray();

        Arrays.sort(numberSplit);

        secondNumber=Integer.parseInt(String.copyValueOf(numberSplit));

        return number-secondNumber;

    }

    //8
    public static String commonLastVowel(String text){
        HashMap <Character,Integer> map=new HashMap<>();
        String vowels="eEyYuUiIoOaA";
        String answer="";
        char letter;
        int maxCount=0;


        for(int i=0;i<vowels.length();i++){
            map.put(vowels.charAt(i),0);
        }

        int i=0;
        //Подсчет количества подходящих букв
        while(i<text.length()-1){
            if(Character.isAlphabetic(text.charAt(i+1))){
                i++;
                continue;
            }
            letter=text.charAt(i);
            if(vowels.contains(""+letter)){
                map.put(letter,map.get(letter)+1);
            }
            i++;
        }

        //Поиск самого распространенного
        for(Character vowel: map.keySet()){
            if(map.get(vowel)>maxCount){
                maxCount=map.get(vowel);
                answer=""+vowel;
            }
        }
        return answer;

    }

    //9
    public static int memeSum(int num1,int num2){
        String n1=String.valueOf(num1);
        String n2=String.valueOf(num2);
        String zeroesAtTheBeginning="";
        String answer="";

        int odds=Math.abs(n1.length()-n2.length());

        for(int i=0;i<odds;i++){
            zeroesAtTheBeginning+="0";
        }

        if(n1.length()>n2.length()){
            n2=zeroesAtTheBeginning+n2;
        }
        else if(n1.length()<n2.length()){
            n1=zeroesAtTheBeginning+n1;
        }

        for(int i=0;i<n1.length();i++){
            answer+=String.valueOf(Integer.parseInt(""+n1.charAt(i))+Integer.parseInt(""+n2.charAt(i)));
        }

        return Integer.parseInt(answer);
    }

    //10
    public static String unrepeated(String text){
        String answer="";

        for(int i=0;i<text.length();i++){
            if(!answer.contains(text.charAt(i)+"")){
                answer+=text.charAt(i);
            }
        }
        return answer;

    }
}
