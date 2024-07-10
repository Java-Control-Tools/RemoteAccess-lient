package ru.at0m1cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Класс сервера
 * @author at0m1cc
 * @version 1.0
 * */

public class ServerLogic {
    /**Поле порта для подключения*/
    private final int port;
    /**Поле передаваемого набора команд*/
    private final Commands command;
    /**
     * Конструктор - создания нового объекта с определёнными параметрами
     * @param port Порт для подключения к данному компьютеру (По умолчанию 5556)
     * @param command Основные команды Windows
     * */
    public ServerLogic(int port, Commands command) {
        this.port = port;
        this.command = command;
    }
    /**
     * Метод для запуска сервера
     * */
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port); // Создаём сервер сокет
            while (true) {
                Socket socket = serverSocket.accept(); // Ждём подключения к нашему сокету
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Получение информации с клиента
                String request = reader.readLine();//Передаём всю информацию с клиента
                //Проверка команд
                if (request.equals("reboot")) {
                    command.reboot();
                }
                else if (request.equals("powerOff")) {
                    command.powerOff();
                }
                else if (request.equals("logOut")) {
                    command.logOut();
                }
                else if (request.equals("checkStatus")) {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("OK".getBytes());
                    outputStream.flush();
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //Если что-либо пошло не по плану
        }
    }

}
