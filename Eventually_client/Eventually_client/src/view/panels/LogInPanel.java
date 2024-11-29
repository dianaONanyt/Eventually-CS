package view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;
import view.elements.TextPrompt;

public class LogInPanel extends JPanel {
    private JLabel pfpLabel;
    private JPanel emptyW;
    private JPanel center;
    private JPanel emptyE;
    private JPanel emptyS;
    private JPanel emptyN;
    private JPanel pfpPanel;
    private JPanel infoPanel;
    private JTextField user;
    private JTextField password;
    private ArrayList<String> dataLogIn;

    public LogInPanel(ActionListener controller) {
        dataLogIn = new ArrayList<>();
        this.setBackground(Palette.LIGHT_BG);
        this.setLayout(new BorderLayout());
        initComponents(controller);
    }

    private void initComponents(ActionListener controller) {
        this.emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(120, 20));
        emptyE.setBackground(Palette.DARK_BG);

        this.emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(120, 20));
        emptyW.setBackground(Palette.DARK_BG);

        emptyS = new JPanel();
        emptyS.setPreferredSize(new Dimension(10, 20));
        emptyS.setBackground(Palette.DARK_BG);

        emptyN = new JPanel();
        emptyN.setPreferredSize(new Dimension(10, 20));
        emptyN.setBackground(Palette.DARK_BG);

        center = new JPanel();
        center.setLayout(new GridLayout(1, 2, 0, 1));
        center.setBackground(Palette.LIGHT_BG);

        ImageIcon pfpIcon = new ImageIcon("resources\\img\\pfp.png");
        pfpLabel = new JLabel();
        pfpLabel.setIcon(new ImageIcon(pfpIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
        pfpLabel.setBorder(BorderFactory.createEmptyBorder(140, 0, 0, 0));
        this.pfpPanel = new JPanel();
        pfpPanel.setBackground(Palette.BERRY_BLUE);
        pfpPanel.setPreferredSize(new Dimension(450, 600));
        pfpPanel.add(pfpLabel);
        pfpPanel.setVisible(true);

        this.infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(130, 0, 0, 0));
        infoPanel.setPreferredSize(new Dimension(450, 800));

        JLabel infoHeader = new JLabel("Ingresa tus Datos!");
        infoHeader.setFont(Fonts.NANDIA_28);
        infoHeader.setPreferredSize(new Dimension(450, 50));
        infoHeader.setHorizontalAlignment(SwingConstants.CENTER);

        user = new JTextField();
        TextPrompt forName = new TextPrompt("  Ingresa tu nombre completo", user);
        forName.changeAlpha(0.7f);
        user.setFont(Fonts.BOGART_14);
        user.setPreferredSize(new Dimension(450, 50));
        user.setHorizontalAlignment(SwingConstants.CENTER);

        password = new JPasswordField();
        TextPrompt forPass = new TextPrompt("  Ingresa tu contraseña", password);
        forPass.changeAlpha(0.7f);
        password.setPreferredSize(new Dimension(450, 50));
        password.setHorizontalAlignment(SwingConstants.CENTER);

        Button createAccount = new Button("Crear Cuenta");
        createAccount.setPreferredSize(new Dimension(220, 40));
        createAccount.setBackground(Palette.MIDNIGHT_MIRAGE);
        createAccount.addActionListener(controller);
        createAccount.setActionCommand("createAccount");

        Button logIn = new Button("Iniciar Sesión");
        logIn.setPreferredSize(new Dimension(220, 40));
        logIn.setBackground(Palette.MIDNIGHT_MIRAGE);
        logIn.setActionCommand("logIn");
        logIn.addActionListener(controller);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setPreferredSize(new Dimension(450, 40));
        buttons.setLayout(new GridLayout(1, 2, 20, 0));
        buttons.add(createAccount);
        buttons.add(logIn);

        infoPanel.add(infoHeader);
        infoPanel.add(user);
        infoPanel.add(password);
        infoPanel.add(buttons);

        center.add(pfpPanel);
        center.add(infoPanel);

        this.add(center, BorderLayout.CENTER);
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);

        this.setVisible(true);
    }

    public ArrayList<String> getDataLogIn() {
        String userString = user.getText();
        String passwordString = password.getText();
        dataLogIn.add(userString);
        dataLogIn.add(passwordString);
        return dataLogIn;
    }

    public void cleanFields() {
        user.setText("");
        password.setText("");
    }

}
