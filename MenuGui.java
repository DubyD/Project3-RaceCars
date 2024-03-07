//Author WD


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class MenuGui {

    // Scene to export
    private VBox menuOption;

    // Instructions for User
    private Label instructions;

    // Middle child holding the next 2 variables
    private HBox linedUp;
    private Label replay;

    // The Grand Children
    private Button startButton;
    private ComboBox puzzleSelection;

    public MenuGui(){

        // Setting up the scene to be exported
        this.menuOption = new VBox();
        this.menuOption.setAlignment(Pos.CENTER);

        // Instructions for starting the game
        this.instructions = new Label("Select a Puzzle size, then press 'Start Puzzle'!\n"+
                "Match the people to their attributes, then press 'check answers'\n");

        // Setting up the Child to add to the Scene export
        this.linedUp = new HBox( 10);
        this.linedUp.setAlignment(Pos.CENTER);

        // Grandchildren
        this.startButton = new Button("Start Puzzle");

        // Sets up options
        this.puzzleSelection = new ComboBox<String>();
        this.dropBoxOptions();
        //If user Won this Label Appears
        this.replay = new Label();

        //adding Grandchildren
        this.linedUp.getChildren().add(this.puzzleSelection);
        this.linedUp.getChildren().add(this.startButton);

        //adding middle children
        this.menuOption.getChildren().add(this.instructions);
        this.menuOption.getChildren().add(this.linedUp);
        this.menuOption.getChildren().add(this.replay);

    }

    private void dropBoxOptions(){

        //Setting up the DropBox options
        ObservableList<String> options = FXCollections.observableArrayList();

        //PuzzleReader static method to retrieve all sizes from .csv file
        ArrayList<String> puzzleSizes = PuzzleReader.getAllSizes();

        //Loops through the List until it is empty
        //Sizes are number of matching catagories x items in catagories
        while(puzzleSizes.isEmpty() == false){
            String x = puzzleSizes.get(0);
            options.add(x);
            puzzleSizes.remove(x);
        }
        this.puzzleSelection.setItems(options);
    }

    //I know Casting is gross but it wouldn't let me compile without it
    public String getSelectedItem(){
        return (String) this.puzzleSelection.getValue();
    }

    //Sends a Button Pointer to apply a Lambda function to the Menu's button
    public Button getStartButton(){
        return this.startButton;
    }

    //Sends the Menu Gui to be added to the Stage
    public VBox getMenu(){
        return this.menuOption;
    }

    public Label getReplay(){
        return this.replay;
    }

    @Override
    public String toString(){
        String reply = "This is a Main Menu for the Game";
        return reply;
    }

}
