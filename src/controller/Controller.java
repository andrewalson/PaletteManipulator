package controller;

import com.sun.tools.javac.Main;
import model.Picture;
import view.MainView;

import javax.swing.*;

public class Controller {

    static MainView mainView = new MainView();

    public static void setOriginalPicture(Picture picture) {
        mainView.setPictureOnOriginal(picture);
    }

    public static void generateReducedPalettePicture(int numberColors, String algorithmType) {
        mainView.generateReducedPalettePicture(numberColors, algorithmType);
    }

    // create a static method called generateReducedPalettePicture which takes in an integer that represents the
    // numbers of colors chosen to reduce to as well as a String representing the chosen reduction algorithm
    //TODO in this method, you should call a method in the MainView called generateReducedPalettePicture which also
    // takes in the integer and the string . in the method in mainview, print out the parameters to confirm it has the right thing
}
