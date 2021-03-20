// Класс для работы с точкой на плоскости
public class Point3d extends Point2d {

    //Координаты точки

    private double zCoord;

    //Конструктор класса по умолчанию
    public Point3d(){
        super();
        zCoord=0;
    }
    // Конструктор класса для ввода трех координат
    public Point3d(double x, double y, double z){
        super(x,y);
        zCoord=z;
    }

    public double getZ(){
        return zCoord;
    }
    public void setZ(double z){
        zCoord=z;
    }

    // Метод сравнения двух точек
    public boolean isEqual(Point3d obj){
        if (super.getX()==obj.getX() && super.getY()==obj.getY() && zCoord== obj.getZ() )
            return true;
        else
            return false;
    }


    // Метод для рассчета расстояния от между двумя точками
    public double distanceTo (Point3d obj){
        //Ввод локальных переменных для удобства ввода в формулу
        double x1=getX();
        double y1=getY();
        double z1=zCoord;
        double x2= obj.getX();
        double y2= obj.getY();
        double z2= obj.getZ();

        double distance=Math.abs(Math.pow(Math.pow(x1-x2,2)+Math.pow(y1-y2,2)+Math.pow(z1+z2,2), 1.0/2));

        return distance;
    }

}
