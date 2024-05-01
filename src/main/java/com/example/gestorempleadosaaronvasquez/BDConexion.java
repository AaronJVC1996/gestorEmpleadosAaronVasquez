package com.example.gestorempleadosaaronvasquez;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {

    private static final String URL = "jdbc:mysql://localhost:3306/gestionempleados";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection conectar;

    public BDConexion() {
        try {
            conectar = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Base de datos conectada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}