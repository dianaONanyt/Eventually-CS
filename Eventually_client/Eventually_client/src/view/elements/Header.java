package view.elements;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;

public class Header extends JPanel {
    JLabel name;
    JLabel nameUser;
    JPanel pfpPanel;
    ImageIcon userIcon;
    JButton userIconButton;

    public Header(ActionListener controller) {
        nameUser = new JLabel();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        this.setBackground(Palette.BERRY_BLUE);
    }

    private void initPanels(ActionListener controller) {
        name = new JLabel("Eventually!");
        name.setFont(Fonts.NANDIA_28);
        name.setForeground(Palette.LIGHT_BG);
        name.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        pfpPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pfpPanel.setOpaque(false);

        userIcon = new ImageIcon("resources\\img\\pfp.png");
        this.userIconButton = new JButton(new ImageIcon(userIcon.getImage().getScaledInstance(50, 50,
                Image.SCALE_SMOOTH)));
        userIconButton.setPreferredSize(new Dimension(50, 50));
        userIconButton.addActionListener(controller);
        userIconButton.setActionCommand("showProfile");

        userIconButton.setBorder(new LineBorder(Palette.BERRY_BLUE));
        userIconButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pfpPanel.add(nameUser);
        pfpPanel.add(userIconButton);

        this.add(name, BorderLayout.WEST);
        this.add(pfpPanel, BorderLayout.EAST);
    }

    public JButton getUserIconButton() {
        return userIconButton;
    }

    public void setNameUser(String nickName, ActionListener controller) {
        this.nameUser.setText(nickName);
        initPanels(controller);
    }

    public void removePanel() {
        this.remove(pfpPanel);
    }
}
