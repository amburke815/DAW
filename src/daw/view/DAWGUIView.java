package daw.view;

import daw.model.DAWModel;
import daw.model.IDAWModel;
import daw.model.datastructs.Pair;
import daw.model.sounds.ISound;
import daw.model.sounds.Sound;
import daw.view.commands.IGUICommand;
import daw.view.commands.PlayRackCommand;
import daw.view.commands.PressedBeatBtnCommand;
import daw.view.editor.IEditorTool;
import daw.view.editor.SoundEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.EventListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

public class DAWGUIView extends JFrame
    implements IDAWView, ActionListener, EventListener, ItemListener {

  // the model
  private final IDAWModel mdl = new DAWModel();

  // current editor tool
  private IEditorTool currentTool;
  private final Map<String, IEditorTool> rackEditorsMap = initRackEditorsMap();

  // the dimensions of a typical computer screen
  private static final int SCREEN_WIDTH = 1200;
  private static final int SCREEN_HEIGHT = 900;
  private static final JButton offBeatBlock = new JButton();
  private static final JButton onBeatBlock = new JButton();
  private static final JButton currentBeatBlock = new JButton();
  private static final JPanel controlsPanel = new JPanel();

  // for generalization
  int rackWidth = 4;
  int rackHeight = 4; // inv var relationship w/ numBeats


  // top-level gui elements
  private JPanel mainPanel = new JPanel();
  private JPanel rackPanel = new JPanel();

  private JButton testBtn = new JButton();
  private JPanel rackEditorsPanel = new JPanel();

  // hashmap for commands
  private final Map<String, IGUICommand> commandsMap = initCommandsMap();




  public DAWGUIView() {
    super();
    setTitle("My DAW");
    setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
    currentTool = new SoundEditor();

    mainPanel.setLayout(new BorderLayout());

    // UI setups here
    initRackGUIElements();
    initControlsPanel();
    //initRackEditorsPanel();
    // initMenuRibbonGUIElements();
    // initButtonGUIElements();

    // finally, add the panel
    add(mainPanel, BorderLayout.EAST);

  }

  private void initRackEditorsPanel() {
    addBtn("toggle mode", "toggle mode", rackEditorsPanel);
    addBtn("edit mode", "edit mode", rackEditorsPanel);
    addBtn("soundboard mode", "soundboard mode", rackEditorsPanel);

    mainPanel.add(rackEditorsPanel);
  }

  private void initRackGUIElements() {
    rackPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JPanel rackLyr0 = new JPanel();
    JPanel rackLyr1 = new JPanel();
    JPanel rackLyr2 = new JPanel();
    JPanel rackLyr3 = new JPanel();

    Map<JPanel, List<Pair<ISound, JButton>>> rackGUIElements = new HashMap<>();

    // generalize to time signature a/b --> remove 4/4 below
    for (int i = 0; i < rackWidth; i++) {
      List<Pair<ISound, JButton>> thisLayerBtns = new ArrayList<>();
      JPanel thisLayerPanel = new JPanel();
      for (int j = 0; j < rackHeight; j++) {
        JButton thisBeatBtn = new JButton();
        thisBeatBtn.addActionListener(this);
        String beatBtnID = "beat btn " + logicalBinaryIdxToLogicalUnaryIndex(i,j);
        thisBeatBtn.setActionCommand(beatBtnID);
        ISound thisSound = mdl.getRack().getSounds()[logicalBinaryIdxToLogicalUnaryIndex(i,j)];
        commandsMap.put(beatBtnID, new PressedBeatBtnCommand(thisSound, thisBeatBtn));
        thisBeatBtn.setText("" + (logicalBinaryIdxToLogicalUnaryIndex(i,j) + 1));
        thisLayerBtns.add(new Pair<>(Sound.QUARTER_REST, thisBeatBtn));
        thisLayerPanel.add(thisBeatBtn);
      }

      rackGUIElements.putIfAbsent(thisLayerPanel, thisLayerBtns);
      c.gridy = i;
      rackPanel.add(thisLayerPanel, c);
    }

    testBtn.addActionListener(this);
    testBtn.setActionCommand("test btn");
    rackPanel
        .setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED, Color.RED, Color.CYAN));


    mainPanel.add(rackPanel);

  }

  private void initControlsPanel() {
    controlsPanel.setLayout(new BorderLayout());
    controlsPanel.setBorder(BorderFactory.createEtchedBorder(Color.CYAN, Color.GREEN));
    JButton playRackBtn = new JButton();
    playRackBtn.setText("▶");
    playRackBtn.addActionListener(this);
    playRackBtn.setActionCommand("play rack");
    controlsPanel.add(playRackBtn);
    mainPanel.add(controlsPanel, BorderLayout.WEST);
  }



  private Map<String, IGUICommand> initCommandsMap() {
    Map<String, IGUICommand> commandsMap = new HashMap<>();

    commandsMap.putIfAbsent("play rack", new PlayRackCommand(mdl));
    return commandsMap;
  }

  private Map<String, IEditorTool> initRackEditorsMap() {
    Map<String, IEditorTool> editorsMap = new HashMap<>();

    editorsMap.putIfAbsent("editor mode", new SoundEditor());
    //rackEditorsMap.putIfAbsent("soundboard mode", new SoundBoardMode());
    //rackEditorsMap.putIfAbsent("toggle mode", new ToggleMode());

    return editorsMap;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (commandsMap.containsKey(e.getActionCommand())) { // command pattern
      commandsMap.get(e.getActionCommand()).execute();
    }

    if (rackEditorsMap.containsKey(e.getActionCommand())) {
      rackEditorsMap.get(e.getActionCommand()).clickAction();
    }
  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  private static int logicalBinaryIdxToLogicalUnaryIndex(int i, int j) {
    return i * 4 + j;
  }

  private void addBtn(String label, String actionCommandName, Container parent) {
    JButton newBtn = new JButton();
    newBtn.setText(label);
    newBtn.addActionListener(this);
    newBtn.setActionCommand(actionCommandName);
    parent.add(newBtn);
  }
}
