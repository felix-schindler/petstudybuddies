package de.hdm_stuttgart.mi.PetStudyBuddies.core;

import java.util.HashMap;

public class ScreensFramework {
    public static HashMap<String, String> screens = new HashMap<>();

    public static Runnable init = () -> {
        screens.put("Dashboard", "/fxml/Dashboard/Dashboard.fxml");
        screens.put("Edit Note", "/fxml/Notes/EditNote.fxml");
        screens.put("Note", "/fxml/Notes/Notes.fxml");
        screens.put("Add Pet", "/fxml/Pet/AddPet.fxml");
        screens.put("Change Petname", "/fxml/Pet/ChangePetname.fxml");
        screens.put("Pet", "/fxml/Pet/PetDashboard.fxml");
        screens.put("Add List", "/fxml/ToDoList/ToDoListAddList.fxml");
        screens.put("Add Task", "/fxml/ToDoList/ToDoListAddTask.fxml");
        screens.put("Assign Task", "/fxml/ToDoList/ToDoListAssignTask.fxml");
        screens.put("ToDoList Dashboard", "/fxml/ToDoList/ToDoListDashboard2.fxml");
        screens.put("Modify Task", "/fxml/ToDoList/ToDoListModifyTask.fxml");
        screens.put("Modify Title", "/fxml/ToDoList/ToDoListModifyTitle.fxml");
        screens.put("Share To Do List", "/fxml/ToDoList/ToDoListShare.fxml");
        screens.put("Lists", "/fxml/ToDoList/TaskList.fxml");
        screens.put("Login", "/fxml/User/Login.fxml");
        screens.put("Register", "/fxml/User/Register.fxml");
        screens.put("User Settings", "/fxml/User/UserSettings.fxml");
    };

}
