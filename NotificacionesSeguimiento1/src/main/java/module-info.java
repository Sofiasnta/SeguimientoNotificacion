module co.edu.uniquindio.poo.notificacionesseguimiento1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.notificacionesseguimiento1 to javafx.fxml;
    exports co.edu.uniquindio.poo.notificacionesseguimiento1;




    //  exports co.edu.uniquindio.poo.notificacionesactividad.controller;
    //opens co.edu.uniquindio.poo.notificacionesactividad.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.notificacionesseguimiento1.viewController;
    opens co.edu.uniquindio.poo.notificacionesseguimiento1.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.notificacionesseguimiento1.model;
    opens co.edu.uniquindio.poo.notificacionesseguimiento1.model to javafx.fxml;
}