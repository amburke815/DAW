package daw.model.rack;

import daw.model.sounds.ISound;

public interface IRack {

  // get the sounds array in the rack
  ISound[] getSounds();

  void toggleBeat(int beatIdx)
      throws IllegalArgumentException;

  void beatOff(int beatIdx)
    throws IllegalArgumentException;

  void beatOn(int beatIdx)
    throws IllegalArgumentException;

  void addSound(int beatIdx, ISound sound)
    throws IllegalArgumentException;

  void delSound(int beatIdx)
    throws IllegalArgumentException;

  void delAll();
}
