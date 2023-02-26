module ms.lab1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ms.lab1 to javafx.fxml;
    exports ms.lab1;
}