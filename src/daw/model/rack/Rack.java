package daw.model.rack;

import daw.Utils;
import daw.model.sounds.ISound;
import daw.model.sounds.Sound;

public class Rack implements IRack {

  private static final int STD_RACK_SIZE = 16;

  private ISound[] sounds;
  private int numBeats;

  // make an "empty" 16-beat rack
  public Rack() {
    numBeats = STD_RACK_SIZE;
    sounds = new ISound[numBeats];

    for (int i = 0; i < numBeats; i++) {
      sounds[i] = new Sound("hihat_1.wav", false); //Sound.QUARTER_REST;
    }

    // to delete
//    for (int i = 0; i < numBeats; i += 4) {
//      sounds[i] = new Sound("808_1.wav", false);
//    }
//
//    for (int i = 3; i < numBeats; i += 4) {
//      sounds[i] = new Sound("hihat_1.wav", false);
//    }
  }

  @Override
  public ISound[] getSounds() {
    return sounds;
  }

  @Override
  public void toggleBeat(int beatIdx) throws IllegalArgumentException {
    safeSelect(beatIdx).toggle();
  }

  @Override
  public void beatOff(int beatIdx) throws IllegalArgumentException {
    safeSelect(beatIdx).setOff();
  }

  @Override
  public void beatOn(int beatIdx) throws IllegalArgumentException {
    safeSelect(beatIdx).setOn();
  }

  @Override
  public void addSound(int beatIdx, ISound sound) throws IllegalArgumentException {
    sounds[Utils.intBetween(0, beatIdx, numBeats -1)] =
        Utils.notNull(sound);
  }

  @Override
  public void delSound(int beatIdx) throws IllegalArgumentException { // deleting is identical to
    // setting to off
    sounds[Utils.intBetween(0,beatIdx,numBeats-1)].setOff();
  }

  @Override
  public void delAll() { // deleting is identical to setting to off
    for (int i = 0; i < numBeats; i++) {
      sounds[i].setOff();
    }
  }

  private ISound safeSelect(int beatIdx)
      throws IllegalArgumentException {
    return sounds[Utils.intBetween(0, beatIdx, numBeats - 1)];
  }
}
