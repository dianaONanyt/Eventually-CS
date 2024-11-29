package view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.elements.Button;
import view.elements.Palette;

public class Events extends JScrollPane {
    private JPanel scrollAuxEvents;
    private ArrayList<String> eventsInfo;
    private ArrayList<EventPanel> eventsPanel;

    public Events(ActionListener controller) {
        eventsInfo = new ArrayList<>();
        eventsPanel = new ArrayList<>();
        scrollAuxEvents = new JPanel();
        scrollAuxEvents.setOpaque(false);
        scrollAuxEvents.setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollAuxEvents.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        scrollAuxEvents.setBackground(Palette.DARK_BG);
        this.getViewport().add(scrollAuxEvents);
    }

    private void initPanelEvents(ActionListener controller, String userType) {
        for (int i = 0; i < eventsInfo.size(); i++) {
            String[] info = eventsInfo.get(i).split(",");
            String name = info[0];
            String imagePath = info[1];
            EventPanel event = new EventPanel(name, imagePath, controller);
            eventsPanel.add(event);
            scrollAuxEvents.add(event);
        }
        scrollAuxEvents.revalidate();
        scrollAuxEvents.repaint();

        if (userType.equals("model.UserAdmin")) {
            Button addEvent = new Button("Agregar evento");
            addEvent.setPreferredSize(new Dimension(450, 620));
            addEvent.setBackground(Palette.MIDNIGHT_MIRAGE);
            addEvent.setActionCommand("addEvent");
            addEvent.addActionListener(controller);
            scrollAuxEvents.add(addEvent);
        }

    }

    public void setEvents(ArrayList<String> eventsInfo, String userType, ActionListener controller) {
        this.eventsInfo = eventsInfo;
        scrollAuxEvents.removeAll();
        initPanelEvents(controller, userType);
    }

}
