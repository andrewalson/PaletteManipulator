package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.swing.ImageIcon;

/**
 * The Picture class provides access to the pixels in an image, allows the pixels
 * to be modified and provides functionality to display an image on an
 * objectdraw canvas.
 * 
 * @author Barbara Lerner
 * @version November 26, 2011
 */
public class Picture {
    // The pixels that make up the image.

    private int[] pixels;
    // The number of pixels wide that the image is
    private int imgWidth;
    // The number of rows of pixels in the image.
    private int imgHeight;
    // The visible display of the image
    private ImageIcon icon;

    /**
     * Load a picture from a file.
     * @param file the name of the file containing an image to load.  The filename
     * cannot be null.  An error message will be displayed to the user if the value
     * does not correspond to a readable file containing an image.
     */
    public Picture(File file) {
        assert file != null;

        icon = new ImageIcon(file.getPath());
        Image image = icon.getImage();
        imgWidth = icon.getIconWidth();
        imgHeight = icon.getIconHeight();
        pixels = new int[imgWidth * imgHeight];
        extractPixels(image);
    }

    /**
     * Creates an empty pixel array
     * @param width the width of the picture, in pixels
     * @param height the height of the picture, in pixels
     */
    protected Picture(int width, int height) {
        imgWidth = width;
        imgHeight = height;
        pixels = new int[imgWidth * imgHeight];
    }

    /**
     * Extract the pixels from the image
     * @param image the image to get the pixels from
     */
    private void extractPixels(Image image) {
        PixelGrabber pg = new PixelGrabber(image, 0, 0, imgWidth, imgHeight, pixels, 0, imgWidth);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            System.err.println("interrupted waiting for pixels!");
            pixels = null;
            return;
        }
        if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
            System.err.println("image fetch aborted or image not found");
            pixels = null;
            return;
        }
    }

    /**
     * Creates a viewable image from a pixel array.
     */
    protected void createIconFromPixels() {
        icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(imgWidth, imgHeight, pixels, 0, imgWidth)));
    }

    /**
     * @return the number of columns of pixels in the image.  Assumes that there was no
     * error when the Picture object was constructed.
     */
    public int getWidth() {
        assert pixels != null;
        return imgWidth;
    }

    /**
     * @return the number of rows of pixels in the image.  Assumes that there was no
     * error when the Picture object was constructed.
     */
    public int getHeight() {
        assert pixels != null;
        return imgHeight;
    }

    /**
     * Get the color of a pixel at a particular location.  This
     * method will fail (with an ArrayIndexOutOfBoundsException)
     * if row or col are negative or are beyond the bounds of the image.  
     * It will fail (with NullPointerException) if the image file was
     * not read successfully.
     * @param row the row of the pixel
     * @param col the column of the pixel
     * @return the color of the requested pixel
     */
    public Color getPixel(double row, double col) {
        assert pixels != null;
        assert row >= 0 && row < imgHeight;
        assert col >= 0 && col < imgWidth;
        return new Color(pixels[(int) row * imgWidth + (int) col]);
    }

    /**
     * Change the color of a pixel at a particular location.  This method 
     * does nothing if row or col are negative or are beyond the dimensions of 
     * the image. It will fail (with NullPointerException) if the image file was
     * not read successfully.
     * @param row the row of the pixel
     * @param col the column of the pixel
     * @param color the new color for the pixel.  Should not be null.
     */
    public void setPixel(double row, double col, Color color) {
        assert pixels != null;
        assert row >= 0 && row < imgHeight;
        assert col >= 0 && col < imgWidth;
        assert color != null;

        int row1 = (int) row;
        int col1 = (int) col;
        if (row1 >= 0 && row1 < imgHeight && col1 >= 0 && col1 < imgWidth) {
            pixels[row1 * imgWidth + col1] = color.getRGB();
        }
    }

    /**
     * Draws the image on the display.  Assumes that the image was successfully 
     * read from the file.
     * @param panel the panel to draw in
     * @param g the graphics object being used in the panel
     * @param x the leftmost column for the image
     * @param y the topmost row for the image.
     */
    public void paint(Component panel, Graphics g, int x, int y) {
        assert pixels != null;

        icon.paintIcon(panel, g, x, y);
    }

    /**
     * Create a scaled image from the pixels
     * @param scaling the % of the original size that the scaled image should be.
     */
    public void scaleImage(double scaling) {
        int scaledImgWidth = (int) (scaling * imgWidth);
        int scaledImgHeight = (int) (scaling * imgHeight);
        Image image = icon.getImage().getScaledInstance(scaledImgWidth, scaledImgHeight, Image.SCALE_SMOOTH);
        icon.setImage(image);
    }
}