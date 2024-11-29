package view.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;

public class EventPanel extends JPanel {
    private ImageIcon poster;
    private JLabel posterImage;
    private JLabel eventName;
    private Button infoButton;

    public EventPanel(String nameEvent, String route, ActionListener controller) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(450, 620));
        this.setBackground(Palette.LIGHT_BG);
        this.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));

        poster = new ImageIcon(route);
        posterImage = new JLabel();
        posterImage.setIcon(new ImageIcon(poster.getImage().getScaledInstance(370, 510, Image.SCALE_SMOOTH)));
        posterImage.setPreferredSize(new Dimension(400, 600));
        posterImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        eventName = new JLabel(nameEvent);
        eventName.setFont(Fonts.BOGART_18);
        eventName.setHorizontalAlignment(SwingConstants.CENTER);
        eventName.setPreferredSize(new Dimension(450, 20));
        eventName.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoButton = new Button("Más info.");
        infoButton.setActionCommand("masInfo///" + nameEvent);
        infoButton.addActionListener(controller);
        infoButton.setPreferredSize(new Dimension(200, 40));
        infoButton.setBackground(Palette.MIDNIGHT_MIRAGE);
        infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(posterImage);
        this.add(eventName);
        this.add(infoButton);
        this.setVisible(true);
    }

    public JLabel getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(JLabel posterImage) {
        this.posterImage = posterImage;
    }

    public JLabel getEventName() {
        return eventName;
    }

    public void setEventName(JLabel eventName) {
        this.eventName = eventName;
    }

    public Button getInfoButton() {
        return infoButton;
    }

    public void setInfoButton(Button infoButton) {
        this.infoButton = infoButton;
    }

    public ImageIcon getPoster() {
        return poster;
    }

    public void setPoster(ImageIcon poster) {
        this.poster = poster;
    }

}
