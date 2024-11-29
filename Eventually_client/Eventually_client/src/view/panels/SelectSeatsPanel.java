package view.panels;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SelectSeatsPanel extends JPanel {

    private JLabel text;
    private JPanel seatsPanel;
    private int maxSelectableSeats = 1;
    private JCheckBox[][] seats;
    private JButton nextButton;
    private JComboBox<Integer> amountComboBox;
    private ArrayList<String> dataSeats;
    private ArrayList<String> dataEvent;

    public SelectSeatsPanel(ActionListener controller, int rows, int columns) {
        this.setLayout(new BorderLayout());
        this.setBackground(Palette.LIGHT_BLUE);
        dataSeats = new ArrayList<>();
        dataEvent = new ArrayList<>();
        seatsPanel = new JPanel(new GridLayout(rows, columns));
    }

    private void initiComponents(ActionListener controller, int rows, int columns) {
        text = new JLabel("Selecciona los asientos!");
        text.setBackground(Palette.LIGHT_BG);
        text.setFont(Fonts.BOGART_14);
        text.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel lettersPanel = new JPanel(new GridLayout(rows, 1));
        lettersPanel.setBackground(Palette.LIGHT_BG);

        for (int i = 0; i < rows; i++) {
            JLabel rowLabel = new JLabel(String.valueOf((char) ('A' + i)));
            rowLabel.setFont(Fonts.NANDIA_28);
            rowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lettersPanel.add(rowLabel);
        }

        seatsPanel.setBackground(Palette.BERRY_BLUE);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Palette.LIGHT_BG);
        bottomPanel.setLayout(new FlowLayout());

        amountComboBox = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5 });
        amountComboBox.addActionListener(e -> maxSelectableSeats = (int) amountComboBox.getSelectedItem());
        JLabel amountText = new JLabel("Cantidad de asientos:");
        amountText.setFont(Fonts.BOGART_14);
        bottomPanel.add(amountText);
        bottomPanel.add(amountComboBox);

        nextButton = new Button("Comprar");
        String nicknameClient = dataEvent.get(0);
        String sectionName = dataEvent.get(1);
        String eventName = dataEvent.get(2);
        nextButton.setActionCommand("shopSeat///" + nicknameClient + "///" + sectionName + "///" + eventName);
        nextButton.addActionListener(controller);
        nextButton.setBackground(Palette.MIDNIGHT_MIRAGE);
        bottomPanel.add(nextButton);

        nextButton.addActionListener(e -> {
            List<String> selectedSeats = getSelectedSeats();

            if (selectedSeats.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona al menos un asiento.");
            } else {
                JOptionPane.showMessageDialog(this, "Asiento(s) comprados: " + selectedSeats + "!");
            }
        });

        removeAll();
        this.add(text, BorderLayout.NORTH);
        this.add(seatsPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(lettersPanel, BorderLayout.WEST);
    }

    private void initCheckBox(int rows, int columns) {
        seats = new JCheckBox[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JCheckBox seat = new JCheckBox();
                seat.setBackground(Palette.LIGHT_BLUE);
                seat.setPreferredSize(new Dimension(25, 25));
                seat.addActionListener(new SeatSelectionListener());
                seats[i][j] = seat;
                seatsPanel.add(seat);
            }
        }
    }

    private class SeatSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedSeats = 0;

            for (JCheckBox[] row : seats) {
                for (JCheckBox seat : row) {
                    if (seat.isSelected()) {
                        selectedSeats++;
                    }
                }
            }
            if (selectedSeats > maxSelectableSeats) {
                ((JCheckBox) e.getSource()).setSelected(false);
            }
        }
    }

    public List<String> getSelectedSeats() {
        List<String> selectedSeats = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j].isSelected()) {
                    String seatLabel = (char) ('A' + i) + String.valueOf(j + 1);
                    selectedSeats.add(seatLabel);
                }
            }
        }
        return selectedSeats;
    }

    public void setSeatsInfo(ArrayList<String> dataSeats, ArrayList<String> dataEvent, ActionListener controller) {
        this.dataSeats = dataSeats;
        this.dataEvent = dataEvent;
        int rows = Integer.parseInt(dataSeats.get(0));
        int columns = Integer.parseInt(dataSeats.get(1));
        initCheckBox(rows, columns);
        enableOcuppedSeats(dataSeats);
        initiComponents(controller, rows, columns);

    }

    private void enableOcuppedSeats(ArrayList<String> dataSeats) {
        for (int k = 2; k < dataSeats.size(); k++) {
            String nameSeat = dataSeats.get(k);
            System.out.println("silla en la vista: " + nameSeat);
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    String seatLabel = (char) ('A' + i) + String.valueOf(j + 1);
                    if (nameSeat.equals(seatLabel)) {
                        System.out.println("silla enable false: " + seatLabel);
                        seats[i][j].setBackground(Palette.MIDNIGHT_MIRAGE);
                        seats[i][j].setEnabled(false);
                    }
                }
            }
        }
    }

    public void cleanSelectionsSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j].setSelected(false);
            }
        }
    }
}
