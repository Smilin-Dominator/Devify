package frontend;

import backend.hash;
import backend.verify;
import backend.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class ui extends JFrame implements ActionListener {

    // Colours
    private final Color sea_green_crayola = Color.decode("#00FFC5");
    private final Color celeste = Color.decode("#ADF5FF");
    private final Color orange_red_crayola = Color.decode("#FF5E5B");
    private final Color corn = Color.decode("#FFED66");
    private final Color yellow_orange = Color.decode("#F4AC45");

    // Backend Functions
    private final hash Hash = new hash();
    private final verify Verify = new verify();
    private final common Common = new common();

    // JElements
    private final JFileChooser fileChooser;
    private final JTextField hashText;
    private final JPanel fileActions;

    // Strings
    private String chosen_file;

    public ui() {

        // Bare Bones
        setSize(485,800);
        setTitle("Devify - The Verifier!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel master = new JPanel();
        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));

        // Hash Text
        hashText = new JTextField();
        hashText.setHorizontalAlignment(JTextField.CENTER);
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

    private void showActions() {

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

    private void hash_file() throws IOException {
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
            try {
                Path dir = Paths.get(chosen_file).getParent();
                Path shafile = Paths.get(dir.toString(), "sha256.txt");
                List<String> contents = Files.readAllLines(shafile);
                String line = contents.get(0);
                String[] line_split = line.split("  ");
                String hash = line_split[0];
                String filename = line_split[1];
                boolean samefile = Common.filename_in_path(chosen_file, filename);
                if (samefile) {
                    hash_file();
                    if (Objects.equals(hash, hashText.getText())) {
                        hashText.setText("File Is The Same!");
                    } else {
                        hashText.setText("File Is Not The Same!");
                    }
                } else {
                    hashText.setText("This Is Not The File That's Hashed!");
                }
            } catch (IOException ex) {
                hashText.setText("There is no 'sha256.txt' in this DIR!");
            }
        }

    }

}
