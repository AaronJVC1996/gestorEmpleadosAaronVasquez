package com.example.gestorempleadosaaronvasquez;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MensajeController {
    @FXML
    private Label mensajeLabel;

    // Este es el mesaje que se mostrara al hacer click en insertar en el gestor empleados
    public void setMensaje(String nombre) {
        mensajeLabel.setText("Mensaje\n\n\nEmpleado " + nombre + " introducido en la base de datos \nsatisfactoriamente.");
    }

    // Tambien se cerrara la ventana con el mensaje al darle click en aceptar
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) mensajeLabel.getScene().getWindow();
        stage.close();
    }
}
//todo ver la separacion del mensaje con su derecha y ver la separacion del boton aceptar con su derecha y por abajo
// ver como colocar el simbolo imagen de ! en azul, me quede al final de la pagina 2 del pdf