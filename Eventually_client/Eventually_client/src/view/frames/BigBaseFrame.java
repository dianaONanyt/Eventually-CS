package view.frames;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class BigBaseFrame extends JFrame {

    public BigBaseFrame(ActionListener actionListener) {
        this.setIconImage(new ImageIcon("resources\\img\\ticket.png").getImage());
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Eventually!");
    }

}
