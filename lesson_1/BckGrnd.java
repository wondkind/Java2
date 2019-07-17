package Java2.lesson_1;

import java.awt.*;

public class BckGrnd{

    private float red = 0;
    private float green = 0;
    private float blue = 0;
    private int napravRed = 1;
    private int napravGreen = 1;
    private int napravBlue = 1;

    public void changeColor(GameCanvas canvas, float delta){

        delta *= 100; //просто чуть ускоряем смену цвета

        float deltaRed = delta * napravRed;
        float deltaGreen = delta * 1.1f * napravGreen;
        float deltaBlue = delta * 1.2f * napravBlue;

        red += deltaRed;
        green += deltaGreen;
        blue += deltaBlue;

        if(red > 255 || red < 0){
            red -= deltaRed;
            napravRed = -napravRed;
        }
        if(blue > 255 || blue < 0){
            blue -= deltaBlue;
            napravBlue = -napravBlue;
        }
        if(green > 255 || green < 0){
            green -= deltaGreen;
            napravGreen = -napravGreen;
        }
        canvas.setBackground(new Color((int)red, (int)green, (int)blue));
    }

}
