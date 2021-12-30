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
    private final JPanel fileActions;
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
        hashText.setText("Choose An Action!");

        // File Chooser
        fileChooser = new JFileChooser();
        fileChooser.addActionListener(this);

        // Actions For The File
        fileActions = new JPanel();
        fileActions.setVisible(false);

        // Adding to Master
        master.add(fileChooser);
        master.add(fileActions);
        add(master);

        // Final
        setVisible(true);

    }

    public void showActions() {

        fileActions.removeAll();
        fileActions.revalidate();

        fileActions.setBackground(Color.getHSBColor(174, 77, 77));
        fileActions.setLayout(new BorderLayout(1, 1));
        hashText.setText("Choose An Action!");

        JButton genHash = new JButton("Generate Hash");
        genHash.addActionListener(this);

        JButton genChecksum = new JButton("Generate Checksum");
        genChecksum.addActionListener(this);

        JButton verifyChecksum = new JButton("Verify Checksum");
        verifyChecksum.addActionListener(this);

        fileActions.add(hashText, BorderLayout.PAGE_START);
        fileActions.add(genHash, BorderLayout.LINE_START);
        fileActions.add(genChecksum, BorderLayout.CENTER);
        fileActions.add(verifyChecksum, BorderLayout.LINE_END);

        fileActions.repaint();
        fileActions.setVisible(true);

    }

    public void hash_file() throws IOException {
        hashText.setText(Hash.file(chosen_file));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String ac_com = e.getActionCommand();

        // The File Choosing Section
        if (Objects.equals(ac_com, "ApproveSelection")) {
            chosen_file = fileChooser.getSelectedFile().getAbsolutePath();
            showActions();
        }
        else if (Objects.equals(ac_com, "CancelSelection")) {
            fileActions.setVisible(false);
        }

        // The File Actions
        else if (Objects.equals(ac_com, "Generate Hash")) {
            try {
                hash_file();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (Objects.equals(ac_com, "Generate Checksum")) {
            try {
                hash_file();
                Hash.checksum(hashText.getText(), chosen_file, hashText);
                fileChooser.rescanCurrentDirectory();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (Objects.equals(ac_com, "Verify Checksum")) {
            System.out.println("fff");
        }

    }

}
