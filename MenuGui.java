//Author WD
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuGui extends JFrame {
    private JPanel menuOption;
    private JLabel instructions;
    private JPanel linedUp;
    private JLabel replay;
    private JButton startButton;
    private JComboBox<String> sizeSelection;

    public MenuGui() {
        setTitle("Menu GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Setting up the scene to be exported
        this.menuOption = new JPanel();
        this.menuOption.setLayout(new BoxLayout(menuOption, BoxLayout.Y_AXIS));
        this.menuOption.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instructions for starting the game
        this.instructions = new JLabel("Select a Game Size. your number will be converted"+
                                       "into a square. ex(4 = 4x4)");
        this.instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Setting up the Child to add to the Scene export
        this.linedUp = new JPanel();
        this.linedUp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // Grandchildren
        this.startButton = new JButton("Start Your Engines");

        // Sets up options
        this.sizeSelection = new JComboBox<>();
        dropBoxOptions();

        // If user Won this Label Appears
        this.replay = new JLabel();
        this.replay.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding Grandchildren
        this.linedUp.add(sizeSelection);
        this.linedUp.add(startButton);

        // Adding middle children
        this.menuOption.add(instructions);
        this.menuOption.add(linedUp);
        this.menuOption.add(replay);

        add(this.menuOption, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private void dropBoxOptions() {
        // Setting up the DropBox options

        for(int i = 9; i  > 3; i --){
            String x = String.valueOf(i);
            this.sizeSelection.addItem(x);
        }
    }

    public String getSelectedItem() {
        return (String) sizeSelection.getSelectedItem();
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JLabel getReplay() {
        return replay;
    }

    @Override
    public String toString() {
        return "This is a Main Menu for the Game";
    }
}
