package frontend;

import backend.hash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class ui extends JFrame implements ActionListener {

    private final hash Hash = new hash();
    private final JFileChooser fileChooser;
    private final JTextField hashText;
    private String chosen_file;

    public ui() {

        // Bare Bones
        setSize(450,800);
        setTitle("Devify - The Verifier!");

        // Main Panel
        JPanel master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));

        // Hash Text
        hashText = new JTextField();
        hashText.setText("Select A File!");

        // File Chooser
        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Addition
        master.add(fileChooser);
        master.add(hashText);
        add(master);

        // Final
        setVisible(true);

    }

    public void hash_file() throws IOException {
        hashText.setText(Hash.file(chosen_file));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ac_com = e.getActionCommand();
        if (Objects.equals(ac_com, "ApproveSelection")) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            chosen_file = path;
            System.out.println("Chosen File: " + path);
            try {
                hash_file();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (Objects.equals(ac_com, "CancelSelection")) {
            System.exit(0);
        }
    }

}
