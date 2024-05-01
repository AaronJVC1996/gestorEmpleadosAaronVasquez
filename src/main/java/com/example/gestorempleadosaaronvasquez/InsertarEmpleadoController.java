package com.example.gestorempleadosaaronvasquez;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertarEmpleadoController {

    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox<String> puestoComboBox;

    @FXML
    private TextField salarioTextField;

    private BDConexion conexionBD;

    // Este es el metodo dado al boton Insertar
    @FXML
    private void insertarEmpleado() {
        // Obtenemos los valores de los campos nombre, puesto y salario
        String nombre = nombreTextField.getText();
        String puesto = puestoComboBox.getValue();
        String salario = salarioTextField.getText();

        // Comprobamos que todos los campos estan completos
        if (nombre.isEmpty() || puesto == null || salario.isEmpty()) {
            // Mostrar un mensaje de error si algun campo esta vacio
            System.out.println("Error: Todos los campos deben estar rellenados");
        } else {
            // Si se ha rellenado todos los campos correctamente el nuevo empleado
            // sera introducido en la base de datos
            try {
                // Obtenemos la conexion a la base de datos
                conexionBD = new BDConexion();
                Connection conexion = conexionBD.getConexion();

                // Crear la consulta SQL para insertar el nuevo empleado
                String consulta = "INSERT INTO empleados (nombre, puesto, salario, fecha) VALUES (?, ?, ?, CURDATE())";
                PreparedStatement declaracion = conexion.prepareStatement(consulta);
                declaracion.setString(1, nombre);
                declaracion.setString(2, puesto);
                declaracion.setString(3, salario);

                // Ejecutar la consulta
                declaracion.executeUpdate();
                // Llamada ala ventana emergente, nos dira que se aniadio correctamente y habra que darle
                // click en aceptar
                Main.mostrarVentanaMensaje(nombre);
                // Mostrar un mensaje indicando que el empleado se ha insertado correctamente
                System.out.println("Se ha ingresado el empleado correctamente");

            } catch (SQLException e) {
                // Error al insertar el empleado en la base de datos
                // Si introducimos letras en el salario por ejemplo.
                System.out.println("Error al insertar el empleado en la base de datos: " + e.getMessage());
            }
        }
    }
}