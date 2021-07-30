package de.hdm_stuttgart.mi.PetStudyBuddies.controllers;

import java.util.HashMap;
/**
 * Framework containing Picture resources
 */
public class PictureFramework {
    /**
     * HashMap containg Keywords for pictures
     */
    public static HashMap<String, String> pictures = new HashMap<>();

    public static Runnable init = () -> {
        pictures.put("Sad", "file:src/main/resources/images/Pet/iu-14.jpg");
        pictures.put("Happy", "file:src/main/resources/images/Pet/iu.jpeg");
        pictures.put("Content", "file:src/main/resources/images/Pet/iu-6.jpeg");
        pictures.put("No Pet", "file:src/main/resources/images/Pet/iu-7.jpeg");
    };
}
