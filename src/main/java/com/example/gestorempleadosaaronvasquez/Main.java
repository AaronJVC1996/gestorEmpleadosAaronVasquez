package com.example.gestorempleadosaaronvasquez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("InsertarEmpleado.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 275);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void mostrarVentanaMensaje(String nombre) {
        // Metodo para la ventana emergente si se logra insertar el empleado.
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Mensaje.fxml"));
            Stage stage = new Stage();
            stage.setTitle("HECHO");
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            MensajeController controller = loader.getController();
            controller.setMensaje(nombre);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}