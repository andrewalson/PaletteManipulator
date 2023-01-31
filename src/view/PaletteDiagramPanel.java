package view;

import javax.swing.*;
import java.awt.*;

public class PaletteDiagramPanel extends JPanel {

    int width = 20;
    int height = 20;
    int x = 40;
    int y = 40;

    public PaletteDiagramPanel() {
        this.setBorder(BorderFactory.createTitledBorder("Palette"));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.black);

        for (int i = 0; i < 16; i++) {
            for (int f = 0; f < 16; f++) {
                g.drawRect(x, y, 20, 20);
                x += 20;
            }
            y += 20;
            x -= 320;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
