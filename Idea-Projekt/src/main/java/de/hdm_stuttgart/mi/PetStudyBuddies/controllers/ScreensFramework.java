package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import java.util.HashMap;

public class ScreensFramework {
    public static HashMap<String, String> screens = new HashMap<>();

    public static Runnable init = () -> {
        screens.put("Dashboard", "/fxml/Dashboard/Dashboard.fxml");
        screens.put("", "");
    };

    public static String DashboardID = "Dashboard";
    public static String DashboardFilename = "/fxml/Dashboard/Dashboard.fxml";
    public static String EditNoteID = "Edit Note";
    public static String EditNoteFilename = "/fxml/Notes/EditNote.fxml";
    public static String NotesID = "Note";
    public static String NotesFilename = "/fxml/Notes/Note.fxml";
    public static String AddPetID = "Add Pet";
    public static String AddPetFilename = "/fxml/Pet/AddPet.fxml";
    public static String PetDashboardID = "Add Pet";
    public static String PetDashboardFilename = "/fxml/Pet/PetDashboard.fxml";
    public static String ToDoListAddListID = "Add List";
    public static String ToDoListAddListFilename = "/fxml/ToDoList/ToDoListAddList.fxml";
    public static String ToDoListAddTaskID = "Add Task";
    public static String ToDoListAddTaskFilename = "/fxml/ToDoList/ToDoListAddTask.fxml";
    public static String ToDoListAssignTaskID = "Assign Task";
    public static String ToDoListAssignTaskFilename = "/fxml/ToDoList/ToDoListAssignTask.fxml";
    public static String ToDoListDashboardID = "ToDoList Dashboard";
    public static String ToDoListDashbboardFilename = "/fxml/ToDoList/ToDoListDashboard2.fxml";
    public static String ToDoListModifyTask = "Modify Task";
    public static String ToDoListModifyTaskFilename = "/fxml/ToDoList/ToDoListModifyTask.fxml";
    public static String ToDoListModifyTitleID = "Modify Title";
    public static String ToDoListModifyTitleFilename = "/fxml/ToDoList/ToDoListModifyTitle.fxml";
    public static String ToDoListShareListID = "Share To Do List";
    public static String ToDoListShareListFilename = "/fxml/ToDoList/ToDoListShareToDoList.fxml";
    public static String TaskListID = "View Lists";
    public static String TaskListFilename = "/fxml/ToDoList/TaskList.fxml";
    public static String LoginID = "Login";
    public static String LoginFilename = "/fxml/User/Login.fxml";
    public static String RegisterID = "Register";
    public static String RegisterFilename = "/fxml/User/Register.fxml";
    public static String UserSettingsID = "User Settings";
    public static String UserSettingsFilename = "/fxml/User/UserSettings.fxml";
}
