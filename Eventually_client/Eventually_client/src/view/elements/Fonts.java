package view.elements;

import java.awt.Font;
import java.io.File;
import java.awt.FontFormatException;
import java.io.IOException;

public class Fonts {
    public static final Font BOGART_18;
    public static final Font BOGART_14;
    public static final Font NANDIA_28;

    static {
        Font bogart = null;
        Font nandia = null;
        try {
            bogart = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\font\\Bogart-Regular-trial.ttf"))
                    .deriveFont(Font.BOLD, 18);
            nandia = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\font\\Nandia.ttf")).deriveFont(Font.BOLD,
                    28);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        if (bogart != null) {
            BOGART_18 = bogart.deriveFont(18);
            BOGART_14 = bogart.deriveFont(14);
            NANDIA_28 = nandia.deriveFont(28);
        } else {
            BOGART_18 = new Font("SansSerif", Font.BOLD, 18);
            BOGART_14 = new Font("SansSerif", Font.BOLD, 14);
            NANDIA_28 = new Font("SansSerif", Font.BOLD, 28);
        }
    }

    private Fonts() {
    }
}
