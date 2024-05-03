package com.example.gestorempleadosaaronvasquez;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.sql.*;

public class ConsultaEmpleadoController {
    @FXML
    private ListView<String> empleadosListView;

    @FXML
    private Label idLabel;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label puestoLabel;

    @FXML
    private Label salarioLabel;

    @FXML
    private Label fechaLabel;

    @FXML
    private void initialize() {
        // Cargar los nombres de los empleados desde la base de datos
        cargarEmpleados();

        // Configurar el listener para el evento de selección en el ListView (obtenido de internet)
        empleadosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Mostrar los detalles del empleado seleccionado
            mostrarDetallesEmpleado(newValue);
        });
    }

    // Metodo para cargar los nombres de los empleados desde la base de datos y mostrarlos en el ListView
    private void cargarEmpleados() {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionempleados", "root", "root")) {
            String consulta = "SELECT nombre FROM empleados";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
                ResultSet resultado = declaracion.executeQuery();
                while (resultado.next()) {
                    empleadosListView.getItems().add(resultado.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para mostrar los detalles del empleado elegido
    private void mostrarDetallesEmpleado(String nombreEmpleado) {
        // Realizar la consulta a la base de datos para obtener los detalles del empleado
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionempleados", "root", "root")) {
            String consulta = "SELECT id, nombre, puesto, salario, fecha FROM empleados WHERE nombre = ?";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
                declaracion.setString(1, nombreEmpleado);
                ResultSet resultado = declaracion.executeQuery();
                if (resultado.next()) {
                    idLabel.setText(resultado.getString("id"));
                    nombreLabel.setText(resultado.getString("nombre"));
                    puestoLabel.setText(resultado.getString("puesto"));
                    salarioLabel.setText(resultado.getString("salario"));
                    fechaLabel.setText(resultado.getString("fecha"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para eliminar un empleado seleccionado
    @FXML
    private void eliminarEmpleado() {
        // Obtener el nombre del empleado seleccionado en el ListView
        String nombreEmpleado = empleadosListView.getSelectionModel().getSelectedItem();

        // Realizar la eliminación del empleado en la base de datos
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionempleados", "root", "root")) {
            String consulta = "DELETE FROM empleados WHERE nombre = ?";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
                declaracion.setString(1, nombreEmpleado);
                declaracion.executeUpdate();

                // Eliminar el empleado de la lista en el ListView
                empleadosListView.getItems().remove(nombreEmpleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para actualizar la lista de empleados
    @FXML
    private void refrescarLista() {
        // Limpiar la lista y volver a cargar los empleados desde la base de datos
        empleadosListView.getItems().clear();
        cargarEmpleados();
    }
}