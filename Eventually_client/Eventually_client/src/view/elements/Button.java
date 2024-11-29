package view.elements;

import java.awt.Cursor;

import javax.swing.JButton;

public class Button extends JButton {

    public Button(String text) {
        this.setForeground(Palette.LIGHT_BG);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setText(text);
        this.setFont(Fonts.BOGART_14);
        this.setFocusPainted(false);
    }
}
