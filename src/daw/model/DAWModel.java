package daw.model;

import daw.model.rack.IRack;
import daw.model.rack.Rack;
import daw.model.sounds.ISound;
import daw.model.sounds.Sound;
import daw.model.status.EStatus;
import java.util.Arrays;

public class DAWModel implements IDAWModel {

  private final static long SLEEP_TIME = 300;
  private static final int BPM = 120;

  private final IRack rack = new Rack();
  private EStatus status;

  public DAWModel() {
    status = EStatus.STOP;
  }

  @Override
  public void play() {
    if (status == EStatus.PAUSE ||
        status == EStatus.LOOP_PAUSE ||
        status == EStatus.STOP ||
        status == EStatus.LOOP_STOP) {
      status = EStatus.PLAY;

      for (ISound s : rack.getSounds()) {
        s.play();
        try {
          Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
          throw new IllegalStateException("Thread interrupted\n\n" + Arrays
              .toString(e.getStackTrace()));
        }
      }
    }
    status = EStatus.STOP;
  }

  @Override
  public void play(boolean loop) {
    if (status == EStatus.PAUSE ||
        status == EStatus.LOOP_PAUSE ||
        status == EStatus.STOP ||
        status == EStatus.LOOP_STOP) {
      status = EStatus.LOOP_PLAY;
      while (status == EStatus.LOOP_PLAY) {
        play();
        try {
          Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
          throw new IllegalStateException("Thread interrupted\n\n" + Arrays
              .toString(e.getStackTrace()));
        }
      }
    }
  }

  @Override
  public void pause() {

  }

  @Override
  public void stop() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void jumpTo(int beatIdx) throws IllegalArgumentException {

  }

  @Override
  public void jumpTo(long msFromStart) {

  }

  @Override
  public void toggleBeat(int beatIdx) throws IllegalArgumentException {

  }

  @Override
  public void beatOff(int beatIdx) throws IllegalArgumentException {

  }

  @Override
  public void beatOn(int beatIdx) throws IllegalArgumentException {

  }

  @Override
  public void delSound(int beatIdx) throws IllegalArgumentException {

  }

  @Override
  public void addSound(int beatIdx, ISound sound) throws IllegalArgumentException {

  }

  @Override
  public void delAll() {

  }

  @Override
  public IRack getRack() {
    return rack;
  }
}
