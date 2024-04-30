module com.example.gestorempleadosaaronvasquez {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestorempleadosaaronvasquez to javafx.fxml;
    exports com.example.gestorempleadosaaronvasquez;
}