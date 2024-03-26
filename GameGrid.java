//Author WD

import javax.swing.*;
import java.awt.*;

    //Separates the Game to tidy up the GameScreen
public class GameGrid extends JPanel {


    private JLabel[][] labels;

    private int size;
    private City gotham;
    private TurnTaker timeWarp;

        //Iterates through the city to apply relevant components
    public GameGrid(int size, int racers) {
            //Makes it pretty
        this.setLayout(new BorderLayout());



            //Sets the size and city
        this.gotham = new City(size, racers); //uses the city to set text in labels
        this.size = size; //saves size in an easy to access variable
        this.timeWarp = new TurnTaker(this.gotham, this);

            //Gui representation of city
        labels = new JLabel[size][size];
        JPanel gridPanel = new JPanel(new GridLayout(size, size));

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                    //Label creation
                JLabel label = new JLabel();
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(60, 20));

                    //adding Label to Grid
                labels[row][col] = label;
                gridPanel.add(label);
            }
        }

            //Adds the Grid and Updates user view
        this.add(gridPanel, BorderLayout.CENTER);
        this.updateGrid();


    }
    public GameGrid(){
        this.gotham = null;
        this.labels = null;
        this.size = -1;
    }


        //Used to update Labels in TurnTaker
    private void updateGrid() {


            //Clears all Labels
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                labels[row][col].setText("");
            }
        }

            // Set text for occupied positions, reLabel
        for (GamePiece next : this.gotham.getBoard()) {
            if(labels[next.getX()][next.getY()].getText().equals("")) {
                labels[next.getX()][next.getY()].setText(next.toString());
            }else{
                String temp = labels[next.getX()][next.getY()].getText();
                temp = temp + next.toString();
                labels[next.getX()][next.getY()].setText(temp);
            }
        }

    }

        //Used to update after cars move
    public void updateLabels() {
        updateGrid();
    }

    public boolean getFinished(){
        return this.gotham.getFinished();
    }

    public City getGotham(){
        return this.gotham;
    }
}
