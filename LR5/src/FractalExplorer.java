import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;

/** Представляет графическое отображение фрактала **/
public class FractalExplorer extends JFrame {

    /** Размер изображения **/
    private int screenSize;

    /** Объект компонента отображения фрактала **/
    private JImageDisplay display;

    /** Объект генератора фракталов **/
    private FractalGenerator fractal;

    /** Объекты фракталов **/
    private FractalGenerator mandelbrot;
    private FractalGenerator tricorn;
    private FractalGenerator burningShip;

    /** Объект плоскости отрисовки фрактала **/
    private Rectangle2D.Double range;
    //TODO
    /** Конструктор, принимающий рамзер окна (длину или ширину) **/
    public FractalExplorer(int size){

        screenSize=size;

        display= new JImageDisplay(size,size);

        fractal = new Mandelbrot();

        mandelbrot = new Mandelbrot();
        tricorn = new Tricorn();
        burningShip= new BurningShip();

        range=new Rectangle2D.Double();
        fractal.getInitialRange(range);

    }

    /** Выводит графическое изображение **/
    public void createAndShowGui(){

        //Установка слоя для компонента отображения
        display.setLayout(new BorderLayout());

        //Инициализация окна
        JFrame frame = new JFrame("Fractal Explorer");

        //Инициализация панелей
        JPanel upperPanel = new JPanel();
        JPanel lowerPanel = new JPanel();

        //Инициализация элементов
        JButton resetButton = new JButton("Reset fractal");
        JButton saveButton = new JButton("Save");
        JComboBox comboBox = new JComboBox();
        JLabel label = new JLabel();

        //Установка текста для ферхней панели
        label.setText("Fractal:");

        //Установка содержания выпадающего списка
        comboBox.addItem(mandelbrot);
        comboBox.addItem(tricorn);
        comboBox.addItem(burningShip);

        //Установка команд для кнопок
        resetButton.setActionCommand("Reset");
        saveButton.setActionCommand("Save");

        //Установка логики для кнопок
        ButtonPressHandler handler = new ButtonPressHandler();
        resetButton.addActionListener(handler);
        saveButton.addActionListener(handler);

        //Установка логики на нажатие кнопки мыши
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        //Установка логики на выбор из списка
        ComboBoxHandler switchHandler = new ComboBoxHandler();
        comboBox.addActionListener(switchHandler);

        //Настройки верхней панели
        upperPanel.add(label);
        upperPanel.add(comboBox);

        //Настройка нижней панели
        lowerPanel.add(saveButton);
        lowerPanel.add(resetButton);

        //Добавление элементов в слой окна
        frame.add(display, BorderLayout.CENTER);
        frame.add(lowerPanel, BorderLayout.SOUTH);
        frame.add(upperPanel,BorderLayout.NORTH);

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
                double xCoord = fractal.getCoord(range.x, range.x + range.width, screenSize, x);
                double yCoord = fractal.getCoord(range.y, range.y + range.height, screenSize, y);

                //Количество итерация для координат в области отображения
                int iteration = fractal.numIterations(xCoord, yCoord);

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

    /** Внутренний класс для отработки нажатия на кнопки **/
    private class ButtonPressHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //Получить команду
            String command = e.getActionCommand();

            //Обработка соответсвующей команды
            if (command.equals("Reset")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else{
                //Окно выбора окна
                JFileChooser fileChooser = new JFileChooser();

                //Фильтр png для файла
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                fileChooser.setFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);

                //Подтверждение операции
                int userChoice = fileChooser.showSaveDialog(display);
                if(userChoice==JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    try {
                        ImageIO.write(display.image, "png", file);
                    }
                    catch (Exception exception){
                        JOptionPane.showMessageDialog(display,exception.getMessage(),"Can't save image",JOptionPane.ERROR_MESSAGE);

                    }

                }
            }
        }
    }

    /** Внутренний класс для отработки нажатия ЛКМ **/
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x=e.getX();
            int y=e.getY();

            double xCoord = fractal.getCoord(range.x, range.x + range.width, screenSize, x);
            double yCoord = fractal.getCoord(range.y,range.y+range.height, screenSize,y);

            fractal.recenterAndZoomRange(range,xCoord,yCoord,0.5);

            drawFractal();
        }
    }

    /** Внутренний класс для отработки действий с combobox **/
    private class ComboBoxHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox source = (JComboBox) e.getSource();
            fractal = (FractalGenerator) source.getSelectedItem();
            fractal.getInitialRange(range);
            drawFractal();
        }
    }


    /** Старт программы **/
    public static void main(String[] args){
        FractalExplorer explorer = new FractalExplorer(800);
        explorer.createAndShowGui();
        explorer.drawFractal();
    }

}
