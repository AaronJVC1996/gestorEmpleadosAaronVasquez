/** @Author Aaron Vasquez */
package com.example.gestorempleadosaaronvasquez;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditarEmpleadoController {
    @FXML
    private Label idLabel;
    @FXML
    private TextField nombreTextField;

    @FXML
    private ComboBox<String> puestoComboBox;

    @FXML
    private TextField salarioTextField;
    private String nombreEmpleado;

    // Metodo para inicializar los datos del empleado en los campos de edición
    public void initData(String nombreEmpleado) {
        // Realizar una consulta a la base de datos para obtener el resto de la información del empleado
        this.nombreEmpleado = nombreEmpleado; // Guardar el primer nombre antes de edicion
        BDConexion conexionBD = new BDConexion();
        try (Connection conexion = conexionBD.getConexion()) {
            String consulta = "SELECT id, nombre, puesto, salario FROM empleados WHERE nombre = ?";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
                declaracion.setString(1, nombreEmpleado);
                ResultSet resultado = declaracion.executeQuery();
                if (resultado.next()) {
                    String id = resultado.getString("id");
                    String nombre = resultado.getString("nombre");
                    String puesto = resultado.getString("puesto");
                    int salario = resultado.getInt("salario");

                    // Mostrar los datos del empleado en los campos de edicion
                    idLabel.setText("id: " + id);
                    nombreTextField.setText(nombre);
                    puestoComboBox.setValue(puesto); // Establecer el puesto seleccionado en el ComboBox
                    salarioTextField.setText(String.valueOf(salario));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para guardar los cambios y cerrar la ventana de edición
    @FXML
    private void guardarCambios() {
        // Obtener los nuevos valores de los campos de edición
        String nuevoNombre = nombreTextField.getText();
        String nuevoPuesto = puestoComboBox.getValue(); // Obtener el valor seleccionado del ComboBox
        int nuevoSalario = (int) Long.parseLong(salarioTextField.getText()); // Long para que no de error al llegar a 10 cifras de 9, casteado.

        // Actualizar los datos del empleado en la base de datos
        BDConexion conexionBD = new BDConexion();
        try (Connection conexion = conexionBD.getConexion()) {
            String consulta = "UPDATE empleados SET nombre = ?, puesto = ?, salario = ? WHERE nombre = ?";
            try (PreparedStatement declaracion = conexion.prepareStatement(consulta)) {
                declaracion.setString(1, nuevoNombre);
                declaracion.setString(2, nuevoPuesto);
                declaracion.setInt(3, nuevoSalario);
                declaracion.setString(4, nombreEmpleado); // Usamos el nombre original del empleado como referencia para la actualizacion

                // Ejecutar la consulta de actualizacion
                declaracion.executeUpdate();

                // Mostrar mensaje de exito
                System.out.println("Los cambios se guardaron correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar cualquier error que ocurra durante la actualización de la base de datos
            System.out.println("Error al guardar los cambios en la base de datos: " + e.getMessage());
        }

        // Cerrar la ventana de edicion despues de guardar los cambios
        cerrarVentana();
    }

    // Metodo para cancelar la edicion y cerrar la ventana
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) nombreTextField.getScene().getWindow();
        stage.close();
    }
}