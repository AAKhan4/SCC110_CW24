import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class MusicManager {
    private boolean mute;
    private Clip clip;
    private AudioInputStream ais;

    public MusicManager() {
        mute = false;
    }

    public boolean getStatus() {
        return mute;
    }

    public void setStatus(boolean mute) {
        this.mute = mute;
    }

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
