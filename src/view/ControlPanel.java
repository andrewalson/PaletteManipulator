package view;

import controller.Controller;
import model.Picture;
import model.ReductionAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel {

    JPanel controlPanel = new JPanel();

    JComboBox<Integer> numberOfColorsToReduceToDropdown = new JComboBox(new Integer[]{4, 16, 64, 256});
    JComboBox<String> reductionAlgorithmDropdown = new JComboBox(new String[] {ReductionAlgorithm.RANDOM, ReductionAlgorithm.MOST_FREQUENT_BY_REGION});

    JFileChooser photoSelector = new JFileChooser(System.getProperty("user.home"));

    public ControlPanel() {

        controlPanel.setBackground(Color.YELLOW);

        JButton loadPhotoButton = new JButton("Load Photo");
        controlPanel.add(loadPhotoButton);

        JButton generatePaletteButton = new JButton("Generate Palette");
        controlPanel.add(generatePaletteButton);

        controlPanel.add(numberOfColorsToReduceToDropdown);
        controlPanel.add(reductionAlgorithmDropdown);

        loadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (photoSelector.showOpenDialog(controlPanel) == JFileChooser.APPROVE_OPTION) {
                    photoSelector.getSelectedFile();

                    Picture original = new Picture(photoSelector.getSelectedFile());
                    Controller.setOriginalPicture(original);
                }
            }
        });

        generatePaletteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberColors = (int) numberOfColorsToReduceToDropdown.getSelectedItem();
                String algorithmType = (String) reductionAlgorithmDropdown.getSelectedItem();
                Controller.generateReducedPalettePicture(numberColors, algorithmType);
            }
        });

        // create number of color JComboBox, add options (itwms) 4, 16, 64, 256
        // create palette reduction algorithm JComboBox, add options "Random" and "Most Frequent By Region"

        // create a "generate palette" button, add action listener.
        // when this button is clicked, get the selected values from the #ofcolors JComboBox, and the reduction algorithm JComboBox
        //TODO call a method in the Controller called generateReducedPalettePicture and pass in as parameters the number of colors and the reduction algorithm chosen
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}
