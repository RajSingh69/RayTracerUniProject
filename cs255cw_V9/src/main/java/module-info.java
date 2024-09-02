module com.example.cs255cwv3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs255cwv3 to javafx.fxml;
    exports com.example.cs255cwv3;
}