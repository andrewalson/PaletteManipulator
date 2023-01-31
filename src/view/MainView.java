package view;

import model.MostFrequentColorByRegionAlgorithm;
import model.Picture;
import model.RandomColorsReductionAlgorithm;
import model.ReductionAlgorithm;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    // create an interface in the Model package called RedutionAlgorithm
    // define two methods in the ReductionAlgorithm ingerface, one is called selectColors() and it returns nothing, and the other is getReducedPalettePicture() which returns a Picture
    // create a class in the Model package called RandomColorsReductionAlgorithm that implements the interface ReductionAlgorithm
    // create a class in the Model package called MostFrequentColorByRegionReductionAlgorithm that implements the interface ReductionAlgorithm

    PicturePanel originalPicturePanel = new PicturePanel();
    PicturePanel newPicturePanel = new PicturePanel();
    PaletteDiagramPanel paletteDiagramPanel = new PaletteDiagramPanel();

    MostFrequentColorByRegionAlgorithm mostFrequentColorByRegionAlgorithm = new MostFrequentColorByRegionAlgorithm();

    RandomColorsReductionAlgorithm randomColorsReductionAlgorithm = new RandomColorsReductionAlgorithm();

    Picture originalPicture;

    public MainView() {
        super();
        this.getContentPane().setLayout(new BorderLayout());
        ControlPanel controlPanelHolder = new ControlPanel();
        JPanel controlPanel = controlPanelHolder.getControlPanel();
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.X_AXIS));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayPanel.add(originalPicturePanel);
        displayPanel.add(newPicturePanel);
        displayPanel.add(paletteDiagramPanel);

        this.add(displayPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.pack();
    }

    public void setPictureOnOriginal(Picture picture) {
        if (picture != null) {
            originalPicture = picture;
            originalPicturePanel.setPicture(picture);
        }
    }

    public void generateReducedPalettePicture(int numberColors, String algorithmType) {
        //System.out.println(numberColors + algorithmType);
        if (algorithmType.equals(ReductionAlgorithm.RANDOM)) {
            randomColorsReductionAlgorithm.generateReducedPalettePicture(numberColors, originalPicture);
        } else {

        }
    }

}
