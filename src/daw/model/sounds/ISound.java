package daw.model.sounds;

// function object for sounds. allows lambda construction of form [new SoundImpl(path) -> play()]
public interface ISound {

  void play();

  void toggle();

  void setOn();

  void setOff();

  boolean isOn();
}
