package view.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.DateFormatter;

import view.elements.Button;
import view.elements.Fonts;
import view.elements.Palette;
import view.elements.TextPrompt;

public class AddEventPanel extends JPanel {

    private JTable sections;
    private JPanel selectSections;
    private Button next;
    private Button back;
    private JPanel typePanel;
    private JTextField name;
    private JFormattedTextField date;
    private JComboBox<String> hourjcb;
    // private JPanel addImageCheck;
    private JPanel hoursPanel;
    private JComboBox<String> typesEventjcb;
    private JPanel emptyW;
    private JPanel emptyE;
    private JPanel emptyS;
    private JPanel emptyN;
    private JPanel center;

    public AddEventPanel(ActionListener controller) {
        this.setLayout(new BorderLayout());
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

        center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new GridLayout(4, 2, 20, 20));

        name = new JTextField();
        name.setBackground(Palette.LIGHT_BG);
        TextPrompt forName = new TextPrompt("  Ingresa el nombre del evento", name);
        forName.setFont(Fonts.BOGART_14);
        forName.changeAlpha(0.7f);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        date = new JFormattedTextField(dateFormatter);
        date.setBackground(Palette.LIGHT_BG);
        TextPrompt forDate = new TextPrompt("  Ingresa la fecha del evento [dd/MM/yyyy]", date);
        forDate.setFont(Fonts.BOGART_14);
        forDate.changeAlpha(0.7f);

        hoursPanel = new JPanel();
        hoursPanel.setLayout(new BorderLayout());
        hoursPanel.setPreferredSize(new Dimension(300, 100));
        hoursPanel.setBackground(Palette.LIGHT_BG);
        String[] hoursString = { "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
                "18:00", "19:00", "20:00", "21:00", "22:00" };
        hourjcb = new JComboBox<>(hoursString);
        hourjcb.setBackground(Palette.LIGHT_BG);
        hourjcb.setFocusable(false);
        hourjcb.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        hoursPanel.add(hourjcb, BorderLayout.CENTER);

        JLabel titleHour = new JLabel("Selecciona la hora del evento", SwingConstants.CENTER);
        titleHour.setOpaque(true);
        titleHour.setBackground(Palette.MIDNIGHT_MIRAGE);
        titleHour.setForeground(Palette.LIGHT_BG);
        titleHour.setFont(Fonts.BOGART_18);
        titleHour.setPreferredSize(new Dimension(300, 30));
        hoursPanel.add(titleHour, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new Object[] { "Seleccionar", "Sección" }, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0)
                    return Boolean.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        model.addRow(new Object[] { false, "Platea A" });
        model.addRow(new Object[] { false, "Platea B" });
        model.addRow(new Object[] { false, "Platea C" });
        model.addRow(new Object[] { false, "General A" });
        model.addRow(new Object[] { false, "General B" });
        model.addRow(new Object[] { false, "General C" });

        sections = new JTable(model);
        sections.setRowHeight(44);

        JTableHeader header = sections.getTableHeader();
        header.setFont(Fonts.BOGART_18);
        header.setBackground(Palette.MIDNIGHT_MIRAGE);
        header.setForeground(Palette.LIGHT_BG);

        DefaultTableCellRenderer textRenderer = new DefaultTableCellRenderer();
        textRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        textRenderer.setBackground(Palette.LIGHT_BG);

        sections.getColumnModel().getColumn(1).setCellRenderer(textRenderer);

        TableColumn selectColumn = sections.getColumnModel().getColumn(0);
        selectColumn.setPreferredWidth(30);
        TableColumn sectionColumn = sections.getColumnModel().getColumn(1);
        sectionColumn.setPreferredWidth(200);

        selectSections = new JPanel();
        selectSections.setPreferredSize(new Dimension(400, 200));
        selectSections.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(sections);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        selectSections.add(scrollPane, BorderLayout.CENTER);

        JLabel tittle = new JLabel("Selecciona las secciones del evento", SwingConstants.CENTER);
        tittle.setFont(Fonts.BOGART_18);
        tittle.setOpaque(true);
        tittle.setBackground(Palette.MIDNIGHT_MIRAGE);
        tittle.setForeground(Palette.LIGHT_BG);
        tittle.setPreferredSize(new Dimension(400, 40));

        selectSections.add(tittle, BorderLayout.NORTH);

        typePanel = new JPanel();
        typePanel.setLayout(new BorderLayout());
        typePanel.setPreferredSize(new Dimension(300, 100));
        typePanel.setBackground(Palette.LIGHT_BG);

        String[] eventType = { "musica", "cultura", "deporte", "familia", "festival", "comedia" };
        typesEventjcb = new JComboBox<>(eventType);
        typesEventjcb.setBackground(Palette.LIGHT_BG);
        typesEventjcb.setFont(Fonts.BOGART_18);
        typesEventjcb.setFocusable(false);
        typesEventjcb.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        typePanel.add(typesEventjcb, BorderLayout.CENTER);

        JLabel title = new JLabel("Selecciona el tipo de evento", SwingConstants.CENTER);
        title.setOpaque(true);
        title.setBackground(Palette.MIDNIGHT_MIRAGE);
        title.setForeground(Palette.LIGHT_BG);
        title.setFont(Fonts.BOGART_18);
        title.setPreferredSize(new Dimension(300, 30));
        typePanel.add(title, BorderLayout.NORTH);

        next = new Button("Siguiente");
        next.setBackground(Palette.MIDNIGHT_MIRAGE);
        next.setActionCommand("addNewEvent");
        next.addActionListener(controller);

        back = new Button("Atrás");
        back.setBackground(Palette.MIDNIGHT_MIRAGE);
        back.setActionCommand("backToEvents");
        back.addActionListener(controller);

        // addImageCheck = new JPanel();
        // addImageCheck.setOpaque(false);
        // addImageCheck.setLayout(new BoxLayout(addImageCheck, BoxLayout.Y_AXIS));

        // JPanel upperPanel = new JPanel();
        // upperPanel.setOpaque(false);
        // upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // JLabel addImageText = new JLabel("Agregar poster de evento");
        // addImageText.setBackground(Palette.LIGHT_BG);
        // addImageText.setFont(Fonts.BOGART_18);

        // JCheckBox addPoster = new JCheckBox();
        // addPoster.setOpaque(false);

        // upperPanel.add(addImageText);
        // upperPanel.add(addPoster);

        // JTextField routeField = new JTextField(20);
        // routeField.setBackground(Palette.LIGHT_BG);
        // TextPrompt forRoute = new TextPrompt("Ingresa la ruta de la imagen",
        // routeField);
        // forRoute.setFont(Fonts.BOGART_14);
        // forRoute.changeAlpha(0.7f);

        // addPoster.addActionListener(e -> {
        // routeField.setEnabled(addPoster.isSelected());
        // });

        // addImageCheck.add(upperPanel);
        // addImageCheck.add(Box.createVerticalStrut(10));
        // addImageCheck.add(routeField);

        this.setBackground(Palette.DARK_BG);
        center.add(name);
        center.add(date);
        // center.add(addImageCheck);
        center.add(hoursPanel);
        // center.add(selectSections);
        center.add(typePanel);
        center.add(back);
        center.add(next);
        this.add(emptyE, BorderLayout.EAST);
        this.add(emptyS, BorderLayout.SOUTH);
        this.add(emptyW, BorderLayout.WEST);
        this.add(emptyN, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public ArrayList<String> getDataNewEvent() {
        ArrayList<String> dataNewEvent = new ArrayList<>();
        dataNewEvent.add(name.getText());
        dataNewEvent.add(date.getText());
        dataNewEvent.add(hourjcb.getSelectedItem().toString());
        dataNewEvent.add(typesEventjcb.getSelectedItem().toString());
        System.out.println("datos nuevo evento desde vista: " + dataNewEvent.toString());
        return dataNewEvent;
    }
}
