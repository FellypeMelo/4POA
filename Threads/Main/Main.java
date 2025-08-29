package Main;

import javax.swing.SwingUtilities;
import Main.CorridaGatos;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CorridaGatos();
        });
    }
}
