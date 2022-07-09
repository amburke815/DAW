package daw.view.commands;

import daw.Utils;
import daw.model.IDAWModel;

public class PlayRackCommand implements IGUICommand {
  private IDAWModel mdl;

  public PlayRackCommand(IDAWModel mdl) {
    this.mdl = Utils.notNull(mdl);
  }

  public PlayRackCommand() {
    mdl.play();
  }

  @Override
  public void execute() {
    mdl.play();
  }
}
