package de.hdm_stuttgart.mi.PetStudyBuddies;

import de.hdm_stuttgart.mi.PetStudyBuddies.Core.Utils;

/**
 * For now just a class for testing
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(Utils.sha1("Felix"));
        System.out.println(Utils.sha1("felix"));
        System.out.println(Utils.sha1("abc"));
    }
}
