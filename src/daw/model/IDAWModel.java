package daw.model;

import daw.model.rack.IRack;
import daw.model.sounds.ISound;

public interface IDAWModel {

  void play(); // play without loop

  void play(boolean loop); // play with optional loop

  void pause();

  void stop();

  void restart();

  void jumpTo(int beatIdx)
      throws IllegalArgumentException; // if given bad beat index;

  void jumpTo(long msFromStart);

  void toggleBeat(int beatIdx)
      throws IllegalArgumentException; // if given bad beat index;

  void beatOff(int beatIdx)
      throws IllegalArgumentException; // if given bad beat index;

  void beatOn(int beatIdx)
      throws IllegalArgumentException; // if given bad beat index

  void delSound(int beatIdx)
      throws IllegalArgumentException; // if given bad beat index;

  void addSound(int beatIdx, ISound sound)
    throws IllegalArgumentException; // if given bad beat index

  void delAll();

  IRack getRack();
}
