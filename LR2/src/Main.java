import java.util.Scanner;

public class Main {

    // Точка входа в приложение
    public static void main(String[] args) {
        Point3d A = new Point3d(3.4,12,6.78); //Пример конструктора с 3 аргументами
        Point3d B = new Point3d(); //Пример конструктора по умолчанию
        Point3d C = GetCoord(); // Вызов метода для ввода значений пользователем

        // Если две точки равны, выводится сообщение
        if (A.isEqual(B)|| A.isEqual(C)|| B.isEqual(C))
            System.out.println("Some dots are equal!");
        else{
            double S=ComputeArea(A,B,C);
            System.out.println("S="+S);
        }
    }

    // Метод для расчета площади трегуольника по трем точкам
    public static double ComputeArea(Point3d obj1, Point3d obj2, Point3d obj3){
        // Рассчет длинн сторон треугольника
        double aSide=obj1.distanceTo(obj2);
        double bSide=obj2.distanceTo(obj3);
        double cSide=obj3.distanceTo(obj1);

        //Расчет полупериметра треугольника
        double p = (aSide+bSide+cSide)/2;

        //Рассчет площади треугольника
        double S = Math.pow(p*(p-aSide)*(p-bSide)*(p-cSide),1.0/2);

        return S;
    }

    // Метод для ввода координат точки
    public static Point3d GetCoord(){
        Scanner in = new Scanner(System.in);

        System.out.println("Enter x:");
        double x = in.nextDouble();
        System.out.println("Enter y:");
        double y = in.nextDouble();
        System.out.println("Enter z:");
        double z = in.nextDouble();

        Point3d Dot = new Point3d(x,y,z);
        return Dot;

    }
}
