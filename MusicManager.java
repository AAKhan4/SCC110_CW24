import java.io.File;
import javax.sound.sampled.*;

public class MusicManager {

    //Following instance variables define the MusicManager class
    private boolean mute; //boolean whether the sound should be muted
    private Clip clip; //holds audio data for sound effect
    private AudioInputStream ais; // input stream

    /**
     * Constructor - sets mute as false by default
     */
    public MusicManager() {
        mute = false;
    }

    /**
     * Gets the current status of this object
     * 
     * @return whether this object is muted or not
     */
    public boolean getStatus() {
        return mute;
    }

    /**
     * Changes the status of this object
     * 
     * @param mute boolean stating whether the object should be muted or unmuted
     */
    public void setStatus(boolean mute) {
        this.mute = mute;
    }

    /**
     * Plays audio from a .wav file given to this function
     * 
     * @param filePath relative path of the file to be played
     */
    public void play(String filePath) {
        if (mute) return;
        try {
            File f = new File(filePath);

            ais = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            System.out.println("Unsupported Audio File");
        }
    }
}
