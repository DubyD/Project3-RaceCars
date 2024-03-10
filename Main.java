import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        //Invokes Java Swing. instead of Main extending application
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("PuzzlePals");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SceneSwitcher sceneSwitcher = new SceneSwitcher(frame);
            //sceneSwitcher.switchToMenuScene();

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
