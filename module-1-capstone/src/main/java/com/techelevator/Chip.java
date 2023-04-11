package com.techelevator;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Chip extends Snacks implements Sound{
    public Chip(String location, String name, double price, String type) {
        super(location, name, price, type);

    }

    @Override
    public String printOut() {
        return "Crunch Crunch, Yum!";
    }

    public void sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);

        File soundFile = new File("Crunch.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();

        String response = scanner.next();
        while (response.equals(getSnackType())) {
            clip.start();
        }
    }
}
