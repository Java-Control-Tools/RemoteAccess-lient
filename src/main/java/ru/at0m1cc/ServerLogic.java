package ru.at0m1cc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Objects;

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
                switch (request) {
                    case "reboot" -> command.reboot();
                    case "powerOff" -> command.powerOff();
                    case "logOut" -> command.logOut();
                    case "screen" -> {
                        command.screenShot();
                        File file = new File("screen.png");
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                        byte[] byteArray = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = bis.read(byteArray)) != -1) {
                            bos.write(byteArray,0,bytesRead);
                            bos.flush();
                        }
                        bis.close();
                        bos.close();
                    }
                    case "checkStatus" -> {
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write("OK\n".getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //Если что-либо пошло не по плану
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

}
