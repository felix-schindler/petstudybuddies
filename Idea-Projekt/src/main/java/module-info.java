module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires sqlite.jdbc;
    requires java.sql.rowset;
    requires java.desktop;

    opens de.hdm_stuttgart.mi.PetStudyBuddies to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Core to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Core;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Controller to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Controller;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Models to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Models;
}
