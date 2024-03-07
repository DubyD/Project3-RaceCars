import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SceneSwitcher {

    private JFrame frame;

    private JPanel menuPanel;
    private JPanel gamePanel;

    private MenuGui menuSetter;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;

        this.menuSetter = new MenuGui();

        this.menuSetter.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuSetter.getSelectedItem() != null) {
                    showGame(menuSetter.getSelectedItem());
                }
            }
        });

        this.menuPanel = new JPanel();
        this.menuPanel.setLayout(new BorderLayout());
        this.menuPanel.add(new JLabel("Select a Puzzle size, then press 'Start Puzzle'!\n" +
                "Match the people to their attributes, then press 'check answers'\n"), BorderLayout.NORTH);
        this.menuPanel.add(menuSetter.getMenu(), BorderLayout.CENTER);

        this.frame.setContentPane(menuPanel);
        this.frame.setVisible(true);
    }

    private void showGame(String puzzleSize) {
        Board gameBoard = new Board(puzzleSize);

        gameBoard.getEndButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });

        gameBoard.getCheckButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameBoard.getPuzzle().isFinished()) {
                    showMenu();
                    menuSetter.getReplay().setText("Play Again?");
                }
            }
        });

        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameBoard.getBoard(), BorderLayout.CENTER);

        this.frame.setContentPane(gamePanel);
        this.frame.revalidate();
    }

    private void showMenu() {
        this.frame.setContentPane(this.menuPanel);
        this.frame.revalidate();
    }
}