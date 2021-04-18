import java.util.HashMap;

public class Tasks3 {

    //1
    public static HashMap<String,Integer> millionsRounding(HashMap<String,Integer> arr){
        int population;
        int temp;
        for (String i: arr.keySet()){
            population=arr.get(i);
            temp=population%1000000;
            population=population/1000000*1000000;

            if(temp>=500000){
                population+=1000000;
            }
            arr.put(i,population);
        }
        return arr;
    }

    //2
    private static double gradVRad(double grad){
        return grad*Math.PI/180;
    }
    public static double[] otherSides(double smallSide){
        double sin30=Math.sin(gradVRad(30));
        double sin60=Math.sin(gradVRad(60));
        double sin90=Math.sin(gradVRad(90));

        double middleSide=(smallSide/sin30)*sin60;
        double largeSide=(smallSide/sin30)*sin90;

        double[] arr=new double[2];
        arr[0]=(double) Math.round((largeSide)*100)/100;
        arr[1]=(double) Math.round((middleSide)*100)/100;
        return arr;
    }

    //3
    public static String rps(String firstPlayer,String secondPlayer){
        if(firstPlayer.equals(secondPlayer)){
            return "TIE";
        }
        else if(firstPlayer.equals("rock") && secondPlayer.equals("scissors")){
            return "Player 1 wins";
        }
        else if(firstPlayer.equals("paper") && secondPlayer.equals("rock")){
            return "Player 1 wins";
        }
        else if(firstPlayer.equals("scissors") && secondPlayer.equals("paper")){
            return "Player 1 wins";
        }
        else{
            return "Player 2 wins";
        }
    }

    //4
    public static int warOfNumbers(int[] numbers){
        int even=0; //четное
        int odd =0; //нечетное

        for (int number : numbers) {
            if (number % 2 == 0) {
                even++;
            } else {
                odd++;
            }

        }

        return Math.abs(even-odd);
    }

    //5
    public static String reverseCase(String text){
        String newText="";
        for(int i=0;i<text.length();i++){
            if (Character.isLowerCase(text.charAt(i))){
                newText+=Character.toUpperCase(text.charAt(i));
            }
            else{
                newText+=Character.toLowerCase(text.charAt(i));
            }
        }
        return newText;
    }

    //6
    public static String inatorInator(String text){

        String vowels="aeiouy";

        int numberAtTheEnd=text.length()*1000;

        if(vowels.indexOf(text.charAt(text.length()-1))>=0){
            text+="-";
        }
        return text+"inator "+numberAtTheEnd;

    }

    //7
    public static boolean doesBreakFit(int a, int b, int c, int w, int h){

        int[] sides={a,b,c};

        for(int i=0;i<sides.length;i++){
            for(int j=0;j<sides.length;j++){
                if(i!=j && sides[i]<=w && sides[j]<=h){
                    return true;
                }
            }
        }
        return false;
    }

    //8
    public static double totalDistance(double fuel,double consumption,int passengers,boolean AC){

        consumption+=passengers*5*(consumption/100);
        if(AC){
            consumption+=10*(consumption/100);
        }
        return (fuel/consumption)*100;
    }

    //9
    public static int mean(int[] numbers){
        int sum=0;
        for(int num:numbers){
            sum+=num;
        }
        return sum/numbers.length;
    }

    //10
    public static boolean parityAnalysis(int number){
        String strNum=String.valueOf(number);
        int sum=0;

        for(int i=0; i<strNum.length();i++){
            sum+=Integer.parseInt(""+strNum.charAt(i));
        }

        return number%2==sum%2;
    }
}
