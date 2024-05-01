module com.example.gestorempleadosaaronvasquez {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gestorempleadosaaronvasquez to javafx.fxml;
    exports com.example.gestorempleadosaaronvasquez;
}