package view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import view.elements.Palette;

public class SectionPanel extends JPanel {

    private JPanel center;
    private JPanel emptyW;
    private JPanel emptyE;
    private JPanel emptyWS;
    private JPanel emptyES;
    private JPanel emptyS;
    private JPanel emptyN;
    private SelectSeatsPanel seatsPanel;
    private ArrayList<String> dataSeats;
    private ArrayList<String> dataEvent;

    public SectionPanel(ActionListener controller, int rows, int columns) {
        dataSeats = new ArrayList<>();
        this.setBackground(Palette.DARK_BG);
        this.setLayout(new BorderLayout());
    }

    private void initComponents(ActionListener controller, int rows, int columns) {
        center = new JPanel();
        center.setBackground(Palette.LIGHT_BG);
        center.setLayout(new BorderLayout());

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

        this.emptyWS = new JPanel();
        emptyWS.setPreferredSize(new Dimension(120, 20));
        emptyWS.setBackground(Palette.LIGHT_BG);

        this.emptyES = new JPanel();
        emptyES.setPreferredSize(new Dimension(120, 20));
        emptyES.setBackground(Palette.LIGHT_BG);

        removeAll();
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);
        this.add(seatsPanel, BorderLayout.CENTER);

    }

    public List<String> getSelectedSeats() {
        return seatsPanel.getSelectedSeats();
    }

    public void setSeatsInfo(ArrayList<String> dataSeats, ArrayList<String> dataEvent, ActionListener controller) {
        this.dataSeats = dataSeats;
        this.dataEvent = dataEvent;
        int rows = Integer.parseInt(dataSeats.get(0));
        int columns = Integer.parseInt(dataSeats.get(1));
        this.seatsPanel = new SelectSeatsPanel(controller, rows, columns);
        seatsPanel.setSeatsInfo(dataSeats, dataEvent, controller);
        initComponents(controller, rows, columns);
    }

    public void cleanSelectionsSeats() {
        seatsPanel.cleanSelectionsSeats();
    }

    public void hideSection() {
        seatsPanel.setVisible(false);
    }

}
