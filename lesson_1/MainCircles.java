package Java2.lesson_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/*
	Полностью разобраться с кодом
	Прочитать методичку к следующему уроку
	Написать класс Бэкграунд, изменяющий цвет канвы в зависимости от времени
	 * Реализовать добавление новых кружков по клику используя ТОЛЬКО массивы
	 ** Реализовать по клику другой кнопки удаление кружков (никаких эррейЛист)
* */

public class MainCircles extends JFrame {

    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private int maxCountBalls = 25;
    private BckGrnd bckGrnd = new BckGrnd();
    private Sprite[] sprites = new Sprite[0];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        GameCanvas gameCanvas = new GameCanvas(this);
        initApplication();
        add(gameCanvas, BorderLayout.CENTER);
        setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int btn = e.getButton();
                if(btn == MouseEvent.BUTTON1){
                    //добавить круг
                    if(sprites.length < maxCountBalls) addCircle(true);
                } else if(btn == MouseEvent.BUTTON3){
                    //удалить круг
                    if(sprites.length > 0) addCircle(false);
                }
            }
        });
    }

    void addCircle(Boolean bool){
        if(bool){
           Sprite[] newSpriteArr = Arrays.copyOf(sprites, sprites.length + 1);
           newSpriteArr[sprites.length] = new Ball();
           sprites = newSpriteArr;
        } else {
            Sprite[] newSpriteArr = Arrays.copyOfRange(sprites, 1, sprites.length);
            sprites = newSpriteArr;
        }
    }

    void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime){
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }

    void setBackgroundColor(GameCanvas canvas, float delta){
        bckGrnd.changeColor(canvas, delta);
    }

}