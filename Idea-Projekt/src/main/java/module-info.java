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
    opens de.hdm_stuttgart.mi.PetStudyBuddies.core to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.core;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.controllers to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.controllers;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.models to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.models;
}
