module com.example.assignment42_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment42_final to javafx.fxml;
    exports com.example.assignment42_final;
}