module com.example.ass1data {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires xstream;

    opens com.example.ass1data to javafx.fxml;
    exports com.example.ass1data;
    exports Controllers;
    opens Controllers to javafx.fxml;
    exports Objects;
    opens Objects to javafx.fxml, xstream;
    exports LinkedList;
    opens LinkedList to javafx.fxml, xstream;

}