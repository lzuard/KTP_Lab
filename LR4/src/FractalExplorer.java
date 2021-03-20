import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/** Представляет графическое отображение фрактала **/
public class FractalExplorer extends JFrame {

    /** Размер изображения **/
    private int screenSize;

    /** Объект компонента отображения фрактала **/
    private JImageDisplay display;

    /** Объект генератора фрактала **/
    private FractalGenerator generator;

    /** Объект плоскости отрисовки фрактала **/
    private Rectangle2D.Double range;

    /** Конструктор, принимающий рамзер окна (длину или ширину) **/
    public FractalExplorer(int size){

        screenSize=size;

        display= new JImageDisplay(size,size);

        generator =new Mandelbrot();

        range=new Rectangle2D.Double();
        generator.getInitialRange(range);

    }

    /** Выводит графическое изображение **/
    public void createAndShowGui(){

        //Установка слоя для компонента отображения
        display.setLayout(new BorderLayout());

        //Инициализация окна
        JFrame frame = new JFrame("Fractal Explorer");
        JButton resetButton = new JButton("Reset");


        //Установка логики для кнопки
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);

        //Установка логики на нажатие кнопки мыши
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        //Добавление элементов в слой окна
        frame.add(display, BorderLayout.CENTER);
        frame.add(resetButton, BorderLayout.SOUTH);

        //Границы и выход по нажатию на кнопку закрытия
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0,0, 1000, 1000);

        //Формирование окна
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /** Метод отрисовки фрактала **/
    private void drawFractal(){

        //Перебор всех пикселей внутри окна
        for (int x=0; x<screenSize; x++) {
            for (int y = 0; y < screenSize; y++) {
                //Поиск соответсвующих координат х и у в области отображения фрактала
                double xCoord = generator.getCoord(range.x, range.x + range.width, screenSize, x);
                double yCoord = generator.getCoord(range.y, range.y + range.height, screenSize, y);

                //Количество итерация для координат в области отображения
                int iteration = generator.numIterations(xCoord, yCoord);

                //Выбор цвета пикселя в зависимости от количества итераций
                if (iteration == -1) {
                    display.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    /** Внутренний класс для отработки нажатия на кнопку **/
    private class ResetHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            generator.getInitialRange(range);
            drawFractal();
        }
    }

    /** Внутренний класс для отработки нажатия ЛКМ **/
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();

            double xCoord = generator.getCoord(range.x, range.x + range.width, screenSize, x);
            double yCoord = generator.getCoord(range.y,range.y+range.height, screenSize,y);

            generator.recenterAndZoomRange(range,xCoord,yCoord,0.5);

            drawFractal();
        }
    }

    public static void main(String[] args){
        FractalExplorer explorer = new FractalExplorer(800);
        explorer.createAndShowGui();
        explorer.drawFractal();
    }

}
