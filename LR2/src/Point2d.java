// Класс из методички

/**
 * двумерный класс точки.
 **/
public class Point2d {

    /** Поля **/
    private double xCoord;
    private double yCoord;


    /** Конструкторы **/
    public Point2d ( double x, double y) {
        xCoord = x;
        yCoord = y;
    }

    public Point2d () {
        this.xCoord=0;
        this.yCoord=0;
    }

    /** Методы **/
    public double getX () {
        return xCoord;
    }



    public double getY () {
        return yCoord;
    }

    public void setX ( double val) {
        xCoord = val;
    }
    public void setY ( double val) {
        yCoord = val;
    }
}