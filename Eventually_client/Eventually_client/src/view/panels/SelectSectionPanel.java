package view.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.elements.Fonts;
import view.elements.Palette;
import view.elements.Button;

public class SelectSectionPanel extends JPanel {

    private JPanel theater;
    private JLabel text;
    private Button scenary;
    private JPanel upper;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel emptyS;
    private JButton back;
    private String nameEvent;
    private ArrayList<String> sections;
    private ArrayList<String> sectionsAvailables;

    public SelectSectionPanel(ActionListener controller) {
        nameEvent = "";
        sections = new ArrayList<>();
        sectionsAvailables = new ArrayList<>();
        this.setLayout(new BorderLayout());
        this.setBackground(Palette.DARK_BG);
        this.setVisible(true);
    }

    private void initStuff(ActionListener controller) {
        upper = new JPanel();
        upper.setLayout(new BorderLayout(10, 10));
        upper.setBorder(BorderFactory.createEmptyBorder(30, 0, 40, 0));
        upper.setBackground(Palette.DARK_BG);

        JPanel emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(400, 0));
        emptyW.setOpaque(false);

        ImageIcon backImage = new ImageIcon("resources\\img\\back.png");
        back = new JButton(backImage);

        back.setPreferredSize(new Dimension(30, 30));
        back.setBorder(new LineBorder(Palette.DARK_BG));
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.setActionCommand("backToEvents");
        back.addActionListener(controller);
        emptyW.add(back);
        upper.add(emptyW, BorderLayout.WEST);

        JPanel emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(400, 0));
        emptyE.setOpaque(false);
        upper.add(emptyE, BorderLayout.EAST);

        ArrayList<Button> buttonsSections = new ArrayList<>();
        String plateaA = sections.get(0);
        Button buttonPA = new Button(plateaA);
        buttonsSections.add(buttonPA);
        String plateaB = sections.get(1);
        Button buttonPB = new Button(plateaB);
        buttonsSections.add(buttonPB);
        String plateaC = sections.get(2);
        Button buttonPC = new Button(plateaC);
        buttonsSections.add(buttonPC);
        String generalA = sections.get(3);
        Button buttonGA = new Button(generalA);
        buttonsSections.add(buttonGA);
        String generalB = sections.get(4);
        Button buttonGB = new Button(generalB);
        buttonsSections.add(buttonGB);
        String generalC = sections.get(5);
        Button buttonGC = new Button(generalC);
        buttonsSections.add(buttonGC);

        for (Button button : buttonsSections) {
            button.setEnabled(false);
        }

        for (Button button : buttonsSections) {
            for (String name : sectionsAvailables) {
                if (button.getText().equals(name)) {
                    button.setEnabled(true);
                }
            }
        }

        leftPanel = createVerticalPanel(buttonPA, buttonGA, 1, controller);
        centerPanel = createVerticalPanel(buttonPB, buttonGB, 2, controller);
        rightPanel = createVerticalPanel(buttonPC, buttonGC, 1, controller);

        text = new JLabel("Selecciona la sección que deseas visualizar!");
        text.setFont(Fonts.NANDIA_28);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        upper.add(text, BorderLayout.NORTH);

        scenary = new Button("ESCENARIO");
        scenary.setPreferredSize(new Dimension(200, 80));
        scenary.setEnabled(false);
        scenary.setBackground(Palette.MIDNIGHT_MIRAGE);
        scenary.setAlignmentX(Component.CENTER_ALIGNMENT);
        upper.add(scenary, BorderLayout.CENTER);

        this.theater = new JPanel();
        theater.setLayout(new FlowLayout());
        theater.setOpaque(false);

        theater.add(leftPanel);
        theater.add(centerPanel);
        theater.add(rightPanel);

        emptyS = new JPanel();
        emptyS.setPreferredSize(new Dimension(10, 20));
        emptyS.setBackground(Palette.DARK_BG);

        emptyW = new JPanel();
        emptyW.setPreferredSize(new Dimension(120, 20));
        emptyW.setBackground(Palette.DARK_BG);

        emptyE = new JPanel();
        emptyE.setPreferredSize(new Dimension(120, 20));
        emptyE.setBackground(Palette.DARK_BG);

        removeAll();
        this.add(upper, BorderLayout.NORTH);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyE, BorderLayout.EAST);
        this.add(theater, BorderLayout.CENTER);
    }

    private JPanel createVerticalPanel(Button button1, Button button2, int weight, ActionListener controller) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Palette.DARK_BG);

        Dimension panelSize = new Dimension(weight == 1 ? 190 : 600, 400);
        panel.setPreferredSize(panelSize);

        String button1Name = button1.getText();
        button1.setBackground(Palette.BERRY_BLUE);
        button1.setActionCommand("click///" + button1Name + "///" + nameEvent);
        button1.addActionListener(controller);
        String button2Name = button2.getText();
        button2.setBackground(Palette.MIDNIGHT_MIRAGE);
        button2.setActionCommand("click///" + button2Name + "///" + nameEvent);
        button2.addActionListener(controller);

        panel.add(button1);
        panel.add(button2);

        return panel;
    }

    public void setInfoSections(String nameEvent, ArrayList<String> sections, ArrayList<String> sectionsAvailables,
            ActionListener controller) {
        this.nameEvent = nameEvent;
        this.sections = sections;
        this.sectionsAvailables = sectionsAvailables;
        initStuff(controller);
    }
}
