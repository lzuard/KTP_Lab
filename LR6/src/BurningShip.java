import java.awt.geom.Rectangle2D;

/**Класс, который строит фрактал Burning ship **/
public class BurningShip extends FractalGenerator{

    /** Максимальное количество итераций **/
    public static final int MAX_ITERATIONS = 2000;

    /**
     * Метод позволяет генератору фракталов определить наиболее
     * "интересную" область комплексной плоскости для
     * конкретного фрактала.
     */
    public void getInitialRange(Rectangle2D.Double rectangle){
        rectangle.x=-2;
        rectangle.y=-2.5;
        rectangle.width=4;
        rectangle.height=4;
    }

    /** Реализует итеративную функцию для фрактала Burning ship **/
    public int numIterations(double x, double y){

        int i = 0;


        double num = 0;
        double num_i = 0;


        while (i < MAX_ITERATIONS && num * num + num_i * num_i < 4)
        {
            double new_num = num * num - num_i * num_i + x;
            double new_num_i = 2 * Math.abs(num) * Math.abs(num_i) + y;
            num = new_num;
            num_i = new_num_i;
            i += 1;
        }

        if (i == MAX_ITERATIONS)
        {
            return -1;
        }
        return i;
    }

    /** Получить название фрактала **/
    @Override
    public String toString(){
        return "Burning ship";
    }
}
