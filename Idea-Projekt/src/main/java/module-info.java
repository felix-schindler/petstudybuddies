module gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;

    opens de.hdm_stuttgart.mi.PetStudyBuddies to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Controller to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Controller;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Views to javafx.fxml;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Views;
    exports de.hdm_stuttgart.mi.PetStudyBuddies.Views.Components;
    opens de.hdm_stuttgart.mi.PetStudyBuddies.Views.Components to javafx.fxml;
}
