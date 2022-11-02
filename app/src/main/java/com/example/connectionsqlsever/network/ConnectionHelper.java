package com.example.connectionsqlsever.network;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    String userName, userPassword, ip, port, database;

    @SuppressLint("NewApi")
    //класс установки соединения
    public Connection connectionClass() {
        ip = "ngknn.ru"; // сервер к которому подключаемся
        database = "Movies"; // название нашей базы данных
        userName = "41П"; // имя пользователя
        userPassword = "123456"; // пароль пользователя
        port = "1433"; // порт подключения

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        /*StrictMode - это инструмент разработчика, который обнаруживает ошибки, которые вы могли бы сделать случайно,
         и обращает на них ваше внимание, чтобы вы могли их исправить.*/
        StrictMode.setThreadPolicy(policy);

        Connection connection = null; //
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+
                    "databasename="+ database+";user="+userName+";password="+userPassword+";";
            connection = DriverManager.getConnection(ConnectionURL);

        } catch (Exception ex) {
            Log.e("Error",ex.getMessage()); // выведение ошибки в консоль
        }
        return connection;
    }
}
