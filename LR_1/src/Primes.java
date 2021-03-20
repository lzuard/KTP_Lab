// Класс, который выводит все простые числа от 2 до 100
public class Primes {

    //Главный метод класса, запускающая вывод чисел
    public static void PrimesMain() {
        System.out.println("All prime numbers from 2 to 100:");

        for (int i=2;i<=100;i++){

            if (IsPrime(i))
                System.out.println(i);
        }
    }

    //Проверка, является ли число простым или нет
    public static boolean IsPrime(int n){

        for (int i=2;i<n;i++){
            if (n%i==0)
                return false;
        }

        return true;
    }

}
