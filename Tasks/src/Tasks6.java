import java.util.*;

public class Tasks6 {

    //Задание 1
    //Метод удаления буквы из строки по индексу
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
    /**1.Удаляем из текста все символы кроме букв
     * 2.Копируем эту строку во временную переменную
     * 3.Перебираем большой текст, если текущая буква содержится во времеенной строке,добавляем ее в анограмму и
     *   удаляем ее из временной.
     * 4.Если следующая буква не содерджится во временной, обнуляем анограмму и восстанавливаем временную перемнную **/
    public static String hiddenAnagram(String bigTextInput, String smallTextInput){
        String bigText="";
        String smallText="";
        String tempText;
        String anagram="";
        String letter;


        for(int i=0; i<bigTextInput.length();i++){
            if(Character.isAlphabetic(bigTextInput.charAt(i))){
                bigText+=bigTextInput.charAt(i);
            }
        }
        bigText=bigText.toLowerCase(Locale.ROOT);

        for(int i=0; i<smallTextInput.length();i++){
            if(Character.isAlphabetic(smallTextInput.charAt(i))){
                smallText+=smallTextInput.charAt(i);
            }
        }
        smallText=smallText.toLowerCase(Locale.ROOT);
        tempText=smallText;

        int i=0;
        while(i<bigText.length() && anagram.length()!=smallText.length()){

            if(Character.isAlphabetic(bigText.charAt(i))) {

                letter =("" + bigText.charAt(i));

                if (tempText.contains(letter)) {
                    anagram += letter;
                    tempText = deleteLetter(tempText, tempText.indexOf(letter));
                } else {
                    i-=anagram.length();
                    anagram = "";
                    tempText = smallText;
                }
            }
        i++;
        }

        return anagram.length()==smallText.length()? anagram:"noutfond";
    }



    //Задание 2
    public static ArrayList<String> collect(String text, int size){
        ArrayList<String> arr=new ArrayList<>();
        if (text.length() >= size) {
            arr.add(text.substring(0, size));
            arr.addAll(collect(text.substring(size), size));
            Collections.sort(arr);
        }
        return arr;
    }


    //Задание 3

    public static String nicoCipher(String message,String key){
        int keySize=key.length();       //Длинна ключа для сокращения кода
        String answer="";               //Ответ
        String sortedKeyString;         //Строка с отсортированным по алфовиту ключом

        char[] sortedKeyLetters=key.toCharArray();              //Массив букв ключа, для того, чтобы отсортировать его
        int[] numKey=new int[keySize];                          //Ключ в цифрах
        String[] sortedMessageLetters = new String[keySize];    //Массив отсортированных букв сообщения

        Arrays.sort(sortedKeyLetters); //Сортировка букв ключа
        sortedKeyString=String.copyValueOf(sortedKeyLetters); //Запись массива букв в строчку для получения кода в цифрах

        //Заполнение ключа в цифрах
        for(int i=0;i<keySize;i++){
            numKey[i]=sortedKeyString.indexOf(key.charAt(i));
        }

        //Заполнение массива букв пустыми строчками (До этого было null, и он прибавлял буквы к строке "null")
        for(int i=0;i<keySize;i++){
            sortedMessageLetters[i]="";
        }
        //Делаем как в шаге 2 в задании
        for(int i=0;i<message.length();i++){
            sortedMessageLetters[numKey[i%keySize]]+=message.charAt(i);
        }

        //Временные для сокращения кода
        String tempString;
        String tempLetter;
        int i=0;
        while(!String.join("",sortedMessageLetters).equals("")){ //Пока все строчки из массива не будут пустыми
            tempString=sortedMessageLetters[i%keySize];
            if(tempString.length()>0){
                tempLetter=tempString.charAt(0)+"";
                sortedMessageLetters[i%keySize]=deleteLetter(tempString,0); //Метод из 1 задания, удаляет букву по индексу
            }
            else{
                tempLetter=" ";
            }

            answer+=tempLetter;
            i++;
        }

        return answer;
    }


    //Задание 4
    public static int[] twoProduct(int[] numbers, int composition){
        int num1=0;
        int num2=0;
        int gap=numbers.length+1;

        if(numbers.length>1) {
            for (int i = 1; i < numbers.length - 1; i++) {
                for (int j = i-1; j>=0; j--) {
                    if (numbers[i] * numbers[j] == composition && j - i < gap) {
                        num1 = numbers[j];
                        num2 = numbers[i];
                        gap = j - i;
                    }
                }
            }
        }
        return num1!=0? new int[]{num1,num2}:new int[]{};
    }


    //Задание 5
    public static int[] isExact(int f, int m, int n) {
        if (f < n) {
            return isExact(f * (m + 1), m + 1, n);
        }
        return new int[] {f, m};
    }

    public static int[] isExact(int n) {
        int[] res = isExact(1, 1, n);
        if (res[0] == n) return res;
        return new int[] {};
    }

    //6
    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String f = frac.substring(startBracket+1, frac.length()-1);
            frac = frac.substring(0, startBracket);
            for (int i = 0; i < 9 - f.length(); i++)
                frac += f;
        }
        double a = Double.parseDouble(frac);
        int div = 2;
        while (Math.ceil(a * div) - a * div > 0.000001){
            div++;
        }
        return "" + (int)Math.ceil(a * div) + "/" + div;
    }

    //7
    public static String pilish_string(String str) {
        String res = "";
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0;
        int p;
        int n;
        while (str.length() > 0) {
            p = pi.charAt(piIndex) - 48;
            n = Math.min(p, str.length());
            res += str.substring(0, n);
            str = str.substring(n);
            piIndex++;
            if (str.length() > 0) {
                res += ' ';
            }
            else if (p > n) {
                for (int i = 0; i < p - n; i++) {
                    res += res.charAt(res.length() - 1);
                }
            }
        }
        return res;
    }

    //8
    public static String generateNonconsecutive(int n) {
        String res = "";
        String format = "%" + n + 's';
        int count = 2 << (n-1);
        nextNumber:
        for (int i = 0; i < count; i++) {
            String num = String.format(format, Integer.toBinaryString(i)).replace(' ', '0');
            for (int j = 0; j < n - 1; j++) {
                if (num.charAt(j) == '1' && num.charAt(j + 1) == '1') {
                    continue nextNumber;
                }
            }
            res += num + ' ';
        }
        return res.substring(0, res.length() - 1);
    }

    //9
    private static int[] getLetterSet(String str) {
        int[] set = new int[26];
        for (char c : str.toCharArray())
            set[c - 97]++;
        return set;
    }

    public static String isValid(String str) {
        int[] set = getLetterSet(str);
        int[] medium = new int[str.length()];
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0) medium[set[i]]++;
        int cur = 0;
        int max = 0;
        for (int i = 0; i < medium.length; i++)
            if (medium[i] > cur) {
                cur = medium[i];
                max = i;
            }
        boolean index = false;
        for (int i = 0; i < set.length; i++)
            if (set[i] != 0 && max - set[i] != 0) {
                if (index) return "NO";
                index = true;
            }
        return "YES";
    }

    //10
    public static int[][] sumsUp(int[] arr) {
        ArrayList<int[]> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(8 - arr[i])) {
                int a = arr[i];
                int b = 8 - a;
                if (a > b) {
                    b = a;
                    a = 8 - b;
                }
                res.add(new int[] {i - map.get(8-arr[i]), a, b});
            }
            map.put(arr[i], i);
        }
        Collections.sort(res, (o1, o2) -> o1[0]-o2[0]);
        int[][] newRes = new int[res.size()][];
        for (int i = 0; i < res.size(); i++)
            newRes[i] = new int[] {res.get(i)[1], res.get(i)[2]};
        return newRes;
    }
}
