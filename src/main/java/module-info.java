module com.example.manajemenbuku {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.commons;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.manajemenbuku to javafx.fxml;
    exports com.example.manajemenbuku;
    exports com.example.manajemenbuku.controller;
    opens com.example.manajemenbuku.controller to javafx.fxml;
}