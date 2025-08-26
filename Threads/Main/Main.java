package Threads.Main;

import javax.swing.SwingUtilities;
import Threads.Main.JanelaCorrida;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JanelaCorrida();
        });
    }
}
