package frontend;

import javax.swing.*;
import java.awt.*;

public class ui extends JFrame {

    public ui() {

        // Bare Bones
        setSize(450,800);
        setTitle("Devify - The Verifier!");
        setVisible(true);

        // Main Panel
        JPanel master = new JPanel();
        master.setLayout(new BorderLayout(0, 0));

        // Addition
        add(master);

    }

}
