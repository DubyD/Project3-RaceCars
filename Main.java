//Author WD

import javax.swing.*;

    //Runs the Start Lambda Method
public class Main {
    public static void main(String[] args) {

        //Invokes Java Swing. instead of Main extending application
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Auto_Racing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 1200);

            SceneSwitcher sceneSwitcher = new SceneSwitcher(frame);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
