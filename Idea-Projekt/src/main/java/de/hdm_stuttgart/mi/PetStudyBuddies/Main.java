package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Account;
import de.hdm_stuttgart.mi.PetStudyBuddies.Core.User.Auth;

import java.util.Scanner;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) {
        if (Account.getLoggedUser() == null) {
            Scanner scan = new Scanner(System.in);

            System.out.println("1. Login\n2. Register\n3. End");
            final int menuChoose = scan.nextInt();

            switch (menuChoose) {
                case 1 -> {
                    System.out.println("==========\nLogin\n==========");
                    System.out.println("EMail eingeben:");
                    final String email = scan.next();
                    System.out.println("Passwort eingeben:");
                    final String password = scan.next();
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
    }
}
