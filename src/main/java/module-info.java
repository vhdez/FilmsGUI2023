module com.example.filmsgui2023 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.scienceleadership.filmsgui2023 to javafx.fxml;
    exports org.scienceleadership.filmsgui2023;
}