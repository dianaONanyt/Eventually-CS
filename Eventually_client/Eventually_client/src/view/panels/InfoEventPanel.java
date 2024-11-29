package view.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;

import javax.swing.JLabel;

import java.util.ArrayList;

public class InfoEventPanel extends JPanel {

    private JPanel poster;
    private JPanel info;
    private JPanel emptyW;
    private JPanel emptyE;
    private JPanel emptyS;
    private JPanel emptyN;
    private JButton back;
    private JPanel center;
    private ArrayList<String> infoEvent;
    private String nameEvent;
    private String typeUser;

    public InfoEventPanel(ActionListener controller) {
        infoEvent = new ArrayList<>();
        typeUser = "";
        this.setLayout(new BorderLayout());
        this.setBackground(Palette.DARK_BG);
        this.setVisible(true);
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

        // array. 0.Image 1.nombre 2.tipo 3.fecha 4. hora 5.
        String path = infoEvent.get(0);

        ImageIcon posterImage = new ImageIcon(path);
        JLabel posterLabel = new JLabel();
        posterLabel.setIcon(new ImageIcon(posterImage.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH)));
        this.poster = new JPanel();
        poster.setOpaque(false);
        poster.setPreferredSize(new Dimension(450, 800));
        poster.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        poster.add(posterLabel);
        poster.setVisible(true);

        this.info = new JPanel();
        info.setLayout(new FlowLayout());
        info.setOpaque(false);
        info.setPreferredSize(new Dimension(450, 800));
        info.setBorder(BorderFactory.createEmptyBorder(160, 0, 0, 0));

        String name = infoEvent.get(1);
        JLabel tittle = new JLabel(name);
        tittle.setFont(Fonts.NANDIA_28);
        tittle.setPreferredSize(new Dimension(450, 50));
        tittle.setHorizontalAlignment(SwingConstants.CENTER);

        String typeString = infoEvent.get(2);
        JLabel type = new JLabel("Tipo: " + typeString);
        type.setFont(Fonts.NANDIA_28);
        type.setPreferredSize(new Dimension(450, 50));
        type.setHorizontalAlignment(SwingConstants.CENTER);

        String dateString = infoEvent.get(3);
        JLabel dateLabel = new JLabel(dateString);
        dateLabel.setFont(Fonts.NANDIA_28);
        dateLabel.setPreferredSize(new Dimension(450, 50));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String time = infoEvent.get(4);
        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(Fonts.NANDIA_28);
        timeLabel.setPreferredSize(new Dimension(450, 50));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Button shop = new Button("Comprar");
        shop.setActionCommand("shopEvent///" + name);
        shop.setBackground(Palette.MIDNIGHT_MIRAGE);
        shop.addActionListener(controller);
        shop.setPreferredSize(new Dimension(200, 40));
        shop.setAlignmentX(Component.CENTER_ALIGNMENT);

        Button editSections = new Button("Cambiar secciones disponibles");
        editSections.setActionCommand("changeSectionsAvailables///" + name);
        editSections.setBackground(Palette.MIDNIGHT_MIRAGE);
        editSections.addActionListener(controller);
        editSections.setPreferredSize(new Dimension(200, 40));
        editSections.setAlignmentX(Component.CENTER_ALIGNMENT);

        Button deleteEvent = new Button("Eliminar evento");
        deleteEvent.setActionCommand("deleteEvent///" + name);
        deleteEvent.setBackground(Palette.MIDNIGHT_MIRAGE);
        deleteEvent.addActionListener(controller);
        deleteEvent.setPreferredSize(new Dimension(200, 40));
        deleteEvent.setAlignmentX(Component.CENTER_ALIGNMENT);

        removeAll();
        info.add(tittle);
        info.add(type);
        info.add(dateLabel);
        info.add(timeLabel);
        if (typeUser.equals("class model.UserClient")) {
            info.add(shop);
        } else {
            info.add(editSections);
            info.add(deleteEvent);
        }

        center.add(poster);
        center.add(info);

        this.add(center, BorderLayout.CENTER);
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);

        this.setVisible(true);
    }

    public void setEventInfo(ArrayList<String> eventInfo, String typeUser, ActionListener controller) {
        this.infoEvent = eventInfo;
        this.typeUser = typeUser;
        System.out.println("user que recibe: " + typeUser);
        initStuff(controller);
    }

    public void setEventName(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getNameEventSelected() {
        return nameEvent;
    }
}
