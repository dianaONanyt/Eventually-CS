package view.panels;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;
import view.elements.TextPrompt;

public class CreateProfilePanel extends JPanel {

    private JLabel pfpLabel;
    private JPanel emptyW;
    private JPanel center;
    private JPanel emptyE;
    private JPanel emptyS;
    private JPanel emptyN;
    private JPanel pfpPanel;
    private JLabel infoHeader;
    private JButton back;
    private JTextField name;
    private JTextField nickName;
    private JTextField id;
    private JTextField mail;
    private JPasswordField password;
    private Button createAccount;
    private JPanel infoPanel;
    private ArrayList<String> infoNewAccount;

    public CreateProfilePanel(ActionListener controller) {
        infoNewAccount = new ArrayList<>();
        this.setLayout(new BorderLayout());
        this.setBackground(Palette.LIGHT_BG);
        initStuff(controller);
    }

    private void initStuff(ActionListener controller) {
        this.emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(120, 20));
        emptyE.setBackground(Palette.DARK_BG);

        this.emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(120, 20));
        emptyW.setBackground(Palette.DARK_BG);

        ImageIcon backImage = new ImageIcon("resources\\img\\back.png");
        back = new JButton(backImage);

        back.setPreferredSize(new Dimension(30, 30));
        back.setBorder(new LineBorder(Palette.DARK_BG));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setActionCommand("backFromProfile");
        back.addActionListener(controller);
        emptyW.add(back);

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

        infoHeader = new JLabel("  Ingresa tus Datos!");
        infoHeader.setFont(Fonts.NANDIA_28);
        infoHeader.setPreferredSize(new Dimension(450, 50));
        infoHeader.setHorizontalAlignment(SwingConstants.CENTER);

        name = new JTextField();
        TextPrompt forName = new TextPrompt("  Ingresa tu nombre completo", name);
        forName.changeAlpha(0.7f);
        name.setPreferredSize(new Dimension(450, 50));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        nickName = new JTextField();
        TextPrompt forNickName = new TextPrompt("  Ingresa un nombre de usuario", nickName);
        forNickName.changeAlpha(0.7f);
        nickName.setPreferredSize(new Dimension(450, 50));
        nickName.setHorizontalAlignment(SwingConstants.CENTER);

        id = new JTextField();
        TextPrompt forId = new TextPrompt("  Ingresa tu número de identificación", id);
        forId.changeAlpha(0.7f);
        id.setPreferredSize(new Dimension(450, 50));
        id.setHorizontalAlignment(SwingConstants.CENTER);

        mail = new JTextField();
        TextPrompt forMail = new TextPrompt("  Ingresa tu email", mail);
        forMail.changeAlpha(0.7f);
        mail.setFont(Fonts.NANDIA_28);
        mail.setPreferredSize(new Dimension(450, 50));
        mail.setHorizontalAlignment(SwingConstants.CENTER);

        password = new JPasswordField();
        TextPrompt forPass = new TextPrompt("  Ingresa tu contraseña", password);
        forPass.changeAlpha(0.7f);
        password.setFont(Fonts.NANDIA_28);
        password.setPreferredSize(new Dimension(450, 50));
        password.setHorizontalAlignment(SwingConstants.CENTER);

        createAccount = new Button("Crear");
        createAccount.setActionCommand("createNewAccount");
        createAccount.addActionListener(controller);
        createAccount.setPreferredSize(new Dimension(220, 40));
        createAccount.setBackground(Palette.MIDNIGHT_MIRAGE);

        infoPanel.add(infoHeader);
        infoPanel.add(name);
        infoPanel.add(id);
        infoPanel.add(mail);
        infoPanel.add(nickName);
        infoPanel.add(password);
        infoPanel.add(createAccount);

        center.add(pfpPanel);
        center.add(infoPanel);

        this.add(center, BorderLayout.CENTER);
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);

        this.setVisible(true);
    }

    public ArrayList<String> getDataNewUser() {
        String nameString = name.getText();
        String idString = id.getText();
        String mailString = mail.getText();
        String nicknameString = mail.getText();
        String passowrdString = String.valueOf(password.getPassword());
        infoNewAccount.add(nameString);
        infoNewAccount.add(idString);
        infoNewAccount.add(mailString);
        infoNewAccount.add(nicknameString);
        infoNewAccount.add(passowrdString);
        return infoNewAccount;
    }
}
