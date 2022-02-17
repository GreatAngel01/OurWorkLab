module app {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.app to javafx.fxml;
    exports ru.vsu.app;
}