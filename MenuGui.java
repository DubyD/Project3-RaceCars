//Author WD
import javax.swing.*;
import java.awt.*;


    //Openning Screen of the Game
public class MenuGui extends JPanel {
    private JPanel menuOption;
    private JLabel instructions;
    private JPanel linedUp;
    private JLabel size;
    private JLabel numOfRacers;
    private JLabel replay;
    private JLabel results;
    private JButton startButton;
    private JComboBox<String> sizeSelection;
    private JComboBox<String> racerSelection;

    public MenuGui(){
        setLayout(new BorderLayout());

        // Setting up the scene to be exported
        this.menuOption = new JPanel();
        this.menuOption.setLayout(new BoxLayout(menuOption, BoxLayout.Y_AXIS));
        this.menuOption.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Instructions for starting the game
        this.instructions = new JLabel("Select a number of racers and Game Size your number will be converted"+
                                       "into a square. ex(4 = 4x4)");

        this.instructions.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Setting up the Child to line up options
        this.linedUp = new JPanel();
        this.linedUp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

            // Grandchildren
        this.startButton = new JButton("Start Your Engines");

            //Sets dropbox labels
        this.size = new JLabel("Game Size:");
        this.numOfRacers = new JLabel("Number of Racers:");

            // Sets up options dropbox method below constructor
        this.sizeSelection = new JComboBox<>();
        this.racerSelection = new JComboBox<>();
        sizeDropBoxOptions();
        racersDropBoxOptions();

            // If user played these Labels Appear
        this.results = new JLabel();
        this.results.setAlignmentX(Component.CENTER_ALIGNMENT);


            // Adding Grandchildren
        this.linedUp.add(this.size);
        this.linedUp.add(sizeSelection);
        this.linedUp.add(this.numOfRacers);
        this.linedUp.add(racerSelection);

        // Adding middle children
        this.menuOption.add(instructions);
        this.menuOption.add(linedUp);
        this.menuOption.add(startButton);
        this.menuOption.add(results);

        this.add(this.menuOption, BorderLayout.CENTER);
    }

    private void sizeDropBoxOptions() {
        // Setting up the DropBox options

        for(int i = 5; i  < 21; i++){
            String x = String.valueOf(i);
            this.sizeSelection.addItem(x);
        }
    }

    private void racersDropBoxOptions(){

        for(int i = 1; i < 4; i++){
            String x = String.valueOf(i);
            this.racerSelection.addItem(x);
        }
    }

    public String getSelectedSizeItem() {
        return (String) sizeSelection.getSelectedItem();
    }

    public String getSelectedRacingItem(){
        return (String) racerSelection.getSelectedItem();
    }

    public JButton getStartButton() {
        return this.startButton;
    }

    public JLabel getResults() {
        return this.results;
    }

    @Override
    public String toString() {
        return "This is a Main Menu for the Game";
    }
}
