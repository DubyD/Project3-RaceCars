//Author WD

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

        this.menuSetter = new MenuGui();
        this.gameBoard = new GameScreen();

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

        this.menuPanel = new JPanel();
        this.menuPanel.setLayout(new BorderLayout());
        this.menuPanel.add(new JLabel("Select a Puzzle size, then press 'Start Puzzle'!\n" +
                "Match the people to their attributes, then press 'check answers'\n"), BorderLayout.NORTH);
        this.menuPanel.add(menuSetter, BorderLayout.CENTER);

        this.frame.setContentPane(menuPanel);
        this.frame.setVisible(true);
    }

    private void showGame(String size, String racersNum) {
        GameScreen gameBoard = new GameScreen(Integer.parseInt(size), Integer.parseInt(racersNum));

        gameBoard.getEndButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameBoard.getFinished()){
                    showMenu();
                    menuSetter.getResults().setText(gameBoard.getResults());
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