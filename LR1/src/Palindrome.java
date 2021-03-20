import java.util.Scanner;

// Класс, который проверяет слова на полиндромы
public class Palindrome {

    // Главный метод класса, запускает анализ
    public static void palindromeMain(){
        Scanner in = new Scanner(System.in); // Создание объекта класса, который позволяет считывать данные с консоли

        System.out.println("Type your words bellow:");
        String all_words =in.nextLine()+" "; // Ввод строки со словами, в конце добавляется пробел

        String cur_word=""; // Переменная для анализа текущего слова

        // В цикле до тех пор, пока не встретится пробел записывается слово в переменную,
        // затем анализируется полученное слово
        for (int i=0;i<all_words.length();i++) {
            if (all_words.charAt(i) == ' ') {
                if (isPalindrome(cur_word))
                    System.out.println("word \"Pa"+cur_word+"\" is palindrome");
                else
                    System.out.println("word \""+cur_word+"\" isn't palindrome");
                cur_word="";
            }
            else
                cur_word+=all_words.charAt(i);
        }
    }

    // Метод проверяет является ли слово палиндромом или нет
    // Модификаторы доступа public в соответсвии с заданием*
    public static boolean isPalindrome(String word){
        String reverseStr=reverseString(word);

        if (reverseStr.equals(word))
            return true;
        else
            return false;
    }

    // Метод возвращает строку, записанную справа налево
    public static String reverseString(String word){
        String reverseStr="";

        for (int i=word.length()-1;i>=0;i--)
            reverseStr+=word.charAt(i);

        return  reverseStr;
    }

}
