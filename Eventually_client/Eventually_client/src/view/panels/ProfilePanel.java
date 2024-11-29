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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;

public class ProfilePanel extends JPanel {

    private JLabel pfpLabel;
    private JPanel emptyW;
    private JPanel center;
    private JPanel emptyE;
    private JPanel emptyS;
    private JPanel emptyN;
    private JPanel pfpPanel;
    private JButton back;
    private JPanel infoPanel;
    private Button logOut;
    private JPanel panelCards;
    private JScrollPane scrollPane;
    private ArrayList<String> profileInfo;
    private ArrayList<String> cardsInfo;

    public ProfilePanel(ActionListener controller) {
        profileInfo = new ArrayList<>();
        cardsInfo = new ArrayList<>();
        panelCards = new JPanel();
        scrollPane = new JScrollPane();
        this.setLayout(new BorderLayout());
        this.setBackground(Palette.DARK_BG);
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
        back.setActionCommand("backToEvents");
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

        JLabel infoHeader = new JLabel("-------------------- Tu Perfil! --------------------");
        infoHeader.setFont(Fonts.NANDIA_28);
        infoHeader.setPreferredSize(new Dimension(450, 50));
        infoHeader.setHorizontalAlignment(SwingConstants.CENTER);

        String nameString = profileInfo.get(0);
        JLabel name = new JLabel(nameString);
        name.setFont(Fonts.NANDIA_28);
        name.setPreferredSize(new Dimension(450, 50));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        String idString = profileInfo.get(1);
        JLabel id = new JLabel(idString);
        id.setFont(Fonts.NANDIA_28);
        id.setPreferredSize(new Dimension(450, 50));
        id.setHorizontalAlignment(SwingConstants.CENTER);

        String mailString = profileInfo.get(2);
        JLabel mail = new JLabel(mailString);
        mail.setFont(Fonts.NANDIA_28);
        mail.setPreferredSize(new Dimension(450, 50));
        mail.setHorizontalAlignment(SwingConstants.CENTER);

        Button addCard = new Button("Añadir");
        addCard.setPreferredSize(new Dimension(220, 40));
        addCard.addActionListener(controller);
        addCard.setActionCommand("addCard");

        addCard.setBackground(Palette.MIDNIGHT_MIRAGE);
        Button removeCard = new Button("Eliminar");
        removeCard.setPreferredSize(new Dimension(450, 40));
        removeCard.setBackground(Palette.MIDNIGHT_MIRAGE);

        logOut = new Button("Cerrar sesion");
        logOut.setPreferredSize(new Dimension(150, 30));
        logOut.setBackground(Palette.BERRY_BLUE);
        logOut.setActionCommand("logOut");
        logOut.addActionListener(controller);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setPreferredSize(new Dimension(450, 40));
        buttons.setLayout(new GridLayout(1, 2, 20, 0));
        if (cardsInfo != null) {
            buttons.add(addCard);
            buttons.add(removeCard);
        }

        this.removeAll();
        infoPanel.add(infoHeader);
        infoPanel.add(name);
        infoPanel.add(id);
        infoPanel.add(mail);
        infoPanel.add(scrollPane);
        infoPanel.add(buttons);
        infoPanel.add(logOut);

        center.add(pfpPanel);
        center.add(infoPanel);

        this.add(center, BorderLayout.CENTER);
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);

        this.setVisible(true);
    }

    public void setProfileInfo(ArrayList<String> profileInfo, ArrayList<String> cardsInfo, ActionListener controller) {
        this.profileInfo = profileInfo;
        this.cardsInfo = cardsInfo;
        if (cardsInfo != null) {
            addCards(cardsInfo);
        }
        initStuff(controller);
    }

    private void addCards(ArrayList<String> cardsInfo) {
        JPanel panelCards = new JPanel();

        panelCards.setPreferredSize(new Dimension(450, 50));
        panelCards.setLayout(new BoxLayout(panelCards, BoxLayout.Y_AXIS));
        for (String nameCard : cardsInfo) {
            JLabel hh = new JLabel(nameCard);
            hh.setPreferredSize(new Dimension(400, 40));
            panelCards.add(hh);
            System.out.println("agregadoo" + nameCard);
        }

        panelCards.revalidate();
        panelCards.repaint();
        this.panelCards = panelCards;

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(panelCards);
        scrollPane.setPreferredSize(new Dimension(450, 55));
        this.scrollPane = scrollPane;
    }
}
