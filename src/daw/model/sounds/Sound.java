package daw.model.sounds;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements ISound {


  public static String PROJECT_DIR = System.getProperty("user.dir");
  public static String RES_DIR = PROJECT_DIR + "\\res";
  public static final Sound QUARTER_REST = new Sound("QtrRest.wav", false);
  private String fullPath;
  // private Clip audioClip = null;

  private boolean on;

  // allows developers to create the sound based on only its file name
  public Sound(String path, boolean absolute) {
    fullPath = "";
    if (!absolute) {
      fullPath += RES_DIR + "\\" + path;
    } else {
      fullPath += path;
    }
  }

  public Sound(String absPath) {
    this(absPath, true);
  }


  @Override
  public void play() {
    // if the sound has been created correctly then play it
//    if (audioClip == null) {
//      throw new IllegalStateException("Can't play a null audio clip, must initialize first");
//      // set up the audio clip to be played
//    }
    try {
      File audioFile = new File(fullPath);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      AudioFormat format = audioStream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip audioClip = (Clip) AudioSystem.getLine(info);
      audioClip.open(audioStream);
      System.out.println("created sound");
      // if we created the sound we set its state to ON
      on = true;
      audioClip.start();
    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
      throw new IllegalArgumentException(
          "Bad file or file path: \"" + fullPath + "\"\n\n" + Arrays
              .toString(e.getStackTrace()));

    }
  }

  @Override
  public void toggle() {
    on = !on;
  }

  @Override
  public void setOn() {
    on = true;
  }

  @Override
  public void setOff() {
    on = false;
    fullPath =  PROJECT_DIR += "QtrRest.wav";
  }

  @Override
  public boolean isOn() {
    return on;
  }

}
