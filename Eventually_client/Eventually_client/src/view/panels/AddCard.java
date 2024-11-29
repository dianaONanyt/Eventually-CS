package view.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.elements.Button;
import view.elements.Palette;
import view.elements.TextPrompt;

public class AddCard extends JDialog {
    private JTextField cardName;
    private TextPrompt forCardName;
    private JTextField cardNumber;
    private TextPrompt forCardNumber;
    private JPanel background;
    private Button addButton;

    public AddCard(ActionListener controller) {
        cardName = new JTextField();
        forCardName = new TextPrompt("  Ingresa el nombre de la tarjeta", cardName);
        forCardName.changeAlpha(0.7f);

        cardNumber = new JTextField();
        forCardNumber = new TextPrompt("  Ingresa el número de la tarjeta", cardNumber);
        forCardNumber.changeAlpha(0.7f);

        addButton = new Button("Agregar");
        addButton.setBackground(Palette.MIDNIGHT_MIRAGE);
        addButton.setActionCommand("addNewCard");
        addButton.addActionListener(controller);

        background = new JPanel();
        background.setLayout(new GridLayout(3, 1, 10, 10));
        background.add(cardName);
        background.add(cardNumber);
        background.add(addButton);

        this.setResizable(false);
        this.setSize(new Dimension(750, 590));
        this.setTitle("Información de Tarjeta");
        this.add(background);
    }

    public ArrayList<String> getDataNewCard() {
        ArrayList<String> dataNewCard = new ArrayList<>();
        String type = cardName.getText();
        String number = cardNumber.getText();
        dataNewCard.add(type);
        dataNewCard.add(number);
        return dataNewCard;
    }
}
