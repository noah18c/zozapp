module org.zoz {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.zoz to javafx.fxml;
    exports org.zoz;
}
