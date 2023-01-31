package view;

import model.Picture;

import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {

    Picture picture;

    public PicturePanel() {
        this.setBorder(BorderFactory.createTitledBorder("Before"));
    }

    public void setPicture(Picture picture) {
        if (picture != null) {
            double horizontalScaling = (double) this.getWidth() / picture.getWidth();
            double verticalScaling = (double) this.getHeight() / picture.getHeight();
            double scale = Math.min(horizontalScaling, verticalScaling);
            if (scale < 1) {
                picture.scaleImage(scale);
            }
        }
        this.picture = picture;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        assert g != null;
        super.paintComponent(g);

        if (picture != null) {
            picture.paint(this, g, 10, 10);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 600);
    }

    // method to set border title
    // pass in border panel title

}
