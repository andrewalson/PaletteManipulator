package model;

public interface ReductionAlgorithm {

    static String RANDOM = "Random";
    static String MOST_FREQUENT_BY_REGION = "Most frequent by region";

    void selectColors();

    Picture getReducedPalettePicture();

    Picture generateReducedPalettePicture(int numberColors, Picture picture);


}
