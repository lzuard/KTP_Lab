import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String,Integer> arr=new HashMap<>();
        arr.put("Nice", 942208);
        arr.put("Abu Dhabi", 1482816);
        arr.put("Naples", 2186853);
        arr.put("Vatican City", 572);

        arr=Tasks3.millionsRounding(arr);
        System.out.println(arr);

        double[] arr1=Tasks3.otherSides(1);
        for (int i=0;i<2;i++){
            System.out.println(arr1[i]);
        }

        System.out.println(Tasks3.inatorInator("sasha"));
        System.out.println(Tasks3.doesBreakFit(1, 1, 1, 1, 1));
        System.out.println(Tasks3.totalDistance(55.5, 5.5, 5, false));

        System.out.println(Tasks4.cons(new int[]{5, 6, 7, 8, 9, 9}));
        System.out.println(Tasks4.unmix("123456"));
        System.out.println(Tasks4.noYelling("Oh my goodness!!!"));
        System.out.println(Tasks4.xPronounce("The x ray is excellent"));
        System.out.println(Tasks4.largestGap(new int[]{9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));
        System.out.println(Tasks4.commonLastVowel("OOI UUI EEI AAI"));
        System.out.println(Tasks4.memeSum(122, 81));

        //System.out.println(Tasks4.mbGood(832));


        System.out.println(Tasks6.hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println(Tasks6.collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));

        int[] arr2=new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        arr2=Tasks6.twoProduct(arr2, 10);
        for (int i:arr2){
            System.out.println(i);
        }
        /*
        int[] arr3 =Tasks6.isExact(6);
        for (int i:arr3){
            System.out.println(i);
        }

         */

        System.out.println(Tasks6.nicoCipher("iloveher", "612345"));
        System.out.println(Tasks4.unrepeated("hello"));
        System.out.println(Tasks4.mbGood(665));


    }
}
