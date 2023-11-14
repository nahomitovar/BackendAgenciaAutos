package com.nahomi.tovar.backendagenciaautos.BaseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {

    private static Connection connection = null;

    private Conexion(){

    }

    public static  Connection getConnection(){
        if (connection == null) {
            synchronized (Conexion.class) {
                if (connection == null) {
                    try {
                        String connectionUrl = "jdbc:mysql://localhost:3306/autos";
                        connection = DriverManager.getConnection(connectionUrl, "root", "");
                    } catch (SQLException e) {
                        throw new RuntimeException("Error conectando a la base de datos", e);
                    }
                }
            }
        }
        return connection;
    }

}
