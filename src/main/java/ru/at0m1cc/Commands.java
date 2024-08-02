package ru.at0m1cc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Класс с набором команд
 * @author at0m1cc
 * @version 1.0
 * */
public class Commands {
    /**Метод, который отправляет shell скрипт для перезагрузки ПК*/
    public void reboot(){
        try {
            Runtime.getRuntime().exec("shutdown /r /t 0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**Метод, который отправляет shell скрипт для выключения ПК*/
    public void powerOff(){
        try {
            Runtime.getRuntime().exec("shutdown /s /t 0");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**Метод, который отправляет shell скрипт для выхода из системы*/
    public void logOut(){
        try {
            Runtime.getRuntime().exec("shutdown /l");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void screenShot() throws AWTException, IOException {
        Robot robot = new Robot();
        String format = "png";
        String name = "screen." + format;

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Rectangle rectangle = new Rectangle(toolkit.getScreenSize());
        BufferedImage screenshot = robot.createScreenCapture(rectangle);
        ImageIO.write(screenshot, format, new File(name));
    }
}
