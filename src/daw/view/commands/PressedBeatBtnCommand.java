package daw.view.commands;

import daw.Utils;
import daw.model.sounds.ISound;
import java.awt.Color;
import javax.swing.JButton;

public class PressedBeatBtnCommand implements IGUICommand {

  private ISound sound;
  private JButton btn;

  public PressedBeatBtnCommand(ISound sound, JButton btn) {
    this.sound = Utils.notNull(sound);
    this.btn = Utils.notNull(btn);
  }

  @Override
  public void execute() {
    if (sound.isOn()) {
      // sound.play();
      btn.setBackground(Color.black);
      sound.toggle();
    }
    else {
      btn.setBackground(Color.white);
      sound.toggle();
    }
  }
}
