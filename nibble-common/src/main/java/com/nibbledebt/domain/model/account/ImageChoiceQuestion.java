package com.nibbledebt.domain.model.account;

/**
 * @author a.salachyonok
 */

public class ImageChoiceQuestion extends Question{

    private ImageChoice[] imageChoice;

    public ImageChoice[] getImageChoice() {
        return imageChoice;
    }

    public void setImageChoice(ImageChoice[] imageChoice) {
        this.imageChoice = imageChoice;
    }
}
