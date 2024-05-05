/** @Author Aaron Vasquez */
package com.example.gestorempleadosaaronvasquez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Crear un TabPane que tendra las pestañas
        TabPane tabPane = new TabPane();

        // Crear la pestaña para insertar empleados
        Tab insercionTab = new Tab("Insertar Empleado");
        insercionTab.setContent(FXMLLoader.load(Main.class.getResource("InsertarEmpleado.fxml")));
        tabPane.getTabs().add(insercionTab);

        // Crear la pestaña para consulta empleados
        Tab consultaTab = new Tab("Consultar Empleados");
        consultaTab.setContent(FXMLLoader.load(Main.class.getResource("ConsultaEmpleado.fxml")));
        tabPane.getTabs().add(consultaTab);

        // Crear un Label para el texto "Gestor de Empleados"
        Label titleLabel = new Label("Gestor de Empleados");
        titleLabel.setStyle("-fx-font-size: 26px;");

        // Crear un VBox que contendrá el Label y el TabPane
        VBox root = new VBox(titleLabel, tabPane);
        root.setAlignment(Pos.CENTER);
        // Establecer la altura minima de las pestañas para permitir espacio para el texto
        tabPane.setTabMinHeight(30);

        // Crear la escena y poner el VBox como el contenido principal
        Scene scene = new Scene(root, 500, 425);

        // Establecer la escena en el escenario principal
        primaryStage.setScene(scene);
        primaryStage.setTitle("AaronVasquezProyectoIndividual");
        primaryStage.show();
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
        launch(args);
    }
}









    /*

    Este codigo era el que tenia al principio hasta que me di cuenta que estaba mal, lo dejo aqui para
    poder ver parte del codigo y reutilizarlo

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

    public static void mostrarVentanaConsulta() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("ConsultaEmpleado.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Consulta de Empleados");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}


     */