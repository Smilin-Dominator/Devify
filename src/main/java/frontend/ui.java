package frontend;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ui extends JFrame implements ActionListener {

    private JFileChooser fileChooser;

    public ui() {

        // Bare Bones
        setSize(450,800);
        setTitle("Devify - The Verifier!");

        // Main Panel
        JPanel master = new JPanel();
        master.setLayout(new BorderLayout(0, 0));

        // File Chooser
        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Addition
        master.add(fileChooser);
        add(master);

        // Final
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac_com = e.getActionCommand();
        if (Objects.equals(ac_com, "ApproveSelection")) {
            System.out.println(fileChooser.getSelectedFile());
        } else if (Objects.equals(ac_com, "CancelSelection")) {
            System.exit(0);
        }
    }

}
