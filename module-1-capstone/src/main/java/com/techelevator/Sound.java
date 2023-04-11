package com.techelevator;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface Sound {
    public void sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
}
