//Author WD

import javax.swing.*;
import java.awt.*;

    //Makes a neat display of the game
public class GameScreen extends JPanel {

    private GameGrid gameGrid;
    private JButton endGameButton;

    public GameScreen(int size, int racers) {

        this.setLayout(new BorderLayout());

            // Create GameGrid
        this.gameGrid = new GameGrid(size, racers);
        this.add(this.gameGrid, BorderLayout.CENTER);

            // Create JButton
        this.endGameButton = new JButton("End Game");

        // Creates a space for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(endGameButton);

        // Add buttonPanel to the bottom of the frame
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    public JButton getEndButton(){
        return this.endGameButton;
    }

    public GameScreen(){
        this.gameGrid = null;
    }


    public City getGotham(){
        return this.gameGrid.getGotham();
    }

    public boolean getFinished(){
        return this.gameGrid.getFinished();
    }

}
