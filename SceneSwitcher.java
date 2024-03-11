//Author WD

import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    //Switches between the Openning menu and the Game Screen
public class SceneSwitcher {

    private JFrame frame;

    private JPanel menuPanel;
    private JPanel gamePanel;

    private MenuGui menuSetter;
    private GameScreen gameBoard;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;

            //Sets up the Content of the two screens
        this.menuSetter = new MenuGui();
        this.gameBoard = new GameScreen();

            //adds a Lambda function to the start button
        this.menuSetter.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuSetter.getSelectedSizeItem() != null ) {
                    if(menuSetter.getSelectedRacingItem() != null) {
                        showGame(menuSetter.getSelectedSizeItem(), menuSetter.getSelectedRacingItem());
                    }
                }
            }
        });

            //Sets up the Menu Screen
        this.menuPanel = new JPanel();
        this.menuPanel.add(menuSetter, BorderLayout.CENTER);

            //Initiates the menu first
        this.frame.setContentPane(menuPanel);
        this.frame.setVisible(true);
    }

        //non parameter constructor to complete class
    public SceneSwitcher(){
        this.menuPanel = null;
        this.frame = null;
        this.gameBoard = null;
        this.gamePanel = null;
        this.menuSetter = null;
    }

    private void showGame(String size, String racersNum) {
        GameScreen gameBoard = new GameScreen(Integer.parseInt(size), Integer.parseInt(racersNum));

        gameBoard.getEndButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameBoard.getFinished()){
                    menuSetter.setResults(gameBoard.getGotham().getResults());
                    showMenu();

                }
            }
        });


        this.gamePanel = new JPanel();
        this.gamePanel.setLayout(new BorderLayout());
        this.gamePanel.add(gameBoard, BorderLayout.CENTER);

        this.frame.setContentPane(gamePanel);
        this.frame.revalidate();
    }

    private void showMenu() {
        this.frame.setContentPane(this.menuPanel);
        this.frame.revalidate();
    }
}