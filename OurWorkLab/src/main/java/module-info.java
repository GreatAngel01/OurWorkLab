module app {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.vsu.app to javafx.fxml;
    exports ru.vsu.app;
    exports ru.vsu.app.visual;
    opens ru.vsu.app.visual to javafx.fxml;
}