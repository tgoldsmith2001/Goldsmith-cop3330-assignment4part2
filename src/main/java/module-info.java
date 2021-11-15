module com.example.assignment42_final {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucf.assignments to javafx.fxml;
    exports ucf.assignments;
}