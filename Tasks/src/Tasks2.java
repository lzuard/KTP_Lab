import java.util.Locale;

public class Tasks2 {

    public static int oppositeHouse(int num, int length){
     return Math.abs(num-1-length*2);
    }

    public static String nameShuffle(String fullName){
        String name = "";
        String surname="";

        int i=0;

        while (i<fullName.length() && fullName.charAt(i)!=' '){
            name+=fullName.charAt(i);
            i++;
        }

        i++;
        while(i<fullName.length()){
            surname+=fullName.charAt(i);
            i++;
        }

        return surname+" "+name;
    }

    public static int discount(int price, int sale){

        return price-price/100*sale;
    }

    public static int differenceMaxMin(int[] arr){
        int min=arr[0];
        int max=arr[0];

        for (int i: arr){
            if(arr[i]<min){
                min=arr[i];
            }
            else if (arr[i]>max){
                max=arr[i];
            }
        }
        return max-min;
    }

    public static int equal(int a, int b, int c){
        int count=0;
        if(a==b){count++;}
        if(a==c){count++;}
        if(b==c){count++;}

        return count;
    }

    public static String reverse(String string){
        String reverseString="";

        for (int i=reverseString.length()-1;i>=0;i--){
            reverseString+=string.charAt(i);
        }

        return reverseString;
    }

    public static boolean getXO(String string){
        int xCount=0;
        int yCount=0;

        string=string.toLowerCase();

        for(int i=0; i<string.length();i++){
            if(string.charAt(i)=='x'){
                xCount++;
            }
            else if(string.charAt(i)=='o'){
                yCount++;
            }
        }

        return xCount==yCount;
    }

    public static String bomb(String text){

        text=text.toLowerCase();

        if(text.contains("bomb")){
            return "DUCK!";
        }
        else{
            return "Relax, there's no bomb.";
        }

    }

    public static boolean sameAscii(String val1, String val2){
        int num1=0;
        int num2=0;

        for (int i=0;i<val1.length();i++){
            num1+=(int)val1.charAt(i);
        }
        for (int i=0;i<val2.length();i++){
            num2+=(int)val2.charAt(i);
        }
        return num1==num2;
    }

}
