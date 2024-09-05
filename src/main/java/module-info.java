module org.zoz {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.zoz to javafx.fxml;
    exports org.zoz;
}
