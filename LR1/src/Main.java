import java.util.Scanner;

/*
Точка входа в приложение.
    Тут пользователь выбирает, что ему необходимо сделать: вывести простые числа или проверить слова
 */
public class Main {

    public static void main(String[] args) {



        Scanner in = new Scanner(System.in);// Создание объекта класса, который позволяет считывать данные с консоли

        System.out.println("Type \"1\" for primes check \nType \"2\" for palindrome check");
        String answer = in.nextLine();//Считывание ответа

        // Вызов необходимой подпрограммы в соответсвии с ответом
        switch (answer){
            case "1":
                Primes.PrimesMain();
                break;
            case "2":
                Palindrome.palindromeMain();
                break;
            default: //Если ответ не подходит под шаблон, программа останавливет работу
                break;


        }


    }
}
