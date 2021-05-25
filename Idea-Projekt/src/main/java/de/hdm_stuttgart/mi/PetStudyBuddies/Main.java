package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.InsertQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.DB.SelectQuery;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;

import java.util.Scanner;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) {
        int menu = 0;
        while (Account.getLoggedUser() == null) {
            Scanner scan = new Scanner(System.in);

            System.out.println("1. Login\n2. Register\n3. End");
            menu = scan.nextInt();

            switch (menu) {
                case 1 -> {
                    System.out.println("==========\nLogin\n==========");
                    String email = "fs146@hdm-stuttgart.de", password = "test"; // Makes testing easier
                    /* System.out.println("EMail eingeben:");
                    final String email = scan.next();
                    System.out.println("Passwort eingeben:");
                    final String password = scan.next(); */

                    Account.setUser(Auth.login(email, password));

                    if (Account.getLoggedUser() != null) {
                        System.out.println("Login war erfolgreich.");
                    } else {
                        System.out.println("EMail oder Password war falsch.");
                    }
                }
                case 2 -> {
                    System.out.println("==========\nRegister\n==========");
                    System.out.println("EMail eingeben:");
                    final String email = scan.next();
                    System.out.println("Username eingeben:");
                    final String uname = scan.next();
                    System.out.println("Passwort eingeben:");
                    final String pass = scan.next();
                    if (Auth.register(email, uname, pass))
                        System.out.println("Registrierung war erfolgreich.");
                    else
                        System.out.println("Registrierung war nicht erfolgreich.");
                }
                case 3 -> System.out.println("End");
                default -> System.out.println("Invalid input, please fix your attitude");
            }
        }

        while (Account.getLoggedUser() != null && menu != 4) {
            Scanner scan = new Scanner(System.in);
            System.out.flush();
            System.out.println("Welcome " + Account.getLoggedUser().getUsername());
            System.out.println("1. Notizen anzeigen\n2. Notiz erstellen\n3. Notiz bearbeiten\n4. Ende");
            System.out.print("Deine Wahl: ");
            menu = scan.nextInt();

            switch (menu) {
                case 1 ->
                        Utils.printResultSet(new SelectQuery("Note", "*", "UserID=" + Account.getLoggedUser().getID()).fetchAll());
                case 2 -> {
                    System.out.print("Choose a title: ");
                    final String title = scan.next();
                    System.out.print("Write content: ");
                    final String content = scan.next();

                    if (new InsertQuery("Note", new String[]{"UserID", "Title", "Content"}, new String[]{String.valueOf(Account.getLoggedUser().getID()), title, content}).Count() == 1) {
                        System.out.println("New note created");
                    } else {
                        System.err.println("Note could not be created");
                    }
                }
                case 4 -> System.out.println("Ok, dann nicht.");
                default -> System.err.println("Oh no");
            }
        }
    }
}
