package frontend;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ui extends JFrame implements ActionListener {

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }

}
